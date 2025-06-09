package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.TeknisiDAO;
import com.mycompany.teknocaree.model.Teknisi;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ManageTeknisiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteTeknisi(request, response);
                    break;
                default:
                    listTeknisi(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Terjadi kesalahan: " + e.getMessage());
            listTeknisi(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi");
            return;
        }

        try {
            switch (action) {
                case "insert":
                    insertTeknisi(request, response);
                    break;
                case "update":
                    updateTeknisi(request, response);
                    break;
                default:
                     response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Gagal memproses permintaan: " + e.getMessage());
            listTeknisi(request, response);
        }
    }

    private void listTeknisi(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramShowAll = request.getParameter("showAll");
        boolean tampilkanTidakAktif = "true".equalsIgnoreCase(paramShowAll);
        
        List<Teknisi> listTeknisi = TeknisiDAO.getAllTeknisi(tampilkanTidakAktif);
        request.setAttribute("listTeknisi", listTeknisi);
        request.setAttribute("tampilkanTidakAktif", tampilkanTidakAktif);
        request.getRequestDispatcher("/admin/manage_teknisi.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("teknisi", new Teknisi());
        request.setAttribute("formAction", "insert");
        request.getRequestDispatcher("/admin/form_teknisi.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Teknisi existingTeknisi = TeknisiDAO.getTeknisiById(id);
        if (existingTeknisi == null) {
            request.setAttribute("errorMessage", "Teknisi dengan ID " + id + " tidak ditemukan.");
            listTeknisi(request, response);
            return;
        }
        request.setAttribute("teknisi", existingTeknisi);
        request.setAttribute("formAction", "update");
        request.getRequestDispatcher("/admin/form_teknisi.jsp").forward(request, response);
    }

    private void insertTeknisi(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String namaTeknisi = request.getParameter("namaTeknisi");
        String spesialisasi = request.getParameter("spesialisasi");
        String kontakTeknisi = request.getParameter("kontakTeknisi");
        boolean aktif = "on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif"));
        HttpSession session = request.getSession();

        if (namaTeknisi == null || namaTeknisi.trim().isEmpty()) {
            session.setAttribute("formError", "Nama teknisi tidak boleh kosong.");
            response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi?action=new");
            return;
        }

        Teknisi newTeknisi = new Teknisi(0, namaTeknisi, spesialisasi, kontakTeknisi, aktif);
        boolean success = TeknisiDAO.insertTeknisi(newTeknisi);

        if (success) {
            session.setAttribute("successMessage", "Teknisi baru berhasil ditambahkan!");
        } else {
            session.setAttribute("errorMessage", "Gagal menambahkan teknisi baru.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi");
    }

    private void updateTeknisi(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String namaTeknisi = request.getParameter("namaTeknisi");
        String spesialisasi = request.getParameter("spesialisasi");
        String kontakTeknisi = request.getParameter("kontakTeknisi");
        boolean aktif = "on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif"));
        HttpSession session = request.getSession();
        
        if (namaTeknisi == null || namaTeknisi.trim().isEmpty()) {
             session.setAttribute("formError", "Nama teknisi tidak boleh kosong.");
             response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi?action=edit&id=" + id);
             return;
        }

        Teknisi teknisi = new Teknisi(id, namaTeknisi, spesialisasi, kontakTeknisi, aktif);
        boolean success = TeknisiDAO.updateTeknisi(teknisi);

        if (success) {
            session.setAttribute("successMessage", "Data teknisi berhasil diperbarui!");
        } else {
            session.setAttribute("errorMessage", "Gagal memperbarui data teknisi.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi");
    }

    private void deleteTeknisi(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        
        // if (StrukDAO.isTeknisiInUse(id)) {
        //     session.setAttribute("errorMessage", "Teknisi tidak dapat dihapus karena masih memiliki data servis terkait. Nonaktifkan saja.");
        // } else {
        //     boolean success = TeknisiDAO.deleteTeknisi(id);
        //     if (success) {
        //         session.setAttribute("successMessage", "Teknisi berhasil dihapus!");
        //     } else {
        //         session.setAttribute("errorMessage", "Gagal menghapus teknisi.");
        //     }
        // }

        Teknisi teknisi = TeknisiDAO.getTeknisiById(id);
        if (teknisi != null) {
            teknisi.setAktif(false);
            boolean success = TeknisiDAO.updateTeknisi(teknisi);
             if (success) {
                session.setAttribute("successMessage", "Teknisi ID " + id + " berhasil dinonaktifkan.");
            } else {
                session.setAttribute("errorMessage", "Gagal menonaktifkan teknisi ID " + id + ".");
            }
        } else {
             session.setAttribute("errorMessage", "Teknisi ID " + id + " tidak ditemukan.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageTeknisi");
    }
}