package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.StrukDAO;
import com.mycompany.teknocaree.model.dto.StrukDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LaporanServisServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String statusFilter = request.getParameter("statusFilter");
        if (statusFilter == null || statusFilter.isEmpty()) {
            statusFilter = "Semua";
        }

        List<StrukDTO> laporanServis = StrukDAO.getStrukDTOByStatus(statusFilter);
        Map<String, Long> strukByStatus = StrukDAO.getCountStrukByStatus();

        String[] daftarStatusServis = {"Semua", "Baru Masuk", "Diagnosa", "Menunggu Sparepart", "Dikerjakan", "Selesai", "Diambil", "Dibatalkan"};
        
        request.setAttribute("laporanServis", laporanServis);
        request.setAttribute("strukByStatus", strukByStatus);
        request.setAttribute("selectedStatus", statusFilter);
        request.setAttribute("daftarStatusServis", daftarStatusServis);

        request.getRequestDispatcher("/admin/laporan_servis.jsp").forward(request, response);
    }
}