package com.bosch.boschadministration.portlet;

import com.bosch.boschadministration.util.BAActionUtil;
import com.bosch.boschadministration.util.BAConstants;
import com.bosch.commons.service.BCConfigLocalServiceUtil;
import com.bosch.layoutimporter.servicebuilder.service.LayoutImportLocalServiceUtil;
import com.bosch.portletbridge.service.PBConfigLocalServiceUtil;
import com.bosch.portletbridge.service.PBLocalServiceUtil;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.CompanyMaxUsersException;
import com.liferay.portal.ContactBirthdayException;
import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactFullNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.EmailAddressException;
import com.liferay.portal.GroupFriendlyURLException;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.RequiredUserException;
import com.liferay.portal.ReservedUserEmailAddressException;
import com.liferay.portal.ReservedUserScreenNameException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserReminderQueryException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.UserSmsException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.cache.MultiVMKeyPoolUtil;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HeapUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.ThreadUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.ServiceComponent;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceComponentLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.enterpriseadmin.util.EnterpriseAdminUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHost;
import org.apache.log4j.Level;

/**
 * Portlet implementation class BoschAdminPortlet.
 */
public class BoschAdminPortlet extends MVCPortlet {

    /** The Constant LOG. */
    public static final Log LOG = LogFactoryUtil.getLog(BoschAdminPortlet.class);

