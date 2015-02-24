<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
        <meta charset="utf-8" />
        <meta name="format-detection" content="telephone=no" />
        <!-- WARNING: for iOS 7, remove the width=device-width and height=device-height attributes. See https://issues.apache.org/jira/browse/CB-4323 -->
        <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />
        <link rel="stylesheet" type="text/css" href="/static/css/custom.min.css" />        
        <link rel="stylesheet" type="text/css" href="/static/css/jquery.mobile.icons.min.css" />        
        <link rel="stylesheet" type="text/css" href="/static/css/jquery.mobile.structure-1.4.0.min.css" />
        
        <title>Wallet</title>
        
         <script type="text/javascript" src="/static/js/jquery-2.1.0.min.js"></script>  
        <script type="text/javascript" src="/static/js/jquery.mobile-1.4.0.min.js"></script>            
        <script type="text/javascript" src="/static/js/index.js"></script>
        
        <script type="text/javascript">
        
        var resultData="";
        var resultTraverse="";
        var stored;

$(function(){			
				
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
	
	$("#decryptButton").click(function(){
		$.mobile.changePage('#inputPrivateKey');	
	});
	
	
	$("#submitPrivateKey").click(function(){
		
		$.ajax({
			url : "/verify-privatekey",
			type : "GET",
			data : "id=${userId}&privateKey="+$("#privateKey").val(),
			dataType : "json",
			success : function(data) {				
				if(data+""=="true"){
					$("#resultString").html(resultData);
					$("#treeTraverse").html(resultTraverse);
					
					$("#analysis").html("");
		               var counti=0;
						for(var i=0;i<stored.companiesNames.length;i++){
							$("#analysis").append('<div><strong>Comany :</strong>'+stored.companiesNames[i]+'<br/>'+
							'<strong>Traverse : </strong>'+stored.tra[i]+
							'<div><br/>'+
										'<strong>Result : </strong>'+stored.results[i]);
							if(counti < stored.count[i]){
								$("#bestResult").html('<div><strong>Comany :</strong>'+stored.companiesNames[i]+'<br/>'+
										'<strong>Traverse : </strong>'+stored.tra[i]+
										'<div><br/>'+
										'<strong>Result : </strong>'+stored.results[i]);
								counti=stored.count[i];
							}
						}
					
					$.mobile.changePage('#showExpensesReportPage');	
					
					
					
					$("#decryptButton").hide();
				}else{
					alert("Wrong private Key.!");
				}
			},
			error : function(data) {			
				alert("fail"+data.responseText);
				
			},
			complete: function() {          
	            

	        } 
		});		
	});
	
$("#addExpense").click(function(){
	
		$.ajax({
			url : "/medical/addMedicalProfile",
			type : "GET",
			data : "booldPressure="+$("#booldPressure").val()+"&energyExpenditure="+$("#energyExpenditure").val()+"&missedMedication="+$("#missedMedication").val()+"&id=${userId}&saltIntake="+$("#saltIntake").val()+
					"&companyId="+$("#companyId").val()+"&bloodGroup="+$("#bloodGroup").val()+"&suger="+$("#suger").val()+"&heartBeats="+$("#heartBeats").val()+"&normalDiet="+$("#normalDiet").val()+"&physicalActivity="+$("#physicalActivity").val()+"&privateKey="+$("#privateKey").val(),
			dataType : "json",
			success : function(data) {		
				
				if(data.failure == undefined || data.failure+""==""){
				
					alert("added");	
					stored=data;
					$("#expenseList").empty();
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Blood Pressure</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.bloodPressure+'</li>'+
						     ' <li>Threshold : '+data.threshold.bloodPressure+'</li>'+						     
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Energy Expenditure</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.energyExpenditure+'</li>'+
						     ' <li>Threshold : '+data.threshold.energyExpenditure+'</li>'+
						     
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Missed Medication</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.missedMedication+'</li>'+
						     ' <li>Threshold : '+data.threshold.missedMedication+'</li>'+
						     
						   ' </ul>'+
						  '</li>');
					$("#expenseList").append('<li data-role="collapsible" data-iconpos="right" data-shadow="false" data-corners="false" class="cola">'+
						    '<h2>Salt Intake</h2>'+
						    '<ul data-role="listview" data-shadow="false" data-inset="true" data-corners="false">'+
						      '<li>User data : '+data.profile.saltIntake+'</li>'+
						     ' <li>Threshold : '+data.threshold.saltIntake+'</li>'+						     
						   ' </ul>'+
						  '</li>');
					
					$("#resultString").html(data.enc);
					$("#treeTraverse").html(data.encTra);	
					
					resultData=data.result+"";
					resultTraverse=data.traversePath+"";
					$("#decryptButton").show();
					$("#analysis").html("");
	               var counti=0;
					for(var i=0;i<data.companiesNames.length;i++){
						$("#analysis").append('<div><strong>Comany :</strong>'+data.companiesNames[i]+'<br/>'+
						'<strong>Traverse : </strong>'+data.resultEncTra[i]+
						'<div><br/>'+
						'<strong>Result : </strong>'+data.resultsEnc[i]);
						if(counti < data.count[i]){
							$("#bestResult").html('<div><strong>Comany :</strong>'+data.companiesNames[i]+'<br/>'+
									'<strong>Traverse : </strong>'+data.resultEncTra[i]+
									'<div><br/>'+
									'<strong>Result : </strong>'+data.resultsEnc[i]);
							counti=data.count[i];
						}
					}
					
					window.localStorage.setItem("medicalProfileId",data.profile.id);
					
					$.mobile.changePage('#showExpensesReportPage');	
					
				}else{
					alet("Wrong private Key.!");
				}
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
	});	
});


function logout(){	
	location.href="/mobile";
}

</script>
    </head>
<body style="font-size: x-large;">

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
                <label for="bloodGroup">Blood Group *</label>
                <input id="bloodGroup" type="text" name="bloodGroup" value="" />
                </p>
                
                <p>
                <label for="suger">Suger *</label>
                <input id="suger" type="text" name="suger" value="" />
                </p>
                
                <p>
                <label for="heartBeats">Heart Beats *</label>
                <input id="heartBeats" type="text" name="heartBeats" value="" />
                </p>
                
                <p>
                <label for="physicalActivity">Physical Activity *</label>
                <input id="physicalActivity" type="text" name="physicalActivity" value="" placeholder="true/false"/>
                </p>
                
                <p>
                <label for="normalDiet">Normal Diet *</label>
                <input id="normalDiet" type="text" name="normalDiet" value="" placeholder="true/false"/>
                </p>
                
                <p>
                <label for="companyId">Company *</label>
                <select id="companyId" name="companyId">
                </select>
                <input type="hidden" name="id" id="idHidden" value="">
		<input type="hidden" name="id" id="auth" value="">
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
	<br/>
 	

	<div>
		<div><b>Result : </b></div>
		<p id="resultString"></p>
	</div>
	
	<div>
		<div><b>Tree Traverse : </b></div>
		<p id="treeTraverse"></p>
	</div>
	
	<div>
		<div><b>Analysis : </b></div>
		<p id="analysis"></p>
	</div>
	<div>
		<div><b>Best Result : </b></div>
		<p id="bestResult"></p>
	</div>
	<br/>
	<input type="button" id="decryptButton" value="Decrypt">
	<br/>
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


<div data-role="page" id="inputPrivateKey" data-theme="b">
	<div data-role="header">
		<h1>Your Private Key</h1>
		    <a href="javascript:logout();" data-icon="gear" class="ui-btn-right">Logout</a>
		
	</div><!-- /header -->

	<div role="main" class="ui-content">
		<label for="privateKey">Private key :</label>
		<input type="text" id="privateKey" name="privateKey"/>
		<input type="button" id="submitPrivateKey" value="Submit">
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


<script type="text/javascript">

$(function(){
$.ajax({
	url : "/list-companies.json",
	type : "GET",				
	dataType : "json",
	success : function(data) {		
		$.each(data,function(index,val){			
			$("#companyId").append('<option value="'+index+'">'+val+'</option>');
		});
		
		
	}
});

});

</script>

</body>
</html>