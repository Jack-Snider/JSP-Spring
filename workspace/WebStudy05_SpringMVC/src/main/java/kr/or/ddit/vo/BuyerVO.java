package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable {
	private int rnum;
	
	@NotBlank(groups=UpdateGroup.class)
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	
	private String lprodNm;
	
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	@NotBlank
	private String buyerZip;
	@NotBlank
	private String buyerAdd1;
	@NotBlank
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	@NotBlank
	private String buyerCharger;
	private String buyerTelext;
	
//	해당 거래처와의 거래 품목수.
	private int prodCount;
	
//	 해당 거래처와의 거래 품목 리스트.
	private List<ProdVO> prodList;
}















