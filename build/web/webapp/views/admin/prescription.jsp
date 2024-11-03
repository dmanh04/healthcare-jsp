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


        </style>

        <!--Header-->
        <%@include file="../../common/admin/header.jsp" %>
        <!--End Header-->
        <!--Menu-->
        <%@include file="../../common/admin/menu.jsp" %>
        <!--End Menu-->
        <div class="content">
            <h2>Mã hồ sơ bệnh án: ${medicalRecords.id}</h2>
            <h5>Khách hàng: ${appointments.customerName}</h5>
            <p>Chẩn đoán: ${medicalRecords.diagnosis}</p>
            <p>Điều trị: ${medicalRecords.treatment}</p>
            <div class="row">
                <div class="col-md-5">
                    <h5 class="mb-3">Chọn thuốc</h5>
                    <div style="max-height: 500px; overflow-y: auto;">
                        <form>
                            <label for="searchMedicine">Tìm thuốc theo tên:</label>
                            <input 
                                type="text" 
                                id="searchMedicine" 
                                name="nameMedicine" 
                                placeholder="Nhập tên thuốc..." 
                                aria-label="Search Medicine" 
                                style="width: 100%; padding: 8px; margin-bottom: 15px; border: 1px solid #ced4da; border-radius: 4px; font-size: 16px;"
                                >
                        </form>
                        <table class="table table-bordered table-hover">
                            <thead class="table-light">
                                <tr>
                                    <th>Id</th>
                                    <th>Tên thuốc</th>
                                    <th>Giá</th>
                                    <th>Số lượng còn (viên)</th>
                                    <th>Chọn thuốc</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${listMedicine}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.medicineName}</td>
                                        <td><fmt:formatNumber value="${item.price}" type="currency" /></td>
                                        <td>${item.quantityInStock}</td>
                                        <td style="text-align: center">
                                            <button 
                                                type="button" 
                                                class="btn btn-sm btn-primary" 
                                                data-bs-toggle="modal" 
                                                data-bs-target="#medicineMapperModal"
                                                onclick="setMedicineId(${item.id}, '${item.medicineName}', ${item.quantityInStock})"> 
                                                <i class="fa-solid fa-plus" style="font-size: 15px; margin-left: 0;"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-7">
                    <h5 class="mb-3">Đơn thuốc</h5>
                    <table class="table table-bordered table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>#</th>
                                <th>Tên thuốc</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Cách dùng</th>
                                <th>Tổng cộng</th>
                                <th>Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalPrice" value="0" />
                            <c:forEach var="prescription" items="${listPreResponse}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${prescription.medicine.medicineName}</td>
                                    <td>${prescription.quantityPrescribed}</td>
                                    <td><fmt:formatNumber value="${prescription.medicine.price}" type="currency" /></td>
                                    <td>${prescription.notes}</td>
                                    <td><fmt:formatNumber value="${prescription.medicine.price * prescription.quantityPrescribed}" type="currency" /></td>
                                    <td>
                                        <button class="btn btn-danger btn-sm" 
                                                onclick="deletePrescription(${prescription.id})">Xóa</button>
                                    </td>
                                </tr>
                                <c:set var="totalPrice" value="${totalPrice + (prescription.medicine.price * prescription.quantityPrescribed)}" />
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="text-end">
                        <h6>Tổng giá:   <fmt:formatNumber value="${totalPrice}" type="currency" /></h6>
                    </div>
                </div>

            </div>

            <div class="modal fade" id="medicineMapperModal" tabindex="-1" aria-labelledby="medicineMapperModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="medicineMapperModalLabel">Medicine Mapper</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="medicineMapperForm">
                                <div class="mb-3">
                                    <p id="medicineDisplayName" class="fw-bold"></p>
                                    <p id="maxQuantityDisplay" class="text-muted"></p>
                                    <label for="quantityInput" class="form-label">Quantity</label>
                                    <input type="number" class="form-control" name="quantityPrescribed" id="quantityInput" required min="1" />
                                </div>
                                <div class="mb-3">
                                    <label for="usageInput" class="form-label">Usage Instructions</label>
                                    <textarea class="form-control" name="notes" id="usageInput" rows="3" required></textarea>
                                </div>
                                <input type="hidden" id="medicineId" name="medicineId"/>
                                <input type="hidden" id="appointmentID" name="recordId" value="${medicalRecords.id}" />
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button type="button" class="btn btn-primary" id="submitMedicineMapper">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--Footer-->
            <%@include file="../../common/admin/footer.jsp" %>
            <!--Footer-->

            <script>
                function deletePrescription(id) {
                    if (confirm('Bạn có chắc chắn muốn xóa đơn thuốc này?')) {
                        fetch('/Healthcare/api/prescriptions?id=' + id, {
                            method: 'DELETE',
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        })
                                .then(response => {
                                    if (response.ok) {
                                        alert('Đơn thuốc đã được xóa thành công!');
                                        location.reload();
                                    } else {
                                        alert('Có lỗi xảy ra khi xóa đơn thuốc. Vui lòng thử lại.');
                                    }
                                })
                                .catch(error => {
                                    console.error('Error:', error);
                                    alert('Có lỗi xảy ra. Vui lòng thử lại.');
                                });
                    }
                }
                document.getElementById("submitMedicineMapper").addEventListener("click", async () => {
                    const quantityPrescribed = document.getElementById("quantityInput").value;
                    const note = document.getElementById("usageInput").value;
                    const medicineId = document.getElementById("medicineId").value;
                    const recordId = document.getElementById("appointmentID").value;
                    const data = {
                        quantityPrescribed: quantityPrescribed,
                        notes: note,
                        medicineId: medicineId,
                        recordId: recordId
                    };

                    try {
                        const response = await fetch("/Healthcare/api/prescriptions", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(data)
                        });

                        if (response.ok) {
                            const result = await response.json();
                            alert("Medicine mapped successfully!");
                            location.reload();
                        } else {
                            const error = await response.json();
                            alert("Failed to map medicine: " + error.message);
                        }
                    } catch (error) {
                        console.error("Error:", error);
                        alert("An error occurred. Please try again later.");
                    }
                });
                function setMedicineId(id, name, maxQuantity) {
                    document.getElementById('medicineId').value = id;
                    document.getElementById('medicineDisplayName').textContent = `Medicine: ` + name;

                    const quantityInput = document.getElementById('quantityInput');
                    quantityInput.max = maxQuantity;
                    quantityInput.value = "";
                    const maxQuantityDisplay = document.getElementById('maxQuantityDisplay');
                    maxQuantityDisplay.textContent = `Max Quantity Available: ` + maxQuantity;
                }
            </script>

            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
