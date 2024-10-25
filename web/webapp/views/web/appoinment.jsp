<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/reset.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/base.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/appoinment.css"/>">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
    </head>
    <body>
        <style>
            button:disabled {
                background-color: gray;
                color: white;
                cursor: not-allowed;
                opacity: 0.5;
            }
        </style>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->        
        <p class="b">Trang chủ >> Đặt lịch khám</p>
        <h1>Ưu đãi mới nhất</h1>       
        <img src="webapp/assets/images/banner.jpg" alt="Ưu đãi">
        <div class="c">
            <h1>Đặt lịch ngay!</h1>
            <form action="<c:url value='/appointment'/>" method="post" onsubmit="return validateForm()">
                <div class="fr">
                    <div class="fg">
                        <label for="name">Họ tên *</label>
                        <input type="text" id="name" name="name" placeholder="Họ tên" required>
                    </div>
                    <div class="fg">
                        <label for="phone">Số điện thoại *</label>
                        <input type="text" id="phone" name="phone" placeholder="Số điện thoại" required>
                        <small id="phoneError" style="color: red; display: none;">Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại Việt Nam.</small>
                    </div>
                </div>

                <div class="fr">
                    <div class="fg">
                        <label for="date">Thời gian đặt lịch *</label>
                        <input type="date" id="date" name="date" required value="<%= currentDate %>" onchange="fetchTimeSlots()">
                    </div>
                    <div class="fg">
                        <label for="doctor">Bác sĩ*</label>
                        <select id="doctor" name="doctorId" onchange="fetchTimeSlots()">
                            <c:forEach var="doctor" items="${listDoctor}">
                                <option value="${doctor.id}">${doctor.firstName} ${doctor.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ts" id="ts"> 
                    <input type="hidden" id="timeSlotId" name="timeSlotId">
                    <c:forEach items="${timeSlots}" var="slot">
                        <button 
                            type="button" 
                            onclick="selectTimeSlot(this, ${slot.id})"
                            <c:if test="${slot.isBooked}">disabled</c:if>
                                >
                            ${slot.time}
                        </button>
                    </c:forEach>
                </div>
                <div class="fg">
                    <label for="content">Dịch vụ*</label>
                    <select id="content" name="serviceId">
                        <c:forEach var="service" items="${mapService}">
                            <option value="${service.key}">${service.value.serviceName}  (<fmt:formatNumber value="${service.value.price}" type="currency" />)</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="fg">
                    <label for="note">Ghi chú</label>
                    <input type="text" id="note" name="note" placeholder="Ghi chú">
                </div>

                <button type="submit" class="submit-btn">Đặt lịch hẹn!</button>
            </form>

        </div>

        <div class="ct">
            <section class="ct_i">
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/11/bsduc.png">
            </section>
            <div class="ct_c">
                <h2>Đăng ký dịch vụ tư vấn miễn phí</h2>
                <<h3>Vui lòng để lại thông tin của bạn tại đây, để chúng tôi có thể giúp bạn</h3>
                <div class="ct_f">
                    <form action="">
                        <div class="ig"> 
                            <input type="text" placeholder="Họ và tên">
                            <input type="text" placeholder="Điện thoại">
                        </div>
                        <input type="text" placeholder="Dịch vụ cần tư vấn">
                        <textarea placeholder="Ghi chú"></textarea>
                        <button type="submit">Đăng ký ngay</button>
                    </form>
                </div>
            </div>
        </div>

        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->

        <script>
            function fetchTimeSlots() {
                const doctorId = document.getElementById('doctor').value;
                const appointmentDate = document.getElementById('date').value;
                const timeSlotsContainer = document.querySelector('#ts');
                timeSlotsContainer.innerHTML = '';

                fetch(`/Healthcare/api/slot?doctorId=` + doctorId + `&date=` + appointmentDate)
                        .then(response => response.json())
                        .then(data => {
                            const hiddenInput = document.createElement('input');
                            hiddenInput.type = 'hidden';
                            hiddenInput.id = 'timeSlotId';
                            hiddenInput.name = 'timeSlotId';
                            timeSlotsContainer.appendChild(hiddenInput);
                            data.forEach(slot => {
                                const button = document.createElement('button');
                                button.type = 'button';
                                button.innerText = slot.time;
                                button.setAttribute('onclick', `selectTimeSlot(this, ` + slot.id + ` )`);
                                if (slot.isBooked) {
                                    button.disabled = true;
                                }
                                button.classList.toggle('disabled', slot.isBooked);
                                timeSlotsContainer.appendChild(button);
                            });
                        })
                        .catch(error => {
                            console.error('Error fetching time slots:', error);
                        });
            }

            function validatePhoneNumber(phoneNumber) {
                const vietnamPhoneRegex = /^(0[3|5|7|8|9])+([0-9]{8})$/;
                return vietnamPhoneRegex.test(phoneNumber);
            }

            function validateForm() {
                const phoneInput = document.getElementById('phone');
                const phoneError = document.getElementById('phoneError');

                if (!validatePhoneNumber(phoneInput.value)) {
                    phoneError.style.display = 'block'; 
                    return false; 
                } else {
                    phoneError.style.display = 'none'; 
                    return true; 
                }
            }

            function selectTimeSlot(button, slotId) {
                document.getElementById('timeSlotId').value = slotId;
                var buttons = document.querySelectorAll('.ts button');

                buttons.forEach(function (btn) {
                    btn.style.backgroundColor = '';
                });
                button.style.backgroundColor = 'blue';
            }

            function setDateLimits() {
                const dateInput = document.getElementById('date');
                const today = new Date();
                const dd = String(today.getDate()).padStart(2, '0');
                const mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0
                const yyyy = today.getFullYear();
                const formattedToday = yyyy + '-' + mm + '-' + dd;
                dateInput.setAttribute('min', formattedToday);
                today.setDate(today.getDate() + 7);
                const maxDd = String(today.getDate()).padStart(2, '0');
                const maxMm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0
                const maxYyyy = today.getFullYear();
                const formattedMaxDate = maxYyyy + '-' + maxMm + '-' + maxDd;

                dateInput.setAttribute('max', formattedMaxDate);
            }

            window.onload = setDateLimits;

            function selectTimeSlot(button, slotId) {
                document.getElementById('timeSlotId').value = slotId;
                var buttons = document.querySelectorAll('.ts button');

                buttons.forEach(function (btn) {
                    btn.style.backgroundColor = '';
                });
                button.style.backgroundColor = 'blue';
            }
        </script>
    </body>
</html>
