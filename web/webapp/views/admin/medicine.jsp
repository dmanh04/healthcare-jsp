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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script type="text/javascript">
            const handleDelete = (id) => {
                if (confirm("Do you want to delete this medicine ?")) {
                    window.location = "medicine/delete?id=" + id;
                }
            };
        </script>
    </head>
    <body>

        <style>
            #errorMessage {
                color: red;
                font-weight: bold;
                display: none;
                margin-bottom: 15px;
            }

            #errorMessage.visible {
                display: block;
            }

            .header-service {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 25px;
                padding: 10px 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            .formSearch label {
                font-weight: bold;
                margin-right: 10px;
                font-size: 16px;
            }

            .formSearch input[type="text"] {
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                width: 250px;
            }

            .formSearch button {
                margin-left: 10px;
            }

            .inner-btn {
                text-align: right;
                padding-right: 20px;
            }

            .inner-btn .btn {
                padding: 8px 15px;
                border-radius: 5px;
                background-color: #007bff;
                border-color: #007bff;
                transition: background-color 0.3s ease;
            }

            .inner-btn .btn:hover {
                background-color: #0056b3;
            }
            .container{
                max-width: 1320px;

            }
            .content{
                margin-left: 250px;
                padding: 20px;
                background-color: #F5F7FA;
            }
            thead tr th,tbody tr td{
                text-align: center;
            }
            thead tr th{
                font-weight: bold;
            }
        </style>
        <%@include file="../../common/admin/header.jsp" %>

        <%@include file="../../common/admin/menu.jsp" %>

        <div class="content">
            <div class="header-service">
                <form class="formSearch" action="<c:url value='/admin/medicine'/>" method="get">
                    <label>Min Price: </label>
                    <input type="number" name="minPrice" value="${param.minPrice != null ? param.minPrice : ''}">
                    <label>Max Price: </label>
                    <input type="number" name="maxPrice" value="${param.maxPrice != null ? param.maxPrice : ''}">
                    <label>Search by name: </label>
                    <input type="text" name="name" value="${param.name != null ? param.name : ''}">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>

                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                    <div class="inner-btn">
                        <a class="btn btn-primary" href="/Healthcare/webapp/views/admin/add-medicine.jsp">Add Medicine
                        </a>
                    </div>
                </c:if>   
            </div>

            <div class="container">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Medicine Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                                <th>Actions</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="medicine" items="${requestScope.listMedicine}">
                            <tr>
                                <td>${medicine.id}</td>
                                <td>${medicine.medicineName}</td>
                                <td>
                                    <fmt:formatNumber value="${medicine.price}" type="currency" />
                                </td>
                                <td>${medicine.quantityInStock}</td>
                                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                                    <td>
                                        <a class="btn btn-primary" href="<c:url value='/admin/detail/medicine?id=${medicine.id}'/>">Detail</a>
                                        <a class="btn btn-warning" href="<c:url value='/admin/edit/medicine?id=${medicine.id}'/>">Edit</a>
                                        <button onclick="handleDelete(${medicine.id})" class="btn btn-danger" >
                                            Delete
                                        </button>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!--Footer-->
        <%@include file="../../common/admin/footer.jsp" %>
        <!--Footer-->
    </body>
</html>
