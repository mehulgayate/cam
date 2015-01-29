<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IN ADMIN PANEL | Powered by INDEZINER</title>
<link rel="stylesheet" type="text/css" href="/static/css/stylea.css" />
<script type="text/javascript" src="/static/js/clockp.js"></script>
<script type="text/javascript" src="/static/js/clockh.js"></script> 
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/ddaccordion.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "submenuheader", //Shared CSS class name of headers group
	contentclass: "submenu", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>

<script type="text/javascript" src="/static/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
	
</script>

<script language="javascript" type="text/javascript" src="/static/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="/static/css/niceforms-default.css" />

</head>
<body>
<div id="main_container">

	<div class="header">
    <div class="logo"><a href="#"><img src="" alt="" title="" border="0" /></a></div>
    
    <div class="right_header">Welcome TA Admin, <a href="#">Visit site</a> | <a href="/ta/logout" class="logout">Logout</a></div>
    <div id="clock_a"></div>
    </div>
    
    <div class="main_content">
    
                    <div class="menu">
                    <ul>
                    <li><a class="current" href="/ta">Ta Home</a></li>                                       
                    </ul>
                    </div> 
                    
                    
                    
                    
    <div class="center_content">  
    <div class="right_content">            
    
    <div >    
    <h2 style="display: inline-block; float: left;">User Information</h2></a> 
    </div>                
    
    
    <form action="/admin/update-user">
    <table id="rounded-corner" summary="Blocked Users">
    
    <tbody>        
        <tr>   	 
        	<td>Name</td>
            <td>${user.name}</td>
            
        </tr>
        <tr>   	 
        	<td>Email</td>
            <td>${user.email}</td>
            
        </tr>
        <tr>   	 
        	<td>Private Key</td>
            <td>${user.privateKey}</td>
            
        </tr>
        <tr>   	 
        	<td>Blood Pressure</td>
            <td>${medicalProfile.bloodPressure}</td>
            
        </tr>
        <tr>   	 
        	<td>Missed Medications</td>
            <td>${medicalProfile.missedMedication}</td>
            
        </tr>
        <tr>   	 
        	<td>Energy Expenditure</td>
            <td>${medicalProfile.energyExpenditure}</td>
            
        </tr>
        <tr>   	 
        	<td>Salt Intake</td>
            <td>${medicalProfile.saltIntake}</td>
            
        </tr>  
        <tr>   	
        	<td>Suger</td>
            <td>${medicalProfile.suger}</td>         
        </tr>         
         <tr>   	 
        	<td>Heart Beats</td>
            <td>${medicalProfile.heartBeats}</td>
            
        </tr>
    </tbody>
</table>
</form>   	
          	
     </div><!-- end of right content-->
            
                    
  </div>   <!--end of center content -->               
                    
                    
    
    
    <div class="clear"></div>
    </div> <!--end of main content-->
	
    
    <div class="footer">
    
    	
    	
    
    </div>

</div>		
</body>
</html>