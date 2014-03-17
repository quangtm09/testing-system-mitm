package com.bosch.boschadministration.util;

import com.liferay.util.portlet.PortletProps;

/**
 * The Class BAPropsValues.
 */
public final class BAPropsValues {

    /** PORTAL_IMPL. */
    public static final String PORTAL_IMPL = PortletProps.get("portal-impl");

    /** F_PORTAL_IMPL. */
    public static final String F_PORTAL_IMPL = PortletProps.get("f-portal-impl");

    /** RESTRICTED_EXTENSION EXTENSION. */
    public static final String RESTRICTED_EXTENSION = PortletProps.get("restricted-extension");

    /** TOMCAT_LOG_ROOT. */
    public static final String TOMCAT_LOG_ROOT = PortletProps.get("tomcat.log.root");

    /** BA_RESOURCE. */
    public static final String BA_RESOURCE = PortletProps.get("ba-resource");

    /** IMPORTANT_CACHE_NAME. */
    public static final String[] IMPORTANT_CACHE_NAME = PortletProps.getArray("important-cache-name");

    /** IMPORTANT_CACHE_KEY */
    public static final String[] IMPORTANT_CACHE_KEY = PortletProps.getArray("important-cache-key");

    /**
     * Utility classes should not have a public or default constructor.
     */
    private BAPropsValues() {
    }

}
