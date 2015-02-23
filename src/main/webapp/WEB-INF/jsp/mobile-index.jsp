<!DOCTYPE html>
<!--
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
     KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
-->
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="format-detection" content="telephone=no" />
        <!-- WARNING: for iOS 7, remove the width=device-width and height=device-height attributes. See https://issues.apache.org/jira/browse/CB-4323 -->
        <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />
        <link rel="stylesheet" type="text/css" href="/static/css/custom.min.css" />        
        <link rel="stylesheet" type="text/css" href="/static/css/jquery.mobile.icons.min.css" />        
        <link rel="stylesheet" type="text/css" href="/static/css/jquery.mobile.structure-1.4.0.min.css" />
        
        
        <title>CAM</title>
    </head>
    <body style="font-size: x-large;">
       <div data-role="page" style="height: 100% !important; position:relative !important;" id="loginPage" data-theme="b">

	<div data-role="header">
		<h1>CAM - Login</h1>
	</div><!-- /header -->

	<div role="main" class="ui-content" >
		<label for="email">Email :</label>
		<input type="text" name="email" id="email" value="">
		<label for="password">Password</label>
		<input type="password" name="password" id="password" value="">
		<button class="ui-shadow ui-btn ui-corner-all" id="login">Submit</button>
		<br>
		<br>
		<a class="ui-shadow ui-btn ui-corner-all" href="#registerPage" id="login">Register</a>
	</div><!-- /content -->

	<div data-role="footer" style="bottom:0; position:absolute !important; top: auto !important; width:100%;">
		<h4>Click On submit to Login</h4>
	</div><!-- /footer -->
</div><!-- /page -->


 <div data-role="page" style="height: 100% !important; position:relative !important;" id="registerPage">

	<div data-role="header">
		<h1>CAM - Register</h1>
	</div><!-- /header -->

	<div role="main" class="ui-content">
		<label for="name">Name</label>
		<input type="text" name="password" id="namereg" value="">
		<label for="email">Email :</label>
		<input type="text" name="email" id="emailreg" value="">
		<label for="password">Password</label>
		<input type="password" name="password" id="passwordreg" value="">
		<input type="hidden" name="id" id="idHidden" value="">
		<input type="hidden" name="id" id="auth" value="">
		<a class="ui-shadow ui-btn ui-corner-all" href="registerPage" id="register">Submit</a>
	</div><!-- /content -->

	<div data-role="footer" style="bottom:0; position:absolute !important; top: auto !important; width:100%;">
		<h4>Click On submit to Register</h4>
	</div><!-- /footer -->
</div><!-- /page -->

        <script type="text/javascript" src="/static/js/jquery-2.1.0.min.js"></script>  
        <script type="text/javascript" src="/static/js/jquery.mobile-1.4.0.min.js"></script>            
        <script type="text/javascript" src="/static/js/index.js"></script>
        <script type="text/javascript">
        
        $(function(){     	
        	
        	
        	$("#register").click(function(){
        		$.ajax({
        			url : "/register",
        			type : "post",
        			data : "email="+$("#emailreg").val()+"&password="+$("#passwordreg").val()+"&name="+$("#namereg").val(),
        			dataType : "json",
        			success : function(data) {				
        				alert("Successfully Registered !");
        				$.mobile.changePage('#loginPage');


        			},
        			error : function() {
        				
					alert(data.reponseText);
        			}
        		});
        	});
        	
		$("#login").click(function(){
        		
        		$.ajax({
        			url : "/login",
        			type : "post",
        			data : "email="+$("#email").val()+"&password="+$("#password").val(),
        			dataType : "json",
        			success : function(data) {        				
        				if(data.success=="success"){
        				$("#idHidden").val(data.id);
        				$("#auth").val(true);        				
        				alert("Please note your private key : "+data.privateKey)
        				location.href="/mobile/encrypted-data";
        			}else if(data.fail=="fail"){
        				alert("email / password incorrect");
        			}

        			},
        			error : function() {
        				

        			}
        		});
        	});
        	
        	}, false);        
        </script>
        
        
    </body>
</html>
