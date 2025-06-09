package com.mycompany.teknocaree.controller.admin;

import com.mycompany.teknocaree.dao.StrukDAO;
import com.mycompany.teknocaree.model.dto.StrukDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CetakStrukServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            StrukDTO struk = StrukDAO.getStrukDTOById(id);

            if (struk != null) {
                request.setAttribute("struk", struk);
                // Menggunakan halaman print_struk.jsp yang ada di root webapp
                request.getRequestDispatcher("/print_struk.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "Struk dengan ID " + id + " tidak ditemukan untuk dicetak.");
                response.sendRedirect(request.getContextPath() + "/admin/manageStruk");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID Struk tidak valid untuk dicetak.");
            response.sendRedirect(request.getContextPath() + "/admin/manageStruk");
        }
    }
}