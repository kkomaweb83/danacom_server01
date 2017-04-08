package com.da.na;

import java.io.Serializable;

public class VbbVo implements Serializable {
	public static final long serialVersionUID = 39396L;
	
	// 공유 견적서
	private String vbb_no;
	private String vbb_title;
	private String vbb_content;
	private String vbb_mem_no;
	private String vbb_date;
	private String vbb_recomm;
	private String vbb_count;
	private String vbb_btr_answer;
	// 공유 견적서 한줄댓글
	private String vbr_no;
	private String vbr_content;
	private String vbr_rdate;
	private String vbr_vbb_no;
	private String vbr_mem_no;
	private String vbr_title;
	// 회원
	private String mem_id;
	private String mem_no;	
	
	public VbbVo() {
	}

	public String getVbb_no() {
		return vbb_no;
	}

	public void setVbb_no(String vbb_no) {
		this.vbb_no = vbb_no;
	}

	public String getVbb_title() {
		return vbb_title;
	}

	public void setVbb_title(String vbb_title) {
		this.vbb_title = vbb_title;
	}

	public String getVbb_content() {
		return vbb_content;
	}

	public void setVbb_content(String vbb_content) {
		this.vbb_content = vbb_content;
	}

	public String getVbb_mem_no() {
		return vbb_mem_no;
	}

	public void setVbb_mem_no(String vbb_mem_no) {
		this.vbb_mem_no = vbb_mem_no;
	}

	public String getVbb_date() {
		return vbb_date;
	}

	public void setVbb_date(String vbb_date) {
		this.vbb_date = vbb_date;
	}

	public String getVbb_recomm() {
		return vbb_recomm;
	}

	public void setVbb_recomm(String vbb_recomm) {
		this.vbb_recomm = vbb_recomm;
	}

	public String getVbb_count() {
		return vbb_count;
	}

	public void setVbb_count(String vbb_count) {
		this.vbb_count = vbb_count;
	}

	public String getVbb_btr_answer() {
		return vbb_btr_answer;
	}

	public void setVbb_btr_answer(String vbb_btr_answer) {
		this.vbb_btr_answer = vbb_btr_answer;
	}

	public String getVbr_no() {
		return vbr_no;
	}

	public void setVbr_no(String vbr_no) {
		this.vbr_no = vbr_no;
	}

	public String getVbr_content() {
		return vbr_content;
	}

	public void setVbr_content(String vbr_content) {
		this.vbr_content = vbr_content;
	}

	public String getVbr_rdate() {
		return vbr_rdate;
	}

	public void setVbr_rdate(String vbr_rdate) {
		this.vbr_rdate = vbr_rdate;
	}

	public String getVbr_vbb_no() {
		return vbr_vbb_no;
	}

	public void setVbr_vbb_no(String vbr_vbb_no) {
		this.vbr_vbb_no = vbr_vbb_no;
	}

	public String getVbr_mem_no() {
		return vbr_mem_no;
	}

	public void setVbr_mem_no(String vbr_mem_no) {
		this.vbr_mem_no = vbr_mem_no;
	}

	public String getVbr_title() {
		return vbr_title;
	}

	public void setVbr_title(String vbr_title) {
		this.vbr_title = vbr_title;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
}
