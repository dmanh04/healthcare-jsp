<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <style>
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

    <body>
        <div class="header">
            <div class="analytics">
                <<i class="fa-solid fa-envelope fa-2x"></i>
            </div>
            <c:if test="${sessionScope.ROLE_CURRENT == 'DOCTOR'}">
                <div class="dropdown">
                    <button id="notification-bell" class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <span class="badge bg-danger" id="notification-count">0</span>
                        <i class="fa fa-bell" style="font-size: 1.5rem;"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="notification-bell" id="notification-list">
                    </ul>
                </div>
            </c:if>
            <div class="user-profile">
                <c:if test="${not empty sessionScope.USER_CURRENT}">
                    <span>Xin chào, ${sessionScope.USER_CURRENT}</span>
                </c:if>
                <c:if test="${empty sessionScope.USER_CURRENT}">
                    <span>Xin chào, khách!</span>
                </c:if>
                <c:if test="${not empty sessionScope.PHOTOS_CURRENT}">
                    <img src="<c:url value='/uploads/${sessionScope.PHOTOS_CURRENT}'/>" alt="Periodontal Icon"/>
                </c:if>
                <c:if test="${empty sessionScope.PHOTOS_CURRENT}">
                    <img src="<c:url value='/webapp/assets/images/periodontal-icon.png' />" alt="Periodontal Icon"/>

                </c:if>
            </div> 
        </div>
    </div>

    <script>
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
</body>
</html>
