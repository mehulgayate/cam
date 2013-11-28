<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Medical Profile</title>

	<!---------- CSS ------------>
	
    <link href="${pageContext.request.contextPath}/static/css/addMedicalProfile.css" rel="stylesheet" >
</head>

<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">
        
        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		<h1>Add Your Medical Profile <br/> FREE Today!</h1>

            
            </div>
		           
            <form id="send" action="/medical/addMedicalProfile">
            	
                <p>

                <label for="name">Boold Pressure *</label>
                <input id="name" type="text" name="booldPressure" value="" />
                </p>
                
                <p>
                <label for="company">Energy Expenditure *</label>
                <input id="company" type="text" name="energyExpenditure" value="" />
                </p>
                
                <p>

                <label for="email">Missed Medication *</label>
                <input id="email" type="text" name="missedMedication" value="" />
                </p>
                
                <p>
                <label for="website">Salt Intake *</label>
                <input id="website" type="text" name="saltIntake" value="" />
                </p>
                                
                <p>
				<input type="hidden" name="auth_token" value="${token.token }">
                <button id="submit" type="submit">Submit</button>
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
