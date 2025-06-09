<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulir Customer</title>
    <%-- Sisipkan link ke Bootstrap CSS dan file CSS kustom Anda di sini --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Formulir Data Pelanggan</h2>
    <p>Silakan isi data pelanggan di bawah ini.</p>

    <div class="card">
        <div class="card-body">
            <%--
                - action: Mengarah ke servlet yang menangani logika.
                - method: Harus "post" untuk mengirim data pembaruan.
            --%>
            <form action="${pageContext.request.contextPath}/manageCustomer" method="post">

                <%--
                    PENTING: Hidden field ini mengirimkan action 'update'
                    dan ID pelanggan yang sedang diedit. Tanpa ini, servlet tidak tahu
                    data mana yang harus diperbarui.
                --%>
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="${customer.id}" />

                <div class="mb-3">
                    <label for="nama" class="form-label">Nama Pelanggan</label>
                    <input type="text" class="form-control" id="nama" name="nama" value="<c:out value='${customer.nama}'/>" required>
                </div>
                <div class="mb-3">
                    <label for="kontak" class="form-label">Kontak (No. HP atau Email)</label>
                    <input type="text" class="form-control" id="kontak" name="kontak" value="<c:out value='${customer.kontak}'/>" required>
                </div>

                <a href="${pageContext.request.contextPath}/manageCustomer?action=list" class="btn btn-secondary">Batal</a>
                <button type="submit" class="btn btn-primary">Simpan Perubahan</button>
            </form>
        </div>
    </div>
</div>

<%-- Sisipkan link ke Bootstrap JS dan file skrip kustom Anda di sini --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>