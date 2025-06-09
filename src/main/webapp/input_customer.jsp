<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Input Pelanggan"/>
</jsp:include>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-7">

            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Langkah 1: Input Data Pelanggan</h3>
                </div>
                <div class="card-body">
                    <p class="card-text text-muted">Silakan masukkan nama dan kontak pelanggan yang akan melakukan servis.</p>
                    <form action="CustomerController" method="post" onsubmit="return validasiFormPelanggan();">
                        <div class="mb-3">
                            <label for="nama" class="form-label"><strong>Nama Pelanggan</strong></label>
                            <input type="text" class="form-control" id="nama" name="nama" required>
                            <div id="namaError" class="invalid-feedback">Nama tidak boleh kosong.</div>
                        </div>
                        <div class="mb-3">
                            <label for="kontak" class="form-label"><strong>Kontak (No. HP atau Email)</strong></label>
                            <input type="text" class="form-control" id="kontak" name="kontak" required>
                            <div id="kontakError" class="invalid-feedback">Format kontak tidak valid.</div>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-lg">Lanjutkan ke Pilih Layanan <i class="fas fa-arrow-right"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>