<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>

<aside class="sidebar">
    <a href="<c:url value='/admin'/>">
        <i class="fa-solid fa-house"></i> Trang chủ
    </a>
    <a href="#">
        <i class="fa-solid fa-users"></i> Quản lí khách hàng
    </a>
    <a href="#">
        <i class="fa-solid fa-pills"></i> Quản lí thuốc
    </a>
    <a href="#">
        <i class="fa-solid fa-users-cog"></i> Quản lí nhân viên
    </a>
    <a href="<c:url value='/admin/service'/>">
        <i class="fa-solid fa-concierge-bell"></i> Quản lí dịch vụ
    </a>
    <a href="#">
        <i class="fa-solid fa-chart-bar"></i> Quản tài chính
    </a>
    <a href="<c:url value='/logout'/>">
        <i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
    </a>
</aside>