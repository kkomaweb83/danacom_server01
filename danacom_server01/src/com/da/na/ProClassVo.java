package com.da.na;

import java.io.Serializable;
import java.util.List;

public class ProClassVo implements Serializable {
	public static final long serialVersionUID = 39392L;

	private String pcl_no;
	private String pcl_name;
	private int pcl_step;
	private String pcl_basis;
	private String pcl_upperno;
	
	private String pa_pcl_name;
	
	private List<ProClassVo> pcl_list;
	private String pcl_old_upperno;
	private String pcl_next_no;
	private String pcl_next_name;
	
	private ProductVo proVO;
	private int ppt_no;
	
	public ProClassVo() {
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

	public int getPcl_step() {
		return pcl_step;
	}

	public void setPcl_step(int pcl_step) {
		this.pcl_step = pcl_step;
	}

	public String getPcl_basis() {
		return pcl_basis;
	}

	public void setPcl_basis(String pcl_basis) {
		this.pcl_basis = pcl_basis;
	}

	public String getPcl_upperno() {
		return pcl_upperno;
	}

	public void setPcl_upperno(String pcl_upperno) {
		this.pcl_upperno = pcl_upperno;
	}

	public String getPa_pcl_name() {
		return pa_pcl_name;
	}

	public void setPa_pcl_name(String pa_pcl_name) {
		this.pa_pcl_name = pa_pcl_name;
	}

	public List<ProClassVo> getPcl_list() {
		return pcl_list;
	}

	public void setPcl_list(List<ProClassVo> pcl_list) {
		this.pcl_list = pcl_list;
	}

	public String getPcl_old_upperno() {
		return pcl_old_upperno;
	}

	public void setPcl_old_upperno(String pcl_old_upperno) {
		this.pcl_old_upperno = pcl_old_upperno;
	}

	public String getPcl_next_no() {
		return pcl_next_no;
	}

	public void setPcl_next_no(String pcl_next_no) {
		this.pcl_next_no = pcl_next_no;
	}

	public String getPcl_next_name() {
		return pcl_next_name;
	}

	public void setPcl_next_name(String pcl_next_name) {
		this.pcl_next_name = pcl_next_name;
	}

	public ProductVo getProVO() {
		return proVO;
	}

	public void setProVO(ProductVo proVO) {
		this.proVO = proVO;
	}

	public int getPpt_no() {
		return ppt_no;
	}

	public void setPpt_no(int ppt_no) {
		this.ppt_no = ppt_no;
	}
	
}
