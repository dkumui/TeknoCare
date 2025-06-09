<%@ include file="admin_header.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Daftar Semua Servis</h3>
    <a href="${pageContext.request.contextPath}/input_customer.jsp" class="btn btn-success">
        <i class="bi bi-plus-circle-fill me-1"></i> Input Servis Baru (Pelanggan)
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-body">
        <form method="GET" action="${pageContext.request.contextPath}/admin/manageStruk" class="row g-3 align-items-center">
            <div class="col-md-5">
                <label for="search" class="visually-hidden">Cari</label>
                <input type="text" class="form-control" id="search" name="search" placeholder="Cari ID, Nama Pelanggan, Layanan, Kerusakan, Status..." value="<c:out value='${searchQuery}'/>">
            </div>
            <div class="col-md-4">
                <label for="statusFilter" class="visually-hidden">Filter Status</label>
                <select class="form-select" id="statusFilter" name="statusFilter">
                    <c:forEach var="statusOpt" items="${daftarStatusServis}">
                        <option value="${statusOpt}" ${statusOpt == selectedStatus ? 'selected' : ''}><c:out value="${statusOpt}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-auto">
                <button type="submit" class="btn btn-primary w-100">
                    <i class="bi bi-funnel-fill me-1"></i> Terapkan Filter/Cari
                </button>
            </div>
             <c:if test="${not empty searchQuery || (not empty selectedStatus && selectedStatus != 'Semua')}">
                 <div class="col-md-auto">
                    <a href="${pageContext.request.contextPath}/admin/manageStruk" class="btn btn-outline-danger w-100">
                        <i class="bi bi-x-circle me-1"></i> Reset
                    </a>
                </div>
            </c:if>
        </form>
    </div>
</div>


<c:choose>
    <c:when test="${not empty listStruk}">
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Pelanggan</th>
                        <th>Layanan</th>
                        <th>Kerusakan</th>
                        <th class="text-end">Biaya (Rp)</th>
                        <th>Teknisi</th>
                        <th>Tgl. Servis</th>
                        <th>Tgl. Jadi</th>
                        <th>Status</th>
                        <th>Catatan</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="struk" items="${listStruk}">
                        <tr>
                            <td><c:out value="${struk.id}"/></td>
                            <td>
                                <c:out value="${struk.namaCustomer}"/><br>
                                <small class="text-muted"><c:out value="${struk.kontakCustomer}"/></small>
                            </td>
                            <td><c:out value="${struk.jenisLayanan}"/></td>
                            <td><c:out value="${struk.jenisKerusakan}"/></td>
                            <td class="text-end"><fmt:formatNumber value="${struk.biayaService}" type="number" minFractionDigits="0"/></td>
                            <td><c:out value="${struk.namaTeknisi}"/></td>
                            <td><fmt:formatDate value="${struk.tanggalServiceAsDate}" pattern="dd MMM yyyy"/></td>
                            <td><fmt:formatDate value="${struk.tanggalJadiAsDate}" pattern="dd MMM yyyy"/></td>
                            <td>
                                <span class="badge 
                                    <c:choose>
                                        <c:when test="${struk.statusServis == 'Baru Masuk'}">bg-light text-dark border</c:when>
                                        <c:when test="${struk.statusServis == 'Diagnosa'}">bg-warning text-dark</c:when>
                                        <c:when test="${struk.statusServis == 'Menunggu Sparepart'}">bg-info text-dark</c:when>
                                        <c:when test="${struk.statusServis == 'Dikerjakan'}">bg-primary</c:when>
                                        <c:when test="${struk.statusServis == 'Selesai'}">bg-success</c:when>
                                        <c:when test="${struk.statusServis == 'Diambil Pelanggan'}">bg-dark</c:when>
                                        <c:when test="${struk.statusServis == 'Dibatalkan'}">bg-danger</c:when>
                                        <c:otherwise>bg-secondary</c:otherwise>
                                    </c:choose>">
                                    <c:out value="${empty struk.statusServis ? 'N/A' : struk.statusServis}"/>
                                </span>
                            </td>
                             <td>
                                <c:if test="${not empty struk.catatanTeknisi}">
                                    <i class="bi bi-chat-left-text-fill text-info" title="<c:out value="${struk.catatanTeknisi}"/>"></i>
                                </c:if>
                            </td>
                            <td>
                                <button type="button" class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#updateStatusModal-${struk.id}" title="Update Status">
                                    <i class="bi bi-pencil-fill"></i> Status
                                </button>
                                 <a href="${pageContext.request.contextPath}/admin/manageStruk?action=delete&id=${struk.id}"
                                   class="btn btn-sm btn-danger ms-1" title="Hapus Struk"
                                   onclick="event.preventDefault(); konfirmasiHapusAdmin('Struk Servis', '${struk.id}', 'ID ${struk.id} - ${struk.namaCustomer}', this.href);">
                                    <i class="bi bi-trash-fill"></i>
                                </a>


                                <div class="modal fade" id="updateStatusModal-${struk.id}" tabindex="-1" aria-labelledby="updateStatusModalLabel-${struk.id}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form action="${pageContext.request.contextPath}/admin/manageStruk" method="POST">
                                            <input type="hidden" name="action" value="updateStatus">
                                            <input type="hidden" name="strukId" value="${struk.id}">
                                            <input type="hidden" name="lastStatusFilter" value="${selectedStatus}">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="updateStatusModalLabel-${struk.id}">Update Status Servis - Struk ID: ${struk.id}</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <label for="statusServis-${struk.id}" class="form-label">Status Baru:</label>
                                                        <select class="form-select" id="statusServis-${struk.id}" name="statusServis" required>
                                                            <c:forEach var="statusOptModal" items="${daftarStatusServis}">
                                                                <c:if test="${statusOptModal != 'Semua'}"> <%-- Jangan tampilkan 'Semua' di pilihan update --%>
                                                                    <option value="${statusOptModal}" ${statusOptModal == struk.statusServis ? 'selected' : ''}>
                                                                        <c:out value="${statusOptModal}"/>
                                                                    </option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="catatanTeknisi-${struk.id}" class="form-label">Catatan Teknisi (Opsional):</label>
                                                        <textarea class="form-control" id="catatanTeknisi-${struk.id}" name="catatanTeknisi" rows="3"><c:out value="${struk.catatanTeknisi}"/></textarea>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Batal</button>
                                                    <button type="submit" class="btn btn-primary">Simpan Perubahan</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
         <div class="alert alert-info mt-3" role="alert">
          Tidak ada data servis ditemukan untuk filter "<c:out value='${selectedStatus}'/>"<c:if test="${not empty searchQuery}"> dan pencarian "<c:out value='${searchQuery}'/>"</c:if>.
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="admin_footer.jsp" %>