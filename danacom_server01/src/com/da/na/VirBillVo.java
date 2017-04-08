package com.da.na;

import java.io.Serializable;

public class VirBillVo implements Serializable {
	public static final long serialVersionUID = 39394L;
	
	private int vbl_no;
	private int vbl_mem_no;
	private String vbl_bor_answer;
	private String vbl_title;
	private String vbl_date;
	private int vbb_no;
	private String vbb_content;
	
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

	public int getVbb_no() {
		return vbb_no;
	}

	public void setVbb_no(int vbb_no) {
		this.vbb_no = vbb_no;
	}

	public String getVbb_content() {
		return vbb_content;
	}

	public void setVbb_content(String vbb_content) {
		this.vbb_content = vbb_content;
	}
	
}
