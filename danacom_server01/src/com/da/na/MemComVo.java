package com.da.na;

import java.io.Serializable;
import java.util.List;

public class MemComVo implements Serializable {
	public static final long serialVersionUID = 39391L;
	
	private int mem_no;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_email;
	private String mem_hp;
	private int mem_mil;
	private String mem_rdate;
	private String mem_admin_autho;
	private String msg;
	private int cmd;
	private List<String> memComIdList;
	
	public MemComVo() {
	}

	public MemComVo(int mem_no, String mem_id, String mem_pass, String mem_name, String mem_email, String mem_hp,
			int mem_mil, String mem_rdate, String mem_admin_autho) {
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.mem_hp = mem_hp;
		this.mem_mil = mem_mil;
		this.mem_rdate = mem_rdate;
		this.mem_admin_autho = mem_admin_autho;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public int getMem_mil() {
		return mem_mil;
	}

	public void setMem_mil(int mem_mil) {
		this.mem_mil = mem_mil;
	}

	public String getMem_rdate() {
		return mem_rdate;
	}

	public void setMem_rdate(String mem_rdate) {
		this.mem_rdate = mem_rdate;
	}

	public String getMem_admin_autho() {
		return mem_admin_autho;
	}

	public void setMem_admin_autho(String mem_admin_autho) {
		this.mem_admin_autho = mem_admin_autho;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public List<String> getMemComIdList() {
		return memComIdList;
	}

	public void setMemComIdList(List<String> memComIdList) {
		this.memComIdList = memComIdList;
	}
	
}
