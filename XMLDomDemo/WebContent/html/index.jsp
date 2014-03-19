<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/XMLDomDemo/css/bootstrap.vertical-tabs.min.css">
	    <link rel="stylesheet" href="/XMLDomDemo/css/index.css">
	
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<title>XML Demo</title>
	</head>
	<body>
		<center>
			<h1>XML Demo</h1>
		</center>
		<div style="width: 100%">
			<div style="width: 33%; float: left">
				<h2>Books.xml</h2>
				&lt;?xml version="1.0" encoding="UTF-8"?&gt;<br>
			&lt;bookstore&gt;<br>
			&nbsp;
			&lt;book category="cooking"&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;title lang="en"&gt;Everyday Italian&lt;/title&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;Giada De Laurentiis&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;year&gt;2005&lt;/year&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;price&gt;30.00&lt;/price&gt;<br>
			&nbsp;
			&lt;/book&gt;<br>
			&nbsp;
			&lt;book category="children"&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;title lang="en"&gt;Harry Potter&lt;/title&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;J K. Rowling&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;year&gt;2005&lt;/year&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;price&gt;29.99&lt;/price&gt;<br>
			&nbsp;
			&lt;/book&gt;<br>
			&nbsp;
			&lt;book category="web"&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;title lang="en"&gt;XQuery Kick Start&lt;/title&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;James McGovern&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;Per Bothner&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;Kurt Cagle&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;James Linn&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;Vaidyanathan Nagarajan&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;year&gt;2003&lt;/year&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;price&gt;49.99&lt;/price&gt;<br>
			&nbsp;
			&lt;/book&gt;<br>
			&nbsp;
			&lt;book category="web" cover="paperback"&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;title lang="en"&gt;Learning XML&lt;/title&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;author&gt;Erik T. Ray&lt;/author&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;year&gt;2003&lt;/year&gt;<br>
			  &nbsp;&nbsp;&nbsp;
			  &lt;price&gt;39.95&lt;/price&gt;<br>
			&nbsp;
			&lt;/book&gt;<br>
			&lt;/bookstore&gt;
			</div>
			<div style="width: 67%; float: left">
	            <hr/>
	            <div class="col-xs-9">
	                <!-- Tab panes -->
	                <div class="tab-content">
	                  <div class="tab-pane active" id="1">
	                  		<h2>XML DOM Accessing Nodes</h2>
	                  		<ol>
	                  			<li><strong>getElementsByTagName() method</strong>
	                  			<br/>
	                  				<div>
	                  					<div class="code">xmlDoc.getElementsByTagName("title");</div>
	                  					<div></div>
	                  				</div>
	                  			<br/>
	                  			<li><strong>Looping through (traversing) the nodes tree</strong>
	                  			<br/>
	                  				<div>
	                  					<div class="code">
											var xmlDoc=loadXMLDoc("books.xml");<br>
											var	x=xmlDoc.getElementsByTagName("title");<br>
										<br>
										for (i=0;i&lt;x.length;i++)<br>
										&nbsp;&nbsp;{<br>
										&nbsp;&nbsp;document.write(x[i].childNodes[0].nodeValue);<br>
										&nbsp;&nbsp;document.write("&lt;br&gt;");<br>
										&nbsp;&nbsp;}
										</div>
										<div class="example_code">
										
											Everyday Italian
											Harry Potter
											XQuery Kick Start
											Learning XML
										</div>
									</div>
								<br/>
	                  			<li><strong>Navigating the node tree, using the node relationships.</strong>
	                  				<div>Test</div>
	                  		</ol>
	                  </div>
	                  <div class="tab-pane" id="2">
	                  		<h2>XML DOM Node Information</h2>
	                  </div>
	                  <div class="tab-pane" id="3">
	                  		<h2>XML DOM Node List</h2>
	                  </div>
	                  <div class="tab-pane" id="4">
	                  		<h2>XML DOM Traverse Node Tree</h2>
	                  </div>
	                  <div class="tab-pane" id="5">
	                  		<h2>XML DOM Navigating Node</h2>
	                  </div>
	                </div>
	            </div>
	
	            <div class="col-xs-3"> <!-- required for floating -->
	                <!-- Nav tabs -->
	                <ul class="nav nav-tabs tabs-right">
	                  <li class="active"><a href="#1" data-toggle="tab">XML DOM Accessing Nodes</a></li>
	                  <li><a href="#2" data-toggle="tab">XML DOM Node Information</a></li>
	                  <li><a href="#3" data-toggle="tab">XML DOM Node List</a></li>
	                  <li><a href="#4" data-toggle="tab">XML DOM Traverse Node Tree</a></li>
	                  <li><a href="#5" data-toggle="tab">XML DOM Navigating Node</a></li>
	                </ul>
	            </div>
			</div>
		</div>
	</body>
</html>

<script type="text/javascript">
var xmlFilePath = '/XMLDomDemo/books.xml';
// load XML doc
function loadXMLDoc(filename)
{
if (window.XMLHttpRequest)
  {
  xhttp=new XMLHttpRequest();
  }
else // code for IE5 and IE6
  {
  xhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xhttp.open("GET",filename,false);
xhttp.send();
return xhttp.responseXML;
}

// Load XML String
function loadXMLString(txt) 
{
if (window.DOMParser)
  {
  parser=new DOMParser();
  xmlDoc=parser.parseFromString(txt,"text/xml");
  }
else // code for IE
  {
  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  xmlDoc.async=false;
  xmlDoc.loadXML(txt); 
  }
return xmlDoc;
}
</script>