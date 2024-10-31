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
    </head>
    <body>

        <style>
            .table tr {
                padding-bottom: 15px;
            }

            .table td {
                padding: 10px;
            }

            .table {
                border-collapse: separate;
                border-spacing: 0 10px;
            }
            .content{
                margin-left: 250px;
                padding: 20px;
                height: 433px;
                display: flex;
                justify-content: center;
                background-color: white;
            }
            .form{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width: 721px;
                height: 445px;
                background-color: #f2f2f2;
            }
            .form table {
                font-size: 20px;
            }
            .form {
                border: 2px solid #ddd; /* Khung bao quanh bảng */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
            }

            table {
                display: flex;
                justify-content: center;
                width: 100%;
                border-collapse: separate;
                border-spacing: 0 10px; /* Khoảng cách giữa các hàng */
            }

            td {
                padding: 8px;
                vertical-align: middle;
            }
            input{
                font-size: 15px;
                width: 232px;
                height: 33px;
                border-radius: 5px;
                border: 1px solid #ced4da;
            }
            button {
                margin-top: 20px;
                padding: 10px 36px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            button:hover {
                background-color: #0056b3;
            }
            label{
                font-size: 20px;
            }
            .error{
                color: red;
                font-size: 20px;
            }
            .description{
                    width: 232px;
                    height: 33px;
                    font-size: 15px;
                    border-radius: 5px;
                    border: 1px solid #ced4da;
                }
            </style>
            <!--Header-->
            <%@include file="../../common/admin/header.jsp" %>
            <!--End Header-->
            <!--Menu-->
            <%@include file="../../common/admin/menu.jsp" %>
            <!--End Menu-->
            <!--form-->
            <c:set value="${requestScope.medicine}" var="medicine"/>
            <div class="content">
                <form class="form" action="<c:url value='/admin/edit/medicine'/>" method="post">
                    <table border="0">
                        <tbody>
                            <tr class="error">
                                <c:if test="${requestScope.error != null}">
                                    <td>${requestScope.error}</td>
                                </c:if>
                            </tr>
                            <tr>
                                <td><label>Enter id:</label></td>
                                <td><input type="number" name="id" readonly="" value="${medicine.id}"/></td>
                            </tr>
                            <tr>
                                <td><label>Enter name:</label></td>
                                <td><input type="text" name="name" value="${medicine.medicineName}"/></td>
                            </tr>
                            <tr>
                                <td><label>Enter price:</label></td>
                                <td><input type="number" name="price" value="${medicine.price}"/></td>
                            </tr>
                            <tr>
                                <td><label>Enter description:</label></td>
                                <td><textarea class="description" name="description" >${medicine.description}</textarea></td>
                            </tr>
                            <tr>
                                <td><label>Enter quantity in stock:</label></td>
                                <td><input type="number" name="quantity" value="${medicine.quantityInStock}"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <button type="submit">EDIT</button>
                </form>
            </div>

            <!--endform-->
            <!--Footer-->
            <%@include file="../../common/admin/footer.jsp" %>
            <!--Footer-->




        </body>
    </html>
