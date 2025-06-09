<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="id_ID"/>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin TeknoCare - ${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <style>
        body { display: flex; min-height: 100vh; flex-direction: column; }
        .wrapper { display: flex; flex-grow: 1; align-items: stretch; }
        #sidebar { min-width: 250px; max-width: 250px; background: #343a40; color: #fff; transition: all 0.3s; }
        #sidebar.active { margin-left: -250px; }
        #sidebar .sidebar-header { padding: 20px; background: #2c3136; }
        #sidebar ul.components { padding: 20px 0; border-bottom: 1px solid #47748b; }
        #sidebar ul p { color: #fff; padding: 10px; }
        #sidebar ul li a { padding: 10px; font-size: 1.1em; display: block; color: #adb5bd; text-decoration: none; }
        #sidebar ul li a:hover { color: #fff; background: #495057; }
        #sidebar ul li.active > a, a[aria-expanded="true"] { color: #fff; background: #007bff; }
        
        ul.CTAs { padding: 20px; }
        ul.CTAs a { text-align: center; padding: 10px; display: block; border-radius: 5px; margin-bottom: 5px; }

        #content { width: 100%; padding: 20px; min-height: 100vh; transition: all 0.3s; }

        #sidebarCollapse span {
            display: none;
        }
        @media (min-width: 768px) {
            #sidebarCollapse span {
                display: inline;
            }
        }
    </style>
</head>
<body class="light-mode">
    <div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>Admin TeknoCare</h3>
            </div>
            <ul class="list-unstyled components">
                <p>Menu Utama</p>
                <li class="${pageTitle == 'Dashboard' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/dashboard"><i class="bi bi-grid-fill me-2"></i>Dashboard</a>
                </li>
                <li class="${pageTitle == 'Manajemen Teknisi' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/manageTeknisi"><i class="bi bi-people-fill me-2"></i>Manajemen Teknisi</a>
                </li>
                <li class="${pageTitle == 'Manajemen Layanan' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/manageLayananKatalog"><i class="bi bi-card-list me-2"></i>Manajemen Layanan</a>
                </li>
                <li class="${pageTitle == 'Manajemen Servis' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/manageStruk"><i class="bi bi-tools me-2"></i>Manajemen Servis</a>
                </li>
                <li class="${pageTitle == 'Laporan Servis' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/laporanServis"><i class="bi bi-file-earmark-text-fill me-2"></i>Laporan Servis</a>
                </li>
            </ul>
            <ul class="list-unstyled CTAs">
                <li>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary text-white w-100">Halaman Pelanggan</a>
                </li>
            </ul>
        </nav>

        <div id="content">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="bi bi-list"></i>
                        <span>Toggle Menu</span>
                    </button>
                    <h4 class="ms-auto me-auto">${pageTitle}</h4>
                    <div>
                        <span class="navbar-text me-3">Selamat datang, Admin!</span>
                        <%-- <a href="${pageContext.request.contextPath}/logoutAdmin" class="btn btn-outline-danger">Logout</a> --%>
                    </div>
                </div>
            </nav>
            <hr>
            
            <%-- Pesan Sukses/Error dari Sesi (Flash Messages) --%>
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.successMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="successMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${sessionScope.errorMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="errorMessage" scope="session"/>
            </c:if>
             <c:if test="${not empty sessionScope.formError}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Perhatian!</strong> ${sessionScope.formError}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="formError" scope="session"/>
            </c:if>
            
            <main class="container-fluid mt-3">