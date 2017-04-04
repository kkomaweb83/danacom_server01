package com.da.na;

import java.io.Serializable;

public class VblDetVo implements Serializable {
	public static final long serialVersionUID = 39395L;
	
	private int vdt_no;
	private int vdt_vbl_no;
	private int vdt_quantity;
	private int vdt_pro_no;
	
	public VblDetVo() {
	}

	public int getVdt_no() {
		return vdt_no;
	}

	public void setVdt_no(int vdt_no) {
		this.vdt_no = vdt_no;
	}

	public int getVdt_vbl_no() {
		return vdt_vbl_no;
	}

	public void setVdt_vbl_no(int vdt_vbl_no) {
		this.vdt_vbl_no = vdt_vbl_no;
	}

	public int getVdt_quantity() {
		return vdt_quantity;
	}

	public void setVdt_quantity(int vdt_quantity) {
		this.vdt_quantity = vdt_quantity;
	}

	public int getVdt_pro_no() {
		return vdt_pro_no;
	}

	public void setVdt_pro_no(int vdt_pro_no) {
		this.vdt_pro_no = vdt_pro_no;
	}
	
}
