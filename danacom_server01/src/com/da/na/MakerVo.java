package com.da.na;

import java.io.Serializable;
import java.util.List;

public class MakerVo implements Serializable {
	public static final long serialVersionUID = 39394L;
	
	private int mkr_no;
	private String mkr_name;
	private String mkr_pcl_no;
	
	private String mkr_insert;
	private String pcl_name;
	private List ist;
	
	public MakerVo() {
	}

	public int getMkr_no() {
		return mkr_no;
	}

	public void setMkr_no(int mkr_no) {
		this.mkr_no = mkr_no;
	}

	public String getMkr_name() {
		return mkr_name;
	}

	public void setMkr_name(String mkr_name) {
		this.mkr_name = mkr_name;
	}

	public String getMkr_pcl_no() {
		return mkr_pcl_no;
	}

	public void setMkr_pcl_no(String mkr_pcl_no) {
		this.mkr_pcl_no = mkr_pcl_no;
	}

	public String getMkr_insert() {
		return mkr_insert;
	}

	public void setMkr_insert(String mkr_insert) {
		this.mkr_insert = mkr_insert;
	}

	public String getPcl_name() {
		return pcl_name;
	}

	public void setPcl_name(String pcl_name) {
		this.pcl_name = pcl_name;
	}

	public List getIst() {
		return ist;
	}

	public void setIst(List ist) {
		this.ist = ist;
	}
	
}
