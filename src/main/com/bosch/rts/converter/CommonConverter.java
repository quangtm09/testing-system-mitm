/**
 * 
 */
package com.bosch.rts.converter;

import java.util.Collection;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.bosch.rts.utilities.RTSConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonConverter.
 *
 * @author bit71hc
 */
public class CommonConverter implements Converter {
	
	/**
	 * Instantiates a new common converter.
	 */
	public CommonConverter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext context, UIComponent uiComponent,
			String strValue) {
		Collection<Object> objs = (Collection<Object>) uiComponent
				.getAttributes().get(RTSConstants.SUGGESTION_VALUES);
		if (objs == null) {
			List<UIComponent> childUIs = uiComponent.getChildren();
			for (UIComponent uiCom : childUIs) {
				if (uiCom instanceof UISelectItems) {
					UISelectItems selectItems = (UISelectItems) uiCom;
					Collection<SelectItem> selectItemList = (Collection<SelectItem>) selectItems
							.getValue();
					for (SelectItem obj : selectItemList) {
						if (obj.getValue().toString()
								.equalsIgnoreCase(strValue)) {
							return obj.getValue();
						}
					}
					break;
				}
			}
			return null;
		}
		for (Object obj : objs) {
			if (obj.toString().equalsIgnoreCase(strValue)) {
				return obj;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent uiComponent,
			Object objValue) {
		return objValue == null ? RTSConstants.BLANK : objValue.toString();
	}
}
