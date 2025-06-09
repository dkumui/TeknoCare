package com.mycompany.teknocaree.controller;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "list";
        }

        try {
            switch (action) {
                case "delete":
                    deleteStruk(request, response);
                    break;
                case "list":
                default:
                    listStruk(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Terjadi kesalahan server: " + e.getMessage());
            listStruk(request, response);
        }
    }

    private void listStruk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<StrukDTO> strukList;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            strukList = StrukDAO.searchStruk(searchQuery.trim());
        } else {
            strukList = StrukDAO.getAllStrukDTO();
        }

        request.setAttribute("strukList", strukList);
        request.setAttribute("searchQuery", (searchQuery != null ? searchQuery : ""));
        request.getRequestDispatcher("/list_struk.jsp").forward(request, response);
    }

    private void deleteStruk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            boolean success = StrukDAO.deleteStrukById(id);
            if (success) {
                session.setAttribute("successMessage", "Struk dengan ID " + id + " berhasil dihapus.");
            } else {
                session.setAttribute("errorMessage", "Gagal menghapus struk ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID struk tidak valid.");
        }
        
        response.sendRedirect(request.getContextPath() + "/manageStruk?action=list");
    }
}