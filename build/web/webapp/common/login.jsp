<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../common/taglib.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/reset.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/base.css"/>">
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/style.css"/>">
    </head>
    <body>
        <%@include file="../common/web/header.jsp" %>

        <div class="login">
            <div class="container">
                <div class="left">
                    <img src="https://media.istockphoto.com/id/2172154481/vi/anh/m%E1%BB%99t-chi%E1%BA%BFc-r%C4%83ng-ho%E1%BA%A1t-h%C3%ACnh-m%E1%BB%89m-c%C6%B0%E1%BB%9Di-%C4%91%E1%BB%A9ng-tr%C3%AAn-n%E1%BB%81n-m%C3%A0u-xanh-v%E1%BB%9Bi-c%C3%A1c-d%E1%BB%A5ng-c%E1%BB%A5-nha-khoa-%C4%91%C6%B0%E1%BB%A3c-%C4%91%E1%BA%B7t-b%C3%AAn.jpg?s=2048x2048&w=is&k=20&c=4niF3NeCOMXnkREDl2r6Ycyf8kSW82fOQu_qGA_3f9Q=" alt="">
                </div>
                <div class="right">
                    <form action="" class="form">
                        <div class="form-item">
                            <input type="text" class="form-control" placeholder="Email/Số điện thoại/Tên đăng nhập">
                        </div>
                        <div class="form-item">
                            <input type="password" class="form-control" placeholder="Mật khẩu">
                        </div>
                        <div class="button">
                            <button class="btn-small">Đăng nhập</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="../common/web/footer.jsp" %>
    </body>
</html>
