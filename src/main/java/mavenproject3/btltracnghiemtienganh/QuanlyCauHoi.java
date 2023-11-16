/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.awt.Choice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class QuanlyCauHoi {

	// khai bao list danh sach
	private List<CauHoi> danhSach = new ArrayList<>();

	// getter and setter
	public List<CauHoi> getDanhSach() {
		return danhSach;
	}

	// khoi tao cac danh list
	public void setDanhSach(List<CauHoi> danhSach) {
		this.danhSach = danhSach;
	}

	// constractor
	public QuanlyCauHoi() {
	}

	// inport file // chua chinh sua
	public int dem = 0;

	public void inportFile(String tapTin) throws FileNotFoundException {
		// bien temp
		int dang = 0, soLuongCauhoi = 0, soLuongPhuongAn = 0;
		String line = null, ghichu = null;
		File ifile = new File(tapTin);
		try (Scanner scanner = new Scanner(ifile)) {
			if (scanner.hasNext()) {
				dem = scanner.nextInt();
			}
			scanner.nextLine();
			while (scanner.hasNext()) {
				dang = scanner.nextInt();
				scanner.nextLine();
				switch (dang) {
				case 1: {// multiplechoi
					scanner.nextLine();// don rac
					CauHoi qs = new MultipleChoice();
					qs.setMaDangCauHoi(dang);
					qs.setId(scanner.nextInt());// lay ID
					qs.setMaDanhMuc(scanner.nextInt());// lay Ma danh Muc
					switch (scanner.nextInt()) {// lay muc do cau hoi
					case 1: {
						qs.setLev(Level.EASY);
						break;
					}
					case 2: {
						qs.setLev(Level.MEDIUM);
						break;
					}
					case 3: {
						qs.setLev(Level.HARD);
						break;
					}
					}
					soLuongPhuongAn = scanner.nextInt();
					qs.setPhuongAnDung(scanner.nextInt());// lay phuong an dung
					scanner.nextLine();
					qs.setNoiDungCauHoi(scanner.nextLine());// lay noi dung
					for (int i = 0; i < soLuongPhuongAn; i++) {
						PhuongAn pas = new PhuongAn();
						pas.setNoiDungDapAn(scanner.nextLine());
						qs.getPa().add(pas);
					}
					qs.setGhichu(scanner.nextLine());
					qs.setDem(dem);
					danhSach.add(qs);//
					break;
				}
				case 2: {// conversation , Incomplete
					scanner.nextLine();// don rac 
					CauHoi qs = new Conversation();// khoi tao
					qs.setMaDangCauHoi(dang);
					qs.setId(scanner.nextInt());
					soLuongCauhoi = scanner.nextInt();
					qs.setMaDanhMuc(scanner.nextInt());
					switch (scanner.nextInt()) {
					case 1: {
						qs.setLev(Level.EASY);
						break;
					}
					case 2: {
						qs.setLev(Level.MEDIUM);
						break;
					}
					case 3: {
						qs.setLev(Level.HARD);
						break;
					}
					}
					scanner.nextLine();
					qs.setNoiDungCauHoi(scanner.nextLine());
					for (int i = 0; i < soLuongCauhoi; i++) {
						MultipleChoice cauHoiCV = new MultipleChoice();
						cauHoiCV.setPhuongAnDung(scanner.nextInt());
						scanner.nextLine();
						cauHoiCV.setNoiDungCauHoi(scanner.nextLine());
						for (int j = 0; j < 4; j++) {
							PhuongAn dapAnNoiDung = new PhuongAn();
							dapAnNoiDung.setNoiDungDapAn(scanner.nextLine());
							cauHoiCV.getPa().add(dapAnNoiDung);
						}
						cauHoiCV.setGhichu(scanner.nextLine());
						qs.getDaQuiz().add(cauHoiCV);
					}
					qs.setDem(dem);
					danhSach.add(qs);//
					break;
				}
				case 3: {// Incomplete
					scanner.nextLine();
					CauHoi qs = new Incomplete();
					qs.setMaDangCauHoi(dang);
					qs.setId(scanner.nextInt());
					soLuongCauhoi = scanner.nextInt();
					qs.setMaDanhMuc(scanner.nextInt());
					switch (scanner.nextInt()) {
					case 1: {
						qs.setLev(Level.EASY);
						break;
					}
					case 2: {
						qs.setLev(Level.MEDIUM);
						break;
					}
					case 3: {
						qs.setLev(Level.HARD);
						break;
					}
					}
					scanner.nextLine();
					qs.setNoiDungCauHoi(scanner.nextLine());
					for (int i = 0; i < soLuongCauhoi; i++) {
						MultipleChoice cauHoiIC = new MultipleChoice();
						cauHoiIC.setPhuongAnDung(scanner.nextInt());
						scanner.nextLine();
						cauHoiIC.setNoiDungCauHoi(scanner.nextLine());
						for (int j = 0; j < 4; j++) {
							PhuongAn dapAnNoiDung = new PhuongAn();
							dapAnNoiDung.setNoiDungDapAn(scanner.nextLine());
							cauHoiIC.getPa().add(dapAnNoiDung);
						}
						cauHoiIC.setGhichu(scanner.nextLine());
						qs.getDaQuiz().add(cauHoiIC);
					}
					qs.setDem(dem);
					danhSach.add(qs);//
					break;
				}
				}
			}

			scanner.close();// dong file lai
		} catch (IOException e) {
			System.out.println("Can not read file!");
			e.printStackTrace();
		}
	}

	// hien thi cau hoi
	public void showListQuestion() {

		for (CauHoi quiz : danhSach) {
			switch (quiz.getMaDangCauHoi()) {
			case 1:
				System.out.println("-------------------------Cau hoi Multiplechoice-------------------------");
				quiz.show();
				System.out.println("--------------------------------------------------------------------");
				break;
			case 2:
				System.out.println("-------------------------Cau hoi Conversation-------------------------");
				quiz.show();
				System.out.println("--------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("-------------------------Cau hoi Incomplete-------------------------");
				quiz.show();
				System.out.println("--------------------------------------------------------------------");
				break;
			}
		}
	}

	// them cau hoi tu ban phim
	DanhMuc dm = new DanhMuc();

	public void menu() {
		System.out.println("---------------------MENU----------------------");
		System.out.println("1.Them cau hoi dang MultipleChoice");
		System.out.println("2.Them cau hoi dang Conversation");
		System.out.println("3.Them cau hoi dang Incomplete");
		System.out.println("4.Thoat!!");
	}

//           them nhieu cau hoi// chua chinh sua
	public void addQuestions() throws IOException {
		int choice = 1;
		String chon, n;
		do {
			menu();
			System.out.print("=> ");
			do {
				chon = CauHinh.sc.nextLine();
				if (CauHinh.checking(chon, "1", "4")) {
					System.out.print("chon lai: ");
				}
			} while (CauHinh.checking(chon, "1", "4"));

			switch (Integer.parseInt(chon)) {
			case 1: {// multiple choise
				System.out.print("Nhap so luong cau muon them: ");
				do{
					n = CauHinh.sc.nextLine();
					if(!CauHinh.isNumber(n))
						System.out.print("Nhap lai: ");
				}while(!CauHinh.isNumber(n));
				
				for (int i = 0; i < Integer.parseInt(n); i++) {
					System.out.printf("Cau hoi thu [%d]\n", i + 1);
					CauHoi cauMoi = new MultipleChoice();
					cauMoi.setMaDangCauHoi(1);
					// sang ham nhap multiple
					cauMoi.nhapCauHoi();
					danhSach.add(cauMoi);
					// xong
				}
				dem += Integer.parseInt(n);
				break;
			}
			case 2: {
				CauHoi newCauHoi = new Conversation();
				newCauHoi.setMaDangCauHoi(2);
				newCauHoi.nhapCauHoi();
				// xong
				danhSach.add(newCauHoi);
				dem += 1;
				break;
			}
			case 3: {
				CauHoi newcCauHoi = new Incomplete();
				newcCauHoi.setMaDangCauHoi(3);
				newcCauHoi.nhapCauHoi();
				//xong
				danhSach.add(newcCauHoi);
				dem += 1;
				break;
			}
			}
		} while (!chon.equals("4"));
		writingInText(CauHinh.fileCauHoi);
	}

	public void writingInText(String tapTin) throws IOException {
		try {
			FileWriter ghi = new FileWriter(tapTin);
			ghi.write(Integer.toString(dem) + "\n");
			for (CauHoi item : danhSach) {
				switch (item.getMaDangCauHoi()) {
				case 1:
					ghi.write("1\n");
					ghi.write("############## MultipleChoice ##############\n");
					ghi.write(Integer.toString(item.getId()) + " ");
					ghi.write(Integer.toString(item.getMaDanhMuc()));
					if (item.getLev() == Level.EASY) {
						ghi.write(" 1 ");
					} else if (item.getLev() == Level.MEDIUM) {
						ghi.write(" 2 ");
					} else if (item.getLev() == Level.HARD) {
						ghi.write(" 3 ");
					}
					ghi.write(Integer.toString(item.getPa().size()) + " ");
					ghi.write(Integer.toString(item.getPhuongAnDung()) + "\n");
					ghi.write(item.getNoiDungCauHoi() + "\n");
					for (int j = 0; j < item.getPa().size(); j++) {
						ghi.write(item.getPa().get(j).getNoiDungDapAn() + "\n");

					}
					ghi.write(item.getGhichu() + "\n");
					break;

				case 2,3:
					if (item.getMaDangCauHoi() == 2) {
						ghi.write("2\n");
						ghi.write("############## Conversation ##############\n");
					} else if (item.getMaDangCauHoi() == 3) {
						ghi.write("3\n");
						ghi.write("############## Incomplete ##############\n");
					}
					ghi.write(Integer.toString(item.getId()) + " ");
					ghi.write(Integer.toString(item.getDaQuiz().size()) + " ");
					ghi.write(Integer.toString(item.getMaDanhMuc()));
					if (item.getLev() == Level.EASY) {
						ghi.write(" 1\n");
					} else if (item.getLev() == Level.MEDIUM) {
						ghi.write(" 2\n");
					} else if (item.getLev() == Level.HARD) {
						ghi.write(" 3\n");
					}
					ghi.write(item.getNoiDungCauHoi() + "\n");
					for (int z = 0; z < item.getDaQuiz().size(); z++) {
						ghi.write(Integer.toString(item.getDaQuiz().get(z).getPhuongAnDung()) + "\n");
						ghi.write(item.getDaQuiz().get(z).getNoiDungCauHoi() + "\n");
						for (int j = 0; j < 4; j++) {
							ghi.write(item.getDaQuiz().get(z).getPa().get(j).getNoiDungDapAn() + "\n");
						}
						ghi.write(item.getDaQuiz().get(z).getGhichu() + "\n");
					}
					break;
				}
			}
			ghi.close();
		} catch (Exception e) {
			System.out.println("Error writing to file ");
			e.printStackTrace();
		}

	}
	// xoa cau hoi 

	public void removeQuestion() throws IOException {
		String chose, soCau;
		boolean tt = true;
		List<Integer> vitri = new ArrayList<>();
		do {
			System.out.print("[1].MultipleChoice\n[2].Conversation\n[3].Incomplete\n");
			System.out.print("Chon dang cau hoi de xoa: ");
			do {
				chose = CauHinh.sc.nextLine();
				if (CauHinh.checking(chose, "1", "3")) {
					System.out.print("Again => ");
				}
			} while (CauHinh.checking(chose, "1", "3"));
			// xuat danh sach
			for (int i = 0; i < danhSach.size(); i++) {
				if (this.danhSach.get(i).getMaDangCauHoi() == Integer.parseInt(chose)) {
					System.out.printf("------------------------- Cau so %d -------------------------\n", i + 1);
					this.danhSach.get(i).show();
					vitri.add(i);
					System.out.println("----------------------------------------------------------------");
				}
			}
			if (vitri.size() != 0) {
				// kiem tra nhap
				System.out.print("Nhap so cau muon xoa: ");
				do {
					tt = true;
					soCau = CauHinh.sc.nextLine();
					if (CauHinh.isNumber(soCau)) {
						for (Integer j : vitri) {
							if ((Integer.parseInt(soCau)-1) == j) {
								tt = false;
							}
						}
					} else {
						System.out.print("Not Find!!, choose again => ");
					}
				} while (tt);
				danhSach.remove(Integer.parseInt(soCau) - 1);
				System.out.println("Done!!");
			}else
				System.out.println("He thong khong con cau hoi nao!!!");

			
			
			// cap nhat id cau hoi

			// nhap lau chon
			System.out.print("Tiep tuc xoa cau hoi khac?\n[1].yes\n[2].no\n=> ");
			do {
				chose = CauHinh.sc.nextLine();
				if (CauHinh.checking(chose, "1", "2")) {
					System.out.println("Again => ");
				}
			} while (CauHinh.checking(chose, "1", "2"));
		} while (!chose.equals("2"));
		this.writingInText(CauHinh.fileCauHoi);
	}
//	 ################################# tim cau hoi #################################

	public List<Integer> findQues(int dang, String s, String mucdo, String danhmuc) {
		// set muc
		Level m;
		if (mucdo.equals("1")) {
			m = Level.EASY;
		} else if (mucdo.equals("2")) {
			m = Level.MEDIUM;
		} else {
			m = Level.HARD;
		}
		//set danh muc
		int dmuc = 100 + (Integer.parseInt(danhmuc) - 1);
		List<Integer> id = new ArrayList<>();
		for (int i = 0; i < danhSach.size(); i++) {
			if (danhSach.get(i).getNoiDungCauHoi().contains(s) && m == danhSach.get(i).getLev() && danhSach.get(i).getMaDanhMuc() == dmuc && danhSach.get(i).getMaDangCauHoi() == dang) {
				id.add(i);
			}
		}
		return id;
	}

	// chi noi dung
	public List<Integer> findQues(int dang, String s) {
		List<Integer> id = new ArrayList<>();// luu vi tri trong list
		for (int i = 0; i < danhSach.size(); i++) {
			if (danhSach.get(i).getNoiDungCauHoi().contains(s) && danhSach.get(i).getMaDangCauHoi() == dang) {
				id.add(i);
			}
		}
		return id;
	}

	// noi dung va muc do
	public List<Integer> findQues(int dang, String s, String mucdo) {
		Level m;
		if (mucdo.equals("1")) {
			m = Level.EASY;
		} else if (mucdo.equals("2")) {
			m = Level.MEDIUM;
		} else {
			m = Level.HARD;
		}

		List<Integer> id = new ArrayList<>();
		for (int i = 0; i < danhSach.size(); i++) {
			if (danhSach.get(i).getNoiDungCauHoi().contains(s) && m == danhSach.get(i).getLev() && danhSach.get(i).getMaDangCauHoi() == dang) {
				id.add(i);
			}
		}
		return id;
	}

	// noi dung vs danh muc
	public List<Integer> findQues(String danhmuc, int dang, String s) {
		int dmuc = 100 + (Integer.parseInt(danhmuc) - 1);
		List<Integer> id = new ArrayList<>();
		for (int i = 0; i < danhSach.size(); i++) {
			if (danhSach.get(i).getNoiDungCauHoi().contains(s) && danhSach.get(i).getMaDanhMuc() == dmuc && danhSach.get(i).getMaDangCauHoi() == dang) {
				id.add(i);
			}
		}
		return id;
	}

	// tim cau hoi ham chinh
	public void timCauHoi() {
		String chose = null, danhMuc, mucdo, noidung;
		List<Integer> id = new ArrayList<>();

		System.out.print("[1].MultipleChoice\n[2].Conversation\n[3].Incomplete\n=> ");
		do {
			chose = CauHinh.sc.nextLine();
			if (CauHinh.checking(chose, "1", "3")) {
				System.out.print("chon lai => ");
			}
		} while (CauHinh.checking(chose, "1", "3"));

		// chia ra cac dang
		System.out.print("Nhap noi dung cau hoi muon tim: ");
		noidung = CauHinh.sc.nextLine();
		noidung = noidung.trim();
		if (noidung.length() == 0) {
			noidung = " ";
		}
		System.out.print("[1].DE\n[2].TRUNG BINH\n[3].KHO\n[4].DE TRONG PHAN NAY\n=> ");
		do {
			mucdo = CauHinh.sc.nextLine();
			if (CauHinh.checking(mucdo, "1", "4")) {
				System.out.print("Chon lai=> ");
			}
		} while (CauHinh.checking(mucdo, "1", "4"));
		dm.showDanhMuc();
		System.out.printf("[%d] De trong\n", dm.getSoLuongDanhMuc() + 1);
		System.out.print("=> ");
		do {
			danhMuc = CauHinh.sc.nextLine();
			if (CauHinh.checking(mucdo, "1", Integer.toString(dm.getSoLuongDanhMuc() + 1))) {
				System.out.print("Chon lai => ");
			}
		} while (CauHinh.checking(mucdo, "1", Integer.toString(dm.getSoLuongDanhMuc() + 1)));
		// phan loai nap chong 
		if (!mucdo.equals("4") && !danhMuc.equals(Integer.toString(dm.getSoLuongDanhMuc() + 1))) {
			// noi dung, muc do va danh muc
			id = findQues(Integer.parseInt(chose), noidung, mucdo, danhMuc);
		} else if (!mucdo.equals("4") && danhMuc.equals(Integer.toString(dm.getSoLuongDanhMuc() + 1))) {
			id = findQues(Integer.parseInt(chose), noidung, mucdo);// noi dung, mucdo
		} else if (mucdo.equals("4") && !danhMuc.equals(Integer.toString(dm.getSoLuongDanhMuc() + 1))) {
			id = findQues(danhMuc, Integer.parseInt(chose), noidung);// noi dung , danh muc
		} else if (mucdo.equals("4") && danhMuc.equals(Integer.toString(dm.getSoLuongDanhMuc() + 1))) {
			id = findQues(Integer.parseInt(chose), noidung);// noi dung
		}
		// xuat ra
		/// thong bao khi chay xong
		if (id.size() == 0) {
			System.out.println("Khong tim thay cau hoi nao!!");
		} else {
			//// xuat ra man hịnh
			System.out.println("------------------------- Ket qua Tim kiem cau hoi -------------------------");
			for (int i = 0; i < id.size(); i++) {
				this.danhSach.get(id.get(i)).show();
			}
			System.out.println("Done!");
		}
	}
// cac chuc nang

	public void mainQuestion() throws IOException {
		String choose;
		do {
			System.out.println("==========================================================");
			System.out.println("||                +++ QUAN LY CAU HOI +++               ||");
			System.out.println("==========================================================");
			System.out.println("|| 1.Xem danh sach cau hoi                              ||");
			System.out.println("|| 2.Tim cau hoi theo noi dung, danh muc va muc do      ||");
			System.out.println("|| 3.Them cau hoi                                       ||");
			System.out.println("|| 4.Xoa cau hoi                                        ||");
			System.out.println("|| 5.Quay lai Menu chinh                                ||");
			System.out.println("==========================================================");
			System.out.print("Enter => ");
			do {// kiem tra lua chon
				choose = CauHinh.sc.nextLine();
				if (CauHinh.checking(choose, "1", "5")) {
					System.out.print("Again => ");
				}
			} while (CauHinh.checking(choose, "1", "5"));

			switch (Integer.parseInt(choose)) {

			case 1:
				this.showListQuestion();
				break;
			case 2:
				timCauHoi();
				break;
			case 3:
				this.addQuestions();
				break;
			case 4:
				this.removeQuestion();
				break;
			}
		} while (!choose.equals("5"));
	}

}
