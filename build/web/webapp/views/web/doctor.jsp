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
                <img src="<c:url value='/uploads/${d.photos}'/>" alt="User Photo">
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
            </div>
            <div class="right-content">
                <h3>Giới thiệu</h3>
                <p>“Tôi muốn trở thành bác sĩ để có thể nối nghiệp của mẹ…”</p>
                <p>BS ${d.firstName} ${d.lastName}tốt nghiệp Đại học Y Hà Nội,<strong>có hơn 10 năm kinh nghiệm</strong>
                    trong điều trị các bệnh lý răng miệng: Cấy ghép implant, bọc răng sứ, nhổ răng khôn, điều
                    trị tuỷ,…</p>
                <p>Mỗi năm, bác sĩ ${d.firstName} ${d.lastName} thực hiện trung bình:</p>
                <ul>
                    <li>300 ca cấy ghép implant</li>
                    <li>300 ca bọc răng sứ</li>
                    <li>1500 ca răng khôn từ khó đến phức tạp</li>
                    <li>Và hàng ngàn ca điều trị tuỷ, nha chu,…</li>
                </ul>
                <p>Bác sĩ ${d.firstName} ${d.lastName} cho rằng, mỗi ca điều trị răng cho bệnh nhân với anh không chỉ là giúp họ đỡ đau
                    đớn mà hơn thế là mong muốn mang lại nụ cười thật đẹp và khỏe mạnh.</p>
                <div>
                    <i class="fa-solid fa-user-nurse"></i>
                    <p>Hãy trở thành phiên bản hoàn hảo nhất của chính mình </p>

                </div>
            </div>

        </div>
        <!--End Main-->
        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
    </body>

</html>