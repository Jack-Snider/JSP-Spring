package kr.or.ddit.board.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

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
@ToString(exclude= {"boPass", "boContent", "boFiles", "attatchList"})
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
	
	private List<MultipartFile> boFiles;
	public void setBoFiles(List<MultipartFile> boFiles) {
		if(boFiles==null || boFiles.isEmpty()) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile file  : boFiles) {
			if(file.isEmpty()) continue;
			attatchList.add(new AttatchVO(file));
		}
	}
	
	private int startNo;
	private List<AttatchVO> attatchList;
}










