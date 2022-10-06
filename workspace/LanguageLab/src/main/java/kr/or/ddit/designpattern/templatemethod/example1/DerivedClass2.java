package kr.or.ddit.designpattern.templatemethod.example1;

public class DerivedClass2 extends TemplateClass {

	@Override
	protected void stepTwo() {
		System.out.println("2단계의 case2");
	}

}
