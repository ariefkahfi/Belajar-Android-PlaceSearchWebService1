package com.kahfi.arief.cobagooglemaps2googleplacesearchwebservice.model;

/**
 * Created by Arief on 9/1/2017.
 */

public class ATM {
    private String nama;
    private String alamat;


    public ATM(){}

    public ATM(String nama,String alamat){
        this.nama=nama;
        this.alamat=alamat;
    }

    public void setNama(String nama){
        this.nama=nama;
    }
    public String getNama(){return nama;}

    public void setAlamat(String alamat){
        this.alamat=alamat;
    }
    public String getAlamat(){
        return alamat;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                '}';
    }
}
