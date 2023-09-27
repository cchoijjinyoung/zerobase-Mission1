<%@ page import="com.zerobase.misson1.service.WifiService" %>
<%@ page import="com.zerobase.misson1.dto.RowResponse" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.misson1.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.misson1.dto.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    String mgrNo = request.getParameter("mgrNo");
    WifiService wifiService = new WifiService();
    RowResponse row = wifiService.findByMgrNo(mgrNo);
%>
    <h1>와이파이 상세 정보</h1>
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
        <form action="bookmark-add-submit.jsp" method="post">
            <input type="hidden" name="wifiMgrNo" value="<%=row.getX_SWIFI_MGR_NO()%>">
            <select name="bookmarkGroupId">
                <option value="" disabled selected>북마크 그룹 이름 선택</option>
                <%
                    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
                    List<BookmarkGroup> bookmarkGroupList = bookmarkGroupService.list();
                    for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {
                %>
                <option value="<%=bookmarkGroup.getId()%>"><%=bookmarkGroup.getName()%></option>
                <%
                    }
                %>
            </select>
            <button>북마크 추가하기</button>
        </form>
    </div>
    <table>
        <colgroup>
            <col style="width: 20%;"/>
            <col style="width: 80%;"/>
        </colgroup>
        <tbody>
        <tr>
            <th>관리번호</th>
            <td><%=row.getX_SWIFI_MGR_NO()%></td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=row.getX_SWIFI_WRDOFC()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=row.getX_SWIFI_MAIN_NM()%></td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><%=row.getX_SWIFI_ADRES1()%></td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%=row.getX_SWIFI_ADRES2()%></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%=row.getX_SWIFI_INSTL_FLOOR()%></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%=row.getX_SWIFI_INSTL_TY()%></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%=row.getX_SWIFI_INSTL_MBY()%></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%=row.getX_SWIFI_SVC_SE()%></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%=row.getX_SWIFI_CMCWR()%></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%=row.getX_SWIFI_CNSTC_YEAR()%></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%=row.getX_SWIFI_INOUT_DOOR()%></td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td><%=row.getX_SWIFI_REMARS3()%></td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%=row.getLAT()%></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%=row.getLNT()%></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=row.getWORK_DTTM()%></td>
        </tr>
        </tbody>
    </table>
</body>
</html>