    /**
     * Action method when user clicks on clearAllCaches Button.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws Exception
     *             the exception
     */
    public void clearAllCaches(final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception {
        final String debugText = "All Portletbridge-Caches cleared successfully !";

        MultiVMPoolUtil.removeCache(BAConstants.PB_CONFIG_CACHE_ID);
        MultiVMPoolUtil.removeCache(BAConstants.PB_CONTENT_CACHE_ID);
        MultiVMPoolUtil.removeCache(BAConstants.PB_LAYOUT_CACHE_ID);
        MultiVMPoolUtil.removeCache(BAConstants.PB_URI_CACHE_ID);

        SessionMessages.add(actionRequest, "allCachesCleared");
        LOG.debug(debugText);

        actionResponse.setRenderParameter("baMainTabs", "portlet-bridge");
    }

    /**
     * Submit li form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    public void submitLIFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) {
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        // Actions
        if (cmd.equals(Constants.SEARCH)) {
            // Layout
            this.searchLayout(actionRequest, actionResponse);

        } else if (cmd.equals(BAConstants.DOWNLOAD_FILE)) {
            // download file
            this.downloadXmlFile(BAConstants.LAYOUT_IMPORTER_PORTLETID, BAConstants.LI_PROPS_VALUES_CLASS,
                    actionRequest, actionResponse);

        } else if (cmd.equals(BAConstants.REVERT_UPDATED_LAYOUT)) {
            // revert updated layouts
            this.revertUpdatedLayout(actionRequest, actionResponse);

        } else if (cmd.equals(BAConstants.CLEAR_DB)) {
            // clear DB
            this.clearMandatorsDB(actionRequest, actionResponse);

        } else if (cmd.equals(BAConstants.RESEND_GSA_FEED)){
        	// resend GSA Feed
        	this.resendGSAFeed(actionRequest, actionResponse);
        } else {
            // default command
            this.searchLayout(actionRequest, actionResponse);
        }

    }
    
    /**
     * LRD-1060: resend GSA feed
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    public void resendGSAFeed(final ActionRequest actionRequest, final ActionResponse actionResponse) {
    	
		final String[] selectedMandators = ParamUtil.getParameterValues(actionRequest, "selectedMandators", null);
		final ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(Validator.isNotNull(selectedMandators)){
    		for(final String mandatorName: selectedMandators){
				try {
					final Group mandator = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), mandatorName);
					LayoutImportLocalServiceUtil.generateGSAFeedByXpath(mandatorName);
				} catch (final Exception e) {
					LOG.error("Cannot resend GSA feed for [" + mandatorName + "] since this group is not exist.");
				} 
    		}
		} else {
			LOG.error("selectedMandators param is NULL, the GSA feed cannot be re-sent.");
		}

        actionResponse.setRenderParameter("baMainTabs", "layout-importer");
        actionResponse.setRenderParameter("baLiTabs", "manage-mandator");  
    }

    /**
     * Search layout.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void searchLayout(final ActionRequest actionRequest, final ActionResponse actionResponse) {

        actionResponse.setRenderParameter("baMainTabs", "layout-importer");
        actionResponse.setRenderParameter("baLiTabs", "manage-layout");
        // set parameters
        actionResponse.setRenderParameter("type", ParamUtil.getString(actionRequest, "type"));
        actionResponse.setRenderParameter("boschLayoutId", ParamUtil.getString(actionRequest, "boschLayoutId"));
        actionResponse.setRenderParameter("mandatorSelectId", ParamUtil.getString(actionRequest, "mandatorSelect"));
        actionResponse.setRenderParameter("friendlyUrl", ParamUtil.getString(actionRequest, "friendlyUrl"));
        actionResponse.setRenderParameter("cmd", ParamUtil.getString(actionRequest, "cmd"));
        actionResponse.setRenderParameter("andOperator", ParamUtil.getString(actionRequest, "andOperator"));

    }
    
    /**
     * Submit organization form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException 
     */
    public void searchOrganizationFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException {
        //final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        actionResponse.setRenderParameter("name", ParamUtil.getString(actionRequest, "name"));
        actionResponse.setRenderParameter("type", ParamUtil.getString(actionRequest, "type"));
        actionResponse.setRenderParameter("countryId", ParamUtil.getString(actionRequest, "countryId"));
        actionResponse.setRenderParameter("regionId", ParamUtil.getString(actionRequest, "regionId"));
        actionResponse.setRenderParameter("andOperator", ParamUtil.getString(actionRequest, "andOperator"));
        actionResponse.setRenderParameter("jspPage", BAConstants.SELECT_ORGANIZATION_JSP);
    }
    
    /**
     * Submit community(group) form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException 
     */
    public void searchCommunityFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException {
        //final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        actionResponse.setRenderParameter("name", ParamUtil.getString(actionRequest, "name"));
        actionResponse.setRenderParameter("jspPage", BAConstants.SELECT_COMMUNITY_JSP);
    }
    
    /**
     * Submit user group form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException 
     */
    public void searchUserGroupFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException {
        //final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        actionResponse.setRenderParameter("name", ParamUtil.getString(actionRequest, "name"));
        actionResponse.setRenderParameter("jspPage", BAConstants.SELECT_USER_GROUP_JSP);
    }
    
    /**
     * Submit user form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException 
     */
    public void submitUserFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException {
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        if(cmd.equals(BAConstants.CLONE_USER)){
        	renderClonePage(actionRequest, actionResponse);
        } else 
        	searchUser(actionRequest, actionResponse);
    }
    
    /**
     * Search user.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void searchUser(final ActionRequest actionRequest, final ActionResponse actionResponse) {
    	// Copy search user params in request to response
    	PortalUtil.copyRequestParameters(actionRequest, actionResponse);
        actionResponse.setRenderParameter("baMainTabs", "user");        
    }
    
    /**
     * Search user.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException 
     */
    private void renderClonePage(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException {
		// set parameters
        actionResponse.setRenderParameter("jspPage", "/html/boschadminportlet/clone_user.jsp");
        actionResponse.setRenderParameter("selUserId", ParamUtil.getString(actionRequest, "selUserId"));
    }

    /**
     * Submit di form action.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    public void submitDIFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse) {

        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        // Action
        if (cmd.equals(BAConstants.DOWNLOAD_FILE)) {
            // download file
            this.downloadXmlFile(BAConstants.DOCUMENT_IMPORTER_PORTLETID, BAConstants.DI_PROPS_VALUES_CLASS,
                    actionRequest, actionResponse);
        } else if (cmd.equals(BAConstants.SET_UP_CONFIG)) {

            // setup config - temporarily disable - LRD 1628
            final boolean isSettingUpSuccessful = false;//DIConfigLocalServiceUtil.setupConfig();
            if (isSettingUpSuccessful) {
                SessionMessages.add(actionRequest, "set-up-config-success-msg");
            } else {
                SessionErrors.add(actionRequest, "set-up-config-error-msg");
            }
            actionResponse.setRenderParameter("isSettingUpSuccessful", String.valueOf(!isSettingUpSuccessful));

            actionResponse.setRenderParameter("baMainTabs", "document-importer");
        }

    }

    /**
     * Clear mandators db.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void clearMandatorsDB(final ActionRequest actionRequest, final ActionResponse actionResponse) {

        final String deleteMandatorsString = ParamUtil.getString(actionRequest, "deleteMandators");
        // check deleteMandatorsString
        if (Validator.isNull(deleteMandatorsString)) {
            LOG.error("The deleteMandatorsString is null");
            return;
        }

        final String[] deleteMandators = StringUtil.split(deleteMandatorsString);

        // read request parameters
        final boolean restartImport = ParamUtil.getBoolean(actionRequest, "restartImport");
        for (final String mandator : deleteMandators) {

            // clear Database
            this.clearMandatorDB(mandator, actionRequest, restartImport);

        }

        actionResponse.setRenderParameter("baMainTabs", "layout-importer");
        actionResponse.setRenderParameter("baLiTabs", "manage-mandator");

    }

    /**
     * Revert updated layout.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void revertUpdatedLayout(final ActionRequest actionRequest, final ActionResponse actionResponse) {
        final String revertUpdatedLayoutString = ParamUtil.getString(actionRequest, "revertUpdatedLayouts");
        // check revertUpdatedLayoutString
        if (Validator.isNull(revertUpdatedLayoutString)) {
            LOG.error("The revertUpdatedLayoutString is null");
            return;
        }
        try {

            final String[] updatedLayouts = StringUtil.split(revertUpdatedLayoutString);
            for (final String updatedLayout : updatedLayouts) {
                // change a layout from 'manually_updated' to an unchanged LI layout.
                LayoutImportLocalServiceUtil.updateOrRevertManualLayout(
                        LayoutLocalServiceUtil.getLayout(Long.parseLong(updatedLayout)), true);

            }

        } catch (final Exception e) {
            LOG.error("error occurred while revert manually updated layout");
        }
        actionResponse.setRenderParameter("baMainTabs", "layout-importer");
        actionResponse.setRenderParameter("baLiTabs", "manage-layout");
        // set parameters
        actionResponse.setRenderParameter("mandatorSelectId", ParamUtil.getString(actionRequest, "mandatorSelect"));
        actionResponse.setRenderParameter("keywords", ParamUtil.getString(actionRequest, "keywords"));
        actionResponse.setRenderParameter("boschLayoutId", ParamUtil.getString(actionRequest, "boschLayoutId"));
        actionResponse.setRenderParameter("friendlyUrl", ParamUtil.getString(actionRequest, "friendlyUrl"));
        actionResponse.setRenderParameter("andOperator", ParamUtil.getString(actionRequest, "andOperator"));

    }

    /**
     * Clear mandator db.
     *
     * @param mandatorName
     *            the mandator name
     * @param actionRequest
     *            the action request
     */
    private void clearMandatorDB(final String mandatorName, final ActionRequest actionRequest,
            final boolean restartImport) {

        final ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // get current group
        final Group currentGroup = themeDisplay.getScopeGroup();

        try {

            // get desired group
            final Group desiredGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), mandatorName);

            // check if the user is not inside the current group
            if (currentGroup.equals(desiredGroup)) {
                SessionErrors.add(actionRequest, "you-cannot-delete-layouts");
            } else {

                // restart Import
                if (restartImport) {
                    // delete all layouts of mandator and start a new import
                    LayoutImportLocalServiceUtil.cleanDBForSpecicMandator(desiredGroup.getGroupId(), restartImport);
                } else {
                    // Restarts an import for the group without deleting the layouts
                    LayoutImportLocalServiceUtil.restartNavigationImport(desiredGroup.getGroupId());
                }
                SessionMessages.add(actionRequest, "layouts-deleted-successfully");
            }
        } catch (final Exception e) {
            LOG.error(e);
        }

    }

