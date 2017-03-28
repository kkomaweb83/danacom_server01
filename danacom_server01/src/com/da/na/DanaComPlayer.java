package com.da.na;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DanaComPlayer extends Thread {
	Socket s;
	DanaComServer danaComServer;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	private String mem_id = "";
	
	public DanaComPlayer() {
	}
	public DanaComPlayer(Socket s, DanaComServer danaComServer) {
		try {
			this.s = s;
			this.danaComServer = danaComServer;
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	@Override
	public void run() {
		DanaComProtocol readPort = null;
		DanaComProtocol writePort = null;
		MemComVo memComWriteVo = null;
		DanaComDao dao = null;
		
		try {
			dana_player : while(true){
				readPort = (DanaComProtocol)ois.readObject();
				dao = new DanaComDao();
				System.out.println("11 : " + readPort.getP_cmd());
				
				switch(readPort.getP_cmd()){
				case 100:  // 로그인
					memComWriteVo = dao.getLoginChk(readPort.getMemComVo());
					if(memComWriteVo.getCmd() == 101){
						mem_id = memComWriteVo.getMem_id();
					}
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(100);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					break;
				case 109:  // 로그아웃
					danaComServer.delPlayer(this);
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(2001);
					writePort.setMemComIdList(danaComServer.getUsers());
					
					danaComServer.sendMsgAllPlayer(writePort);
					break;
				case 200:  // ID 중복검사
					memComWriteVo = dao.getDupId(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(200);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break dana_player;
				case 300:  // 회원가입
					memComWriteVo = dao.insertMember(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(300);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break dana_player;
				case 2001: // 접속회원 목록
					writePort = new DanaComProtocol();
					writePort.setP_cmd(2001);
					writePort.setMemComIdList(danaComServer.getUsers());
					
					danaComServer.sendMsgAllPlayer(writePort);
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
				danaComServer.delPlayer(this);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

}
