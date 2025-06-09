<%@ include file="admin_header.jsp" %>

<div class="row">
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Total Pelanggan</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${totalPelanggan}"/></div>
                    </div>
                    <div class="col-auto">
                        <i class="bi bi-people-fill fs-2 text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Total Teknisi Aktif</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${totalTeknisi}"/></div>
                    </div>
                    <div class="col-auto">
                        <i class="bi bi-person-workspace fs-2 text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Layanan di Katalog</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${totalLayananKatalog}"/></div>
                    </div>
                    <div class="col-auto">
                        <i class="bi bi-card-checklist fs-2 text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Total Servis Tercatat</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${totalStruk}"/></div>
                    </div>
                    <div class="col-auto">
                        <i class="bi bi-receipt-cutoff fs-2 text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-6 mb-4">
        <div class="card shadow">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Ringkasan Servis per Status</h6>
            </div>
            <div class="card-body">
                <c:if test="${empty strukByStatus}">
                    <p>Belum ada data servis.</p>
                </c:if>
                <c:forEach var="entry" items="${strukByStatus}">
                    <h4 class="small font-weight-bold"><c:out value="${entry.key}"/> <span class="float-end"><c:out value="${entry.value}"/></span></h4>
                    <div class="progress mb-4">
                        <div class="progress-bar 
                            <c:choose>
                                <c:when test="${entry.key == 'Baru Masuk'}">bg-info</c:when>
                                <c:when test="${entry.key == 'Diagnosa'}">bg-warning</c:when>
                                <c:when test="${entry.key == 'Dikerjakan'}">bg-primary</c:when>
                                <c:when test="${entry.key == 'Selesai'}">bg-success</c:when>
                                <c:when test="${entry.key == 'Diambil Pelanggan'}">bg-dark</c:when>
                                <c:when test="${entry.key == 'Dibatalkan'}">bg-danger</c:when>
                                <c:otherwise>bg-secondary</c:otherwise>
                            </c:choose>" 
                             role="progressbar" style="width: <c:if test='${totalStruk > 0}'><fmt:formatNumber value='${(entry.value / totalStruk) * 100}' maxFractionDigits='0'/></c:if><c:if test='${totalStruk == 0}'>0</c:if>%" 
                             aria-valuenow="<c:if test='${totalStruk > 0}'><fmt:formatNumber value='${(entry.value / totalStruk) * 100}' maxFractionDigits='0'/></c:if><c:if test='${totalStruk == 0}'>0</c:if>" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="col-lg-6 mb-4">
         <div class="card shadow h-100">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Total Estimasi Pendapatan (Dari Servis Selesai/Diambil)</h6>
            </div>
            <div class="card-body text-center">
                 <h2 class="display-4 fw-bold">Rp <fmt:formatNumber value="${totalPendapatan}" type="number" minFractionDigits="0" maxFractionDigits="0"/></h2>
                 <p class="text-muted">Berdasarkan servis yang telah selesai atau diambil pelanggan.</p>
                 <a href="${pageContext.request.contextPath}/admin/laporanServis?statusFilter=Selesai" class="btn btn-sm btn-outline-primary">Lihat Detail Laporan Selesai</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="admin_footer.jsp" %>