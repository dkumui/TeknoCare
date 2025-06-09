<%@ include file="admin_header.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Daftar Teknisi</h3>
    <div>
        <a href="${pageContext.request.contextPath}/admin/manageTeknisi?action=new" class="btn btn-primary">
            <i class="bi bi-plus-circle-fill me-1"></i> Tambah Teknisi Baru
        </a>
         <a href="${pageContext.request.contextPath}/admin/manageTeknisi?showAll=${!tampilkanTidakAktif}" class="btn btn-outline-secondary ms-2">
            <i class="bi ${tampilkanTidakAktif ? 'bi-eye-slash' : 'bi-eye'} me-1"></i> 
            ${tampilkanTidakAktif ? 'Sembunyikan Non-Aktif' : 'Tampilkan Semua'}
        </a>
    </div>
</div>

<c:choose>
    <c:when test="${not empty listTeknisi}">
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama Teknisi</th>
                        <th>Spesialisasi</th>
                        <th>Kontak</th>
                        <th>Status</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="teknisi" items="${listTeknisi}">
                        <tr>
                            <td><c:out value="${teknisi.id}"/></td>
                            <td><c:out value="${teknisi.namaTeknisi}"/></td>
                            <td><c:out value="${empty teknisi.spesialisasi ? '-' : teknisi.spesialisasi}"/></td>
                            <td><c:out value="${empty teknisi.kontakTeknisi ? '-' : teknisi.kontakTeknisi}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${teknisi.aktif}">
                                        <span class="badge bg-success">Aktif</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Tidak Aktif</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/manageTeknisi?action=edit&id=${teknisi.id}" class="btn btn-sm btn-warning me-1" title="Edit">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <%-- Tombol delete akan menonaktifkan teknisi --%>
                                <button type="button" class="btn btn-sm btn-danger" title="${teknisi.aktif ? 'Nonaktifkan' : 'Aktifkan Kembali (Edit)'}"
                                        onclick="konfirmasiNonaktifAdmin('Teknisi', '${teknisi.id}', '${teknisi.namaTeknisi}', '${pageContext.request.contextPath}/admin/manageTeknisi?action=delete&id=${teknisi.id}')">
                                    <i class="bi ${teknisi.aktif ? 'bi-toggle-off' : 'bi-toggle-on'}"></i>
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
          Belum ada data teknisi. Silakan tambahkan teknisi baru.
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="admin_footer.jsp" %>