<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    int id = Integer.parseInt(request.getParameter("id"));
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    BookmarkGroup bookmarkGroup = bookmarkGroupService.findById(id);
%>
<h1>북마크 그룹 삭제</h1>
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
<div>
    <span>북마크 그룹을 삭제하시겠습니까?</span>
    <span>(<span style="color: red">주의!</span>: 해당 그룹의 모든 북마크가 삭제됩니다.)</span>
</div>
<div id="table-container">
    <form action="bookmark-group-delete-submit.jsp" method="post">
        <table>
            <colgroup>
                <col style="width: 20%;"/>
                <col style="width: 80%;"/>
            </colgroup>
            <tbody>
            <tr>
                <th>북마크 이름</th>
                <td>
                    <input type="hidden" id="bookmarkGroupId" name="bookmarkGroupId" value="<%=bookmarkGroup.getId()%>">
                    <%=bookmarkGroup.getName()%>
                </td>
            </tr>
            <tr>
                <th>순서</th>
                <td>
                    <%=bookmarkGroup.getSequence()%>
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center">
            <a href="bookmark-group.jsp">돌아가기</a>
            <button>삭제</button>
        </div>
    </form>
</div>
</body>
</html>