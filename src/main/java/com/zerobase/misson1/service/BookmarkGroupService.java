package com.zerobase.misson1.service;

import com.zerobase.misson1.dto.BookmarkGroup;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {

    public List<BookmarkGroup> list() {
        List<BookmarkGroup> bookmarkGroups = new ArrayList<>();
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " SELECT * FROM bookmark_group ORDER BY BOOKMARK_GROUP_SQ ";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getInt("BOOKMARK_GROUP_ID"));
                bookmarkGroup.setName(rs.getString("BOOKMARK_GROUP_NM"));
                bookmarkGroup.setSequence(rs.getInt("BOOKMARK_GROUP_SQ"));
                bookmarkGroup.setCreateDate(rs.getString("CREATE_DT"));
                bookmarkGroup.setModifyDate(rs.getString("MODIFY_DT"));
                bookmarkGroups.add(bookmarkGroup);
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
        return bookmarkGroups;
    }

    public void insert(String name, int sequence) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " INSERT INTO bookmark_group (BOOKMARK_GROUP_NM, BOOKMARK_GROUP_SQ, CREATE_DT, MODIFY_DT) VALUES (?, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, sequence);
            pstmt.setString(3, LocalDateTime.now().withNano(0).toString());
            pstmt.setString(4, "");
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

    public void edit(int id, String name, int sequence) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " UPDATE bookmark_group SET BOOKMARK_GROUP_NM = ?, BOOKMARK_GROUP_SQ = ?, MODIFY_DT = ? WHERE BOOKMARK_GROUP_ID = ? ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, sequence);
            pstmt.setString(3, LocalDateTime.now().withNano(0).toString());
            pstmt.setInt(4, id);
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

    public BookmarkGroup findById(int id) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        BookmarkGroup bookmarkGroup = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = "SELECT * from bookmark_group WHERE BOOKMARK_GROUP_ID = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getInt("BOOKMARK_GROUP_ID"));
                bookmarkGroup.setName(rs.getString("BOOKMARK_GROUP_NM"));
                bookmarkGroup.setSequence(rs.getInt("BOOKMARK_GROUP_SQ"));
                bookmarkGroup.setCreateDate(rs.getString("CREATE_DT"));
                bookmarkGroup.setModifyDate(rs.getString("MODIFY_DT"));
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
        return bookmarkGroup;
    }

    public void delete(int id) {
        String dbFile = "C:/work/tool/sqlite/wifimisson.db";
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            String sql = " DELETE FROM bookmark_group where BOOKMARK_GROUP_ID = ? ";
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
