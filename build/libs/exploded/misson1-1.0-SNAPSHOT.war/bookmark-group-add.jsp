<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>북마크 그룹 추가</h1>
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
    <form action="bookmark-group-add-submit.jsp" method="post">
        <table>
            <colgroup>
                <col style="width: 20%;"/>
                <col style="width: 80%;"/>
            </colgroup>
            <tbody>
            <tr>
                <th>북마크 그룹 이름</th>
                <td>
                    <input type="text" id="name" name="name">
                </td>
            </tr>
            <tr>
                <th>순서</th>
                <td>
                    <input type="text" id="sequence" name="sequence">
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center">
            <button type="submit">추가</button>
        </div>
    </form>
</div>
</body>
</html>