<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Daftar Struk Servis"/>
</jsp:include>

<div class="container mt-4">
     <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Daftar Struk Servis</h2>
        <a href="pilih_customer.jsp" class="btn btn-primary">
            <i class="fas fa-plus"></i> Input Servis Baru
        </a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <form action="ListStrukServlet" method="get" class="mb-4">
                 <div class="input-group">
                    <input type="text" name="search" class="form-control" placeholder="Cari ID Struk, Nama Pelanggan, Jenis Layanan/Kerusakan..." value="${searchQuery}">
                    <button class="btn btn-outline-secondary" type="submit">Cari</button>
                </div>
            </form>
            
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Nama Pelanggan</th>
                            <th>Layanan</th>
                            <th>Kerusakan</th>
                            <th>Biaya (Rp)</th>
                            <th>Teknisi</th>
                            <th>Tgl Servis</th>
                            <th>Tgl Selesai</th>
                            <th>Status</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="struk" items="${strukList}">
                            <tr>
                                <td><c:out value="${struk.namaCustomer}"/></td>
                                <td><c:out value="${struk.jenisLayanan}"/></td>
                                <td><c:out value="${struk.jenisKerusakan}"/></td>
                                <td><fmt:formatNumber value="${struk.biayaService}" type="number" minFractionDigits="0"/></td>
                                <td><c:out value="${struk.namaTeknisi}"/></td>
                                <td><fmt:formatDate value="${struk.tanggalServiceAsDate}" pattern="dd-MM-yyyy"/></td>
                                <td><fmt:formatDate value="${struk.tanggalJadiAsDate}" pattern="dd-MM-yyyy"/></td>
                                <td><span class="badge bg-info text-dark">${struk.statusServis}</span></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/manageStruk?action=delete&id=${struk.id}" 
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('Yakin ingin menghapus struk ID ${struk.id}?');">Hapus</a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty strukList}">
                             <tr>
                                <td colspan="10" class="text-center text-muted">Tidak ada data struk yang ditemukan.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>