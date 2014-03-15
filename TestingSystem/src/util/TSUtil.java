package util;

import javax.servlet.http.HttpServletRequest;

public class TSUtil {
	/**
	 * Set default value of parameter if parameter is null
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String paramName, String defaultValue){
		String paramValue = request.getParameter(paramName);
		if(null == paramValue){
			paramValue = defaultValue;
		}
		return paramValue;
	}
	
	public static Object getAttribute(HttpServletRequest request, String attributeName, Object defaultValue){
		Object attributeValue = request.getAttribute(attributeName);
		if(null == attributeValue){
			attributeValue = defaultValue;
		}
		return attributeValue;
	}
}