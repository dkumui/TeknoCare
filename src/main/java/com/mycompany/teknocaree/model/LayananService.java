package com.mycompany.teknocaree.model;

public abstract class LayananService {
    
    protected int id;
    protected String jenisKerusakan;
    protected int biayaService;

    public LayananService() {}

    public LayananService(int id, String jenisKerusakan, int biayaService) {
        this.id = id;
        this.jenisKerusakan = jenisKerusakan;
        this.biayaService = biayaService;
    }

    public abstract void inputData();
    public abstract void showDetail();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getJenisKerusakan() { return jenisKerusakan; }
    public void setJenisKerusakan(String jenisKerusakan) { this.jenisKerusakan = jenisKerusakan; }

    public int getBiayaService() { return biayaService; }
    public void setBiayaService(int biayaService) { this.biayaService = biayaService; }
}
