<%@ page import="com.zerobase.misson1.service.WifiService" %>
<%@ page import="com.zerobase.misson1.dto.WifiInfo" %>
<%@ page import="com.zerobase.misson1.dto.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <div style="text-align: center">
        <%
            WifiService wifiService = new WifiService();
            List<WifiInfo.RowInfo> rowInfos = wifiService.loadAll();
            int totalCount = wifiService.insertAll(rowInfos);
        %>
        <h1>
            <%=totalCount%>개의 WIFI 정보를 정상적으로 저장하였습니다.
        </h1>
        <a href="/..">홈 으로 가기</a>
    </div>
</body>
</html>
