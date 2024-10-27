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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->

        <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="position: fixed; top: 20px; right: 20px; z-index: 1050;">
            <div class="toast-header">
                <strong class="me-auto" style="font-size: 1.4rem;">Notification</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <p>Profile updated successfully!</p>
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
                    <div class="breadcum">Trang chủ / Thay đổi mật khẩu</div>
                    <div class="main-content">
                        <form id="changePasswordForm" action="<c:url value='change-password'/>" onsubmit="return validateForm()" method="post">
                            <span id="errorMessage" style="color: red; font-weight: bold; display: block; font-size: 1.4rem; margin-bottom: 15px;">${errorMessage}</span>
                            <div class="mb-3">
                                <label for="oldPassword" class="form-label">Mật khẩu cũ: *</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword"  value="${param.oldPassword != null ? param.oldPassword : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="newPassword" class="form-label">Mật khẩu mới: *</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" value="${param.newPassword != null ? param.newPassword : ''}" required>
                            </div>

                            <div class="mb-3">
                                <label for="retypePassword" class="form-label">Xác nhận mật khẩu mới: *</label>
                                <input type="password" class="form-control" id="retypePassword" name="retypePassword" required>
                            </div>
                            <div class="center">
                                <button type="submit" class="btn btn-primary">Thay đổi mật khẩu</button>
                            </div>
                        </form>    
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
                            function validateForm() {
                                const newPassword = document.getElementById("newPassword").value;
                                const retypePassword = document.getElementById("retypePassword").value;
                                const errorMessage = document.getElementById("errorMessage");
                                if (newPassword !== retypePassword) {
                                    errorMessage.textContent = "Mật khẩu mới và xác nhận mật khẩu phải giống nhau.";
                                    return false;
                                }
                                errorMessage.textContent = "";
                                return true;
                            }
        </script>
    </body>
</html>
