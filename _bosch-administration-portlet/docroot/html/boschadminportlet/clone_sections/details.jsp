<%@include file="/html/init.jsp"%>

<%
User selUser = (User)request.getAttribute("selUser");	

Contact selContact = (Contact)request.getAttribute("selContact");

String displayEmailAddress = StringPool.BLANK;

if (selUser != null) {
	displayEmailAddress = selUser.getDisplayEmailAddress();
}

Calendar birthday = CalendarFactoryUtil.getCalendar();

birthday.set(Calendar.MONTH, Calendar.JANUARY);
birthday.set(Calendar.DATE, 1);
birthday.set(Calendar.YEAR, 1970);

if (selContact != null) {
	birthday.setTime(selContact.getBirthday());
}

%>

<liferay-ui:error-marker key="errorSection" value="details" />

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="details" /></h3>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<aui:select bean="<%= selContact %>" label="title[person]" model="<%= Contact.class %>" name="prefixId" listType="<%= ListTypeConstants.CONTACT_PREFIX %>" listTypeFieldName="prefixId" showEmptyOption="<%= true %>" />

	<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class %>" message="the-screen-name-you-requested-is-already-taken" />

	<liferay-ui:error exception="<%= GroupFriendlyURLException.class %>">

		<%
		GroupFriendlyURLException gfurle = (GroupFriendlyURLException)errorException;
		%>

		<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DUPLICATE %>">
			<liferay-ui:message key="the-screen-name-you-requested-is-associated-with-an-existing-friendly-url" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ReservedUserScreenNameException.class %>" message="the-screen-name-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />

	<aui:input name="screenName" />
	
	<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

	<c:choose>
		<c:when test="<%= (selUser != null) && !EnterpriseAdminUtil.hasUpdateEmailAddress(permissionChecker, selUser) %>">
			<aui:field-wrapper name="emailAddress">
				<%= displayEmailAddress %>

				<aui:input name="emailAddress" type="hidden" value="<%= selUser.getEmailAddress() %>" />
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>
			<aui:input name="emailAddress" />
		</c:otherwise>
	</c:choose>

	<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= ContactFullNameException.class %>" message="please-enter-a-valid-first-middle-and-last-name" />

	<aui:input name="firstName" />

	<aui:input name="middleName" />

	<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />

	<aui:input name="lastName" />

	<aui:select bean="<%= selContact %>" label="suffix" model="<%= Contact.class %>" name="suffixId" listType="<%= ListTypeConstants.CONTACT_SUFFIX %>" listTypeFieldName="suffixId" showEmptyOption="<%= true %>" />
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<c:if test="<%= selUser != null %>">
		<liferay-ui:error exception="<%= DuplicateUserIdException.class %>" message="the-user-id-you-requested-is-already-taken" />
		<liferay-ui:error exception="<%= ReservedUserIdException.class %>" message="the-user-id-you-requested-is-reserved" />
		<liferay-ui:error exception="<%= UserIdException.class %>" message="please-enter-a-valid-user-id" />

		<aui:field-wrapper name="userId">
			<%= selUser.getUserId() %>

			<aui:input name="userId" type="hidden" value="<%= selUser.getUserId() %>" />
		</aui:field-wrapper>
	</c:if>

	<liferay-ui:error exception="<%= ContactBirthdayException.class %>" message="please-enter-a-valid-date" />

	<aui:input bean="<%= selContact %>" model="<%= Contact.class %>" name="birthday" value="<%= birthday %>" />

	<aui:select bean="<%= selContact %>" label="gender" model="<%= Contact.class %>" name="male">
		<aui:option label="male" value="true" />
		<aui:option label="female" value="false" />
	</aui:select>

	<aui:input name="jobTitle" />
</aui:fieldset>