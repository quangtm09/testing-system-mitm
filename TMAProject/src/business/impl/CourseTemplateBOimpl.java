package business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

import dao.CourseTemplateDAO;
import dao.SubjectDAO;

import model.Account;
import model.CourseTemplate;
import model.Permission;
import model.Person;
import model.Role;
import model.Subject;
import business.CourseTemplateBO;

public class CourseTemplateBOimpl implements CourseTemplateBO {

	private String id;
	private String name;
	private CourseTemplateDAO coursetemplatedao;

	private SubjectDAO subjectDAO;
	private Integer subjectId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseTemplateDAO getCoursetemplatedao() {
		return coursetemplatedao;
	}

	public void setCoursetemplatedao(CourseTemplateDAO coursetemplatedao) {
		this.coursetemplatedao = coursetemplatedao;
	}

	public void init() {
//		HibernateUtil.beginTransaction();
//
//		Subject s1 = new Subject("Hibernate",
//				"What is hibernate? How to use? Advantages and drawbacks?");
//
//		Subject s2 = new Subject("Spring",
//				"What is Spring? How to use? Advantages and drawbacks?");
//
//		Subject s3 = new Subject("Operating System", "What is OS?");
//
//		Subject s4 = new Subject("Principles of Programming Language",
//				"What is PPL?");
//
//		Subject s5 = new Subject("Computer Architecture", "What is CA?");
//
//		Subject s6 = new Subject("Data Structure & Algorithms",
//				"What is DSA?");
//
//		Subject s7 = new Subject("Network Programming", "What is NP?");
//
//		Subject s8 = new Subject("Web Application Development",
//				"What is WAD?");
//
//		Subject s9 = new Subject("Liferay Social Network", "What is LSN?");
//
//		Subject s10 = new Subject("Artificial Intelligence",
//				"What is AI?");
//
//		Subject s11 = new Subject("Calculus 1",
//				"What should we do in this course?");
//
//		Subject s12 = new Subject("Calculus 2",
//				"What should we do in this course?");
//
//		Subject s13 = new Subject("Calculus 3",
//				"What should we do in this course?");
//
//		Subject s14 = new Subject("Physics 1",
//				"What should we do in this course?");
//
//		Subject s15 = new Subject("Physics 2",
//				"What should we do in this course?");
//
//		Subject s16 = new Subject("Physics 3",
//				"What should we do in this course?");
//
//		Subject s17 = new Subject("Information System Management",
//				"What should we do in this course?");
//
//		Subject s18 = new Subject("Academic Writing",
//				"What should we do in this course?");
//
//		Subject s19 = new Subject("IELTS",
//				"What should we do in this course?");
//
//		Subject s20 = new Subject("TOEFL",
//				"What should we do in this course?");
//
//		
//		CourseTemplate ct1 = new CourseTemplate("K18", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s1.getCourseTemplate().add(ct1);
//		s2.getCourseTemplate().add(ct1);
//		s3.getCourseTemplate().add(ct1);
//		s4.getCourseTemplate().add(ct1);
//		ct1.getSubject().add(s1);
//		ct1.getSubject().add(s2);
//		ct1.getSubject().add(s3);
//		ct1.getSubject().add(s4);
//		this.coursetemplatedao.save(ct1);
//
//		CourseTemplate ct2 = new CourseTemplate("K17", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s2.getCourseTemplate().add(ct2);
//		ct2.getSubject().add(s2);
//		this.coursetemplatedao.save(ct2);
//
//		CourseTemplate ct3 = new CourseTemplate("K16", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s3.getCourseTemplate().add(ct3);
//		ct3.getSubject().add(s3);
//		this.coursetemplatedao.save(ct3);
//
//		CourseTemplate ct4 = new CourseTemplate("K15", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s4.getCourseTemplate().add(ct4);
//		ct4.getSubject().add(s4);
//		this.coursetemplatedao.save(ct4);
//
//		CourseTemplate ct5 = new CourseTemplate("K14", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s5.getCourseTemplate().add(ct5);
//		ct5.getSubject().add(s5);
//		this.coursetemplatedao.save(ct5);
//
//		CourseTemplate ct6 = new CourseTemplate("K13", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s6.getCourseTemplate().add(ct6);
//		ct6.getSubject().add(s6);
//		this.coursetemplatedao.save(ct6);
//
//		CourseTemplate ct7 = new CourseTemplate("K12", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s7.getCourseTemplate().add(ct7);
//		ct7.getSubject().add(s7);
//		this.coursetemplatedao.save(ct7);
//
//		CourseTemplate ct8 = new CourseTemplate("K11", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s8.getCourseTemplate().add(ct8);
//		ct8.getSubject().add(s8);
//		this.coursetemplatedao.save(ct8);
//
//		CourseTemplate ct9 = new CourseTemplate("K10", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s9.getCourseTemplate().add(ct9);
//		ct9.getSubject().add(s9);
//		this.coursetemplatedao.save(ct9);
//
//		CourseTemplate ct10 = new CourseTemplate("K09", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s10.getCourseTemplate().add(ct10);
//		ct10.getSubject().add(s10);
//		this.coursetemplatedao.save(ct10);
//
//		CourseTemplate ct11 = new CourseTemplate("K08", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s11.getCourseTemplate().add(ct11);
//		ct11.getSubject().add(s11);
//		this.coursetemplatedao.save(ct11);
//
//		CourseTemplate ct12 = new CourseTemplate("K07", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s12.getCourseTemplate().add(ct12);
//		ct12.getSubject().add(s12);
//		this.coursetemplatedao.save(ct12);
//
//		CourseTemplate ct13 = new CourseTemplate("K06", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s13.getCourseTemplate().add(ct13);
//		ct13.getSubject().add(s13);
//		this.coursetemplatedao.save(ct13);
//
//		CourseTemplate ct14 = new CourseTemplate("K05", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s14.getCourseTemplate().add(ct14);
//		ct14.getSubject().add(s14);
//		this.coursetemplatedao.save(ct14);
//
//		CourseTemplate ct15 = new CourseTemplate("K04", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s15.getCourseTemplate().add(ct15);
//		ct15.getSubject().add(s15);
//		this.coursetemplatedao.save(ct15);
//
//		CourseTemplate ct16 = new CourseTemplate("K03", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s16.getCourseTemplate().add(ct16);
//		ct16.getSubject().add(s16);
//		this.coursetemplatedao.save(ct16);
//
//		CourseTemplate ct17 = new CourseTemplate("K02", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s17.getCourseTemplate().add(ct17);
//		ct17.getSubject().add(s17);
//		this.coursetemplatedao.save(ct17);
//
//		CourseTemplate ct18 = new CourseTemplate("K01", new GregorianCalendar(
//				1999, 9, 3), new GregorianCalendar(1999, 10, 3),
//				"JAVA for Everyone");
//
//		s18.getCourseTemplate().add(ct18);
//		ct18.getSubject().add(s18);
//		this.coursetemplatedao.save(ct18);
//		// flushCourse();
//		System.out.println("Update");
//		HibernateUtil.commitTransaction();
//		
//		//SessionFactory sf = HibernateUtil.getSessionFactory();
//		Session ss = HibernateUtil.getSession();
//		ss.beginTransaction();
//
//		Permission adminPermission1 = new Permission("loginPermission", true);
//		ss.save(adminPermission1);
//		Permission adminPermission2 = new Permission("editPermission", true);
//		ss.save(adminPermission2);
//		Permission adminPermission3 = new Permission("viewPermission", true);
//		ss.save(adminPermission3);
//		Permission adminPermission4 = new Permission("managePermission", true);
//		ss.save(adminPermission4);
//
//		Role adminRole = new Role("Admin");
//		adminRole.getPermissionLists().add(adminPermission1);
//		adminRole.getPermissionLists().add(adminPermission2);
//		adminRole.getPermissionLists().add(adminPermission3);
//		adminRole.getPermissionLists().add(adminPermission4);
//		ss.save(adminRole);
//
//		Permission traineePermission1 = new Permission("loginPermission", true);
//		ss.save(traineePermission1);
//		Permission traineePermission2 = new Permission("editPermission", true);
//		ss.save(traineePermission2);
//		Permission traineePermission3 = new Permission("viewPermission", false);
//		ss.save(traineePermission3);
//		Permission traineePermission4 = new Permission("managePermission",
//				false);
//		ss.save(traineePermission4);
//
//		Role traineeRole = new Role("Trainee");
//		traineeRole.getPermissionLists().add(traineePermission1);
//		traineeRole.getPermissionLists().add(traineePermission2);
//		traineeRole.getPermissionLists().add(traineePermission3);
//		traineeRole.getPermissionLists().add(traineePermission4);
//		ss.save(traineeRole);
//
//		Permission trainerPermission1 = new Permission("loginPermission", true);
//		ss.save(trainerPermission1);
//		Permission trainerPermission2 = new Permission("editPermission", true);
//		ss.save(trainerPermission2);
//		Permission trainerPermission3 = new Permission("viewPermission", false);
//		ss.save(trainerPermission3);
//		Permission trainerPermission4 = new Permission("managePermission",
//				false);
//		ss.save(trainerPermission4);
//
//		Role trainerRole = new Role("Trainer");
//		trainerRole.getPermissionLists().add(trainerPermission1);
//		trainerRole.getPermissionLists().add(trainerPermission2);
//		trainerRole.getPermissionLists().add(trainerPermission3);
//		trainerRole.getPermissionLists().add(trainerPermission4);
//		ss.save(trainerRole);
//
//		Permission coordinatorPermission1 = new Permission("loginPermission",
//				true);
//		ss.save(coordinatorPermission1);
//		Permission coordinatorPermission2 = new Permission("editPermission",
//				true);
//		ss.save(coordinatorPermission2);
//		Permission coordinatorPermission3 = new Permission("viewPermission",
//				false);
//		ss.save(coordinatorPermission3);
//		Permission coordinatorPermission4 = new Permission("managePermission",
//				false);
//		ss.save(coordinatorPermission4);
//
//		Role coordinatorRole = new Role("Coordinator");
//		coordinatorRole.getPermissionLists().add(coordinatorPermission1);
//		coordinatorRole.getPermissionLists().add(coordinatorPermission2);
//		coordinatorRole.getPermissionLists().add(coordinatorPermission3);
//		coordinatorRole.getPermissionLists().add(coordinatorPermission4);
//		ss.save(coordinatorRole);
//
//		Person person1 = new Person("Trung Huy Thu", "0938641688",
//				"trunghuythu@gmail.com");
//		Person person2 = new Person("Voong Thu Nhan", "0909569156",
//				"thanhnhankmm@gmail.com");
//		Person person3 = new Person("Nguyen Duy Anh", "0935571991",
//				"nda1291@yahoo.com");
//		Person person4 = new Person("Nguyen Duc Dinh Nghia", "0905616016",
//				"nddnghia@gmail.com");
//		Person person5 = new Person("Tran Minh Quang", "0945148959",
//				"tranminhquang2209@gmail.com");
//		Person person6 = new Person("Lam Nhu Y", "01666142813",
//				"lny102@gmail.com");
//		Person person7 = new Person("Nguyen Duy Tuan Quang", "01265333445",
//				"zusquang@gmail.com");
//		Person person8 = new Person("Dang Quoc Dat", "905231689",
//				"datdq295@gmail.com");
//		Person person9 = new Person("Nguyen Thi Nhu Ngoc", "1224986341",
//				"nhungoc.nguyen90@gmail.com");
//		Person person10 = new Person("Hoang Xuan Nam", "0988357016",
//				"eric_hoang@live.com");
//		Person person11 = new Person("Truong Trong Nghia", "01267250431",
//				"kendi382@gmail.com");
//		Person person12 = new Person("Pham Thi My Trang", "01224951595",
//				"mytrangptit@gmail.com");
//		Person person13 = new Person("Nguyen Tan Loc", "0937388111",
//				"tanloc55555@gmail.com");
//		Person person14 = new Person("Nguyen Thanh Phuoc", "0969320426",
//				"phuocnguyen8@vanlanguni.vn");
//		Person person15 = new Person("Chau Kinh To", "0982788582",
//				"ckto@tma.com.vn");
//
//		Account account1 = new Account("admin", "admin");
//		Account account2 = new Account("nhank18", "123");
//		Account account3 = new Account("duyanhk18", "123");
//		Account account4 = new Account("dinhnghiak18", "123");
//		Account account5 = new Account("minhquangk18", "123");
//		Account account6 = new Account("nhuyk18", "123");
//		Account account7 = new Account("tuanquangk18", "123");
//		Account account8 = new Account("quocdatk18", "123");
//		Account account9 = new Account("nhungock18", "123");
//		Account account10 = new Account("xuannam18", "123");
//		Account account11 = new Account("trongnghia18", "123");
//		Account account12 = new Account("mytrangk18", "123");
//		Account account13 = new Account("tanlock18", "123");
//		Account account14 = new Account("thanhphuock18", "123");
//		Account account15 = new Account("ckto", "123");
//
//		account1.setRole(adminRole);
//		account1.setPerson(person1);
//		ss.save(account1);
//
//		account2.setRole(trainerRole);
//		account2.setPerson(person2);
//		ss.save(account2);
//
//		account3.setRole(coordinatorRole);
//		account3.setPerson(person3);
//		ss.save(account3);
//
//		account4.setRole(traineeRole);
//		account4.setPerson(person4);
//		ss.save(account4);
//
//		account5.setRole(traineeRole);
//		account5.setPerson(person5);
//		ss.save(account5);
//
//		account6.setRole(traineeRole);
//		account6.setPerson(person6);
//		ss.save(account6);
//
//		account7.setRole(traineeRole);
//		account7.setPerson(person7);
//		ss.save(account7);
//
//		account8.setRole(traineeRole);
//		account8.setPerson(person8);
//		ss.save(account8);
//
//		account9.setRole(traineeRole);
//		account9.setPerson(person9);
//		ss.save(account9);
//
//		account10.setRole(traineeRole);
//		account10.setPerson(person10);
//		ss.save(account10);
//
//		account11.setRole(traineeRole);
//		account11.setPerson(person11);
//		ss.save(account11);
//
//		account12.setRole(traineeRole);
//		account12.setPerson(person12);
//		ss.save(account12);
//
//		account13.setRole(traineeRole);
//		account13.setPerson(person13);
//		ss.save(account13);
//
//		account14.setRole(traineeRole);
//		account14.setPerson(person14);
//		ss.save(account14);
//
//		account15.setRole(traineeRole);
//		account15.setPerson(person15);
//		ss.save(account15);
//
//		person1.setAccount(account1);
//		ss.save(person1);
//
//		person2.setAccount(account2);
//		ss.save(person2);
//
//		person3.setAccount(account3);
//		ss.save(person3);
//
//		person4.setAccount(account4);
//		ss.save(person4);
//
//		person5.setAccount(account5);
//		ss.save(person5);
//
//		person6.setAccount(account6);
//		ss.save(person6);
//
//		person7.setAccount(account7);
//		ss.save(person7);
//
//		person8.setAccount(account8);
//		ss.save(person8);
//
//		person9.setAccount(account9);
//		ss.save(person9);
//
//		person10.setAccount(account10);
//		ss.save(person10);
//
//		person11.setAccount(account11);
//		ss.save(person11);
//
//		person12.setAccount(account12);
//		ss.save(person12);
//
//		person13.setAccount(account13);
//		ss.save(person13);
//
//		person14.setAccount(account14);
//		ss.save(person14);
//
//		person15.setAccount(account15);
//		ss.save(person15);
//		
//		ss.getTransaction().commit();
//		

	}

