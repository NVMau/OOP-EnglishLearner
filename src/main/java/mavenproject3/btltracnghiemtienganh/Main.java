/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		QuanlyCauHoi quizManagement = new QuanlyCauHoi();
		quizManagement.inportFile(CauHinh.fileCauHoi);
		// lớp hoc sinh các user
		QuanLyHocSinh users = new QuanLyHocSinh();
		users.importHocSinh(CauHinh.fileHocSinh);
		// test lam bai
		Test test = new Test();
		//id user
		int idList = -1;

		String choose = "", userChose;
		boolean conti = true;
		// vong lăp chương trình
		do {
			if (idList == -1) { // chua co user tham gia
				// nhap lua chon trong menu
				menu();
				do {
					choose = CauHinh.sc.nextLine();
					if (CauHinh.checking(choose, "1", "5")) {
						System.out.print("CHOOSE AGAIN! => ");
					}
				} while (CauHinh.checking(choose, "1", "5"));
				// khi user chon xong lua chon
				switch (Integer.parseInt(choose)) {
				case 1:
					idList = signinAndLogin(users);
					break;
				case 2:
					users.mainQLHocSinh();
					break;
				case 3:
					quizManagement.mainQuestion();
					break;
				case 4:
					System.out.println("Vui long dang nhap de lam bai kiem tra");
					idList = signinAndLogin(users);
					test.mainTest(idList, quizManagement, users);
					break;
				case 5:
					System.out.print("BYE BYE!!\n");
					break;
				}

			} else {// khi co user tham gia
				// user nhập lựa chọn
				menuUser(users.getDs().get(idList).getTen());
				do {
					choose = CauHinh.sc.nextLine();
					if (CauHinh.checking(choose, "1", "4")) {
						System.out.print("CHOOSE AGAIN! => ");
					}
				} while (CauHinh.checking(choose, "1", "4"));
				// khi user chon xong lua chon
				switch (Integer.parseInt(choose)) {
				case 1:
					quizManagement.mainQuestion();
					break;
				case 2:
					test.mainTest(idList, quizManagement, users);
					break;
				case 3:
					//Nhap thang nam

					int[] thangNam = new int[2];
					boolean inputValid = false;

					while (!inputValid) {
						try {
							System.out.print("Nhap thang muon thong ke (1-12): ");
							int thang = Integer.parseInt(CauHinh.sc.nextLine());

							System.out.print("Nhap nam muon thong ke: ");
							int nam = Integer.parseInt(CauHinh.sc.nextLine());
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(users.getDs().get(idList).getNgayDangKy());
							int namDangKy = calendar.get(Calendar.YEAR);
							if (thang >= 1 && thang <= 12 && nam >= namDangKy) {
								thangNam[0] = thang;
								thangNam[1] = nam;
								inputValid = true;
							} else {
								System.out.println("Thoi gian nhap vao khong hop le. Vui long nhap lai.");
							}
						} catch (NumberFormatException e) {
							System.out.println("Thoi gian nhap vao khong hop le. Vui long nhap lai.");
						}
					}
					users.getDs().get(idList).thongKeKetQuaTheoThang(thangNam[0], thangNam[1]);
					break;
				case 4:
					idList = -1;
					System.out.println("SEE YOU AGAIN <3");
					break;
				}
			}

		} while (!choose.equals("5"));
		users.exportHocSinh(CauHinh.fileHocSinh);
	}

	// lay id trong list hoc sinh
	public static int signinAndLogin(QuanLyHocSinh ds) throws FileNotFoundException {
		String chon, user, pass;
		int idList;
		// hiển thị các lựa chọn cho user
		System.out.print("[1].Dang nhap\n[2].Dang ky\n=> ");
		do {
			chon = CauHinh.sc.nextLine();
			if (CauHinh.checking(chon, "1", "2")) {
				System.out.print("Chon lai =>");
			}
		} while (CauHinh.checking(chon, "1", "2"));

		if (chon.equals("1")) {
			do {
				System.out.print("Ma dang nhap: ");
				user = CauHinh.sc.nextLine();
				System.out.print("Mat khau: ");
				pass = CauHinh.sc.nextLine();
				idList = ds.getIdlist(user, pass);
				if (idList != -1) {
					return idList; // tra lai id khi tim thay
				}
				System.out.println("Khong tim thay tai khoan nao!");
			} while (true);

		} else {
			HocSinh hs = new HocSinh();
			hs.nhapHocSinh();
			System.out.println("Ma dang nhap lan toi cua ban la: " + hs.getMaHocSinh());
			ds.addUser(hs);
			ds.exportHocSinh("HocSinh.txt");
			return ds.getDs().size() - 1;// lay id moi tao xong
		}
	}

	public static void menu() {
		System.out.println("==========================================================");
		System.out.println("||       +++ CHUONG TRINH LUYEN TAP TIENG ANH +++       ||");
		System.out.println("==========================================================");
		System.out.println("||  1. Dang ky/Dang nhap                                ||");
		System.out.println("||  2. Quan ly nguoi hoc                                ||");
		System.out.println("||  3. Quan ly cau hoi                                  ||");
		System.out.println("||  4. Luyen tap                                        ||");
		System.out.println("||  5. Thoat chuong trinh                               ||");
		System.out.println("==========================================================");
		System.out.print("ENTER => ");
	}

	public static void menuUser(String name) {
		System.out.printf("==========================================================    (HELLO %s)\n",name);
		System.out.println("||       +++ CHUONG TRINH LUYEN TAP TIENG ANH +++       ||     /\\_____/\\");
		System.out.println("==========================================================    /  o   o  \\");
		System.out.println("||  1. Quan ly cau hoi                                  ||   ( ==  ^  == )");
		System.out.println("||  2. Luyen tap                                        ||    )         (");
		System.out.println("||  3. Thong ke ket qua hoc tap                         ||   (           )");
		System.out.println("||  4. Dang xuat                                        ||  ( (  ) * (  ) )");
		System.out.println("========================================================== (__(__)___(__)__)");
		System.out.print("ENTER => ");
	}
}
