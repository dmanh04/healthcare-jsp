<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/reset.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/base.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/style.css'/>">
        <link rel="stylesheet" href="<c:url value='webapp/assets/css/account.css'/>">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <!--Header-->
        <%@include file="../../common/web/header.jsp" %>
        <!--End Header-->

        <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="position: fixed; top: 20px; right: 20px; z-index: 1050;">
            <div class="toast-header">
                <strong class="me-auto" style="font-size: 1.4rem;">Notification</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <p>Profile updated successfully!</p>
            </div>
        </div>


        <div class="account">
            <div class="content">
                <div class="sub-menu">
                    <li class="sub-name">
                        <img src="<c:url value='/uploads/${sessionScope.PHOTOS_CURRENT}'/>" alt="photos"/>
                        <span>${sessionScope.USER_CURRENT}</span>
                    </li>
                    <ul class="sub-nav">
                        <li class="sub-item"><a href="#" class="sub-link">Cập nhật hồ sơ</a></li>
                        <li class="sub-item"><a href="<c:url value='manage-appointment'/>" class="sub-link">Quản lý hoạt động</a></li>
                        <li class="sub-item"><a href="<c:url value='change-password'/>" class="sub-link">Đổi mật khẩu</a></li>
                        <li class="sub-item"><a href="<c:url value='/logout'/>" class="sub-link">Đăng xuất</a></li>
                    </ul>
                </div>

                <div class="body">
                    <div class="breadcum">Trang chủ / Cập nhật hồ sơ</div>
                    <div class="main-content">
                        <form id="updateUserForm" action="<c:url value='account'/>" onsubmit="return validateForm('${user.id}')" method="post">
                            <span id="errorMessage${user.id}" style="color: red; font-weight: bold; display: block; font-size: 1.4rem; margin-bottom: 15px;"></span>
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" class="form-control" id="username${user.id}" name="username" value="${user.username}" required>

                            <div class="mb-3">
                                <label for="firstName${user.id}" class="form-label">First Name</label>
                                <input type="text" class="form-control" id="firstName${user.id}" name="firstName" value="${user.firstName}" required>
                            </div>

                            <div class="mb-3">
                                <label for="lastName${user.id}" class="form-label">Last Name</label>
                                <input type="text" class="form-control" id="lastName${user.id}" name="lastName" value="${user.lastName}" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Gender*</label>
                                <div class="d-flex align-items-center">
                                    <div class="form-check me-3">
                                        <input type="radio" class="form-check-input" id="genderMale${user.id}" name="gender" value="Male" <c:if test="${user.gender == 'Male'}">checked</c:if> required>
                                        <label class="form-check-label" for="genderMale${user.id}">Male</label>
                                    </div>
                                    <div class="form-check me-3">
                                        <input type="radio" class="form-check-input" id="genderFemale${user.id}" name="gender" value="Female" <c:if test="${user.gender == 'Female'}">checked</c:if>>
                                        <label class="form-check-label" for="genderFemale${user.id}">Female</label>
                                    </div>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" id="genderOther${user.id}" name="gender" value="Other" <c:if test="${user.gender == 'Other'}">checked</c:if>>
                                        <label class="form-check-label" for="genderOther${user.id}">Other</label>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="dob${user.id}" class="form-label">Dob</label>
                                <input type="date" class="form-control" id="dob${user.id}" name="dob"  value="${user.dob}">
                            </div>

                            <div class="mb-3">
                                <label for="phone${user.id}" class="form-label">Phone</label>
                                <input type="text" class="form-control" id="phone${user.id}" name="phone" value="${user.phone}" required>
                            </div>

                            <div class="center">
                                <button type="submit" class="btn btn-primary">Cập nhật hồ sơ</button>
                            </div>
                        </form>

                        <div id="photoPreview" class="mb-3" style="padding: 20px; background-color: #fff">
                            <label class="form-label">Photos:</label>
                            <div class="outer-img" style="text-align: center">
                                <img id="userPhoto" src="<c:url value='/uploads/${user.photos}'/>" alt="User Photo" class="img-thumbnail me-2" style="max-width: 100px; max-height: 100px;">
                            </div>
                        </div>
                        <form id="uploadPhotosForm" action="<c:url value='account/upload'/>" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${user.id}">
                            <div class="mb-3">
                                <label for="photos" class="form-label">Select New Photos</label>
                                <input type="file" class="form-control" id="photos" name="photos" multiple required onchange="previewPhotos(event)">
                            </div>
                            <div class="center">
                                <button type="submit" class="btn btn-primary">Cập nhật ảnh đại diện</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>    

        <!--Footer-->
        <%@include file="../../common/web/footer.jsp" %>
        <!--Footer-->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

        <script>
                                    function previewPhotos(event) {
                                        const files = event.target.files;

                                        if (files.length > 0) {
                                            const reader = new FileReader();
                                            reader.onload = function (e) {
                                                const userPhoto = document.getElementById('userPhoto');
                                                userPhoto.src = e.target.result;
                                            };
                                            reader.readAsDataURL(files[0]);
                                        }
                                    }
                                    document.addEventListener("DOMContentLoaded", function () {
                                        const urlParams = new URLSearchParams(window.location.search);
                                        const success = urlParams.get("success");
                                        if (success === "true") {
                                            const toastEl = document.getElementById("toast");
                                            const toast = new bootstrap.Toast(toastEl);
                                            toast.show();
                                        }
                                    });

                                    function validateForm(userId) {
                                        const phone = document.getElementById("phone" + userId).value;
                                        const errorMessage = document.getElementById("errorMessage" + userId);
                                        const vietnamPhoneRegex = /^(0[3|5|7|8|9][0-9]{8})$/;
                                        errorMessage.innerText = "";
                                        if (!vietnamPhoneRegex.test(phone)) {
                                            errorMessage.innerText =
                                                    "Please enter a valid Vietnamese phone number.";
                                            return false;
                                        }
                                        return true;
                                    }
        </script>
    </body>
</html>
