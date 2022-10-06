package kr.or.ddit.designpattern.templatemethod;

import kr.or.ddit.designpattern.templatemethod.example1.DerivedClass1;
import kr.or.ddit.designpattern.templatemethod.example1.DerivedClass2;
import kr.or.ddit.designpattern.templatemethod.example1.TemplateClass;

public class Example1Ground {
	public static void main(String[] args) {
		TemplateClass[] array = new TemplateClass[] {
			new DerivedClass1()
			, new DerivedClass2()
		};
		
		for(TemplateClass obj : array) {
			obj.template();
		}
	}
}
