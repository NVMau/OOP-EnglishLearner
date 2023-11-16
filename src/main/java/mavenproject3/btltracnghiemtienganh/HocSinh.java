/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nmau4
 */
public class HocSinh {

	//khai bao bao cac bien
	private static int dem;
	private String ten, diaChi, password;
	private int maHocSinh = ++dem;
	private boolean gioiTinh;
	private Date ngaySinh, ngayDangKy;
	private List<TestResult> testResults;

	public HocSinh() {
		this.testResults = new ArrayList<>();
	}

	// khoi tao new hoc sinh
	//Dùng kiểu date
	public HocSinh(String ten, String diaChi, int maHocSinh, boolean gioiTinh, Date ngaySinh, Date ngayDangKy) {
		this.ten = ten;
		this.diaChi = diaChi;
		this.maHocSinh = maHocSinh;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayDangKy = ngayDangKy;
		this.testResults = new ArrayList<>();
	}

	//Dùng kiểu string cho  Date
	public HocSinh(String ten, String diaChi, boolean gioiTinh, String ngaySinh, String ngayDangKy) throws ParseException {
		this.ten = ten;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = CauHinh.dateFormat.parse(ngaySinh);
		this.ngayDangKy = CauHinh.dateFormat.parse(ngayDangKy);
		this.testResults = new ArrayList<>();

	}

	// Thêm người dùng mới từ bàn phím
	public void nhapHocSinh() {
		System.out.print("Nhap ten: ");
		this.ten = CauHinh.sc.nextLine();

		System.out.print("Nhap dia chi: ");
		this.diaChi = CauHinh.sc.nextLine();

		System.out.print("Nhap gioi tinh:(Nam/Nu): ");
		String gioiTinhInput = CauHinh.sc.nextLine();
		this.gioiTinh = gioiTinhInput.equalsIgnoreCase("Nam");

		System.out.print("Nhap ngay sinh hoc vien (dd/MM/yyyy): ");
		String ngaySinhInput = CauHinh.sc.nextLine();

		try {
			this.ngaySinh = CauHinh.dateFormat.parse(ngaySinhInput);
		} catch (ParseException ex) {
			Logger.getLogger(HocSinh.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.ngayDangKy = CauHinh.toDay;
		System.out.print("Nhap mat khau: ");
		this.password = CauHinh.sc.nextLine();
	}

	// Lưu bài kiểm tra vào danh sách những bài kiểm tra 
	public void addTestResults(TestResult testResult) {
		getTestResults().add(testResult);
	}

	//Thong ke kết quả học tập
	public String getTestResultsString() {
		StringBuilder sb = new StringBuilder();
		for (TestResult tr : testResults) {
			sb.append(CauHinh.dateFormat.format(tr.getDate())).append(",");
			sb.append(tr.getScore()).append(",");
			for (int i = 0; i < tr.getAnsweredQuestions().size(); i++) {
				sb.append(tr.getAnsweredQuestions().get(i));
				if (i < tr.getAnsweredQuestions().size() - 1) {
					sb.append(",");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
// lấy dữ liệu bài test từ file

	public void addTestResultFromString(String testResultString) {
		String[] parts = testResultString.split(",");
		try {
			Date date = CauHinh.dateFormat.parse(parts[0]);
			double score = Double.parseDouble(parts[1]);
			TestResult tr = new TestResult(score, date);
			for (int i = 2; i < parts.length; i++) {
				tr.addAnsweredQuestion(Integer.parseInt(parts[i]));
			}
			testResults.add(tr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	//Thong ke kqht
	public void thongKeKetQuaTheoThang(int thang, int nam) {
		int soLanLamKiemTra = 0;
		double tongDiem = 0;
		double diemTrungBinh;

		Calendar calendar = Calendar.getInstance();
		System.out.printf("Thang %d nam %d:\n", thang, nam);

		for (TestResult testResult : testResults) {
			calendar.setTime(testResult.getDate());
			int thangTest = calendar.get(Calendar.MONTH) + 1;

			int namTest = calendar.get(Calendar.YEAR);

			if (thangTest == thang && namTest == nam) {
				System.out.print("\tNgay: " + CauHinh.dateFormat.format(testResult.getDate()));
				System.out.printf("\tDiem: %.2f\n", testResult.getScore());
				soLanLamKiemTra++;
				tongDiem += testResult.getScore();
			}
		}

		if (soLanLamKiemTra > 0) {
			diemTrungBinh = tongDiem / soLanLamKiemTra;
		} else {
			diemTrungBinh = 0;
		}

		System.out.println("So lan lam kiem tra: " + soLanLamKiemTra);
		System.out.printf("Diem trung binh: %.2f\n", diemTrungBinh);
	}

	//Hiển thị chi tiết người dùng
	public void hienThi() {
		String gioiTinhStr = gioiTinh ? "Nam" : "Nu";
		System.out.println("Ten: " + ten);
		System.out.println("Dia chi: " + diaChi);
		System.out.println("Ma hoc sinh: " + maHocSinh);
		System.out.println("Gioi tinh: " + gioiTinhStr);
		System.out.printf("Ngay Sinh: %s\n", CauHinh.dateFormat.format(this.ngaySinh));
		System.out.printf("Ngay vao hoc: %s\n", CauHinh.dateFormat.format(this.ngayDangKy));
		System.out.println("Danh sach diem:");
		for (TestResult testResult : testResults) {
			System.out.print("\tNgay: " + CauHinh.dateFormat.format(testResult.getDate()));
			System.out.printf("\tDiem: %.2f\n", testResult.getScore());
		}
		System.out.println("");
	}

	// Getter and setter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTen() {
		return ten;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public int getMaHocSinh() {
		return maHocSinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public Date getNgayDangKy() {
		return ngayDangKy;
	}

	// Setters
	public void setTen(String ten) {
		this.ten = ten;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public void setMaHocSinh(int maHocSinh) {
		this.maHocSinh = maHocSinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	/**
	 * @return the testResults
	 */
	public List<TestResult> getTestResults() {
		return testResults;
	}

	/**
	 * @param testResults the testResults to set
	 */
	public void setTestResults(List<TestResult> testResults) {
		this.testResults = testResults;
	}
	/**
	 * @return the TestResult
	 */

	/**
	 * @return the TestResults
	 */
}
