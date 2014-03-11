package business;

import java.util.List;
import java.util.Set;

import model.CourseTemplate;

public interface CourseTemplateBO {
	// public final int NoPerPage = 10;
	public Set<CourseTemplate> getCourseTemplate();

	public CourseTemplate getById(int id);

	public void DeletebyID(int id);

	public Set<CourseTemplate> getByName(String name);

	public Set<CourseTemplate> search(int id, String name);

	public boolean saveCourseTemplate(CourseTemplate entity);

	public boolean updateCourseTemplate(CourseTemplate entity);

	public boolean deleteCourseTemplate(CourseTemplate entity);

	public int CountTemplate();

	public int CountTemplate(String name);

	public Set<Integer> CountTemplatePages();

	public Set<Integer> CountTemplatePages(String name);

	public Set<CourseTemplate> Search(String name, int pageNumber);

	public Set<CourseTemplate> search(int pageNumber);

	public Set<CourseTemplate> search(String name, String subject,
			int pageNumber);
	public boolean CheckName(String name);
	public boolean flushCourse();

	public boolean closeCourse();
}
