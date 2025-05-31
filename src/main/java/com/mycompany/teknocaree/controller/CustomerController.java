/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teknocaree.controller;

/**
 *
 * @author Azka
 */

import com.mycompany.teknocaree.dao.CustomerDAO;
import com.mycompany.teknocaree.model.Customer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nama = request.getParameter("nama");
        String kontak = request.getParameter("kontak");
        
        System.out.println("Nama: " + nama);
        System.out.println("Kontak: " + kontak);

        Customer customer = new Customer();
        customer.setNama(nama);
        customer.setKontak(kontak);

        CustomerDAO.addCustomer(customer);
        response.sendRedirect("input_customer.jsp");
    }
}