<%@ page import="com.zerobase.misson1.service.HistoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<script>
    alert("삭제되었습니다.");
    window.location.href = "history.jsp";
</script>
<body>
<%
    Integer id = Integer.parseInt(request.getParameter("id"));
    HistoryService historyService = new HistoryService();
    historyService.delete(id);
%>
</body>
</html>
