/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teknocaree.dao;

/**
 *
 * @author Azka
 */

import com.mycompany.teknocaree.model.Customer;
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;
import java.util.*;

public class CustomerDAO {
//    public static void insertCustomer(Customer customer) {
//        String sql = "INSERT INTO customer (nama, kontak) VALUES (?, ?)";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, customer.getNama());
//            ps.setString(2, customer.getKontak());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void addCustomer(Customer customer) {
        Connection conn = DBConnection.getConnection();

        try {
            String sql = "INSERT INTO customer (nama, kontak) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getNama());
            ps.setString(2, customer.getKontak());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
