/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MultipleChoice extends CauHoi {

	private String ghichu;
	private int phuongAnDung;
	public static int demCauhoi;
	private List<PhuongAn> pa = new ArrayList<>();// phan nay setter se them 1 phuong an
	
	// constractor
	public MultipleChoice() {
	}

	public MultipleChoice(int maDanhMuc) {
		super(maDanhMuc);
	}

	public MultipleChoice(int maDanhMuc, int level, int id) {
		super(maDanhMuc, level, id);
	}

	// getter and setter
	public String getNoiDungCauHoi() {
		return noiDungCauHoi;
	}

	public void setNoiDungCauHoi(String noiDungCauHoi) {
		this.noiDungCauHoi = noiDungCauHoi;
	}

	@Override
	public int getPhuongAnDung() {
		return phuongAnDung;
	}

	@Override
	public void setPhuongAnDung(int phuongAnDung) {
		this.phuongAnDung = phuongAnDung;
	}

	@Override
	public List<PhuongAn> getPa() {
		return pa;
	}

	@Override
	public void setPa(List<PhuongAn> pa) {
		this.pa = pa;
	}

	@Override
	public String getGhichu() {
		return ghichu;
	}

	@Override
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	// cac phuong thuc
	
	// xuat mot cau hoi 
	@Override
	public void show() {// xuất ra đầy đủ thông tin
		DanhMuc dm = new DanhMuc();
		System.out.println("ID: " + this.id);
		if (this.maDanhMuc != 0) {
			System.out.println("Danh muc: " + dm.getMapDanhMuc().get(this.maDanhMuc));
		}
		if (this.lev == Level.EASY || this.lev == Level.MEDIUM || this.lev == Level.HARD) {
			System.out.println("Muc do: " + this.lev);
		}
		System.out.println(this.noiDungCauHoi);
		char alpha = 'A';

		for (PhuongAn item : pa) {
			System.out.printf("%c)", alpha);
			item.show();
			alpha++;
		}

	}

	@Override
	public void showLess() {// xuất ra các nội dung để user làm bài
		System.out.println(this.noiDungCauHoi);
		char alpha = 'A';
		for (PhuongAn item : pa) {
			System.out.printf("%c)", alpha);
			item.show();
			alpha++;
		}
	}
	
	// xuất ra nôi dung và phương án đúng 
	@Override
	public void ShowKQ(){
		System.out.println(this.noiDungCauHoi);
		System.out.print("Phuong an dung la: ");
		System.out.print(this.pa.get(this.phuongAnDung-1));
		if (this.ghichu.length() != 0)
			System.out.printf("%s\n",this.ghichu);
	}

	// them cau hoi
	@Override
	public void nhapCauHoi() {
		// bien
		DanhMuc dm = new DanhMuc();
		String chooseDM, max, level, n, paDung;
		// chon danh muc
		dm.showDanhMuc();
		System.out.print("Chon danh muc: ");
		do {
			chooseDM = CauHinh.sc.nextLine();
			
			if (CauHinh.checking(chooseDM, "1", max = Integer.toString(dm.getSoLuongDanhMuc()))) {
				System.out.print("Chon lai:  ");
			} else {
				this.setMaDanhMuc(100 + (Integer.parseInt(chooseDM) - 1));
			}
		} while (CauHinh.checking(chooseDM, "1", max = Integer.toString(dm.getSoLuongDanhMuc())));
		// chon level
		System.out.print("1.De\n2.Trung binh\n3.Kho\n=>  ");
		do {
			level = CauHinh.sc.nextLine();
			if (CauHinh.checking(level, "1", "3")) {
				System.out.print("Chon lai: ");
			} else {
				switch (Integer.parseInt(level)) {
				case 1: {
					this.setLev(lev.EASY);
					break;
				}
				case 2: {
					this.setLev(lev.MEDIUM);
					break;
				}
				case 3: {
					this.setLev(lev.HARD);
					break;
				}
				}
			}
		} while (CauHinh.checking(level, "1", "3"));
		// nhap noi dung
//		CauHinh.sc.nextLine();
		System.out.print("Nhap noi dung cau hoi\n=> ");
		this.setNoiDungCauHoi(CauHinh.sc.nextLine());
		// nhap phuong an
		System.out.print("Nhap so luong phuong an: ");
		do {
			n = CauHinh.sc.nextLine();
			if (CauHinh.checking(n,"2", "10")) {
				System.out.print("Chon lai: ");
			} else {
				for (int i = 0; i < Integer.parseInt(n); i++) {
					System.out.printf("Noi dung phong an [%d]\n=>", i + 1);
					PhuongAn newpa = new PhuongAn();
					newpa.nhapPhuongAn();
					pa.add(newpa);
				}
				System.out.print("Them ghi chu: ");
				this.ghichu = CauHinh.sc.nextLine();
			}
		} while (CauHinh.checking(n,"2", "10"));// toi da 10 phuong an
		// nhap phuong an dung
		System.out.print("Phuong an dung la: ");
		do {
			paDung = CauHinh.sc.nextLine();
			if (CauHinh.checking(paDung, "1", Integer.toString(pa.size()))) {
				System.out.print("Chon lai: ");
			} else {
				this.setPhuongAnDung(Integer.parseInt(paDung));
			}
		} while (CauHinh.checking(paDung, "1", Integer.toString(pa.size())));
//		this.setDem(this.getDem()-Integer.parseInt(n));
		//xong
	}
}
