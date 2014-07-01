<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>

	<!---------- CSS ------------>	
    <link href="/static/css/addMedicalProfile.css" rel="stylesheet" >
</head>

<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">
        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		
        
                <h1>Login</h1>

            
            </div>
                        
            <form id="send" action="/getToken">
            	
                <p>

                <label for="email">Email *</label>
                <input id="email" type="text" name="email" value="" />
                </p>
                
                <p>
                <label for="website">Password *</label>
                <input id="website" type="password" name="password" value="" />
                </p>
                
                <p>

                <button id="submit" type="submit" value="Login">Submit</button>
                </p>
                
            </form>
            
		<div id="required">
		<p>* Required Fields<br/>
		</p>
		</div>


            </div>
        
        <!--END #signup-inner -->
        </div>
        
    <!--END #signup-form -->   
    </div>

</body>
</html>

