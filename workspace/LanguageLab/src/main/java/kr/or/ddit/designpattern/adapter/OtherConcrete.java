package kr.or.ddit.designpattern.adapter;

public class OtherConcrete implements Target {

	@Override
	public void request() {
		System.out.println("target 의 구현체 OtherConcrete 동작");
	}

}
