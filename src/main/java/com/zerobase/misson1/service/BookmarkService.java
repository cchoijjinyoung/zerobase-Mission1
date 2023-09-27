package com.zerobase.misson1.service;

import com.zerobase.misson1.dto.Bookmark;
import com.zerobase.misson1.dto.BookmarkGroup;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {

    public List<Bookmark> list() {
        List<Bookmark> bookmarks = new ArrayList<>();
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " SELECT * FROM bookmark ";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getInt("BOOKMARK_ID"));
                bookmark.setBookmarkGroupId(rs.getInt("BOOKMARK_GROUP_ID"));
                bookmark.setXSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
                bookmark.setCreateDate(rs.getString("CREATE_DT"));
                bookmarks.add(bookmark);
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
        return bookmarks;
    }

    public Bookmark findById(int id) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Bookmark bookmark = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = "SELECT * from bookmark WHERE BOOKMARK_ID = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookmark = new Bookmark();
                bookmark.setId(rs.getInt("BOOKMARK_ID"));
                bookmark.setBookmarkGroupId(rs.getInt("BOOKMARK_GROUP_ID"));
                bookmark.setXSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
                bookmark.setCreateDate(rs.getString("CREATE_DT"));
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
        return bookmark;
    }

    public void insert(int bookmarkGroupId, String wifiMgrNo) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " INSERT INTO bookmark (BOOKMARK_GROUP_ID, X_SWIFI_MGR_NO, CREATE_DT) VALUES (?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, bookmarkGroupId);
            pstmt.setString(2, wifiMgrNo);
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

    public void delete(int id) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " DELETE FROM bookmark where BOOKMARK_ID = ? ";
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

    public void deleteByBookmarkGroupId(int bookmarkGroupId) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " DELETE FROM bookmark where BOOKMARK_GROUP_ID = ? ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, bookmarkGroupId);
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

    public int countUseBookmarkGroups(int bookmarkGroupId) {
        int count = 0;
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " select count() " +
                    " from bookmark b " +
                    " where b.BOOKMARK_GROUP_ID = ? ";
            pstmt = conn.prepareStatement(sql);

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookmarkGroupId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");
            }
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
}
