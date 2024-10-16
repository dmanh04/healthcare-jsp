<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/reset.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/base.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/style.css"/>">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <script>
            function validateForm() {
                var name = document.forms["registerForm"]["name"].value;
                var username = document.forms["registerForm"]["username"].value;
                var password = document.forms["registerForm"]["password"].value;
                var retypePassword = document.forms["registerForm"]["retypePassword"].value;

                var errorMessage = document.getElementById("error-message");

                if (name === "" || username === "" || password === "" || retypePassword === "") {
                    errorMessage.textContent = "All fields are required!";
                    return false; // Prevent form submission
                }

                if (password !== retypePassword) {
                    errorMessage.textContent = "Passwords do not match!";
                    return false; 
                }

                errorMessage.textContent = "";
                return true;
            }
        </script>
    </head>
    <body>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->
        <div class="login">
            <div class="container">
                <div class="left">
                    <img src="https://media.istockphoto.com/id/2172154481/vi/anh/m%E1%BB%99t-chi%E1%BA%BFc-r%C4%83ng-ho%E1%BA%A1t-h%C3%ACnh-m%E1%BB%89m-c%C6%B0%E1%BB%9Di-%C4%91%E1%BB%A9ng-tr%C3%AAn-n%E1%BB%81n-m%C3%A0u-xanh-v%E1%BB%9Bi-c%C3%A1c-d%E1%BB%A5ng-c%E1%BB%A5-nha-khoa-%C4%91%C6%B0%E1%BB%A3c-%C4%91%E1%BA%B7t-b%C3%AAn.jpg?s=2048x2048&w=is&k=20&c=4niF3NeCOMXnkREDl2r6Ycyf8kSW82fOQu_qGA_3f9Q=" alt="">
                </div>
                <div class="right">
                    <!-- Error message span -->
                    <span id="error-message" style="color: red; font-size: 14px; margin-top: 10px; margin-bottom: 10px; display: block;">${errorMessage}</span>

                    <!-- Form with onsubmit validation -->
                    <form name="registerForm" action="<c:url value='register'/>" method="post" class="form" onsubmit="return validateForm();">
                        <div class="form-item">
                            <input type="text" name="name" class="form-control" placeholder="Nhập họ và tên">
                        </div>
                        <div class="form-item">
                            <input type="text" name="username" class="form-control" placeholder="Email/Tên đăng nhập">
                        </div>
                        <div class="form-item">
                            <input type="password" name="password" class="form-control" placeholder="Mật khẩu">
                        </div>
                        <div class="form-item">
                            <input type="password" name="retypePassword" class="form-control" placeholder="Xác nhận mật khẩu">
                        </div>
                        <div class="button">
                            <button type="submit" class="btn-small">Đăng kí</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
    </body>
</html>
