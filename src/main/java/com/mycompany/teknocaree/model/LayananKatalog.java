package com.mycompany.teknocaree.model;

public class LayananKatalog {
    private int idKatalog;
    private String namaLayanan;
    private String deskripsiLayanan;
    private String tipePerangkat;
    private int biayaStandar;
    private boolean aktif;

    public LayananKatalog() {
        this.aktif = true;
    }

    public int getIdKatalog() {
        return idKatalog;
    }

    public void setIdKatalog(int idKatalog) {
        this.idKatalog = idKatalog;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public String getDeskripsiLayanan() {
        return deskripsiLayanan;
    }

    public void setDeskripsiLayanan(String deskripsiLayanan) {
        this.deskripsiLayanan = deskripsiLayanan;
    }

    public String getTipePerangkat() {
        return tipePerangkat;
    }

    public void setTipePerangkat(String tipePerangkat) {
        this.tipePerangkat = tipePerangkat;
    }

    public int getBiayaStandar() {
        return biayaStandar;
    }

    public void setBiayaStandar(int biayaStandar) {
        this.biayaStandar = biayaStandar;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }
}