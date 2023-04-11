package fileUpDown;

import java.util.List;
import java.util.Vector;

import common.JDBConnPool;

public class MyFileDao extends JDBConnPool {
	//새로운 게시물을 입력합니다.
	public int insertFile(MyFileDto dto) {
		int applyResult = 0;
		try {
			String query="INSERT INTO MYFILE("
					+ "IDX, NAME, TITLE, CATE, OFILE, NFILE)"
					+ "VALUES(	"
					+ "SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getCate());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getNfile());
			
			applyResult = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		return applyResult;
	}
	//파일 목록을 반환합니다.
	public List<MyFileDto> myFileList() {
		List<MyFileDto> fileList = new Vector<MyFileDto>();
		//쿼리문 작성
		String query ="SELECT * FROM MYFILE ORDER BY IDX DESC";
		try {
			psmt = con.prepareStatement(query); //쿼리 준비
			rs = psmt.executeQuery();//쿼리 실행
			while(rs.next()) { //목록 안의 파일 수만큼 반복
				// DTO에 저장
				MyFileDto dto = new MyFileDto();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setNfile(rs.getString(6));
				dto.setRegdate(rs.getString(7));
				fileList.add(dto);	//목록에 추가
				
			}
		}
		catch(Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		return fileList;	//목록 반환
	}
	
}
