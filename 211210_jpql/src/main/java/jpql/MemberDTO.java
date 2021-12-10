package jpql;

public class MemberDTO {
	
	private String username;
	private int age;
	
	//new jpql.MemberDTO(m.username, m.age) 사용하기 위해서 만든 생성자
	public MemberDTO(String username, int age) { 	
		this.username = username;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
