<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body style="background-color:#f6f4ff; font-family: cursive;">
   <tiles:insertAttribute name="banner" />
   
   <table width="100%" height="50px" style="background-color:#afafff;">
		<tr>
			<td><tiles:insertAttribute name="search" /></td>
			<td><tiles:insertAttribute name="home" /></td>
			<td><tiles:insertAttribute name="grades" /></td>
			<td><tiles:insertAttribute name="subjects" /></td>
			<td><tiles:insertAttribute name="enrollment" /></td>
			<td><tiles:insertAttribute name="myFriends" /></td>
			<td><tiles:insertAttribute name="cgpa" /></td>
			
		</tr>
   </table>
   <br />
   <div style="width:200px; float:left;">
   		<br /><br />
   		<tiles:insertAttribute name="pic" />
   </div>
   <div style="margin-left:250px;">
   		<tiles:insertAttribute name="body" />
   </div>
   <div style="width:100%; float:left;">
   		<br /><br /><br />
   </div>

   <table width="100%" style="background-color:#dfdfff;">
		<colgroup>
			<col style="width:50%" />
			<col style="width:50%" />
		</colgroup>
   		<thead>
   			<tr style="background-color:#afafff; height:40px; color:#412066;">
   				<td align="center" valign="middle"><b>ANNOUNCEMENTS</b></td>
   				<td align="center" valign="middle"><b>NEWS</b></td>
   			</tr>
   		</thead>
   		<tbody>
			<tr>
				<td align="center"><div align="left" style="height:300px; width:99%; word-wrap:break-word; border:2px solid; border-color:#7066b6;"><tiles:insertAttribute name="footerAnnouncements" /></div></td>
				<td align="center"><div align="left" style="height:300px; width:99%; word-wrap:break-word; border:2px solid; border-color:#7066b6;"><tiles:insertAttribute name="footerNews" /></div></td>
			</tr>
   		</tbody>
	</table>
</body>
</html>
