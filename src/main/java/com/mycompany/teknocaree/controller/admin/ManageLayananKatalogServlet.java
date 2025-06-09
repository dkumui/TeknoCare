package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.LayananKatalogDAO;
import com.mycompany.teknocaree.model.LayananKatalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ManageLayananKatalogServlet extends HttpServlet {

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
                    deleteLayanan(request, response);
                    break;
                default: // "list"
                    listLayanan(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Terjadi kesalahan: " + e.getMessage());
            listLayanan(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
         if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
            return;
        }

        try {
            switch (action) {
                case "insert":
                    insertLayanan(request, response);
                    break;
                case "update":
                    updateLayanan(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Gagal memproses permintaan: " + e.getMessage());
            listLayanan(request, response);
        }
    }

    private void listLayanan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramShowAll = request.getParameter("showAll");
        boolean tampilkanTidakAktif = "true".equalsIgnoreCase(paramShowAll);

        List<LayananKatalog> listLayanan = LayananKatalogDAO.getAllLayananKatalog(tampilkanTidakAktif);
        request.setAttribute("listLayananKatalog", listLayanan);
        request.setAttribute("tampilkanTidakAktif", tampilkanTidakAktif);
        request.getRequestDispatcher("/admin/manage_layanan_katalog.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("layananKatalog", new LayananKatalog());
        request.setAttribute("formAction", "insert");
        request.getRequestDispatcher("/admin/form_layanan_katalog.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LayananKatalog existingLayanan = LayananKatalogDAO.getLayananKatalogById(id);
         if (existingLayanan == null) {
            request.setAttribute("errorMessage", "Layanan Katalog dengan ID " + id + " tidak ditemukan.");
            listLayanan(request, response);
            return;
        }
        request.setAttribute("layananKatalog", existingLayanan);
        request.setAttribute("formAction", "update");
        request.getRequestDispatcher("/admin/form_layanan_katalog.jsp").forward(request, response);
    }

    private void insertLayanan(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String namaLayanan = request.getParameter("namaLayanan");
        String deskripsiLayanan = request.getParameter("deskripsiLayanan");
        String tipePerangkat = request.getParameter("tipePerangkat");
        int biayaStandar = 0;
        HttpSession session = request.getSession();

        if (namaLayanan == null || namaLayanan.trim().isEmpty() || tipePerangkat == null || tipePerangkat.trim().isEmpty()) {
            session.setAttribute("formError", "Nama Layanan dan Tipe Perangkat tidak boleh kosong.");
            
            LayananKatalog layananFromForm = new LayananKatalog();
            layananFromForm.setNamaLayanan(namaLayanan);
            layananFromForm.setDeskripsiLayanan(deskripsiLayanan);
            layananFromForm.setTipePerangkat(tipePerangkat);
            try { layananFromForm.setBiayaStandar(Integer.parseInt(request.getParameter("biayaStandar"))); } catch (NumberFormatException e) { /* biarkan 0 */ }
            layananFromForm.setAktif("on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif")));
            request.setAttribute("layananKatalog", layananFromForm);
            request.setAttribute("formAction", "insert");
            request.getRequestDispatcher("/admin/form_layanan_katalog.jsp").forward(request, response);
            return;
        }

        try {
            biayaStandar = Integer.parseInt(request.getParameter("biayaStandar"));
            if (biayaStandar < 0) biayaStandar = 0;
        } catch (NumberFormatException e) {
            biayaStandar = 0;
        }
        boolean aktif = "on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif"));

        LayananKatalog newLayanan = new LayananKatalog();
        newLayanan.setNamaLayanan(namaLayanan);
        newLayanan.setDeskripsiLayanan(deskripsiLayanan);
        newLayanan.setTipePerangkat(tipePerangkat);
        newLayanan.setBiayaStandar(biayaStandar);
        newLayanan.setAktif(aktif);

        boolean success = LayananKatalogDAO.insertLayananKatalog(newLayanan);

        if (success) {
            session.setAttribute("successMessage", "Layanan baru berhasil ditambahkan ke katalog!");
        } else {
            session.setAttribute("errorMessage", "Gagal menambahkan layanan baru.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
    }

    private void updateLayanan(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String namaLayanan = request.getParameter("namaLayanan");
        String deskripsiLayanan = request.getParameter("deskripsiLayanan");
        String tipePerangkat = request.getParameter("tipePerangkat");
        int biayaStandar = 0;
        HttpSession session = request.getSession();

        if (namaLayanan == null || namaLayanan.trim().isEmpty() || tipePerangkat == null || tipePerangkat.trim().isEmpty()) {
            session.setAttribute("formError", "Nama Layanan dan Tipe Perangkat tidak boleh kosong.");

            LayananKatalog layananFromForm = LayananKatalogDAO.getLayananKatalogById(id);
            if(layananFromForm != null) {
                layananFromForm.setNamaLayanan(namaLayanan);
                layananFromForm.setDeskripsiLayanan(deskripsiLayanan);
                layananFromForm.setTipePerangkat(tipePerangkat);
                try { layananFromForm.setBiayaStandar(Integer.parseInt(request.getParameter("biayaStandar"))); } catch (NumberFormatException e) { /* biarkan nilai lama */ }
                layananFromForm.setAktif("on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif")));
                request.setAttribute("layananKatalog", layananFromForm);
                request.setAttribute("formAction", "update");
                request.getRequestDispatcher("/admin/form_layanan_katalog.jsp").forward(request, response);
            } else {
                 response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
            }
            return;
        }
        
        try {
            biayaStandar = Integer.parseInt(request.getParameter("biayaStandar"));
             if (biayaStandar < 0) biayaStandar = 0;
        } catch (NumberFormatException e) {
            biayaStandar = 0;
        }
        boolean aktif = "on".equalsIgnoreCase(request.getParameter("aktif")) || "true".equalsIgnoreCase(request.getParameter("aktif"));


        LayananKatalog layanan = new LayananKatalog();
        layanan.setIdKatalog(id);
        layanan.setNamaLayanan(namaLayanan);
        layanan.setDeskripsiLayanan(deskripsiLayanan);
        layanan.setTipePerangkat(tipePerangkat);
        layanan.setBiayaStandar(biayaStandar);
        layanan.setAktif(aktif);

        boolean success = LayananKatalogDAO.updateLayananKatalog(layanan);

        if (success) {
            session.setAttribute("successMessage", "Data layanan berhasil diperbarui!");
        } else {
            session.setAttribute("errorMessage", "Gagal memperbarui data layanan.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
    }

    private void deleteLayanan(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();

        LayananKatalog layanan = LayananKatalogDAO.getLayananKatalogById(id);
        if(layanan != null) {
            layanan.setAktif(false); // Nonaktifkan
            boolean success = LayananKatalogDAO.updateLayananKatalog(layanan);
            if (success) {
                session.setAttribute("successMessage", "Layanan ID " + id + " berhasil dinonaktifkan dari katalog.");
            } else {
                session.setAttribute("errorMessage", "Gagal menonaktifkan layanan ID " + id + ".");
            }
        } else {
             session.setAttribute("errorMessage", "Layanan ID " + id + " tidak ditemukan.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageLayananKatalog");
    }
}