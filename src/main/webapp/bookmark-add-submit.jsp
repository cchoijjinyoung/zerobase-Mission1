<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<script>
    alert("북마크 정보를 추가하였습니다.");
    window.location.href = "bookmark-list.jsp";
</script>
<body>
<%
    request.setCharacterEncoding("utf-8");
    int name = Integer.parseInt(request.getParameter("bookmarkGroupId"));
    String sequence = request.getParameter("wifiMgrNo");
    BookmarkService bookmarkService = new BookmarkService();
    bookmarkService.insert(name, sequence);
%>
</body>
</html>
