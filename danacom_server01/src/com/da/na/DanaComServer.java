package com.da.na;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DanaComServer implements Runnable  {
	ServerSocket ss;
	
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
		try {
			while(true){
				Socket s = ss.accept();
				
				DanaComPlayer player = new DanaComPlayer(s, this);
				danaComPlayerList.add(player);
				player.start();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(ss != null) ss.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	
	// 로그인한 전체회원 목록
	public List<String> getUsers(int type, DanaComPlayer danaComPlayer){
		List<String> memIdList = new ArrayList<>();
		for(DanaComPlayer k : danaComPlayerList){
			if(!"".equals(k.getMem_id())){
				if(!(type == 9 && k == danaComPlayer)){
					memIdList.add(k.getMem_id());
				}
			}
		}
		
		return memIdList;
	}
	
	// 로그인한 회원모두에게 결과를 보냄
	public void sendMsgAllPlayer(DanaComProtocol writePort){
		try {
			for(DanaComPlayer k : danaComPlayerList){
				if(!"".equals(k.getMem_id())){
					k.oos.writeObject(writePort);
					k.oos.flush();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	// 로그인 이외는 처리후 제거
	public void delPlayer(DanaComPlayer player) {
		danaComPlayerList.remove(player);
	}
	
	public static void main(String[] args) {
		new DanaComServer();
	}

	// 로그아웃한 내역을 회원모두에게 결과를 보냄
	public void sendMsgAllPlayerOut(DanaComProtocol writePort, DanaComPlayer danaComPlayer) {
		try {
			for(DanaComPlayer k : danaComPlayerList){
				if(!"".equals(k.getMem_id())){
					if(k == danaComPlayer){
						writePort.setP_cmd(2009);
					}else{
						writePort.setP_cmd(2001);
					}
					k.oos.writeObject(writePort);
					k.oos.flush();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
