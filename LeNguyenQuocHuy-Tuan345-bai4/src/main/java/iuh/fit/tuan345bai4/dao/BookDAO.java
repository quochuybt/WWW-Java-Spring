package iuh.fit.tuan345bai4.dao;

import iuh.fit.tuan345bai4.beans.Book;
import iuh.fit.tuan345bai4.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
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
                String tittle = rs.getString("TITTLE");
                String author = rs.getString("AUTHOR");
                double price = rs.getDouble("PRICE");
                String image = rs.getString("IMGBOOK");
                int quantity = rs.getInt("QUANTITY");
                Book b = new Book(id,tittle,author,price,image,quantity);
                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Book getBookById(long id) {
        String sql = "SELECT * FROM books WHERE ID=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Long bookid = rs.getLong("ID");
                    String tittle = rs.getString("TITTLE");
                    String author = rs.getString("AUTHOR");
                    double price = rs.getDouble("PRICE");
                    String image = rs.getString("IMGBOOK");
                    int quantity = rs.getInt("QUANTITY");

                    Book b = new Book(bookid,tittle,author,price,image,quantity);

                    return b;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
