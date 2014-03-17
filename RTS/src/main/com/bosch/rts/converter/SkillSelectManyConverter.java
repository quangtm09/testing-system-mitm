package com.bosch.rts.converter;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.bosch.rts.entity.Skill;

/**
 * The Class SkillSelectManyConverter.
 */
public class SkillSelectManyConverter implements Converter, Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1844164313131747604L;

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent uiComponent,
			String value) {
		List<UIComponent> childUIs = uiComponent.getChildren();
		for (UIComponent uiCom : childUIs) {
			if (uiCom instanceof UISelectItems) {
				UISelectItems selectItems = (UISelectItems) uiCom;
				List<?> skills = (List<?>) selectItems.getValue();
				for (Object obj : skills) {
					Skill skill = (Skill) ((SelectItem) obj).getValue();
					if (skill.getSklName().equalsIgnoreCase(value)) {
						return skill;
					}
				}
				break;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Skill) arg2).getSklName();
	}
}
