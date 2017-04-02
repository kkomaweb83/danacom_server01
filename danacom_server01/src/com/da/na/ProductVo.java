package com.da.na;

import java.io.Serializable;
import java.util.List;

public class ProductVo implements Serializable {
	public static final long serialVersionUID = 39393L;
	
	private int pro_no;
	private String pro_name;
	private int pro_realprice;
	private int pro_disprice;
	private String pro_regdate;
	private int pro_milege;
	private int pro_stockcount;
	private String pro_pcl_no;
	private int pro_mkr_no;
	
	private String psm_conent;
	private String pmg_file;
	private String pro_ch_price;
	private String pro_ch2_price;
	private String pcl_name;	
	private String mkr_name;	
	private String pdt_conent;	
	private String setSearch;	
	private String mkr_yes;
		
	private String[] step;
	
	private String[] pdt_step51_sy;
	private String[] mkr_no_sy;
	private String pmg_file_s1;
	private String pmg_file_s2;
	private String ppt_pro_name;
	private int pst_quantity;
	
	private List list;
	
	private int cis_no;
	private String cis_con;
	private String cis_date;
	private int cis_mem_no;
	private String cpn_name;
	private int cpn_price;
	private int cpn_minpc;
	private int cpn_dline;
	
	private int firstRow ;
	private int lastRow;
	private String page;
	private int proOrderCode;
	
	private String searchKey;
	private String searchValue;

	public ProductVo() {
	}

	public int getPro_no() {
		return pro_no;
	}

	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getPro_realprice() {
		return pro_realprice;
	}

	public void setPro_realprice(int pro_realprice) {
		this.pro_realprice = pro_realprice;
	}

	public int getPro_disprice() {
		return pro_disprice;
	}

	public void setPro_disprice(int pro_disprice) {
		this.pro_disprice = pro_disprice;
	}

	public String getPro_regdate() {
		return pro_regdate;
	}

	public void setPro_regdate(String pro_regdate) {
		this.pro_regdate = pro_regdate;
	}

	public int getPro_milege() {
		return pro_milege;
	}

	public void setPro_milege(int pro_milege) {
		this.pro_milege = pro_milege;
	}

	public int getPro_stockcount() {
		return pro_stockcount;
	}

	public void setPro_stockcount(int pro_stockcount) {
		this.pro_stockcount = pro_stockcount;
	}

	public String getPro_pcl_no() {
		return pro_pcl_no;
	}

	public void setPro_pcl_no(String pro_pcl_no) {
		this.pro_pcl_no = pro_pcl_no;
	}

	public int getPro_mkr_no() {
		return pro_mkr_no;
	}

	public void setPro_mkr_no(int pro_mkr_no) {
		this.pro_mkr_no = pro_mkr_no;
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

	public String getPro_ch_price() {
		return pro_ch_price;
	}

	public void setPro_ch_price(String pro_ch_price) {
		this.pro_ch_price = pro_ch_price;
	}

	public String getPro_ch2_price() {
		return pro_ch2_price;
	}

	public void setPro_ch2_price(String pro_ch2_price) {
		this.pro_ch2_price = pro_ch2_price;
	}

	public String getPcl_name() {
		return pcl_name;
	}

	public void setPcl_name(String pcl_name) {
		this.pcl_name = pcl_name;
	}

	public String getMkr_name() {
		return mkr_name;
	}

	public void setMkr_name(String mkr_name) {
		this.mkr_name = mkr_name;
	}

	public String getPdt_conent() {
		return pdt_conent;
	}

	public void setPdt_conent(String pdt_conent) {
		this.pdt_conent = pdt_conent;
	}

	public String getSetSearch() {
		return setSearch;
	}

	public void setSetSearch(String setSearch) {
		this.setSearch = setSearch;
	}

	public String getMkr_yes() {
		return mkr_yes;
	}

	public void setMkr_yes(String mkr_yes) {
		this.mkr_yes = mkr_yes;
	}

	public String[] getStep() {
		return step;
	}

	public void setStep(String[] step) {
		this.step = step;
	}

	public String[] getPdt_step51_sy() {
		return pdt_step51_sy;
	}

	public void setPdt_step51_sy(String[] pdt_step51_sy) {
		this.pdt_step51_sy = pdt_step51_sy;
	}

	public String[] getMkr_no_sy() {
		return mkr_no_sy;
	}

	public void setMkr_no_sy(String[] mkr_no_sy) {
		this.mkr_no_sy = mkr_no_sy;
	}

	public String getPmg_file_s1() {
		return pmg_file_s1;
	}

	public void setPmg_file_s1(String pmg_file_s1) {
		this.pmg_file_s1 = pmg_file_s1;
	}

	public String getPmg_file_s2() {
		return pmg_file_s2;
	}

	public void setPmg_file_s2(String pmg_file_s2) {
		this.pmg_file_s2 = pmg_file_s2;
	}

	public String getPpt_pro_name() {
		return ppt_pro_name;
	}

	public void setPpt_pro_name(String ppt_pro_name) {
		this.ppt_pro_name = ppt_pro_name;
	}

	public int getPst_quantity() {
		return pst_quantity;
	}

	public void setPst_quantity(int pst_quantity) {
		this.pst_quantity = pst_quantity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCis_no() {
		return cis_no;
	}

	public void setCis_no(int cis_no) {
		this.cis_no = cis_no;
	}

	public String getCis_con() {
		return cis_con;
	}

	public void setCis_con(String cis_con) {
		this.cis_con = cis_con;
	}

	public String getCis_date() {
		return cis_date;
	}

	public void setCis_date(String cis_date) {
		this.cis_date = cis_date;
	}

	public int getCis_mem_no() {
		return cis_mem_no;
	}

	public void setCis_mem_no(int cis_mem_no) {
		this.cis_mem_no = cis_mem_no;
	}

	public String getCpn_name() {
		return cpn_name;
	}

	public void setCpn_name(String cpn_name) {
		this.cpn_name = cpn_name;
	}

	public int getCpn_price() {
		return cpn_price;
	}

	public void setCpn_price(int cpn_price) {
		this.cpn_price = cpn_price;
	}

	public int getCpn_minpc() {
		return cpn_minpc;
	}

	public void setCpn_minpc(int cpn_minpc) {
		this.cpn_minpc = cpn_minpc;
	}

	public int getCpn_dline() {
		return cpn_dline;
	}

	public void setCpn_dline(int cpn_dline) {
		this.cpn_dline = cpn_dline;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getProOrderCode() {
		return proOrderCode;
	}

	public void setProOrderCode(int proOrderCode) {
		this.proOrderCode = proOrderCode;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
