document.addEventListener('DOMContentLoaded', function () {
    const darkModeToggle = document.getElementById('darkModeToggle');
    const bodyElement = document.body;

    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(function(alert) {
        setTimeout(function() {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });
});

function konfirmasiHapus(tipeItem, idItem, servletUrl) {
    if (confirm(`Apakah Anda YAKIN ingin menghapus ${tipeItem} dengan ID ${idItem}? Tindakan ini tidak dapat dibatalkan.`)) {
        window.location.href = `${servletUrl}?tipe=${tipeItem}&id=${idItem}`;
    }
}