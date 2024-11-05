<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/reset.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/base.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/style.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/account.css'/>">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script>
            function completeAppointment(recordId, appointmentId, recipientUserId) {
                const diagnosis = document.getElementById('diagnosis' + appointmentId).value;
                const treatment = document.getElementById('treatment' + appointmentId).value;
                fetch('/Healthcare/api/record/action', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        recordId: recordId,
                        appointmentId: appointmentId,
                        recipientUserId: recipientUserId,
                        diagnosis: diagnosis,
                        treatment: treatment
                    })
                })
                        .then(response => response.json())
                        .then(data => {
                            alert('Appointment rejected successfully! success');
                            location.reload();
                        })
                        .catch(error => console.error('Error:', error));
            }
            function callApiAndShowModel(appointmentId) {
                const apiUrl = "/Healthcare/api/record/action?appointmentId=" + appointmentId;
                $.ajax({
                    url: apiUrl,
                    method: 'GET',
                    success: function (medicalRecord) {
                        $('#modalTitle').text("Medical Record for Appointment ID: " + appointmentId);
                        $('#modalContent').html(
                                "<p>Diagnosis: " + medicalRecord.diagnosis + "</p>" +
                                "<p>Treatment: " + medicalRecord.treatment + "</p>"
                                );
                        $('#medicalRecordModal').modal('show');
                    },
                    error: function () {
                        alert('Medical record not found');
                    }
                });
            }

            function prescriptions(appointmentId) {
                fetch(`/Healthcare/api/prescriptions?appointmentId=` + appointmentId)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.json();
                        })
                        .then(data => {
                            showPrescriptionsModal(data);
                        })
                        .catch(error => {
                            console.error('Error fetching prescriptions:', error);
                            alert('Có lỗi xảy ra khi lấy đơn thuốc.');
                        });
            }
            function showPrescriptionsModal(prescriptions) {
                const prescriptionsTableBody = document.querySelector('#prescriptionsTable tbody');
                const selectAllCheckbox = document.querySelector('#selectAllCheckbox');
                const buyButton = document.querySelector('#buyButton');
                prescriptionsTableBody.innerHTML = '';
                let totalPrice = 0;

                selectAllCheckbox.checked = false;
                selectAllCheckbox.removeEventListener('change', toggleAllCheckboxes);
                buyButton.removeEventListener('click', handleBuy);

                prescriptions.forEach((prescription, index) => {
                    const rowPrice = prescription.medicine.price * prescription.quantityPrescribed;
                    totalPrice += rowPrice;

                    const row =
                            '<tr>' +
                            '<td><input type="checkbox" class="prescription-checkbox" data-index="' + index + '"></td>' +
                            '<td>' + (index + 1) + '</td>' +
                            '<td>' + prescription.medicine.medicineName + '</td>' +
                            '<td>' + prescription.quantityPrescribed + '</td>' +
                            '<td>' + prescription.medicine.price + ' VND</td>' +
                            '<td>' + prescription.notes + '</td>' +
                            '<td class="row-price">' + rowPrice + ' VND</td>' +
                            '</tr>';
                    prescriptionsTableBody.innerHTML += row;
                });

                const totalRow =
                        '<tr id="totalRow">' +
                        '<td colspan="6" style="text-align:right;"><strong>Tổng giá:</strong></td>' +
                        '<td><strong id="totalPriceDisplay">' + totalPrice + ' VND</strong></td>' +
                        '</tr>';
                prescriptionsTableBody.innerHTML += totalRow;

                $('#prescriptionsModal').modal('show');

                function toggleAllCheckboxes() {
                    const checkboxes = document.querySelectorAll('.prescription-checkbox');
                    checkboxes.forEach(checkbox => checkbox.checked = selectAllCheckbox.checked);
                    updateTotalPrice();
                }

                function updateTotalPrice() {
                    let updatedTotalPrice = 0;
                    document.querySelectorAll('.prescription-checkbox').forEach((checkbox, index) => {
                        if (checkbox.checked) {
                            const rowPrice = prescriptions[index].medicine.price * prescriptions[index].quantityPrescribed;
                            updatedTotalPrice += rowPrice;
                        }
                    });
                    document.getElementById('totalPriceDisplay').innerHTML = updatedTotalPrice + ' VND';
                }

                document.querySelectorAll('.prescription-checkbox').forEach(checkbox => {
                    checkbox.addEventListener('change', updateTotalPrice);
                });

                selectAllCheckbox.addEventListener('change', toggleAllCheckboxes);

                function handleBuy() {
                    const selectedPrescriptions = [];
                    document.querySelectorAll('.prescription-checkbox:checked').forEach(checkbox => {
                        const index = parseInt(checkbox.getAttribute('data-index'), 10);
                        selectedPrescriptions.push(prescriptions[index].id);
                    });

                    if (selectedPrescriptions.length > 0) {
                        fetch('/Healthcare/api/medicine-purchases', {
                            method: 'POST',
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify({listPrescriptionsId: selectedPrescriptions})
                        })
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Network response was not ok ' + response.statusText);
                                    }
                                    return response.json();
                                })
                                .then(data => {
                                    alert(data);
                                    selectedPrescriptions.forEach(prescriptionId => {
                                        const button = document.getElementById(`showPrescriptions${prescriptionId}`);
                                        if (button) {
                                            button.remove(); // Remove the button from the DOM
                                        }
                                    });

                                    $('#prescriptionsModal').modal('hide');
                                    window.location.reload();
                                })
                                .catch(error => {
                                    console.error('Error:', error);
                                    alert('Có lỗi xảy ra trong quá trình mua: ' + error.message); // Improved error alert
                                });
                    } else {
                        alert('Vui lòng chọn ít nhất một đơn thuốc.');
                    }
                }
                buyButton.addEventListener('click', handleBuy);
            }


        </script>
    </head>
    <body>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->

        <style>
            .table {
                width: 100%;
                background-color: #ffffff;
                border-collapse: collapse;
                border: 1px solid #ddd;
                margin-top: 20px;
            }

            .table th, .table td {
                padding: 12px;
                text-align: left;
                border: 1px solid #ddd;
                font-size: 1.4rem;
            }

            .table thead th {
                background-color: var(--color-yellow);
                color: white;
                font-weight: bold;
                text-transform: uppercase;
                font-size: 1.6rem;
            }

            .table tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .table tbody tr:hover {
                background-color: #ddd;
            }

            .table th {
                font-size: 1rem;
                text-align: center;
            }

            .table td {
                font-size: 1.3rem;
                color: #333;
                text-align: center;
            }


            .toast-header {
                background-color: #4CAF50;
                color: white;
            }

            .toast-body {
                color: #333;
            }

            .breadcum {
                font-size: 1.2rem;
                color: #666;
                margin-bottom: 15px;
            }

            .status-confirmed {
                background-color: #d4edda;
                color: #155724;
                font-weight: bold;
                padding: 5px;
                border-radius: 4px;
            }
            .status-cancelled {
                background-color: #f8d7da;
                color: #721c24;
                font-weight: bold;
                padding: 5px;
                border-radius: 4px;
            }
            .status-completed {
                background-color: #d1ecf1;
                color: #0c5460;
                font-weight: bold;
                padding: 5px;
                border-radius: 4px;
            }
            .status-pending {
                background-color: #fff3cd;
                color: #856404;
                font-weight: bold;
                padding: 5px;
                border-radius: 4px;
            }
            .cancel-btn {
                background-color: #dc3545;
                color: #ffffff;
                padding: 5px 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
            }
            .cancel-btn:hover {
                background-color: #c82333;
            }
        </style>

        <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="position: fixed; top: 20px; right: 20px; z-index: 1050;">
            <div class="toast-header">
                <strong class="me-auto" style="font-size: 1.4rem;">Notification</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <p>Appointment updated successfully!</p>
            </div>
        </div>

        <div class="account">
            <div class="content">
                <div class="sub-menu">
                    <li class="sub-name">
                        <img src="<c:url value='/uploads/${sessionScope.PHOTOS_CURRENT}'/>" alt="photos"/>
                        <span>${sessionScope.USER_CURRENT}</span>
                    </li>
                    <ul class="sub-nav">
                        <li class="sub-item"><a href="<c:url value='account'/>" class="sub-link">Cập nhật hồ sơ</a></li>
                        <li class="sub-item"><a href="<c:url value='manage-appointment'/>" class="sub-link">Quản lý hoạt động</a></li>
                        <li class="sub-item"><a href="<c:url value='change-password'/>" class="sub-link">Đổi mật khẩu</a></li>
                        <li class="sub-item"><a href="<c:url value='/logout'/>" class="sub-link">Đăng xuất</a></li>
                    </ul>
                </div>

                <div class="body">
                    <div class="breadcum">Trang chủ / Quản lý lịch hẹn</div>
                    <div class="main-content">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Id cuộc hẹn</th>
                                    <th>Người đặt khám</th>
                                    <th>Bác sĩ</th>
                                    <th>Dịch vụ</th>
                                    <th>Ngày hẹn</th>
                                    <th>Giờ</th>
                                    <th>Trạng thái</th>
                                    <th>Ghi chú</th>
                                    <th>Số điện thoại</th>
                                    <th>Hoạt động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="appointment" items="${listAppointment}">
                                    <tr>
                                        <td>${appointment.id}</td>
                                        <td>${appointment.customerName}</td>
                                        <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
                                        <td>${appointment.services.serviceName}</td>
                                        <td><fmt:formatDate value="${appointment.date}" pattern="yyyy-MM-dd" /></td>
                                        <td>${appointment.timeSlot.time}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${appointment.status == 'CONFIRMED'}">
                                                    <span class="status-confirmed">${appointment.status}</span>
                                                </c:when>
                                                <c:when test="${appointment.status == 'CANCELLED'}">
                                                    <span class="status-cancelled">${appointment.status}</span>
                                                </c:when>
                                                <c:when test="${appointment.status == 'COMPLETED'}">
                                                    <span class="status-completed">${appointment.status}</span>
                                                </c:when>
                                                <c:when test="${appointment.status == 'PENDING'}">
                                                    <span class="status-pending">${appointment.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${appointment.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${appointment.notes}</td>
                                        <td>${appointment.phone}</td>
                                        <td>
                                            <c:if test="${appointment.status == 'PENDING' or appointment.status == 'CONFIRMED'}">
                                                <button type="button" class="cancel-btn" 
                                                        onclick="setIdCancel(${appointment.id}, ${appointment.doctor.id})" 
                                                        data-bs-toggle="modal" data-bs-target="#confirmModal">Hủy lịch hẹn</button>
                                            </c:if>
                                            <c:if test="${appointment.status == 'COMPLETED'}">
                                                <button id="detailAppointment${appointment.id}" type="button" 
                                                        title="History appointment" 
                                                        onclick="callApiAndShowModel(${appointment.id})"
                                                        class="btn" style="border: 0 !important; padding: 4px 10px !important; background-color: gray !important;">
                                                    <i class="fa-solid fa-file-medical"  style="font-size: 15px; color: #E1C9B0; margin-left: 0;"></i>
                                                </button>

                                                <button id="showPrescriptions${appointment.id}" type="button" 
                                                        title="Prescriptions appointment" 
                                                        onclick="prescriptions(${appointment.id})"
                                                        class="btn" style="border: 0 !important; padding: 4px 10px !important; background-color: #1492E6 !important;">
                                                    <i class="fa-solid fa-prescription-bottle-medical" style="font-size: 15px; color: white; margin-left: 0;"></i>
                                                </button>        
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div> 

        <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel" style="font-size: 20px">Xác nhận hủy lịch hẹn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" style="font-size: 15px">
                        Bạn có chắc chắn muốn hủy lịch hẹn này?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" style="font-size: 14px !important;" data-bs-dismiss="modal">Đóng</button>
                        <form action="account/cancel" method="post">
                            <input id="idCancel" name="idCancel" type="hidden"/>
                            <input id="doctorId" name="doctorId" type="hidden"/>
                            <button type="button" class="btn btn-danger" style="background-color: red !important; font-size: 14px !important;" id="confirmCancelButton">Hủy lịch hẹn</button>
                        </form>        
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="medicalRecordModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitle">Medical Record</h5>
                    </div>
                    <div class="modal-body" id="modalContent">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="prescriptionsModal" tabindex="-1" role="dialog" aria-labelledby="prescriptionsModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="prescriptionsModalLabel">Đơn thuốc</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered table-hover" id="prescriptionsTable">
                            <thead class="table-light">
                                <tr>
                                    <th><input type="checkbox" id="selectAllCheckbox"></th> <!-- Select All Checkbox -->
                                    <th>#</th>
                                    <th>Tên thuốc</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Cách dùng</th>
                                    <th>Tổng cộng</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="buyButton" class="btn btn-primary">Mua</button>
                        <button type="button" class="btn btn-danger" style="background-color: red !important;" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>


        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

        <script>
                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                const urlParams = new URLSearchParams(window.location.search);
                                                                const success = urlParams.get("success");
                                                                if (success === "true") {
                                                                    const toastEl = document.getElementById("toast");
                                                                    const toast = new bootstrap.Toast(toastEl);
                                                                    toast.show();
                                                                }
                                                            });
                                                            function setIdCancel(appointmentId, doctorId) {
                                                                document.getElementById('idCancel').value = appointmentId;
                                                                document.getElementById('doctorId').value = doctorId;
                                                            }
                                                            document.getElementById('confirmCancelButton').addEventListener('click', function () {
                                                                this.closest('form').submit();
                                                            });
        </script>
    </body>
</html>
