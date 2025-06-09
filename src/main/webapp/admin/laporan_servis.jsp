<%@ include file="admin_header.jsp" %>

<h3>Laporan Servis TeknoCare</h3>
<hr>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Filter Laporan</h6>
    </div>
    <div class="card-body">
        <form method="GET" action="${pageContext.request.contextPath}/admin/laporanServis" class="row g-3 align-items-center">
            <div class="col-md-4">
                <label for="statusFilter" class="form-label">Filter berdasarkan Status Servis:</label>
                <select class="form-select" id="statusFilter" name="statusFilter" onchange="this.form.submit()">
                     <c:forEach var="statusOpt" items="${daftarStatusServis}">
                        <option value="${statusOpt}" ${statusOpt == selectedStatus ? 'selected' : ''}><c:out value="${statusOpt}"/></option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Detail Servis (<c:out value="${selectedStatus}"/>)</h6>
        <%-- Tombol Export bisa ditambahkan di sini nanti --%>
    </div>
    <div class="card-body">
        <c:choose>
            <c:when test="${not empty laporanServis}">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover" id="laporanTable">
                        <thead class="table-light">
                            <tr>
                                <th>ID Struk</th>
                                <th>Pelanggan</th>
                                <th>Layanan</th>
                                <th>Kerusakan</th>
                                <th class="text-end">Biaya (Rp)</th>
                                <th>Teknisi</th>
                                <th>Tgl. Servis</th>
                                <th>Estimasi Jadi</th>
                                <th>Status Aktual</th>
                                 <th>Catatan</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="servis" items="${laporanServis}">
                                <tr>
                                    <td><c:out value="${servis.id}"/></td>
                                    <td><c:out value="${servis.namaCustomer}"/></td>
                                    <td><c:out value="${servis.jenisLayanan}"/></td>
                                    <td><c:out value="${servis.jenisKerusakan}"/></td>
                                    <td class="text-end"><fmt:formatNumber value="${servis.biayaService}" type="number" minFractionDigits="0"/></td>
                                    <td><c:out value="${servis.namaTeknisi}"/></td>
                                    <td><fmt:formatDate value="${servis.tanggalServiceAsDate}" pattern="dd/MM/yy"/></td>
                                    <td><fmt:formatDate value="${servis.tanggalJadiAsDate}" pattern="dd/MM/yy"/></td>
                                    <td><c:out value="${servis.statusServis}"/></td>
                                    <td><c:out value="${servis.catatanTeknisi}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                         <tfoot class="table-light">
                            <tr>
                                <td colspan="4" class="text-end fw-bold">Total Servis Ditampilkan:</td>
                                <td class="text-end fw-bold">${laporanServis.size()}</td>
                                <td colspan="5"></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">Tidak ada data servis ditemukan untuk status "<c:out value="${selectedStatus}"/>".</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="row mt-4">
    <div class="col-md-6">
        <div class="card shadow">
             <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Ringkasan Jumlah Servis per Status (Semua Data)</h6>
            </div>
            <div class="card-body">
                 <ul class="list-group">
                    <c:forEach var="entry" items="${strukByStatus}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <c:out value="${entry.key}"/>
                            <span class="badge bg-primary rounded-pill"><c:out value="${entry.value}"/></span>
                        </li>
                    </c:forEach>
                 </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="admin_footer.jsp" %>