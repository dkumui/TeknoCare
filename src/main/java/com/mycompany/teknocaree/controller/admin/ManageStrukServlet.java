package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.StrukDAO;
import com.mycompany.teknocaree.model.dto.StrukDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ManageStrukServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        String[] daftarStatusServis = {"Semua", "Baru Masuk", "Diagnosa", "Menunggu Sparepart", "Dikerjakan", "Selesai", "Diambil Pelanggan", "Dibatalkan"};
        request.setAttribute("daftarStatusServis", daftarStatusServis);

        if ("list".equals(action)) {
            String statusFilter = request.getParameter("statusFilter");
            if (statusFilter == null || statusFilter.trim().isEmpty()) {
                statusFilter = "Semua";
            }
            
            String searchQuery = request.getParameter("search");
            List<StrukDTO> listStruk;

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                 listStruk = StrukDAO.searchStruk(searchQuery);
            } else {
                 listStruk = StrukDAO.getStrukDTOByStatus(statusFilter);
            }
            
            request.setAttribute("listStruk", listStruk);
            request.setAttribute("selectedStatus", statusFilter);
            request.setAttribute("searchQuery", (searchQuery != null ? searchQuery : ""));
            request.getRequestDispatcher("/admin/manage_struk.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            deleteStruk(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("updateStatus".equals(action)) {
            try {
                int strukId = Integer.parseInt(request.getParameter("strukId"));
                String statusServis = request.getParameter("statusServis");
                String catatanTeknisi = request.getParameter("catatanTeknisi");

                if (statusServis == null || statusServis.trim().isEmpty()) {
                    session.setAttribute("errorMessage", "Status servis tidak boleh kosong.");
                } else {
                    boolean success = StrukDAO.updateStatusServis(strukId, statusServis, catatanTeknisi);
                    if (success) {
                        session.setAttribute("successMessage", "Status servis untuk struk ID " + strukId + " berhasil diperbarui.");
                    } else {
                        session.setAttribute("errorMessage", "Gagal memperbarui status servis.");
                    }
                }
            } catch (NumberFormatException e) {
                session.setAttribute("errorMessage", "ID Struk tidak valid.");
            }
        }
        String lastStatusFilter = request.getParameter("lastStatusFilter");
        String redirectUrl = request.getContextPath() + "/admin/manageStruk";
        if (lastStatusFilter != null && !lastStatusFilter.isEmpty() && !lastStatusFilter.equals("Semua")) {
            redirectUrl += "?statusFilter=" + lastStatusFilter;
        }
        response.sendRedirect(redirectUrl);
    }
    
    private void deleteStruk(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean success = StrukDAO.deleteStrukById(id);
            if (success) {
                session.setAttribute("successMessage", "Struk ID " + id + " berhasil dihapus.");
            } else {
                session.setAttribute("errorMessage", "Gagal menghapus struk ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID struk tidak valid untuk penghapusan.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageStruk");
    }
}