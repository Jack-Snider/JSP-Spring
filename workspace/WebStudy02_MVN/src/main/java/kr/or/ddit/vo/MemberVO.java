package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 관리(Domain Layer)
 * 
 * 한사람의 회원에 대한 정보(구매기록이 포함)
 * 	has 관계 
 *  has A (1:1)
 *  has Many (1:N)
 *
 *  2개 이상의 테이블을 조인하고 결과 바인딩하는 방법.
 *  1. 대상 테이블 간의 관계 파악.
 *  	MEMBER(1) : PROD(N)
 *  	PROD(1) : BUYER(1)
 *  	PROD(1) : MEMBER(N)
 *  2. 각 테이블로부터 데이터를 바인딩할 VO 정의
 *     해당 VO 간의 관계를 형성.
 *     MemberVO has Many ProdVO
 *     ProdVO has A BuyerVO
 *     ProdVO has Many MemberVO
 *  3. 조인 쿼리 작성
 *  4. resultMap 을 사용하여 결과 바인딩(수동)
 *  	has Many ->  collection   
 *  	has A -> association
 */
@Slf4j
@Data
@EqualsAndHashCode(of="memId")
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"})
@MultipartConfig
public class MemberVO implements Serializable{
	
	private int rnum;
	
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12, groups = {Default.class, DeleteGroup.class})
	@JsonIgnore
	private transient String memPass;
	@NotBlank
	private String memName;
	@NotBlank(groups = {InsertGroup.class})
	@JsonIgnore
	private transient String memRegno1;
	@NotBlank(groups = {InsertGroup.class})
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@Email
	@NotBlank
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	@Min(0)
	private Integer memMileage;
	private Boolean memDelete;
	
	private Set<ProdVO> prodList; // has Many
	
	private String memRole;
	
	// 프로필 이미지를 위한 필드
	private byte[] memImg; // 서버가 DB에 저장하는 용도
	private MultipartFile memImage; // 클라이언트로부터 받는 용도
	
	
	// 이미지가 이미 있나 없나 확인
	public void setMemImage( MultipartFile memImage ) throws IOException {
		
		if( memImage == null || memImage.isEmpty() ) return; // 이미지가 비어있을 경우
		
		this.memImage = memImage;
		this.memImg = memImage.getBytes();
		
	}
	
	// base64로 인코딩 하기 위한 메소드 ( 64개의 문자로 인코딩 )
	public String getBase64Img() {
		if( memImg == null ) {
			// 변환할게 없을 경우
			return null;
		}else {
			// 변환할게 있을 경우
			String base64Text = Base64.getEncoder().encodeToString( memImg );
			log.info( "base64 encoded text : {}", base64Text );
			return base64Text;
		}
	}
	
	public void saveTo( File saveFolder ) throws IOException {
		if(memImage==null) return;
		File saveFile = new File( saveFolder, this.memImg.toString() );
		memImage.transferTo(saveFile);
	}
	
	
	
	public String getMemTest() {
		return "테스트";
	}
}













