<%@include file="/html/init.jsp" %>

<%
User selUser = (User)request.getAttribute("selUser");

PasswordPolicy passwordPolicy = (PasswordPolicy)request.getAttribute("passwordPolicy");

boolean passwordResetDisabled = false;

if ((selUser.getLastLoginDate() == null) && passwordPolicy.isChangeable() && passwordPolicy.isChangeRequired()) {
	passwordResetDisabled = true;
}

boolean passwordReset = false;

if (passwordResetDisabled) {
	passwordReset = true;
}
else {
	passwordReset = BeanParamUtil.getBoolean(selUser, request, "passwordReset");
}

boolean autoPassword = ParamUtil.getBoolean(request, "autoPassword", false);
%>

<liferay-ui:error-marker key="errorSection" value="password" />

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="password" /></h3>

<aui:input inlineLabel="left" label="generate-auto-password" name="autoPassword" type="checkbox" value="<%= autoPassword %>"/>

<liferay-ui:error exception="<%= UserPasswordException.class %>">

	<%
	UserPasswordException upe = (UserPasswordException)errorException;
	%>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_ALREADY_USED %>">
		<liferay-ui:message key="that-password-has-already-been-used-please-enter-in-a-different-password" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS %>">
		<liferay-ui:message key="that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_INVALID %>">
		<liferay-ui:message key="that-password-is-invalid-please-enter-in-a-different-password" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_LENGTH %>">
		<%= LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false) %>
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_NOT_CHANGEABLE %>">
		<liferay-ui:message key="your-password-cannot-be-changed" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_SAME_AS_CURRENT %>">
		<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-in-a-different-password" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_TRIVIAL %>">
		<liferay-ui:message key="that-password-is-too-trivial" />
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_YOUNG %>">
		<%= LanguageUtil.format(pageContext, "you-cannot-change-your-password-yet-please-wait-at-least-x-before-changing-your-password-again", LanguageUtil.getTimeDescription(pageContext, passwordPolicy.getMinAge() * 1000), false) %>
	</c:if>

	<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
		<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
	</c:if>
</liferay-ui:error>

<aui:fieldset cssClass="passwordFieldSet">
	<aui:input autocomplete="off" label="new-password" name="password1" size="30" type="password" />

	<aui:input autocomplete="off" label="enter-again" name="password2" size="30" type="password" />
</aui:fieldset>

<h3><liferay-ui:message key="reminder" /></h3>

<%
	boolean hasCustomQuestion = true;
%>

<div class="portlet-msg-alert alert-message display-none">
	<liferay-ui:message key="alert-generate-auto-password"/>
</div>

<aui:fieldset cssClass="reminderQueryFieldSet">
	<aui:select label="question" name="reminderQueryQuestion">

		<%
		Set<String> questions = selUser.getReminderQueryQuestions();
		for (String question : questions) {
			if (selUser.getReminderQueryQuestion().equals(question)) {
				hasCustomQuestion = false;
		%>

				<aui:option label="<%= question %>" selected="<%= true %>" value="<%= question %>" />
				<%--<option selected="<%= true %>" value="<%= question %>"><liferay-ui:message key="<%= question %>" /></option> --%>
		<%
			}
			else {
		%>
				
				<aui:option label="<%= question %>" />

		<%
			}
		}

		if (hasCustomQuestion && Validator.isNull(selUser.getReminderQueryQuestion())) {
			hasCustomQuestion = false;
		}
		
		if(!hasCustomQuestion) {
		%>
		
			<aui:option label="write-my-own-question" selected="<%= hasCustomQuestion %>" value="<%= EnterpriseAdminUtil.CUSTOM_QUESTION %>" />
		
		<%
		} else {
		%>
		
			<option value="<%= EnterpriseAdminUtil.CUSTOM_QUESTION %>" selected="<%= hasCustomQuestion%>"><liferay-ui:message key="write-my-own-question" /></option>
		
		<%
		}
		%>
	</aui:select>

	<div id="<portlet:namespace />customQuestionDiv">
		<aui:input fieldParam="reminderQueryCustomQuestion" label="custom-question" name="reminderQueryQuestion" />
	</div>

	
	<aui:input label="answer" name="reminderQueryAnswer" size="50" value="<%= selUser.getReminderQueryAnswer() %>" />
</aui:fieldset>

<aui:script use="aui-base">
	var autoPassword = A.one('#<portlet:namespace/>autoPassword');
	var autoPasswordCheckbox = A.one('#<portlet:namespace/>autoPasswordCheckbox');
	var reminderQueryQuestion = A.one('#<portlet:namespace />reminderQueryQuestion');
	var customQuestionDiv = A.one('#<portlet:namespace />customQuestionDiv');
	var passwordFieldSet = A.one('.passwordFieldSet');
	var reminderQueryFieldSet = A.one('.reminderQueryFieldSet');
	var alertMessageFieldSet = A.one('.alert-message');

	if (<%= !hasCustomQuestion %> && customQuestionDiv) {
		customQuestionDiv.hide();
	}
	
	if(<%= autoPassword %>){
		passwordFieldSet.hide();
		reminderQueryFieldSet.hide();
		alertMessageFieldSet.replaceClass('display-none', 'display-block');
	}
	
	if (autoPasswordCheckbox) {
		autoPasswordCheckbox.on(
			'click',
			function(event) {
				if(autoPassword){
					if(autoPassword.get('value') == 'false'){
						passwordFieldSet.show();
						reminderQueryFieldSet.show();
						alertMessageFieldSet.replaceClass('display-block', 'display-none');
					} else {
						passwordFieldSet.hide();
						reminderQueryFieldSet.hide();
						alertMessageFieldSet.replaceClass('display-none', 'display-block');
					}
				}
			}
		);
	}

	if (reminderQueryQuestion) {
		reminderQueryQuestion.on(
			'change',
			function(event) {
				if (event.target.val() == '<%= EnterpriseAdminUtil.CUSTOM_QUESTION %>') {
					var reminderQueryCustomQuestion = A.one('#<portlet:namespace />reminderQueryCustomQuestion');

					if (customQuestionDiv) {
						customQuestionDiv.show();
					}

					<%
					
					for (String question : PropsUtil.getArray("users.reminder.queries.questions")) {
					%>

						if (reminderQueryCustomQuestion && (reminderQueryCustomQuestion.val() == '<%= UnicodeFormatter.toString(question) %>')) {
							reminderQueryCustomQuestion.val('');
						}

					<%
					}
					%>

					Liferay.Util.focusFormField(reminderQueryCustomQuestion);
				}
				else{
					if (customQuestionDiv) {
						customQuestionDiv.hide();
					}

					Liferay.Util.focusFormField(A.one('#<portlet:namespace />reminderQueryAnswer'));
				}
			}
		);
	}
</aui:script>
