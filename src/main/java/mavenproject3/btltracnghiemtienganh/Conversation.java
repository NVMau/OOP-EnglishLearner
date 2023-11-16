/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Conversation extends CauHoi {
	public static int dem = 0;
	private List<MultipleChoice> daQuiz = new ArrayList<>();

	// constractor
	public Conversation() {
	}

	public Conversation(int maDanhMuc) {
		super(maDanhMuc);
	}

	public Conversation(int maDanhMuc, int level, int id) {
		super(maDanhMuc, level, id);
	}

	// getter and setter

	@Override
	public List<MultipleChoice> getDaQuiz() {
		return daQuiz;
	}

	@Override
	public void setDaQuiz(List<MultipleChoice> daQuiz) {
		this.daQuiz = daQuiz;
	}
	// to string

	@Override
	public String toString() {
		return String.format("Doc doan van sau va chon phuong an\n%s\nA)%s\nB)%s\nC)%s\nD)%s\n", this.noiDungCauHoi, this.getDaQuiz().get(0), this.getDaQuiz().get(1), this.getDaQuiz().get(2), this.getDaQuiz().get(3));
	}
	
	// cac phuong thuc abstract
	// show 
	DanhMuc dm = new DanhMuc();
	@Override
	public void show() {
		System.out.println("ID: " + this.id);
		System.out.println("Danh muc: " + dm.getMapDanhMuc().get(this.maDanhMuc));
		System.out.println("Cap do: " + this.lev);
		System.out.println(this.noiDungCauHoi);
		for (MultipleChoice item : daQuiz) {
			item.showLess();
		}

	}
	// them cau hoi

	@Override
	public void nhapCauHoi() {
		// bien
		String s, choseDM, level,paDung, n;
		//chon danh muc
		dm.showDanhMuc();
		System.out.print("Chon danh muc: ");
		do {
			choseDM = CauHinh.sc.nextLine();
			if (CauHinh.checking(choseDM, "1", Integer.toString(dm.getSoLuongDanhMuc()))) {
				System.out.print("Chon lai:  ");
			} else {
				this.setMaDanhMuc(100 + (Integer.parseInt(choseDM) - 1));
			}
		} while (CauHinh.checking(choseDM, "1", Integer.toString(dm.getSoLuongDanhMuc())));
		// chon muc do
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
		//nhap noi dung chinh
		System.out.print("Nhap noi dung doan van\n=> ");
//		CauHinh.sc.nextLine();
		this.setNoiDungCauHoi(CauHinh.sc.nextLine());
		//nhap so luong cau hoi muon them vao
		System.out.print("Nhap so luong cau hoi muon them vao: ");
		do {
			n = CauHinh.sc.nextLine();
			if (CauHinh.checking(n, "1", "20")) {// toi da 20 cau hoi 
				System.out.print("Chon lai: ");
			} else {
				for (int i = 0; i < Integer.parseInt(n); i++) {
					// khai bai multiplechoi
					MultipleChoice newQuest = new MultipleChoice();
					System.out.printf("Cau hoi thu [%d]\nNhap noi dung cau hoi =>", i + 1);
					newQuest.setNoiDungCauHoi(CauHinh.sc.nextLine());
					for (int j = 0; j < 4; j++) {
						System.out.printf("Nhap noi dung phuong an [%d]\n=>", j + 1);
						PhuongAn pas = new PhuongAn();
						pas.setNoiDungDapAn(CauHinh.sc.nextLine());
						newQuest.getPa().add(pas);
					}
					//nhap ghi chu cho cau hoi
					System.out.print("Them ghi chu: ");
					newQuest.setGhichu(CauHinh.sc.nextLine());
					//chon phuong an 
					System.out.print("Phuong an dung la: ");
					do {
						paDung = CauHinh.sc.nextLine();
						if (CauHinh.checking(paDung, "1", "4")) {
							System.out.print("Chon lai: ");
						} else {
							newQuest.setPhuongAnDung(Integer.parseInt(paDung));
						}
					} while (CauHinh.checking(paDung, "1", "4"));
					this.daQuiz.add(newQuest);
				}
			}
		} while (CauHinh.checking(n, "1", "20"));
		this.setDem(this.getDem()-Integer.parseInt(n));
	}

	@Override
	public void showLess() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
