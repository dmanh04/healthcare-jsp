<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>

    <body>
        <div class="header">
            <div class="analytics">
                <<i class="fa-solid fa-envelope fa-2x"></i>
            </div>
            <div class="bell">
                <i class="fa-solid fa-bell fa-2x"></i>
            </div>
            <div class="user-profile">
                <c:if test="${not empty sessionScope.USER_CURRENT}">
                    <span>Xin chào, ${sessionScope.USER_CURRENT}</span>
                </c:if>
                <c:if test="${empty sessionScope.USER_CURRENT}">
                    <span>Xin chào, khách!</span>
                </c:if>
                <img src="<c:url value='/webapp/assets/images/periodontal-icon.png' />" alt="Periodontal Icon"/>
            </div> 
        </div>
    </div>

</body>
</html>
