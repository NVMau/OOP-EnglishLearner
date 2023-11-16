/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nmau4
 */
public class CauHinh {
    
        public static final String fileHocSinh = "src/main/resources/HocSinh.txt";
        public static final String fileCauHoi = "src/main/resources/CauHoi.txt";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static final Scanner sc = new Scanner(System.in);
	//Lưu ngày hiện tại để dùng chung
	public static final Date toDay = new Date();

	// kiem tra ki tu nhap vao 
	public static final boolean checking(String num, String min, String max) {
		// xoa khoang trang
		num = num.trim();
		// neu chuoi rong
		if (num.length() == 0) {
			return true;
		}
		// kiem tra tung ki tu so la chu cai thi tra ve false 
		for (int i = 0; i < num.length(); i++) {
			if (!(num.charAt(i) >= '0' && num.charAt(i) <= '9')) {// la ky tu chu tra ve true
				return true;
			}
		}
		// so sanh  trong pham vi tra ve false  neu khong nam trong pham vi do
		return !(Integer.parseInt(min) <= Integer.parseInt(num) && Integer.parseInt(max) >= Integer.parseInt(num));
	}

	// kiem tra phuong an nhap, an toan thi false
	public static final boolean checkingChose(String choose, int sizeOption) {
		char c = 'A', kyTu;
		//xoa khoang trang
		choose = choose.trim();
		// neu chuoi rong va nhieu hon 2 ki tu
		if (choose.length() == 0 || choose.length() >= 2) {
			return true;
		}
		// neu chuoi la so
		kyTu = choose.charAt(0);
		if (kyTu >= '1' && kyTu <= '9') // nếu nằm trong phạm vi sẽ trả về false
		{
			return !(Integer.parseInt(choose) >= 1 && Integer.parseInt(choose) <= sizeOption);
		}

		// neu chuoi la ki tu
		// dua len dang in hoa
		choose = choose.toUpperCase();
		kyTu = choose.charAt(0);
		for (int i = 1; i < sizeOption; i++) {
			c++;
		}

		// neu nam trong A va D..Z thi sẽ trả về false
		return !('A' <= kyTu && kyTu <= c);
	}

	// kiem tra dung sai, nếu đúng trả về true 
	public static final boolean iscorect(String choose, String limit) {
		char c = 'A', kyTu;
		choose = choose.trim();
		kyTu = choose.charAt(0);
		// neu chuoi la so
		if (kyTu >= '1' && kyTu <= limit.charAt(0)) {
			return choose.equals(limit);// co the bi loi 
		}
		// neu la chuoi ku tu
		for (int i = 1; i < Integer.parseInt(limit); i++) {
			c++;
		}
		choose.toUpperCase();
		kyTu = choose.charAt(0);
		return (kyTu == c);
	}
	
	public static final boolean isNumber(String s){
		s.trim();
		if(s.length() == 0)
			return false;
		
		// kiem tra tung ki tu so la chu cai thi tra ve false 
		for (int i = 0; i < s.length(); i++) {
			if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {// la ky tu chu tra ve true
				return false;
			}
		}
		return true;
	}

}
