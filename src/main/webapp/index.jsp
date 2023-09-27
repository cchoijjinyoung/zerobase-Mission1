<%@ page import="com.zerobase.misson1.service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zerobase.misson1.dto.RowResponse" %>
<%@ page import="com.zerobase.misson1.service.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<script>
    // 내 위치 가져오기
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(accessToGeo);
        }
    }

    function accessToGeo(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;

        if (lat && lng) {
            document.getElementById('lat').value = lat;
            document.getElementById('lnt').value = lng;
        }
    }
</script>
<body>
<%
    String lat = "";
    String lnt = "";
    if (request.getParameter("lat") == null && request.getParameter("lnt") == null) {
        lat = "0.0";
        lnt = "0.0";
    } else {
        lat = request.getParameter("lat");
        lnt = request.getParameter("lnt");
    }
    WifiService wifiService = new WifiService();
    List<RowResponse> rows = wifiService.findList(lat, lnt);
%>
<h1>와이파이 정보 구하기</h1>
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
<div id="get-container">
    <form method="get">
        LAT: <input type="text" id="lat" name="lat" value="<%=lat%>"/>
        LNT: <input type="text" id="lnt" name="lnt" value="<%=lnt%>"/>
        <button type="button" onclick="getLocation()">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 보기</button>
    </form>
</div>
<div id="table-container">
    <table>
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (("0.0".equals(lat) && "0.0".equals(lnt))
                || (lat == null && lnt == null)
                || (rows.size() == 0)) {
        %>
        <tr>
            <td style="text-align: center" colspan="17">위치 정보를 입력한 후 조회해주세요.</td>
        </tr>
        <%
        } else {
            HistoryService historyService = new HistoryService();
            historyService.insert(lat, lnt);

            for (RowResponse row : rows) {
        %>
        <tr>
            <td><%=row.getDistance()%>
            </td>
            <td><%=row.getX_SWIFI_MGR_NO()%>
            </td>
            <td><%=row.getX_SWIFI_WRDOFC()%>
            </td>
            <td>
                <a href="/detail.jsp?mgrNo=<%=row.getX_SWIFI_MGR_NO()%>"><%=row.getX_SWIFI_MAIN_NM()%></a>
            </td>
            <td><%=row.getX_SWIFI_ADRES1()%>
            </td>
            <td><%=row.getX_SWIFI_ADRES2()%>
            </td>
            <td><%=row.getX_SWIFI_INSTL_FLOOR()%>
            </td>
            <td><%=row.getX_SWIFI_INSTL_TY()%>
            </td>
            <td><%=row.getX_SWIFI_INSTL_MBY()%>
            </td>
            <td><%=row.getX_SWIFI_SVC_SE()%>
            </td>
            <td><%=row.getX_SWIFI_CMCWR()%>
            </td>
            <td><%=row.getX_SWIFI_CNSTC_YEAR()%>
            </td>
            <td><%=row.getX_SWIFI_INOUT_DOOR()%>
            </td>
            <td><%=row.getX_SWIFI_REMARS3()%>
            </td>
            <td><%=row.getLNT()%>
            </td>
            <td><%=row.getLAT()%>
            </td>
            <td><%=row.getWORK_DTTM()%>
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