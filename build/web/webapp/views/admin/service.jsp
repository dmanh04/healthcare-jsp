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
                display: none;
                margin-bottom: 15px;
            }

            #errorMessage.visible {
                display: block;
            }

            .header-service {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 25px;
                padding: 10px 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            .formSearch label {
                font-weight: bold;
                margin-right: 10px;
                font-size: 16px;
            }

            .formSearch input[type="text"] {
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                width: 250px;
            }

            .formSearch button {
                margin-left: 10px;
            }

            .inner-btn {
                text-align: right;
                padding-right: 20px;
            }

            .inner-btn .btn {
                padding: 8px 15px;
                border-radius: 5px;
                background-color: #007bff;
                border-color: #007bff;
                transition: background-color 0.3s ease;
            }

            .inner-btn .btn:hover {
                background-color: #0056b3;
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
                    Service updated successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${param.add == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Service added successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <c:if test="${param.remove == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Service with ID <strong>${param.serviceId}</strong> deleted successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="header-service">
                <form class="formSearch" action="<c:url value='/admin/service'/>" method="get">
                    <label>Search name: </label>
                    <input type="text" name="search" value="${param.search != null ? param.search : ''}">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                    <div class="inner-btn">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addServiceModal">Add Service</button>
                    </div>
                </c:if>
            </div>

            <div class="container">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Service Name</th>
                            <th>Service Image</th>
                            <th>Price</th>
                            <th>Duration (min)</th>
                                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                                <th>Actions</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="service" items="${pageableService.data}">
                            <tr>
                                <td>${service.id}</td>
                                <td>${service.serviceName}</td>
                                <td> 
                                    <img src="${service.image}" style="width: 167px; object-fit: cover; height: 130px" alt="${service.serviceName}"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${service.price}" type="currency" />
                                </td>
                                <td>${service.duration}</td>
                                <c:if test="${sessionScope.ROLE_CURRENT == 'ADMIN'}">
                                    <td>
                                        <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editServiceModal${service.id}">Edit</button>
                                        <button type="button" class="btn btn-danger" onclick="showDeleteModal(${service.id}, '${service.serviceName}')">
                                            Delete
                                        </button>
                                    </td>
                                </c:if>
                            </tr>

                        <div class="modal fade" id="editServiceModal${service.id}" tabindex="-1" aria-labelledby="editServiceModalLabel${service.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editServiceModalLabel${service.id}">Edit Service</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <span id="errorMessage${service.id}" style="color: red;  font-weight: bold;
                                              margin-bottom: 15px;">${errorMessage != null ? errorMessage : ''}</span>
                                        <form id="updateServiceForm${service.id}" action="<c:url value='/admin/edit/service'/>" onblur="checkServiceName(${service.id})" method="post">
                                            <input type="hidden" name="id" value="${service.id}">
                                            <div class="mb-3">
                                                <label for="serviceName${service.id}" class="form-label">Service Name</label>
                                                <input type="text" class="form-control" id="serviceName${service.id}" name="serviceName" value="${service.serviceName}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="description${service.id}" class="form-label">Description</label>
                                                <textarea class="form-control" id="description${service.id}" name="description">${service.description}</textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label for="price${service.id}" class="form-label">Price</label>
                                                <input type="number" class="form-control" id="price${service.id}" name="price" step="0.01" value="${service.price}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="duration${service.id}" class="form-label">Duration (in minutes)</label>
                                                <input type="number" class="form-control" id="duration${service.id}" name="duration" value="${service.duration}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="image${service.id}" class="form-label">Image URL</label>
                                                <input type="text" class="form-control" id="image${service.id}" name="image" value="${service.image}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="icon${service.id}" class="form-label">Icon URL</label>
                                                <input type="text" class="form-control" id="icon${service.id}" name="icon" value="${service.icon}">
                                            </div>
                                            <button type="submit"  onclick="checkSubmit(${service.id}); return false;"  class="btn btn-primary">Update Service</button>
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
                    <li class="page-item <c:if test="${pageableService.page == 1}">disabled</c:if>">
                        <a class="page-link" href="<c:url value='/admin/service?page=${pageableService.page - 1}&search=${param.search}'/>">Previous</a>
                    </li>

                    <c:set var="totalPages" value="${pageableService.totalPage}" />
                    <c:set var="currentPage" value="${pageableService.page}" />

                    <c:if test="${totalPages > 0}">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                                <a class="page-link" href="<c:url value='/admin/service?page=${i}&search=${param.search}'/>">${i}</a>
                            </li>
                        </c:forEach>
                    </c:if>

                    <li class="page-item <c:if test="${pageableService.page == pageableService.totalPage}">disabled</c:if>">
                        <a class="page-link" href="<c:url value='/admin/service?page=${pageableService.page + 1}&search=${param.search}'/>">Next</a>
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
                        Are you sure you want to delete the service "<span id="deleteServiceName"></span>"?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <!-- Dynamically update the form action with the service ID -->
                        <form id="deleteForm" action="<c:url value='/admin/delete/service'/>" method="post">
                            <input type="hidden" id="deleteServiceId" name="id">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>



        <!-- Add Service Modal -->
        <div class="modal fade" id="addServiceModal" tabindex="-1" aria-labelledby="addServiceModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addServiceModalLabel">Add Service</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" ></button>
                    </div>
                    <div class="modal-body">
                        <span id="errorMessage"></span>
                        <form id="addServiceForm" action="<c:url value='/admin/service'/>" method="post">
                            <div class="mb-3">
                                <label for="serviceName" class="form-label">Service Name*</label>
                                <input type="text" class="form-control" id="serviceName" onblur="checkName()" name="serviceName" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">Price*</label>
                                <input type="number" class="form-control" id="price" name="price" step="0.01" required>
                            </div>
                            <div class="mb-3">
                                <label for="duration" class="form-label">Duration (in minutes)*</label>
                                <input type="number" class="form-control" id="duration" name="duration" required>
                            </div>
                            <div class="mb-3">
                                <label for="image" class="form-label">Image URL*</label>
                                <input type="text" class="form-control" id="image" name="image" required="">
                            </div>
                            <div class="mb-3">
                                <label for="icon" class="form-label">Icon URL</label>
                                <input type="text" class="form-control" id="icon" name="icon">
                            </div>
                            <button type="submit" class="btn btn-primary">Add Service</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--Footer-->
        <%@include file="../../common/admin/footer.jsp" %>
        <!--Footer-->

        <script>

            function checkSubmit(serviceId) {
                const errorMessageElement = document.getElementById(`errorMessage` + serviceId);
                if (errorMessageElement.textContent.trim() !== '') {
                    alert('Please fix the errors before submitting the form.');
                    return false;
                } else {
                    document.getElementById(`editServiceModal` + serviceId).submit();
                }
            }

            function checkServiceName(serviceId) {
                const serviceName = document.getElementById(`serviceName` + serviceId).value;
                const params = new URLSearchParams();
                params.append('serviceName', serviceName);
                params.append('id', serviceId);

                fetch('/Healthcare/api/service', {
                    method: 'POST',
                    body: params
                })
                        .then(response => response.text())
                        .then(exists => {
                            const errorMessageElement = document.getElementById(`errorMessage` + serviceId);
                            if (exists === 'true') {
                                errorMessageElement.textContent = 'Service name already exists.';
                                errorMessageElement.style.display = 'block';
                            } else {
                                errorMessageElement.textContent = ''; // Clear the error message
                                errorMessageElement.style.display = 'none';
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
            }

            <c:forEach var="service" items="${pageableService.data}">
            document.getElementById(`serviceName${service.id}`).addEventListener('blur', function () {
                checkServiceName(${service.id}); // Pass the serviceId to the function
            });
            </c:forEach>

            function showDeleteModal(serviceId, serviceName) {
                document.getElementById('deleteServiceId').value = serviceId;
                document.getElementById('deleteServiceName').textContent = serviceName;
                var deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
                deleteModal.show();
            }

            document.getElementById('serviceName').addEventListener('blur', function () {
                const serviceName = this.value.trim();
                const errorMessageDiv = document.getElementById('errorMessage');
                errorMessageDiv.style.display = 'none'; // Initially hide the error message
                if (!serviceName) {
                    errorMessageDiv.innerHTML = 'Service name is required.';
                    errorMessageDiv.classList.remove('d-none');
                    errorMessageDiv.style.display = 'block';
                    return;
                }
                fetch('/Healthcare/api/service', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'serviceName': serviceName
                    })
                })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.text();
                        })
                        .then(data => {
                            if (data === 'true') {
                                errorMessageDiv.innerHTML = 'Service name already exists.';
                                errorMessageDiv.classList.remove('d-none');
                                errorMessageDiv.style.display = 'block';
                            } else {
                                errorMessageDiv.style.display = 'none'; // No errors, hide the message
                            }
                        })
                        .catch(error => {
                            console.error('There was a problem with the fetch operation:', error);
                        });
            });

            document.getElementById('addServiceForm').addEventListener('submit', function (event) {
                const errorMessageDiv = document.getElementById('errorMessage');
                if (errorMessageDiv.textContent.trim() !== '' && errorMessageDiv.style.display === 'block') {
                    alert('Please fix the errors before submitting the form.');
                    event.preventDefault();
                    return;
                }
                const modal = bootstrap.Modal.getInstance(document.getElementById('addServiceModal'));
                modal.hide();
            });
        </script>




        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
