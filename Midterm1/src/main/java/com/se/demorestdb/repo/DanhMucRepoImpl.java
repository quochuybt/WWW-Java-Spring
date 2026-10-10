package com.se.demorestdb.repo;

import com.se.demorestdb.model.DanhMuc;
import jakarta.enterprise.context.ApplicationScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DanhMucRepoImpl implements DanhMucRepo{

    private volatile DataSource dataSource;
    public DataSource getDataSource() {
        if (dataSource==null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                dataSource = (DataSource) env.lookup("jdbc/quanlytintuc");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return dataSource;
    }


    @Override
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> danhMucs = new ArrayList<>();
        String sql = "Select * from danhmuc";

        try (Connection conn = getDataSource().getConnection();
             PreparedStatement pt = conn.prepareStatement(sql);
             ResultSet rs = pt.executeQuery()
        ) {
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMaDM(rs.getInt("MADM"));
                danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                danhMuc.setGhiChu(rs.getString("GHICHU"));
                danhMucs.add(danhMuc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return danhMucs;
    }

    @Override
    public DanhMuc getDanhMucById(int id) {
        DanhMuc danhMuc = null;
        String sql = "select * from danhmuc where madm = ?";
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement pt = conn.prepareStatement(sql);
        ) {
            pt.setInt(1,id);
            try (ResultSet rs = pt.executeQuery()) {
                if (rs.next()) {
                    danhMuc = new DanhMuc();
                    danhMuc.setMaDM(rs.getInt("MADM"));
                    danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                    danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                    danhMuc.setGhiChu(rs.getString("GHICHU"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return danhMuc;
    }

    @Override
    public DanhMuc addDanhMuc(DanhMuc danhMuc) {
        String sql = "insert into danhmuc (madm,tendanhmuc,nguoiquanly,ghichu) values (?,?,?,?)";
        try (Connection conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1,danhMuc.getMaDM());
            ps.setString(2,danhMuc.getTenDanhMuc());
            ps.setString(3,danhMuc.getNguoiQuanLy());
            ps.setString(4,danhMuc.getGhiChu());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return danhMuc;
    }

    @Override
    public DanhMuc updateDanhMuc(int id, DanhMuc danhMuc) {
        String sql = "update danhmuc set tendanhmuc = ?, nguoiquanly = ?, ghichu = ? where madm = ?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1,danhMuc.getTenDanhMuc());
            ps.setString(2,danhMuc.getNguoiQuanLy());
            ps.setString(3,danhMuc.getGhiChu());
            ps.setInt(4,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return danhMuc;
    }

    @Override
    public void deleteDanhMuc(int id) {
        String sql = "delete from danhmuc where madm= ?";
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
