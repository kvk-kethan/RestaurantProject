<%@page import="org.apache.commons.codec.binary.Base64"%>

<%@page import="java.util.List"%>
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
<%
	List<Items> items = (List<Items>) request.getAttribute("list");
%>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Picture</th>
			<th>Type</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Items item:items) {%>
		<tr>
			<th><%=item.getItem_id()%></th>
			<th><%=item.getItem_name()%></th>
			<th><%
						String base64 = Base64.encodeBase64String(item.getPicture());
						%> <img height="150px" width="150px" alt="unknown"
						src="data:image/jpeg;base64,<%=base64%>"></th>
			<th><%=item.getFood_type()%></th>
			<th><%=item.getItem_price()%></th>
			<th><%=item.getQuantity()%></th>
			<th><a href="edit?id=<%=item.getItem_id()%>"><button>Edit</button></a></th>
			<th><a href="delete?id=<%=item.getItem_id()%>"><button>Delete</button></a></th>
		</tr>
		<%} %>
	</table>
</body>
</html>