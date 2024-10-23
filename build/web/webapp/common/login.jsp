<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../common/taglib.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/reset.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/base.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/style.css'/>">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <script>
            function validateForm() {
                var username = document.forms["loginForm"]["username"].value;
                var password = document.forms["loginForm"]["password"].value;
                var errorMessage = document.getElementById("error-message");
                errorMessage.textContent = "";
                if (username === "" || password === "") {
                    errorMessage.textContent = "Username and password cannot be empty!";
                    return false;
                }

                return true;
            }
        </script>
    </head>
    <body>
        <%@include file="../common/web/header.jsp" %>

        <div class="login">
            <div class="container">
                <div class="left">
                    <img src="https://media.istockphoto.com/id/2172154481/vi/anh/m%E1%BB%99t-chi%E1%BA%BFc-r%C4%83ng-ho%E1%BA%A1t-h%C3%ACnh-m%E1%BB%89m-c%C6%B0%E1%BB%9Di-%C4%91%E1%BB%A9ng-tr%C3%AAn-n%E1%BB%81n-m%C3%A0u-xanh-v%E1%BB%9Bi-c%C3%A1c-d%E1%BB%A5ng-c%E1%BB%A5-nha-khoa-%C4%91%C6%B0%E1%BB%A3c-%C4%91%E1%BA%B7t-b%C3%AAn.jpg?s=2048x2048&w=is&k=20&c=4niF3NeCOMXnkREDl2r6Ycyf8kSW82fOQu_qGA_3f9Q=" alt="">
                </div>
                <div class="right">
                    <c:if test="${not empty successMessage}">
                        <span style="color: green; font-size: 14px; margin-top: 10px; margin-bottom: 10px; display: block;">${successMessage}</span>
                    </c:if>

                    <c:if test="${not empty errorMessage}">
                        <span style="color: red; font-size: 14px; margin-top: 10px; margin-bottom: 10px; display: block;" id="error-message">${errorMessage}</span>
                    </c:if>

                    <form name="loginForm" action="<c:url value='login'/>" method="post" class="form" onsubmit="return validateForm();">
                        <div class="form-item">
                            <input type="text" name="username" class="form-control" value="${param.username != null ? param.username : ''}" placeholder="Email/Tên đăng nhập">
                        </div>
                        <div class="form-item">
                            <input type="password" name="password" class="form-control" value="${param.password != null ? param.password : ''}" placeholder="Mật khẩu">
                        </div>
                        <div class="text" style="width: 105%;
                             text-align: end;
                             padding: 0px 0px;">
                            <a href="#" style="font-size: 14px">Quên mật khẩu?</a>
                        </div>
                        <div class="button" style="margin-top: 10px">
                            <button type="submit" class="btn-small">Đăng nhập</button>
                        </div>
                        <div class="text-2" style="    width: 105%;
                             text-align: center;
                             padding: 20px 0px;
                             font-size: 12px;
                             margin-top: 10px">
                            <span>Chưa có tài khoản </span><a href="<c:url value='/register'/>" style="font-size: 14px; text-decoration: underline !important;">Đăng ký ngay</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="../common/web/footer.jsp" %>
    </body>
</html>