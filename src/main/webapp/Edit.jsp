<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="Dto.Items"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Items item=(Items)request.getAttribute("item"); %>
<form action="update" method="post" enctype="multipart/form-data">
	<input type="number" name="id" value="<%=item.getItem_id()%>" hidden=""><br>
    Name:<input type="text" name="name" value="<%=item.getItem_name()%>"><br>
    Price:<input type="number" name="price" value="<%=item.getItem_price()%>"><br>
    Food Type:<%if(item.getFood_type().equals("veg")){ %>
	<input type="radio" name="type" value="veg" checked="checked" >Veg
	<input type="radio" name="type" value="non-veg">Non-Veg
	<%}else{ %>
	<input type="radio" name="type" value="veg" >Veg
	<input type="radio" name="type" value="non-veg" checked="checked" >Non-Veg
	<%} %>
	<br>
    Quantity:<input type="number" name="quantity" value="<%=item.getQuantity()%>"><br>
    Picture:<%
						String base64 = Base64.encodeBase64String(item.getPicture());
						%> <img height="100px" width="100px" alt="unknown"
						src="data:image/jpeg;base64,<%=base64%>">
    
    <input type="file" name="picture"><br>
    <button type="submit" value="submit">Upload</button>
    <button type="reset">Cancel</button>
</form>
</body>
</html>