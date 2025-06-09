<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h3>Edit Struk Servis ID: ${struk.id}</h3>
            <div class="card">
                <div class="card-header">
                    Detail Servis untuk Pelanggan: <strong><c:out value="${struk.namaCustomer}"/></strong>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/manageStruk" method="POST">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${struk.id}">

                        <dl class="row">
                            <dt class="col-sm-4">Jenis Layanan</dt>
                            <dd class="col-sm-8"><c:out value="${struk.jenisLayanan}"/></dd>

                            <dt class="col-sm-4">Deskripsi Kerusakan</dt>
                            <dd class="col-sm-8"><c:out value="${struk.jenisKerusakan}"/></dd>

                            <dt class="col-sm-4">Teknisi</dt>
                            <dd class="col-sm-8"><c:out value="${struk.namaTeknisi}"/></dd>
                        </dl>
                        <hr>

                        <div class="mb-3">
                            <label for="statusServis" class="form-label"><strong>Status Servis</strong></label>
                            <select class="form-select" id="statusServis" name="statusServis">
                                <c:forEach items="${daftarStatusServis}" var="status">
                                    <option value="${status}" ${struk.statusServis == status ? 'selected' : ''}>
                                        <c:out value="${status}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="catatanTeknisi" class="form-label"><strong>Catatan Teknisi</strong></label>
                            <textarea class="form-control" id="catatanTeknisi" name="catatanTeknisi" rows="4"><c:out value="${struk.catatanTeknisi}"/></textarea>
                        </div>

                        <a href="${pageContext.request.contextPath}/admin/manageStruk" class="btn btn-secondary">Batal</a>
                        <button type="submit" class="btn btn-primary">Simpan Perubahan</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />