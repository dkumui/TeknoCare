package com.mycompany.teknocaree.model;

public class Customer {
    private int id;
    private String nama;
    private String kontak;

    public Customer() {}

    public Customer(int id, String nama, String kontak) {
        this.id = id;
        this.nama = nama;
        this.kontak = kontak;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKontak() { return kontak; }
    public void setKontak(String kontak) { this.kontak = kontak; }
}