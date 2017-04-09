package com.da.na;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
			String url = "jdbc:oracle:thin:@localhost:1521:java";  // java
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

	public List<ProClassVo> getPclList(String pcl_upperno, String mode) {
		StringBuffer sql = new StringBuffer();
		List<ProClassVo> class_list = new ArrayList<>();
		
		try {
			sql.append(" SELECT A.PCL_NO, A.PCL_NAME, A.PCL_STEP, A.PCL_BASIS");
			sql.append(" , NVL(A.PCL_UPPERNO, 'NULL') PCL_UPPERNO");
			sql.append(" , NVL(B.PCL_NAME, 'NULL') PA_PCL_NAME");
			sql.append(" FROM PRO_CLASS A, PRO_CLASS B ");
			sql.append(" WHERE A.PCL_UPPERNO = B.PCL_NO(+) ");
			sql.append(" AND A.PCL_UPPERNO = ?");
			sql.append(" ORDER BY A.PCL_NO");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, pcl_upperno);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				ProClassVo vo = new ProClassVo();
				vo.setPcl_no(rs.getString("PCL_NO")); 
				vo.setPcl_name(rs.getString("PCL_NAME"));
				vo.setPcl_step(rs.getInt("PCL_STEP"));
				vo.setPcl_basis(rs.getString("PCL_BASIS"));
				vo.setPcl_upperno(rs.getString("PCL_UPPERNO"));
				vo.setPa_pcl_name(rs.getString("PA_PCL_NAME"));
				class_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return class_list;
	}

	public List<MakerVo> getMkrList(String pcl_upperno, String mode) {
		StringBuffer sql = new StringBuffer();
		List<MakerVo> mkr_list = new ArrayList<>();
		
		try {
			sql.append(" SELECT MKR_NO, MKR_NAME, MKR_PCL_NO, PCL_NAME");
			sql.append(" FROM MAKER A, PRO_CLASS B");
			sql.append(" WHERE A.MKR_PCL_NO = B.PCL_NO");
			sql.append(" AND MKR_PCL_NO = ?");
			sql.append(" ORDER BY MKR_NO");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, pcl_upperno);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				MakerVo vo = new MakerVo();
				vo.setMkr_no(rs.getInt("MKR_NO"));
				vo.setMkr_name(rs.getString("MKR_NAME"));
				vo.setMkr_pcl_no(rs.getString("MKR_PCL_NO"));
				vo.setPcl_name(rs.getString("PCL_NAME"));

				mkr_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return mkr_list;
	}

	public String getMainPclName(String pcl_upperno, String mode) {
		StringBuffer sql = new StringBuffer();
		StringBuffer pclName = new StringBuffer("");
		
		try {
			sql.append(" SELECT PCL_NAME FROM PRO_CLASS");
			sql.append(" WHERE PCL_NO = ?");
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, pcl_upperno);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				pclName.append(rs.getString("PCL_NAME"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return pclName.toString();
	}

	public List<ProductVo> getProMainList(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		List<ProductVo> proList = new ArrayList<>();
		
		try {
			sql.append(" SELECT PRO_NO, PRO_NAME, PRO_DISPRICE, PRO_REALPRICE");
			sql.append(" , TO_CHAR(PRO_DISPRICE, '999,999,999,999')||'원' PRO_CH_PRICE");
			sql.append(" , TO_CHAR(PRO_DISPRICE, '999,999,999,999') PRO_CH2_PRICE");
			sql.append(" , TO_CHAR(PRO_REGDATE, 'YYYY/MM/DD') PRO_REGDATE");
			sql.append(" , PSM_CONENT");
			sql.append(" , PMG_FILE");
			sql.append(" , PRO_PCL_NO");
			sql.append(" , PRO_MKR_NO");
			sql.append(" , PRO_NAME AS PPT_PRO_NAME");
			sql.append(" FROM PRODUCT, PRO_SUMM, PRO_IMG ");
			sql.append(" WHERE PRO_NO = PSM_PRO_NO ");
			sql.append(" AND PRO_NO = PMG_PRO_NO ");
			sql.append(" AND PRO_PCL_NO = ? ");
			sql.append(" AND PMG_IDT_NO = 1 ");
			sql.append(" ORDER BY PRO_REGDATE DESC");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, readPort.getPcl_no());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				ProductVo vo = new ProductVo();
				vo.setPro_no(rs.getInt("PRO_NO"));
				vo.setPro_name(rs.getString("PRO_NAME"));
				vo.setPro_disprice(rs.getInt("PRO_DISPRICE"));
				vo.setPro_realprice(rs.getInt("PRO_REALPRICE"));
				vo.setPro_ch_price(rs.getString("PRO_CH_PRICE"));
				vo.setPro_ch2_price(rs.getString("PRO_CH2_PRICE"));
				vo.setPro_regdate(rs.getString("PRO_REGDATE"));
				vo.setPsm_conent(rs.getString("PSM_CONENT"));
				vo.setPmg_file(rs.getString("PMG_FILE"));
				vo.setPro_pcl_no(rs.getString("PRO_PCL_NO"));
				vo.setPro_mkr_no(rs.getInt("PRO_MKR_NO"));
				vo.setPpt_pro_name(rs.getString("PPT_PRO_NAME"));
				proList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return proList;
	}

	public int getVblMaxNo(String mode) {
		StringBuffer sql = new StringBuffer();
		StringBuffer vbl_no = new StringBuffer("");
		
		try {
			sql.append(" SELECT NVL(MAX(VBL_NO), 0)+1 FROM VIR_BILL");
			
			ptmt = conn.prepareStatement(sql.toString());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				vbl_no.append(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return Integer.parseInt(vbl_no.toString());
	}

	public Map<String, String> vblInsert(VirBillVo virBillVo, String mode) {
		StringBuffer sql = new StringBuffer();
		Map<String, String> resultMap = new HashMap<>();
		
		try {
			sql.append(" INSERT INTO VIR_BILL");
			sql.append(" (VBL_NO, VBL_MEM_NO, VBL_BOR_ANSWER, VBL_TITLE, VBL_DATE) VALUES ");
			sql.append(" (?, ?, ?, ?, SYSDATE)");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, virBillVo.getVbl_no());
			ptmt.setInt(2, virBillVo.getVbl_mem_no());
			ptmt.setString(3, virBillVo.getVbl_bor_answer());
			ptmt.setString(4, virBillVo.getVbl_title());
			int res = ptmt.executeUpdate();
			if(res > 0){
				resultMap.put("r_msg", "견적서 등록성공");
				resultMap.put("r_cmd", "301");
			}else{
				resultMap.put("r_msg", "견적서 등록실패");
				resultMap.put("r_cmd", "302");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultMap;
	}

	public void vdtInsert(VblDetVo vblDetVo, int vdt_vbl_no, String mode) {
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append(" INSERT INTO VBL_DET");
			sql.append(" (VDT_NO, VDT_VBL_NO, VDT_QUANTITY, VDT_PRO_NO) VALUES ");
			sql.append(" ((SELECT NVL(MAX(VDT_NO), 0)+1 FROM VBL_DET) ");
			sql.append(" , ?, ?, ?) ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, vdt_vbl_no);
			ptmt.setInt(2, vblDetVo.getVdt_quantity());
			ptmt.setInt(3, vblDetVo.getVdt_pro_no());
			int res = ptmt.executeUpdate();
			if(res > 0){
				System.out.println("견적서 등록성공");
			}else{
				System.out.println("견적서 등록실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public List<VirBillVo> getVblList(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		List<VirBillVo> vir_list = new ArrayList<>();
		
		try {
			sql.append(" SELECT VBL_NO, VBL_MEM_NO, VBL_BOR_ANSWER");
			sql.append(" , VBL_TITLE");
			sql.append(" , TO_CHAR(VBL_DATE, 'YYYY-MM-DD HH24:MI') VBL_DATE");
			sql.append(" FROM VIR_BILL");
			sql.append(" WHERE VBL_MEM_NO = ?");
			sql.append(" ORDER BY VBL_NO DESC");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, readPort.getMemComVo().getMem_no());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				VirBillVo vo = new VirBillVo();
				vo.setVbl_no(rs.getInt("VBL_NO"));
				vo.setVbl_mem_no(rs.getInt("VBL_MEM_NO"));
				vo.setVbl_bor_answer(rs.getString("VBL_BOR_ANSWER"));
				vo.setVbl_title(rs.getString("VBL_TITLE"));
				vo.setVbl_date(rs.getString("VBL_DATE"));

				vir_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return vir_list;
	}
	
	public ProductVo getvVblProVo(ProClassVo pclVo, String mode) {
		StringBuffer sql = new StringBuffer();
		ProductVo proVo = new ProductVo();
		
		try {
			sql.append(" SELECT PRO_NO, PRO_DISPRICE");
			sql.append(" , TO_CHAR(PRO_DISPRICE, '999,999,999,999')||'원' PRO_CH_PRICE");
			sql.append(" , TO_CHAR(PRO_DISPRICE, '999,999,999,999') PRO_CH2_PRICE");
			sql.append(" , PRO_PCL_NO");
			sql.append(" , PRO_NAME");
			sql.append(" , VDT_QUANTITY AS PST_QUANTITY");
			sql.append(" FROM PRODUCT, VBL_DET ");
			sql.append(" WHERE VDT_PRO_NO = PRO_NO AND VDT_VBL_NO = ? ");
			sql.append(" AND PRO_NO IN (SELECT VDT_PRO_NO FROM VBL_DET WHERE VDT_VBL_NO = ?) ");
			sql.append(" AND PRO_PCL_NO = ? ");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, pclVo.getPpt_no());
			ptmt.setInt(2, pclVo.getPpt_no());
			ptmt.setString(3, pclVo.getPcl_no());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				proVo.setPro_no(rs.getInt("PRO_NO"));
				proVo.setPro_disprice(rs.getInt("PRO_DISPRICE"));
				proVo.setPro_ch_price(rs.getString("PRO_CH_PRICE"));
				proVo.setPro_ch2_price(rs.getString("PRO_CH2_PRICE"));
				proVo.setPro_pcl_no(rs.getString("PRO_PCL_NO"));
				proVo.setPro_name(rs.getString("PRO_NAME"));
				proVo.setPpt_pro_name(rs.getString("PRO_NAME"));
				proVo.setPst_quantity(rs.getInt("PST_QUANTITY"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return proVo;
	}

	public VirBillVo getVblVo(int vbl_no, String mode) {
		StringBuffer sql = new StringBuffer();
		VirBillVo vo = new VirBillVo();
		
		try {
			sql.append(" SELECT VBL_NO, VBL_MEM_NO, VBL_BOR_ANSWER");
			sql.append(" , VBL_TITLE");
			sql.append(" , TO_CHAR(VBL_DATE, 'YYYY-MM-DD HH24:MI') VBL_DATE");
			sql.append(" FROM VIR_BILL");
			sql.append(" WHERE VBL_NO = ?");
			sql.append(" ORDER BY VBL_NO DESC");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, vbl_no);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				vo.setVbl_no(rs.getInt("VBL_NO"));
				vo.setVbl_mem_no(rs.getInt("VBL_MEM_NO"));
				vo.setVbl_bor_answer(rs.getString("VBL_BOR_ANSWER"));
				vo.setVbl_title(rs.getString("VBL_TITLE"));
				vo.setVbl_date(rs.getString("VBL_DATE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return vo;
	}

	public Map<String, String> vblUpdate(VirBillVo virBillVo, String mode) {
		StringBuffer sql = new StringBuffer();
		Map<String, String> resultMap = new HashMap<>();
		
		try {
			sql.append(" UPDATE VIR_BILL SET");
			sql.append(" VBL_TITLE = ? ");
			sql.append(" , VBL_BOR_ANSWER = ? ");
			sql.append(" WHERE VBL_NO = ? ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, virBillVo.getVbl_title());
			ptmt.setString(2, virBillVo.getVbl_bor_answer());
			ptmt.setInt(3, virBillVo.getVbl_no());
			int res = ptmt.executeUpdate();
			if(res > 0){
				resultMap.put("r_msg", "견적서 수정성공");
				resultMap.put("r_cmd", "311");
			}else{
				resultMap.put("r_msg", "견적서 수정실패");
				resultMap.put("r_cmd", "312");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultMap;
	}

	public void vdtDelete(int vblMaxNo_u, String mode) {
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append(" DELETE FROM VBL_DET");
			sql.append(" WHERE VDT_VBL_NO = ? ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, vblMaxNo_u);
			int res = ptmt.executeUpdate();
			if(res > 0){
				System.out.println("견적서 상세 삭제성공");
			}else{
				System.out.println("견적서 상세 삭제실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public Map<String, String> vblDelete(int vblMaxNo_d, String mode) {
		StringBuffer sql = new StringBuffer();
		Map<String, String> resultMap = new HashMap<>();
		
		try {
			sql.append(" DELETE FROM VIR_BILL");
			sql.append(" WHERE VBL_NO = ? ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, vblMaxNo_d);
			int res = ptmt.executeUpdate();
			if(res > 0){
				resultMap.put("r_msg", "견적서 삭제성공");
				resultMap.put("r_cmd", "331");
			}else{
				resultMap.put("r_msg", "견적서 삭제실패");
				resultMap.put("r_cmd", "332");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultMap;
	}

	public int getVbbMaxNo(String mode) {
		StringBuffer sql = new StringBuffer();
		StringBuffer vbb_no = new StringBuffer("");
		
		try {
			sql.append(" SELECT NVL(MAX(VBB_NO), 0)+1 FROM VIR_BILL_BOARD");
			
			ptmt = conn.prepareStatement(sql.toString());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				vbb_no.append(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return Integer.parseInt(vbb_no.toString());
	}

	public Map<String, String> vbbInsert(VirBillVo virBillVo, String mode) {
		StringBuffer sql = new StringBuffer();
		Map<String, String> resultMap = new HashMap<>();
		
		try {
			sql.append(" INSERT INTO VIR_BILL_BOARD");
			sql.append(" (VBB_NO, VBB_CONTENT, VBB_MEM_NO, VBB_DATE, VBB_RECOMM ");
			sql.append(" , VBB_COUNT, VBB_BTR_ANSWER, VBB_TITLE) VALUES ");
			sql.append(" (?, ?, ?, SYSDATE, 0, 0, 'n', ?) ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, virBillVo.getVbb_no());
			ptmt.setString(2, virBillVo.getVbb_content());
			ptmt.setInt(3, virBillVo.getVbl_mem_no());
			ptmt.setString(4, virBillVo.getVbl_title());
			int res = ptmt.executeUpdate();
			if(res > 0){
				resultMap.put("r_msg", "공유 견적서 등록성공");
				resultMap.put("r_cmd", "501");
			}else{
				resultMap.put("r_msg", "공유 견적서 등록실패");
				resultMap.put("r_cmd", "502");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultMap;
	}

	public void vdsInsert(VblDetVo vblDetVo, int vbbMaxNo, String mode) {
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append(" INSERT INTO VBL_DET_SHARE");
			sql.append(" (VDS_NO, VDS_VBB_NO, VDS_QUANTITY, VDS_PRO_NO) VALUES ");
			sql.append(" ((SELECT NVL(MAX(VDS_NO), 0)+1 FROM VBL_DET_SHARE) ");
			sql.append(" , ?, ?, ?) ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setInt(1, vbbMaxNo);
			ptmt.setInt(2, vblDetVo.getVdt_quantity());
			ptmt.setInt(3, vblDetVo.getVdt_pro_no());
			int res = ptmt.executeUpdate();
			if(res > 0){
				System.out.println("공유 견적서 등록성공");
			}else{
				System.out.println("공유 견적서 등록실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public List<VbbVo> getVbbList(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		List<VbbVo> vbb_list = new ArrayList<>();
		
		try {
			sql.append(" select vbb.vbb_no, vbb.vbb_content, mem.mem_id, ");
			sql.append(" to_char(vbb.vbb_date, 'yyyy-mm-dd') as vbb_date, ");
			sql.append(" vbb.vbb_recomm, vbb.vbb_count, vbb.vbb_btr_answer, vbb.vbb_title ");
			sql.append(" from vir_bill_board vbb, mem_com mem where vbb.vbb_mem_no = mem.mem_no");
			sql.append(" order by vbb.vbb_no desc");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				VbbVo vo = new VbbVo();
				vo.setVbb_no(rs.getString("vbb_no"));
				vo.setVbb_content(rs.getString("vbb_content"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setVbb_date(rs.getString("vbb_date"));
				vo.setVbb_recomm(rs.getString("vbb_recomm"));
				vo.setVbb_count(rs.getString("vbb_count"));
				vo.setVbb_btr_answer(rs.getString("vbb_btr_answer"));
				vo.setVbb_title(rs.getString("vbb_title"));

				vbb_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return vbb_list;
	}

	public void countUpVbbContent(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append(" update vir_bill_board set ");
			sql.append(" vbb_count = vbb_count + 1 ");
			sql.append(" where vbb_no = ? ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, readPort.getVbb_no());
			int res = ptmt.executeUpdate();
			if(res > 0){
				System.out.println("공유 견적서 조회수 수정 성공");
			}else{
				System.out.println("공유 견적서 조회수 수정 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public VbbVo getVbbContent(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		VbbVo vo = new VbbVo();
		
		try {
			sql.append(" select vbb.vbb_no, vbb.vbb_content, mem.mem_id, ");
			sql.append(" to_char(vbb.vbb_date, 'yyyy-mm-dd') as vbb_date, ");
			sql.append(" vbb.vbb_recomm, vbb.vbb_count, vbb.vbb_btr_answer, vbb.vbb_title ");
			sql.append(" from vir_bill_board vbb, mem_com mem where vbb.vbb_mem_no = mem.mem_no ");
			sql.append(" and vbb.vbb_no = ? ");
			sql.append(" order by vbb.vbb_no desc ");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, readPort.getVbb_no());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				vo.setVbb_no(rs.getString("vbb_no"));
				vo.setVbb_content(rs.getString("vbb_content"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setVbb_date(rs.getString("vbb_date"));
				vo.setVbb_recomm(rs.getString("vbb_recomm"));
				vo.setVbb_count(rs.getString("vbb_count"));
				vo.setVbb_btr_answer(rs.getString("vbb_btr_answer"));
				vo.setVbb_title(rs.getString("vbb_title"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return vo;
	}

	public List<VbsVo> getVbbContentPro(DanaComProtocol readPort, String mode) {
		StringBuffer sql = new StringBuffer();
		List<VbsVo> vbs_list = new ArrayList<>();
		
		try {
			sql.append(" SELECT VDS.VDS_NO, VDS.VDS_QUANTITY, PRO.PRO_NO, PRO.PRO_NAME, ");
			sql.append(" TO_CHAR(PRO.PRO_DISPRICE,'L9,999,999') PRO_DISPRICE, PRO.PRO_MILEGE, ");
			sql.append(" TO_CHAR(PRO.PRO_REGDATE, 'YYYY-MM-DD') PRO_REGDATE, ");
			sql.append(" MKR.MKR_NAME, PCL.PCL_NAME, PCL.PCL_NO, PSM.PSM_CONENT, PMG.PMG_FILE ");
			sql.append(" FROM VBL_DET_SHARE VDS ");
			sql.append(" JOIN PRODUCT PRO ON VDS.VDS_PRO_NO = PRO.PRO_NO ");
			sql.append(" JOIN MAKER MKR ON PRO.PRO_MKR_NO = MKR.MKR_NO ");
			sql.append(" JOIN PRO_CLASS PCL ON PRO.PRO_PCL_NO = PCL.PCL_NO ");
			sql.append(" JOIN PRO_SUMM PSM ON PRO.PRO_NO = PSM.PSM_PRO_NO ");
			sql.append(" JOIN PRO_IMG PMG ON PRO.PRO_NO = PMG.PMG_PRO_NO ");
			sql.append(" WHERE VDS_VBB_NO = ? ");
			sql.append(" AND PMG.PMG_IDT_NO = 1 ");
			sql.append(" ORDER BY PCL.PCL_NO ");
			System.out.println(sql.toString());
			
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, readPort.getVbb_no());
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				VbsVo vo = new VbsVo();
				vo.setVds_no(rs.getString("VDS_NO"));
				vo.setVds_quantity(rs.getString("VDS_QUANTITY"));
				vo.setPro_no(rs.getString("PRO_NO"));
				vo.setPro_name(rs.getString("PRO_NAME"));
				vo.setPro_disprice(rs.getString("PRO_DISPRICE"));
				vo.setPro_milege(rs.getString("PRO_MILEGE"));
				vo.setPro_regdate(rs.getString("PRO_REGDATE"));
				vo.setMkr_name(rs.getString("MKR_NAME"));
				vo.setPcl_name(rs.getString("PCL_NAME"));
				vo.setPcl_no(rs.getString("PCL_NO"));
				vo.setPsm_conent(rs.getString("PSM_CONENT"));
				vo.setPmg_file(rs.getString("PMG_FILE"));

				vbs_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(mode.equals("quit")){
					if(rs != null) rs.close();
					if(ptmt != null) ptmt.close();
					if(conn != null) conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return vbs_list;
	}
	
}
