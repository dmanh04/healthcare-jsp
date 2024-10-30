<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin service</title>
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/reset.css"/>">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/base.css"/>">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/style_admin.css"/>">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
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

            document.getElementById('medicalRecordModal').addEventListener('hidden.bs.modal', function () {
                if (medicalRecordModal) {
                    medicalRecordModal.hide();
                }
            });

        </script>
    </head>
    <body>

        <style>

            #errorMessage {
                color: red;
                font-weight: bold;
                display: block;
                margin-bottom: 15px;
            }
            .header-appointment {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 25px;
                padding: 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            .out-btn{
                display: flex;
                gap: 5px;
                justify-content: end;
            }
            .pagination-container {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 20px 0;
            }

            .pagination-container .page-item {
                margin: 0 5px;
            }

            .pagination-container .page-link {
                padding: 10px 15px;
                border-radius: 5px;
                text-decoration: none;
                color: #ffffff;
                background-color: #007bff;
                transition: background-color 0.3s ease, transform 0.3s ease;
            }

            .pagination-container .page-link:hover {
                background-color: #0056b3;
                transform: scale(1.05);
            }

            .pagination-container .disabled {
                background-color: #ccc;
                color: #666;
                pointer-events: none;
            }

            .pagination-container .active {
                background-color: #0056b3;
                color: #ffffff;
                font-weight: bold;
            }

            .pagination-container .btn-secondary {
                background-color: #6c757d;
                color: #fff;
            }

            .pagination-container .btn-secondary:hover {
                background-color: #5a6268;
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
            .fs-18{
                margin: 0px;
                font-size: 15px;
            }
            .col-3{
                margin-bottom: 15px !important;
            }
        </style>

        <!--Header-->
        <%@include file="../../common/admin/header.jsp" %>
        <!--End Header-->
        <!--Menu-->
        <%@include file="../../common/admin/menu.jsp" %>
        <!--End Menu-->
        <div class="content">
            <c:if test="${param.edit == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User updated successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${param.add == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User added successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <c:if test="${param.remove == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User with ID <strong>${param.id}</strong> deleted successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="header-appointment">
                <form class="formSearch" action="<c:url value='/admin/appointment'/>" method="get">
                    <div class="row">
                        <div class="col-3">
                            <label>Start Date</label>
                            <div class="form-group">
                                <input name="startDate" type="date" class="form-control" value="${param.startDate != null ? param.startDate : ''}" />
                            </div>
                        </div>
                        <div class="col-3">
                            <label>End Date</label>
                            <div class="form-group">
                                <input name="endDate" type="date" class="form-control" value="${param.endDate != null ? param.endDate : ''}" />
                            </div>
                        </div>
                        <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN' or sessionScope.ROLE_CURRENT == 'STAFF'}">
                            <div class="col-3">
                                <label>Service</label>
                                <div class="form-group">
                                    <select id="service" name="serviceId" class="form-select">
                                        <option value="">Select service</option>
                                        <c:forEach var="service" items="${listService}">
                                            <option value="${service.id}" ${param.serviceId == service.id ? 'selected' : ''}>${service.serviceName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <div class="col-3">
                            <label>Customer Name</label>
                            <div class="form-group">
                                <input name="customerName" type="text" class="form-control" value="${param.customerName != null ? param.customerName : ''}" />
                            </div>
                        </div>
                        <div class="col-3">
                            <label>Status</label>
                            <div class="form-group">
                                <select name="status" class="form-select">
                                    <option value="">Select status</option>
                                    <option value="PENDING"
                                            style=" background-color: #fff3cd;
                                            color: #856404;
                                            font-weight: bold;"      ${param.status == 'PENDING' ? 'selected' : ''}>PENDING</option>
                                    <option value="CONFIRMED" style=" background-color: #d4edda;
                                            color: #155724;
                                            font-weight: bold;" ${param.status == 'CONFIRMED' ? 'selected' : ''}>CONFIRMED</option>
                                    <option value="CANCELLED" style="background-color: #f8d7da;color: #721c24; font-weight: bold;"
                                            ${param.status == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                                    <option value="COMPLETED"
                                            style="  background-color: #d1ecf1;
                                            color: #0c5460;
                                            font-weight: bold;"
                                            ${param.status == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-3">
                            <label>Time Slot</label>
                            <div class="form-group">
                                <select id="timeSlot" name="listSlotId" class="form-select">
                                    <option value="">Select time slot</option>
                                    <c:forEach var="slot" items="${listTimeSlot}">
                                        <option value="${slot.id}" ${param.listSlotId == slot.id ? 'selected' : ''}>${slot.time}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-3">
                            <label>Phone</label>
                            <div class="form-group">
                                <input name="phone" type="text" class="form-control" value="${param.phone != null ? param.phone : ''}" />
                            </div>
                        </div>
                        <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN' or sessionScope.ROLE_CURRENT == 'STAFF'}">
                            <div class="col-3">
                                <label>Doctor</label>
                                <div class="form-group">
                                    <select id="doctor" name="doctorId" class="form-select">
                                        <option value="">Select doctor</option>
                                        <c:forEach var="doctor" items="${listDoctor}">
                                            <option value="${doctor.id}" ${param.doctorId == doctor.id ? 'selected' : ''}>${doctor.firstName} ${doctor.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="out-btn">
                            <button type="reset" class="btn btn-danger">Clear</button>
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>

            </div>

            <div class="container" style="display: flex; flex-direction: column;">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Customer name</th>
                            <th>Phone</th>
                            <th>Date Appointment</th>
                            <th>Status</th>
                            <th>Doctor</th>
                            <th>Time Slot</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="appointment" items="${pageable.data}">
                            <tr>
                                <td>${appointment.id}</td>
                                <td>${appointment.customerName}</td>
                                <td>${appointment.phone}</td>
                                <td>${appointment.date}</td>
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
                                <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
                                <td>${appointment.timeSlot.time}</td>
                                <td>
                                    <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN' or sessionScope.ROLE_CURRENT == 'DOCTOR'}">
                                        <c:choose>
                                            <c:when test="${appointment.status == 'PENDING'}">
                                                <button id="acceptAppointment" title="Accept appointment"
                                                        onclick="acceptAppointment(${appointment.id}, ${appointment.customer.id})"  
                                                        type="button" class="btn" style="border: 0; background-color: green;">
                                                    <i class="fa-solid fa-check" style="font-size: 15px; color: white; margin-left: 0;"></i>
                                                </button>

                                                <button id="rejectAppointment" type="button" 
                                                        title="Reject appointment" 
                                                        onclick="rejectAppointment(${appointment.id}, ${appointment.customer.id})" 
                                                        class="btn" style="border: 0; background-color: red;">
                                                    <i class="fa-solid fa-xmark" style="font-size: 15px; color: white; margin-left: 0;"></i>
                                                </button>
                                            </c:when>

                                            <c:when test="${appointment.status == 'COMPLETED'}">
                                                <button id="detailAppointment${appointment.id}" type="button" 
                                                        title="History appointment" 
                                                        onclick="callApiAndShowModel(${appointment.id})"
                                                        class="btn" style="border: 0; background-color: white;">
                                                    <i class="fa-solid fa-file-medical"  style="font-size: 15px; color: #E1C9B0; margin-left: 0;"></i>
                                                </button>
                                            </c:when>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${appointment.status == 'CONFIRMED'}">

                                                <button id="recordAppointment" title="Record appointment"
                                                        data-bs-toggle="modal" data-bs-target="#confirmModal${appointment.id}"
                                                        type="button" class="btn" style="border: 0; background-color: white;">
                                                    <i class="fa-solid fa-pen-to-square" style="font-size: 15px; color: black; margin-left: 0;
                                                       box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;"></i>
                                                </button> 

                                                <button id="cancellerAppointment" title="Cancel appointment"
                                                        onclick="rejectAppointment(${appointment.id}, ${appointment.customer.id})"  
                                                        type="button" class="btn" style="border: 0; background-color: red;">
                                                    <i class="fa-solid fa-ban" style="font-size: 15px; color: white; margin-left: 0;"></i>
                                                </button>
                                            </c:when>
                                        </c:choose>

                                    </c:if>

                                    <div class="modal fade" id="confirmModal${appointment.id}" tabindex="-1" aria-labelledby="confirmModal${appointment.id}" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editUserModalLabel${appointment.id}">Completed Appointment</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form id="appointmentForm${appointment.id}">
                                                        <span id="errorMessage${appointment.id}"
                                                              style="color: red; font-weight: bold; display: block; margin-bottom: 15px;"></span>

                                                        <input type="hidden" name="appointmentId" value="${appointment.id}">
                                                        <input type="hidden" name="customerId" value="${appointment.customer.id}">

                                                        <div class="mb-3">
                                                            <label class="form-label">Customer name</label>
                                                            <input type="text" class="form-control" name="customerName" value="${appointment.customerName}" readonly>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Phone</label>
                                                            <input type="text" class="form-control" name="phone" value="${appointment.phone}" readonly>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Date</label>
                                                            <input type="text" class="form-control" name="date" value="${appointment.date}" readonly>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Time Slot</label>
                                                            <input type="text" class="form-control" name="timeSlot" value="${appointment.timeSlot.time}" readonly>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Status</label>
                                                            <c:if test="${appointment.status == 'CONFIRMED'}">
                                                                <input value="${appointment.status}"  class="form-control" style=" background-color: #d4edda;
                                                                       color: #155724;
                                                                       font-weight: bold;"></input>
                                                            </c:if>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Diagnosis</label>
                                                            <textarea id="diagnosis${appointment.id}" name="diagnosis" class="form-control"></textarea>
                                                        </div>   
                                                        <div class="mb-3">
                                                            <label class="form-label">Treatment</label>
                                                            <textarea name="treatment" id="treatment${appointment.id}" class="form-control" style="min-height: 50px;"></textarea>
                                                        </div>   
                                                        <button onclick="completeAppointment(null, ${appointment.id}, ${appointment.customer.id})" 
                                                                class="btn btn-primary" type="button">Complete Appointment</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" class="mt-3">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${pageable.page == 1 ? 'disabled' : ''}">
                            <a class="page-link" 
                               href="<c:url value='/admin/appointment'>
                                   <c:param name='page' value='${pageable.page - 1}' />
                                   <c:param name='startDate' value='${param.startDate}' />
                                   <c:param name='endDate' value='${param.endDate}' />
                                   <c:param name='serviceId' value='${param.serviceId}' />
                                   <c:param name='customerName' value='${param.customerName}' />
                                   <c:param name='status' value='${param.status}' />
                                   <c:param name='listSlotId' value='${param.listSlotId}' />
                                   <c:param name='phone' value='${param.phone}' />
                                   <c:param name='doctorId' value='${param.doctorId}' />
                               </c:url>">Previous</a>
                        </li>

                        <c:if test="${pageable.totalPage > 0}">
                            <c:forEach var="i" begin="1" end="${pageable.totalPage}">
                                <li class="page-item ${i == pageable.page ? 'active' : ''}">
                                    <a class="page-link" 
                                       href="<c:url value='/admin/appointment'>
                                           <c:param name='page' value='${i}' />
                                           <c:param name='startDate' value='${param.startDate}' />
                                           <c:param name='endDate' value='${param.endDate}' />
                                           <c:param name='serviceId' value='${param.serviceId}' />
                                           <c:param name='customerName' value='${param.customerName}' />
                                           <c:param name='status' value='${param.status}' />
                                           <c:param name='listSlotId' value='${param.listSlotId}' />
                                           <c:param name='phone' value='${param.phone}' />
                                           <c:param name='doctorId' value='${param.doctorId}' />
                                       </c:url>">${i}</a>
                                </li>
                            </c:forEach>
                        </c:if>

                        <li class="page-item ${pageable.page == pageable.totalPage ? 'disabled' : ''}">
                            <a class="page-link" 
                               href="<c:url value='/admin/appointment'>
                                   <c:param name='page' value='${pageable.page + 1}' />
                                   <c:param name='startDate' value='${param.startDate}' />
                                   <c:param name='endDate' value='${param.endDate}' />
                                   <c:param name='serviceId' value='${param.serviceId}' />
                                   <c:param name='customerName' value='${param.customerName}' />
                                   <c:param name='status' value='${param.status}' />
                                   <c:param name='listSlotId' value='${param.listSlotId}' />
                                   <c:param name='phone' value='${param.phone}' />
                                   <c:param name='doctorId' value='${param.doctorId}' />
                               </c:url>">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>  
        </div>

        <!--Footer-->
        <%@include file="../../common/admin/footer.jsp" %>
        <!--Footer-->

        <!-- Modal -->
        <div class="modal fade" id="medicalRecordModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitle">Medical Record</h5>
                        <!--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>-->
                    </div>
                    <div class="modal-body" id="modalContent">
                    </div>
                    <div class="modal-footer">
                        <!-- Changed id of the Close button to avoid conflict -->
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <script>


            async function acceptAppointment(appointmentId, userId) {
                try {
                    const response = await fetch(`/Healthcare/api/appointment/action?appointmentId=` + appointmentId + `&userId=` + userId, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    if (response.ok) {
                        alert('Appointment accepted successfully! success');
                        location.reload();
                    } else {
                        const errorData = await response.json();
                        alert('Error accepting appointment: ' + errorData.message);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('An error occurred while accepting the appointment.');
                }
            }

            async function rejectAppointment(appointmentId, userId) {
                try {
                    const response = await fetch(`/Healthcare/api/appointment/action?appointmentId=` + appointmentId + `&userId=` + userId, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    if (response.ok) {
                        alert('Appointment rejected successfully! success');
                        location.reload();
                    } else {
                        const errorData = await response.json();
                        alert('Error rejecting appointment: ' + errorData.message);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('An error occurred while rejecting the appointment.');
                }
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
