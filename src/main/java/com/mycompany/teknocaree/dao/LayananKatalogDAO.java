package com.mycompany.teknocaree.dao;

import com.mycompany.teknocaree.model.LayananKatalog; 
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LayananKatalogDAO {

    public static List<LayananKatalog> getAllLayananKatalog(boolean tampilkanTidakAktif) {
        List<LayananKatalog> listLayanan = new ArrayList<>();
        String sql = "SELECT id_katalog, nama_layanan, deskripsi_layanan, tipe_perangkat, biaya_standar, aktif FROM layanan_katalog";
        if (!tampilkanTidakAktif) {
            sql += " WHERE aktif = TRUE";
        }
        sql += " ORDER BY id_katalog ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LayananKatalog layanan = new LayananKatalog();
                layanan.setIdKatalog(rs.getInt("id_katalog"));
                layanan.setNamaLayanan(rs.getString("nama_layanan"));
                layanan.setDeskripsiLayanan(rs.getString("deskripsi_layanan"));
                layanan.setTipePerangkat(rs.getString("tipe_perangkat"));
                layanan.setBiayaStandar(rs.getInt("biaya_standar"));
                layanan.setAktif(rs.getBoolean("aktif"));
                listLayanan.add(layanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLayanan;
    }

    public static LayananKatalog getLayananKatalogById(int idKatalog) {
        LayananKatalog layanan = null;
        String sql = "SELECT id_katalog, nama_layanan, deskripsi_layanan, tipe_perangkat, biaya_standar, aktif FROM layanan_katalog WHERE id_katalog = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKatalog);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    layanan = new LayananKatalog();
                    layanan.setIdKatalog(rs.getInt("id_katalog"));
                    layanan.setNamaLayanan(rs.getString("nama_layanan"));
                    layanan.setDeskripsiLayanan(rs.getString("deskripsi_layanan"));
                    layanan.setTipePerangkat(rs.getString("tipe_perangkat"));
                    layanan.setBiayaStandar(rs.getInt("biaya_standar"));
                    layanan.setAktif(rs.getBoolean("aktif"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layanan;
    }

    public static boolean insertLayananKatalog(LayananKatalog layanan) {
        String sql = "INSERT INTO layanan_katalog (nama_layanan, deskripsi_layanan, tipe_perangkat, biaya_standar, aktif) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, layanan.getNamaLayanan());
            ps.setString(2, layanan.getDeskripsiLayanan());
            ps.setString(3, layanan.getTipePerangkat());
            ps.setInt(4, layanan.getBiayaStandar());
            ps.setBoolean(5, layanan.isAktif());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateLayananKatalog(LayananKatalog layanan) {
        String sql = "UPDATE layanan_katalog SET nama_layanan = ?, deskripsi_layanan = ?, tipe_perangkat = ?, biaya_standar = ?, aktif = ? WHERE id_katalog = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, layanan.getNamaLayanan());
            ps.setString(2, layanan.getDeskripsiLayanan());
            ps.setString(3, layanan.getTipePerangkat());
            ps.setInt(4, layanan.getBiayaStandar());
            ps.setBoolean(5, layanan.isAktif());
            ps.setInt(6, layanan.getIdKatalog());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteLayananKatalog(int idKatalog) {
        String sql = "DELETE FROM layanan_katalog WHERE id_katalog = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKatalog);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     public static long countTotalLayananKatalog() {
        String sql = "SELECT COUNT(*) FROM layanan_katalog";
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