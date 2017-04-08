package com.da.na;

import java.io.Serializable;

public class VbsVo implements Serializable {
	public static final long serialVersionUID = 39397L;
	
	private String vds_no;
	private String vds_quantity;
	private String pro_no;
	private String pro_name;
	private String pro_disprice;
	private String pro_milege;
	private String pro_regdate;
	private String mkr_name;
	private String pcl_name;
	private String pcl_no;
	private String psm_conent;
	private String pmg_file;
	
	public VbsVo() {
	}

	public String getVds_no() {
		return vds_no;
	}

	public void setVds_no(String vds_no) {
		this.vds_no = vds_no;
	}

	public String getVds_quantity() {
		return vds_quantity;
	}

	public void setVds_quantity(String vds_quantity) {
		this.vds_quantity = vds_quantity;
	}

	public String getPro_no() {
		return pro_no;
	}

	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_disprice() {
		return pro_disprice;
	}

	public void setPro_disprice(String pro_disprice) {
		this.pro_disprice = pro_disprice;
	}

	public String getPro_milege() {
		return pro_milege;
	}

	public void setPro_milege(String pro_milege) {
		this.pro_milege = pro_milege;
	}

	public String getPro_regdate() {
		return pro_regdate;
	}

	public void setPro_regdate(String pro_regdate) {
		this.pro_regdate = pro_regdate;
	}

	public String getMkr_name() {
		return mkr_name;
	}

	public void setMkr_name(String mkr_name) {
		this.mkr_name = mkr_name;
	}

	public String getPcl_name() {
		return pcl_name;
	}

	public void setPcl_name(String pcl_name) {
		this.pcl_name = pcl_name;
	}

	public String getPcl_no() {
		return pcl_no;
	}

	public void setPcl_no(String pcl_no) {
		this.pcl_no = pcl_no;
	}

	public String getPsm_conent() {
		return psm_conent;
	}

	public void setPsm_conent(String psm_conent) {
		this.psm_conent = psm_conent;
	}

	public String getPmg_file() {
		return pmg_file;
	}

	public void setPmg_file(String pmg_file) {
		this.pmg_file = pmg_file;
	}
	
}
