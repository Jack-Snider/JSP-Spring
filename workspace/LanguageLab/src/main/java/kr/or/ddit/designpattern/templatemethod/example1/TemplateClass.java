package kr.or.ddit.designpattern.templatemethod.example1;

public abstract class TemplateClass { // 하나의 클래스에 추상 메소드가 하나 이상 있으면 추상 클래스가 되야 한다.
	
	public final void template() {
		stepOne();
		stepTwo();
		stepThree();
	}
	
	private void stepOne() { // -stepOne()
		System.out.println( "1단계" );
	}
	
	protected abstract void stepTwo(); // #stepTwo()
	private void stepThree() { // -stepThree()
		System.out.println( "3단계" );
	}
	
	
}
