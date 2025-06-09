package com.mycompany.teknocaree.controller;

import com.mycompany.teknocaree.dao.CustomerDAO;
import com.mycompany.teknocaree.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ManageCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "list"; 
        }

        try {
            switch (action) {
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                case "list":
                default:
                    listCustomers(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Terjadi kesalahan server: " + e.getMessage());
            listCustomers(request, response); 
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            updateCustomer(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/manageCustomer?action=list");
        }
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = CustomerDAO.getCustomerById(id);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/admin/form_customer.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "Customer dengan ID " + id + " tidak ditemukan.");
                response.sendRedirect(request.getContextPath() + "/manageCustomer?action=list");
            }
        } catch (NumberFormatException e) {
             request.getSession().setAttribute("errorMessage", "ID customer tidak valid.");
             response.sendRedirect(request.getContextPath() + "/manageCustomer?action=list");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nama = request.getParameter("nama");
            String kontak = request.getParameter("kontak");

            if (nama == null || nama.trim().isEmpty() || kontak == null || kontak.trim().isEmpty()) {
                 session.setAttribute("errorMessage", "Nama dan Kontak tidak boleh kosong.");
            } else {
                Customer customer = new Customer(id, nama, kontak);
                boolean success = CustomerDAO.updateCustomer(customer);
                if (success) {
                    session.setAttribute("successMessage", "Data pelanggan berhasil diperbarui.");
                } else {
                    session.setAttribute("errorMessage", "Gagal memperbarui data pelanggan.");
                }
            }
        } catch (NumberFormatException e) {
             session.setAttribute("errorMessage", "ID pelanggan tidak valid.");
        }
        response.sendRedirect(request.getContextPath() + "/manageCustomer?action=list");
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<Customer> customerList;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            customerList = CustomerDAO.searchCustomersByName(searchQuery.trim());
        } else {
            customerList = CustomerDAO.getAllCustomers();
        }

        request.setAttribute("customerList", customerList);
        request.setAttribute("searchQuery", (searchQuery != null ? searchQuery : ""));
        request.getRequestDispatcher("/list_customers.jsp").forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            boolean success = CustomerDAO.deleteCustomerById(id);
            if (success) {
                session.setAttribute("successMessage", "Pelanggan dengan ID " + id + " berhasil dihapus.");
            } else {
                session.setAttribute("errorMessage", "Gagal menghapus pelanggan ID " + id + ". Pastikan pelanggan tidak memiliki riwayat servis.");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID pelanggan tidak valid.");
        }
        
        response.sendRedirect(request.getContextPath() + "/manageCustomer?action=list");
    }
}