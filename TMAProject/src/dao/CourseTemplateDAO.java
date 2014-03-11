package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import model.CourseTemplate;

public interface CourseTemplateDAO extends Dao<CourseTemplate, Integer> {
	public Set<CourseTemplate> getCourseTemplate();

	public CourseTemplate getById(int id);

	public Set<CourseTemplate> getByIds(int id);

	public void DeletebyID(int id);

	public Set<CourseTemplate> getByName(String name);

	public Set<CourseTemplate> search(int id, String name);

	public boolean saveCourseTemplate(CourseTemplate entity);

	public boolean updateCourseTemplate(CourseTemplate entity);

	public boolean deleteCourseTemplate(CourseTemplate entity);

	public int count();

	public Set<CourseTemplate> Search(String name, int pageNumber);

	public Set<CourseTemplate> search(int pageNumber);

	public long count(String name);

	public Set<CourseTemplate> search(String name, String subject,
			int pageNumber);

	public boolean flushCourse();

	public boolean closeCourse();

}