package dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

import model.*;
import dao.*;

public class CourseTemplateDAOimpl extends
		AbstractHibernateDaoSupport<CourseTemplate, Integer> implements
		CourseTemplateDAO {

	protected CourseTemplateDAOimpl() {
		super(CourseTemplate.class);
	}

	@Override
	public Set<CourseTemplate> getCourseTemplate() {
		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = findAll().iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		// TODO Auto-generated method stub
		return ct;
	}

	@Override
	public CourseTemplate getById(int id) {
		// TODO Auto-generated method stub
		return findById(id);
	}

	@Override
	public Set<CourseTemplate> getByName(String name) {
		// TODO Auto-generated method stub

		Criteria crit = getSession().createCriteria(CourseTemplate.class);
		crit.add(Restrictions.like("name", "%" + name + "%"));
		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = crit.list().iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		// TODO Auto-generated method stub
		return ct;
	}

	@Override
	public Set<CourseTemplate> search(int id, String name) {
		// TODO Auto-generated method stub
		Criteria crit = getSession().createCriteria(CourseTemplate.class);
		crit.add(Restrictions.and(Restrictions.eq("id", id),
				Restrictions.like("name", "%" + name + "%")));
		crit.addOrder(Order.asc("name"));

		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = crit.list().iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		// TODO Auto-generated method stub
		return ct;
	}

	@Override
	public boolean saveCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub
		System.out.println("save1()");
		// DAO.deleteCourseTemplate(ct);
		// DAO.DeletebyID(id);
		// getSession().beginTransaction();
		// ss.beginTransaction();
		HibernateUtil.beginTransaction();
		// CourseTemplate c = (CourseTemplate)ss.get(CourseTemplate.class, id);
		save(entity);

		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public boolean updateCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub
		// System.out.println(ct.getName());
		System.out.println("Update1()");
		// DAO.deleteCourseTemplate(ct);
		// DAO.DeletebyID(id);
		// getSession().beginTransaction();
		// ss.beginTransaction();
		HibernateUtil.beginTransaction();
		// CourseTemplate c = (CourseTemplate)ss.get(CourseTemplate.class, id);
		update(entity);

		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public boolean deleteCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub

		// getSession().beginTransaction();
		// HibernateUtil.beginTransaction();
		// ss.beginTransaction();
		HibernateUtil.beginTransaction();
		// CourseTemplate c = (CourseTemplate)ss.get(CourseTemplate.class, id);
		delete(entity);

		HibernateUtil.commitTransaction();

		return true;
	}

	@Override
	public boolean flushCourse() {
		return flush();
	}

	@Override
	public boolean closeCourse() {
		// TODO Auto-generated method stub
		return close();
	}

	@Override
	public void DeletebyID(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from CourseTemplate where id = " + id
				+ " ON DELETE CASCADE";
		Query query = getSession().createQuery(hql);
		// query.setString("name", id);
		int rowCount = query.executeUpdate();

	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		// Query query =
		// getSession().createQuery("select count(*) from CourseTemplate");
		int count = ((Long) getSession().createQuery(
				"select count(*) from CourseTemplate").uniqueResult())
				.intValue();
		/*
		 * Long count = (Long)
		 * getSession().createQuery("select count(*) from CourseTemplate"
		 * ).uniqueResult();
		 */
		// return (Integer)
		// getSession().createCriteria(CourseTemplate.class).setProjection(Projections.rowCount()).uniqueResult();
		return count;

	}

	@Override
	public Set<CourseTemplate> Search(String name, int pageNumber) {
		int quality = 5;
		Session ss = HibernateUtil.getSession();
		Criteria session = ss.createCriteria(CourseTemplate.class);
		@SuppressWarnings("unchecked")
		List<CourseTemplate> ct1 = session
				.add(Restrictions.like("name", "%" + name + "%"))
				.setFirstResult((pageNumber - 1) * quality)
				.setMaxResults(quality)
				.addOrder(Order.asc("name")).list();

		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = ct1.iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		HibernateUtil.commitTransaction();
		// TODO Auto-generated method stub
		return ct;
		
		
	}

	@Override
	public Set<CourseTemplate> search(int pageNumber) {
		// TODO Auto-generated method stub
		/*
		 * int quality = 1; Query session =
		 * getSession().createQuery("from CourseTemplate" );
		 * List<CourseTemplate> ct =
		 * session.setFirstResult((pageNumber-1)*quality
		 * ).setMaxResults(quality).list();
		 */
		// cach 2

		int quality = 5;
		Session ss = HibernateUtil.getSession();
		Criteria session = ss.createCriteria(CourseTemplate.class);
		session.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<CourseTemplate> ct1 = session
				.setFirstResult((pageNumber - 1) * quality)
				.setMaxResults(quality).list();
		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = ct1.iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		HibernateUtil.commitTransaction();
		// TODO Auto-generated method stub
		return ct;
	}

	@Override
	public Set<CourseTemplate> search(String name, String subject,
			int pageNumber) {
		int quality = 5;
		Session ss = HibernateUtil.getSession();
		Criteria session = ss.createCriteria(CourseTemplate.class,
				"CourseTemplate");
		session.add(Restrictions.like("name", "%" + name + "%"));
		session.setFetchMode("Subject", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<CourseTemplate> ct1 = session
				.add(Restrictions.like("CourseTemplate.subjectName", "%"
						+ subject + "%"))
				.setFirstResult((pageNumber - 1) * quality)
				.setMaxResults(quality).list();
		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = ct1.iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		HibernateUtil.commitTransaction();
		// TODO Auto-generated method stub
		return ct;

	}

	@Override
	public long count(String name) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(CourseTemplate.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		criteria.setProjection(Projections.rowCount());

		return ((Long) criteria.list().get(0)).intValue();

	}

	@Override
	public Set<CourseTemplate> getByIds(int id) {
		Criteria crit = getSession().createCriteria(CourseTemplate.class);
		crit.add(Restrictions.eq("id", id));
		crit.addOrder(Order.asc("name"));
		Set<CourseTemplate> ct = new HashSet<CourseTemplate>();
		Iterator<CourseTemplate> lct = crit.list().iterator();
		while(lct.hasNext())
		{
			CourseTemplate courseTemplate = lct.next();
			ct.add(courseTemplate);
		}
		HibernateUtil.commitTransaction();
		// TODO Auto-generated method stub
		return ct;
	}

}
