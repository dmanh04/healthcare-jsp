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
    </head>
    <body>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->

        <div class="banner">
            <img src="webapp/assets/images/banner.jpg" alt="" />
        </div>
        <div class="services-section">
            <div class="container">
                <div class="services-header">
                    <h3>Dịch vụ từ tâm cho trải nghiệm xứng tầm</h3>
                    <a href="#"
                       >Xem thêm dịch vụ <i class="fa-solid fa-arrow-right-long"></i
                        ></a>
                </div>
                <div class="service-grid">
                    <div class="service-card">
                        <i class="fa-solid fa-tooth"></i>
                        <p>Niềng răng Invisalign</p>
                    </div>
                    <div class="service-card">
                        <i class="fa-solid fa-teeth"></i>
                        <p>Niềng răng mắc cài</p>
                    </div>
                    <div class="service-card">
                        <i class="fa-solid fa-teeth-open"></i>
                        <p>Cấy ghép Implant</p>
                    </div>
                    <div class="service-card">
                        <i class="fa-solid fa-notes-medical"></i>
                        <p>Nhổ răng</p>
                    </div>
                    <div class="service-card">
                        <i class="fa-solid fa-syringe"></i>
                        <p>Bọc răng sứ</p>
                    </div>
                    <div class="service-card">
                        <i class="fa-solid fa-tooth"></i>
                        <p>Điều trị nha chu</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="video-section">
            <div class="container">
                <div class="video-text">
                    <p>NHA KHOA THÚY ĐỨC</p>
                    <h1>18 NĂM XÂY DỰNG <br />VÀ PHÁT TRIỂN</h1>
                    <a href="#"
                       >Xem thêm video <i class="fa-solid fa-arrow-right-long"></i
                        ></a>
                </div>
                <div class="video-container">
                    <iframe
                        width="560"
                        height="315"
                        src="https://www.youtube.com/embed/MhDA59xjUtI?si=LO2MolbLD2KSK3EU"
                        title="YouTube video player"
                        frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        allowfullscreen
                        ></iframe>
                </div>
            </div>
        </div>

        <section class="team-section">
            <div class="container">
                <h3>Đội ngũ bác sĩ</h3>
                <p>An tâm đồng hành cùng đội ngũ bác sĩ chuyên môn cao và nhiều năm kinh nghiệm.</p>

                <div class="doctor-grid">
                    <c:forEach items="${requestScope.data}" var="d">
                        <div class="doctor-card">
                            <a href="<c:url value='/doctors/${d.id}'/>">
                                <img src="<c:url value='/uploads/${d.photos}'/>" alt="${d.firstName} ${d.lastName}">
                                <h3>BS.${d.firstName} ${d.lastName}</h3>
                            </a>
                        </div>
                    </c:forEach>
                    <div class="details-link">
                        <a href="<c:url value='/doctors'/>">Xem chi tiết</a>
                    </div>
                </div>
            </div>





            <!--Footer-->
            <%@include file="../../common/web/footer.jsp" %>
            <!--Footer-->
    </body>
</html>
