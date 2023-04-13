package m2board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import board.BoardDTO;
import common.JDBConnPool;

public class MBoardDAO extends JDBConnPool {
	
	// 기본생성자
	public MBoardDAO() {
		super();
	}
	
	//fileboard table 검색조건 고려 결과 전체 개수
	public int countAll(Map<String, Object> map) {
		int totalCount=0;
		
		String sql = "SELECT COUNT(*) FROM FILEBOARD"; 
		if(map.get("searchStr")!=null) {
			sql += " where " +map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}
		try {
			stmt = con.createStatement();	
			rs = stmt.executeQuery(sql);	
			rs.next();	//ResultSet은 맨 처음은 빈공간이기 때문에 한칸 넘기고 해야 함.
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
		
	}
	
	//결과값만..
	public List<MBoardDTO> getListPage(Map<String,Object> map){
		List<MBoardDTO> bl = new Vector<>();
		
		String sql = "SELECT * FROM ( "
				+ "SELECT ROWNUM PNUM, S.* FROM( "
				+ "SELECT F.* FROM FILEBOARD F";
		if(map.get("searchStr")!=null) {
			sql += " where " +map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}
			sql+=" 	order by IDX desc"
				+	") s"
				+")"
				+"where PNUM between ? and ?";

			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs=psmt.executeQuery();
				while(rs.next()) {
					MBoardDTO dto = new MBoardDTO();
					dto.setIdx(rs.getString("idx"));
					dto.setName(rs.getString("name"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setPostdate(rs.getDate("postdate"));
					dto.setOfile(rs.getString("ofile"));
					dto.setNfile(rs.getString("nfile"));
					dto.setDowncount(rs.getInt("downcount"));
					dto.setVisitcount(rs.getInt("visitcount"));
					dto.setPass(rs.getString("pass"));
					bl.add(dto);
				}
			}catch(Exception e) {
				System.out.println("게시물 목록 읽는 중 에러");
				e.printStackTrace();
			}
		
		return bl;
	}

	public int insertWrite(MBoardDTO dto) {
		int result=0;
		String sql = "INSERT INTO FILEBOARD(IDX, NAME, TITLE, CONTENT, OFILE, NFILE, PASS)"
					+"VALUES(SEQ_BOARD_NUM.NEXTVAL,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getNfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외");
			e.printStackTrace();
		}
		
		return result;
	}

	public void updateVisitCount(String idx) {
		String sql="UPDATE FILEBOARD SET VISITCOUNT=VISITCOUNT+1"
				+ "WHERE IDX=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("조회수 증가 중 예외");
			e.printStackTrace();
		}
	}
	public MBoardDTO getView(String idx) {
		MBoardDTO dto = new MBoardDTO();
		String sql="SELECT * FROM FILEBOARD WHERE IDX=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setNfile(rs.getString("nfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setPass(rs.getString("pass"));
			}
		} catch (Exception e) {
			System.out.println("상세보기 중 예외");
			e.printStackTrace();
		}
				
		return dto;
	}

	public void updateDownCount(String idx) {
		String sql="UPDATE FILEBOARD SET DOWNCOUNT=DOWNCOUNT+1"
				+ "WHERE IDX=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("다운로드 수 증가 중 예외");
			e.printStackTrace();
		}
		
	}

	public int getDowncount(String idx) {
		int dcount=0;
		String sql="SELECT DOWNCOUNT FROM FILEBOARD WHERE IDX=?";
		try {
			psmt= con.prepareStatement(sql);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			rs.next();
			dcount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("다운로드 수 읽기 중 에러");
			e.printStackTrace();
		}
		
		return dcount;
	}

	public boolean confirmPassword(String pass, String idx) {
		boolean isRight = false;
		try {
			String sql="SELECT COUNT(*) FROM FILEBOARD WHERE PASS=? AND IDX=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==1) {
				isRight = true;
			}
		} catch (Exception e) {
			System.out.println("암호 검증 중 에러");
			e.printStackTrace();
		}
		return isRight;
	}

	public int deletePost(String idx) {
		int result = 0;
		try {
			String sql="DELETE FROM FILEBOARD WHERE IDX=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 에러");
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int updatePost(MBoardDTO dto) {
		int result = 0;
		try {
			String sql="UPDATE FILEBOARD SET TITLE=?, NAME=?, CONTENT=?, OFILE=?, NFILE=? WHERE IDX=? AND PASS=?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getNfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("게시물 수정 중 에러");
			e.printStackTrace();
		}
		
		return result;
	}
	
}
