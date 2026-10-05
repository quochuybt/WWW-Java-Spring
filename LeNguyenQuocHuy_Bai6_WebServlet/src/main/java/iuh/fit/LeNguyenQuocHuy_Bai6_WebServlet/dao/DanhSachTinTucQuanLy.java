package iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.dao;

import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.DanhMuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model.TinTuc;
import iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhSachTinTucQuanLy {

    private DBUtil dbUtil;

    public DanhSachTinTucQuanLy(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    // Lấy tất cả tin tức
    public List<TinTuc> getAllTinTuc() {

        List<TinTuc> list = new ArrayList<>();

        String sql = "SELECT * FROM TINTUC ORDER BY MATT";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                TinTuc tinTuc = new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                );

                list.add(tinTuc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // Lấy tin tức theo danh mục
    public List<TinTuc> getTinTucByDanhMuc(int maDM) {

        List<TinTuc> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM TINTUC
                WHERE MADM = ?
                ORDER BY MATT
                """;

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDM);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    TinTuc tinTuc = new TinTuc(
                            rs.getInt("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            rs.getInt("MADM")
                    );

                    list.add(tinTuc);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // Thêm tin tức
    public boolean addTinTuc(TinTuc tinTuc) {

        String sql = """
                INSERT INTO TINTUC
                (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, tinTuc.getMaTinTuc());
            ps.setString(2, tinTuc.getTieuDe());
            ps.setString(3, tinTuc.getNoiDungTinTuc());
            ps.setString(4, tinTuc.getLienKet());
            ps.setInt(5, tinTuc.getMaDM());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Xóa tin tức
    public boolean deleteTinTuc(int maTinTuc) {

        String sql = "DELETE FROM TINTUC WHERE MATT = ?";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTinTuc);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy danh sách danh mục
    public List<DanhMuc> getAllDanhMuc() {

        List<DanhMuc> list = new ArrayList<>();

        String sql = "SELECT * FROM DANHMUC ORDER BY MADM";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                DanhMuc danhMuc = new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getString("NGUOIQUANLY"),
                        rs.getString("GHICHU")
                );

                list.add(danhMuc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}