package in.co.sunrays.proj4.bean;

public class RoleBean extends BaseBean {
	public static final int ADMIN=1;
	public static final int STUDENT=2;
	public static final int COLLEGE_SCHOOL=3;
	public static final int KIOSK=4;
	public String name;
	public String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
