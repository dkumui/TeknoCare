<%@ include file="admin_header.jsp" %>

<h3>${pageTitle}</h3>
<hr>
<form action="${pageContext.request.contextPath}/admin/manageLayananKatalog" method="POST" class="needs-validation" novalidate>
    <input type="hidden" name="action" value="${formAction}">
    <c:if test="${formAction == 'update'}">
        <input type="hidden" name="id" value="${layananKatalog.idKatalog}">
    </c:if>

    <div class="row">
        <div class="col-md-8 mb-3">
            <label for="namaLayanan" class="form-label">Nama Layanan <span class="text-danger">*</span></label>
            <input type="text" class="form-control" id="namaLayanan" name="namaLayanan" value="<c:out value='${layananKatalog.namaLayanan}'/>" required>
            <div class="invalid-feedback">Nama layanan wajib diisi.</div>
        </div>
        <div class="col-md-4 mb-3">
            <label for="tipePerangkat" class="form-label">Tipe Perangkat <span class="text-danger">*</span></label>
            <select class="form-select" id="tipePerangkat" name="tipePerangkat" required>
                <option value="" ${empty layananKatalog.tipePerangkat ? 'selected' : ''}>-- Pilih Tipe --</option>
                <option value="Laptop" ${layananKatalog.tipePerangkat == 'Laptop' ? 'selected' : ''}>Laptop</option>
                <option value="PC" ${layananKatalog.tipePerangkat == 'PC' ? 'selected' : ''}>PC</option>
            </select>
            <div class="invalid-feedback">Tipe perangkat wajib dipilih.</div>
        </div>
    </div>

    <div class="mb-3">
        <label for="deskripsiLayanan" class="form-label">Deskripsi Layanan</label>
        <textarea class="form-control" id="deskripsiLayanan" name="deskripsiLayanan" rows="3"><c:out value='${layananKatalog.deskripsiLayanan}'/></textarea>
    </div>
    
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="biayaStandar" class="form-label">Biaya Standar (Rp)</label>
            <input type="number" class="form-control" id="biayaStandar" name="biayaStandar" value="${layananKatalog.biayaStandar}" min="0">
            <div class="form-text">Biaya ini bisa di-override saat input servis.</div>
        </div>
        <div class="col-md-6 mb-3 align-self-center">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" role="switch" id="aktif" name="aktif" value="true" ${layananKatalog.aktif || formAction == 'insert' ? 'checked' : ''}>
                <label class="form-check-label" for="aktif">Aktif di Katalog</label>
            </div>
        </div>
    </div>

    <hr>
    <button type="submit" class="btn btn-primary">${formAction == 'insert' ? 'Simpan Layanan' : 'Perbarui Layanan'}</button>
    <a href="${pageContext.request.contextPath}/admin/manageLayananKatalog" class="btn btn-secondary">Batal</a>
</form>

<script>
(function () {
  'use strict'
  var forms = document.querySelectorAll('.needs-validation')
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
        form.classList.add('was-validated')
      }, false)
    })
})()
</script>

<%@ include file="admin_footer.jsp" %>