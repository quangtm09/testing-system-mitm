package Ajax;

import java.util.List;
import java.util.Set;

import business.CourseTemplateBO;

import model.CourseTemplate;

public class SearchCoursesTemplateimpl implements SearchCourseTemplate {

	private CourseTemplateBO courseTemplateBO;

	@Override
	public Set<CourseTemplate> getbyName(String name) {
		// TODO Auto-generated method stub
		return this.courseTemplateBO.getByName(name);
	}

	@Override
	public CourseTemplate getbyName1(int id) {
		// TODO Auto-generated method stub
		return this.courseTemplateBO.getById(id);
	}

}
