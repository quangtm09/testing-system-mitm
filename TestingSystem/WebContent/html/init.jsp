<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="constants.TSConstants"%>
<%@page import="constants.RoleConstants"%>
<%@page import="constants.LogConstants"%>
<%@page import="constants.PermissionConstants"%>
<%@page import="util.TSUtil"%>
<%@page import="util.StringPool"%>
<%@page import="util.HibernateUtil"%>

<%@page import="model.User"%>
<%@page import="model.Account"%>
<%@page import="model.AccountRoleMap"%>
<%@page import="model.Permission"%>
<%@page import="model.Role"%>
<%@page import="model.RolePermissionMap"%>
<%@page import="model.Logs"%>

<%@page import="dao.UserDao"%>
<%@page import="dao.AccountDao"%>
<%@page import="dao.RoleDao"%>
<%@page import="dao.LogDao"%>
<%@page import="dao.RolePermissionMapDao"%>
<%@page import="dao.PermissionDao"%>
<%@page import="dao.impl.UserDaoImpl"%>
<%@page import="dao.impl.AccountDaoImpl"%>
<%@page import="dao.impl.RoleDaoImpl"%>
<%@page import="dao.impl.LogDaoImpl"%>
<%@page import="dao.impl.RolePermissionMapDaoImpl"%>
<%@page import="dao.impl.PermissionDaoImpl"%>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="org.apache.log4j.MDC"%>

<link rel="stylesheet" type="text/css" href="<%=TSConstants.JQUERY_CSS%>"/>

<script type="text/javascript" src="/TestingSystem/js/jquery.js"></script>
<script type="text/javascript" src="/TestingSystem/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/TestingSystem/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/TestingSystem/js/jquery.pajinate.min.js"></script>
<script type="text/javascript" src="<%=TSConstants.TESTING_SYSTEM_JS%>"></script>
<script type="text/javascript" src="<%=TSConstants.ROLE_MANAGEMENT_JS%>"></script>
<script type="text/javascript" src="<%=TSConstants.LOG_MANAGEMENT_JS%>"></script>

<div id="domMessage" style="display:none;"> 
    <img src="/TestingSystem/images/loader.gif" alt="Loading..."/><br/><h1>Now loading... Please wait</h1>
</div>