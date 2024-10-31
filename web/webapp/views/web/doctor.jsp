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
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->
        <!--Main-->
        <c:set var="d" value="${requestScope.doctor}" />
        <div class="first-content">
            <div class="left-content">
                <h3>Bác sĩ ${d.firstName} ${d.lastName}</h3>
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/11/bsduc.jpg" alt="User Photo">
                <ul>
                    <li>
                        Chuyên môn chính:<br>
                        <span>Niềng răng - nắn chỉnh răng, bọc răng sứ, điều trị tổng quát</span>
                    </li>
                    <li>
                        Kinh nghiệm:<br>
                        <span>5 năm</span>
                    </li>
                </ul>

                <h1>Giới thiệu</h1>

                <h2>Bác sĩ Phạm Hồng Đức là chuyên gia hàng đầu trong lĩnh vực chỉnh nha tại Việt Nam</h2>
                <p>Sau khi tốt nghiệp Trường Đại học Y Hà Nội, bác sĩ Đức nhận ra nhu cầu chính nha để khắc
                    phục những khuyết điểm như răng hô, chìa, móm, khấp khểnh, khớp cần lệch,... của bệnh nhân 
                    trong nước ngày càng cao. Tuy nhiên, bác sĩ nha khoa tổng quát thường bị hạn chế trong việc 
                    thực hiện các kỹ thuật chỉnh nha chuyên sâu, khó có thể đáp ứng được nguyện vọng của bệnh nhân.</p>
                <p>Chính vì vậy, bác sĩ Phạm Hồng Đức đã quyết tâm theo đuổi ngành chính nha chuyên nghiệp để giúp 
                    hàng ngàn khách hàng tìm lại nụ cười tự tin của mình. Để theo đuổi đam mê cũng như tìm định 
                    hướng để phát triển bản thân bác sĩ Đức đã theo học hệ cao học chuyên ngành chính nha tại 
                    Cao học Việt - Pháp, trường Đại học Bordeaux - Segalen.</p>

                <h2>Bác sĩ Phạm Hồng Đức là người sáng tạo ra phương pháp niềng không nhố răng F.A.C.E</h2>
                <p>FA.C.E được biết đến từ lâu là một chương trương trình đào tạo sau đại học tại Mỹ và Châu Âu, 
                    sau này được sử dụng như một phương pháp chỉnh nha bởi các bác sĩ giỏi nhất. Qua việc nghiên 
                    cứu, tìm tôi bác sĩ Đức đã chuyển hóa FA.C.E. đề phù hợp hơn với đặc điểm của người Việt Nam.</p>
                <p>Đối với các trường hợp răng bị sai lệch nghiêm trọng, hồ, khấp khểnh nặng, thường phải nhổ răng 
                    để lấy khoảng trống kéo răng. Tuy nhiên, với phương pháp này của bác sĩ Đức, khách hàng sẽ 
                    không phải nhó bất cứ một chiếc răng nào mà vẫn có thể niềng răng với hiệu quả cao nhất, bảo 
                    tồn nguyên vẹn răng thật đồng thời giảm thiểu tối đa sự đau đớn cho khách hàng.</p>

                <h2>Bác sĩ Đức đã điều trị thành công cho 5000+ ca niềng mắc cài và là bác sĩ đầu tiên tại 
                    Việt Nam có 2000+ ca nièng Invisalign</h2>
                <p>Đây là những con số đặc biệt, nói lên sự tin tưởng của các khách hàng dành cho bác sĩ Đức. 
                    Điều này chính l động lực để bác sĩ Đức và đội ngũ bác sĩ tại Nha khoa không ngừng học tập 
                    nâng cao tay nghề, đáp ứng nhu cầu sức khỏe răng miệng của khách hàng.</p>
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/08/chuc-mung-bac-si-duc-dat-2000-nu-cuoi-invisalign.jpg" alt="thành tích">

                <h1>Giải thưởng và thành tích</h1>
                <h4> • Được đánh giá là bác sĩ TOP 1 Việt Nam và TOP 3 Đông Nam Á về kinh nghiệm, chuyên môn 
                    niềng Invisalign (do hãng Invisalign đánh giá)</h4>
                <h4>• Bác sĩ có các ca điều trị thành công được đăng trên GLOBAL INVISALIGN GALLERY - trang 
                    web trưng bày các ca điều trị niềng răng trong suốt Invisalign xuất sắc nhất toàn cầu</h4>
                <h4>• Bác sĩ duy nhất tại Việt Nam 2 năm liên tiếp đạt thứ hạng Black Diamond</h4>
                <h4>• Bác sĩ có số lượng khách hàng niêng Invisalign nhiều nhất Việt Nam năm 2021</h4>
                <h4>• 1 trong 3 bác sĩ sử dụng hệ thống mắc cài tự động Damon thành công nhất Việt Nam</h4><br><br>
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/08/bac-si-duc-nhan-chung-nhan-balck-diamond.jpg" alt="giải thưởng">

                <h1>Học vấn, đào tạo</h1>
                <h4>• Hệ Răng - Hàm - Mặt Trường Đại học Y Hà Nội</h4>
                <h4>• Hệ cao học Việt - Pháp Đại học Bordeaux - Selagen (Pháp)</h4>
                <h4>• Các khóa học chuyên sâu về chính nha tại Mỹ và Thái Lan</h4>

                <h1>Thành viên của các tổ chức</h1>
                <h4>• Hiệp hội Chỉnh nha Hoa Kỳ AAO</h4>
                <h4>• Hiệp hội Nắn chỉnh răng Thế giới IAO</h4>
                <h4>• Hiệp hội Chỉnh nha thế giới WFO (World Federation of Orthodontists)</h4>
                
                <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/08/bac-si-duc-tham-gia-invisalign-mastery-club.jpg" alt="alt"/>

                <h1>Sách và các công trình nghiên cứu</h1>
                <p>Bác sĩ Phạm Hồng Đức là dịch già của các cuốn sách chuyên sâu về chỉnh nha:</p>
                <h4>• 1001 bí kíp lâm sàng trong chỉnh nha (2015)</h4>
                <h4>• Các ca lâm sàng trong chỉnh nha (2015)</h4>
                <h4>• Cơ sinh học trong chỉnh nhà (2016)</h4>

            </div>
            <div class="right-content">
                <h3>Dịch vụ nổi bật</h3><br>
                <button>Niềng răng mắc cài</button><br>
                <button>Niềng răng Invisalign</button><br>
                <button>Cấy ghép Implant</button><br>
                <button>Bọc răng sứ</button><br>
                <button>Nhổ răng khôn</button><br>
                <button>Hàm tháo lắp</button><br>
                <button>Điều trị nha chu</button><br>
                <button>Điều trị tủy răng</button><br>
                <div>                   
                    <p>Hãy trở thành phiên bản hoàn hảo nhất của chính mình</p><br><br><br>
                    <a href="#">Nhận tư vấn &#8594;</a>
                </div>
            </div>

        </div>
        <!--End Main-->
        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
    </body>

</html>