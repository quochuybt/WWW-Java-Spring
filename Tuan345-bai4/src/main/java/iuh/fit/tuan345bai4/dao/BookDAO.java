package iuh.fit.tuan345bai4.dao;

import iuh.fit.tuan345bai4.model.Book;
import iuh.fit.tuan345bai4.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "Select * from books";

        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                double price = rs.getDouble("PRICE");
                String image = rs.getString("IMGBOOK");

                Book b = new Book(id,title,author,price,image);
                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
