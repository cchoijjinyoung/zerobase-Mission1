<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<script>
    alert("북마크 그룹 정보를 삭제하였습니다.");
    window.location.href = "bookmark-group.jsp";
</script>
<body>
<%
    request.setCharacterEncoding("utf-8");
    int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    BookmarkService bookmarkService = new BookmarkService();
    bookmarkGroupService.delete(bookmarkGroupId);
    bookmarkService.deleteByBookmarkGroupId(bookmarkGroupId);
%>
</body>
</html>
