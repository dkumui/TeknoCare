package com.mycompany.teknocaree.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;

public class StrukDTO {
    private int id;
    private String namaCustomer;
    private String kontakCustomer;
    private String jenisLayanan;
    private String jenisKerusakan;
    private int biayaService;
    private String namaTeknisi;
    private LocalDate tanggalService;
    private LocalDate tanggalJadi;
    private String statusServis;
    private String catatanTeknisi;

    public StrukDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaCustomer() { return namaCustomer; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }

    public String getKontakCustomer() { return kontakCustomer; }
    public void setKontakCustomer(String kontakCustomer) { this.kontakCustomer = kontakCustomer; }

    public String getJenisLayanan() { return jenisLayanan; }
    public void setJenisLayanan(String jenisLayanan) { this.jenisLayanan = jenisLayanan; }

    public String getJenisKerusakan() { return jenisKerusakan; }
    public void setJenisKerusakan(String jenisKerusakan) { this.jenisKerusakan = jenisKerusakan; }

    public int getBiayaService() { return biayaService; }
    public void setBiayaService(int biayaService) { this.biayaService = biayaService; }

    public String getNamaTeknisi() { return namaTeknisi; }
    public void setNamaTeknisi(String namaTeknisi) { this.namaTeknisi = namaTeknisi; }

    public LocalDate getTanggalService() { return tanggalService; }
    public void setTanggalService(LocalDate tanggalService) { this.tanggalService = tanggalService; }

    public LocalDate getTanggalJadi() { return tanggalJadi; }
    public void setTanggalJadi(LocalDate tanggalJadi) { this.tanggalJadi = tanggalJadi; }

    public String getStatusServis() { return statusServis; }
    public void setStatusServis(String statusServis) { this.statusServis = statusServis; }

    public String getCatatanTeknisi() { return catatanTeknisi; }
    public void setCatatanTeknisi(String catatanTeknisi) { this.catatanTeknisi = catatanTeknisi; }

    // Getter BARU untuk konversi ke java.util.Date untuk JSTL
    public Date getTanggalServiceAsDate() {
        if (this.tanggalService == null) {
            return null;
        }
        return Date.from(this.tanggalService.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getTanggalJadiAsDate() {
        if (this.tanggalJadi == null) {
            return null;
        }
        return Date.from(this.tanggalJadi.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}