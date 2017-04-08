package com.da.na;

import java.io.Serializable;
import java.util.List;

public class DanaComProtocol implements Serializable {
	public static final long serialVersionUID = 39491L;
	
	private int p_cmd;
	private List<String> memComIdList;
	private MemComVo memComVo;
	private List<ProClassVo> class_list;
	private List<MakerVo> mkr_list;
	private List<ProductVo> pro_list;
	private VirBillVo virBillVo;
	private List<VirBillVo> vir_list;
	private List<VblDetVo> vdt_list;
	private List<VbbVo> vbb_list;
	private List<VbsVo> vbs_list;
	private String pcl_no;
	private String pcl_name;
	private int vbl_no;
	private int tot_price;
	private String r_msg;
	private int r_cmd;
	
	public DanaComProtocol(){
	}
	public DanaComProtocol(int p_cmd, List<String> memComIdList, MemComVo memComVo) {
		super();
		this.p_cmd = p_cmd;
		this.memComIdList = memComIdList;
		this.memComVo = memComVo;
	}

	public int getP_cmd() {
		return p_cmd;
	}

	public void setP_cmd(int p_cmd) {
		this.p_cmd = p_cmd;
	}

	public List<String> getMemComIdList() {
		return memComIdList;
	}

	public void setMemComIdList(List<String> memComIdList) {
		this.memComIdList = memComIdList;
	}

	public MemComVo getMemComVo() {
		return memComVo;
	}

	public void setMemComVo(MemComVo memComVo) {
		this.memComVo = memComVo;
	}
	public List<ProClassVo> getClass_list() {
		return class_list;
	}
	public void setClass_list(List<ProClassVo> class_list) {
		this.class_list = class_list;
	}
	public List<MakerVo> getMkr_list() {
		return mkr_list;
	}
	public void setMkr_list(List<MakerVo> mkr_list) {
		this.mkr_list = mkr_list;
	}
	public String getPcl_no() {
		return pcl_no;
	}
	public void setPcl_no(String pcl_no) {
		this.pcl_no = pcl_no;
	}
	public String getPcl_name() {
		return pcl_name;
	}
	public void setPcl_name(String pcl_name) {
		this.pcl_name = pcl_name;
	}
	public List<ProductVo> getPro_list() {
		return pro_list;
	}
	public void setPro_list(List<ProductVo> pro_list) {
		this.pro_list = pro_list;
	}
	public VirBillVo getVirBillVo() {
		return virBillVo;
	}
	public void setVirBillVo(VirBillVo virBillVo) {
		this.virBillVo = virBillVo;
	}
	public List<VblDetVo> getVdt_list() {
		return vdt_list;
	}
	public void setVdt_list(List<VblDetVo> vdt_list) {
		this.vdt_list = vdt_list;
	}
	public String getR_msg() {
		return r_msg;
	}
	public void setR_msg(String r_msg) {
		this.r_msg = r_msg;
	}
	public int getR_cmd() {
		return r_cmd;
	}
	public void setR_cmd(int r_cmd) {
		this.r_cmd = r_cmd;
	}
	public List<VirBillVo> getVir_list() {
		return vir_list;
	}
	public void setVir_list(List<VirBillVo> vir_list) {
		this.vir_list = vir_list;
	}
	public int getVbl_no() {
		return vbl_no;
	}
	public void setVbl_no(int vbl_no) {
		this.vbl_no = vbl_no;
	}
	public int getTot_price() {
		return tot_price;
	}
	public void setTot_price(int tot_price) {
		this.tot_price = tot_price;
	}
	public List<VbbVo> getVbb_list() {
		return vbb_list;
	}
	public void setVbb_list(List<VbbVo> vbb_list) {
		this.vbb_list = vbb_list;
	}
	public List<VbsVo> getVbs_list() {
		return vbs_list;
	}
	public void setVbs_list(List<VbsVo> vbs_list) {
		this.vbs_list = vbs_list;
	}
	
}