    /**
     * Download xml file.
     *
     * @param portletId
     *            the portlet id
     * @param className
     *            the class name
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void downloadXmlFile(final String portletId, final String className, final ActionRequest actionRequest,
            final ActionResponse actionResponse) {

        final String mandatorLink = ParamUtil.getString(actionRequest, "mandatorLink");

        // check mandatorLink
        if (Validator.isNull(mandatorLink)) {
            LOG.error("the path is not exist");
            return;
        }

        File file = null;

        try {

            final boolean hasProtocol = HttpUtil.hasProtocol(mandatorLink);
            // check if file is in a URL or local path and copy to archive
            if (hasProtocol) {
                // remote host

                final Class<?> propsClass = BAActionUtil.loadClassFromPortletId(portletId, className);
                // get PROXY
                final HttpHost proxy = new HttpHost((String) propsClass.getField(BAConstants.PROXY_URL_STRING)
                        .get(null), (Integer) propsClass.getField(BAConstants.PROXY_PORT_STRING).get(null));

                // download to archieve
                file = BAActionUtil.downloadToArchive(mandatorLink, proxy);

            } else {
                // local path

                file = new File(mandatorLink);
            }

            // send file to user
            this.downloadFileAction(file, actionRequest, actionResponse);

            // delete temp file if file is in a URL
            if (hasProtocol) {
                FileUtil.delete(file);
            }

        } catch (final Exception e) {
            LOG.error(e);
            return;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.liferay.util.bridges.mvc.MVCPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    @Override
    public void doView(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException,
            PortletException {
        super.doView(renderRequest, renderResponse);
    }

    /**
     * Method getFileAction actions when user clicks on each log file link.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @return the file action
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void getFileAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {

        // get fileKey
        final String fileKey = ParamUtil.getString(actionRequest, "fileKey");

        final String filePath = BAActionUtil.fileMap.get(fileKey);

        if (Validator.isNull(filePath)) {
            LOG.error("the path is not exist");
            return;
        }

        // send to User
        this.downloadFileAction(new File(filePath), actionRequest, actionResponse);
    }

    /**
     * Download file action.
     *
     * @param file
     *            the file
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    private void downloadFileAction(final File file, final ActionRequest actionRequest,
            final ActionResponse actionResponse) throws IOException, PortletException {

        if (FileUtil.exists(file)) {
            ServletResponseUtil.sendFile(PortalUtil.getHttpServletRequest(actionRequest),
                    PortalUtil.getHttpServletResponse(actionResponse), file.getName(), new FileInputStream(file),
                    file.length(), MimeTypesUtil.getContentType(file));
        } else {
            LOG.error("the path is not exist");
            return;
        }
    }

    /**
     * Method submitPluginFileFormAction actions when user clicks on each file link or click on search button in
     * pluginFiles.jsp file
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitPluginFileFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (cmd.equals(Constants.SEARCH)) {
            // redirect to pluginFilesPage
            actionResponse.setRenderParameter(BAConstants.JSP_PAGE, BAConstants.PLUGIN_FILES_JSP);
            // set parameters
            actionResponse.setRenderParameter("keywords", ParamUtil.getString(actionRequest, "keywords"));
            actionResponse.setRenderParameter("installedPluginContext",
                    ParamUtil.getString(actionRequest, "installedPluginContext"));
            actionResponse.setRenderParameter("installedPluginName",
                    ParamUtil.getString(actionRequest, "installedPluginName"));

        } else if (cmd.equals(BAConstants.DOWNLOAD_FILE)) {
            // get file
            this.getFileAction(actionRequest, actionResponse);
        }
    }

    /**
     * Method renderToPluginFilePageAction actions when user clicks on each installed plugin link in the installedPlugin
     * tab in view.jsp file
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void renderToPluginFilePageAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {

        // redirect to pluginFilesPage
        actionResponse.setRenderParameter(BAConstants.JSP_PAGE, BAConstants.PLUGIN_FILES_JSP);
        // set parameters
        actionResponse.setRenderParameter("installedPluginContext",
                ParamUtil.getString(actionRequest, "installedPluginContext"));
        actionResponse.setRenderParameter("installedPluginName",
                ParamUtil.getString(actionRequest, "installedPluginName"));
    }

    /**
     * Method submitLogLevelFormAction in view.jsp file
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitLogLevelFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {

        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        // Action
        if (cmd.equals(BAConstants.UPDATE_CATEGORIES)) {

            // update categories
            this.updateCategories(actionRequest, actionResponse);

            // set parameters
            actionResponse.setRenderParameter("baMainTabs", "log-levels");
            actionResponse.setRenderParameter("baLogLevelTabs", "update-categories");
            actionResponse.setRenderParameter("keywords", ParamUtil.getString(actionRequest, "keywords"));

        } else if (cmd.equals(BAConstants.ADD_CAGEGORY)) {

            // add category
            this.addCategory(actionRequest, actionResponse);

            // set parameters
            actionResponse.setRenderParameter("baMainTabs", "log-levels");
            actionResponse.setRenderParameter("baLogLevelTabs", "add-category");
            actionResponse.setRenderParameter("keywords", ParamUtil.getString(actionRequest, "keywords"));

        } else if (cmd.equals(Constants.SEARCH)) {
            // set parameters
            actionResponse.setRenderParameter("baMainTabs", "log-levels");
            actionResponse.setRenderParameter("baLogLevelTabs", "update-categories");
            actionResponse.setRenderParameter("keywords", ParamUtil.getString(actionRequest, "keywords"));
        }

    }

    /**
     * Adds the category.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void addCategory(final ActionRequest actionRequest, final ActionResponse actionResponse) {

        final String servletContextName = ParamUtil.getString(actionRequest, "logLevelServletContextName");
        final String loggerName = ParamUtil.getString(actionRequest, "loggerName");
        final String priority = ParamUtil.getString(actionRequest, "priority");

        try {

            // set Log Level
            BAActionUtil.setLogLevel(servletContextName, loggerName, priority);

        } catch (final Exception e) {
            LOG.error("Error while updating Log Level");
        }

    }

    /**
     * Update categories.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void updateCategories(final ActionRequest actionRequest, final ActionResponse actionResponse) {

        final Enumeration<String> enu = actionRequest.getParameterNames();

        while (enu.hasMoreElements()) {
            final String name = enu.nextElement();

            if (name.startsWith("logLevel")) {
                final String loggerName = name.substring(8, name.length());
                final String priority = ParamUtil.getString(actionRequest, name, Level.INFO.toString());
                final String servletContextName = ParamUtil.getString(actionRequest, name + "portletId");
                try {
                    if (BAActionUtil.isValidPlugin(servletContextName)) {
                        // set Log Level
                        BAActionUtil.setLogLevel(servletContextName, loggerName, priority);
                    }

                } catch (final Exception e) {
                    LOG.error("Error while updating Log Level");
                }
            }
        }

    }

    /**
     * Method submitEditSessionForm in edit_session.jsp file
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitEditSessionForm(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {

        final String sessionId = ParamUtil.getString(actionRequest, "sessionId");

        // kill session
        try {
            this.invalidateSession(actionRequest, sessionId);

        } catch (final Exception e) {
            LOG.error(e);
        }

        actionResponse.setRenderParameter("baMainTabs", "server-administration");

    }

    /**
     * Submit pB2Form.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitPB2Form(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        // Action
        if (cmd.equals(BAConstants.CLEAN_CACHE)) {

            // clean Cache
            PBLocalServiceUtil.cleanCache();

        } else if (cmd.equals(BAConstants.SET_UP_CONFIG)) {
            // setup config
            final boolean isSettingUpSuccessful = PBConfigLocalServiceUtil.setupConfig();
            if (isSettingUpSuccessful) {
                SessionMessages.add(actionRequest, "set-up-config-success-msg");
            } else {
                SessionErrors.add(actionRequest, "set-up-config-error-msg");
            }
            actionResponse.setRenderParameter("isSettingUpSuccessful", String.valueOf(!isSettingUpSuccessful));

        }

        actionResponse.setRenderParameter("baMainTabs", "portlet-bridge");
    }

    /**
     * LRD-1416 Submit sA2Form. Clean Velocity cache
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitSAForm(final ActionRequest actionRequest, final ActionResponse actionResponse)
             {
    	final String debugText = "All Velocity-Caches cleared successfully !";
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        // Action
        if (cmd.equals(BAConstants.CLEAN_VELOCITY_CACHE)) {

            // clean Velocity cache        	
        	PortalCache _portalCache = MultiVMKeyPoolUtil.getCache(BAConstants.LIFERAY_RESOURCE_CACHE_UTIL_CLASS);
        	
        	_portalCache.removeAll();
        	
            LOG.debug(debugText);
        }

        actionResponse.setRenderParameter("baMainTabs", "server-administration");
        actionResponse.setRenderParameter("baSaTabs", "caches-title");
    }
    
    /**
     * LRD-1448 Submit IpForm
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void submitIPForm(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {
    	
        final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);       
        
        // Action
        if (cmd.equals(BAConstants.RESET_BUILD_NUMBER)) {
        	// Reset build number of a service
        	resetBuildNumber(actionRequest, actionResponse);
        } else if (cmd.equals(BAConstants.REMOVE_SERVICE_COMPONENT)) {
        	// Remove a service from service component table
        	removeServiceComponent(actionRequest, actionResponse);
        } else {
        	// default action is search a service
        	searchService(actionRequest, actionResponse);
        }

        actionResponse.setRenderParameter("baMainTabs", "installed-plugins");
        actionResponse.setRenderParameter("baIpTabs", "service-administration");
    }
    
    /**
     * LRD-1448 Reset build number
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void resetBuildNumber(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {
    	
	    final String debugText = "Reset build number successfully!";
	   	final String resetServiceComponentString = ParamUtil.getString(actionRequest, "resetServiceComponents");
	    
	   	// check resetServiceComponentString
        if (Validator.isNull(resetServiceComponentString)) {
            LOG.error("The resetServiceComponentString is null");
            return;
        }
        
        try {

            final String[] resetServiceComponents = StringUtil.split(resetServiceComponentString);
            for (final String serviceComponentIdString : resetServiceComponents) {
                // set build number of service component to 0
                ServiceComponent serviceComponent = ServiceComponentLocalServiceUtil.getServiceComponent(Long.valueOf(serviceComponentIdString));
                serviceComponent.setBuildNumber(0);
                ServiceComponentLocalServiceUtil.updateServiceComponent(serviceComponent);
            }

        } catch (final Exception e) {
       	 	SessionErrors.add(actionRequest, "cannot-reset-build-number");
            LOG.error("error occurred while resetting build number");
        }
   	
        LOG.debug(debugText);
    }
    
    /**
     * LRD-1448 Remove Service Component
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws PortletException
     *             the portlet exception
     */
    public void removeServiceComponent(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {
    	final String debugText = "Removed service component successfully!";
        	
    	 final String removeServiceComponentString = ParamUtil.getString(actionRequest, "resetServiceComponents");
         // check resetServiceComponentString
         if (Validator.isNull(removeServiceComponentString)) {
             LOG.error("The removeServiceComponentString is null");
             return;
         }
         
         try {

             final String[] removeServiceComponents = StringUtil.split(removeServiceComponentString);
             for (final String serviceComponentIdString : removeServiceComponents) {
                 // remove service component
            	 ServiceComponentLocalServiceUtil.deleteServiceComponent(Long.valueOf(serviceComponentIdString));
             }

         } catch (final Exception e) {
        	 SessionErrors.add(actionRequest, "cannot-remove-service-component");
             LOG.error("error occurred while removing service component");
         }
    	
        LOG.debug(debugText);
    }
    
    /**
     * Search service.
     *
     * @param actionRequest
     *            the action request
     * @param actionResponse
     *            the action response
     */
    private void searchService(final ActionRequest actionRequest, final ActionResponse actionResponse) {
        // set parameters
        actionResponse.setRenderParameter("serviceName", ParamUtil.getString(actionRequest, "serviceName"));
    }

    public void submitCVFormAction(final ActionRequest actionRequest, final ActionResponse actionResponse)
            throws IOException, PortletException {

        final String cacheName = ParamUtil.getString(actionRequest, "cacheName");
        final String cacheKey = ParamUtil.getString(actionRequest, "cacheKey");
        // final Object cacheValue = MultiVMPoolUtil.get(cacheName, cacheKey);
        String cacheValue = StringPool.BLANK;
        try {
            final Object cacheObj = MultiVMPoolUtil.get(cacheName, cacheKey);
            if (null != cacheObj) {
                cacheValue = (String) cacheObj;
            } else {
                cacheValue = LanguageUtil.get(
                        (PortletConfig) actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG),
                        actionRequest.getLocale(), "cache-is-not-exit");
            }
        } catch (final Exception e) {
            cacheValue = LanguageUtil.get(
                    (PortletConfig) actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG),
                    actionRequest.getLocale(), "cache-is-not-exit");
        }

