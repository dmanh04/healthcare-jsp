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
        <link rel="stylesheet" href="<c:url value="webapp/assets/css/advisor.css"/>">
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
        <div class="f">
            <p class="b">Trang chủ >> Chuyên gia tư vấn</p>
            <h1>Xin chào, Chúng tôi có thể giúp gì được cho bạn?</h1>
            <div class="sb">
                <div class="sbc">
                    <form action="search" method="GET">
                        <input type="text" name="query" placeholder=" &#128269; Bạn muốn tìm hiểu về...">
                        <button type="submit">Tìm kiếm</button>
                    </form>                  
                </div>
            </div>
            <p class="ss">Hoặc bạn có thể lựa chọn các tùy chọn dưới đây</p>
            <div class="os">
                <div class="o">
                    <p>Tham khảo kiến thức nha khoa</p>
                    <a href="#">Tại đây &#8594;</a>
                </div>
                <div class="o">
                    <p>Để lại lời nhắn để có câu trả lời chi tiết</p>
                    <a href="#">Tại đây &#8594</a>
                </div>
            </div>
        </div>

        <section class="k">
            <h2>Kiến thức nha khoa</h2>
            <div class="as">
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/viem-loi.jpg" alt="Viêm lợi">
                    <button>Viêm lợi: Đừng xem thường những dấu hiệu ban đầu</button>
                </article>
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/viem-tuyen-nuoc-bot.jpg" alt="Viêm tuyến nước bọt">
                    <button>Viêm tuyến nước bọt là bệnh gì? Có lây không?</button>
                </article>
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/vi-khuan-sau-rang.jpg" alt="Vi khuẩn sâu răng">
                    <button>Vi khuẩn sâu răng-Sự thật cần biết để bảo vệ răng miệng</button>
                </article>
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/chua-nhiet-mieng-bang-rau-diep-ca.jpg" alt="Chữa nhiệt miệng">
                    <button>4 cách chữa nhiệt miệng bằng RAU DIẾP CÁ dễ áp dụng</button>
                </article>
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/mang-bam.jpg" alt="Màng bám răng">
                    <button>Sự thật về Mảng bám răng-Góc nhìn chi tiết</button>
                </article>
                <article>
                    <img src="https://nhakhoathuyduc.com.vn/wp-content/uploads/2024/10/bi-cam-cum-co-nen-nho-rang.jpg" alt="Cảm cúm">
                    <button>Bị cảm có nhổ răng được không? Vấn đề có thể xảy ra là gì?</button>
                </article>
            </div>
            <a href="#"><button>Xem thêm bài viết</button></a>

        </section>

        <div class="content">
            <div class="text">
                <h2>Câu hỏi thường gặp</h2>
                <div class="faq-item">
                    <button>Bị mất răng R6 hàm trên và dưới có niềng được không?</button>
                    <p>Chào Nha khoa Thúy Đức, được một người chị giới thiệu nha khoa với mình đến Thúy Đức. Mình đang tham khảo về quá trình niềng răng nhưng gặp một vấn đề... <br></br> 
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>[TƯ VẤN] Nhổ 2 răng khôn cùng lúc có sao không?</button>
                    <p>Bác sĩ ơi, cháu băn khoan không biết có nên nhổ 2 răng khôn cùng một lúc không? Cả 2 răng khôn đang mọc ở hàm dưới đều mọc lệch, cháu thấy vướng quá...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Thế nào là răng lòi xỉ? Điều trị răng lòi xỉ như thế nào?</button>
                    <p>Chào bác sĩ! Dạo gần đây em có nghe rất nhiều tới tình trạng răng lòi xỉ. Em thấy nó khá giống với răng khấp khểnh và chưa biết phân biệt 2 tình...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Làm sao để khay niềng trong suốt luôn trắng sáng?</button>
                    <p>Chào bác sĩ! Em đã niềng Invisalign được một thời gian rồi, em thấy phương pháp này rát hiệu quả, thẩm mỹ cao lại tiện lợi lúc ăn uống vệ sinh. Tuy...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Nhổ răng khôn sau 5 ngày vẫn đau có sao không?</button>
                    <p>Chào bác sĩ. Tôi nhổ răng khôn hàm dưới được hơn 5 ngày rồi mà vẫn thấy đau với khó chịu quá. Không biết điều này có bình thường không? Tôi tìm hiểu...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Vì sao không nên ngậm nước muối ngay sau khi nhổ răng?</button>
                    <p>Bác sĩ ơi, em có thắc mắc là tại sao không được ngậm nước muối khi vừa nhổ răng xong ạ? Nha sĩ nhổ răng cho em dặn đi dặn lại điều này cho em làm em tò mò...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Răng không có tự rụng được không?</button>
                    <p>Bác sĩ ơi cháu có một chiếc răng khôn hàm dưới đang mọc rất đau nhức, hình như phần lợi gần đó còn sưng lên và có mùi hôi khá khó chịu nữa ạ. Bạn...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Mọc 2 răng khôn cùng lúc có sao không?</button>
                    <p>Chào bác sĩ ạ. Cháu năm nay 22 tuổi, có một răng khôn hàm trên đang mọc. Hôm sau cháu bắt đầu thấy đau phần lợi hàm dưới, biểu hiện giống như lần...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>Mới nhổ răng khôn uống nước cam được không?</button>
                    <p>Cháu nhà tôi vừa mới nhổ răng khôn hôm qua, tôi cho cháu uống nước cam để bồi bổ, tăng sức đề kháng nhưng chồng tôi bảo mới nhổ xong thì không...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
                <div class="faq-item">
                    <button>E buốt sau khi nhổ răng khôn xử lý thế nào?</button>
                    <p>Chào bác sĩ, em mới nhổ răng khôn cách đây 2 ngày thấy tình trạng ê buốt ngày càng rõ rệt hơn, em lo lắng quá. Không biết là do vết mổ em có vấn...<br></br>
                        <a href="#">Xem chi tiết &#8594;</a> </p>
                </div>
            </div>
        </div>
        <script>
            document.querySelectorAll('.faq-item button').forEach(button => {
                button.addEventListener('click', () => {
                    const p = button.nextElementSibling;
                    p.style.display = p.style.display === 'block' ? 'none' : 'block';
                });
            });
        </script>

        <div class="cs">
            <h4>Bạn vẫn chưa tìm được câu trả lời?</h4>
            <p>Nếu bạn vẫn chưa tìm được câu trả lời cho câu hỏi của bạn, hãy liên hệ với chúng tôi. Chúng tôi sẽ cố gắng trả lời bạn sớm nhất.</p>
            <div class="ci">
                <div class="cb">
                    <div class="icon">📞</div>
                    <h3>093 186 3366 | 096 361 4566</h3>
                    <p>Chúng tôi rất vui được phục vụ!</p>
                </div>
                <div class="cb">
                    <div class="icon">📧</div>
                    <h3>tuvan&hoidap@thuyduc.com.vn</h3>
                    <p>Cách tốt nhất để có câu trả lời nhanh nhất.</p>
                </div>
            </div>
        </div>

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
