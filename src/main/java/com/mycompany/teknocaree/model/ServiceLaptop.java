package com.mycompany.teknocaree.model;

public class ServiceLaptop extends LayananService {
    public ServiceLaptop() {}

    @Override
    public void inputData() {
        
    }

    @Override
    public void showDetail() {
        System.out.println("Laptop - " + jenisKerusakan + " - Rp" + biayaService);
    }
}

