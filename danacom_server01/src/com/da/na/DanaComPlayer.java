package com.da.na;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

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
				System.out.println("server run() : " + readPort.getP_cmd());
				
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
					writePort = new DanaComProtocol();
					writePort.setP_cmd(2001);
					writePort.setMemComIdList(danaComServer.getUsers(9,this));
					
					danaComServer.sendMsgAllPlayerOut(writePort, this);
					danaComServer.delPlayer(this);
					break dana_player;
				case 119:  // 종료에 의한 로그아웃
					writePort = new DanaComProtocol();
					writePort.setP_cmd(119);
					writePort.setMemComIdList(danaComServer.getUsers(9,this));
					
					danaComServer.sendMsgAllPlayerOut(writePort, this);
					danaComServer.delPlayer(this);
					break dana_player;
				case 200:  // ID 중복검사
					memComWriteVo = dao.getDupId(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(200);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 300:  // 회원가입
					memComWriteVo = dao.insertMember(readPort.getMemComVo());
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(300);
					writePort.setMemComVo(memComWriteVo);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 2001: // 접속회원 목록
					writePort = new DanaComProtocol();
					writePort.setP_cmd(2001);
					writePort.setMemComIdList(danaComServer.getUsers(1,this));
					
					danaComServer.sendMsgAllPlayer(writePort);
					break;
				case 3001: // 견적서 등록폼 - 상품분류 조회
					List<ProClassVo> class_list = dao.getPclList("NULL", "go");
					for(int i=0; i < class_list.size(); i++){
						if(i > 1) break;
						ProClassVo vo = (ProClassVo)class_list.get(i);
						vo.setPcl_list(dao.getPclList(vo.getPcl_no(),"quit"));
					}
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3001);
					writePort.setClass_list(class_list);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3011: // 견적서 등록폼 - 상품검색 조건(제조사, 상품분류) 조회
					List<ProClassVo> tempList = null;
					ProClassVo pclVO = null;
					
					List<MakerVo> mkr_list = dao.getMkrList(readPort.getPcl_no(), "go");
					List<ProClassVo> pclList = dao.getPclList(readPort.getPcl_no(), "go");
					if(pclList != null){
						for(int i=0; i < pclList.size(); i++){
							pclVO = (ProClassVo)pclList.get(i);
							pclVO.setPcl_list(dao.getPclList(pclVO.getPcl_no(), "go"));
							tempList = pclVO.getPcl_list();
							if(tempList != null){
								for(int j=0; j < tempList.size(); j++){
									pclVO = (ProClassVo)tempList.get(j);
									pclVO.setPcl_list(dao.getPclList(pclVO.getPcl_no(),"go"));
								}
							}
						}
					}
					String mainPclName = dao.getMainPclName(readPort.getPcl_no(), "quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3011);
					writePort.setPcl_no(readPort.getPcl_no());
					writePort.setPcl_name(mainPclName);
					writePort.setMkr_list(mkr_list);
					writePort.setClass_list(pclList);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3021: // 견적서 등록폼 - 기본 상품검색 조회
					List<ProductVo> proList = dao.getProMainList(readPort, "quit");
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3021);
					writePort.setPro_list(proList);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3031: // 견적서 등록폼 - 상품검색 조회버튼
					break;
				case 3051: // 회원 견적서 등록
					int vblMaxNo= dao.getVblMaxNo("go");
					readPort.getVirBillVo().setVbl_no(vblMaxNo);
					Map<String, String> resultMap = dao.vblInsert(readPort.getVirBillVo(), "go");
					
					List<VblDetVo> vdtList = readPort.getVdt_list();
					for (int i = 0; i < vdtList.size(); i++) {
						dao.vdtInsert((VblDetVo)vdtList.get(i), vblMaxNo, "go");
					}
					dao.getVblMaxNo("quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3051);
					writePort.setR_msg(resultMap.get("r_msg"));
					writePort.setR_cmd(Integer.parseInt(resultMap.get("r_cmd")));
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3052: // 회원 견적서 수정
					int vblMaxNo_u = readPort.getVirBillVo().getVbl_no();
					Map<String, String> resultMap_u = dao.vblUpdate(readPort.getVirBillVo(), "go");
					
					dao.vdtDelete(vblMaxNo_u, "go");
					
					List<VblDetVo> vdtList_u = readPort.getVdt_list();
					for (int i = 0; i < vdtList_u.size(); i++) {
						dao.vdtInsert((VblDetVo)vdtList_u.get(i), vblMaxNo_u, "go");
					}
					dao.getVblMaxNo("quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3052);
					writePort.setR_msg(resultMap_u.get("r_msg"));
					writePort.setR_cmd(Integer.parseInt(resultMap_u.get("r_cmd")));
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3053: // 회원 견적서 삭제
					int vblMaxNo_d = readPort.getVirBillVo().getVbl_no();
					dao.vdtDelete(vblMaxNo_d, "go");
					Map<String, String> resultMap_d = dao.vblDelete(vblMaxNo_d, "quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3053);
					writePort.setR_msg(resultMap_d.get("r_msg"));
					writePort.setR_cmd(Integer.parseInt(resultMap_d.get("r_cmd")));
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3061: // 회원 견적서 리스트 조회
					List<VirBillVo> vir_list = dao.getVblList(readPort, "quit");
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3061);
					writePort.setVir_list(vir_list);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3071: // 회원 견적서 상세
					int tot_price = 0;
					VirBillVo virBillVo = dao.getVblVo(readPort.getVbl_no(), "go");
					
					List<ProClassVo> class_list_vbl = dao.getPclList("NULL", "go");
					for(int i=0; i < class_list_vbl.size(); i++){
						if(i > 1) break;
						ProClassVo proVo = (ProClassVo)class_list_vbl.get(i);
						proVo.setPcl_list(dao.getPclList(proVo.getPcl_no(),"go"));
						for(int j = 0; j < proVo.getPcl_list().size(); j++){
							ProClassVo proVo2 = proVo.getPcl_list().get(j);
							proVo2.setPpt_no(readPort.getVbl_no());
							proVo2.setProVO(dao.getvVblProVo(proVo2, "go"));
							if(proVo2.getProVO() != null){
								tot_price += (proVo2.getProVO().getPro_disprice()*proVo2.getProVO().getPst_quantity());
							}
						}
					}
					dao.getVblMaxNo("quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3071);
					writePort.setVirBillVo(virBillVo);
					writePort.setClass_list(class_list_vbl);
					writePort.setTot_price(tot_price);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3072: // 견적서 등록폼 - 상품검색 조건(제조사, 상품분류) 조회
					List<ProClassVo> tempList_u = null;
					ProClassVo pclVO_u = null;
					
					List<MakerVo> mkr_list_u = dao.getMkrList(readPort.getPcl_no(), "go");
					List<ProClassVo> pclList_u = dao.getPclList(readPort.getPcl_no(), "go");
					if(pclList_u != null){
						for(int i=0; i < pclList_u.size(); i++){
							pclVO_u = (ProClassVo)pclList_u.get(i);
							pclVO_u.setPcl_list(dao.getPclList(pclVO_u.getPcl_no(), "go"));
							tempList_u = pclVO_u.getPcl_list();
							if(tempList_u != null){
								for(int j=0; j < tempList_u.size(); j++){
									pclVO_u = (ProClassVo)tempList_u.get(j);
									pclVO_u.setPcl_list(dao.getPclList(pclVO_u.getPcl_no(),"go"));
								}
							}
						}
					}
					String mainPclName_u = dao.getMainPclName(readPort.getPcl_no(), "quit");
					
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3072);
					writePort.setPcl_no(readPort.getPcl_no());
					writePort.setPcl_name(mainPclName_u);
					writePort.setMkr_list(mkr_list_u);
					writePort.setClass_list(pclList_u);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 3073: // 견적서 등록폼 - 기본 상품검색 조회
					List<ProductVo> proList_u = dao.getProMainList(readPort, "quit");
					writePort = new DanaComProtocol();
					writePort.setP_cmd(3073);
					writePort.setPro_list(proList_u);
					
					oos.writeObject(writePort);
					oos.flush();
					
					break;
				case 9999: // 접속 종료
					s.shutdownInput();
					s.shutdownOutput();
					break dana_player;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(ois != null) ois.close();
				if(oos != null) oos.close();
				if(s != null && !s.isClosed()) s.close();
				danaComServer.delPlayer(this);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

}
