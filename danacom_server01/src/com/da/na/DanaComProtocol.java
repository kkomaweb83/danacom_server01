package com.da.na;

import java.io.Serializable;
import java.util.List;

public class DanaComProtocol implements Serializable {
	public static final long serialVersionUID = 39491L;
	
	private int p_cmd;
	private List<String> memComIdList;
	private MemComVo memComVo;
	private List<ProClassVo> class_list;
	
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
	
}
