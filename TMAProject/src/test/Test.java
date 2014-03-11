package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;
import model.Account;
import model.Material;
import model.Permission;
import model.Person;
import model.Role;
import model.Subject;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.HibernateUtil;

public class Test extends TestCase {
	private ClassPathXmlApplicationContext ctx;

	static {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"application-context.xml");
	}

	public void testCreate() {
		Session ss = HibernateUtil.getSession();
		ss.beginTransaction();

		Permission adminPermission1 = new Permission("loginPermission", true);
		ss.save(adminPermission1);
		Permission adminPermission2 = new Permission("editPermission", true);
		ss.save(adminPermission2);
		Permission adminPermission3 = new Permission("viewPermission", true);
		ss.save(adminPermission3);
		Permission adminPermission4 = new Permission("managePermission", true);
		ss.save(adminPermission4);

		Role adminRole = new Role("Admin");
		adminRole.getPermissionLists().add(adminPermission1);
		adminRole.getPermissionLists().add(adminPermission2);
		adminRole.getPermissionLists().add(adminPermission3);
		adminRole.getPermissionLists().add(adminPermission4);
		ss.save(adminRole);

		Permission traineePermission1 = new Permission("loginPermission", true);
		ss.save(traineePermission1);
		Permission traineePermission2 = new Permission("editPermission", true);
		ss.save(traineePermission2);
		Permission traineePermission3 = new Permission("viewPermission", false);
		ss.save(traineePermission3);
		Permission traineePermission4 = new Permission("managePermission",
				false);
		ss.save(traineePermission4);

		Role traineeRole = new Role("Trainee");
		traineeRole.getPermissionLists().add(traineePermission1);
		traineeRole.getPermissionLists().add(traineePermission2);
		traineeRole.getPermissionLists().add(traineePermission3);
		traineeRole.getPermissionLists().add(traineePermission4);
		ss.save(traineeRole);

		Permission trainerPermission1 = new Permission("loginPermission", true);
		ss.save(trainerPermission1);
		Permission trainerPermission2 = new Permission("editPermission", true);
		ss.save(trainerPermission2);
		Permission trainerPermission3 = new Permission("viewPermission", false);
		ss.save(trainerPermission3);
		Permission trainerPermission4 = new Permission("managePermission",
				false);
		ss.save(trainerPermission4);

		Role trainerRole = new Role("Trainer");
		trainerRole.getPermissionLists().add(trainerPermission1);
		trainerRole.getPermissionLists().add(trainerPermission2);
		trainerRole.getPermissionLists().add(trainerPermission3);
		trainerRole.getPermissionLists().add(trainerPermission4);
		ss.save(trainerRole);

		Permission coordinatorPermission1 = new Permission("loginPermission",
				true);
		ss.save(coordinatorPermission1);
		Permission coordinatorPermission2 = new Permission("editPermission",
				true);
		ss.save(coordinatorPermission2);
		Permission coordinatorPermission3 = new Permission("viewPermission",
				false);
		ss.save(coordinatorPermission3);
		Permission coordinatorPermission4 = new Permission("managePermission",
				false);
		ss.save(coordinatorPermission4);

		Role coordinatorRole = new Role("Coordinator");
		coordinatorRole.getPermissionLists().add(coordinatorPermission1);
		coordinatorRole.getPermissionLists().add(coordinatorPermission2);
		coordinatorRole.getPermissionLists().add(coordinatorPermission3);
		coordinatorRole.getPermissionLists().add(coordinatorPermission4);
		ss.save(coordinatorRole);

		Person person1 = new Person("Trung Huy Thu", "0938641688",
				"trunghuythu@gmail.com");
		Person person2 = new Person("Voong Thu Nhan", "0909569156",
				"thanhnhankmm@gmail.com");
		Person person3 = new Person("Nguyen Duy Anh", "0935571991",
				"nda1291@yahoo.com");
		Person person4 = new Person("Nguyen Duc Dinh Nghia", "0905616016",
				"nddnghia@gmail.com");
		Person person5 = new Person("Tran Minh Quang", "0945148959",
				"tranminhquang2209@gmail.com");
		Person person6 = new Person("Lam Nhu Y", "01666142813",
				"lny102@gmail.com");
		Person person7 = new Person("Nguyen Duy Tuan Quang", "01265333445",
				"zusquang@gmail.com");
		Person person8 = new Person("Dang Quoc Dat", "905231689",
				"datdq295@gmail.com");
		Person person9 = new Person("Nguyen Thi Nhu Ngoc", "1224986341",
				"nhungoc.nguyen90@gmail.com");
		Person person10 = new Person("Hoang Xuan Nam", "0988357016",
				"eric_hoang@live.com");
		Person person11 = new Person("Truong Trong Nghia", "01267250431",
				"kendi382@gmail.com");
		Person person12 = new Person("Pham Thi My Trang", "01224951595",
				"mytrangptit@gmail.com");
		Person person13 = new Person("Nguyen Tan Loc", "0937388111",
				"tanloc55555@gmail.com");
		Person person14 = new Person("Nguyen Thanh Phuoc", "0969320426",
				"phuocnguyen8@vanlanguni.vn");
		Person person15 = new Person("Chau Kinh To", "0982788582",
				"ckto@tma.com.vn");

		Account account1 = new Account("admin", "admin");
		Account account2 = new Account("huythuk18", "123");
		Account account3 = new Account("nhank18", "123");
		Account account4 = new Account("duyanhk18", "123");
		Account account5 = new Account("dinhnghia18", "123");
		Account account6 = new Account("minhquang18", "123");
		Account account7 = new Account("nhuy18", "123");
		Account account8 = new Account("tuanquang18", "123");
		Account account9 = new Account("quocdat18", "123");
		Account account10 = new Account("xuannam18", "123");
		Account account11 = new Account("trongnghia18", "123");
		Account account12 = new Account("nhungoc18", "123");
		Account account13 = new Account("mytrang18", "123");
		Account account14 = new Account("tanloc18", "123");
		Account account15 = new Account("thanhphuoc18", "123");

		account1.setRole(adminRole);
		account1.setPerson(person1);
		ss.save(account1);

		account2.setRole(trainerRole);
		account2.setPerson(person2);
		ss.save(account2);

		account3.setRole(coordinatorRole);
		account3.setPerson(person3);
		ss.save(account3);

		account4.setRole(traineeRole);
		account4.setPerson(person4);
		ss.save(account4);

		account5.setRole(traineeRole);
		account5.setPerson(person5);
		ss.save(account5);

		account6.setRole(traineeRole);
		account6.setPerson(person6);
		ss.save(account6);

		account7.setRole(traineeRole);
		account7.setPerson(person7);
		ss.save(account7);

		account8.setRole(traineeRole);
		account8.setPerson(person8);
		ss.save(account8);

		account9.setRole(traineeRole);
		account9.setPerson(person9);
		ss.save(account9);

		account10.setRole(traineeRole);
		account10.setPerson(person10);
		ss.save(account10);

		account11.setRole(traineeRole);
		account11.setPerson(person11);
		ss.save(account11);

		account12.setRole(traineeRole);
		account12.setPerson(person12);
		ss.save(account12);

		account13.setRole(traineeRole);
		account13.setPerson(person13);
		ss.save(account13);

		account14.setRole(traineeRole);
		account14.setPerson(person14);
		ss.save(account14);

		account15.setRole(traineeRole);
		account15.setPerson(person15);
		ss.save(account15);

		person1.setAccount(account1);
		ss.save(person1);

		person2.setAccount(account2);
		ss.save(person2);

		person3.setAccount(account3);
		ss.save(person3);

		person4.setAccount(account4);
		ss.save(person4);

		person5.setAccount(account5);
		ss.save(person5);

		person6.setAccount(account6);
		ss.save(person6);

		person7.setAccount(account7);
		ss.save(person7);

		person8.setAccount(account8);
		ss.save(person8);

		person9.setAccount(account9);
		ss.save(person9);

		person10.setAccount(account10);
		ss.save(person10);

		person11.setAccount(account11);
		ss.save(person11);

		person12.setAccount(account12);
		ss.save(person12);

		person13.setAccount(account13);
		ss.save(person13);

		person14.setAccount(account14);
		ss.save(person14);

		person15.setAccount(account15);
		ss.save(person15);

		Subject subject1 = new Subject("Hibernate",
				"What is hibernate? How to use? Advantages and drawbacks?");

		Subject subject2 = new Subject("Spring",
				"What is Spring? How to use? Advantages and drawbacks?");

		Subject subject3 = new Subject("Operating System", "What is OS?");

		Subject subject4 = new Subject("Principles of Programming Language",
				"What is PPL?");

		Subject subject5 = new Subject("Artificial Intelligence", "What is AI?");

		Subject subject6 = new Subject("Computer Network", "What is CN?");

		Material material1 = new Material("slides OS");

		Material material2 = new Material("book PPL");

		Material material3 = new Material("certified paper");

		Material material4 = new Material("grade I paper");

		Material material5 = new Material("Spring pdf book");

		Material material6 = new Material("hibernate example book");

		Material material7 = new Material("advanced java schdule");

		Material material8 = new Material("updated code");

		material1.setSubject(subject1);
		material2.setSubject(subject1);
		material3.setSubject(subject2);
		material4.setSubject(subject3);
		material5.setSubject(subject4);
		material6.setSubject(subject5);
		material7.setSubject(subject6);
		material8.setSubject(subject6);

		List<Material> hibernateMaterials = new ArrayList<Material>();
		List<Material> springMaterials = new ArrayList<Material>();
		List<Material> OSMaterials = new ArrayList<Material>();
		List<Material> PPLMaterials = new ArrayList<Material>();
		List<Material> AIMaterials = new ArrayList<Material>();
		List<Material> CNMaterials = new ArrayList<Material>();

		hibernateMaterials.add(material1);
		hibernateMaterials.add(material2);

		springMaterials.add(material3);

		OSMaterials.add(material4);

		PPLMaterials.add(material5);

		AIMaterials.add(material6);

		CNMaterials.add(material7);
		CNMaterials.add(material8);

		subject1.setMaterials(hibernateMaterials);
		subject2.setMaterials(springMaterials);
		subject3.setMaterials(OSMaterials);
		subject4.setMaterials(PPLMaterials);
		subject5.setMaterials(AIMaterials);
		subject6.setMaterials(CNMaterials);

		subject1.setPerson(person1);
		subject2.setPerson(person1);
		subject3.setPerson(person2);
		subject4.setPerson(person3);
		subject5.setPerson(person4);
		subject6.setPerson(person4);

		ss.save(material1);
		ss.save(material2);
		ss.save(material3);
		ss.save(material4);
		ss.save(material5);
		ss.save(material6);
		ss.save(material7);
		ss.save(material8);

		ss.save(subject1);
		ss.save(subject2);
		ss.save(subject3);
		ss.save(subject4);
		ss.save(subject5);
		ss.save(subject6);

		ss.getTransaction().commit();
	}

}
