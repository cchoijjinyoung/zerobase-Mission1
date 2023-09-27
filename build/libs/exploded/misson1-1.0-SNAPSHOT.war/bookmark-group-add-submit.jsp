<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<script>
    alert("북마크 그룹 정보를 추가하였습니다.");
    window.location.href = "bookmark-group.jsp";
</script>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    int sequence = Integer.parseInt(request.getParameter("sequence"));
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    bookmarkGroupService.insert(name, sequence);
%>
</body>
</html>
