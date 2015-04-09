<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% List<ImgToWeb> myList = (ArrayList<ItemObj>) request.getAttribute("list"); %> --%>



<c:forEach items="${imgRes}" var="imgRes">
    <c:out value="${imgRes } "/><br />
  </c:forEach>
</body>
</html>