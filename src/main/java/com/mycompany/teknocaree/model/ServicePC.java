/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teknocaree.model;

/**
 *
 * @author Azka
 */

public class ServicePC extends LayananService {
    public ServicePC() {}

    @Override
    public void inputData() {
        
    }

    @Override
    public void showDetail() {
        System.out.println("PC - " + jenisKerusakan + " - Rp" + biayaService);
    }
}