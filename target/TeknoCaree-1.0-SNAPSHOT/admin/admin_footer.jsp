</main>
        </div> </div> <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const sidebarCollapse = document.getElementById('sidebarCollapse');
            const sidebar = document.getElementById('sidebar');
            const darkModeToggleSidebar = document.getElementById('darkModeToggleSidebar');
            const bodyElement = document.body;

            if(sidebarCollapse) {
                sidebarCollapse.addEventListener('click', function () {
                    if(sidebar) sidebar.classList.toggle('active');
                });
            }

            const currentTheme = localStorage.getItem('adminTheme') || 'dark';
            applyTheme(currentTheme);

            if(darkModeToggleSidebar){
                 darkModeToggleSidebar.addEventListener('click', function () {
                    let themeToSet = bodyElement.classList.contains('dark-mode') ? 'light' : 'dark';
                    applyTheme(themeToSet);
                    localStorage.setItem('adminTheme', themeToSet);
                });
            }

            const alerts = document.querySelectorAll('.alert-dismissible');
            alerts.forEach(function(alert) {
                setTimeout(function() {
                    const bsAlert = new bootstrap.Alert(alert);
                    if (bsAlert && typeof bsAlert.close === 'function') {
                        try {
                             bsAlert.close();
                        } catch (e) {
                            console.warn("Gagal menutup alert: ", e);
                        }
                    } else if(alert.parentNode) {
                        alert.parentNode.removeChild(alert);
                    }
                }, 7000);
            });
        });
        
        function konfirmasiHapusAdmin(tipeItem, idItem, namaItem, actionUrl) {
            if (confirm(`Apakah Anda yakin ingin menghapus ${tipeItem} "${namaItem}" (ID: ${idItem})? Tindakan ini mungkin tidak dapat dibatalkan.`)) {
                window.location.href = actionUrl;
            }
        }
        
         function konfirmasiNonaktifAdmin(tipeItem, idItem, namaItem, actionUrl) {
            if (confirm(`Apakah Anda yakin ingin menonaktifkan ${tipeItem} "${namaItem}" (ID: ${idItem})?`)) {
                window.location.href = actionUrl;
            }
        }
    </script>
</body>
</html>