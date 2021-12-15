package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

//클래스를 따로 만들어주고 persistence.xml hibernate.dialect value 설정해주기
//<property name="hibernate.dialect" value="dialect.MyH2Dialect"/>
public class MyH2Dialect extends H2Dialect {

	public MyH2Dialect() {
		registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
	}
}
