<%@include file="/html/init.jsp"%>

<%
	String userId = DMUtil.getUsernameByHttpSession(session);
	String fullName = (String) session.getAttribute("fullName");
%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../assets/ico/favicon.ico">
    
    <link rel="stylesheet" href="/DataMiningDemo/css/sticky-footer-navbar.css">

    <title>Data Mining Demo</title>

    <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.1/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Data Mining Demo</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
            <li style="position: fixed; right: 0; margin-right: 55px;">
            	<a href="/DataMiningDemo/LogoutServlet">
            		<strong>Welcome, <%=userId %>! 
		            	<button class="btn btn-primary">
				  			<i class="icon-user icon-white"></i>Logout
						</button>
					</strong>
				</a>
			</li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <!-- Begin page content -->
    <div class="container">
      <div class="page-header">
        <h1>Applying Association rule & Apriori algorithm</h1>
      </div>
      <p class="lead">
      	<!-- Pin a fixed-height footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS. A fixed navbar has been added with <code>padding-top: 60px;</code> on the <code>body > .container</code>. -->
      	<form class="form-horizontal">
			<fieldset>
			
			<!-- Select Basic -->
			<div class="control-group">
			  <label class="control-label" for="">Student data range:</label><br>
			  <label class="control-label" for="">From</label>
			  <div class="controls" style="display: inline">
			    <select id="from" name="from" class="input-small">
			      <option value="2005">2005</option>
			      <option value="2006">2006</option>
			      <option value="2007" selected="selected">2007</option>
			      <option value="2008">2008</option>
			      <option value="2009">2009</option>
			      <option value="2010">2010</option>
			    </select>
			  </div>
			  
			  <label class="control-label" for="to">To</label>
			  <div class="controls" style="display: inline">
			    <select id="to" name="to" class="input-small">
			      <option value="2005">2005</option>
			      <option value="2006">2006</option>
			      <option value="2007" selected="selected">2007</option>
			      <option value="2008">2008</option>
			      <option value="2009">2009</option>
			      <option value="2010">2010</option>
			    </select>
			  </div>
			</div>
			
			<!-- Select Basic -->
			<div class="control-group">
			  <br>
			</div>
			
			<!-- Text input-->
			<div class="control-group">
			  <label class="control-label" for="lowerBoundMinSup">Lower Minimum Support</label>
			  <div class="controls">
			    <input id="lowerBoundMinSup" name="lowerBoundMinSup" type="text" placeholder="0.1" class="input-small" required="required">
			    <p class="help-block">Lower bound minimum support must less than or equals to upper bound minimum support.</p>
			  </div>
			</div>
			
			<!-- Text input-->
			<div class="control-group">
			  <label class="control-label" for="upperBoundMinSup">Upper Minimum Support</label>
			  <div class="controls">
			    <input id="upperBoundMinSup" name="upperBoundMinSup" type="text" placeholder="1.0" class="input-small" required="required">
			    <p class="help-block">Upper bound minimum support must less than or equals 1.</p>
			  </div>
			</div>
			
			<!-- Text input-->
			<div class="control-group">
			  <label class="control-label" for="numOfRules">Max number of rules</label>
			  <div class="controls">
			    <input id="numOfRules" name="numOfRules" type="text" placeholder="1000" class="input-small" required="required">
			    <p class="help-block">Number of rules to be generated</p>
			  </div>
			</div>
			
			<!-- Select Basic -->
			<div class="control-group">
			  <label class="control-label" for="rankingType">Metric Type</label>
			  <div class="controls">
			    <select id="rankingType" name="rankingType" class="input-medium">
			      <option value="0" selected="selected">Confidence</option>
			      <option value="1">Lift</option>
			      <option value="2">Leverage</option>
			      <option value="3">Conviction</option>
			    </select>
			  </div>
			</div>
			
			<br>
			
			<!-- Text input-->
			<div class="control-group">
			  <label class="control-label" for="metricScore">Minimum Metric Score</label>
			  <div class="controls">
			    <input id="metricScore" name="metricScore" type="text" placeholder="0.1" class="input-small" required="required">
			    
			  </div>
			</div>
			
			</fieldset>
		</form>
			      	
      </p>
      
		<button class="btn btn-primary" id="miningButton">
		  <i class="icon-user icon-white"></i>Process
		</button>
		
		<br><br>
		
		<div id="searchArea" style="text-align: center; display:none">
            Subject name: 
            <input type="text" id="searchRuleInput" placeholder="Data Warehouse" class="input-medium" required="required">
            <label class="control-label" for="in">In</label>
			  <div class="controls" style="display: inline">
			    <select id="searchRuleOption" name="searchRuleOption" class="input-small">
			      <option value="antecedent" selected>Antecedent</option>
                  <option value="consequent">Consequent</option>
                  <option value="both">Both</option>
			    </select>
			  </div>
           
            <button id="searchRuleButton" class="btn btn-primary">
                Search
            </button>
        </div>
        
        <br><br>
		
		<div id="miningResults"></div>
		<br><br>
    </div>
    
    

    <div class="footer">
      <div class="container">
        <p class="text-muted">Data Mining & Data Warehouse Demonstration</p>
      </div>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
  
  <script type="text/javascript">
  $(function() {
	  $('button#miningButton').click(function(){
		  blockUI();
	      var inputLowerMinSupValue = $('input[type=text]#lowerBoundMinSup').val();
	      var inputUpperMinSupValue = $('input[type=text]#upperBoundMinSup').val();
	      var numRules = $('input[type=text]#numOfRules').val();
	      var metricScore = $('input[type=text]#metricScore').val();
	      var from = parseInt($('select#from').val());
	      var to = parseInt($('select#to').val());

	      if(doubleRegex.test(inputLowerMinSupValue) && doubleRegex.test(inputUpperMinSupValue) &&
	         intRegex.test(numRules) && doubleRegex.test(metricScore) && from <= to &&
	         parseFloat(inputLowerMinSupValue) <= parseFloat(inputUpperMinSupValue) && parseFloat(inputUpperMinSupValue) <= 1){
	             $.ajax({
	                  url: '/DataMiningDemo/DataMiningServlet',
	                  data: {
	                      lowerMinSup: inputLowerMinSupValue,
	                      upperMinSup: inputUpperMinSupValue,
	                      numRules: numRules,
	                      metricScore: metricScore,
	                      from: from,
	                      to: to,
	                      rankingType: $('select#rankingType').val()
	                  },
	                  success: function(data){
	                      $('div#miningResults').html(data);
	                  }
	              }).done(function(){
	                  $('div#searchArea').show();
	                  $.unblockUI();
	              });
	          
	      } else {
	          $('div#miningResults').html('<div class="alert alert-danger">' + 'All input values except number of rules must be real number.<br>' +
	                'Lower bound minimum support must less than or equals to upper bound minimum support.<br>' +
	                'Upper bound minimum support must less than or equals 1.<br>' +
	                'Data for mining must be in valid range. (Ex: from 2008 to 2006 is invalid)' + '</div>');
	          $.unblockUI();
	      }
	  });

	  $('button#searchRuleButton').click(function(){
          var searchInput = $('input[type=text]#searchRuleInput').val();
          var searchOption = $('select#searchRuleOption').val();
          blockUI();
          
          if(true){
             $.ajax({
                  url: '/DataMiningDemo/SearchRuleServlet',
                  data: {
                      input: $.trim(searchInput),
                      option: searchOption,
                      rankingType: $('select#rankingType').val(),
                      gradeClassification: $('input#typeOfClassification').val()
                  },
                  success: function(data){
                      $('div#miningResults').html(data);
                      $.unblockUI();
                  }
              });                 
          } else {
               $('div#miningResults').html('<div class="alert alert-danger">Search input cannot be blank.</div>');
          }
          
      });

	  var doubleRegex = /^[0-9]*[.][0-9]+$/;
      var intRegex = /^(0|(\+)?[1-9]{1}[0-9]{0,8}|(\+)?[1-3]{1}[0-9]{1,9}|(\+)?[4]{1}([0-1]{1}[0-9]{8}|[2]{1}([0-8]{1}[0-9]{7}|[9]{1}([0-3]{1}[0-9]{6}|[4]{1}([0-8]{1}[0-9]{5}|[9]{1}([0-5]{1}[0-9]{4}|[6]{1}([0-6]{1}[0-9]{3}|[7]{1}([0-1]{1}[0-9]{2}|[2]{1}([0-8]{1}[0-9]{1}|[9]{1}[0-5]{1})))))))))$/;
      

  });

  </script>
</html>
