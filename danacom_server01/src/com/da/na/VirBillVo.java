package com.da.na;

import java.io.Serializable;

public class VirBillVo implements Serializable {
	public static final long serialVersionUID = 39394L;
	
	private int vbl_no;
	private int vbl_mem_no;
	private String vbl_bor_answer;
	private String vbl_title;
	private String vbl_date;
	
	public VirBillVo() {
	}

	public int getVbl_no() {
		return vbl_no;
	}

	public void setVbl_no(int vbl_no) {
		this.vbl_no = vbl_no;
	}

	public int getVbl_mem_no() {
		return vbl_mem_no;
	}

	public void setVbl_mem_no(int vbl_mem_no) {
		this.vbl_mem_no = vbl_mem_no;
	}

	public String getVbl_bor_answer() {
		return vbl_bor_answer;
	}

	public void setVbl_bor_answer(String vbl_bor_answer) {
		this.vbl_bor_answer = vbl_bor_answer;
	}

	public String getVbl_title() {
		return vbl_title;
	}

	public void setVbl_title(String vbl_title) {
		this.vbl_title = vbl_title;
	}

	public String getVbl_date() {
		return vbl_date;
	}

	public void setVbl_date(String vbl_date) {
		this.vbl_date = vbl_date;
	}
	
}
