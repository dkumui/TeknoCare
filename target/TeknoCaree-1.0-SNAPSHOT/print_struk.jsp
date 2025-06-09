<%@ page import="com.mycompany.teknocaree.model.Struk" %>
<%@ page import="com.mycompany.teknocaree.model.Customer" %>
<%@ page import="com.mycompany.teknocaree.model.LayananService" %>
<%@ page import="com.mycompany.teknocaree.model.Teknisi" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ include file="header.jsp" %>

<!-- <%
    Struk struk = (Struk) request.getAttribute("struk");
    String jenisLayanan = (String) request.getAttribute("jenisLayanan");

    // Notifikasi dari ServiceServlet
    String serviceSaveSuccess = (String) request.getAttribute("serviceSaveSuccess");
    String serviceSaveError = (String) request.getAttribute("serviceSaveError");

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
%> -->

<% if (serviceSaveSuccess != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= serviceSaveSuccess %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Tutup"></button>
    </div>
<% } %>
<% if (serviceSaveError != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= serviceSaveError %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Tutup"></button>
    </div>
<% } %>

<% if (struk != null && struk.getCustomer() != null && struk.getLayanan() != null && struk.getTeknisi() != null) { %>
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0">Struk Servis TeknoCare</h3>
        </div>
        <div class="card-body">
            <div class="row mb-3">
                <div class="col-md-6">
                    <p><strong>Nama Pelanggan:</strong> <%= struk.getCustomer().getNama() %></p>
                    <p><strong>Kontak Pelanggan:</strong> <%= struk.getCustomer().getKontak() %></p>
                </div>
            </div>
            <hr>
            <div class="row mb-3">
                <div class="col-md-6">
                    <p><strong>Jenis Layanan:</strong> <%= jenisLayanan %></p>
                    <p><strong>Kerusakan:</strong> <%= struk.getLayanan().getJenisKerusakan() %></p>
                    <p><strong>Biaya Servis:</strong> Rp <%= String.format(new Locale("id", "ID"), "%,d", struk.getLayanan().getBiayaService()) %></p>
                </div>
                <div class="col-md-6">
                    <p><strong>Teknisi:</strong> <%= struk.getTeknisi().getNamaTeknisi() %></p>
                    <p><strong>Tanggal Servis:</strong> <%= struk.getTanggalService().format(dateFormatter) %></p>
                    <p><strong>Estimasi Tanggal Selesai:</strong> <%= struk.getTanggalJadi().format(dateFormatter) %></p>
                </div>
            </div>
        </div>
        <div class="card-footer text-center">
            <button class="btn btn-secondary" onclick="window.print()">Cetak Struk</button>
            <a href="${pageContext.request.contextPath}/input_customer.jsp" class="btn btn-success ms-2">Input Servis Baru</a>
        </div>
    </div>
<% } else if (serviceSaveError == null) { // Hanya tampilkan jika tidak ada error spesifik dari servlet %>
    <div class="alert alert-warning" role="alert">
        Data struk tidak lengkap atau tidak ditemukan. Mohon ulangi proses input.
    </div>
    <a href="${pageContext.request.contextPath}/input_customer.jsp" class="btn btn-primary">Kembali ke Input Pelanggan</a>
<% } %>


<%@ include file="footer.jsp" %>