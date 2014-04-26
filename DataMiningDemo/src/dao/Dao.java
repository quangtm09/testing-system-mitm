package dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<C, ID extends Serializable> {
	List<C> findAll();

	C findById(ID id);

	boolean save(C entity);

	boolean update(C entity);

	boolean delete(C entity);

	C merge(C detachedInstance);

	boolean flush();

	boolean close();
}
