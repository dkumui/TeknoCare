package com.mycompany.teknocaree.controller;

import com.mycompany.teknocaree.dao.*;
import com.mycompany.teknocaree.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class ServiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String customerIdStr = request.getParameter("customerId");
        Customer customer;
        int customerId;
        
        if (customerIdStr != null && !customerIdStr.trim().isEmpty()) {
            try {
                customerId = Integer.parseInt(customerIdStr);
                customer = CustomerDAO.getCustomerById(customerId);
                if (customer == null) {
                    throw new Exception("Pelanggan dengan ID " + customerId + " tidak ditemukan.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("serviceSaveError", "Terjadi kesalahan: " + e.getMessage());
                request.getRequestDispatcher("pilih_customer.jsp").forward(request, response);
                return;
            }
        } else {
            customer = (Customer) session.getAttribute("customerDataForService");

            if (customer == null) {
                request.setAttribute("serviceSaveError", "Sesi pelanggan telah berakhir. Silakan mulai dari awal.");
                request.getRequestDispatcher("pilih_customer.jsp").forward(request, response);
                return;
            }

            customerId = CustomerDAO.insertCustomer(customer);
            if (customerId == -1) {
                request.setAttribute("serviceSaveError", "FATAL: Gagal menyimpan data pelanggan baru ke database.");
                request.getRequestDispatcher("input_customer.jsp").forward(request, response);
                return;
            }
            customer.setId(customerId);
        }

        String tipePerangkat = request.getParameter("tipePerangkat");
        String idKatalogStr = request.getParameter("idKatalog");
        String deskripsiDetail = request.getParameter("deskripsiDetail");

        if (tipePerangkat == null || idKatalogStr == null || idKatalogStr.trim().isEmpty()) {
            request.setAttribute("serviceSaveError", "Tipe Perangkat dan Jenis Layanan harus dipilih.");
            List<LayananKatalog> layananList = LayananKatalogDAO.getAllLayananKatalog(true);
            request.setAttribute("layananList", layananList);
            request.setAttribute("customerForService", customer);
            request.getRequestDispatcher("input_service.jsp").forward(request, response);
            return;
        }

        try {
            int idKatalog = Integer.parseInt(idKatalogStr);
            LayananKatalog katalog = LayananKatalogDAO.getLayananKatalogById(idKatalog);

            String jenisKerusakanFinal = katalog.getNamaLayanan();
            if (deskripsiDetail != null && !deskripsiDetail.trim().isEmpty()) {
                jenisKerusakanFinal += " (" + deskripsiDetail.trim() + ")";
            }

            LayananService service = "Laptop".equalsIgnoreCase(tipePerangkat) ? new ServiceLaptop() : new ServicePC();
            service.setJenisKerusakan(jenisKerusakanFinal);
            service.setBiayaService(katalog.getBiayaStandar());

            int layananId = LayananServiceDAO.insertService(service, tipePerangkat, idKatalog);

            Teknisi teknisi = TeknisiDAO.getRandomTeknisi();
            LocalDate today = LocalDate.now();
            LocalDate tanggalJadi = today.plusDays(new Random().nextInt(3) + 2);

            Struk struk = new Struk(customer, service, teknisi, today, tanggalJadi);
            
            boolean strukSaved = false;
            if (layananId != -1 && teknisi != null) {
                 StrukDAO.insertStruk(struk, customerId, layananId, teknisi.getId());
                 strukSaved = true;
            }
           
            request.setAttribute("struk", struk);
            request.setAttribute("jenisLayanan", tipePerangkat);

            if (strukSaved) {
                request.setAttribute("serviceSaveSuccess", "Struk servis berhasil dibuat!");
                session.removeAttribute("customerDataForService");
            } else {
                String errorMessage = "Gagal membuat struk servis. ";
                if (layananId == -1) errorMessage += "Gagal menyimpan detail layanan. ";
                if (teknisi == null) errorMessage += "Tidak ada teknisi yang tersedia. ";
                request.setAttribute("serviceSaveError", errorMessage);
            }

            request.getRequestDispatcher("print_struk.jsp").forward(request, response);

        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("serviceSaveError", "Terjadi kesalahan sistem saat memproses data servis: " + e.getMessage());
            request.getRequestDispatcher("input_service.jsp").forward(request, response);
        }
    }
}