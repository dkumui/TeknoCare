// File: src/main/java/com/mycompany/teknocaree/controller/PilihCustomerServlet.java
package com.mycompany.teknocaree.controller;

import com.mycompany.teknocaree.dao.CustomerDAO;
import com.mycompany.teknocaree.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/pilihCustomer")
public class PilihCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            List<Customer> customerList = CustomerDAO.searchCustomersByName(searchQuery.trim());
            request.setAttribute("customerList", customerList);
        }
        request.getRequestDispatcher("pilih_customer.jsp").forward(request, response);
    }
}