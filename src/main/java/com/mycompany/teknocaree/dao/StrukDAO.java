// File: TeknoCare/src/main/java/com/mycompany/teknocaree/dao/StrukDAO.java
package com.mycompany.teknocaree.dao;

import com.mycompany.teknocaree.model.Struk;
import com.mycompany.teknocaree.model.dto.StrukDTO;
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StrukDAO {
    public static void insertStruk(Struk struk, int customerId, int layananId, int teknisiId) {
        String sql = "INSERT INTO struk (customer_id, layanan_id, teknisi_id, tanggal_service, tanggal_jadi, status_servis) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setInt(2, layananId);
            ps.setInt(3, teknisiId);
            ps.setDate(4, Date.valueOf(struk.getTanggalService()));
            ps.setDate(5, Date.valueOf(struk.getTanggalJadi()));
            ps.setString(6, "Baru Masuk");

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static StrukDTO mapResultSetToStrukDTO(ResultSet rs) throws SQLException {
        StrukDTO dto = new StrukDTO();
        dto.setId(rs.getInt("struk_id"));
        dto.setNamaCustomer(rs.getString("customer_nama"));
        dto.setKontakCustomer(rs.getString("customer_kontak"));
        dto.setJenisLayanan(rs.getString("layanan_jenis")); 
        dto.setJenisKerusakan(rs.getString("layanan_kerusakan")); 
        dto.setBiayaService(rs.getInt("layanan_biaya")); 
        dto.setNamaTeknisi(rs.getString("teknisi_nama")); 
        dto.setTanggalService(rs.getDate("struk_tgl_service").toLocalDate());
        dto.setTanggalJadi(rs.getDate("struk_tgl_jadi").toLocalDate());
        dto.setStatusServis(rs.getString("struk_status"));
        dto.setCatatanTeknisi(rs.getString("struk_catatan"));
        return dto;
    }

    public static List<StrukDTO> getStrukDTOByStatus(String statusFilter) {
        List<StrukDTO> strukList = new ArrayList<>();
         String sql = "SELECT s.id as struk_id, c.nama as customer_nama, c.kontak as customer_kontak, " +
                     "ls.jenis_layanan as layanan_jenis, ls.jenis_kerusakan as layanan_kerusakan, ls.biaya_service as layanan_biaya, " +
                     "t.nama_teknisi as teknisi_nama, s.tanggal_service as struk_tgl_service, s.tanggal_jadi as struk_tgl_jadi, " +
                     "s.status_servis as struk_status, s.catatan_teknisi as struk_catatan " +
                     "FROM struk s " +
                     "JOIN customer c ON s.customer_id = c.id " +
                     "JOIN layanan_service ls ON s.layanan_id = ls.id " +
                     "JOIN teknisi t ON s.teknisi_id = t.id ";

        if (statusFilter != null && !statusFilter.trim().isEmpty() && !statusFilter.equalsIgnoreCase("Semua")) {
            sql += " WHERE s.status_servis = ? ";
        }
        sql += " ORDER BY s.id ASC";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (statusFilter != null && !statusFilter.trim().isEmpty() && !statusFilter.equalsIgnoreCase("Semua")) {
                ps.setString(1, statusFilter);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                strukList.add(mapResultSetToStrukDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strukList;
    }
    
    public static List<StrukDTO> getAllStrukDTO() {
        return getStrukDTOByStatus("Semua"); 
    }
    
    public static List<StrukDTO> searchStruk(String query) {
        List<StrukDTO> strukList = new ArrayList<>();
        String sql = "SELECT s.id as struk_id, c.nama as customer_nama, c.kontak as customer_kontak, " +
                     "ls.jenis_layanan as layanan_jenis, ls.jenis_kerusakan as layanan_kerusakan, ls.biaya_service as layanan_biaya, " +
                     "t.nama_teknisi as teknisi_nama, s.tanggal_service as struk_tgl_service, s.tanggal_jadi as struk_tgl_jadi, " +
                     "s.status_servis as struk_status, s.catatan_teknisi as struk_catatan " +
                     "FROM struk s " +
                     "JOIN customer c ON s.customer_id = c.id " +
                     "JOIN layanan_service ls ON s.layanan_id = ls.id " +
                     "JOIN teknisi t ON s.teknisi_id = t.id " +
                     "WHERE CAST(s.id AS CHAR) LIKE ? OR LOWER(c.nama) LIKE LOWER(?) OR LOWER(ls.jenis_layanan) LIKE LOWER(?) OR LOWER(ls.jenis_kerusakan) LIKE LOWER(?) OR LOWER(s.status_servis) LIKE LOWER(?) " +
                     "ORDER BY s.id ASC";
        
        String searchQuery = "%" + query.toLowerCase() + "%";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ps.setString(5, searchQuery); 
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                strukList.add(mapResultSetToStrukDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strukList;
    }
    
    public static StrukDTO getStrukDTOById(int id) {
        StrukDTO dto = null;
        String sql = "SELECT s.id as struk_id, c.nama as customer_nama, c.kontak as customer_kontak, " +
                     "ls.jenis_layanan as layanan_jenis, ls.jenis_kerusakan as layanan_kerusakan, ls.biaya_service as layanan_biaya, " +
                     "t.nama_teknisi as teknisi_nama, s.tanggal_service as struk_tgl_service, s.tanggal_jadi as struk_tgl_jadi, " +
                     "s.status_servis as struk_status, s.catatan_teknisi as struk_catatan " +
                     "FROM struk s " +
                     "JOIN customer c ON s.customer_id = c.id " +
                     "JOIN layanan_service ls ON s.layanan_id = ls.id " +
                     "JOIN teknisi t ON s.teknisi_id = t.id " +
                     "WHERE s.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dto = mapResultSetToStrukDTO(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static boolean updateStatusServis(int strukId, String status, String catatanTeknisi) {
        String sql = "UPDATE struk SET status_servis = ?, catatan_teknisi = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, catatanTeknisi);
            ps.setInt(3, strukId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Long> getCountStrukByStatus() {
        Map<String, Long> countByStatus = new HashMap<>();
        String sql = "SELECT status_servis, COUNT(*) as jumlah FROM struk GROUP BY status_servis";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String status = rs.getString("status_servis");
                if (status == null || status.trim().isEmpty()){
                    status = "Belum Ada Status"; 
                }
                countByStatus.put(status, rs.getLong("jumlah"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countByStatus;
    }
    
    public static long countTotalStruk() {
        String sql = "SELECT COUNT(*) FROM struk";
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

    public static double getTotalPendapatanSelesai() {
        double totalPendapatan = 0;
        String sql = "SELECT SUM(ls.biaya_service) as total " +
                     "FROM struk s " +
                     "JOIN layanan_service ls ON s.layanan_id = ls.id " +
                     "WHERE s.status_servis = 'Selesai' OR s.status_servis = 'Diambil Pelanggan'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalPendapatan = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPendapatan;
    }

    public static boolean deleteStrukById(int id) {
        String sql = "DELETE FROM struk WHERE id = ?";
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
}