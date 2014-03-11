package Ajax;

import java.util.List;
import java.util.Set;

import model.CourseTemplate;

public interface SearchCourseTemplate {

	public Set<CourseTemplate> getbyName(String name);

	public CourseTemplate getbyName1(int id);
}
