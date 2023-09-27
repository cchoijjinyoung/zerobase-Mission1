<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="com.zerobase.misson1.service.BookmarkService" %>
<%@ page import="com.zerobase.misson1.dto.Bookmark" %>
<%@ page import="com.zerobase.misson1.service.WifiService" %>
<%@ page import="com.zerobase.misson1.dto.RowResponse" %>
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
    BookmarkService bookmarkService = new BookmarkService();
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    WifiService wifiService = new WifiService();
    Bookmark bookmark = bookmarkService.findById(id);
    BookmarkGroup bookmarkGroup = bookmarkGroupService.findById(bookmark.getBookmarkGroupId());
    RowResponse wifi = wifiService.findByMgrNo(bookmark.getXSwifiMgrNo());
%>
<h1>북마크 삭제</h1>
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
    <span>북마크를 삭제하시겠습니까?</span>
</div>
<div id="table-container">
    <form action="bookmark-delete-submit.jsp" method="post">
        <table>
            <colgroup>
                <col style="width: 20%;"/>
                <col style="width: 80%;"/>
            </colgroup>
            <tbody>
            <tr>
                <th>북마크 이름</th>
                <td>
                    <input type="hidden" id="bookmarkId" name="bookmarkId" value="<%=bookmark.getId()%>">
                    <%=bookmarkGroup.getName()%>
                </td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td>
                    <%=wifi.getX_SWIFI_MAIN_NM()%>
                </td>
            </tr>
            <tr>
                <th>등록일자</th>
                <td>
                    <%=bookmark.getCreateDate()%>
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center">
            <a href="bookmark-list.jsp">돌아가기</a>
            <button>삭제</button>
        </div>
    </form>
</div>
</body>
</html>