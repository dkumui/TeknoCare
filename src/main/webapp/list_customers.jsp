<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Daftar Pelanggan"/>
</jsp:include>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Daftar Pelanggan</h2>
        <a href="input_customer.jsp" class="btn btn-primary">
            <i class="fas fa-plus"></i> Tambah Pelanggan Baru
        </a>
    </div>

    <c:if test="${not empty sessionScope.successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${sessionScope.successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${sessionScope.errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% session.removeAttribute("errorMessage"); %>
    </c:if>

    <div class="card shadow-sm">
        <div class="card-body">
            <form action="ListCustomerServlet" method="get" class="mb-4">
                <div class="input-group">
                    <input type="text" name="search" class="form-control" placeholder="Cari berdasarkan nama..." value="${searchQuery}">
                    <button class="btn btn-outline-secondary" type="submit">Cari</button>
                </div>
            </form>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Nama</th>
                            <th scope="col">Kontak</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customer" items="${customerList}">
                            <tr>
                                <td><c:out value="${customer.nama}"/></td>
                                <td><c:out value="${customer.kontak}"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/manageCustomer?action=edit&id=${customer.id}" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <a href="${pageContext.request.contextPath}/manageCustomer?action=delete&id=${customer.id}" 
                                    class="btn btn-sm btn-danger" 
                                    onclick="return confirm('Yakin ingin menghapus pelanggan ${customer.nama}?');">Hapus</a>
                                </td>
                            </tr>
                        </c:forEach>
                        
                        <c:if test="${empty customerList}">
                            <tr>
                                <td colspan="4" class="text-center text-muted">Tidak ada data pelanggan yang ditemukan.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>