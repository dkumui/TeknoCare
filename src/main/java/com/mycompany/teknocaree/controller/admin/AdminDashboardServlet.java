package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.CustomerDAO;
import com.mycompany.teknocaree.dao.LayananKatalogDAO;
import com.mycompany.teknocaree.dao.StrukDAO;
import com.mycompany.teknocaree.dao.TeknisiDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long totalPelanggan = CustomerDAO.countTotalCustomers();
        long totalTeknisi = TeknisiDAO.countTotalTeknisi();
        long totalLayananKatalog = LayananKatalogDAO.countTotalLayananKatalog();
        long totalStruk = StrukDAO.countTotalStruk();
        Map<String, Long> strukByStatus = StrukDAO.getCountStrukByStatus();
        double totalPendapatan = StrukDAO.getTotalPendapatanSelesai();

        request.setAttribute("totalPelanggan", totalPelanggan);
        request.setAttribute("totalTeknisi", totalTeknisi);
        request.setAttribute("totalLayananKatalog", totalLayananKatalog);
        request.setAttribute("totalStruk", totalStruk);
        request.setAttribute("strukByStatus", strukByStatus);
        request.setAttribute("totalPendapatan", totalPendapatan);
        
        String[] daftarStatusServis = {"Baru Masuk", "Diagnosa", "Menunggu Sparepart", "Dikerjakan", "Selesai", "Diambil Pelanggan", "Dibatalkan"};
        request.setAttribute("daftarStatusServis", daftarStatusServis);


        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}