package com.yzm.jdbc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

@Component
public class JDBCDemo {

    @PostConstruct
    public void demo() {
        selectDemo();
//        insertDemo();
//        updateDemo();
//        deleteDemo();
//        callDemo();
    }

    private void selectDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "select * from shopee_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, rs);
        }
    }

    private void insertDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "insert into account(name,money) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "DDD");
            ps.setDouble(2, 4444.4);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    private void updateDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "update account set money = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 4000.0);
            ps.setInt(2, 4);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    private void deleteDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "delete from account where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    /**
     * CREATE PROCEDURE select_all()
     * BEGIN
     *  SELECT * FROM account;
     * END;
     */
    private void callDemo() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "call select_all()";
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, cs, rs);
        }
    }

}
