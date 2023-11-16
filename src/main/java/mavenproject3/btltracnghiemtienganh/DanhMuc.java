/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class DanhMuc {
	private static HashMap<Integer, String> mapDanhMuc = new HashMap<>();
    {
        this.mapDanhMuc.put(100, "Danh tu");
        this.mapDanhMuc.put(101, "Tinh tu");
        this.mapDanhMuc.put(102, "Dong tu");
        this.mapDanhMuc.put(103, "Trang tu");
        this.mapDanhMuc.put(104, "Cau hoi duoi");
        this.mapDanhMuc.put(105, "Tong hop");
        // them trong tuong lai
    }
	
	public HashMap<Integer, String> getMapDanhMuc() {
		return mapDanhMuc;
	}

	public void setMapDanhMuc(HashMap<Integer, String> mapDanhMuc) {
		this.mapDanhMuc = mapDanhMuc;
	}
	public int getSoLuongDanhMuc() {
        return this.mapDanhMuc.size();
    }
	// xuat danh muc
    public void showDanhMuc() {
        int i = 1;
        for (String value : mapDanhMuc.values()) {
            System.out.println("[" + i + "] " + value);
            i++;
        }
    }
	
}
