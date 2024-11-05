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

    </head>
    <body>

        <style>
            /* Header Purchase Styles */
            .header-purchase {
                background-color: #f8f9fa; /* Light background color */
                padding: 20px; /* Spacing around the header */
                border-radius: 8px; /* Rounded corners */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow */
                margin-bottom: 20px; /* Spacing below the header */
            }

            .header-purchase label {
                font-weight: bold; /* Bold labels for better visibility */
                margin-right: 10px; /* Spacing between labels and inputs */
            }

            .header-purchase input[type="text"],
            .header-purchase input[type="date"] {
                padding: 10px; /* Padding inside the input fields */
                border: 1px solid #ced4da; /* Light border */
                border-radius: 4px; /* Slightly rounded corners for inputs */
                margin-right: 15px; /* Spacing between inputs */
            }

            .header-purchase button {
                padding: 10px 20px; /* Padding for the button */
                background-color: #007bff; /* Primary button color */
                color: white; /* White text color */
                border: none; /* No border */
                border-radius: 4px; /* Rounded corners for button */
                cursor: pointer; /* Pointer cursor on hover */
                transition: background-color 0.3s; /* Transition effect */
            }

            .header-purchase button:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }
        </style>

        <!--Header-->
        <%@include file="../../common/admin/header.jsp" %>
        <!--End Header-->
        <!--Menu-->
        <%@include file="../../common/admin/menu.jsp" %>
        <!--End Menu-->
        <div class="content">
            <div class="header-purchase">
                <form class="formSearch" action="<c:url value='/admin/medical-purchase'/>" method="get">
                    <label>Customer name:  </label>
                    <input type="text" name="customerName" value="${param.customerName != null ? param.customerName : ''}">
                    <label>Start Date: </label>
                    <input type="date" name="startDate" value="${param.startDate != null ? param.startDate : ''}">
                    <label>End Date: </label>
                    <input type="date" name="endDate" value="${param.endDate != null ? param.endDate : ''}">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>


            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Customer Name</th>
                        <th>Diagnosis</th>
                        <th>Treatment</th>
                        <th>Date</th>
                        <th>Prescribed Medicines</th>
                        <th>Purchased Medicines</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="appointment" items="${pageable.data}">
                        <tr>
                            <td>${appointment.medicalRecordId}</td>
                            <td>${appointment.customerName}</td>
                            <td>${appointment.diagnosis}</td>
                            <td>${appointment.treatment}</td>
                            <td>${appointment.appointmentDate}</td>
                            <td>
                                <c:forEach var="medicine" items="${appointment.prescribedMedicines}">
                                    <div>${medicine}</div>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="purchased" items="${appointment.purchasedMedicines}">
                                    <div>${purchased}</div>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${pageable.page == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="<c:url value='/admin/medical-purchase?page=${pageable.page - 1}&customerName=${param.customerName}&startDate=${param.startDate}&endDate=${param.endDate}'/>">Previous</a>
                    </li>

                    <c:forEach var="i" begin="1" end="${pageable.totalPage}">
                        <li class="page-item ${i == pageable.page ? 'active' : ''}">
                            <a class="page-link" href="<c:url value='/admin/medical-purchase?page=${i}&customerName=${param.customerName}&startDate=${param.startDate}&endDate=${param.endDate}'/>">${i}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${pageable.page == pageable.totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="<c:url value='/admin/medical-purchase?page=${pageable.page + 1}&customerName=${param.customerName}&startDate=${param.startDate}&endDate=${param.endDate}'/>">Next</a>
                    </li>
                </ul>
            </nav>

        </div>
    </div>

    <!--Footer-->
    <%@include file="../../common/admin/footer.jsp" %>
    <!--Footer-->




    <script>


    </script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</body>
</html>
