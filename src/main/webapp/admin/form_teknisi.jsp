<%@ include file="admin_header.jsp" %>

<h3>${pageTitle}</h3>
<hr>
<form action="${pageContext.request.contextPath}/admin/manageTeknisi" method="POST" class="needs-validation" novalidate>
    <input type="hidden" name="action" value="${formAction}">
    <c:if test="${formAction == 'update'}">
        <input type="hidden" name="id" value="${teknisi.id}">
    </c:if>

    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="namaTeknisi" class="form-label">Nama Teknisi <span class="text-danger">*</span></label>
            <input type="text" class="form-control" id="namaTeknisi" name="namaTeknisi" value="<c:out value='${teknisi.namaTeknisi}'/>" required>
            <div class="invalid-feedback">Nama teknisi wajib diisi.</div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="spesialisasi" class="form-label">Spesialisasi</label>
            <input type="text" class="form-control" id="spesialisasi" name="spesialisasi" value="<c:out value='${teknisi.spesialisasi}'/>">
             <div class="form-text">Contoh: Laptop, Software, Jaringan, PC Hardware.</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="kontakTeknisi" class="form-label">Kontak Teknisi</label>
            <input type="text" class="form-control" id="kontakTeknisi" name="kontakTeknisi" value="<c:out value='${teknisi.kontakTeknisi}'/>">
            <div class="form-text">Nomor telepon atau email.</div>
        </div>
         <div class="col-md-6 mb-3 align-self-center">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" role="switch" id="aktif" name="aktif" value="true" ${teknisi.aktif || formAction == 'insert' ? 'checked' : ''}>
                <label class="form-check-label" for="aktif">Aktif</label>
                 <div class="form-text">Jika tidak dicentang, teknisi tidak akan muncul di pilihan random untuk servis baru.</div>
            </div>
        </div>
    </div>
    
    <hr>
    <button type="submit" class="btn btn-primary">${formAction == 'insert' ? 'Simpan Teknisi' : 'Perbarui Teknisi'}</button>
    <a href="${pageContext.request.contextPath}/admin/manageTeknisi" class="btn btn-secondary">Batal</a>
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