package kr.or.ddit.board.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 게시판 관리를 위한 Domain Layer
 *
 */
@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"boPass", "boContent"})
public class BoardVO {
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	@Email
	private String boMail;
	@NotBlank
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
}
