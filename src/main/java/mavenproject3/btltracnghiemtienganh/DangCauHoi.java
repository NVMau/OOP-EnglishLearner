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
public class DangCauHoi {
	private static HashMap<Integer, String> mapDangCauHoi = new HashMap<>();

	{
		this.mapDangCauHoi.put(1, "MultipleChoice");
		this.mapDangCauHoi.put(2, "Conversation");
		this.mapDangCauHoi.put(3, "Incomplete");
	}

	public void setMapDangCauHoi(HashMap<Integer, String> mapDangCauHoi) {
		this.mapDangCauHoi = mapDangCauHoi;
	}

	public HashMap<Integer, String> getMapDangCauHoi() {
		return mapDangCauHoi;
	}
}
