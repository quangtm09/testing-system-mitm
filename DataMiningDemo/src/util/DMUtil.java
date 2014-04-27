package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DMUtil {
	/**
	 *
	 * Set default value of attribute if the attribute is null
	 *
	 * @param request
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */
	public static Object getAttribute(final HttpServletRequest request, final String attributeName, final Object defaultValue){
		Object attributeValue = request.getAttribute(attributeName);
		if(null == attributeValue){
			attributeValue = defaultValue;
		}
		return attributeValue;
	}

	/**
	 * Set default value of parameter if parameter is null
	 *
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static String getParameter(final HttpServletRequest request, final String paramName, final String defaultValue){
		String paramValue = request.getParameter(paramName);
		if(null == paramValue){
			paramValue = defaultValue;
		}
		return paramValue;
	}

	public static String getUsernameByCookie(final HttpServletRequest request){
		String username = null;
		final Cookie[] cookies = request.getCookies();

		if(null != cookies){
			for(final Cookie cookie: cookies){
				if(cookie.getName().equals("userId")){
					username = cookie.getName();
				}
			}
		}
		return username;
	}

	public static String getUsernameByHttpSession(final HttpSession session){
		return (String) session.getAttribute("userId");
	}

	/**
	 * Check if user is logged in or not
	 *
	 * @param session
	 * @return
	 */
	public static boolean isUserLoggedIn(final HttpSession session) {
		boolean isUserLoggedIn = false;

		if(session.getAttribute("userId") != null){
			isUserLoggedIn = true;
		}

		return isUserLoggedIn;

	}
}
