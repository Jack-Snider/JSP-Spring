package kr.or.ddit.object;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



	@JacksonXmlRootElement( localName = "root" )
	//익명 클래스
	public  class TestVO implements Serializable{
		/*
		 * VO, JavaBean, DTO 모두 비슷한 개념
		 * 
		 * 이들 모두의 규칙성.
		 * 1. 값을 담을 수 있는 PROPERTY가 필요하다.
		 * 2. 적절한 형태의 캡슐화가 필요하다.
		 * 3. 제어를 할 수 있어야 한다. ( GETTER / SETTER )
		 * 4. 객체안에 저장되있는 데이터가 있다면 그 상태를 비교할 수 있어야 한다. ( HashCode, equals , Compare )
		 * 5. 상태를 비교하면 그 상태를 확인할 수 있어야 한다. ( toString )
		 * 6. Serialization을 상속받아야 한다. ( 직렬화 )
		 * 7. 보호해야되는 상태가 있다면 적절한 상태의 키워드가 있어야 한다. ( transient : 직렬화에서 제외시키는 property )
		 */
		
		private String prop1;
		
		@JsonIgnore
		private transient String prop2;
		// transient는 직렬화 과정에서 보호해야될 데이터기 때문에 제외시킨다.
		// **transient는 직렬화에서 제외될 뿐이지 Marshalling에서는 제외되지 않는다.
		// 그래서 @JsonIgnore라는 어노테이션을 붙혀주면 해당 필드는 Marshalling을 무시할 수 있다. 
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((prop1 == null) ? 0 : prop1.hashCode());
			result = prime * result + ((prop2 == null) ? 0 : prop2.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestVO other = (TestVO) obj;
			if (prop1 == null) {
				if (other.prop1 != null)
					return false;
			} else if (!prop1.equals(other.prop1))
				return false;
			if (prop2 == null) {
				if (other.prop2 != null)
					return false;
			} else if (!prop2.equals(other.prop2))
				return false;
			return true;
		}
		
		
		public String getProp1() {
			return prop1;
		}
		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}
		public String getProp2() {
			return prop2;
		}
		public void setProp2(String prop2) {
			this.prop2 = prop2;
		}

		@Override
		public String toString() {
			return "TestVO [prop1=" + prop1 + ", prop2=" + prop2 + ", hashCode()=" + hashCode() + ", getProp1()="
					+ getProp1() + ", getProp2()=" + getProp2() + ", getClass()=" + getClass() + ", toString()="
					+ super.toString() + "]";
		}
		
		
		
		
	}
	
