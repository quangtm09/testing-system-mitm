package com.bosch.boschadministration.util;

import com.bosch.boschadministration.model.BAItemModel;
import com.bosch.boschadministration.model.impl.BAItemModelImpl;
import com.bosch.boschadministration.util.comparator.BAItemModelItemDateComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemIdComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemNameComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemSecondValueComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemTypeComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemUrlComparator;
import com.bosch.boschadministration.util.comparator.BAItemModelItemValueComparator;
import com.bosch.layoutimporter.servicebuilder.NoSuchLayoutImportException;
import com.bosch.layoutimporter.servicebuilder.model.LayoutImport;
import com.bosch.layoutimporter.servicebuilder.service.LayoutImportLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.ServiceComponent;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerPath;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceComponentLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserTrackerLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.SystemProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * The Class BAActionUtil.
 * 
 * @author eks8fe
 */

public class BAActionUtil {

    /** The Constant LOG. */
    public static final Log LOG = LogFactoryUtil.getLog(BAActionUtil.class);

    /**
     * Field fileMap.
     */
    public static Map<String, String> fileMap = new HashMap<String, String>();

    /**
     * Method getInstalledPatches.
     * 
     * @return List<FileItem>
     */
    public static List<BAItemModel> getInstalledPatches() {
    	final List<BAItemModel> installedPatches = new ArrayList<BAItemModel>();

        // get portal lib path
        final String portalLibPath = PortalUtil.getPortalLibDir();

        String jarPath = portalLibPath + BAPropsValues.F_PORTAL_IMPL + StringPool.PERIOD + BAConstants.JAR;

        final File file = new File(jarPath);

        // check exist of f-portal-impl.jar
        if (!file.exists()) {
            jarPath = PortalUtil.getPortalLibDir() + BAPropsValues.PORTAL_IMPL + StringPool.PERIOD + BAConstants.JAR;
        }

        JarFile jarfile = null;
        Manifest manifestInstalPatch = null;
        try {
            // Open the JAR file
            jarfile = new JarFile(jarPath);
            // Get the manifest
            manifestInstalPatch = jarfile.getManifest();

        } catch (final IOException e) {
            LOG.fatal("Cannot get manifest from jar file", e);
        }

        // Get the manifest entries
        if (Validator.isNotNull(manifestInstalPatch)) {
            /** Get All main Attributes */
            final Attributes attrs = manifestInstalPatch.getMainAttributes();
            // Enumerate each attribute
            for (final Object elementAttMa : attrs.keySet()) {
                // Get attribute name
                final Attributes.Name attrName = (Attributes.Name) elementAttMa;
                // Get attribute value
                final String attrValue = attrs.getValue(attrName);

                installedPatches.add(new BAItemModelImpl(attrName.toString(), attrValue));
            }
        }

        return installedPatches;
    }

    /**
     * Gets the files from directory.
     * 
     * @param dir
     *            the dir
     * @param includes
     *            the includes
     * @param excludes
     *            the excludes
     * @param fileNameCriteria
     *            the file name criteria
     * @return the files from directory
     */
    public static List<BAItemModel> getFilesFromDirectory(final String dir, final String includes,
            final String excludes, final String fileNameCriteria) {
        final List<BAItemModel> fileItems = new ArrayList<BAItemModel>();

        // 1. check directory
        if (Validator.isNotNull(dir) && new File(dir).exists()) {
            // 2. file search
            final String[] fileArray = FileUtil.find(dir, includes, excludes);

            // add File Item to List
            if (Validator.isNotNull(fileArray)) {
                fileMap.clear();
                BAActionUtil.addFileToList(fileArray, fileItems, fileNameCriteria);
            }
        } else {
            LOG.error("The directory is not specified");
        }

        return fileItems;
    }

    /**
     * Adds the file to list.
     * 
     * @param fileArray
     *            the file array
     * @param fileItems
     *            the file items
     * @param fileNameCriteria
     *            the file name criteria
     */
    private static void addFileToList(final String[] fileArray, final List<BAItemModel> fileItems,
            final String fileNameCriteria) {
        File file = null;
        final String fileKey = StringPool.BLANK;

        if (Validator.isNull(fileNameCriteria)) {
            // add normal
            for (final String filePath : fileArray) {
                file = new File(filePath);
                BAActionUtil.addFiles(file, filePath, fileKey, fileItems);
            }
        } else {
            // check criteria
            // filter condition
            for (final String filePath : fileArray) {
                file = new File(filePath);
                if (BAActionUtil.isMatched(file.getName(), fileNameCriteria)) {
                    BAActionUtil.addFiles(file, filePath, fileKey, fileItems);
                }
            }
        }
    }

    /**
     * Adds the files.
     * 
     * @param file
     *            the file
     * @param filePath
     *            the file path
     * @param fileKey
     *            the file key
     * @param fileItems
     *            the file items
     */
    private static void addFiles(final File file, final String filePath, String fileKey,
            final List<BAItemModel> fileItems) {

        fileKey = UUID.randomUUID().toString();
        fileMap.put(fileKey, filePath);
        fileItems.add(new BAItemModelImpl(file.getName(), fileKey, new Date(file.lastModified())));

    }

    /**
     * Method is populating data from Group object.
     * 
     * @param companyId
     *            long
     * @return group list
     */
    public static List<Group> getMandatorList(final long companyId) {

        try {
            return LayoutImportLocalServiceUtil.getMandatorList(companyId);
        } catch (final Exception e) {
            LOG.error(e);
        }

        return null;
    }

