/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

/**
 *
 * @author nmau4
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuanLyHocSinh {

	//Danh sách hoc sinh
	private List<HocSinh> ds = new ArrayList<>();

	//constructor
	public QuanLyHocSinh() {
	}

	//nhâp học sinh vào danh sách từ file 
	public void importHocSinh(String tapTin) throws ParseException, FileNotFoundException {
		File f = new File(tapTin);
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNext()) {
				HocSinh h = new HocSinh();
				h.setTen(sc.nextLine());
				h.setDiaChi(sc.nextLine());
				int maHocSinh = Integer.parseInt(sc.nextLine());
				h.setMaHocSinh(maHocSinh);
				h.setGioiTinh(Boolean.parseBoolean(sc.nextLine()));
				h.setPassword(sc.nextLine());
				Date ngaySinh = CauHinh.dateFormat.parse(sc.nextLine());
				h.setNgaySinh(ngaySinh);
				Date ngayDangKy = CauHinh.dateFormat.parse(sc.nextLine());
				h.setNgayDangKy(ngayDangKy);
				int testResultsCount = Integer.parseInt(sc.nextLine());
				for (int i = 0; i < testResultsCount; i++) {
					h.addTestResultFromString(sc.nextLine());
				}
				ds.add(h);
			}
		}
	}

	// Xuất danh sách học sinh ra tập tin text
	public void exportHocSinh(String tapTin) throws FileNotFoundException {
		File f = new File(tapTin);
		try (PrintWriter pw = new PrintWriter(f)) {
			for (HocSinh h : ds) {
				pw.println(h.getTen());
				pw.println(h.getDiaChi());
				pw.println(h.getMaHocSinh());
				pw.println(h.isGioiTinh());
				pw.println(h.getPassword());
				pw.println(CauHinh.dateFormat.format(h.getNgaySinh()));
				pw.println(CauHinh.dateFormat.format(h.getNgayDangKy()));
				pw.println(h.getTestResults().size());
				pw.print(h.getTestResultsString());
			}
		}
	}
//Thêm phương thức getHocSinhById để tìm học sinh theo mã học sinh

	public HocSinh getHocSinhById(int maHocSinh) {
		for (HocSinh h : ds) {
			if (h.getMaHocSinh() == maHocSinh) {
				return h;
			}
		}
		return null;
	}

	// them mot hoc sinh vao danh sach
	public void addUser(HocSinh h) {
		this.ds.add(h);
	}

	//Thêm nhiều học sinh 
	public void nhapHocSinh(int n) {
		for (int i = 0; i < n; i++) {
			System.out.printf("=== Nhap hoc vien thu === %d\n", i + 1);
			HocSinh h = new HocSinh();
			h.nhapHocSinh();
			this.getDs().add(h);

		}
	}

	// Tra cứu người học theo họ tên
	public List<HocSinh> traCuuTheoTen(String ten) {
		return ds.stream()
			.filter(h -> h.getTen().equalsIgnoreCase(ten))
			.collect(Collectors.toList());
	}

	// Tra cứu người học theo giới tính
	public List<HocSinh> traCuuTheoGioiTinh(boolean gioiTinh) {
		return ds.stream()
			.filter(h -> h.isGioiTinh() == gioiTinh)
			.collect(Collectors.toList());
	}

	// Tra cứu người học theo ngày sinh
	public List<HocSinh> traCuuTheoNgaySinh(Date ngaySinh) {
		return ds.stream()
			.filter(h -> h.getNgaySinh().equals(ngaySinh))
			.collect(Collectors.toList());
	}

	// Tra cứu người học theo quê quán
	public List<HocSinh> traCuuTheoQueQuan(String queQuan) {
		return ds.stream()
			.filter(h -> h.getDiaChi().equalsIgnoreCase(queQuan))
			.collect(Collectors.toList());
	}

	// Cập nhật người học
	public void capNhatHocSinh(int maHocSinh, HocSinh h) {
		for (HocSinh hs : ds) {
			if (hs.getMaHocSinh() == maHocSinh) {
				hs.setTen(h.getTen());
				hs.setDiaChi(h.getDiaChi());
				hs.setGioiTinh(h.isGioiTinh());
				hs.setPassword(h.getPassword());
				hs.setNgaySinh(h.getNgaySinh());
				hs.setNgayDangKy(h.getNgayDangKy());
				hs.setTestResults(h.getTestResults());
				break;
			}
		}
	}

	// Xoá người học
	public void xoaHocSinh(int maHocSinh) {
		ds.removeIf(h -> h.getMaHocSinh() == maHocSinh);
	}
	//menu QLHocSinh

	public void mainQLHocSinh() throws IOException {
		String choose;
		do {
			System.out.println("==========================================================");
			System.out.println("||                +++ QUAN LY NGUOI DUNG +++            || ");
			System.out.println("==========================================================");
			System.out.println("||[1].Xem danh sach nguoi hoc                           ||");
			System.out.println("||[2].Tra cuu nguoi hoc                                 ||");
			System.out.println("||[3].Cap nhat thong tin nguoi hoc                      ||");
			System.out.println("||[4].Xoa nguoi hoc                                     ||");
			System.out.println("||[5].Quay lai Menu chinh                               ||");
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
				System.out.println("Danh sach nguoi hoc: ");
				this.showAllUser();
				break;
			case 2:
				mainTraCuuHS();
				break;
			case 3:
				System.out.println("Nhap ma hoc sinh can cap nhat: ");
				int maHocSinh = Integer.parseInt(CauHinh.sc.nextLine());
				HocSinh hocSinh = getHocSinhById(maHocSinh);
				if (hocSinh == null) {
					System.out.println("Khong tim thay hoc sinh co ma hoc sinh: " + maHocSinh);
				} else {
					System.out.println("Thong tin hoc sinh can cap nhat: ");
					hocSinh.hienThi();
					System.out.println("Nhap thong tin hoc sinh moi: ");
					HocSinh hocSinhMoi = new HocSinh();
					hocSinhMoi.nhapHocSinh();
					hocSinhMoi.setTestResults(hocSinh.getTestResults());
					capNhatHocSinh(maHocSinh, hocSinhMoi);
					System.out.println("Cap nhat thanh cong!");
					this.exportHocSinh("src/main/resources/HocSinh.txt");
				}
				break;
			case 4:
				System.out.println("Nhap ma hoc sinh can xoa: ");
				int maHocSinhXoa = Integer.parseInt(CauHinh.sc.nextLine());
				Boolean checkXoa = false;
				for (HocSinh hs : ds) {
					if (hs.getMaHocSinh() == maHocSinhXoa) {
						this.xoaHocSinh(hs.getMaHocSinh());
						System.out.println("\tXoa Thanh cong!");
						this.exportHocSinh("src/main/resources/HocSinh.txt");
						checkXoa = true;
						break;
					}
				}
				if (!checkXoa) {
					System.out.println("\tKhong tim thay hoc sinh can xoa!");
					break;
				}

			}
		} while (!choose.equals("5"));
	}

	public void mainTraCuuHS() throws IOException {
		String choose;
		do {
			System.out.println("==========================================================");
			System.out.println("||                +++ TRA CUU HOC SINH +++                ||");
			System.out.println("==========================================================");
			System.out.println("|| [1].Tra cuu theo ho ten                                ||");
			System.out.println("|| [2].Tra cuu theo gioi tinh                             ||");
			System.out.println("|| [3].Tra cuu theo ngay sinh                             ||");
			System.out.println("|| [4].Tra cuu theo que quan                              ||");
			System.out.println("|| [5].Quay lai Menu chinh                                ||");
			System.out.println("==========================================================");
			System.out.print("Enter => ");
			do {// kiem tra lua chon
				choose = CauHinh.sc.nextLine();
				if (CauHinh.checking(choose, "1", "5")) {
					System.out.print("Again => ");
				}
			} while (CauHinh.checking(choose, "1", "5"));

			List<HocSinh> ketQuaTraCuu;

			switch (Integer.parseInt(choose)) {
			case 1:
				System.out.println("Nhap ten can tra cuu: ");
				String ten = CauHinh.sc.nextLine();
				ketQuaTraCuu = traCuuTheoTen(ten);
				System.out.println("Ket qua tra cuu theo ten: ");
				if (ketQuaTraCuu.isEmpty()) {
					System.out.println("Khong tim thay hoc sinh!");
				} else {
					ketQuaTraCuu.forEach(h -> h.hienThi());
				}
				break;
			case 2:
				System.out.print("Nhap gioi tinh (Nam/Nu): ");
				String gioiTinhString = CauHinh.sc.nextLine();
				boolean gioiTinh = parseGioiTinh(gioiTinhString);
				ketQuaTraCuu = traCuuTheoGioiTinh(gioiTinh);
				System.out.println("Ket qua tra cuu theo gioi tinh: ");
				if (ketQuaTraCuu.isEmpty()) {
					System.out.println("\tKhong tim thay hoc sinh!");
				} else {
					ketQuaTraCuu.forEach(h -> h.hienThi());
				}
				break;
			case 3:
				System.out.println("Nhap ngay sinh can tra cuu (dd/MM/yyyy): ");
				String ngaySinhStr = CauHinh.sc.nextLine();
				try {
					Date ngaySinh = CauHinh.dateFormat.parse(ngaySinhStr);
					ketQuaTraCuu = traCuuTheoNgaySinh(ngaySinh);
					System.out.println("Ket qua tra cuu theo ngay sinh: ");
					if (ketQuaTraCuu.isEmpty()) {
						System.out.println("\tKhong tim thay hoc sinh!");
					} else {
						ketQuaTraCuu.forEach(h -> h.hienThi());
					}
					break;
				} catch (ParseException e) {
					System.out.println("Ngay sinh nhap khong hop le!");
				}
				break;
			case 4:
				System.out.println("Nhap que quan can tra cuu: ");
				String queQuan = CauHinh.sc.nextLine();
				ketQuaTraCuu = traCuuTheoQueQuan(queQuan);
				System.out.println("Ket qua tra cuu theo que quan: ");
				if (ketQuaTraCuu.isEmpty()) {
					System.out.println("\tKhong tim thay hoc sinh!");
				} else {
					ketQuaTraCuu.forEach(h -> h.hienThi());
				}
				break;
			}

		} while (!choose.equals("5"));
	}

	public boolean parseGioiTinh(String gioiTinhString) {
		while (true) {
			if (gioiTinhString.equalsIgnoreCase("Nam")) {
				return true;
			} else if (gioiTinhString.equalsIgnoreCase("Nu")) {
				return false;
			} else {
				System.out.println("Gioi tinh vua nhap khong hop le! \nVui long nhap lai(Nam/Nu): ");
				gioiTinhString = CauHinh.sc.nextLine();// yêu cầu người dùng nhập lại giá trị
			}
		}
	}

	//Hiển thị nhiều người học
	public void showAllUser() {
		this.ds.forEach(h -> h.hienThi());
	}

	// tim kiem sinh vien de dang nhap
	public int getIdlist(String id, String pass) {
		id = id.trim();
		pass = pass.trim();
		for (int i = 0; i < this.ds.size(); i++) {
			if (Integer.toString(this.ds.get(i).getMaHocSinh()).equals(id) && this.ds.get(i).getPassword().equals(pass)) {
				return i;
			}
		}
		return -1;
	}

	// getter and setter
	/**
	 * @return the ds
	 */
	public List<HocSinh> getDs() {
		return ds;
	}

	/**
	 * @param ds the ds to set
	 */
	public void setDs(List<HocSinh> ds) {
		this.ds = ds;
	}

}
