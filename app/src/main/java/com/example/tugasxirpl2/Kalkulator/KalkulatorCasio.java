package com.example.tugasxirpl2.Kalkulator;

public class KalkulatorCasio extends AbstactKalkulator {
    @Override
    public int penjumlahan(int angka1, int angka2) {
        int hasil = angka1+angka2;
        return hasil;
    }

    @Override
    public int pengurangan(int angka1, int angka2) {
        int hasil = angka1-angka2;
        return hasil;
    }

    @Override
    public int perkalian(int angka1, int angka2) {
        int hasil = angka1*angka2;
        return hasil;
    }

    @Override
    public double pembagian(double angka1, double angka2) {
        double hasilbagi = angka1-angka2;
        return hasilbagi;
    }
}
