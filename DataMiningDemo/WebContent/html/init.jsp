<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="util.DMUtil" %>
<%@page import="constant.DMConstant" %>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>

<script type="text/javascript" src="/DataMiningDemo/js/jquery.js"></script>
<script type="text/javascript" src="/DataMiningDemo/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/DataMiningDemo/js/jquery.pajinate.min.js"></script>

<link rel="stylesheet" href="/DataMiningDemo/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/DataMiningDemo/bootstrap/css/bootstrap-theme.min.css">
<script src="/DataMiningDemo/bootstrap/js/bootstrap.min.js"></script>

<div id="domMessage" style="display:none;"> 
    <img src="/DataMiningDemo/images/loader.gif" alt="Loading..."/><br/><h1>Now loading... Please wait</h1>
</div>