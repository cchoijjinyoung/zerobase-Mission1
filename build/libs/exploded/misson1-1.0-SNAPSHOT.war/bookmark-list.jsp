<%@ page import="com.zerobase.misson1.service.BookmarkService" %>
<%@ page import="com.zerobase.misson1.dto.Bookmark" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.service.WifiService" %>
<%@ page import="com.zerobase.misson1.dto.RowResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    BookmarkService bookmarkService = new BookmarkService();
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    WifiService wifiService = new WifiService();
    List<Bookmark> list = bookmarkService.list();
%>
<h1>북마크 목록</h1>
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
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list.size() == 0) {
        %>
        <tr>
            <td style="text-align: center" colspan="5">북마크 정보가 존재하지 않습니다.</td>
        </tr>
        <%
        } else {
            for (Bookmark bookmark : list) {
                BookmarkGroup bookmarkGroup = bookmarkGroupService.findById(bookmark.getBookmarkGroupId());
                RowResponse wifi = wifiService.findByMgrNo(bookmark.getXSwifiMgrNo());
        %>
        <tr>
            <td><%=bookmark.getId()%>
            </td>
            <td><%=bookmarkGroup.getName()%>
            </td>
            <td><a href="/detail.jsp?mgrNo=<%=bookmark.getXSwifiMgrNo()%>"><%=wifi.getX_SWIFI_MAIN_NM()%></a>
            </td>
            <td><%=bookmark.getCreateDate()%>
            </td>
            <td style="text-align: center">
                <a href="bookmark-delete.jsp?id=<%=bookmark.getId()%>">삭제</a>
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
