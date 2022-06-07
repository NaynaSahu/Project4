package in.co.sunrays.proj4.bean;

/**
 * @author Nayna
 *
 */
public class CourseBean extends BaseBean{
	
	private String courseName ;
	private String description;
	private String duration;
	public CourseBean() {
		// TODO Auto-generated constructor stub
	}
	

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String discription) {
		this.description = discription;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return courseName;
	}
	

}
