package kr.or.ddit.member.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

   @Override
   public int insertMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public MemberVO selectMember(String memId) {
      StringBuffer sql = new StringBuffer();
      
      sql.append("SELECT                                                    ");
       sql.append("MEM_ID,    MEM_PASS,    MEM_NAME,                         ");
       sql.append("MEM_REGNO1,    MEM_REGNO2,                                ");
       sql.append("TO_CHAR(MEM_BIR, 'YYYY-MM-DD') AS MEM_BIR,                ");
       sql.append("MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                        ");
       sql.append("MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                    ");
       sql.append("MEM_MAIL,    MEM_JOB,    MEM_LIKE,                        ");
       sql.append("MEM_MEMORIAL,                                             ");
       sql.append("TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') AS MEM_MEMORIALDAY,");   
       sql.append("MEM_MILEAGE,                                              ");
       sql.append("MEM_DELETE                                                ");
       sql.append("FROM                                                      ");
       sql.append("MEMBER                                                    ");
       sql.append("WHERE MEM_ID = ?                                    ");
      
      
      try(
         Connection conn = ConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         pstmt.setString(1, memId);
         ResultSet rs = pstmt.executeQuery();
         
         ResultSetMetaData rsmd = rs.getMetaData();
         
         MemberVO member = null;
         if(rs.next()) {
            member = (MemberVO) queryForObject( rs, MemberVO.class );

//            member = new MemberVO();
//            member.setMemId(rs.getString("MEM_ID"));
//            member.setMemPass(rs.getString("MEM_PASS"));
//            member.setMemName(rs.getString("MEM_NAME"));
//            member.setMemRegno1(rs.getString("MEM_REGNO1"));
//            member.setMemRegno2(rs.getString("MEM_REGNO2"));
//            member.setMemBir(rs.getString("MEM_BIR"));
//            member.setMemZip(rs.getString("MEM_ZIP"));
//            member.setMemAdd1(rs.getString("MEM_ADD1"));
//            member.setMemAdd2(rs.getString("MEM_ADD2"));
//            member.setMemHometel(rs.getString("MEM_HOMETEL"));
//            member.setMemComtel(rs.getString("MEM_COMTEL"));
//            member.setMemHp(rs.getString("MEM_HP"));
//            member.setMemMail(rs.getString("MEM_MAIL"));
//            member.setMemJob(rs.getString("MEM_JOB"));
//            member.setMemLike(rs.getString("MEM_LIKE"));
//            member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
//            member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
//            member.setMemMileage(rs.getInt("MEM_MILEAGE"));
//            member.setMemDelete(rs.getString("MEM_DELETE"));
         }
         return member;
      }catch(SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
         throw new RuntimeException(e);
      }
   }
   
   private Object queryForObject(ResultSet rs, Class<?> resultClass) throws SQLException, IntrospectionException,
    InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

       ResultSetMetaData rsmd = rs.getMetaData(); // rs의 메타데이터 저장
       int count = rsmd.getColumnCount(); // rs의 컬럼 갯수 저장
       String[] columnNames = new String[count]; // 배열에 컬럼명 저장
      
       for (int i = 1; i <= count; i++) {
          columnNames[i - 1] = rsmd.getColumnName(i); // 컬럼명 배열 초기화
      
       }
      
       Object retValue = resultClass.newInstance(); // 새로운 오브젝트 객체 하나 선언
      
       for (String col : columnNames) {
          String propertyName = snakeToCamel(col); // 스네이크 -> 카멜표기로 변경
          PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultClass); // 파라미터 : 가져올 필드, 주인객체 ( 클래스 타입 ) 해당 필드의 getter,setter 의 정보를 가지고있다.
          Class<?> propertyType = pd.getPropertyType(); // resultClass에 따라 Class타입 결정
          Method setter = pd.getWriteMethod(); // getWriteMethod는 반환받을 녀석( pd )의 setter가 된다. ( 해당 필드의 setter 메소드 반환 )
          Object propertyValue = null; // 필드의 타입이 아직 무엇인지 모르기 때문에 Object로 일단 선언
          if ( propertyType.equals( Integer.class ) ) {
             propertyValue = rs.getInt( col ); // 컬럼네임 앞에 ex) int rs.getInt("mem_id")
          } else {
             propertyValue = rs.getString( col ); // 컬럼네임 앞에 String
          }
          // invoke : 메소드 실행
          // reValue : 필드이름,  propertyValue : 클래스타입
          setter.invoke( retValue, propertyValue ); 
          // member.setMemId(peropertyValue);
       }
      
       return retValue;
      
      }
      
      private String snakeToCamel(String col) {
       System.out.println( "Before : " + col );
       
       col = col.toLowerCase();// col로 들어오는게 애초에 전부 대문자라 모두 소문자로 바꾸고 시작.
       
       boolean flag = false;
       
       StringBuffer toCamel = new StringBuffer();
       String[] tmp = new String[ col.length() ];
       
       for( int i = 0; i < tmp.length; i++ ){
          if( col.charAt( i ) == '_' ){
             tmp[i] = ""; // _ 없애기
             flag = true;
          }else if( flag ){
             String s = col.charAt( i ) + "";
             s = s.toUpperCase();
             tmp[i] = s;
             flag = false;
          }else{
             tmp[i] = col.charAt( i ) + "";
          }
       }
      
       for( int i = 0; i < tmp.length; i++ ){
          toCamel.append(tmp[i]);
       }
       
       System.out.println( "After : " + toCamel.toString() );
       return toCamel.toString();
       
       
      }


   @Override
   public List<MemberVO> selectMemberList() {
      
      //String sql = "SELECT MEM_ID, MEM_NAME, MEM_ADD1, MEM_ADD2, MEM_HP, MEM_MAIL, MEM_MILEAGE FROM MEMBER";
      StringBuffer sql = new StringBuffer(); 
      
      // 반복되지 않는 파트1
      sql.append("SELECT");
       sql.append(" MEM_ID, MEM_NAME, MEM_ADD1,");
       sql.append(" MEM_ADD2, MEM_HP, MEM_MAIL,");
       sql.append(" MEM_MILEAGE");
       sql.append(" FROM MEMBER");
      try(
         Connection conn = ConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         ResultSet rs = pstmt.executeQuery();
         
         // 반복되지 않는 파트2
         List<MemberVO> memberList = new ArrayList<MemberVO>();
         
         while(rs.next()) {
            MemberVO member = new MemberVO();
            member.setMemId(rs.getString("MEM_ID"));
            member.setMemName(rs.getString("MEM_NAME"));
            member.setMemMail(rs.getString("MEM_MAIL"));
            member.setMemHp(rs.getString("MEM_HP"));
            member.setMemAdd1(rs.getString("MEM_ADD1"));
            member.setMemAdd2(rs.getString("MEM_ADD2"));
            member.setMemMileage(rs.getInt("MEM_MILEAGE"));
            memberList.add(member);
         }
         
         return memberList;
         
      }catch(SQLException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public int updateMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int deleteMember(String memId) {
      // TODO Auto-generated method stub
      return 0;
   }

}