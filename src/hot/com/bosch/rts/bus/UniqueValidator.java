package com.bosch.rts.bus;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 * The Class UniqueValidator.
 */
@Name("UniqueValidator")
public class UniqueValidator implements Validator<RTSUnique>,
		PropertyConstraint {
	// Entity for which validation is to be fired
	/** The target entity. */
	private String targetEntity;

	// Field for which validation is to be fired.
	/** The field. */
	private String field;

	/** The component home. */
	private String componentHome;
	
	/** The id field name. */
	private String idFieldName;
	
	/** The is running. */
	private boolean isRunning = false;

	/* (non-Javadoc)
	 * @see org.hibernate.validator.PropertyConstraint#apply(org.hibernate.mapping.Property)
	 */
	public void apply(Property arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Validate.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @throws ValidatorException the validator exception
	 */
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#isValid(java.lang.Object)
	 */
	public boolean isValid(Object value) {
		if (isRunning) {
			return true;
		}
		EntityManager entityManager = (EntityManager) Component
				.getInstance("entityManager");
		EntityHome<?> entityHome = (EntityHome<?>) Component
				.getInstance(componentHome);
		String sql = "select t from " + targetEntity + " t ";
		if (value instanceof String) {
			sql += " where lower( t." + field + " )" + " = lower(:value)";
		} else {
			sql += "where t." + field + " = :value";
		}
		if (entityHome.getId() != null) {
			sql += " and t." + idFieldName + " <> :id";
		}
		Query query = entityManager.createQuery(sql);
		query.setParameter("value", value);
		if (entityHome.isIdDefined()) {
			query.setParameter("id", entityHome.getId());
		}
		isRunning = true;
		List<?> temp = query.getResultList();
		isRunning = false;
		if (temp.size() > 0) {
			return false;
		}
		return true;

	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#initialize(java.lang.annotation.Annotation)
	 */
	public void initialize(RTSUnique parameters) {
		// TODO Auto-generated method stub
		targetEntity = ((RTSUnique) parameters).entityName();
		field = ((RTSUnique) parameters).fieldName();
		componentHome = ((RTSUnique) parameters).entityHomeComponent();
		idFieldName = ((RTSUnique) parameters).idFieldName();
	}

}
