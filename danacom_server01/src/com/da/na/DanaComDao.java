package com.da.na;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DanaComDao {
	
	Connection conn = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	public DanaComDao() {
		connect();
	}
	
	public void connect(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  // java
			String user = "danacom"; // id
			String password = "oracle";
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public MemComVo getLoginChk(MemComVo memComReadVo){
		StringBuffer sql = new StringBuffer();
		int size = 0;
		MemComVo memComWriteVo = new MemComVo(); 
		
		try {
			sql.append("select mem_no, mem_id, mem_pass, mem_name, mem_email, mem_hp ");
			sql.append(",mem_mil, to_char(mem_rdate,'YYYY-MM-DD') as date_desc, mem_admin_autho from mem_com where mem_id = ?");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, memComReadVo.getMem_id());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				size++;
				memComWriteVo.setMem_no(rs.getInt("mem_no")); 
				memComWriteVo.setMem_id(rs.getString("mem_id"));
				memComWriteVo.setMem_pass(rs.getString("mem_pass"));
				memComWriteVo.setMem_name(rs.getString("mem_name"));
				memComWriteVo.setMem_email(rs.getString("mem_email"));
				memComWriteVo.setMem_hp(rs.getString("mem_hp"));
				memComWriteVo.setMem_mil(rs.getInt("mem_mil"));
				memComWriteVo.setMem_rdate(rs.getString("date_desc"));
				memComWriteVo.setMem_admin_autho(rs.getString("mem_admin_autho"));
			}

			if(size == 0){
				memComWriteVo.setMsg("해당 ID가 존재하지 않습니다.");
				memComWriteVo.setCmd(102);
			}else if(size == 1){
				if(memComWriteVo.getMem_pass().equals(memComReadVo.getMem_pass())){
					memComWriteVo.setMsg("");
					memComWriteVo.setCmd(101);
				}else{
					memComWriteVo.setMsg("해당 PW가 같지 않습니다.");
					memComWriteVo.setCmd(102);
				}
				
			}else if(size > 1){
				memComWriteVo.setMsg("해당 ID가 여러개 존재 합니다.");
				memComWriteVo.setCmd(102);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptmt != null) ptmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memComWriteVo;
	}
	
	// ID 중복검사
	public MemComVo getDupId(MemComVo memComReadVo){
		StringBuffer sql = new StringBuffer();
		MemComVo memComWriteVo = new MemComVo(); 
		
		try {
			sql.append("select mem_id from mem_com where mem_id = ?");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, memComReadVo.getMem_id());
			rs = ptmt.executeQuery();
			
			boolean res = rs.next();
			if(res){
				memComWriteVo.setMsg("아이디가 존재합니다.");
				memComWriteVo.setCmd(201);
			}else{
				memComWriteVo.setMsg("사용가능한 아이디 입니다.");
				memComWriteVo.setCmd(202);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptmt != null) ptmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memComWriteVo;
	}
	
	// 회원가입
	public MemComVo insertMember(MemComVo memComReadVo){
		StringBuffer sql = new StringBuffer();
		MemComVo memComWriteVo = new MemComVo(); 
		
		try {
			sql.append("insert into mem_com values(mem_no_seq.nextval,?,?,?,?,?,?,sysdate,'n')");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, memComReadVo.getMem_id());
			ptmt.setString(2, memComReadVo.getMem_pass());
			ptmt.setString(3, memComReadVo.getMem_name());
			ptmt.setString(4, memComReadVo.getMem_email());
			ptmt.setString(5, memComReadVo.getMem_hp());
			ptmt.setInt(6, memComReadVo.getMem_mil());
			int res = ptmt.executeUpdate();
			if(res > 0){
				memComWriteVo.setMsg("회원가입 성공");
				memComWriteVo.setCmd(301);
			}else{
				memComWriteVo.setMsg("회원가입 실패");
				memComWriteVo.setCmd(302);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptmt != null) ptmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memComWriteVo; 
	}
	
}
