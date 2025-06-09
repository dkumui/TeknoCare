package com.mycompany.teknocaree.model;

import java.time.LocalDate;

public class Struk {
    private int id;
    private Customer customer;
    private LayananService layanan;
    private Teknisi teknisi;
    private LocalDate tanggalService;
    private LocalDate tanggalJadi;

    public Struk() {}

    public Struk(Customer customer, LayananService layanan, Teknisi teknisi, LocalDate tanggalService, LocalDate tanggalJadi) {
        this.customer = customer;
        this.layanan = layanan;
        this.teknisi = teknisi;
        this.tanggalService = tanggalService;
        this.tanggalJadi = tanggalJadi;
    }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LayananService getLayanan() { return layanan; }
    public void setLayanan(LayananService layanan) { this.layanan = layanan; }

    public Teknisi getTeknisi() { return teknisi; }
    public void setTeknisi(Teknisi teknisi) { this.teknisi = teknisi; }

    public LocalDate getTanggalService() { return tanggalService; }
    public void setTanggalService(LocalDate tanggalService) { this.tanggalService = tanggalService; }

    public LocalDate getTanggalJadi() { return tanggalJadi; }
    public void setTanggalJadi(LocalDate tanggalJadi) { this.tanggalJadi = tanggalJadi; }
}