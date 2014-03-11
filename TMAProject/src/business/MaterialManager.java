package business;

import java.util.List;

import model.Material;
import model.Subject;

public interface MaterialManager {
	public Material getMaterialById(Integer materialId);

	public List<Material> listAllMaterial();
	public Material getMaterialByTitle(String title);
	public List<Material> getMaterialByUrl(String url);
	public List<Material> getMaterialByDateModification(String dateModification);
	public List<Material> getMaterialBySubjectId(Integer subjectId);

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
