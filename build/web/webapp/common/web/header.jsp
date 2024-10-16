<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<!DOCTYPE html>
<header class="header">
    <div class="contact">
        <div class="container">
            <ul class="list-contact">
                <li class="item-contact">
                    <a href="#">093 186 3366 | 096 361 4566</a>
                </li>
                <li class="item-contact"><a href="#">Hỗ trợ</a></li>
                <li class="item-contact"><a href="#">Tuyển dụng</a></li>
                <li class="item-contact"><a href="#">Liên hệ</a></li>
                <c:choose>
                        <c:when test="${not empty sessionScope.USER_CURRENT}">
                        <li class="item-contact">
                            <a href="#">Xin chào, ${sessionScope.USER_CURRENT}</a>
                        </li>
                        <li class="item-contact">
                            <a href="<c:url value='/logout'/>">Đăng xuất</a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="item-contact">
                            <a href="<c:url value='/login'/>">Đăng nhập</a>
                        </li>
                        <li class="item-contact">
                            <a href="<c:url value='/register'/>">Đăng kí</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
    <div class="menu">
        <div class="container">
            <a href="<c:url value="/home"/>">
                <img src="webapp/assets/images/logo.jpg" alt="" />
            </a>
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/home"/>">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Về chúng tôi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Dịch vụ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Đội ngũ bác sĩ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Chuyên gia tư vấn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cửa hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Kiến thức</a>
                </li>
            </ul>
            <button class="btn-small">
                <i class="fa-solid fa-calendar-check"></i> Đặt lịch hẹn
            </button>
        </div>
    </div>
</header>

