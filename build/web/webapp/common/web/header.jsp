<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <style>
        .container {
            max-width: 1140px !important;
            margin-left: auto !important;
            margin-right: auto !important;
        }
        #notification-bell {
            position: relative;
            border: none;
            color: #333;
            font-size: 1.5rem;
            cursor: pointer;
            padding: 5px 10px;
            background: none;
            transition: color 0.3s ease;
        }

        #notification-bell:hover {
            color: #007bff;
        }

        #notification-count {
            position: absolute;
            top: -5px;
            right: -5px;
            background-color: #dc3545;
            color: white;
            border-radius: 50%;
            font-size: 0.8rem;
            padding: 2px 6px;
            font-weight: bold;
        }

        #notification-list {
            width: 250px;
            max-height: 300px;
            overflow-y: auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 10px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        /* Notification list items */
        #notification-list .dropdown-item {
            padding: 8px 12px;
            color: #333;
            font-size: 0.9rem;
            border-bottom: 1px solid #eee;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }

        #notification-list .dropdown-item:last-child {
            border-bottom: none;
        }

        #notification-list .dropdown-item:hover {
            background-color: #f8f9fa;
        }
    </style>
    <header class="header">
        <div class="contact">
            <div class="container">
                <ul class="list-contact" style="align-items: center">
                    <li class="item-contact">
                        <a href="#">093 186 3366 | 096 361 4566</a>
                    </li>
                    <li class="item-contact"><a href="#">Hỗ trợ</a></li>
                    <li class="item-contact"><a href="#">Tuyển dụng</a></li>
                    <li class="item-contact"><a href="#">Liên hệ</a></li>
                        <c:choose>
                            <c:when test="${not empty sessionScope.USER_CURRENT}">
                            <li class="item-contact">
                                <a href="<c:url value='/account'/>" style="display: flex; align-items: center; gap: 5px;">
                                    <span>Xin chào, ${sessionScope.USER_CURRENT}</span>
                                    <img  src="<c:url value='/uploads/${sessionScope.PHOTOS_CURRENT}'/>" style="height: 30px; width: 30px; object-fit: cover;"   alt="photos"/>
                                </a>
                            </li>
                            <li>
                                <div class="dropdown">
                                    <button id="notification-bell" class="btn btn-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        <span class="badge bg-danger" id="notification-count">0</span>
                                        <i class="fa fa-bell" style="font-size: 1.5rem;"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="notification-bell" id="notification-list">
                                    </ul>
                                </div>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li class="item-contact">
                                <a href="<c:url value='/login'/>">Đăng nhập</a>
                            </li>
                            <li class="item-contact">
                                <a href="<c:url value='/register'/>">Đăng kí</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <div class="menu">
            <div class="container">
                <a href="<c:url value="/home"/>">
                    <img src="/Healthcare/webapp/assets/images/logo.jpg" alt="" />
                </a>
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/home"/>">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Về chúng tôi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/services'/>">Dịch vụ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/doctors'/>">Đội ngũ bác sĩ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Khách hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/advisor'/>">Chuyên gia tư vấn</a>
                    </li>
                </ul>
                <button class="btn-small" id="appointment">
                    <i class="fa-solid fa-calendar-check"></i> Đặt lịch hẹn
                </button>
            </div>
        </div>

        <script>
            document.getElementById('appointment').onclick = function () {
                window.location.href = '<c:url value="/appointment" />';
            };

            document.addEventListener("DOMContentLoaded", function () {
                setInterval(fetchNotifications, 30000);
                fetchNotifications();
                document.getElementById("notification-bell").addEventListener("click", markNotificationsAsRead);

            });

            function fetchNotifications() {
                fetch('/Healthcare/api/notification')
                        .then(response => response.json())
                        .then(data => {
                            updateNotificationList(data);
                            updateNotificationCount(data.length);
                        })
                        .catch(error => console.error("Error fetching notifications:", error));
            }

            function updateNotificationList(notifications) {
                const notificationList = document.getElementById("notification-list");
                notificationList.innerHTML = '';

                if (notifications.length === 0) {
                    notificationList.innerHTML = '<li class="dropdown-item">No new notifications</li>';
                } else {
                    notifications.forEach(notification => {
                        const listItem = document.createElement("li");
                        listItem.classList.add("dropdown-item");
                        listItem.textContent = notification.message;
                        notificationList.appendChild(listItem);
                    });
                }
            }

            function updateNotificationCount(count) {
                document.getElementById("notification-count").textContent = count;
            }

            function markNotificationsAsRead() {
                fetch('/Healthcare/api/notification', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                        .then(response => {
                            if (response.ok) {
                            } else {
                                console.error("Error marking notifications as read.");
                            }
                        })
                        .catch(error => console.error("Error in POST request:", error));
            }
        </script>

    </header>
</html>
