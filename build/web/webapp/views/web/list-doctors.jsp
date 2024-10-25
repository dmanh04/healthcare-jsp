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
                            <li><strong> Hiệp hội chỉnh nha Hoa Kì</strong></li>
                            <li><strong> Hiệp hội nắn chỉnh răng thế giới</strong></li>
                            <li><strong> Hiệp hội chỉnh nha thế giới</strong></li>
                        </ul> 
                    </div >
                    <table>
                        <tr>
                            <td><p>Chuyên môn chính :</p><br>
                                <strong>Chỉnh nha</strong><br><br><br><br><br>
                                <p>Chức danh:</p><br>
                                <strong>Bác sĩ nha khoa</strong><br>
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
                            <p> " Được biết đến không chỉ là một trong những chuyên gia hàng đầu trong lĩnh vực
                                chỉnh nha tại Viet Nam, bác sĩ Phạm Hồng Đuc còn được mọi người yêu mến bởi
                                sự gần gũi, tận tâm và tận tình."</p><br>
                            <p> Bác sĩ Đuc được hang Invisalign đánh giá là bác sĩ TOP 1 Việt Nam và TOP 3
                                Đông Nam Á về kinh nghiệm, chuyên môn.


                                Bác sĩ duy nhất tại Việt Nam 2 nam liên tiếp đạt thứ hạng Black Diamond

                                Bác sĩ đầu tiên tại Việt Nam có 2000+ ca niềng Invisalign thành công

                                Bác sĩ Đuc đã có kinh nghiệm điều trị hơn 5000 ca chỉnh nha mắc cài

                                Bác sĩ Đuc được coi là một trong nhung bác sĩ có số lượng khach hàng niềng
                                răng lớn nhất Hà Nội.</p>
                        </td>
                        <td>
                            <strong>Các chứng chỉ đào tạo:</strong><br><br>
                            <p>- Chứng chỉ đào tạo bác sĩ chỉnh nha do Sở y tế Hà Nội cấp</p><br>
                            <p>-Chương trình TAD Module 2 Nieng răng tăng trưởng</p><br>
                            <p>- Khoa học chỉnh nha chuyen sâu: Đào Tạo Chỉnh Nha Chuyen Nghiep "Immersion
                                in Bioprogressive-Meaw"</p><br>
                            <p>- Tham dự hội nghị khoa học & triển lãm nắn chỉnh răng lần 2: Hướng đến kết quả
                                chỉnh nha thành công với tiếp cận đa chuyên khoa</p> 


                        </td>
                    </tr>
                </table>


            </div>

            <a class="info" href="<c:url value='/doctors/${d.id}'/>">
                <span>Xem thông tin chi tiết</span>&nbsp;&nbsp;
                <i class="fa-solid fa-arrow-right fa-2x"></i>
            </a> 
        </c:forEach>




        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->
    </body>
</html>
