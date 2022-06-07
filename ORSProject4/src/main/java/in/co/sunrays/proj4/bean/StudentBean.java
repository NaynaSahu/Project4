package in.co.sunrays.proj4.bean;

import java.util.Date;

/**
 * @author Nayna
 *
 */
public class StudentBean extends BaseBean {

	private String firstName;
	private String lastName;
	private Date dob;
	private String mobileNo;
	private String email;
	private long collegeId;
	private String collegeName;

	public StudentBean() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int i) {
		this.collegeId = i;
	}

	public String getCollegeName() {
		return collegeName;
	}

	
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		String fullName = firstName + " " + lastName;
		return fullName;
	}

}
