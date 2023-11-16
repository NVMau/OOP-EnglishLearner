/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
public abstract class CauHoi {
	// 19/4/2023 ban chinh sua cuoi cung4.0
	private static int dem;
	protected String noiDungCauHoi;
	protected int maDanhMuc, maDangCauHoi;
	protected Level lev;
	protected int id;
	{
		id = ++dem;
	}

	//constractor
	public CauHoi() {

	}

	public CauHoi(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public CauHoi(int maDanhMuc, int i, int id) {
		this.maDanhMuc = maDanhMuc;
		this.id = id;
		switch (i) {
		case 1: {
			this.lev = Level.EASY;
		}
		case 2: {
			this.lev = Level.MEDIUM;
		}
		case 3: {
			this.lev = Level.HARD;
		}
		}
	}

	// getter and setter
	public int getMaDangCauHoi() {
		return maDangCauHoi;
	}

	public void setMaDangCauHoi(int maDangCauHoi) {
		this.maDangCauHoi = maDangCauHoi;
	}
	
	public String getNoiDungCauHoi() {
		return noiDungCauHoi;
	}

	public void setNoiDungCauHoi(String noiDungCauHoi) {
		this.noiDungCauHoi = noiDungCauHoi;
	}

	public int getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public Level getLev() {
		return lev;
	}

	public void setLev(Level lev) {
		this.lev = lev;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDem() {
		return dem;
	}

	public void setDem(int dem) {
		this.dem = dem;
	}
	// phuong thuc thuong
	public void setPhuongAnDung(int phuongAnDung){
		
	}

	public void setPa(List<PhuongAn> pa) {
		
	}
	
	public List<PhuongAn> getPa() {
		return null;
	}
	
	public void setGhichu(String ghichu) {
		
	}
	
	public void setDaQuiz(List<MultipleChoice> daQuiz) {
	}
	public List<MultipleChoice> getDaQuiz() {
		return null;
	}
	
	public String getGhichu() {
		return null;
	}
	
	public int getPhuongAnDung() {
		return 0;
	}
	
	public void ShowKQ(){
		
	}
	// cac phuong thuc abstract
	public abstract void show();

	public abstract void showLess();
	
	public abstract void nhapCauHoi();

	// to string
	@Override
	public String toString() {
		return "CauHoi{" + "noiDungCauHoi=" + noiDungCauHoi + ", maDanhMuc=" + maDanhMuc + ", lev=" + lev + ", id=" + id + '}';
	}

}
