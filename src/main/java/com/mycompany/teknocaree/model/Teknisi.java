package com.mycompany.teknocaree.model;

public class Teknisi {
    private int id;
    private String namaTeknisi;
    private String spesialisasi;
    private String kontakTeknisi;
    private boolean aktif;

    public Teknisi() {
        this.aktif = true;
    }

    public Teknisi(int id, String namaTeknisi) {
        this.id = id;
        this.namaTeknisi = namaTeknisi;
        this.aktif = true;
    }
    
    public Teknisi(int id, String namaTeknisi, String spesialisasi, String kontakTeknisi, boolean aktif) {
        this.id = id;
        this.namaTeknisi = namaTeknisi;
        this.spesialisasi = spesialisasi;
        this.kontakTeknisi = kontakTeknisi;
        this.aktif = aktif;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaTeknisi() { return namaTeknisi; }
    public void setNamaTeknisi(String namaTeknisi) { this.namaTeknisi = namaTeknisi; }

    public String getSpesialisasi() { return spesialisasi; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }

    public String getKontakTeknisi() { return kontakTeknisi; }
    public void setKontakTeknisi(String kontakTeknisi) { this.kontakTeknisi = kontakTeknisi; }

    public boolean isAktif() { return aktif; }
    public void setAktif(boolean aktif) { this.aktif = aktif; }
}