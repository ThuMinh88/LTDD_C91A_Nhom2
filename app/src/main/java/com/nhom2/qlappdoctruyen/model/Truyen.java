package com.nhom2.qlappdoctruyen.model;

public class Truyen {
    private int ID;
    private String tenTruyen;
    private String NoiDung;
    private String Anh;
    private int ID_TK;

    public Truyen(int ID, String tenTruyen, String noiDung, String anh, int ID_TK) {
        this.ID = this.ID;
        this.tenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
