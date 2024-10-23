<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib.jsp" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin service</title>
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/reset.css"/>">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/base.css"/>">
        <link rel="stylesheet" href="<c:url value="/webapp/assets/css/style_admin.css"/>">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>

        <style>

            #errorMessage {
                color: red;
                font-weight: bold;
                display: block;
                margin-bottom: 15px;
            }
            .header-user {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 25px;
                padding: 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            .formSearch {
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                gap: 15px;
            }

            .formSearch label {
                font-weight: bold;
                font-size: 16px;
                margin-right: 10px;
            }

            .formSearch input[type="text"],
            .formSearch select {
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                width: 150px;
            }

            .formSearch button {
                padding: 10px 20px;
                border-radius: 5px;
                background-color: #007bff;
                color: white;
                border-color: #007bff;
                transition: background-color 0.3s ease;
                font-weight: bold;
            }

            .formSearch button:hover {
                background-color: #0056b3;
            }


            .inner-btn .btn {
                padding: 10px 20px;
                border-radius: 5px;
                background-color: #007bff;
                color: white;
                border-color: #007bff;
                transition: background-color 0.3s ease;
                font-weight: bold;
            }

            .inner-btn .btn:hover {
                background-color: #0056b3;
            }


            .fs-18{
                margin: 0px;
                font-size: 15px;
            }
            @media (max-width: 768px) {
                .header-user {
                    flex-direction: column;
                }

                .inner-btn {
                    text-align: left;
                    margin-top: 20px;
                    padding: 0;
                }
            }

        </style>

        <!--Header-->
        <%@include file="../../common/admin/header.jsp" %>
        <!--End Header-->
        <!--Menu-->
        <%@include file="../../common/admin/menu.jsp" %>
        <!--End Menu-->
        <div class="content">
            <c:if test="${param.edit == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User updated successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${param.add == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User added successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <c:if test="${param.remove == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    User with ID <strong>${param.id}</strong> deleted successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="header-user">
                <form class="formSearch" action="<c:url value='/admin/user'/>" method="get">
                    <label>Username: </label>
                    <input type="text" name="usernameSearch" value="${param.usernameSearch != null ? param.usernameSearch : ''}">
                    <label>Fullname: </label>
                    <input type="text" name="fullnameSearch" value="${param.fullnameSearch != null ? param.fullnameSearch : ''}">
                    <select name="roleIdSearch">
                        <option value="">Chọn role</option>
                        <c:forEach items="${roleMap}" var="role">
                            <option value="${role.key}" ${role.key == param.roleIdSearch ? 'selected' : ''}>${role.value}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
                <div class="inner-btn">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUserModal">Add User</button>
                </div>
            </div>

            <div class="container">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Phone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${pageableUser.data}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                                <td>${user.role.roleName}</td>
                                <td>${user.phone}</td>
                                <td>
                                    <button class="btn btn-primary btn-upload" data-bs-toggle="modal" data-bs-target="#uploadPhotosModal${user.id}"><i class="fa-solid fa-upload fs-18"></i></button>
                                    <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editUserModal${user.id}"><i class="fa-solid fa-pen-to-square fs-18" style="color: white"></i></button>
                                    <button type="button" class="btn btn-danger" onclick="showDeleteModal(${user.id}, '${user.username}')">
                                        <i class="fa-solid fa-trash fs-18" style="color: white"></i>
                                    </button>
                                </td>
                            </tr>

                        <div class="modal fade" id="editUserModal${user.id}" tabindex="-1" aria-labelledby="editUserModalLabel${user.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editUserModalLabel${user.id}">Edit User</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="updateUserForm${user.id}" action="<c:url value='/admin/user/edit'/>" onsubmit="return validateForm(`${user.id}`)" method="post">
                                            <span id="errorMessage${user.id}"
                                                  style="  color: red;
                                                  font-weight: bold;
                                                  display: block;
                                                  margin-bottom: 15px;"   ></span>
                                            <input type="hidden" name="id" value="${user.id}">
                                            <div class="mb-3">
                                                <label for="username${user.id}" class="form-label">Username</label>
                                                <input type="text" class="form-control" id="username${user.id}" name="username" onblur="checkUsernameEdit(`${user.id}`)" value="${user.username}" required>
                                            </div>

                                            <div class="mb-3">
                                                <label for="firstName${user.id}" class="form-label">First Name</label>
                                                <input type="text" class="form-control" id="firstName${user.id}" name="firstName" value="${user.firstName}" required>
                                            </div>

                                            <div class="mb-3">
                                                <label for="lastName${user.id}" class="form-label">Last Name</label>
                                                <input type="text" class="form-control" id="lastName${user.id}" name="lastName" value="${user.lastName}" required>
                                            </div>

                                            <div class="mb-3">
                                                <label for="phone${user.id}" class="form-label">Phone</label>
                                                <input type="text" class="form-control" id="phone${user.id}" name="phone" value="${user.phone}" required>
                                            </div>

                                            <div class="mb-3">
                                                <label for="email${user.id}" class="form-label">Email</label>
                                                <input type="email" class="form-control" id="email${user.id}" name="email" value="${user.email}" required>
                                            </div>

                                            <div class="mb-3">
                                                <label for="roleId${user.id}" class="form-label">Role</label>
                                                <select name="roleId" id="roleId${user.id}" class="form-select" required>
                                                    <c:forEach items="${roleMap}" var="role">
                                                        <option value="${role.key}" 
                                                                <c:if test="${role.key == user.role.id}">selected</c:if>
                                                                    >
                                                                ${role.value}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary" >Update User</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Upload Photos Modal -->
                        <div class="modal fade" id="uploadPhotosModal${user.id}" tabindex="-1" aria-labelledby="uploadPhotosModalLabel${user.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="uploadPhotosModalLabel${user.id}">Upload Photos for ${user.username}</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-success">
                                                ${message}
                                            </div>
                                        </c:if>
                                        <div id="photoPreview${user.id}" class="mb-3">
                                            <label class="form-label">Photos:</label>
                                            <img src="<c:url value='/uploads/${user.photos}'/>" alt="User Photo" class="img-thumbnail me-2" style="max-width: 100px; max-height: 100px;">
                                        </div>
                                        <form id="uploadPhotosForm${user.id}" action="<c:url value='/admin/user/upload'/>" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <div class="mb-3">
                                                <label for="photos${user.id}" class="form-label">Select New Photos</label>
                                                <input type="file" class="form-control" id="photos${user.id}" name="photos" multiple required onchange="previewPhotos(event, ${user.id})">
                                            </div>
                                            <button type="submit" class="btn btn-primary">Upload Photos</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table> 
            </div>  

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <!-- Previous Button -->
                    <li class="page-item ${pageableUser.page == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="<c:url value='/admin/user?page=${pageableUser.page - 1}&username=${param.username}&fullname=${param.fullname}&roleId=${param.roleId}'/>">Previous</a>
                    </li>

                    <c:set var="totalPages" value="${pageableUser.totalPage}" />
                    <c:set var="currentPage" value="${pageableUser.page}" />

                    <c:if test="${totalPages > 0}">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="<c:url value='/admin/user?page=${i}&username=${param.username}&fullname=${param.fullname}&roleId=${param.roleId}'/>">${i}</a>
                            </li>
                        </c:forEach>
                    </c:if>

                    <!-- Next Button -->
                    <li class="page-item ${pageableUser.page == pageableUser.totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="<c:url value='/admin/user?page=${pageableUser.page + 1}&username=${param.username}&fullname=${param.fullname}&roleId=${param.roleId}'/>">Next</a>
                    </li>
                </ul>
            </nav>     
        </div>


        <!-- Delete Confirmation Modal (Use only one modal for all delete actions) -->
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteConfirmModalLabel">Confirm Delete</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete user "<span id="usernameRemove"></span>"?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <form id="deleteForm" action="<c:url value='/admin/user/delete'/>" method="post">
                            <input type="hidden" id="deleteUserId" name="id">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Add User Modal -->
        <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addServiceModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addServiceModalLabel">Add User</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <span id="errorMessage"></span>
                        <form id="addUserForm" action="/Healthcare/admin/user" method="post" onsubmit="return validateForm()">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username*</label>
                                <input type="text" class="form-control" id="username" name="username" onblur="checkUserName()" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password*</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="firstName" class="form-label">First name*</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" required>
                            </div>
                            <div class="mb-3">
                                <label for="lastName" class="form-label">Last name*</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" required>
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">Phone*</label>
                                <input type="text" class="form-control" id="phone" name="phone" required>
                            </div>
                            <div class="mb-3">
                                <label for="roleId" class="form-label">Role</label>
                                <select name="roleId" id="roleId" class="form-select" required>
                                    <c:forEach items="${roleMap}" var="role">
                                        <option value="${role.key}">${role.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Add User</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>            



        <!--Footer-->
        <%@include file="../../common/admin/footer.jsp" %>
        <!--Footer-->


        <script>

            function previewPhotos(event, userId) {
                const previewContainer = document.getElementById(`photoPreview` + userId);
                previewContainer.innerHTML = '<label class="form-label">Photos:</label>';

                const files = event.target.files;
                for (let i = 0; i < files.length; i++) {
                    const file = files[i];
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        img.alt = `User Photo ${i + 1}`;
                        img.className = 'img-thumbnail me-2';
                        img.style.maxWidth = '100px';
                        img.style.maxHeight = '100px';
                        previewContainer.appendChild(img);
                    };
                    reader.readAsDataURL(file);
                }
            }

            function checkUsernameEdit(userId) {
                const userInput = document.getElementById('username' + userId).value;

                if (!userInput) {
                    document.getElementById('errorMessage' + userId).innerText = '';
                    return;
                }
                fetch(`/Healthcare/api/user`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        username: userInput,
                        id: userId
                    })
                })
                        .then(response => response.text())
                        .then(data => {
                            if (data === 'true') {
                                document.getElementById('errorMessage' + userId).innerText = 'Username already exists.';
                            } else {
                                document.getElementById('errorMessage' + userId).innerText = '';
                            }
                        })
                        .catch(error => {
                            console.error('Error checking username:', error);
                            document.getElementById('errorMessage' + userId).innerText = 'An error occurred. Please try again.';
                        });
            }

            function showDeleteModal(userId, username) {
                document.getElementById('usernameRemove').textContent = username;
                document.getElementById('deleteUserId').value = userId;
                var deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
                deleteModal.show();
            }

            function checkUserName() {
                const username = document.getElementById('username').value;
                if (!username) {
                    document.getElementById('errorMessage').innerText = '';
                    return;
                }
                fetch(`/Healthcare/api/user`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        username: username
                    })
                })
                        .then(response => response.text())
                        .then(data => {
                            if (data === 'true') {
                                document.getElementById('errorMessage').innerText = 'Service name already exists.';
                            } else {
                                document.getElementById('errorMessage').innerText = '';
                            }
                        })
                        .catch(error => {
                            console.error('Error checking username:', error);
                            document.getElementById('errorMessage').innerText = 'An error occurred. Please try again.';
                        });
            }

            function validateForm() {
                const errorMessage = document.getElementById('errorMessage').innerText;
                if (errorMessage) {
                    alert('Please fix the errors before submitting.');
                    return false;
                }
                return true;
            }

            function validateForm(userId) {
                const errorMessage = document.getElementById('errorMessage' + userId).innerText;
                if (errorMessage) {
                    alert('Please fix the errors before submitting.');
                    return false;
                }
                return true;
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
