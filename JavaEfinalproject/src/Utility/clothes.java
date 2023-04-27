package com.utility;

public class Clothes {
	private int id;
	private String nama;
	private String stok;
	private int harga;
	
	public Clothes(int kode, String nama, int stok, int harga) {
		this.kode = kode;
		this.nama = nama;
		this.stok = stok;
		this.harga = harga;
	}

	public int getkode() {
		return kode;
	}

	public void setkode(int kode) {
		this.kode = kode;
	}

	public String getnama() {
		return nama;
	}

	public void setnama(String nama) {
		this.nama = nama;
	}

	public int getstok() {
		return stok;
	}

	public void setstok(String stok) {
		this.stok = stok;
	}

	public int getharga() {
		return harga;
	}

	public void setharga(int harga) {
		this.harga = harga;
	}
	
}
