<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<script>
    // 삭제 컨펌
    function confirmDelete() {
        var userSelect = confirm("삭제 하시겠습니까?");
        if (userSelect) {
            document.getElementById("deleteForm").submit();
        }
    }

    // Add 폼으로 이동
    function moveGroupAddForm() {
        window.location.href = "bookmark-group-add.jsp";
    }

</script>
<body>
<%
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    List<BookmarkGroup> list = bookmarkGroupService.list();
%>
<h1>북마크 그룹</h1>
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
    <button onclick="moveGroupAddForm()">북마크 그룹 이름 추가</button>
</div>
<div id="table-container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크 그룹 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list.size() == 0) {
        %>
        <tr>
            <td style="text-align: center" colspan="6">정보가 존재하지 않습니다.</td>
        </tr>
        <%
        } else {
            for (BookmarkGroup bookmarkGroup : list) {
        %>
        <tr>
            <td><%=bookmarkGroup.getId()%>
            </td>
            <td><%=bookmarkGroup.getName()%>
            </td>
            <td><%=bookmarkGroup.getSequence()%>
            </td>
            <td><%=bookmarkGroup.getCreateDate()%>
            </td>
            <td><%=bookmarkGroup.getModifyDate()%>
            </td>
            <td style="text-align: center">
                <a href="bookmark-group-edit.jsp?id=<%=bookmarkGroup.getId()%>">수정</a>
                <a href="bookmark-group-delete.jsp?id=<%=bookmarkGroup.getId()%>">삭제</a>
            </td>
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