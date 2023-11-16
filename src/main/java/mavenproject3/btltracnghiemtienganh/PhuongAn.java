/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

/**
 *
 * @author Admin
 */
public class PhuongAn {

    private String noiDungDapAn;
    // constractor

    public PhuongAn(String noiDungDapAn) {
        this.noiDungDapAn = noiDungDapAn;
    }

    public PhuongAn() {
    }

    // getter and setter
    public String getNoiDungDapAn() {
        return noiDungDapAn;
    }

    public void setNoiDungDapAn(String noiDungDapAn) {
        this.noiDungDapAn = noiDungDapAn;
    }

    // to string
    @Override
    public String toString() {
        return String.format("%s\n", this.noiDungDapAn);
    }
    // show

    public void show() {
        System.out.println(this.noiDungDapAn);
    }

    // add phuong an
    public void nhapPhuongAn() {
        this.setNoiDungDapAn(CauHinh.sc.nextLine());
    }
}
