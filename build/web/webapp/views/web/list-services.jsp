<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/reset.css" />">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/base.css" />">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/style.css" />">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>

        <style>

            .container {
                max-width: 1140px !important;
                margin-left: auto !important;
                margin-right: auto !important;
            }


            .name {
                font-family: arial;
                font-weight: bold;
                font-size: 20px;
                margin: 50px 0px 50px 150px;
            }

            .top-section {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0 auto;
                width: 80%;

            }

            .top-section .left {
                width: 100%;
                height: 80%;
            }

            .top-section .left img {
                width: 100%;
                height: 100%;
                object-fit: fill;
            }

            .top-section .right {
                background-color: #EAFAFF;
                height: 450px;
                padding-right: 30px;
                width: 100%;
            }

            .right .text {
                margin-top: 100px;
                padding-bottom: 100px;
            }

            strong {
                font-family: 'HelveticaB';
                font-weight: 400;
                font-size: 20px;

            }

            .top-section .right .text ul {
                margin-top: 30px;
                list-style: disc;
                padding-left: 30px;
            }

            .top-section .right .text ul li {
                margin: 10px 0;
            }

            .top-section .right table {
                border: 1px solid white;
                background-color: #0F4FAF;
            }

            .top-section .right table td {
                border: 1px solid white;
                color: white;
                text-align: center;
                padding: 18px;
                border-right: 1px solid #fff;
            }

            .bottom-section {
                text-align: center;
                display: flex;
                justify-content: center;
                gap: 20px;
                margin: 30px 150px;
            }

            .bottom-section table tr {
                display: flex;
            }

            .bottom-section table tr td {
                width: 50%;
                padding: 20px;
            }

            .bottom-section table tr td p {
                text-align: justify;
            }

            .bottom-section table tr td strong {
                font-family: 'HelveticaB';
                font-size: 20px;
                font-weight: 400;
                text-align: start;
                margin-bottom: 10px;
            }

            .info {
                margin: 20px 0;
                display: flex;
                justify-content: center;
            }

            .info span {
                color: #0F4FAF;
                text-align: center;
                font-size: 15px;
            }

            .info i {
                color: #0F4FAF;
            }

            .first-content {
                display: flex;
                justify-content: center;
                gap: 30px;
                padding: 20px;
            }

            .first-content .right-content {
                width: 360px;
            }

            .first-content .left-content img {
                border-radius: 10px 10px 0 0 ;
                width: 750px;
            }

            .first-content .left-content ul {
                display: flex;
                justify-content: center;
                text-align: center;
                background-color: #0F4FAF;
                padding: 20px;
                width: 750px;
                margin: 0 auto;
                border-radius: 0 0 10px 10px;
            }

            .first-content .left-content ul li {
                text-align: justify;
                color: white;
                font-size: 15px;

            }

            .first-content .left-content ul li:nth-child(1) {
                margin-right: 40px;
            }

            .first-content .left-content ul li span {
                display: block;
                font-size: 15px;
                margin-top: 5px;

            }

            .first-content .left-content h3,.first-content .right-content h3 {
                text-align: left;
                margin-bottom: 10px;
                color: #0056b3;
                font-weight: 500;
            }
            .first-content .right-content h3+p {
                font-size: 16px;
                font-style: italic;
                margin-block: 10px;
            }

            .first-content .right-content p:nth-child(3) {
                font-size: 16px;
                margin-bottom: 10px;
            }

            .first-content .right-content p:nth-child(4) {
                font-weight: 600;
                margin-bottom: 10px;
                color: #0056b3;
            }

            .first-content .right-content ul li {
                list-style-type: disc;
                margin-left: 30px;
                font-size: 14px;
            }

            .first-content .right-content div {
                height: 315px;
                color: white;
                margin-top: 10px;
                background-color: #0f4faf;
                display: flex;
                justify-content: center;
                align-items: center;
                padding-inline: 30px;
                border-radius: 10px;
            }

            .first-content .right-content div p {

                font-size: 30px;
                font-weight: bold;
                word-spacing: 6px;
            }

            .first-content .right-content ul+p {
                margin-block: 10px;
                font-size: 16px;
            }
            .first-content .right-content div i{
                font-size: 30px;
                margin-right: 20px;
            }
            .title p{
                margin: 0 0 20px 150px;
                font-size: 28px;
                font-weight: bold ;
            }
            .banner{
                display: flex;
                justify-content: center;
                margin-bottom: 50px;
            }
            .banner img{
                width: 80%;
            }
            .content{
                display: flex;
                justify-content: center;
                padding-inline:150px;
                margin-bottom: 50px;
            }
            .content .left-content img{
                width: 570px;
                transition: transform 1s ease-in-out;
            }
            .content .left-content img:hover{
                transform: scale(1.05);
            }
            .content .right-content{
                background-color: #0f4faf;
                color: white;
            }
            .content .right-content .right-content1{
                display: flex;
                justify-content: center;
                gap: 20px;
                font-size: 40px;
                font-weight: bold;
                margin-block: 20px;
            }
            .right-content1 p{
                margin-block: auto;
                font-size: 24px;
            }
            .right-content2{
                padding-inline: 40px;
            }
            .right-content2 p {
                text-align: justify;
                word-spacing: -1px;
                margin-bottom: 58px;
            }
            .right-content2 a{
                font-size: 20px;
                color: white;
                margin-left: 6px;
            }
            .first_img{
                display: flex;
                justify-content: center;
            }
            .description{
                text-align: justify;
            }
            .service-content{
                display: flex;
                justify-content: center;
                gap: 30px;
                margin-top: 30px;
                padding-inline: 70px;
            }

            .service-content .first p{
                font-size: 16px;
                margin-bottom: 20px;
                text-align: justify;
            }
            .service-content .first ul{
                list-style: disc;
                padding-inline: 20px;
            }
            .service-content .first ul li{
                font-size: 16px;
                margin-block: 20px;
                text-align: justify;
            }
            .service-content .second{
                padding-inline: 30px;
            }
            .service-content .second ul li span{
                font-weight: bold;
                font-size: 18px;
            }
            .service-content .second ul li p{
                margin-top: 10px;
                font-size: 16px;
                text-align: justify;
            }
            .service-content .second ul li:not(:first-child){
                padding-block: 10px;
            }
            .tiltle-banner p:nth-child(1){
                font-family: 'HelveticaB';
                text-align: center;
                color: #1c4c95;
                font-weight: bold;
                font-size: 40px;
                margin-block: 20px;
            }
            .tiltle-banner p{
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }
        </style>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->

        <!--Main-->
        <div>
            <div class="title">
                <p>Ưu đãi duy nhất</p>
            </div>

            <div class="banner">
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/z5917993926541_2934a4110ef6d4278f6b8108b63cb61a.jpg"
                     alt="">
            </div>
            <div class="title">
                <p>Dịch vụ của chúng tôi</p>
            </div>
            <c:forEach items="${requestScope.data}" var="s">
                <div class="content">
                    <div class="left-content">
                        <img src="${s.image}" alt="Service Photo">
                    </div>
                    <div class="right-content">
                        <div class="right-content1">
                            <i class="fa-solid fa-tooth"></i>
                            <p>${s.serviceName}</p>
                        </div>
                        <div class="right-content2">
                            <p>${s.description}</p>
                            <a href="<c:url value='/services/${s.id}'/>">
                                <span>Xem thông tin chi tiết</span>&nbsp;&nbsp;
                                <i class="fa-solid fa-arrow-right "></i>
                            </a>
                        </div>
                    </div>
                </div>

            </c:forEach>


        </div>
        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>

</html>