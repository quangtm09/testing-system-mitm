<%@include file="/html/init.jsp"%>
<%
	RoleDao roleDao = new RoleDaoImpl();
	Account selAccount = (Account) request.getAttribute("selAccount");
	List<Role> roleList = roleDao.findAll();
%>

<table id="hor-minimalist-b" summary="User Accounts">
	<thead>
    	<tr>
            <th scope="col">Role</th>
            <th scope="col"></th>
        </tr>
    </thead>
	<c:set var="count" value="0"/>
	<c:forEach items="<%=accountList %>" var="account">
		<tr>
			<c:set var="count" value="${count + 1}"/>
			<td>${count}.</td>
			<td>${account.accId}</td>
			<td>
				<c:forEach items="${account.accountRoleMapsForAccId}" var="aRM">
					${aRM.role.roleName}<br/>
				</c:forEach>
			</td>
			<td>
				<a title="Change Password" href="javascript:openChangePasswordDialog('${account.accId}');" onclick=";"><img src="/TestingSystem/images/change-password-icon.png"/></a>
				<a title="Edit Roles" href=""><img src="/TestingSystem/images/add_role_icon.png"/></a>
				<a title="Delete" onclick=""><img src="/TestingSystem/images/delete_icon.png"/></a>
			</td>
		</tr>
	</c:forEach>
</table>