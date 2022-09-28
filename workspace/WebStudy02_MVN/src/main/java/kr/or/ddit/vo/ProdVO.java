package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"prodDetail"})
@EqualsAndHashCode(of = {"prodId"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdVO implements Serializable{
	private String prodId;
	private String prodName;
	private String prodLgu;
	
	private String lprodNm;
	
	private String prodBuyer;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	@JsonIgnore
	private transient String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private BuyerVO buyer; // has A
	
	private Set<MemberVO> memberList; // has Many
	
	private Integer memCount;
}











