<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>

<aside class="sidebar">
    <a href="<c:url value='/admin'/>">
        <i class="fa-solid fa-house"></i> Trang chủ
    </a>
    <a href="<c:url value='/admin/appointment'/>">
        <i class="fa-solid fa-users"></i> Quản lí lịch hẹn
    </a>
    <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN' or sessionScope.ROLE_CURRENT == 'DOCTOR'}">
        <a href="<c:url value='/admin/medicine'/>">
            <i class="fa-solid fa-pills"></i> Quản lí thuốc
        </a>
    </c:if>    
    <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
        <a href="<c:url value='/admin/user'/>">
            <i class="fa-solid fa-users-cog"></i> Quản lí người dùng
        </a>
    </c:if>

    <a href="<c:url value='/admin/service'/>">
        <i class="fa-solid fa-concierge-bell"></i> Quản lí dịch vụ
    </a>
    <a href="<c:url value='/logout'/>">
        <i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
    </a>
</aside>