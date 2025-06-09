package com.mycompany.teknocaree.dao;

import com.mycompany.teknocaree.model.Customer;
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static int insertCustomer(Customer customer) {
        int generatedId = -1;
        String sql = "INSERT INTO customer (nama, kontak) VALUES (?, ?)"; 

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getNama());
            ps.setString(2, customer.getKontak());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        customer.setId(generatedId); 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return generatedId;
    }

    public static Customer getCustomerById(int id) {
        String sql = "SELECT id, nama, kontak FROM customer WHERE id = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setNama(rs.getString("nama"));
                    customer.setKontak(rs.getString("kontak"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, nama, kontak FROM customer ORDER BY id ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setNama(rs.getString("nama"));
                customer.setKontak(rs.getString("kontak"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<Customer> searchCustomersByName(String nameQuery) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, nama, kontak FROM customer WHERE LOWER(nama) LIKE LOWER(?) ORDER BY id ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + nameQuery + "%");
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setNama(rs.getString("nama"));
                    customer.setKontak(rs.getString("kontak"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public static long countTotalCustomers() {
        String sql = "SELECT COUNT(*) FROM customer";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET nama = ?, kontak = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getNama());
            ps.setString(2, customer.getKontak());
            ps.setInt(3, customer.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteCustomerById(int id) {
        String sqlStrukCheck = "SELECT COUNT(*) FROM struk WHERE customer_id = ?";
        String sqlDelete = "DELETE FROM customer WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement psCheck = conn.prepareStatement(sqlStrukCheck)) {
                psCheck.setInt(1, id);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.err.println("Tidak dapat menghapus customer ID " + id + " karena masih memiliki struk terkait.");
                        return false; 
                    }
                }
            }
            
            try (PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {
                psDelete.setInt(1, id);
                int affectedRows = psDelete.executeUpdate();
                return affectedRows > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}