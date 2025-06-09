package com.mycompany.teknocaree.dao;

import com.mycompany.teknocaree.model.Teknisi;
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeknisiDAO {

    public static Teknisi getRandomTeknisi() { 
        List<Teknisi> teknisiList = new ArrayList<>();
        String sql = "SELECT id, nama_teknisi, spesialisasi, kontak_teknisi, aktif FROM teknisi WHERE aktif = TRUE";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Teknisi t = new Teknisi();
                t.setId(rs.getInt("id"));
                t.setNamaTeknisi(rs.getString("nama_teknisi"));
                t.setSpesialisasi(rs.getString("spesialisasi"));
                t.setKontakTeknisi(rs.getString("kontak_teknisi"));
                t.setAktif(rs.getBoolean("aktif"));
                teknisiList.add(t);
            }

            if (!teknisiList.isEmpty()) {
                Random rand = new Random();
                return teknisiList.get(rand.nextInt(teknisiList.size()));
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    public static List<Teknisi> getAllTeknisi(boolean tampilkanTidakAktif) {
        List<Teknisi> listTeknisi = new ArrayList<>();
        String sql = "SELECT id, nama_teknisi, spesialisasi, kontak_teknisi, aktif FROM teknisi";
        if (!tampilkanTidakAktif) {
            sql += " WHERE aktif = TRUE";
        }
        sql += " ORDER BY id ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Teknisi teknisi = new Teknisi();
                teknisi.setId(rs.getInt("id"));
                teknisi.setNamaTeknisi(rs.getString("nama_teknisi"));
                teknisi.setSpesialisasi(rs.getString("spesialisasi"));
                teknisi.setKontakTeknisi(rs.getString("kontak_teknisi"));
                teknisi.setAktif(rs.getBoolean("aktif"));
                listTeknisi.add(teknisi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTeknisi;
    }

    public static Teknisi getTeknisiById(int id) {
        Teknisi teknisi = null;
        String sql = "SELECT id, nama_teknisi, spesialisasi, kontak_teknisi, aktif FROM teknisi WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teknisi = new Teknisi();
                    teknisi.setId(rs.getInt("id"));
                    teknisi.setNamaTeknisi(rs.getString("nama_teknisi"));
                    teknisi.setSpesialisasi(rs.getString("spesialisasi"));
                    teknisi.setKontakTeknisi(rs.getString("kontak_teknisi"));
                    teknisi.setAktif(rs.getBoolean("aktif"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teknisi;
    }

    public static boolean insertTeknisi(Teknisi teknisi) {
        String sql = "INSERT INTO teknisi (nama_teknisi, spesialisasi, kontak_teknisi, aktif) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teknisi.getNamaTeknisi());
            ps.setString(2, teknisi.getSpesialisasi());
            ps.setString(3, teknisi.getKontakTeknisi());
            ps.setBoolean(4, teknisi.isAktif());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateTeknisi(Teknisi teknisi) {
        String sql = "UPDATE teknisi SET nama_teknisi = ?, spesialisasi = ?, kontak_teknisi = ?, aktif = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teknisi.getNamaTeknisi());
            ps.setString(2, teknisi.getSpesialisasi());
            ps.setString(3, teknisi.getKontakTeknisi());
            ps.setBoolean(4, teknisi.isAktif());
            ps.setInt(5, teknisi.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteTeknisi(int id) {
        String sql = "DELETE FROM teknisi WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     public static long countTotalTeknisi() {
        String sql = "SELECT COUNT(*) FROM teknisi";
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
}