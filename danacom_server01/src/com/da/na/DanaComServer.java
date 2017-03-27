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
	
	// 로그인 성공 회원
	ArrayList<DanaComPlayer> danaComPlayerList = new ArrayList<>();
	
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
		DanaComProtocol readPort = null;
		DanaComProtocol writePort = null;
		MemComVo memComWriteVo = null;
		DanaComDao dao = null;
		
		try {
			while(true){
				s = ss.accept();
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				DanaComPlayer player = new DanaComPlayer(s, this);
				danaComPlayerList.add(player);
				player.start();
				
				/*
				readPort = (DanaComProtocol)ois.readObject();
				dao = new DanaComDao();
				
				// ID 중복검사
				if(readPort.getP_cmd() == 200){
					memComWriteVo = dao.getDupId(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(200);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
				// 회원가입
				}else if(readPort.getP_cmd() == 300){
					memComWriteVo = dao.insertMember(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(300);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
				}else{
					DanaComPlayer player = new DanaComPlayer(s, this);
					danaComPlayerList.add(player);
					new Thread(player).start();
				}
				*/
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(ss != null) ss.close();
				if(ois != null) ois.close();
				if(oos != null) oos.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	
	// 로그인한 전체회원 목록
	public List<String> getUsers(){
		List<String> memIdList = new ArrayList<>();
		for(DanaComPlayer k : danaComPlayerList){
			memIdList.add(k.getMem_id());
		}
		
		return memIdList;
	}
	
	public static void main(String[] args) {
		new DanaComServer();
	}
}
