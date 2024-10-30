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
        <style>
            .container {
                max-width: 1140px !important;
                margin-left: auto !important;
                margin-right: auto !important;
            }
            /* Doctor section */
            .team-section {
                text-align: start;
                padding: 50px 0px;
            }

            .team-section .container{
                display: block;
            }


            .team-section h3 {
                margin-bottom: 20px;
                font-weight: 700;
            }

            .team-section p {
                margin-bottom: 40px;
                color: #666;
            }

            .doctor-grid {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                gap: 20px;
            }

            .doctor-card {
                background-color: #fff;
                overflow: hidden;
                width: 250px;
                text-align: center;
                cursor: pointer;
            }

            .doctor-card img {
                display: block;
                height: 263px;
                width: 311px;
                object-fit: cover;
                /*                margin: 0;
                                background: none;
                                padding: 0;
                                border: none;*/
            }

            .doctor-card h3 {
                background: linear-gradient(to bottom, #E6C075, #CF9A4C);
                color: #fff;
                padding: 20px;
                font-size: 1.5em;
            }

            .details-link {
                margin-top: 30px;
            }

            .details-link a {
                color: #007bff;
                text-decoration: none;
                font-weight: bold;
                font-size: 1.8rem;
            }

            .details-link a:hover {
                text-decoration: underline;
            }

            /* End Doctor section */
        </style>
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
                    <a href="<c:url value='/services'/>"
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
                </div>
                <div class="details-link">
                    <a href="<c:url value='/doctors'/>">Xem chi tiết <i class="fa-solid fa-arrow-right-long"></i
                        ></a>
                </div>
            </div>
        </section>     





        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>
</html>
