package dao;

import java.util.Calendar;
import java.util.List;

import model.Material;
import model.Person;
import model.Subject;

public interface MaterialDAO extends Dao<Material, Integer> {
	public String MATERIALID = "materialId";
	public String TITLE = "materialTitle";
	public String SIZE	= "materialSize";
	public String URL = "materialUrl";
	public String DATE_MODIFICATION = "dateModification";
	public String SUBJECT = "subject";
	public String TYPE = "materialType";
	public String ABSOLUTEPATH = "materialAbsolutePath";

	public Material getMaterialById(Integer materialId);

	public List<Material> listAllMaterial();
	public Material getMaterialByTitle(String materialTitle);
	public List<Material> getMaterialByUrl(String url);
	public List<Material> getMaterialByDateModification(String dateModification);
	public List<Material> getMaterialBySubjectId(Integer subjectId);
	public List<Material> getMaterialByPerson(Person person);

	public boolean addMaterial(Material material);
	
	public boolean deleteMaterialById(Integer materialId);
	public void deleteAllMaterial();
	public void deleteAllMaterialOfSpecifiedSubject(Integer subjectId);

	public boolean modifyMaterialById(Material material, Integer newID);
	public boolean modifyMaterialByTitle(Material material, String newTitle);
	public boolean modifyMaterialByUrl(Material material, String newUrl);
	public boolean modifyMaterialByDateModification(Material material, String newDateModification);
	public boolean modifyMaterialByType(Material material, String materialType);
	public boolean modifyMaterialBySubject(Material material, Subject newSubject);

	

}
