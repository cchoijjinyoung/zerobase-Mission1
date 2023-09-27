<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.misson1.dto.History" %>
<%@ page import="com.zerobase.misson1.service.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<script>
    function confirmDelete(button) {
        var userSelect = confirm("삭제 하시겠습니까?");
        if (userSelect) {
            button.form.submit();
        }
    }
</script>
<body>
<%
    HistoryService historyService = new HistoryService();
    List<History> list = historyService.list();
%>
<h1>위치 히스토리 목록</h1>
<div id="link-container">
    <a href="/">홈</a>
    <span>|</span>
    <a href="history.jsp">위치 히스토리 목록</a>
    <span>|</span>
    <a href="load-wifi.jsp">Open API 정보 가져오기</a>
    <span>|</span>
    <a href="bookmark-list.jsp">북마크 보기</a>
    <span>|</span>
    <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<div id="table-container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list.size() == 0) {
        %>
        <tr>
            <td style="text-align: center" colspan="5">위치 히스토리 목록이 존재하지 않습니다.</td>
        </tr>
        <%
        } else {
            for (History history : list) {
        %>
        <tr>
            <form id="deleteForm" method="post" action="/history-delete-submit.jsp">
                <input type="hidden" name="id" value="<%=history.getId()%>"/>
                <td><%=history.getId()%>
                </td>
                <td><%=history.getLAT()%>
                </td>
                <td><%=history.getLNT()%>
                </td>
                <td><%=history.getWORK_DTTM()%>
                </td>
                <td style="text-align: center">
                    <button type="button" onclick="confirmDelete(this)">삭제</button>
                </td>
            </form>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>