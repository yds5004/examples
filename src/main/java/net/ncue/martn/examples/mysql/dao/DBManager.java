package net.ncue.martn.examples.mysql.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

/**
 * Created by dsyoon on 17. 7. 16.
 */
public class DBManager {
    Connection conn = null;
    Statement stmt = null;

    public void connect(String url, String user, String password, String database) throws SQLException, ClassNotFoundException {
        // 1. Driver를 로딩한다.
        Class.forName("com.mysql.jdbc.Driver");

        // 2. Connection 얻어오기
        this.conn = DriverManager.getConnection(url+":3306/"+database, user, password);
        this.stmt = this.conn.createStatement();
    }

    public void disconnect() throws SQLException {
        this.stmt.close();
        this.conn.close();
    }

    public void select(String state) throws SQLException {
        System.out.print("[" + state + "] ");
        if (this.stmt.execute("SELECT id, product, idx1, category, price, originalprice, image FROM emart where id=257642")) {
            ResultSet rs = stmt.getResultSet();
            StringBuffer sb = new StringBuffer();
            while (rs.next()) {
                int id = rs.getInt("id");
                String product = rs.getString("product");
                String idx1 = rs.getString("idx1");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                int originalprice = rs.getInt("originalprice");
                String image = rs.getString("image");

                sb.setLength(0);
                sb.append(id).append("\t").append(product).append("\t").append(idx1).append("\t").append(category).append("\t").append(price).append("\t").append(originalprice).append("\t").append(image);
                System.out.print(sb.toString());
            }
        }
        System.out.println();
    }

    public void insert(String sql) throws SQLException {
        this.stmt.executeUpdate(sql);
    }

    public void update(String sql) throws SQLException {
        this.stmt.executeUpdate(sql);
    }

    public void delete(String sql) throws SQLException {
        this.stmt.executeUpdate(sql);
    }

    public static void main(String[] arg) {
        try {
            DBManager dBManager = new DBManager();
            dBManager.connect("jdbc:mysql://my.mysql.test.com" ,"user_name" ,"password", "database");

            String sql = "";
            dBManager.select("initial state");
            sql= "insert into emart(id, product, idx1, category, price, originalprice, image) values(257642, '코카콜라 1.5L', '코카콜라, 1.5L', 'drink', 1500, 1800, 'coke.jsp')";
            dBManager.insert(sql);
            dBManager.select("after insert");
            sql= "update emart set price=1400 where id=257642";
            dBManager.update(sql);
            dBManager.select("after update");
            sql= "delete from emart where id=257642";
            dBManager.delete(sql);
            dBManager.select("after delete");

            dBManager.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
