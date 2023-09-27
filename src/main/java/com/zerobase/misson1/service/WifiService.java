package com.zerobase.misson1.service;

import com.google.gson.Gson;
import com.zerobase.misson1.dto.RowResponse;
import com.zerobase.misson1.dto.WifiInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.zerobase.misson1.dto.WifiInfo.*;

public class WifiService {
    public List<RowInfo> loadAll() {
        int start = 1;
        int end = 1000;
        int TOTAL_COUNT = 1000;
        List<RowInfo> rowInfos = new ArrayList<>();

        try {
            String key = "4551674c7463677937366470587a6f";

            for (int i = 0; i < TOTAL_COUNT / 1000 + 1; i++) {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));
                String url = urlBuilder.toString();

                OkHttpClient client = new OkHttpClient();

                // get() -> GET 요청
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String responseBody = body.string();
                        System.out.println(responseBody);
                        Gson gson = new Gson();
                        WifiInfo wifiInfo = gson.fromJson(responseBody, WifiInfo.class);
                        rowInfos.addAll(wifiInfo.getTbPublicWifiInfo().getRow());

                        TOTAL_COUNT = wifiInfo.getTbPublicWifiInfo().getList_total_count();
                        start += 1000;
                        end += 1000;
                    }
                } else {
                    System.out.println("Error Occurred");
                }
            }
            return rowInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertAll(List<RowInfo> rowInfos) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);

            String sql = " INSERT INTO wifi_info (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                    " X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, " +
                    " LAT, LNT, WORK_DTTM) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);

            for (RowInfo rowinfo : rowInfos) {
                pstmt.setString(1, rowinfo.getX_SWIFI_MGR_NO());
                pstmt.setString(2, rowinfo.getX_SWIFI_WRDOFC());
                pstmt.setString(3, rowinfo.getX_SWIFI_MAIN_NM());
                pstmt.setString(4, rowinfo.getX_SWIFI_ADRES1());
                pstmt.setString(5, rowinfo.getX_SWIFI_ADRES2());
                pstmt.setString(6, rowinfo.getX_SWIFI_INSTL_FLOOR());
                pstmt.setString(7, rowinfo.getX_SWIFI_INSTL_TY());
                pstmt.setString(8, rowinfo.getX_SWIFI_INSTL_MBY());
                pstmt.setString(9, rowinfo.getX_SWIFI_SVC_SE());
                pstmt.setString(10, rowinfo.getX_SWIFI_CMCWR());
                pstmt.setString(11, rowinfo.getX_SWIFI_CNSTC_YEAR());
                pstmt.setString(12, rowinfo.getX_SWIFI_INOUT_DOOR());
                pstmt.setString(13, rowinfo.getX_SWIFI_REMARS3());
                pstmt.setString(14, rowinfo.getLAT());
                pstmt.setString(15, rowinfo.getLNT());
                pstmt.setString(16, rowinfo.getWORK_DTTM());

                pstmt.addBatch();

                if (++count % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<RowResponse> findList(String lat, String lnt) {
        List<RowResponse> rows = new ArrayList<>();
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = "SELECT *, " +
                    "(6371 * acos( " +
                    "cos( radians(?) ) " +
                    "* cos( radians( LNT ) ) " +
                    "* cos( radians( LAT ) - radians(?) ) " +
                    "+ sin( radians(?) ) " +
                    "* sin( radians( LNT ) ) " +
                    ")) AS distance " +
                    "FROM wifi_info " +
                    "ORDER BY distance " +
                    "LIMIT 20;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, Double.parseDouble(lat));
            pstmt.setDouble(2, Double.parseDouble(lnt));
            pstmt.setDouble(3, Double.parseDouble(lat));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                RowResponse row = new RowResponse();
                row.setDistance(String.format("%.4f", rs.getDouble("distance")));
                row.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                row.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                row.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                row.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                row.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                row.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                row.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                row.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                row.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                row.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                row.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                row.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                row.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                row.setLAT(rs.getString("LAT"));
                row.setLNT(rs.getString("LNT"));
                row.setWORK_DTTM(rs.getString("WORK_DTTM"));
                rows.add(row);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }

    public RowResponse findByMgrNo(String mgrNo) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        RowResponse row = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = "SELECT * from wifi_info WHERE X_SWIFI_MGR_NO = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mgrNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                row = new RowResponse();
                row.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                row.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                row.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                row.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                row.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                row.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                row.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                row.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                row.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                row.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                row.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                row.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                row.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                row.setLAT(rs.getString("LNT"));
                row.setLNT(rs.getString("LAT"));
                row.setWORK_DTTM(rs.getString("WORK_DTTM"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }
}
