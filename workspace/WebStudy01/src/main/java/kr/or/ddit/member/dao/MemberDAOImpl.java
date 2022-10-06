package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MEMBER (         ");
		sql.append(" 	    MEM_ID                ");
		sql.append(" 	   , MEM_PASS             ");
		sql.append(" 	   , MEM_NAME             ");
		sql.append(" 	   , MEM_REGNO1           ");
		sql.append(" 	   , MEM_REGNO2           ");
		sql.append(" 	   , MEM_BIR              ");
		sql.append(" 	   , MEM_ZIP              ");
		sql.append(" 	   , MEM_ADD1             ");
		sql.append(" 	   , MEM_ADD2             ");
		sql.append(" 	   , MEM_HOMETEL          ");
		sql.append(" 	   , MEM_COMTEL           ");
		sql.append(" 	   , MEM_HP               ");
		sql.append(" 	   , MEM_MAIL             ");
		sql.append(" 	   , MEM_JOB              ");
		sql.append(" 	   , MEM_LIKE             ");
		sql.append(" 	   , MEM_MEMORIAL         ");
		sql.append(" 	   , MEM_MEMORIALDAY      ");
		sql.append(" 	) VALUES (                ");
		sql.append(" 			?                 ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , TO_DATE( ? , 'YYYY-MM-DD' )      ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , ?                ");
		sql.append(" 		   , TO_DATE(? , 'YYYY-MM-DD') ");
		sql.append(" 	)						  ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			int index = 1;
			pstmt.setString(index++, member.getMemId());
			pstmt.setString(index++, member.getMemPass());
			pstmt.setString(index++, member.getMemName());
			pstmt.setString(index++, member.getMemRegno1());
			pstmt.setString(index++, member.getMemRegno2());
			pstmt.setString(index++, member.getMemBir());
			pstmt.setString(index++, member.getMemZip());
			pstmt.setString(index++, member.getMemAdd1());
			pstmt.setString(index++, member.getMemAdd2());
			pstmt.setString(index++, member.getMemHometel());
			pstmt.setString(index++, member.getMemComtel());
			pstmt.setString(index++, member.getMemHp());
			pstmt.setString(index++, member.getMemMail());
			pstmt.setString(index++, member.getMemJob());
			pstmt.setString(index++, member.getMemLike());
			pstmt.setString(index++, member.getMemMemorial());
			pstmt.setString(index++, member.getMemMemorialday());
			
			return pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();
//		반복되지 않는 파트 1
		
		sql.append(" SELECT                                                         ");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,                          ");
		sql.append("     MEM_REGNO1,    MEM_REGNO2,                                 ");
		sql.append("     TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,                    ");
		sql.append("     MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                         ");
		sql.append("     MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                     ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,                         ");
		sql.append("     MEM_MEMORIAL,                                              ");
		sql.append("     TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,    ");
		sql.append("     MEM_MILEAGE,    MEM_DELETE                                 ");
		sql.append(" FROM    MEMBER                                                 ");
		sql.append(" WHERE MEM_ID = ?                                          ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			// 반복되지 않는 파트 1-1
			pstmt.setString(1, memId);
			
			ResultSet rs= pstmt.executeQuery();
			
			// 반복되지 않는 파트 2
			MemberVO member = null;
			if(rs.next()) {
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemRegno1(rs.getString("MEM_REGNO1"));
				member.setMemRegno2(rs.getString("MEM_REGNO2"));
				member.setMemBir(rs.getString("MEM_BIR"));
				member.setMemZip(rs.getString("MEM_ZIP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD2"));
				member.setMemHometel(rs.getString("MEM_HOMETEL"));
				member.setMemComtel(rs.getString("MEM_COMTEL"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemJob(rs.getString("MEM_JOB"));
				member.setMemLike(rs.getString("MEM_LIKE"));
				member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
				member.setMemDelete(rs.getBoolean("MEM_DELETE"));
			}
			return member;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
//		반복되지 않는 파트 1
		sql.append(" SELECT                                        ");
		sql.append("     MEM_ID,    MEM_NAME,    MEM_ADD1,         ");
		sql.append("     MEM_HP,    MEM_MAIL,        MEM_MILEAGE   ");
		sql.append(" FROM MEMBER                                   ");
		sql.append(" WHERE MEM_DELETE IS NULL     ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			ResultSet rs= pstmt.executeQuery();
			
			// 반복되지 않는 파트 2
			List<MemberVO> memberList = new ArrayList<>();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				memberList.add(member);
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
			}
			
			return memberList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE MEMBER                   ");
	    sql.append(" SET                             ");
	    sql.append("     MEM_ZIP = ?                 ");
	    sql.append("     , MEM_ADD1 = ?              ");
	    sql.append("     , MEM_ADD2 = ?              ");
	    sql.append("     , MEM_HOMETEL = ?           ");
	    sql.append("     , MEM_COMTEL = ?            ");
	    sql.append("     , MEM_HP = ?                ");
	    sql.append("     , MEM_MAIL = ?              ");
	    sql.append("     , MEM_JOB = ?               ");
	    sql.append("     , MEM_LIKE = ?              ");
	    sql.append("     , MEM_MEMORIAL = ?          ");
	    sql.append("     , MEM_MEMORIALDAY = TO_DATE(?, 'YYYY-MM-DD' )      ");
		sql.append(" WHERE MEM_ID = ?                ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			int index = 1;
			pstmt.setString(index++, member.getMemZip());
			pstmt.setString(index++, member.getMemAdd1());
			pstmt.setString(index++, member.getMemAdd2());
			pstmt.setString(index++, member.getMemHometel());
			pstmt.setString(index++, member.getMemComtel());
			pstmt.setString(index++, member.getMemHp());
			pstmt.setString(index++, member.getMemMail());
			pstmt.setString(index++, member.getMemJob());
			pstmt.setString(index++, member.getMemLike());
			pstmt.setString(index++, member.getMemMemorial());
			pstmt.setString(index++, member.getMemMemorialday());
			pstmt.setString(index++, member.getMemId());
			
			return pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String memId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE MEMBER      ");
        sql.append(" SET MEM_DELETE = '1'   ");
        sql.append(" WHERE MEM_ID = ?   ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			pstmt.setString(1, memId);
			
//			return pstmt.executeUpdate();
			return 0;
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
















