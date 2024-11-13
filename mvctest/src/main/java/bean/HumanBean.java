package bean;

import java.io.Serializable;

// DBから取得したレコードを保持するBean
public class HumanBean implements Serializable {
	// JavaBeansのフィールドはprivateにしなければならない(カプセル化)
	private String id;
	private String name;
	private int age;
	
	// アクセスメソッド(getter, setter)
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
}
