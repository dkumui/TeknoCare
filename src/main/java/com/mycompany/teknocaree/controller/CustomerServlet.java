package com.mycompany.teknocaree.controller;

import com.mycompany.teknocaree.dao.LayananKatalogDAO;
import com.mycompany.teknocaree.model.Customer;
import com.mycompany.teknocaree.model.LayananKatalog;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nama = request.getParameter("nama");
        String kontak = request.getParameter("kontak");

        if (nama == null || nama.trim().isEmpty() || kontak == null || kontak.trim().isEmpty()) {
            request.setAttribute("customerSaveError", "Nama dan Kontak tidak boleh kosong.");
            request.getRequestDispatcher("input_customer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer();
        customer.setNama(nama);
        customer.setKontak(kontak);

        HttpSession session = request.getSession();
        session.setAttribute("customerDataForService", customer);

        try {
            List<LayananKatalog> layananList = LayananKatalogDAO.getAllLayananKatalog(false);
            request.setAttribute("layananList", layananList);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("serviceSaveError", "Gagal memuat data layanan. Silakan coba lagi.");
            request.getRequestDispatcher("input_customer.jsp").forward(request, response);
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("input_service.jsp");
        dispatcher.forward(request, response);
    }
}