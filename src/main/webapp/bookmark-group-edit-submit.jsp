<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<script>
    alert("북마크 그룹 정보를 수정하였습니다.");
    window.location.href = "bookmark-group.jsp";
</script>
<body>
<%
    request.setCharacterEncoding("utf-8");
    int id = Integer.parseInt(request.getParameter("bookmarkGroupId"));
    String name = request.getParameter("bookmarkGroupName");
    int sequence = Integer.parseInt(request.getParameter("bookmarkGroupSequence"));
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    bookmarkGroupService.edit(id, name, sequence);
%>
</body>
</html>
