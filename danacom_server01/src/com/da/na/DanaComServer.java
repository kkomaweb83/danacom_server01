package com.da.na;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanaComServer implements Runnable  {
	ServerSocket ss;
	Socket s = null;
	
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	Connection conn = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	List<String> memComIdList = new ArrayList<>(); 
	
	public DanaComServer() {
		try {
			ss = new ServerSocket(8888);
			System.out.println("서버 대기중...");
			new Thread(this).start();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void run() {
		MemComVo memComReadVo = null;
		MemComVo memComWriteVo = null;
		DanaComDao dao = null;
		
		try {
			while(true){
				s = ss.accept();
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				memComReadVo = (MemComVo)ois.readObject();
				dao = new DanaComDao();
				
				switch(memComReadVo.getCmd()){
				case 100:  // 로그인
					memComWriteVo = dao.getLoginChk(memComReadVo);
					if(memComWriteVo.getCmd() == 101){
						memComIdList.add(memComWriteVo.getMem_name());
					}
					
					oos.writeObject(memComWriteVo);
					oos.flush();
					break;
				case 200: // ID 중복검사
					memComWriteVo = dao.getDupId(memComReadVo);
					oos.writeObject(memComWriteVo);
					oos.flush();
					break;
				case 300: // 회원가입
					memComWriteVo = dao.insertMember(memComReadVo);
					oos.writeObject(memComWriteVo);
					oos.flush();
					break;
				case 2001: // 접속회원 목록
					memComWriteVo = new MemComVo();
					memComWriteVo.setCmd(2001);
					memComWriteVo.setMemComIdList(memComIdList);
					oos.writeObject(memComWriteVo);
					oos.flush();
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(s != null) s.close();
				if(ois != null) ois.close();
				if(oos != null) oos.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	
	public static void main(String[] args) {
		new DanaComServer();
	}
}