<%@ include file="admin_header.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Daftar Layanan dalam Katalog</h3>
     <div>
        <a href="${pageContext.request.contextPath}/admin/manageLayananKatalog?action=new" class="btn btn-primary">
            <i class="bi bi-plus-circle-fill me-1"></i> Tambah Layanan Baru
        </a>
         <a href="${pageContext.request.contextPath}/admin/manageLayananKatalog?showAll=${!tampilkanTidakAktif}" class="btn btn-outline-secondary ms-2">
            <i class="bi ${tampilkanTidakAktif ? 'bi-eye-slash' : 'bi-eye'} me-1"></i> 
            ${tampilkanTidakAktif ? 'Sembunyikan Non-Aktif' : 'Tampilkan Semua'}
        </a>
    </div>
</div>

<c:choose>
    <c:when test="${not empty listLayananKatalog}">
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama Layanan</th>
                        <th>Tipe Perangkat</th>
                        <th class="text-end">Biaya Standar (Rp)</th>
                        <th>Deskripsi</th>
                        <th>Status</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="layanan" items="${listLayananKatalog}">
                        <tr>
                            <td><c:out value="${layanan.idKatalog}"/></td>
                            <td><c:out value="${layanan.namaLayanan}"/></td>
                            <td><span class="badge bg-secondary"><c:out value="${layanan.tipePerangkat}"/></span></td>
                            <td class="text-end"><fmt:formatNumber value="${layanan.biayaStandar}" type="number" minFractionDigits="0"/></td>
                            <td><c:out value="${empty layanan.deskripsiLayanan ? '-' : layanan.deskripsiLayanan}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${layanan.aktif}">
                                        <span class="badge bg-success">Aktif</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Tidak Aktif</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/manageLayananKatalog?action=edit&id=${layanan.idKatalog}" class="btn btn-sm btn-warning me-1" title="Edit">
                                     <i class="bi bi-pencil-square"></i>
                                </a>
                                <button type="button" class="btn btn-sm btn-danger" title="${layanan.aktif ? 'Nonaktifkan' : 'Aktifkan Kembali (Edit)'}"
                                        onclick="konfirmasiNonaktifAdmin('Layanan Katalog', '${layanan.idKatalog}', '${layanan.namaLayanan}', '${pageContext.request.contextPath}/admin/manageLayananKatalog?action=delete&id=${layanan.idKatalog}')">
                                   <i class="bi ${layanan.aktif ? 'bi-toggle-off' : 'bi-toggle-on'}"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-info" role="alert">
          Belum ada data layanan di katalog. Silakan tambahkan layanan baru.
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="admin_footer.jsp" %>