        actionResponse.setRenderParameter("baMainTabs", "cache-viewer");
        actionResponse.setRenderParameter("cacheValue", cacheValue);
        actionResponse.setRenderParameter("cacheName", cacheName);
        actionResponse.setRenderParameter("cacheKey", cacheKey);
    }

    /**
     * Invalidate session.
     *
     * @param actionRequest
     *            the action request
     * @param sessionId
     *            the session id
     * @throws Exception
     *             the exception
     */
    protected void invalidateSession(final ActionRequest actionRequest, final String sessionId) throws Exception {

        final HttpSession userSession = PortalSessionContext.get(sessionId);

        if (userSession != null) {
            try {
                if (!actionRequest.getPortletSession().getId().equals(sessionId)) {

                    userSession.invalidate();
                }
            } catch (final Exception e) {
                LOG.error(e);
            }
        }
    }

    @Override
    public void serveResource(final ResourceRequest resourceRequest, final ResourceResponse resourceResponse)
            throws IOException, PortletException {

        // get resourceID
        final String resourceID = resourceRequest.getResourceID();
        // get Writer from resourceResponse
        final PrintWriter out = resourceResponse.getWriter();
        // get CacheName and CacheKey from resourceRequest
        final String cacheName = ParamUtil.getString(resourceRequest, "cacheName");
        final String cacheKey = ParamUtil.getString(resourceRequest, "cacheKey");

        // initialize Cache Value
        Object cacheValueObj = true;
        final StringBuffer dataBuffer = new StringBuffer();

        if ("viewCache".equals(resourceID)) {
            // ============== View Cache ==============
            try {
                if (Validator.isNotNull(cacheName) && Validator.isNotNull(cacheKey)) {
                    // get cacheValueObj from MultiVMPoolUtil
                    cacheValueObj = MultiVMPoolUtil.get(cacheName, cacheKey);
                }

                if (null == cacheValueObj) {
                    // cache is not exist
                    dataBuffer.append(BAConstants.FALSE);
                } else if (this.isCacheValueEditable(cacheValueObj)) {
                    // arrayResults: [isCacheValueEditable],[cacheType],[cacheValue]
                    dataBuffer.append(BAConstants.TRUE);
                    dataBuffer.append(StringPool.COMMA);
                    dataBuffer.append(cacheValueObj.getClass().getName());
                    dataBuffer.append(StringPool.COMMA);
                    dataBuffer.append(cacheValueObj.toString());
                } else {
                    // arrayResults: [isCacheValueEditable],[cacheType],[cacheValue]
                    dataBuffer.append(BAConstants.FALSE);
                    dataBuffer.append(StringPool.COMMA);
                    dataBuffer.append(cacheValueObj.getClass().getName());
                    dataBuffer.append(StringPool.COMMA);
                    dataBuffer.append(cacheValueObj.toString());
                }

            } catch (final Exception e) {
                LOG.error("Error while getting Cache Value");
                dataBuffer.append(BAConstants.FALSE);
            }
        } else if ("updateCache".equals(resourceID)) {
            // ============== Update Cache ==============
            final String cacheType = ParamUtil.getString(resourceRequest, "cacheType");
            final String cacheValue = ParamUtil.getString(resourceRequest, "cacheValue");
            // update cacheValue
            if (Validator.isNotNull(cacheName) && Validator.isNotNull(cacheKey) && Validator.isNotNull(cacheType)
                    && Validator.isNotNull(cacheValue)) {
                try {
                    Serializable cacheValueSer = null;
                    if (Boolean.class.getName().equals(cacheType)) {
                        cacheValueSer = Boolean.valueOf(cacheValue);
                    } else if (Character.class.getName().equals(cacheType)) {
                        if (1 < cacheValue.length()) {
                            throw new NumberFormatException();
                        }
                        cacheValueSer = cacheValue.charAt(0);
                    } else if (Byte.class.getName().equals(cacheType)) {
                        cacheValueSer = Byte.valueOf(cacheValue);
                    } else if (Short.class.getName().equals(cacheType)) {
                        cacheValueSer = Short.valueOf(cacheValue);
                    } else if (Integer.class.getName().equals(cacheType)) {
                        cacheValueSer = Integer.valueOf(cacheValue);
                    } else if (Long.class.getName().equals(cacheType)) {
                        cacheValueSer = Long.valueOf(cacheValue);
                    } else if (Float.class.getName().equals(cacheType)) {
                        cacheValueSer = Float.valueOf(cacheValue);
                    } else if (Double.class.getName().equals(cacheType)) {
                        cacheValueSer = Double.valueOf(cacheValue);
                    } else {
                        cacheValueSer = cacheValue;
                    }

                    // removing cache
                    MultiVMPoolUtil.remove(cacheName, cacheKey);
                    // put cache value back
                    MultiVMPoolUtil.put(cacheName, cacheKey, cacheValueSer);

                    dataBuffer.append(BAConstants.TRUE);
                    dataBuffer.append(StringPool.COMMA);
                    dataBuffer.append(cacheValueSer.toString());

                } catch (final NumberFormatException e) {
                    dataBuffer.append(BAConstants.FALSE);
                }
            } else {
                dataBuffer.append(BAConstants.FALSE);
            }

        }
        out.print(dataBuffer);
        out.flush();
        out.close();
    }

    /**
     * The <b>isCacheValueEditable</b> method only allows whether updating cache
     *
     * @param cacheName
     * @param cacheKey
     * @param cacheObj
     * @return
     */
    private boolean isCacheValueEditable(final Object cacheObj) {
        final Class<? extends Object> cacheValueClass = cacheObj.getClass();
        if (!(cacheObj instanceof String) && cacheObj.toString().contains(StringPool.AT)) {
            // is a instance of POJO class
            return false;
        } else {
            return true;
        }

    }
    
    /**
     * Create clone user
     * 
     * @param actionRequest
     * @param actionResponse
     * @return User
     * @throws Exception
     */
    public User createClonalUser(final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception{
    	final ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		User clonalUser = null;

		final long selUserId = ParamUtil.getLong(actionRequest, "selUserId");
		final User selUser = UserLocalServiceUtil.getUser(selUserId);

		final Contact contact = selUser.getContact();

		final boolean autoPassword = ParamUtil.getBoolean(actionRequest,
				"autoPassword", false);
		
		String password1 = null;
		String password2 = null;

		String reminderQueryQuestion = null;

		String reminderQueryAnswer = null;
		
		if(!autoPassword){
			password1 = actionRequest.getParameter("password1");
			password2 = actionRequest.getParameter("password2");

			reminderQueryQuestion = ParamUtil.getString(actionRequest,
					"reminderQueryQuestion");

			if (reminderQueryQuestion.equals(EnterpriseAdminUtil.CUSTOM_QUESTION)) {
				reminderQueryQuestion = ParamUtil.getString(actionRequest,
						"reminderQueryCustomQuestion");
			}

			reminderQueryAnswer = ParamUtil.getString(actionRequest,
					"reminderQueryAnswer");
		}
		
		final boolean autoScreenName = ParamUtil.getBoolean(actionRequest,
				"autoScreenName", false);
		final String screenName = ParamUtil.getString(actionRequest, "screenName");
		final String emailAddress = ParamUtil
				.getString(actionRequest, "emailAddress");

		final String firstName = BeanParamUtil.getString(selUser, actionRequest,
				"firstName");
		final String middleName = BeanParamUtil.getString(selUser, actionRequest,
				"middleName");
		final String lastName = BeanParamUtil.getString(selUser, actionRequest,
				"lastName");
		final int prefixId = BeanParamUtil.getInteger(contact, actionRequest,
				"prefixId");
		final int suffixId = BeanParamUtil.getInteger(contact, actionRequest,
				"suffixId");
		final boolean male = BeanParamUtil.getBoolean(selUser, actionRequest, "male",
				true);

		final long facebookId = 0;
		final String openId = ParamUtil.getString(actionRequest, "openId");
		final String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
		final String languageId = ParamUtil.getString(actionRequest, "languageId");
		final String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		final String greeting = ParamUtil.getString(actionRequest, "greeting");
		final String comments = ParamUtil.getString(actionRequest, "comments");
		final String smsSn = ParamUtil.getString(actionRequest, "smsSn");
		final String aimSn = ParamUtil.getString(actionRequest, "aimSn");
		final String facebookSn = ParamUtil.getString(actionRequest, "facebookSn");
		final String icqSn = ParamUtil.getString(actionRequest, "icqSn");
		final String jabberSn = ParamUtil.getString(actionRequest, "jabberSn");
		final String msnSn = ParamUtil.getString(actionRequest, "msnSn");
		final String mySpaceSn = ParamUtil.getString(actionRequest, "mySpaceSn");
		final String skypeSn = ParamUtil.getString(actionRequest, "skypeSn");
		final String twitterSn = ParamUtil.getString(actionRequest, "twitterSn");
		final String ymSn = ParamUtil.getString(actionRequest, "ymSn");
		final boolean sendEmail = ParamUtil.getBoolean(actionRequest,
				"sendEmail", true);

		final Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

		final int birthdayMonth = ParamUtil.getInteger(actionRequest,
				"birthdayMonth", birthdayCal.get(Calendar.MONTH));
		final int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay",
				birthdayCal.get(Calendar.DATE));
		final int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear",
				birthdayCal.get(Calendar.YEAR));

		final long[] groupIds = BAActionUtil.getLongArray(actionRequest,
				"groupsSearchContainerPrimaryKeys");
		final long[] organizationIds = BAActionUtil.getLongArray(actionRequest,
				"organizationsSearchContainerPrimaryKeys");
		final long[] roleIds = BAActionUtil.getLongArray(actionRequest,
				"rolesSearchContainerPrimaryKeys");

		List<UserGroupRole> userGroupRoles = null;

		if ((actionRequest.getParameter("groupRolesGroupIds") != null)
				|| (actionRequest.getParameter("groupRolesRoleIds") != null)) {

			userGroupRoles = EnterpriseAdminUtil
					.getUserGroupRoles(actionRequest);
		}

		final long[] userGroupIds = BAActionUtil.getLongArray(actionRequest,
				"userGroupsSearchContainerPrimaryKeys");

		final ServiceContext serviceContext = ServiceContextFactory.getInstance(
				User.class.getName(), actionRequest);

		try {
			clonalUser = UserServiceUtil.addUser(themeDisplay.getCompanyId(),
					autoPassword, password1, password2, autoScreenName,
					screenName, emailAddress, facebookId, openId,
					LocaleUtil.getDefault(), firstName, middleName, lastName,
					prefixId, suffixId, male, birthdayMonth, birthdayDay,
					birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, serviceContext);

			for (UserGroupRole userGroupRole : userGroupRoles) {
				userGroupRole.setUserId(clonalUser.getUserId());
			}

			clonalUser = UserServiceUtil.updateUser(clonalUser.getUserId(),
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					false, reminderQueryQuestion, reminderQueryAnswer,
					screenName, emailAddress, facebookId, openId,
					LanguageUtil.getLanguageId(LocaleUtil.getDefault()),
					timeZoneId, greeting, comments, firstName, middleName,
					lastName, prefixId, suffixId, male, birthdayMonth,
					birthdayDay, birthdayYear, smsSn, aimSn, facebookSn, icqSn,
					jabberSn, msnSn, mySpaceSn, skypeSn, twitterSn, ymSn,
					jobTitle, groupIds, organizationIds, roleIds,
					userGroupRoles, userGroupIds, serviceContext);
			
			// Creating clonal user succeeded, then move to success page
			if(Validator.isNotNull(clonalUser)){			
				actionResponse.setRenderParameter("jspPage", BAConstants.CLONE_SUCCESS_PAGE);
				actionResponse.setRenderParameter("clonalUserId", String.valueOf(clonalUser.getUserId()));
			}

		} catch (final Exception e) {
			if (e instanceof NoSuchUserException
					|| e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.setRenderParameter(BAConstants.JSP_PAGE,	BAConstants.VIEW_JSP);
				
				actionResponse.setRenderParameter("baMainTabs", "user");
			} else if (e instanceof AddressCityException
					|| e instanceof AddressStreetException
					|| e instanceof AddressZipException
					|| e instanceof CompanyMaxUsersException
					|| e instanceof ContactBirthdayException
					|| e instanceof ContactFirstNameException
					|| e instanceof ContactFullNameException
					|| e instanceof ContactLastNameException
					|| e instanceof DuplicateUserEmailAddressException
					|| e instanceof DuplicateUserScreenNameException
					|| e instanceof EmailAddressException
					|| e instanceof GroupFriendlyURLException
					|| e instanceof NoSuchCountryException
					|| e instanceof NoSuchListTypeException
					|| e instanceof NoSuchRegionException
					|| e instanceof PhoneNumberException
					|| e instanceof RequiredUserException
					|| e instanceof ReservedUserEmailAddressException
					|| e instanceof ReservedUserScreenNameException
					|| e instanceof UserEmailAddressException
					|| e instanceof UserIdException
					|| e instanceof UserPasswordException
					|| e instanceof UserReminderQueryException
					|| e instanceof UserScreenNameException
					|| e instanceof UserSmsException
					|| e instanceof WebsiteURLException) {

				if (e instanceof NoSuchListTypeException) {
					NoSuchListTypeException nslte = (NoSuchListTypeException) e;

					SessionErrors.add(actionRequest, e.getClass().getName()
							+ nslte.getType());
				} else {
					SessionErrors.add(actionRequest, e.getClass().getName(), e);
				}

				if (e instanceof RequiredUserException) {
					actionResponse.setRenderParameter(BAConstants.JSP_PAGE, BAConstants.VIEW_JSP);
					actionResponse.setRenderParameter("baMainTabs", "user");
				}
				
				// Copy request parameter from ACTION REQUEST to ACTION RESPONSE
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);

				actionResponse.setRenderParameter(BAConstants.JSP_PAGE, BAConstants.CLONE_USER_JSP);
				actionResponse.setRenderParameter("selUserId", Long.toString(selUserId));
				
				// Set the exception class name for which page to be displayed first, if the exception is passwordexception, then
				// password page need to be displayed first
				actionResponse.setRenderParameter("exceptionClassName", e.getClass().getName());
			} else {
				throw e;
			}
		}

		return clonalUser;
    }
    
    /**
     * Submit Adv. Tools form
     * 
     * @param actionRequest
     * @param actionResponse
     */
    public void submitAdvToolsForm(ActionRequest actionRequest, ActionResponse actionResponse){
    	final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        // Action
        if (cmd.equals(BAConstants.GENERATE_THREAD_DUMP)) {
            // generate thread dump
        	generateThreadDump(actionRequest, actionResponse);
        } else if (cmd.equals(BAConstants.GENERATE_HEAP_DUMP)){
        	// generate heap dump
        	generateHeapDump(actionRequest, actionResponse);
        }
    }
    
    /**
     * Generate thread dump
     * 
     * @param actionRequest
     * @param actionResponse
     */
    private void generateThreadDump(final ActionRequest actionRequest, final ActionResponse actionResponse){
    	boolean isGeneratingSuccessfully = false;
    	
    	try {
    		if (LOG.isInfoEnabled()) {
        		LOG.info(ThreadUtil.threadDump());
        		isGeneratingSuccessfully = true;
        		SessionMessages.add(actionRequest, "generate-thread-dump-success");
    		}
    		else {
    			LOG.error(
    				"Thread dumps require the log level to be at least INFO for " +
    					getClass().getName());
    			
    			SessionErrors.add(actionRequest, "generate-thread-dump-fail");
    		}
    	} catch (final Exception ex) {
    		LOG.error("Error while generating thread dump.");
    		SessionErrors.add(actionRequest, "generate-thread-dump-fail");
    	}
    	
    	
    	actionResponse.setRenderParameter("isGeneratingSuccessfully", String.valueOf(isGeneratingSuccessfully));
    	actionResponse.setRenderParameter("baMainTabs", "advanced-tools");
    }
    
    /**
     * Generate heap dump
     * 
     * @param actionRequest
     * @param actionResponse
     */
    private void generateHeapDump(final ActionRequest actionRequest, final ActionResponse actionResponse){
    	boolean isGeneratingSuccessfully = false;
    	
    	try {
    		final String dumpFilePath = System.getProperty("liferay.log.root") +  File.separator + "HeapDump.log";
    		final File dumpFile = new File(dumpFilePath);
    		
    		if(!dumpFile.exists()){
    			dumpFile.createNewFile();
    		}    
    		
    		final boolean live = ParamUtil.getBoolean(actionRequest, "live", true);
    		final boolean binary = ParamUtil.getBoolean(actionRequest, "binary", true);
    		
    		HeapUtil.heapDump(live, binary, dumpFilePath);
    		isGeneratingSuccessfully = true;
    		
    		SessionMessages.add(actionRequest, "generate-heap-dump-success");
    	} catch (final Exception e){
    		SessionErrors.add(actionRequest, "generate-heap-dump-fail");
    	}
    	
    	actionResponse.setRenderParameter("isGeneratingSuccessfully", String.valueOf(isGeneratingSuccessfully));
    	actionResponse.setRenderParameter("baMainTabs", "advanced-tools");
    }
    
    /**
     * 
     * Submit BC form
     * 
     * @param actionRequest
     * @param actionResponse
     */
    public void submitBCForm(ActionRequest actionRequest, ActionResponse actionResponse){
    	final String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
    	
    	// Action
    	if (cmd.equals(BAConstants.SET_UP_CONFIG)) {
            // setup config
            final boolean isSettingUpSuccessful = BCConfigLocalServiceUtil.setupConfig();
            if (isSettingUpSuccessful) {
                SessionMessages.add(actionRequest, "set-up-config-success-msg");
            } else {
                SessionErrors.add(actionRequest, "set-up-config-error-msg");
            }
            
            actionResponse.setRenderParameter("isSettingUpSuccessful", String.valueOf(!isSettingUpSuccessful));

        }

        actionResponse.setRenderParameter("baMainTabs", "bosch-commons");
    }
    
    /**
     * 
     * Submit BP form
     * 
     * @param actionRequest
     * @param actionResponse
     */
    public void submitBPForm(ActionRequest actionRequest, ActionResponse actionResponse){
    	final String pluginName = ParamUtil.getString(actionRequest, "pluginName");
    	final String className = ParamUtil.getString(actionRequest, "className");
    	final String methodName = ParamUtil.getString(actionRequest, "methodName");
    	String pluginId = StringPool.BLANK;
    	
    	PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(pluginName);
    	
    	if(Validator.isNotNull(portletApp)){
    		Portlet portlet = portletApp.getPortlets().get(0);
    		pluginId = portlet.getPortletId();
    	}
    	
		try {
			final MethodKey methodKey = new MethodKey(className, methodName);
			Object returnObject = PortletClassInvoker.invoke(false, pluginId, methodKey);
			
			if(Validator.isNotNull(returnObject)){
				// If returnObject is an instance of Boolean class, e.g. set up config successfully or not
				// In the future, returnObject can be instance of other classes 
				if(returnObject instanceof Boolean){
					final Boolean isSettingUpSuccessful = (Boolean) returnObject;
					if(!isSettingUpSuccessful){
						SessionErrors.add(actionRequest, pluginName + BAConstants.ERROR_MESSAGE);
					}
				}
			// If there is nothing to return (i.e. from void method)
			} else {
			//	SessionMessages.add(actionRequest, pluginName + BAConstants.SUCCESS_MESSAGE);
			}
			
		} catch (final Exception e) {
			LOG.error("There is an error while invoking the selected method.");
			SessionErrors.add(actionRequest, pluginName + BAConstants.ERROR_MESSAGE);
		}

        actionResponse.setRenderParameter("baMainTabs", "bosch-plugins");
    }
}