package kr.or.ddit.designpattern.adapter;

public class OtherConcrete implements Target {

	@Override
	public void request() {

		System.out.println( "target의 구현체 OhterConcrete 동작" );

	}

}
