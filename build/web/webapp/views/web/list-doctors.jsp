<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

        <!-- Breadcrumb -->

        <!-- Nội dung chính -->
        <div class="body-container">
            <div class="group">
                <div class="breadcrumb"> Trang chủ » Đội ngũ bác sĩ </div>
                <div class="title">Đội ngũ bác sĩ</div>
                <div class="subtitle">Trang bao gồm tất cả các bác sĩ và trợ tá tại Nha khoa Thúy Đức</div></div>

            <div class="team">
                <div >
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/04/banner.png" alt="Bác sĩ và trợ tá">               
                </div>           
            </div>
        </div>

        <!--Main-->

        <c:forEach items="${requestScope.data}" var="d">
            <p class="name"> Bác sĩ ${d.firstName} ${d.lastName}</p>
            <div class="top-section">
                <div class="left">
                    <img src="<c:url value='/uploads/${d.photos}'/>" alt="User Photo">
                </div>
                <div class="right">
                    <div class="content">
                        <p>Thành viên của các tổ chức</p>
                        <ul>
                            <li><strong> Hiệp hội Chỉnh nha Hoa Kì AAO</strong></li>
                            <li><strong> Hiệp hội Nắn chỉnh răng thế giới IAO</strong></li>
                            <li><strong> Hiệp hội Chỉnh nha thế giới WFO</strong></li>
                        </ul> 
                    </div >
                    <table>
                        <tr>
                            <td><p>Chuyên môn chính :</p><br>
                                <strong>Chỉnh nha</strong><br>
                                <p>Chức danh:</p><br>
                                <strong>Giám đốc chuyên môn</strong><br><br><br><br><br><br>
                            </td>
                            <td><p>Kinh nghiệm</p><br>
                                <strong>14 năm</strong>
                            </td>
                        </tr>
                    </table>

                </div>  
            </div>
            <div class="bottom-section">
                <table>
                    <tr>
                        <td>
                            <p> Được biết đến không chỉ là một trong những chuyên gia hàng đầu trong lĩnh vực
                                chỉnh nha tại Việt Nam, bác sĩ Phạm Hồng Đuc còn được mọi người yêu mến bởi
                                sự gần gũi, tận tâm và tận tình.</p><br><br>
                            <p> &#10003; Bác sĩ Đức được hãng Invisalign đánh giá là bác sĩ TOP 1 Việt Nam và TOP 3
                                Đông Nam Á về kinh nghiệm, chuyên môn.<br><br>
                                &#10003; Bác sĩ duy nhất tại Việt Nam 2 năm liên tiếp đạt thứ hạng Black Diamond<br><br>
                                &#10003; Bác sĩ đầu tiên tại Việt Nam có 2000+ ca niềng Invisalign thành công<br><br>
                                &#10003; Bác sĩ Đức đã có kinh nghiệm điều trị hơn 5000 ca chỉnh nha mắc cài<br><br>
                                &#10003; Bác sĩ Đức được coi là một trong nhưng bác sĩ có số lượng khách hàng niềng
                                răng lớn nhất Hà Nội.</p>
                        </td>
                        <td>
                            <p>&#10003; 1 trong 3 bác sĩ sử dụng hệ thống mắc cài tự động Damon thành công nhất Việt Nam</p><br><br>
                            <p>&#10003; Bác sĩ có cá ca điều trị thành công được đăng trên GLOBAL INVISALIGN GALLERYM - trang 
                                web trưng bày các ca điều trị niềng răng trong suốt Invisalign xuất sắc nhất toàn cầu</p><br><br>
                            <p>&#10003; Dịch giả của những cuốn sách chỉnh nha nổi tiếng như 1001 bí kíp lâm sàng trong 
                                chỉnh nha(2015), Các ca lâm sàng trong chỉnh nha(2015), Cơ sinh học trong chỉnh nha(2016),...</p><br><br>
                            <p>&#10003; Là người đầu tiên đưa phương pháp Niềng không nhổ răng F.A.C.E từ nước ngoài về ứng dụng 
                                tại Việt Nam, giúp hạn chế tối đa việc nhổ răng thậm chí không cần nhổ răng mà vẫn mang lại hiệu 
                                quả điều trị cao nhất.</p><br><br>
                            <a class="info" href="<c:url value='/doctors/${d.id}'/>">
                                <span>Xem chi tiết &#8594;</span>                                 
                            </a>

                        </td>
                    </tr>
                </table>

            </div>

        </c:forEach>

        <div class="consultation">
            <section class="consultation_img">

                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/11/bsduc.png">
            </section>
            <div class="consultation_content">
                <h2>Đăng ký dịch vụ tư vấn miễn phí</h2>
                <<h3>Vui lòng để lại thông tin của bạn tại đây, để chúng tôi có thể giúp bạn</h3>
                <div class="consultation_from">
                    <form action="">
                        <div class="input-group"> 
                            <input type="text" placeholder="Họ và tên">
                            <input type="text" placeholder="Điện thoại">
                        </div>
                        <input type="text" placeholder="Dịch vụ cần tư vấn">
                        <textarea placeholder="Ghi chú"></textarea>
                        <button type="submit">Đăng ký ngay</button>
                    </form>
                </div>
            </div>
        </div>


        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
    </body>
</html>
