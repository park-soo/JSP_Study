package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	public int getTotalCount(Map<String,Object> param) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM BOARD"; 
		if(param.get("findWord")!=null) {
			sql += " where " +param.get("findCol")+" like '%"+param.get("findWord")+"%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 에러");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	//게시물 내용 읽기
	public List<BoardDTO>getList(Map<String,Object> param){
		List<BoardDTO> bl = new Vector<BoardDTO>();
		String sql = "SELECT * FROM BOARD"; 
		if(param.get("findWord")!=null) {
			sql += " where " +param.get("findCol")+" like '%"+param.get("findWord")+"%'";
		}
		sql+=" order by num desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				bl.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 목록 읽는 중 에러");
			e.printStackTrace();
		}
		
		return bl;
	}
	
	// 페이지별 게시물 읽어오기
	public List<BoardDTO> getListPage(Map<String, Object> param){
		List<BoardDTO> bl = new Vector<BoardDTO>();
		String sql = "SELECT * FROM ( "
						+ "SELECT ROWNUM PNUM, S.* FROM( "
						+ "SELECT B.* FROM BOARD B";
		if(param.get("findWord")!=null) {
			sql += " where " +param.get("findCol")+" like '%"+param.get("findWord")+"%'";
		}
		sql+=" 				order by num desc"
						+	") s"
						+")"
						+"where pnum between ? and ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, param.get("start").toString());
			psmt.setString(2, param.get("end").toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				bl.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 목록 읽는 중 에러");
			e.printStackTrace();
		}
		
		return bl;
	}

	
	//게시물 작성
	public int insertWrite(BoardDTO dto) {
		int res=0;
		try {
			String sql="INSERT INTO BOARD(NUM, TITLE, CONTENT, ID, VISITCOUNT)" + " VALUES(SEQ_BOARD_NUM.NEXTVAL,?,?,?,0)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			res = psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("게시물 입력 중 에러");
			e.printStackTrace();
		}
		return res;
	}
	
	public BoardDTO getView(String num) {
		BoardDTO dto = new BoardDTO();
		String sql="SELECT B.* ,M.NAME "
				+ "FROM BOARD B, MEMBER M "
				+ "WHERE B.NUM=?"
				+ "AND  B.ID = M.ID ";
		try {
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, num);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				dto.setName(rs.getString("name"));
			}
		}catch(Exception e){
			System.out.println("게시물 상세보기 중 에러");
			e.printStackTrace();
		}
		return dto;
	}

	// 조회수 증가
	public void updateVisitCount(String num) {
		String sql="UPDATE BOARD SET VISITCOUNT=VISITCOUNT+1 WHERE NUM=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, num);
			psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("게시물 조회수 증가 중 에러");
			e.printStackTrace();
		}
	}
	
	//게시물 삭제
	public int deletePost(String num) {
		int res=0;
		
		try {
			String sql = "DELETE FROM BOARD WHERE NUM=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, num);
			res = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 에러");
			e.printStackTrace();
		}
		
		return res;
	}
	
	//게시물 수정
	public int updateEdit(BoardDTO dto) {
		int res=0;
		try {
			String sql="UPDATE BOARD SET TITLE=?, CONTENT=? WHERE NUM=?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			res = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 에러");
			e.printStackTrace();
		}
		
		return res;
	}
	
}