    /**
     * Method isLayoutImporterDeployed.
     * 
     * @return boolean
     */
    public static boolean isLayoutImporterDeployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.LAYOUT_IMPORTER_PORTLETID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method isDocumentImporterDeployed.
     * 
     * @return boolean
     */
    public static boolean isDocumentImporterDeployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.DOCUMENT_IMPORTER_PORTLETID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method is checking whether _document-importer-1 Portlet is deployed and active.
     * 
     * @return true if portlet is deployt and active
     */
    public static boolean isDI1Deployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.DOCUMENT_IMPORTER_PORTLETID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method is checking whether _document-importer-2 Portlet is deployed and active.
     * 
     * @return true if portlet is deployt and active
     */
    public static boolean isDI2Deployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.DOCUMENT_IMPORTER_2_PORTLET_ID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method is checking whether PortletBridge 1 Portlet is deployed and active.
     * 
     * @return true if portlet is deployt and active
     */
    public static boolean isPortletBridge1Deployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.PORTLETBRIDGE_PORTLETID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method is checking whether PortletBridge 2 Portlet is deployed and active.
     * 
     * @return true if portlet is deployt and active
     */
    public static boolean isPortletBridge2Deployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.PORTLET_BRIDGE_2_PORTLET_ID);
        if (portlet != null) {
            return true;
        }

        return false;
    }

    /**
     * Method is checking for getMandatorList method.
     * 
     * @return Boolean
     */
    public static boolean isLayoutImporterVersionSupported() {

        boolean isLayoutImporterVersionSupported = false;
        ClassLoader portletClassLoader = null;

        try {

            // 1. check whether getMandatorList method is presented in the service jar file in
            // _bosch-administration-portlet
            // final Method method = MethodCache.get(BAConstants.LAYOUT_LOCAL_SERVICE_CLASS,
            // BAConstants.GET_MANDATOR_LIST_METHOD, new Class[] { long.class });

            // if (null != method) {
            isLayoutImporterVersionSupported = true;
            // }

            if (isLayoutImporterVersionSupported) {

                // 2. check whether getMandatorList method is presented in the implementation class in deployed
                // _bosch-layout-importer-portlet

                portletClassLoader = PortletClassLoaderUtil.getClassLoader(BAConstants.LAYOUT_IMPORTER_PORTLETID);

                if (null == portletClassLoader) {
                    LOG.error("The " + BAConstants.LAYOUT_IMPORTER_PORTLETID + " ClassLoader is null");
                    isLayoutImporterVersionSupported = false;
                } else {
                    isLayoutImporterVersionSupported = BAActionUtil.checkGetMandtorListMethod(portletClassLoader
                            .loadClass(BAConstants.LAYOUT_LOCAL_SERVICE_IMPL_CLASS));
                }

            }
        } catch (final Exception e) {
            isLayoutImporterVersionSupported = false;
        }

        return isLayoutImporterVersionSupported;
    }

    /**
     * Check get mandtor list method.
     * 
     * @param lIClass
     *            the l i class
     * @return true, if successful
     */
    private static boolean checkGetMandtorListMethod(final Class<?> lIClass) {
        boolean isValid = false;
        // check whether class is available
        if (Validator.isNotNull(lIClass)) {
            try {
                // check whether method is available
                if (null == lIClass.getMethod(BAConstants.GET_MANDATOR_LIST_METHOD, long.class)) {
                    isValid = false;
                } else {
                    isValid = true;
                }
            } catch (final NoSuchMethodException e) {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        return isValid;
    }

    /**
     * Method getInstalledPlugins. used to get all installed plugin
     * 
     * @return List<FileItem>
     */
    public static List<BAItemModel> getInstalledPlugins(final boolean dom4jChecking) {

        final List<BAItemModel> installedPlugins = new ArrayList<BAItemModel>();

        try {

            final Set<String> servletSet = ServletContextPool.keySet();
            for (final String servletName : servletSet) {
                if (dom4jChecking) {
                    if (BAActionUtil.isValidPlugin(servletName)) {
                        installedPlugins.add(new BAItemModelImpl(servletName));
                    }
                } else {
                    if (Validator.isNotNull(servletName)) {
                        installedPlugins.add(new BAItemModelImpl(servletName));
                    }
                }

            }

        } catch (final Exception e) {
            LOG.error(e);
        }
        return installedPlugins;
    }

    /**
     * Checks if is valid portlet.
     * 
     * @param portletId
     *            the portlet id
     * @return true, if is valid portlet
     */
    public static boolean isValidPlugin(final String servletName) {

        if (Validator.isNull(servletName)
                || null == BAActionUtil.loadClassFromServletContextname(servletName, BAConstants.DOM4J_ELEMENT_CLASS)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method isMatched used to filter the files.
     * 
     * @param fileName
     *            the file name
     * @param keywords
     *            the keywords
     * @return true, if is matched
     */
    private static boolean isMatched(final String fileName, final String keywords) {

        String criteriaString = keywords.toLowerCase();
        // allow to search with ? and * option

        if (!criteriaString.startsWith(StringPool.STAR)) {
            criteriaString = StringPool.STAR + criteriaString;
        }

        if (!criteriaString.endsWith(StringPool.STAR)) {
            criteriaString = criteriaString + StringPool.STAR;
        }

        if (criteriaString.contains(StringPool.STAR)) {
            criteriaString = criteriaString.replace(StringPool.STAR, StringPool.PERIOD + StringPool.STAR);
        }
        if (criteriaString.contains(StringPool.QUESTION)) {
            criteriaString = criteriaString.replace(StringPool.QUESTION, StringPool.PERIOD + StringPool.QUESTION);
        }

        return fileName.toLowerCase().matches(criteriaString);
    }

    /**
     * Gets the auto deploy dest dir.
     * 
     * @return the auto deploy dest dir
     */
    public static String getAutoDeployDestDir() {

        String destDir = StringPool.BLANK;
        try {

            destDir = PrefsPropsUtil.getString(PropsKeys.AUTO_DEPLOY_DEST_DIR);

            if (Validator.isNull(destDir)) {
                destDir = BAActionUtil.getAutoDeployServerDestDir();
            }

        } catch (final Exception e) {
            LOG.error(e);
        }

        return destDir;
    }

    /**
     * Gets the auto deploy server dest dir.
     * 
     * @return the auto deploy server dest dir *
     */
    private static String getAutoDeployServerDestDir() {
        String destDir = null;

        final String serverId = GetterUtil.getString(ServerDetector.getServerId());

        try {

            if (serverId.equals(ServerDetector.TOMCAT_ID)) {
                destDir = PrefsPropsUtil.getString(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR);
            } else {
                destDir = PrefsPropsUtil.getString("auto.deploy." + serverId + ".dest.dir");
            }

            if (Validator.isNull(destDir)) {
                destDir = PrefsPropsUtil.getString(PropsKeys.AUTO_DEPLOY_DEFAULT_DEST_DIR);
            }

        } catch (final Exception e) {
            LOG.error(e);
        }

        destDir = StringUtil.replace(destDir, CharPool.BACK_SLASH, CharPool.SLASH);

        return destDir;
    }

    /**
     * Gets the item name date order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item name date order by comparator
     */
    public static OrderByComparator getItemNameDateOrderByComparator(final String orderByCol, final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemDate")) {
            orderByComparator = new BAItemModelItemDateComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemNameComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Gets the item name value order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item name value order by comparator
     */
    public static OrderByComparator getItemNameValueOrderByComparator(final String orderByCol, final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemNameComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemValue")) {
            orderByComparator = new BAItemModelItemValueComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Gets the item name value id order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item name value id order by comparator
     */
    public static OrderByComparator getItemNameValueIdOrderByComparator(final String orderByCol,
            final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemNameComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemValue")) {
            orderByComparator = new BAItemModelItemValueComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemId")) {
            orderByComparator = new BAItemModelItemIdComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Gets the item name type url value order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item name type url value order by comparator
     */
    public static OrderByComparator getItemNameTypeUrlValueOrderByComparator(final String orderByCol,
            final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemNameComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemType")) {
            orderByComparator = new BAItemModelItemTypeComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemUrl")) {
            orderByComparator = new BAItemModelItemUrlComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemValue")) {
            orderByComparator = new BAItemModelItemValueComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Gets the item name type url value order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item name type url value order by comparator
     */
    public static OrderByComparator getItemIdNameTypeUrlValueOrderByComparator(final String orderByCol,
            final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemId")) {
            orderByComparator = new BAItemModelItemIdComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemTypeComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemType")) {
            orderByComparator = new BAItemModelItemTypeComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemUrl")) {
            orderByComparator = new BAItemModelItemUrlComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemValue")) {
            orderByComparator = new BAItemModelItemValueComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Gets the item second value name type url value order by comparator.
     * 
     * @param orderByCol
     *            the order by col
     * @param orderByType
     *            the order by type
     * @return the item second value name type url value order by comparator
     */
    public static OrderByComparator getItemSecondValueNameTypeUrlValueOrderByComparator(final String orderByCol,
            final String orderByType) {

        boolean orderByAsc = false;

        if (orderByType.equals("asc")) {
            orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equalsIgnoreCase("itemSecondValue")) {
            orderByComparator = new BAItemModelItemSecondValueComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemName")) {
            orderByComparator = new BAItemModelItemNameComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemType")) {
            orderByComparator = new BAItemModelItemTypeComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemUrl")) {
            orderByComparator = new BAItemModelItemUrlComparator(orderByAsc);
        } else if (orderByCol.equalsIgnoreCase("itemValue")) {
            orderByComparator = new BAItemModelItemValueComparator(orderByAsc);
        }

        return orderByComparator;
    }

    /**
     * Copied from {@link com.bosch.documentimporter.util.FileReadUtil#downloadToArchive(final String srcFile, final
     * String destFile)}
     * 
     * @param srcFile
     *            the src file
     * @param proxy
     *            the proxy
     * @return the file
     * @throws Exception
     *             the exception
     */
    public static File downloadToArchive(final String srcFile, final HttpHost proxy) throws Exception {
        final HttpClient client = new DefaultHttpClient();
        FileOutputStream out = null;

        try {
            // prepare a request object
            final HttpGet get = new HttpGet(srcFile);
            get.setHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.TEXT_XML_UTF8);
            get.setHeader(HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);
            get.setHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);

            // get the response
            HttpResponse response = null;
            try {
                // execute the request WITHOUT proxy
                response = client.execute(get);
            } catch (final Exception e) {

                // execute the request WITH proxy
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
                response = client.execute(get);
            }

            // check if response is OK
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                throw new Exception("HttpClient failed to connect to URL " + get.getURI() + " with status code "
                        + response.getStatusLine());
            }

            // create a temp file
            final StringBundler sb = new StringBundler();
            sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
            sb.append(StringPool.SLASH);
            sb.append(srcFile.substring(srcFile.lastIndexOf(StringPool.SLASH) + 1, srcFile.length()));

            final File tempFile = new File(sb.toString());

            // transfer URL stream to file
            out = new FileOutputStream(tempFile);
            response.getEntity().writeTo(out);

            return tempFile;

        } finally {
            // close connection and file
            client.getConnectionManager().shutdown();
            if (Validator.isNotNull(out)) {
                out.close();
            }
        }
    }

    /**
     * Gets the session users.
     * 
     * @param companyId
     *            the company id
     * @return the session users
     */
    public static List<UserTracker> getSessionUsers(final long companyId) {

        try {
            @SuppressWarnings("unchecked")
            final Map<String, UserTracker> sessionUsers = (Map<String, UserTracker>) PortalClassInvoker.invoke(false,
                    new MethodKey(BAConstants.LIVE_USERS_CLASS, BAConstants.GET_SESSION_USERS_METHOD,
                            new Class[] { long.class }), companyId);

            final List<UserTracker> userTrackers = new ArrayList<UserTracker>(sessionUsers.values());

            return userTrackers;
        } catch (final Exception e) {
            LOG.error(e);
            return new ArrayList<UserTracker>();
        }

    }

    /**
     * Gets the user tracker.
     * 
     * @param companyId
     *            the company id
     * @param httpSession
     *            .getId() the session id
     * @return the user tracker
     */
    public static UserTracker getUserTracker(final long companyId, final HttpSession httpSession) {

        UserTracker userTracker = null;

        try {
            final Object objUserId = httpSession.getAttribute(WebKeys.USER_ID);
            if (null != objUserId) {
                if (GetterUtil.getBoolean(PropsUtil.get(PropsKeys.LIVE_USERS_ENABLED))
                        && GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_TRACKER_MEMORY_ENABLED))) {
                    // get UserTracker from memory (user LiveUser)
                    userTracker = (UserTracker) PortalClassInvoker.invoke(false, new MethodKey(
                            BAConstants.LIVE_USERS_CLASS, BAConstants.GET_USER_TRACKER_METHOD, new Class[] {
                                    long.class, String.class }), companyId, httpSession.getId());
                } else if (GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_TRACKER_PERSISTENCE_ENABLED))
                        && GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_TRACKER_FRIENDLY_PATHS_ENABLED))) {
                    // get UserTracker from database
                    userTracker = UserTrackerLocalServiceUtil.getUserTracker(Long.parseLong(String.valueOf(objUserId)));
                }
            }

        } catch (final Exception e) {
            LOG.error(e);
        }

        return userTracker;
    }

    /**
     * Load class from portlet class loader.
     * 
     * @param portletId
     *            the portlet id
     * @param className
     *            the class name
     * @return the class
     */
    public static Class<?> loadClassFromPortletId(final String portletId, final String className) {

        // get ClassLoader from other portlet
        final ClassLoader portletClassLoader = PortletClassLoaderUtil.getClassLoader(portletId);

        if (null == portletClassLoader) {
            LOG.error("The " + portletId + " ClassLoader is null");
            return null;
        }

        // load class name
        try {
            return portletClassLoader.loadClass(className);
        } catch (final ClassNotFoundException e) {

            return null;
        }
    }

    /**
     * Load class from portlet class loader.
     * 
     * @param servletContextname
     *            the portlet id
     * @param className
     *            the class name
     * @return the class
     */
    public static Class<?> loadClassFromServletContextname(final String servletContextname, final String className) {

        // get ClassLoader from other portlet
        Object classLoaderObj = ServletContextPool.get(servletContextname).getAttribute(
                BAConstants.PORTLET_CLASS_LOADER);

        // LRD-1379
        if (Validator.isNull(classLoaderObj)) {
            classLoaderObj = ServletContextPool.get(servletContextname).getAttribute(BAConstants.PLUGIN_CLASS_LOADER);
        }

        final ClassLoader portletClassLoader = (ClassLoader) classLoaderObj;

        if (null == portletClassLoader) {
            LOG.error("The " + servletContextname + " ClassLoader is null");
            return null;
        }

        // load class name
        try {
            return portletClassLoader.loadClass(className);
        } catch (final ClassNotFoundException e) {

            return null;
        }
    }

    /**
     * Gets the mandator link from li portlet.
     * 
     * @param mandatorName
     *            the mandator name
     * @return the mandator link from li portlet
     */
    public static String getMandatorLinkFromLIPortlet(final String mandatorName) {

        String mandatorLink = StringPool.BLANK;

        try {

            // load LIPropsValues class
            final Class<?> lIClass = BAActionUtil.loadClassFromPortletId(BAConstants.LAYOUT_IMPORTER_PORTLETID,
                    BAConstants.LI_PROPS_VALUES_CLASS);

            if (null == lIClass) {
                LOG.error("fail to load " + BAConstants.LI_PROPS_VALUES_CLASS);
                return mandatorLink;
            }

            // invoke getNavixmlFilepath method
            final Method getNavixmlFilepathMethod = lIClass.getMethod(BAConstants.GET_NAVI_XML_FILE_PATH_METHOD,
                    String.class);

            if (null == getNavixmlFilepathMethod) {
                LOG.error("fail to get method " + BAConstants.GET_NAVI_XML_FILE_PATH_METHOD);
                return mandatorLink;
            }

            mandatorLink = (String) getNavixmlFilepathMethod.invoke(null, mandatorName);

        } catch (final Exception e) {
            LOG.error(e);
            return mandatorLink;
        }
        return mandatorLink;
    }

    /**
     * Gets the lI mandators.
     * 
     * @param mandators
     *            the mandators
     * @return the lI mandators
     */
	public static List<BAItemModel> getLIMandators(final List<Group> mandators) {
		final List<BAItemModel> fileItems = new ArrayList<BAItemModel>();

		if (Validator.isNull(mandators)) {
			return fileItems;
		}

		for (final Group group : mandators) {
			// LRD-1380
			final String mandatorName = group.getName();
			final String mandatorLink = BAActionUtil
					.getMandatorLinkFromLIPortlet(mandatorName);
			long mandatorDate = 0L;
			long groupId = group.getGroupId();
			
			// Last modified date string value
			String lastModCacheVal = null;
			
	        javax.portlet.PortletPreferences prefs = null;
	        long controlPanelPrivatePlid = -1;
	        // Create the key to get preferences
	        final String key = groupId + BAConstants.STRING_SEPARATOR + mandatorLink;
	        try {
	            // Get the default control panel plid
	            controlPanelPrivatePlid = GroupLocalServiceUtil
	                    .getGroup(group.getCompanyId(), GroupConstants.CONTROL_PANEL).getDefaultPrivatePlid();
	            // Get the javax.portlet preferences object
	            prefs = PortletPreferencesLocalServiceUtil.getPreferences(group.getCompanyId(),
	                    PortletKeys.PREFS_OWNER_ID_DEFAULT, PortletKeys.PREFS_OWNER_TYPE_LAYOUT, controlPanelPrivatePlid,
	                    BAConstants.LAYOUT_IMPORTER_PORTLETID);
	            // Get the last modified date value from preferences
	            if (null != prefs) {
	                lastModCacheVal = prefs.getValue(key, null);
	            }
	            
	            if(Validator.isNotNull(lastModCacheVal)){
	            	mandatorDate = Long.valueOf(lastModCacheVal);
	            }
	            
	        } catch (final Exception e) {
				LOG.error("Errors while getting Mandator Last Date of Import", e);
			}
			
			fileItems.add(new BAItemModelImpl(mandatorName, mandatorLink,
					new Date(mandatorDate)));
		}
		return fileItems;
	}

    /**
     * Gets the log file level.
     * 
     * @param keywords
     *            the keywords
     * @return the log file level
     */
    public static List<BAItemModel> getLogFileLevel(final String keywords) {

        final List<BAItemModel> logLevels = new ArrayList<BAItemModel>();

        try {

            for (final BAItemModel baItemModel : BAActionUtil.getInstalledPlugins(true)) {
                final String servletContextName = baItemModel.getItemId();

                // load logManagerClass class
                final Class<?> logManagerClass = BAActionUtil.loadClassFromServletContextname(servletContextName,
                        BAConstants.LOG_MANAGER_CLASS);

                if (null == logManagerClass) {
                    continue;
                }

                // invoke getNavixmlFilepath method
                final Method getCurrentLoggersMethod = logManagerClass
                        .getMethod(BAConstants.GET_CURRENT_LOGGERS_METHOD);

                if (null == getCurrentLoggersMethod) {
                    continue;
                }

                final Enumeration<?> enu = (Enumeration<?>) getCurrentLoggersMethod.invoke(null, new Object[0]);

                while (enu.hasMoreElements()) {

                    final Object obj = enu.nextElement();
                    final Class<?> loggerClass = obj.getClass();

                    final String logName = (String) loggerClass.getMethod("getName", new Class<?>[0]).invoke(obj,
                            new Object[0]);

                    if (BAActionUtil.isMatched(logName, keywords)) {

                        Object levelObj = loggerClass.getMethod("getLevel", new Class<?>[0]).invoke(obj, new Object[0]);

                        if (null == levelObj) {

                            final Object loggerParent = loggerClass.getMethod("getParent", new Class<?>[0]).invoke(obj,
                                    new Object[0]);
                            final Object loggerParentLevelObj = loggerParent.getClass()
                                    .getMethod("getLevel", new Class<?>[0]).invoke(loggerParent, new Object[0]);

                            if (null != loggerParentLevelObj) {
                                levelObj = loggerParentLevelObj;
                            } else {
                                levelObj = Level.INFO.toString();
                            }

                        }

                        logLevels.add(new BAItemModelImpl(servletContextName, logName, levelObj.toString()));
                    }

                }
            }

        } catch (final Exception e) {
            LOG.error(e);
        }

        return logLevels;
    }

    /**
     * Gets the mandators from di portlet.
     * 
     * @return the mandators from di portlet
     */
    public static List<BAItemModel> getMandatorsFromDIPortlet() {
        final List<BAItemModel> docFiles = new ArrayList<BAItemModel>();

        try {

            // load DIPropsValues class
            final Class<?> dIClass = BAActionUtil.loadClassFromPortletId(BAConstants.DOCUMENT_IMPORTER_PORTLETID,
                    BAConstants.DI_PROPS_VALUES_CLASS);

            if (null == dIClass) {
                LOG.error("fail to load " + BAConstants.DI_PROPS_VALUES_CLASS);
                return docFiles;
            }

            // get DOC_XML_FILEPATH field

            @SuppressWarnings("unchecked")
            final Map<String, String> mapMandator = (Map<String, String>) dIClass.getField(
                    BAConstants.DOC_XML_FILEPATH_FIELD).get(dIClass);

            final Iterator<Entry<String, String>> itr = mapMandator.entrySet().iterator();

            while (itr.hasNext()) {
                final Entry<String, String> entry = itr.next();
                docFiles.add(new BAItemModelImpl(entry.getKey(), entry.getValue()));
            }

        } catch (final Exception e) {
            LOG.error(e);
        }

        return docFiles;
    }

    /**
     * Gets the session attributes.
     * 
     * @param userSession
     *            the user session
     * @return the session attributes
     */
    public static List<BAItemModel> getSessionAttributes(final HttpSession userSession) {
        final List<BAItemModel> sessionAttrs = new ArrayList<BAItemModel>();

        if (userSession != null) {

            final Enumeration<?> enu = userSession.getAttributeNames();

            String attrName = StringPool.BLANK;
            String attrValue = StringPool.BLANK;

            while (enu.hasMoreElements()) {

                attrName = (String) enu.nextElement();
                attrValue = userSession.getAttribute(attrName).toString();

                if (BAConstants.J_PASSWORD.equals(attrName)) {
                    attrValue = BAConstants.PASSWORD_DISPLAY_STRING;
                    sessionAttrs.add(new BAItemModelImpl(attrName, attrValue));
                } else {
                    sessionAttrs.add(new BAItemModelImpl(attrName, attrValue));
                }

            }
        }

        return sessionAttrs;
    }

    /**
     * Gets the accessd ur ls.
     * 
     * @param paths
     *            the paths
     * @param locale
     *            the locale
     * @param timeZone
     *            the time zone
     * @return the accessd ur ls
     */
    public static List<BAItemModel> getAccessedURLs(final List<UserTrackerPath> paths, final Locale locale,
            final TimeZone timeZone) {

        final List<BAItemModel> accessedUrls = new ArrayList<BAItemModel>();

        for (final UserTrackerPath userTrackerPath : paths) {

            accessedUrls.add(new BAItemModelImpl(userTrackerPath.getPath(), FastDateFormatFactoryUtil.getDateTime(
                    locale, timeZone).format(userTrackerPath.getPathDate())));

        }

        return accessedUrls;
    }

    /**
     * Gets the layouts by company id.
     * 
     * @param companyId
     *            the company id
     * @param keywords
     *            the keywords
     * @return the layouts by company id
     */
    public static List<Layout> getLayoutsByCompanyId(final long companyId, final String keywords) {

        final List<Layout> layoutList = new ArrayList<Layout>();

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class,
                PortalClassLoaderUtil.getClassLoader()).add(PropertyFactoryUtil.forName("companyId").eq(companyId));
        try {
            @SuppressWarnings("unchecked")
            final List<Layout> layoutListDQ = LayoutLocalServiceUtil.dynamicQuery(dynamicQuery);

            // filtering
            for (final Layout layout : layoutListDQ) {
                if (BAActionUtil.isMatched(layout.getFriendlyURL(), keywords)) {
                    layoutList.add(layout);
                }
            }

        } catch (final SystemException e) {
            LOG.error(e);
        }
        return layoutList;

    }

    /**
     * Gets the layouts by friendly url dynamic query.
     * 
     * @param companyId
     *            the company id
     * @param criteriaString
     *            the criteria string
     * @return the layouts by friendly url dynamic query
     */
    public static DynamicQuery getLayoutsByFriendlyUrlDynamicQuery(final long companyId, final String criteriaString) {

        return DynamicQueryFactoryUtil
                .forClass(Layout.class, PortalClassLoaderUtil.getClassLoader())
                .add(PropertyFactoryUtil.forName("companyId").eq(companyId))
                .add(PropertyFactoryUtil.forName("friendlyURL").like(
                        BAActionUtil.criteriaStringForDynamicQuery(criteriaString)));
    }

    /**
     * Gets the layouts by friendly url.
     * 
     * @param companyId
     *            the company id
     * @param start
     *            the start
     * @param end
     *            the end
     * @param criteriaString
     *            the criteria string
     * @param layoutId
     *            the layout id
     * @return the layouts by friendly url
     */
    public static List<BAItemModel> getLayoutsByFriendlyUrl(final long companyId, final int start, final int end,
            final String criteriaString, final String layoutId) {

        final List<BAItemModel> layoutItems = new ArrayList<BAItemModel>();

        DynamicQuery dynamicQuery = null;
        try {
            if (Validator.isNotNull(layoutId) && Validator.isNumber(layoutId)) {

                dynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("companyId").eq(companyId))
                        .add(PropertyFactoryUtil.forName("friendlyURL").like(criteriaString))
                        .add(PropertyFactoryUtil.forName("layoutId").eq(Long.parseLong(layoutId)));
            } else {

                dynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("companyId").eq(companyId))
                        .add(PropertyFactoryUtil.forName("friendlyURL").like(criteriaString));
            }

            @SuppressWarnings("unchecked")
            final List<Layout> layoutList = LayoutLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);

            for (final Layout layout : layoutList) {
                layoutItems.add(new BAItemModelImpl(String.valueOf(layout.getLayoutId()), layout.getGroup().getName(),
                        layout.getType(), layout.getFriendlyURL(), BAActionUtil.getLayoutTemplateId(layout
                                .getTypeSettings())));
            }
            return layoutItems;

        } catch (final Exception e) {
            LOG.error(e);
            return layoutItems;
        }

    }

    /**
     * Gets the total layouts.
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param mandatorSelectId
     *            the mandator select id
     * @param type
     *            the type
     * @param boschLayoutId
     *            the bosch layout id
     * @param friendlyUrl
     *            the friendly url
     * @return the total layouts
     */
    public static int getTotalLayouts(final boolean andOperator, final long companyId, final long mandatorSelectId,
            final String type, final String boschLayoutId, final String friendlyUrl) {
        int total = 0;
        try {
            final DynamicQuery layoutDynamicQuery = BAActionUtil.getLayoutDynamicQuery(andOperator, companyId,
                    mandatorSelectId, type, boschLayoutId, friendlyUrl);

            total = (int) LayoutLocalServiceUtil.dynamicQueryCount(layoutDynamicQuery);
        } catch (final Exception e) {
            LOG.error(e);
        }
        return total;
    }

    /**
     * Gets the layouts.
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param mandatorSelectId
     *            the mandator select id
     * @param type
     *            the type
     * @param boschLayoutId
     *            the bosch layout id
     * @param friendlyUrl
     *            the friendly url
     * @param start
     *            the start
     * @param end
     *            the end
     * @return the layouts
     */
    public static List<BAItemModel> getLayouts(final boolean andOperator, final long companyId,
            final long mandatorSelectId, final String type, final String boschLayoutId, final String friendlyUrl,
            final int start, final int end) {
        final List<BAItemModel> layoutItems = new ArrayList<BAItemModel>();

        try {
            final DynamicQuery layoutDynamicQuery = BAActionUtil.getLayoutDynamicQuery(andOperator, companyId,
                    mandatorSelectId, type, boschLayoutId, friendlyUrl);
            @SuppressWarnings("unchecked")
            final List<Layout> layoutList = LayoutLocalServiceUtil.dynamicQuery(layoutDynamicQuery, start, end);
            for (final Layout layout : layoutList) {
                String boschLayoutIdString = null;
                try {
                    boschLayoutIdString = LayoutImportLocalServiceUtil.findByLayoutId(layout.getPlid())
                            .getBoschLayoutId();
                } catch (final NoSuchLayoutImportException e) {
                    //
                    boschLayoutIdString = null;
                }
                layoutItems.add(new BAItemModelImpl(String.valueOf(layout.getPlid()), layout.getGroup().getName(),
                        layout.getType(), layout.getFriendlyURL(), BAActionUtil.getLayoutTemplateId(layout
                                .getTypeSettings()), boschLayoutIdString));
            }

            return layoutItems;

        } catch (final Exception e) {
            LOG.error(e);
            return layoutItems;
        }

    }

    /**
     * Gets the layout dynamic query.
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param mandatorSelectId
     *            the mandator select id
     * @param type
     *            the type
     * @param boschLayoutId
     *            the bosch layout id
     * @param friendlyUrl
     *            the friendly url
     * @return the layout dynamic query
     * @throws Exception
     *             the exception
     */
    private static DynamicQuery getLayoutDynamicQuery(final boolean andOperator, final long companyId,
            final long mandatorSelectId, final String type, final String boschLayoutId, final String friendlyUrl)
            throws Exception {

        final DynamicQuery layoutDynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class,
                PortalClassLoaderUtil.getClassLoader());
        final DynamicQuery layoutImportDynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutImport.class,
                PortletClassLoaderUtil.getClassLoader(BAConstants.LAYOUT_IMPORTER_PORTLETID));

        final Criterion companyIdCriterion = RestrictionsFactoryUtil.eq("companyId", companyId);
        Criterion friendlyUrlCriterion = null;
        Criterion typeCriterion = null;
        Criterion boschLayoutIdCriterion = null;
        Criterion groupIdCriterion = null;
        Criterion plidCriterion = null;
        final boolean isMandatorSelectIdNotNull = Validator.isNotNull(mandatorSelectId);
        final boolean isBoschLayoutIdNotNull = Validator.isNotNull(boschLayoutId);
        final boolean isFriendlyUrlNotNull = Validator.isNotNull(friendlyUrl);
        final boolean isTypeNotNull = Validator.isNotNull(type);

        List<LayoutImport> layoutImportList = new ArrayList<LayoutImport>();

        if (andOperator) {
            // ================Search with All fields================
            Criterion andCriterion = companyIdCriterion;

            // mandatorSelect
            if (isMandatorSelectIdNotNull) {
                groupIdCriterion = RestrictionsFactoryUtil.eq("groupId", mandatorSelectId);
                andCriterion = RestrictionsFactoryUtil.and(andCriterion, groupIdCriterion);
            } else {
                groupIdCriterion = RestrictionsFactoryUtil.in("groupId", BAActionUtil.getMandatorIdList(companyId)
                        .toArray());
                andCriterion = RestrictionsFactoryUtil.and(andCriterion, groupIdCriterion);
            }

            // boschLayoutId (layout_importer table)
            if (isBoschLayoutIdNotNull) {
                final String boschLayoutIdcriteriaString = BAActionUtil.criteriaStringForDynamicQuery(boschLayoutId);

                // boschLayoutIdCriterion = RestrictionsFactoryUtil.eq("boschLayoutId", StringPool.BLANK);
                boschLayoutIdCriterion = RestrictionsFactoryUtil.like("boschLayoutId", boschLayoutIdcriteriaString);
                layoutImportDynamicQuery.add(boschLayoutIdCriterion);
                layoutImportList = LayoutImportLocalServiceUtil.dynamicQuery(layoutImportDynamicQuery);
                // Page Layout ID (_plid)
                List<Long> plidList = BAActionUtil.getPlidListByLiList(layoutImportList);
                if (0 == plidList.size()) {
                    plidList = new ArrayList<Long>(1);
                    plidList.add(0L);
                }
                plidCriterion = RestrictionsFactoryUtil.in("plid", plidList.toArray());
                andCriterion = RestrictionsFactoryUtil.and(andCriterion, plidCriterion);

            }

            // type
            if (isTypeNotNull) {
                typeCriterion = RestrictionsFactoryUtil.like("type", BAActionUtil.criteriaStringForDynamicQuery(type));
                andCriterion = RestrictionsFactoryUtil.and(andCriterion, typeCriterion);
            }

            // friendlyUrl
            if (isFriendlyUrlNotNull) {
                friendlyUrlCriterion = RestrictionsFactoryUtil.like("friendlyURL",
                        BAActionUtil.criteriaStringForDynamicQuery(friendlyUrl));
                andCriterion = RestrictionsFactoryUtil.and(andCriterion, friendlyUrlCriterion);
            }
            layoutDynamicQuery.add(andCriterion);

        } else {
            // ================Search with Any fields================
            Criterion orCriterion = RestrictionsFactoryUtil.eq("plid", 0L);

            // boschLayoutId (layout_importer table)
            if (isBoschLayoutIdNotNull) {
                final String boschLayoutIdcriteriaString = BAActionUtil.criteriaStringForDynamicQuery(boschLayoutId);

                boschLayoutIdCriterion = RestrictionsFactoryUtil.like("boschLayoutId", boschLayoutIdcriteriaString);
                layoutImportDynamicQuery.add(boschLayoutIdCriterion);
                layoutImportList = LayoutImportLocalServiceUtil.dynamicQuery(layoutImportDynamicQuery);
                // Page Layout ID (_plid)
                List<Long> plidList = BAActionUtil.getPlidListByLiList(layoutImportList);
                if (0 == plidList.size()) {
                    plidList = new ArrayList<Long>(1);
                    plidList.add(0L);
                }
                plidCriterion = RestrictionsFactoryUtil.in("plid", plidList.toArray());

                orCriterion = RestrictionsFactoryUtil.or(orCriterion, plidCriterion);
            }

            // type
            if (isTypeNotNull) {
                typeCriterion = RestrictionsFactoryUtil.like("type", BAActionUtil.criteriaStringForDynamicQuery(type));
                orCriterion = RestrictionsFactoryUtil.or(orCriterion, typeCriterion);
            }

            // friendlyUrl
            if (isFriendlyUrlNotNull) {
                friendlyUrlCriterion = RestrictionsFactoryUtil.like("friendlyURL",
                        BAActionUtil.criteriaStringForDynamicQuery(friendlyUrl));
                orCriterion = RestrictionsFactoryUtil.or(orCriterion, friendlyUrlCriterion);
            }

            // mandatorSelect
            if (isMandatorSelectIdNotNull) {
                groupIdCriterion = RestrictionsFactoryUtil.eq("groupId", mandatorSelectId);
                orCriterion = RestrictionsFactoryUtil.or(orCriterion, groupIdCriterion);

                layoutDynamicQuery.add(RestrictionsFactoryUtil.and(companyIdCriterion, orCriterion));
            }

            // make sure only search in all mandators in the current company
            groupIdCriterion = RestrictionsFactoryUtil.in("groupId", BAActionUtil.getMandatorIdList(companyId)
                    .toArray());
            orCriterion = RestrictionsFactoryUtil.and(orCriterion, groupIdCriterion);

            // if ((!isBoschLayoutIdNotNull) && (!isFriendlyUrlNotNull) && (!isTypeNotNull)) {
            // layoutDynamicQuery.add(RestrictionsFactoryUtil.and(companyIdCriterion, groupIdCriterion));
            layoutDynamicQuery.add(RestrictionsFactoryUtil.and(
                    RestrictionsFactoryUtil.and(companyIdCriterion, groupIdCriterion), orCriterion));

        }
        return layoutDynamicQuery;
    }

    /**
     * Gets the plid list by li list.
     * 
     * @param liList
     *            the li list
     * @return the plid list by li list
     */
    private static List<Long> getPlidListByLiList(final List<LayoutImport> liList) {
        final List<Long> plIdList = new ArrayList<Long>();
        for (final LayoutImport li : liList) {
            plIdList.add(li.getLayoutId());
        }
        return plIdList;
    }

    /**
     * Gets the total layouts by friendly url.
     * 
     * @param companyId
     *            the company id
     * @param criteriaString
     *            the criteria string
     * @param layoutId
     *            the layout id
     * @return the total layouts by friendly url
     */
    public static int getTotalLayoutsByFriendlyUrl(final long companyId, final String criteriaString,
            final String layoutId) {
        int total = 0;
        DynamicQuery dynamicQuery = null;
        try {
            if (Validator.isNotNull(layoutId) && Validator.isNumber(layoutId)) {

                dynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("companyId").eq(companyId))
                        .add(PropertyFactoryUtil.forName("friendlyURL").like(criteriaString))
                        .add(PropertyFactoryUtil.forName("layoutId").eq(Long.parseLong(layoutId)));
            } else {

                dynamicQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("companyId").eq(companyId))
                        .add(PropertyFactoryUtil.forName("friendlyURL").like(criteriaString));
            }
            total = (int) LayoutLocalServiceUtil.dynamicQueryCount(dynamicQuery);
        } catch (final Exception e) {
            LOG.error(e);
        }

        return total;
    }

    /**
     * Criteria string for dynamic query.
     * 
     * @param criteriaString
     *            the criteria string
     * @return the string
     */
    public static String criteriaStringForDynamicQuery(String criteriaString) {
        criteriaString = criteriaString.toLowerCase();
        // allow to search with ? and * option
        if (criteriaString.contains(StringPool.STAR)) {
            criteriaString = criteriaString.replace(StringPool.STAR, StringPool.PERCENT);
        }
        if (criteriaString.contains(StringPool.QUESTION)) {
            criteriaString = criteriaString.replace(StringPool.QUESTION, StringPool.UNDERLINE);
        }
        criteriaString = StringPool.PERCENT + criteriaString + StringPool.PERCENT;
        return criteriaString;
    }

    /**
     * Gets the layout template id.
     * 
     * @param typeSettings
     *            the type settings
     * @return the layout template id
     */
    public static String getLayoutTemplateId(final String typeSettings) {
        String layoutTemplateId = null;

        if (Validator.isNull(typeSettings)) {
            return StringPool.BLANK;
        } else {

            final UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);
            typeSettingsProperties.fastLoad(typeSettings);

            layoutTemplateId = typeSettingsProperties.get(BAConstants.LAYOUT_TEMPLATE_PROPERTY);

        }

        if (Validator.isNull(layoutTemplateId)) {
            layoutTemplateId = StringPool.BLANK;
        }

        return layoutTemplateId;
    }

    /**
     * Check plugin valid.
     * 
     * @param pluginContext
     *            the plugin context
     * @return true, if successful
     */
    public static boolean checkPluginValid(final String pluginContext) {

        // load logManagerClass class
        final Class<?> logManagerClass = BAActionUtil.loadClassFromPortletId(pluginContext,
                BAConstants.LOG_MANAGER_CLASS);

        if (null == logManagerClass) {
            return false;
        }

        return true;
    }

    /**
     * Gets the updated layout by mandator.
     * 
     * @param mandatorId
     *            the mandator id
     * @param start
     *            the start
     * @param end
     *            the end
     * @return the updated layout by mandator
     */
    public static List<BAItemModel> getUpdatedLayoutByMandator(final long mandatorId, final int start, final int end) {
        final List<BAItemModel> baItems = new ArrayList<BAItemModel>();
        if (Validator.isNull(mandatorId)) {
            return baItems;
        } else {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                    .forClass(LayoutImport.class,
                            PortletClassLoaderUtil.getClassLoader(BAConstants.LAYOUT_IMPORTER_PORTLETID))
                    .add(PropertyFactoryUtil.forName("groupId").eq(mandatorId))
                    .add(PropertyFactoryUtil.forName("boschLayoutId").eq(
                            BAConstants.MANUALLY_UPDATED_LAYOUT_RESET_HASH_VALUE));
            try {

                @SuppressWarnings("unchecked")
                final List<LayoutImport> layoutImportList = LayoutImportLocalServiceUtil.dynamicQuery(dynamicQuery,
                        start, end);
                for (final LayoutImport layoutImport : layoutImportList) {
                    final Layout layout = LayoutLocalServiceUtil.getLayout(layoutImport.getLayoutId());
                    baItems.add(new BAItemModelImpl(String.valueOf(layoutImport.getLayoutId()), layout.getGroup()
                            .getName(), layout.getType(), layout.getFriendlyURL(), BAActionUtil
                            .getLayoutTemplateId(layout.getTypeSettings())));
                }

            } catch (final Exception e) {
                LOG.error("errors occurred while getting layout");
            }

        }

        return baItems;
    }

    /**
     * Gets the total layouts by friendly url.
     * 
     * @param mandatorId
     *            the mandator id
     * @return the total layouts by friendly url
     */
    public static int getTotalUpdatedLayoutByMandator(final long mandatorId) {
        int total = 0;
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                    .forClass(LayoutImport.class,
                            PortletClassLoaderUtil.getClassLoader(BAConstants.LAYOUT_IMPORTER_PORTLETID))
                    .add(PropertyFactoryUtil.forName("groupId").eq(mandatorId))
                    .add(PropertyFactoryUtil.forName("boschLayoutId").eq(
                            BAConstants.MANUALLY_UPDATED_LAYOUT_RESET_HASH_VALUE));

            total = (int) LayoutImportLocalServiceUtil.dynamicQueryCount(dynamicQuery);
        } catch (final Exception e) {
            LOG.error(e);
        }

        return total;
    }

    /**
     * Gets the group name by group id.
     * 
     * @param groupId
     *            the group id
     * @return the group name by group id
     */
    public static String getGroupNameByGroupId(final long groupId) {
        String groupName = StringPool.BLANK;

        try {
            final Group group = GroupLocalServiceUtil.getGroup(groupId);
            if (null == group) {
                LOG.error("Errors orrcured while getting group by groupId");
                return StringPool.BLANK;
            }
            groupName = group.getName();
        } catch (final Exception e) {
            LOG.error("Errors orrcured while getting group by groupId");
            return StringPool.BLANK;
        }

        return groupName;
    }
    
    /**
     * @param companyId
     * @param groupName
     * @param groupType
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List<UserGroup> getUserGroups(final long companyId, final String userGroupName, final int start, final int end) {
        List<UserGroup> userGroups = new ArrayList<UserGroup>();

        try {
            final DynamicQuery userGroupDynamicQuery = BAActionUtil.getUserGroupDynamicQuery(companyId, userGroupName);
            userGroups = UserGroupLocalServiceUtil.dynamicQuery(userGroupDynamicQuery, start, end);

            return userGroups;

        } catch (final Exception e) {
            LOG.error(e);
            return userGroups;
        }
    }
    
    /**
     * @param companyId
     * @param groupName
     * @param groupType
     * @return
     * @throws Exception
     */
    private static DynamicQuery getUserGroupDynamicQuery(final long companyId, final String userGroupName)
        throws Exception {

        final DynamicQuery userGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(UserGroup.class,
                PortalClassLoaderUtil.getClassLoader());

        Criterion companyIdCriterion = RestrictionsFactoryUtil.eq("companyId", companyId);
        Criterion userGroupNameCriterion = null;
        
        final boolean isUserGroupNameNotNull = Validator.isNotNull(userGroupName);
        
        if(isUserGroupNameNotNull){
        	userGroupNameCriterion = RestrictionsFactoryUtil.ilike("name", BAActionUtil.criteriaStringForDynamicQuery(userGroupName));
        	companyIdCriterion = RestrictionsFactoryUtil.and(companyIdCriterion, userGroupNameCriterion);
        }
        
        userGroupDynamicQuery.add(companyIdCriterion);

        return userGroupDynamicQuery;
    }
    
    /**
     * @param companyId
     * @param roleName
     * @param roleType
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List<Role> getRoles(final long companyId, final String roleName, final int roleType, final int start, final int end) {
        List<Role> roles = new ArrayList<Role>();

        try {
            final DynamicQuery roleDynamicQuery = BAActionUtil.getRoleDynamicQuery(companyId, roleName, roleType);
            roles = RoleLocalServiceUtil.dynamicQuery(roleDynamicQuery, start, end);

            return roles;

        } catch (final Exception e) {
            LOG.error(e);
            return roles;
        }
    }
    
    /**
     * @param companyId
     * @param roleName
     * @param roleType
     * @return
     * @throws Exception
     */
    private static DynamicQuery getRoleDynamicQuery(final long companyId, final String roleName, final int roleType)
        throws Exception {

        final DynamicQuery roleDynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class,
                PortalClassLoaderUtil.getClassLoader());

        Criterion companyIdCriterion = RestrictionsFactoryUtil.eq("companyId", companyId);
        Criterion typeCriterion = RestrictionsFactoryUtil.eq("type", roleType);
        Criterion roleNameCriterion = null;
        
        final boolean isGroupNameNotNull = Validator.isNotNull(roleName);
        
        companyIdCriterion = RestrictionsFactoryUtil.and(companyIdCriterion, typeCriterion);
        
        if(isGroupNameNotNull){
        	roleNameCriterion = RestrictionsFactoryUtil.ilike("name", BAActionUtil.criteriaStringForDynamicQuery(roleName));
        	companyIdCriterion = RestrictionsFactoryUtil.and(companyIdCriterion, roleNameCriterion);
        }
        
        roleDynamicQuery.add(companyIdCriterion);

        return roleDynamicQuery;
    }

    /**
     * @param companyId
     * @param groupName
     * @param groupType
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List<Group> getGroups(final long companyId, final String groupName, final int groupType, final int start, final int end) {
        List<Group> groups = new ArrayList<Group>();

        try {
            final DynamicQuery groupDynamicQuery = BAActionUtil.getGroupDynamicQuery(companyId, groupName, groupType);
            groups = GroupLocalServiceUtil.dynamicQuery(groupDynamicQuery, start, end);

            return groups;

        } catch (final Exception e) {
            LOG.error(e);
            return groups;
        }
    }
    
    /**
     * @param companyId
     * @param groupName
     * @param groupType
     * @return
     * @throws Exception
     */
    private static DynamicQuery getGroupDynamicQuery(final long companyId, final String groupName, final int groupType)
        throws Exception {

        final DynamicQuery groupDynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
                PortalClassLoaderUtil.getClassLoader());

        Criterion companyIdCriterion = RestrictionsFactoryUtil.eq("companyId", companyId);
        Criterion typeCriterion = RestrictionsFactoryUtil.eq("type", groupType);
        Criterion groupNameCriterion = null;
        
        final boolean isGroupNameNotNull = Validator.isNotNull(groupName);
        
        companyIdCriterion = RestrictionsFactoryUtil.and(companyIdCriterion, typeCriterion);
        
        if(isGroupNameNotNull){
        	groupNameCriterion = RestrictionsFactoryUtil.ilike("name", BAActionUtil.criteriaStringForDynamicQuery(groupName));
        	companyIdCriterion = RestrictionsFactoryUtil.and(companyIdCriterion, groupNameCriterion);
        }
        
       	groupDynamicQuery.add(companyIdCriterion);

        return groupDynamicQuery;
    }
    
    /**
     * Gets organizations.
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param userId
     *            userId
     * @param emailAddress
     *            the email address
     * @param firstName
     *            the first name
     * @param middleName
     *            the middle name
     * @param lastName
     *            the last name
     * @param start
     *            the start
     * @param end
     *            the end
     * @return user list
     */
    @SuppressWarnings("unchecked")
	public static List<Organization> getOrganizations(final boolean andOperator, final String organizationName,
            final String organizationType, final int start, final int end) {
        List<Organization> organizations = new ArrayList<Organization>();

        try {
            final DynamicQuery organizationDynamicQuery = BAActionUtil.getOrganizationDynamicQuery(andOperator, organizationName,
            		organizationType);
            organizations = OrganizationLocalServiceUtil.dynamicQuery(organizationDynamicQuery, start, end);

            return organizations;

        } catch (final Exception e) {
            LOG.error(e);
            return organizations;
        }
    }
    
    /**
     * Gets the organization dynamic query.
     * 
     * @param andOperator
     * @param organizationName
     * @param organizationStreet
     * @param organizationCity
     * @param organizationType
     * @param countryName
     * @param regionName
     * @param organizationZip
     * @return
     * @throws Exception
     */
    private static DynamicQuery getOrganizationDynamicQuery(final boolean andOperator, String organizationName,
        String organizationType)
        throws Exception {

        final DynamicQuery userDynamicQuery = DynamicQueryFactoryUtil.forClass(Organization.class,
                PortalClassLoaderUtil.getClassLoader());

        Criterion organizationNameCriterion = null;
        Criterion organizationTypeCriterion = null;
        //Criterion countryIdCriterion = null;
        //Criterion regionIdCriterion = null;
        
        if(Validator.isNull(organizationName)){
        	organizationName = "";
        }
        
        if(Validator.isNull(organizationType)){
        	organizationType = "";
        }
        
        //final boolean isCountryIdNotNull = Validator.isNotNull(countryId);
        //final boolean isRegionIdNotNull = Validator.isNotNull(regionId);

        if (andOperator) {
            // ================Search with All fields================
            Criterion andCriterion = null;

        	organizationNameCriterion = RestrictionsFactoryUtil.ilike("name", BAActionUtil.criteriaStringForDynamicQuery(organizationName));
        	andCriterion = organizationNameCriterion;                

        	organizationTypeCriterion = RestrictionsFactoryUtil.ilike("type", BAActionUtil.criteriaStringForDynamicQuery(organizationType));
            andCriterion = RestrictionsFactoryUtil.and(andCriterion, organizationTypeCriterion);

            /*if (isCountryIdNotNull) {
            	countryIdCriterion = RestrictionsFactoryUtil.eq("countryId", countryId);
            	if(andCriterion != null ){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, countryIdCriterion);
            	} else
            		andCriterion = countryIdCriterion;
                
            }
            

            if (isRegionIdNotNull) {
            	regionIdCriterion = RestrictionsFactoryUtil.eq("regionId", regionId);
            	if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, regionIdCriterion);
            	} else
            		andCriterion = regionIdCriterion;
                
            }*/
            
            
            if(andCriterion != null){
            	userDynamicQuery.add(andCriterion);
            }

        } else {
            // ================Search with Any fields================
            Criterion orCriterion = null;

        	organizationNameCriterion = RestrictionsFactoryUtil.ilike("organizationName", BAActionUtil.criteriaStringForDynamicQuery(organizationName));
        	orCriterion = organizationNameCriterion;                

        	organizationTypeCriterion = RestrictionsFactoryUtil.ilike("organizationType", BAActionUtil.criteriaStringForDynamicQuery(organizationType));
        	orCriterion = RestrictionsFactoryUtil.or(orCriterion, organizationTypeCriterion);
            

            /*if (isCountryIdNotNull) {
            	countryIdCriterion = RestrictionsFactoryUtil.eq("countryId", countryId);
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, countryIdCriterion);
            	} else
            		orCriterion = countryIdCriterion;
            }
            

            if (isRegionIdNotNull) {
            	regionIdCriterion = RestrictionsFactoryUtil.eq("regionId", regionId);
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, regionIdCriterion);
            	} else
            		orCriterion = regionIdCriterion;
            	
            }*/
      

            if(orCriterion != null ){
            	userDynamicQuery.add(orCriterion);
            }
        }
        return userDynamicQuery;
    }
    
    /**
     * Gets users.
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param userId
     *            userId
     * @param emailAddress
     *            the email address
     * @param firstName
     *            the first name
     * @param middleName
     *            the middle name
     * @param lastName
     *            the last name
     * @param start
     *            the start
     * @param end
     *            the end
     * @return user list
     */
    @SuppressWarnings("unchecked")
	public static List<User> getUsers(final boolean andOperator, final long companyId,
            final long userId, final String emailAddress, final String firstName, final String middleName, final String lastName,
            final int start, final int end) {
        List<User> users = new ArrayList<User>();

        try {
            final DynamicQuery userDynamicQuery = BAActionUtil.getUserDynamicQuery(andOperator, companyId,
                    userId, emailAddress, firstName, middleName, lastName);
            users = UserLocalServiceUtil.dynamicQuery(userDynamicQuery, start, end);

            return users;

        } catch (final Exception e) {
            LOG.error(e);
            return users;
        }
    }
    
    
    /**
     * Gets the user dynamic query. Company ID = -1 mean ignore company id parameter
     * 
     * @param andOperator
     *            the and operator
     * @param companyId
     *            the company id
     * @param userId
     *            the user id
     * @param emailAddress
     *            the email address
     * @param firstName
     *            the first name
     * @param middleName
     *            the middle name
     * @param lastName
     * 			  the last name
     * @return the user dynamic query
     * @throws Exception
     *             the exception
     */
    private static DynamicQuery getUserDynamicQuery(final boolean andOperator, final long companyId, final long userId,
            final String emailAddress, final String firstName, final String middleName, final String lastName)
            throws Exception {

        final DynamicQuery userDynamicQuery = DynamicQueryFactoryUtil.forClass(User.class,
                PortalClassLoaderUtil.getClassLoader());

        final Criterion companyIdCriterion = RestrictionsFactoryUtil.eq("companyId", companyId);
        Criterion userIdCriterion = null;
        Criterion emailAddressCriterion = null;
        Criterion firstNameCriterion = null;
        Criterion middleNameCriterion = null;
        Criterion lastNameCriterion = null;
        final boolean isUserIdNotNull = Validator.isNotNull(userId);
        final boolean isEmailAddressNotNull = Validator.isNotNull(emailAddress);
        final boolean isFirstNameNotNull = Validator.isNotNull(firstName);
        final boolean isMiddleNameNotNull = Validator.isNotNull(middleName);
        final boolean isLastNameNotNull = Validator.isNotNull(lastName);

        if (andOperator) {
            // ================Search with All fields================
            Criterion andCriterion = null;
            
            if(companyId != -1){
            	andCriterion = companyIdCriterion;
            }
            
            // userId
            if (isUserIdNotNull) {
            	userIdCriterion = RestrictionsFactoryUtil.eq("userId", userId);
            	if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, userIdCriterion);
            	} else
            		andCriterion = userIdCriterion;                
            }

            // email address
            if (isEmailAddressNotNull) {
            	emailAddressCriterion = RestrictionsFactoryUtil.like("emailAddress", BAActionUtil.criteriaStringForDynamicQuery(emailAddress));
            	if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, emailAddressCriterion);
            	} else
            		andCriterion = emailAddressCriterion;
            }

            // first name
            if (isFirstNameNotNull) {
                firstNameCriterion = RestrictionsFactoryUtil.like("firstName", BAActionUtil.criteriaStringForDynamicQuery(firstName));
                if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, firstNameCriterion);
            	} else
            		andCriterion = firstNameCriterion;
            }

            // middle name
            if (isMiddleNameNotNull) {
            	middleNameCriterion = RestrictionsFactoryUtil.like("middleName", BAActionUtil.criteriaStringForDynamicQuery(middleName));
            	if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, middleNameCriterion);
            	} else
            		andCriterion = middleNameCriterion;
            }
            
            // last name
            if (isLastNameNotNull) {
            	lastNameCriterion = RestrictionsFactoryUtil.like("lastName", BAActionUtil.criteriaStringForDynamicQuery(lastName));
            	if(andCriterion != null){
            		andCriterion = RestrictionsFactoryUtil.and(andCriterion, lastNameCriterion);
            	} else
            		andCriterion = lastNameCriterion;
            }
            
            if(andCriterion != null){
            	userDynamicQuery.add(andCriterion);
            }

        } else {
            // ================Search with Any fields================
            Criterion orCriterion = null;
            
            if(companyId != -1){
            	orCriterion = companyIdCriterion;
            }

            // user id
            if (isUserIdNotNull) {
            	userIdCriterion = RestrictionsFactoryUtil.eq("userId", userId);
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, userIdCriterion);
            	} else
            		orCriterion = userIdCriterion;                
            }

            // email address
            if (isEmailAddressNotNull) {
            	emailAddressCriterion = RestrictionsFactoryUtil.like("emailAddress", BAActionUtil.criteriaStringForDynamicQuery(emailAddress));
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, emailAddressCriterion);
            	} else
            		orCriterion = emailAddressCriterion;
            }

            // first name
            if (isFirstNameNotNull) {
                firstNameCriterion = RestrictionsFactoryUtil.like("firstName", BAActionUtil.criteriaStringForDynamicQuery(firstName));
                if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, firstNameCriterion);
            	} else
            		orCriterion = firstNameCriterion;
            }

            // middle name
            if (isMiddleNameNotNull) {
            	middleNameCriterion = RestrictionsFactoryUtil.like("middleName", BAActionUtil.criteriaStringForDynamicQuery(middleName));
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, middleNameCriterion);
            	} else
            		orCriterion = middleNameCriterion;
            }
            
            // last name
            if (isLastNameNotNull) {
            	lastNameCriterion = RestrictionsFactoryUtil.like("lastName", BAActionUtil.criteriaStringForDynamicQuery(lastName));
            	if(orCriterion != null){
            		orCriterion = RestrictionsFactoryUtil.or(orCriterion, lastNameCriterion);
            	} else
            		orCriterion = lastNameCriterion;
            }

            if(orCriterion != null ){
            	userDynamicQuery.add(orCriterion);
            }
        }
        return userDynamicQuery;
    }
    
    /**
     * Gets the user id list.
     * 
     * @param companyId
     *            the company id
     * @return the mandator id list
     */
    private static List<Long> getMandatorIdList(final long companyId) {
        final List<Long> mandatorIdList = new ArrayList<Long>();
        final List<Group> mandatorList = BAActionUtil.getMandatorList(companyId);
        if (null != mandatorList) {
            for (final Group group : mandatorList) {
                mandatorIdList.add(group.getGroupId());
            }
        }
        return mandatorIdList;
    }
    
    /**
     * Method is populating data from ServiceComponent object.
     * 
     * @return service component list
     */
    public static List<ServiceComponent> getServiceComponentList() {
        try {
            return ServiceComponentLocalServiceUtil.getServiceComponents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        } catch (final Exception e) {
            LOG.error(e);
        }

        return null;
    }
    
    /**
     * Gets the total service.
     * 
     * @param serviceName
     *            the service name
     * @return the total layouts
     */
    public static int getTotalServices(final String serviceName) {
        int total = 0;
        try {
            final DynamicQuery serviceDynamicQuery = BAActionUtil.getServiceDynamicQuery(serviceName);

            total = (int) ServiceComponentLocalServiceUtil.dynamicQueryCount(serviceDynamicQuery);
        } catch (final Exception e) {
            LOG.error(e);
        }
        return total;
    }

    /**
     * Gets the services.
     * 
     * @param serviceName
     *            the service name
     * @return the layouts
     * @throws SystemException 
     */
    @SuppressWarnings("unchecked")
	public static List<ServiceComponent> getServices(final String serviceName, final int start, final int end) throws SystemException {
    	List<ServiceComponent> serviceComponentList = new ArrayList<ServiceComponent>();
        try {
            final DynamicQuery serviceDynamicQuery = BAActionUtil.getServiceDynamicQuery(serviceName);
            serviceComponentList = ServiceComponentLocalServiceUtil.dynamicQuery(serviceDynamicQuery, start, end);
            
            return serviceComponentList;
        } catch (final Exception e) {
            LOG.error(e);
            return serviceComponentList;
        }

    }
    
    /**
     * Gets the service dynamic query.
     * 
     * @param serviceName
     *            the service name
     * @return the service dynamic query
     * @throws Exception
     *             the exception
     */
    private static DynamicQuery getServiceDynamicQuery(final String serviceName){
    	final DynamicQuery serviceDynamicQuery = DynamicQueryFactoryUtil.forClass(ServiceComponent.class,
                PortalClassLoaderUtil.getClassLoader());
    
        Criterion serviceNameCriterion = null;

        final boolean isServiceNameNotNull = Validator.isNotNull(serviceName);

        // service name
        if (isServiceNameNotNull) {
            serviceNameCriterion = RestrictionsFactoryUtil.ilike("buildNamespace", BAActionUtil.criteriaStringForDynamicQuery(serviceName));
            serviceDynamicQuery.add(serviceNameCriterion);
            
        }
        
        return serviceDynamicQuery;
    }
    
    /**
     * Get all users from company id
     * 
     * @param companyId
     *            long
     * @param start
     * 		  int
     * @param end 
     * 		  int         
     * @return 
     */
    public static List<User> getAllUsersByCompanyId(final long companyId, int start, int end) {
        try {
            return UserLocalServiceUtil.getCompanyUsers(companyId, start, end);
        } catch (final Exception e) {
            LOG.error(e);
        }

        return null;
    }
    
    /**
     * Method is checking whether BOSCH COMMONS Portlet is deployed and active.
     * 
     * @return true if portlet is deployt and active
     */
    public static boolean isBoschCommonsDeployed() {
        final Portlet portlet = PortletLocalServiceUtil.getPortletById(BAConstants.BOSCH_COMMONS_PORTLET_ID);
        getAvailableSBPlugins();
        if (portlet != null) {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * Method to get plugin that has _bosch-administration.properties file
     * 
     * @return availableSBPlugins
     */
    public static List<BAItemModel> getAvailableSBPlugins(){
    	// Get all installed plugins
    	List<BAItemModel> installedPlugins = BAActionUtil.getInstalledPlugins(false);
    	List<BAItemModel> availableSBPlugins = new ArrayList<BAItemModel>();
    	
    	if(Validator.isNotNull(installedPlugins)){
    		// Query each plugin folder to check if _bosch-administration.properties file exists
    		for(BAItemModel baItemModel: installedPlugins){
        		String pluginDir = BAActionUtil.getAutoDeployDestDir() + StringPool.SLASH + baItemModel.getItemId();
            	String pluginExcludes = BAPropsValues.RESTRICTED_EXTENSION;
            	
            	if(Validator.isNotNull(pluginExcludes)){
            	    pluginExcludes = "**/*." + pluginExcludes;
            	    pluginExcludes = pluginExcludes.replace(StringPool.COMMA, ",**/*.");   
            	}                
                
            	// Get all files, exclude folders and store them into an array
                final String[] fileArray = FileUtil.find(pluginDir, "**/*.*", pluginExcludes);
                
                // Search array for _bosch-administration.properties
                for(String filePath: fileArray){
                	File file = new File(filePath);
                	if(file.getName().equals(BAConstants.BOSCH_ADMINISTRATION_PROPERTIES)){
                		availableSBPlugins.add(baItemModel);
                		break;
                	}
                }
        		
        	}
    	}
    	
    	return availableSBPlugins;
    }

    public static List<BAItemModel> getHttpSessions(final long companyId) {
        final List<BAItemModel> httpSessionList = new ArrayList<BAItemModel>();

        final Collection<HttpSession> httpSessions = PortalSessionContext.values();

        // get Default user
        User user = null;
        User defaultUser = null;
        try {
            defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);
        } catch (final Exception e) {
            LOG.error("Errors orrcured while getting default user");
        }

        for (final HttpSession httpSession : httpSessions) {
            if (null != httpSession.getAttribute(WebKeys.USER_ID)) {
                // signed-in user
                user = (User) httpSession.getAttribute(WebKeys.USER);
            } else {
                user = defaultUser;
            }

            final BAItemModel baItem = new BAItemModelImpl(httpSession.getId());
            // User Id
            baItem.setItemName(String.valueOf(user.getUserId()));
            // Email
            baItem.setItemValue(user.getScreenName());
            // Screen Name
            baItem.setItemSecondValue(user.getEmailAddress());
            // Last Accessed Time
            baItem.setItemDate(new Date(httpSession.getLastAccessedTime()));

            httpSessionList.add(baItem);
        }

        return httpSessionList;
    }

    public static int getCountHttpSessions() {

        return PortalSessionContext.count();
    }

    public static void setLogLevel(final String servletContextName, final String loggerName, final String priority)
            throws Exception {

        final Class<?> log4jUtilClass = BAActionUtil.loadClassFromServletContextname(servletContextName,
                BAConstants.LOG4JUTIL_CLASS);

        final Method setLevelMethod = log4jUtilClass.getMethod("setLevel", String.class, String.class);

        setLevelMethod.invoke(null, loggerName, priority);

    }
	
	/**
	 * @param portletRequest
	 * @param name
	 * @return
	 */
	public static long[] getLongArray(final PortletRequest portletRequest, final String name) {
		final String value = portletRequest.getParameter(name);
		if (value == null) {
			return null;
		}

		return StringUtil.split(GetterUtil.getString(value), 0L);
	}
}
