<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.teknocaree.model.LayananKatalog" %>
<%@ page import="com.mycompany.teknocaree.dao.LayananKatalogDAO" %>
<%@ page import="com.mycompany.teknocaree.model.Customer" %>
<%@ page import="com.mycompany.teknocaree.dao.CustomerDAO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- <%
    // Ambil daftar layanan katalog untuk dropdown
    List<LayananKatalog> layananList = LayananKatalogDAO.getAllLayananKatalog(true); // Ambil yang aktif saja
    pageContext.setAttribute("layananList", layananList);

    // Cek apakah ini alur untuk pelanggan lama atau baru
    String customerIdStr = request.getParameter("customerId");
    Customer customerForService = null;

    if (customerIdStr != null && !customerIdStr.trim().isEmpty()) {
        // --- ALUR PELANGGAN LAMA ---
        // Pelanggan sudah ada, ambil datanya dari DB berdasarkan ID
        try {
            int customerId = Integer.parseInt(customerIdStr);
            customerForService = CustomerDAO.getCustomerById(customerId);
        } catch (NumberFormatException e) {
            // Handle error jika ID tidak valid
            request.setAttribute("serviceSaveError", "ID Pelanggan tidak valid.");
        }
    } else {
        // --- ALUR PELANGGAN BARU ---
        // Ambil data pelanggan yang baru diinput dari session
        customerForService = (Customer) session.getAttribute("customerDataForService");
    }

    // Jika karena suatu hal data customer tidak ditemukan, beri pesan error
    if (customerForService == null) {
        response.sendRedirect("pilih_customer.jsp?error=Data pelanggan tidak ditemukan. Silakan ulangi proses.");
        return; // Hentikan pemrosesan halaman
    }
    
    // Kirim objek customer ke halaman agar bisa diakses dengan EL ${}
    pageContext.setAttribute("customerForService", customerForService);
%> -->

<jsp:include page="header.jsp" />

<div class="container mt-4">
    <div class="card shadow-sm mx-auto" style="max-width: 800px;">
        <div class="card-header bg-primary text-white">
            <h3>Input Detail Servis untuk: <strong>${customerForService.nama}</strong></h3>
            <p class="mb-0">Kontak: ${customerForService.kontak}</p>
        </div>
        <div class="card-body">
            <%-- Tampilkan pesan error jika ada --%>
            <c:if test="${not empty serviceSaveError}">
                <div class="alert alert-danger" role="alert">
                    ${serviceSaveError}
                </div>
            </c:if>

            <form action="ServiceController" method="post" id="serviceForm">

                <c:if test="${not empty param.customerId}">
                    <input type="hidden" name="customerId" value="${param.customerId}">
                </c:if>

                <div class="mb-3">
                    <label for="tipePerangkat" class="form-label">Tipe Perangkat</label>
                    <select class="form-select" id="tipePerangkat" name="tipePerangkat" required>
                        <option value="" disabled selected>-- Pilih Tipe --</option>
                        <option value="Laptop">Laptop</option>
                        <option value="PC">PC</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="idKatalog" class="form-label">Jenis Layanan</label>
                    <select class="form-select" id="idKatalog" name="idKatalog" required>
                        <option value="" disabled selected>-- Pilih Layanan --</option>
                        <c:forEach var="layanan" items="${layananList}">
                             <option value="${layanan.idKatalog}">${layanan.namaLayanan} (Rp ${layanan.biayaStandar})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="deskripsiDetail" class="form-label">Deskripsi / Keluhan Detail (Opsional)</label>
                    <textarea class="form-control" id="deskripsiDetail" name="deskripsiDetail" rows="3" placeholder="Contoh: Laptop sering mati sendiri saat panas, keyboard tombol 'A' tidak berfungsi."></textarea>
                </div>
                
                <hr>

                <div class="d-flex justify-content-between">
                     <a href="pilih_customer.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Kembali
                    </a>
                    <button type="submit" class="btn btn-success">
                        Buat Struk Servis <i class="fas fa-arrow-right"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />