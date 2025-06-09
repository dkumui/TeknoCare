<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container mt-4">
    <div class="row">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-header">
                    <h3><i class="fas fa-search"></i> Cari Pelanggan Lama</h3>
                </div>
                <div class="card-body">
                    <form action="pilihCustomer" method="get">
                        <div class="input-group mb-3">
                            <input type="text" name="search" class="form-control" placeholder="Ketik nama pelanggan..." value="${param.search}">
                            <button class="btn btn-primary" type="submit">Cari</button>
                        </div>
                    </form>
                    
                    <c:if test="${not empty customerList}">
                        <h5 class="mt-4">Hasil Pencarian:</h5>
                        <div class="list-group">
                            <c:forEach var="customer" items="${customerList}">
                                <a href="input_service.jsp?customerId=${customer.id}" class="list-group-item list-group-item-action">
                                    <strong>${customer.nama}</strong> - ${customer.kontak}
                                </a>
                            </c:forEach>
                        </div>
                    </c:if>
                    
                    <c:if test="${empty customerList and not empty param.search}">
                        <p class="text-muted mt-3">Pelanggan tidak ditemukan.</p>
                    </c:if>
                </div>
            </div>
        </div>
        
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-header">
                    <h4><i class="fas fa-user-plus"></i> Pelanggan Baru</h4>
                </div>
                <div class="card-body text-center">
                    <p>Jika pelanggan belum terdaftar, klik tombol di bawah ini.</p>
                    <a href="input_customer.jsp" class="btn btn-success btn-lg">Input Pelanggan Baru</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />