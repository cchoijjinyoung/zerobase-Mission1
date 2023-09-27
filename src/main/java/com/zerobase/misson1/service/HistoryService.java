package com.zerobase.misson1.service;

import com.zerobase.misson1.dto.History;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    public List<History> list() {
        List<History> histories = new ArrayList<>();
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " SELECT * FROM history ORDER BY HISTORY_ID DESC ";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("HISTORY_ID"));
                history.setLAT(rs.getString("HISTORY_LAT"));
                history.setLNT(rs.getString("HISTORY_LNT"));
                history.setWORK_DTTM(rs.getString("HISTORY_WORK_DTTM"));
                histories.add(history);
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
        return histories;
    }

    public void insert(String lat, String lnt) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " INSERT INTO history (HISTORY_LAT, HISTORY_LNT, HISTORY_WORK_DTTM) VALUES(?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, lat);
            pstmt.setString(2, lnt);
            pstmt.setString(3, LocalDateTime.now().withNano(0).toString());
            pstmt.execute();

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
    }

    public void delete(Integer id) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " DELETE FROM history where HISTORY_ID = ? ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.execute();

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
    }
}
