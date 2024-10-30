<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib.jsp" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Page</title>
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
        <c:set var="s" value="${requestScope.service}" />
        <div class="service">

            <div class="first_img">
                <img src="${s.image}" alt="User Photo">
            </div>
            <div class="service-content">
                <div class="first">
                    <p>${s.description}</p>
                    <ul>
                        <li>
                            Bọc trực tiếp lên răng tổn thương: Áp dụng khi chỉ có vấn đề nhỏ như nứt, mẻ và răng
                            vẫn
                            giữ được chân răng. Bác sĩ sẽ tiến hành mài nhỏ chân răng trước khi đặt lớp răng sứ
                            lên
                            trên.
                        </li>
                        <li>
                            Làm cầu răng sứ: Sử dụng khi có răng mất không thể phục hồi, tạo cầu răng sứ bằng
                            cách
                            mài 2 răng bên cạnh để làm trụ cho cầu răng sứ.
                        </li>
                        <li>
                            Dán răng sứ Veneers: Phù hợp với trường hợp răng thưa, xỉn màu hoặc mọc lệch nhẹ.
                            Không
                            đòi hỏi việc mài nhiều răng và không cần lấy tủy, bảo tồn răng tự nhiên.
                        </li>
                        <li>
                            Bọc răng sứ Implant: Giải pháp hàng đầu cho người mất chân răng, sử dụng chân răng
                            nhân
                            tạo (Implant) và gắn răng sứ lên trụ Implant đã được đặt trước đó. Không cần mài
                            răng
                            thật, mang lại khả năng nhai mạnh mẽ và sử dụng lâu dài.
                        </li>
                        <li>
                            Gắn mão răng tạm thời: Áp dụng trong khi chờ phục hình cố định, đặc biệt là cho
                            những trường hợp mất răng hoặc răng yếu cần được bảo vệ tạm thời trong thời gian chờ
                            răng sứ vĩnh viễn hoàn thành.
                        </li>
                        <li>Phủ răng sứ mặt trong: Với trường hợp mặt ngoài của răng đã bị mòn hoặc sứt, bác sĩ
                            có thể phủ sứ ở mặt trong để bảo vệ răng, tăng cường độ cứng và giúp răng chịu lực
                            tốt hơn mà vẫn duy trì được tính thẩm mỹ.
                        </li>
                        <li>
                            Bọc răng toàn sứ Zirconia: Đây là dòng răng toàn sứ cao cấp, vừa có khả năng chịu
                            lực tốt vừa có màu sắc tự nhiên, đặc biệt phù hợp với người có nhu cầu phục hồi cả
                            chức năng lẫn thẩm mỹ cho răng bị tổn thương.
                        </li>
                    </ul>
                </div>
                <div class="second">
                    <ul>
                        <li>
                            <span>Răng sứ chất lượng chống biến đổi màu</span>
                            <p>
                                Mão răng sứ được sản xuất với lớp phủ sứ chống nhiễm màu, giúp ngăn chặn hiện
                                tượng răng ố vàng hay đen xỉn từ thức ăn, cũng như tránh tình trạng biến đổi màu
                                theo thời gian. Đây là sự lựa chọn lý tưởng nếu bạn không muốn trải qua quá
                                trình biến đổi màu sắc của vật liệu nha khoa, khiến cho răng trở nên khác biệt
                                so với màu sắc tự nhiên ban đầu, như trong phương pháp hàn trám răng.
                            </p>
                        </li>
                        <li>
                            <span>Độ bền cao</span>
                            <p>
                                Để răng toàn sứ giữ được chất lượng trong thời gian dài, quan trọng nhất là
                                không tạo ra áp lực quá mức khi nghiền và ăn nhai thức ăn. Việc tránh những vật
                                cứng có thể gây sát thương là một trong những biện pháp quan trọng để đảm bảo
                                răng sứ duy trì sức mạnh và độ ổn định trên cung hàm. Điều này giúp bảo vệ răng
                                sứ, đặc biệt khi sử dụng các vật liệu cao cấp như răng sứ E.Max, HT Smile.
                            </p>
                        </li>
                        <li>
                            <span>Cố định trên cung hàm</span>
                            <p>
                                Khác biệt so với răng giả tháo lắp có thể trượt khỏi vị trí hoặc miếng trám có
                                khả năng bong tróc, bọc răng sứ giúp thân răng được gắn cố định trên cùi răng
                                thật, trên trụ răng sứ titan cấy ghép hoặc neo tại chỗ thông qua một cầu răng,
                                thay thế cho răng đã mất. Do đó, bạn có thể hoàn toàn tin tưởng vào độ chắc chắn
                                và tính ổn định của răng sứ thẩm mỹ.
                            </p>
                        </li>
                        <li>
                            <span>Phục hồi chức năng ăn nhai hiệu quả</span>
                            <p>Răng sứ chất lượng cao, được bảo hành chính hãng, thường có độ chịu lực trung
                                bình từ 360Mpa (đối với răng sứ kim loại) lên đến 900MPa (đối với răng toàn sứ),
                                vượt xa so với sức mạnh của răng thật (thường chỉ từ 80 - 120Mpa). Điều này đảm
                                bảo rằng răng sứ không chỉ đáp ứng mà còn vượt trội trong khả năng chịu đựng áp
                                lực khi nhai và cắn.</p>
                        </li>
                        <li>
                            <span>Đảm bảo khả năng phát âm</span>
                            <p>Mất răng hoặc sử dụng răng giả không vừa có thể gây biến đổi âm thanh, líu lưỡi
                                và tạo khó khăn trong việc nghe. Hơn nữa, việc mất răng có thể dẫn đến tiêu
                                xương, tiếp tục ảnh hưởng đến giọng nói hoặc tạo ra tình trạng hô móm không.
                                Việc khôi phục răng bằng mão sứ có thể giúp cải thiện chất lượng giọng nói, đưa
                                nó về tông bình thường, tạo ra sự tự tin và làm cho việc giao tiếp trở nên thuận
                                lợi hơn bao giờ hết.</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tiltle-banner">
            <p>Quy trình niềng răng tại nha khoa của chúng tôi</p>
            <p>
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2022/11/quytrinh.jpg" alt="">
            </p>
        </div>



        <!--End Main-->
        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
        
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>

</html>