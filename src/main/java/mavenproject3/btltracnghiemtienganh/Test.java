/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.awt.Choice;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class Test {

	// lam cau hoi multiple choice 
	public void startQuizMultipleChoice(HocSinh hocSinh, List<CauHoi> quiz) {
		// huong dan user cach lam bai
		huongDan();
		// cac bien can thiet
		String n;
		List<Boolean> kq = new ArrayList<>(); // khai bao tạm dùng để lưu các câu trả lời đúng
		int count = 0;// dem cau hoi lam dung
		String chose = null;
		Double score;// dung de tinh diem
		Random random = new Random();
		TestResult test = new TestResult();
		List<Integer> nums = new ArrayList<>();// sẽ chứa các id list học sinh chưa làm
		// vòng lặp sẽ lấy tất cả id câu hỏi có trong mul
		for (int i = 1; i < quiz.size(); i++) // nap het tat ca id co trong list
		{
			if(quiz.get(i).getMaDangCauHoi() == 1)
				nums.add(i);// lay vi tri trong list
		}
		// gộp hết các câu hỏi
		for (int i = 0; i < hocSinh.getTestResults().size(); i++)// vòng lặp sẽ đi từng cái id đa test
		{
			nums.addAll(hocSinh.getTestResults().get(i).getAnsweredQuestions());
		}
		// loai tru cau trung
		List<Integer> num = new ArrayList<>();
		for (int i : nums) {
            int countNum = Collections.frequency(nums, i);
            if(countNum == 1)
                num.add(i);
        }
		
		if (num.size() != 0) {
			// nhap so luong muon lam 
			System.out.print("nhap so luong cau hoi muon lam: ");
			do {
				n = CauHinh.sc.nextLine();
				if (n.length() == 0) {
					System.out.print("Chon lai: ");
					continue;
				}
				if (CauHinh.checking(n, "1", Integer.toString(num.size()))) {
					if (CauHinh.isNumber(n)) {
						if (Integer.parseInt(n) > num.size()) {
							System.out.print("He thong khong du cau hoi!");
						}
					}
					System.out.print("Chon lai: ");
				}
			} while (CauHinh.checking(n, "1", Integer.toString(num.size())));

			// tron cau hoi
			for (int i = num.size() - 1; i > 0; i--) {
				int index = random.nextInt(i + 1);
				int temp = num.get(index);
				num.set(index, num.get(i));
				num.set(i, temp);
			}
			//--------------------------- vao lam bai---------------------------
			boolean conti = false;
			for (int i = 0; i < Integer.parseInt(n); i++) {

//				test.addAnsweredQuestion(quiz.get(num.get(i)).getId());
				test.addAnsweredQuestion(num.get(i));
//			sẽ đưa ra các id câu hỏi dùng để chứa các id cau hỏi mà học sinh đang làm

				System.out.printf("Cau hoi thu [%d]\n", i + 1);
				// hien thi cau hoi 
				quiz.get(num.get(i)).showLess();

				System.out.print("Chon phuong an: ");
				// chon phuong an
				do {
					chose = CauHinh.sc.nextLine();
					// kiem tra dữ liệu nhập
					if (CauHinh.checkingChose(chose, quiz.get(num.get(i)).getPa().size())) {
						System.out.print("Chon lai!: ");
						conti = true;
					} else {
						conti = false;
						if (CauHinh.iscorect(chose, Integer.toString(quiz.get(i).getPhuongAnDung()))) {
							count++;
							// add ket qua
							kq.add(true);
						} else {
							// add ket qua
							kq.add(false);
						}

					}
				} while (conti);
			}
			// xuat lai ket qua
			System.out.println("-------------------------------------------------- KET QUA BAI LAM --------------------------------------------------");
			for (int i = 0; i < Integer.parseInt(n); i++) {
				System.out.printf("Cau[%d]", i + 1);
				quiz.get(num.get(i)).ShowKQ();
				if (kq.get(i)) {
					System.out.println("Lam Dung");
				} else {
					System.out.println("Lam Sai");
				}
			}
			//xuat ket qua
			System.out.printf("Ket qua: %d/%d \t\n", count, Integer.parseInt(n));
			score = (double) count / Integer.parseInt(n);
			System.out.printf("Score: %.1f\n", score * 10);
			// luu ket qua cho hoc sinh
			test.setDate(CauHinh.toDay);
			test.setScore(score * 10);
			hocSinh.addTestResults(test);
		} else {
			System.out.println("Khong con cau hoi nao danh cho ban!");
		}
	}

	// lam cau hoi conversation
	public void startQuiz(HocSinh hocSinh, List<CauHoi> quiz,int dang) {
		// huong dan user cach lam bai
		huongDan();
		// bien
		TestResult test = new TestResult();
		Level muc = Level.EASY;
		int count = 0;
		double score;
		boolean conti = false;
		List<Boolean> kq = new ArrayList<>();// dùng để lưu từng kết quá làm
		String chose = null, level = null;
		List<Integer> num = new ArrayList<>();// dùng để chứa các id cau hỏi
		// show muc do
		System.out.print("[1].De\n[2].Trung Binh\n[3].Kho\n");
		// loc chon muc do
		System.out.print("Chon muc do muon lam: ");
		do {
			level = CauHinh.sc.nextLine();
			if (CauHinh.checking(level, "1", "3")) {
				System.out.print("Chon lai => ");
			}
		} while (CauHinh.checking(level, "1", "3"));
		// lấy id mức độ
		if (level.equals("2")) {
			muc = muc.MEDIUM;
		} else if (level.equals("3")) {
			muc = muc.HARD;
		} else {
			muc = muc.EASY;
		}

		// lấy các id theo mức độ đã chọn
		for (int i = 0; i < quiz.size(); i++) {
			if (quiz.get(i).getMaDangCauHoi() == dang && quiz.get(i).getLev() == muc) {
				num.add(i);
			}
		}

		if (num.size() != 0) {// neu khong có câu nào
			Collections.shuffle(num);// ham random
			// lay id so 0
			int idquiz = num.get(0);
			//--------------------------- vao lam bai---------------------------
			// xuat noi dung chinh
			System.out.println(quiz.get(idquiz).getNoiDungCauHoi());
			for (int i = 0; i < quiz.get(idquiz).getDaQuiz().size(); i++) {
				quiz.get(idquiz).getDaQuiz().get(i).showLess();
				// bat dau chon phuong an
				System.out.print("Chon phuong an: ");
				do {

					chose = CauHinh.sc.nextLine();
					if (CauHinh.checkingChose(chose, quiz.get(idquiz).getDaQuiz().get(i).getPa().size())) {
						System.out.print("Chon lai!: ");
						conti = true;
					} else {
						conti = false;

						if (CauHinh.iscorect(chose, "4")) {
							kq.add(true);
							count++;
						} else {
							kq.add(false);
						}
					}
				} while (conti);
			}
			// xuat ket qua va them
			System.out.println("-------------------------------------------------- KET QUA BAI LAM --------------------------------------------------");
			System.out.println(quiz.get(idquiz).getNoiDungCauHoi());
			//xuất từng phương án và hiện đúng sai
			for (int i = 0; i < quiz.get(idquiz).getDaQuiz().size(); i++) {
				quiz.get(idquiz).getDaQuiz().get(i).ShowKQ();
				if (kq.get(i)) {
					System.out.println("Lam Dung");
				} else {
					System.out.println("Lam Sai");
				}
			}

			System.out.printf("Ket qua: %d/%d \t\n", count, quiz.get(idquiz).getDaQuiz().size());
			score = (double) count / quiz.get(idquiz).getDaQuiz().size();
			System.out.printf("Score: %.1f\n", score * 10);
			// luu ket qua cho hoc sinh
			test.setDate(CauHinh.toDay);
			test.setScore(score * 10);
			hocSinh.addTestResults(test);
		} else {
			System.out.println("He thong chua co cau hoi ve muc do nay!");
		}

	}

	public static void menuTest() {
		System.out.println("==========================================================");
		System.out.println("||                   +++ LUYEN TAP +++                  ||");
		System.out.println("==========================================================");
		System.out.println("||  1. Multiple Choice                                  ||");
		System.out.println("||  2. Incomplete                                       ||");
		System.out.println("||  3. Conversation                                     ||");
		System.out.println("||  4. Tro lai                                          ||");
		System.out.println("==========================================================");
		System.out.print("ENTER => ");
	}

	public void mainTest(int id, QuanlyCauHoi quiz, QuanLyHocSinh hs) throws FileNotFoundException {
		String chon = null;
		do {
			menuTest();
			// nhap lua chon
			chon = CauHinh.sc.nextLine();
			if (CauHinh.checking(chon, "1", "4")) {
				System.out.println("Chon lai => ");
			}
			switch (Integer.parseInt(chon)) {
			case 1:
				startQuizMultipleChoice(hs.getDs().get(id), quiz.getDanhSach());
				break;
			case 2:
				startQuiz(hs.getDs().get(id), quiz.getDanhSach(),3);
				break;
			case 3:
				startQuiz(hs.getDs().get(id), quiz.getDanhSach(),2);
				break;
			case 4:
				hs.exportHocSinh("HocSinh.txt");
				break;
			}
		} while (!chon.equals("4"));
	}

	public void huongDan() {
		// huong dan user cach lam bai
		System.out.println("**** Chu y: hoc sinh nhap phuong an bang cach chon cac phim 1, 2, 3.. hoac A, B, C ****");
		System.out.println("       ___\n      /    )\n    <)__ *//\\__/\\\n     / /_ (_*00*_)\n    (       U U   )\noOooOoOooOoooOOooOOO");
	}
}
