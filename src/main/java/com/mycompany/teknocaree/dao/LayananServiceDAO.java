package com.mycompany.teknocaree.dao;

import com.mycompany.teknocaree.model.LayananService;
import com.mycompany.teknocaree.util.DBConnection;

import java.sql.*;

public class LayananServiceDAO {
    public static int insertService(LayananService service, String jenisLayanan, int idKatalog) {
        String sql = "INSERT INTO layanan_service (jenis_layanan, id_katalog_fk, jenis_kerusakan, biaya_service) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, jenisLayanan);
            ps.setInt(2, idKatalog);
            ps.setString(3, service.getJenisKerusakan());
            ps.setInt(4, service.getBiayaService());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}