	@Override
	public Set<CourseTemplate> getCourseTemplate() {
		// TODO Auto-generated method stub
		return coursetemplatedao.getCourseTemplate();
	}

	@Override
	public CourseTemplate getById(int id) {
		// TODO Auto-generated method stub
		return  this.coursetemplatedao.getById(id);
	}
 
	@Override
	public Set<CourseTemplate> getByName(String name) {
		// TODO Auto-generated method stub
		return coursetemplatedao.getByName(name);
	}

	@Override
	public Set<CourseTemplate> search(int id, String name) {
		// TODO Auto-generated method stub
		HibernateUtil.beginTransaction();
		Set<CourseTemplate> courseTemplate = this.coursetemplatedao.search(id, name);
		HibernateUtil.commitTransaction();
		return courseTemplate;
	}

	@Override
	public boolean saveCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.save(entity);
	}

	@Override
	public boolean updateCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.updateCourseTemplate(entity);
	}

	@Override
	public boolean deleteCourseTemplate(CourseTemplate entity) {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.deleteCourseTemplate(entity);
	}

	@Override
	public boolean flushCourse() {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.flushCourse();
	}

	@Override
	public boolean closeCourse() {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.closeCourse();
	}

	@Override
	public void DeletebyID(int id) {
		// TODO Auto-generated method stub
		CourseTemplate ct = this.coursetemplatedao.getById(id);
		// System.out.println(ct.getName());
		System.out.println("delete()");
		// DAO.deleteCourseTemplate(ct);
		// DAO.DeletebyID(id);
		

		// CourseTemplate c = (CourseTemplate)ss.get(CourseTemplate.class, id);
		this.coursetemplatedao.deleteCourseTemplate(ct);

		
	}

	@Override
	public int CountTemplate() {

		return this.coursetemplatedao.count();
	}

	@Override
	public Set<CourseTemplate> Search(String name, int pageNumber) {
		// TODO Auto-generated method stub
		HibernateUtil.beginTransaction();
		Set<CourseTemplate> ct = this.coursetemplatedao.Search(name, pageNumber);
		HibernateUtil.commitTransaction();
		return ct;
	}

	@Override
	public Set<CourseTemplate> search(int pageNumber) {
		HibernateUtil.beginTransaction();
		Set<CourseTemplate> ct = this.coursetemplatedao.search(pageNumber);
		HibernateUtil.commitTransaction();
		return ct;
	}

	@Override
	public Set<CourseTemplate> search(String name, String subject,
			int pageNumber) {
		// TODO Auto-generated method stub
		return this.coursetemplatedao.search(name, subject, pageNumber);
	}

	@Override
	public Set<Integer> CountTemplatePages() {
		Set<Integer> pages = new HashSet<Integer>();
		int i = (int) (Math.ceil((double) this.CountTemplate() / 5));
		// int k=1;
		while (i > 0) {
			pages.add(i);
			i--;
		}
		return pages;
	}

	@Override
	public Set<Integer> CountTemplatePages(String name) {
		// TODO Auto-generated method stub
		Set<Integer> pages = new HashSet<Integer>();
		int i = (int) (Math
				.ceil((double) this.coursetemplatedao.count(name) / 5));
		int k = 1;
		while (k <= i) {
			pages.add(k);
			k++;
		}
		return pages;
	}

	@Override
	public int CountTemplate(String name) {
		// TODO Auto-generated method stub
		int i = (int) (Math
				.ceil((double) this.coursetemplatedao.count(name) / 5));
		return i;
	}

	@Override
	public boolean CheckName(String name) {
		Set<CourseTemplate> ListCt = this.coursetemplatedao.getCourseTemplate();
		Iterator<CourseTemplate> iter = ListCt.iterator();
		while(iter.hasNext())
		{
			CourseTemplate ct = (CourseTemplate) iter.next();
			if(name.equals(ct.getName().toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}

}
