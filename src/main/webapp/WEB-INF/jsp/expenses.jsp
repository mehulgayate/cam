<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
        <meta charset="utf-8" />
        <meta name="format-detection" content="telephone=no" />
        <!-- WARNING: for iOS 7, remove the width=device-width and height=device-height attributes. See https://issues.apache.org/jira/browse/CB-4323 -->
        <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />
        <link rel="stylesheet" type="text/css" href="css/custom.min.css" />        
        <link rel="stylesheet" type="text/css" href="css/jquery.mobile.icons.min.css" />        
        <link rel="stylesheet" type="text/css" href="css/jquery.mobile.structure-1.4.0.min.css" />
        
        <title>Wallet</title>
        
         <script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>  
        <script type="text/javascript" src="js/jquery.mobile-1.4.0.min.js"></script>            
        <script type="text/javascript" src="cordova.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
        
        <script type="text/javascript">

$(function(){
	document.addEventListener("deviceready", function(){
	/* $("#getExpensesButton").click(function(){
		
		$.ajax({
			url : "http://173.248.178.102:8080/expense/get.json",
			type : "post",
			data : "startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val()+"&id="+window.localStorage.getItem("id"),
			dataType : "json",
			success : function(data) {
				 $("#expenseList").empty();
				$.each( data, function( key, value ) {					 
				$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
					    '<h2>'+value.date+'</h2>'+
					    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
					      '<li>'+value.type+'</li>'+
					     ' <li>'+value.amount+'</li>'+
					   ' </ul>'+
					  '</li>')			
				});
				
				$.mobile.changePage('#showExpensesReportPage');				
				// $('#expenseList').listview('refresh');
				 //$(".cola").collapsibleset( "refresh" );
			},
			error : function() {
				

			},
			complete: function() {
	            $('#expenseList').listview('refresh');
	            $("#expenseList ul").each(function(i) {
	                $(this).listview(); 
	            });
	            $('#expenseList').find('li[data-role=collapsible]').collapsible({refresh:true});
	            

	        } 
		});
	});*/
	
			
		$.ajax({
			url : "http://ec2-54-69-128-107.us-west-2.compute.amazonaws.com:8080/medical/profile",
			type : "GET",
			data : "id="+window.localStorage.getItem("medicalProfileId"),
			dataType : "json",
			success : function(data) {	
					
				
					$("#expenseList").empty();
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Blood Pressure</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.bloodPressure+'</li>'+
						     ' <li>Threshold : '+data.threshold.bloodPressure+'</li>'+
						     ' <li>Tree Location : '+data.profile.bloodPressureTreeLocation+'</li>'+
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Energy Expenditure</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.energyExpenditure+'</li>'+
						     ' <li>Threshold : '+data.threshold.energyExpenditure+'</li>'+
						     ' <li>Tree Location : '+data.profile.energyExpenditureTreeLocation+'</li>'+
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Missed Medication</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.missedMedication+'</li>'+
						     ' <li>Threshold : '+data.threshold.missedMedication+'</li>'+
						     ' <li>Tree Location : '+data.profile.energyExpenditureTreeLocation+'</li>'+
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Salt Intake</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.saltIntake+'</li>'+
						     ' <li>Threshold : '+data.threshold.saltIntake+'</li>'+
						     ' <li>Tree Location : '+data.profile.saltIntakeTreeLocation+'</li>'+
						   ' </ul>'+
						  '</li>');
					
					$.mobile.changePage('#showExpensesReportPage');	
			},
			error : function(data) {			
				alert("fail"+data.responseText);
				
			},
			complete: function() {
	            $('#expenseList').listview('refresh');
	            $("#expenseList ul").each(function(i) {
	                $(this).listview(); 
	            });
	            $('#expenseList').find('li[data-role=collapsible]').collapsible({refresh:true});
	            

	        } 
		});
	
	
	}, false);
	
});


function logout(){
	window.localStorage.removeItem("id");
	window.localStorage.setItem("auth",false);
	location.href="index.html";
}

</script>
    </head>
<body style="font-size: x-large;">
<div data-role="page" id="showExpensesPage" data-theme="b">

	<div data-role="header">
		<h1>Profile Review</h1>
		    <a href="javascript:logout();" data-icon="gear" class="ui-btn-right">Logout</a>
		
	</div><!-- /header -->

	<div role="main" class="ui-content">
		<label for="text-basic">Start Date</label>
		<input type="date" name="text-basic" id="startDate" value="2014-02-01">
		<label for="text-basic">End Date</label>
		<input type="date" name="text-basic" id="endDate" value="2014-02-10">
		<button class="ui-shadow ui-btn ui-corner-all" id="getExpensesButton">Submit</button>
	</div><!-- /content -->

	<div data-role="footer" style="bottom:0; position:absolute !important; top: auto !important; width:100%;">
		<div data-role="navbar">
   		 <ul>
	        <li><a href="#addExpensePage">Add Profile</a></li>
	        <li><a href="#showExpensesPage" class="ui-btn-active">Show Profile</a></li>
    	</ul>
</div><!-- /navbar -->
	</div><!-- /footer -->
</div><!-- /page -->


<div data-role="page" id="showExpensesReportPage" data-theme="b">
	<div data-role="header">
		<h1>Profile Review</h1>
		    <a href="javascript:logout();" data-icon="gear" class="ui-btn-right">Logout</a>
		
	</div><!-- /header -->

	<div role="main" class="ui-content">
		<ul data-role="listview" class="ui-listview-outer" data-inset="true" id="expenseList">
   
	</ul>
	</div><!-- /content -->

	<div data-role="footer" style="bottom:0; position:absolute !important; top: auto !important; width:100%;">
		<div data-role="navbar">
   		 <ul>
	        <li><a href="#addExpensePage">Add Profile</a></li>
	        <li><a href="#showExpensesPage" class="ui-btn-active">Show Profile</a></li>
    	</ul>
</div><!-- /navbar -->
	</div><!-- /footer -->
</div><!-- /page -->

<div data-role="page" id="addExpensePage" data-theme="b">

	<div data-role="header">
		<h1>Add Your Medical Profile</h1>
    <a href="javascript:logout();" data-icon="gear" class="ui-btn-right">Logout</a>

	</div><!-- /header -->

	<div class="ui-content">
		<form id="send" action="/medical/addMedicalProfile">
            	
                <p>

                <label for="booldPressure">Boold Pressure *</label>
                <input id="booldPressure" type="text" name="booldPressure" value="" />
                </p>
                
                <p>
                <label for="energyExpenditure">Energy Expenditure *</label>
                <input id="energyExpenditure" type="text" name="energyExpenditure" value="" />
                </p>
                
                <p>

                <label for="missedMedication">Missed Medication *</label>
                <input id="missedMedication" type="text" name="missedMedication" value="" />
                </p>
                
                <p>
                <label for="saltIntake">Salt Intake *</label>
                <input id="saltIntake" type="text" name="saltIntake" value="" />
                </p>
                                
                <p>
                <button id="addExpense" type="button">Submit</button>
                </p>
                
            </form>
	</div><!-- /content -->

	<div data-role="footer" style="bottom:0; position:absolute !important; top: auto !important; width:100%;">
		<div data-role="navbar">
   		 <ul>
	        <li><a href="#" class="ui-btn-active">Add Profile</a></li>
	        <li><a href="#showExpensesPage">Show Profile</a></li>
    	</ul>
</div><!-- /navbar -->
	</div><!-- /footer -->
</div><!-- /page -->


</body>
</html>