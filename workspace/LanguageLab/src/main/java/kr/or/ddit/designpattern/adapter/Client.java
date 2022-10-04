package kr.or.ddit.designpattern.adapter;

public class Client {
	
	public static void main( String ... args ) {
		
		Target target = new Adapter( new Adaptee() );
		target.request();
	}
}
