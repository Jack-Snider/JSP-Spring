package kr.or.ddit.board.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
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
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class}) //
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank(groups=InsertGroup.class) //
	private String boWriter;
	@NotBlank(groups=InsertGroup.class) //
	private String boIp;
	@Email
	private String boMail;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
}










