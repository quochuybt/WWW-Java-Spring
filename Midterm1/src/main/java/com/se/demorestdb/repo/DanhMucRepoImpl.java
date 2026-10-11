package com.se.demorestdb.repo;

import com.se.demorestdb.bean.BaseBean;
import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.dto.Cau3;
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
public class DanhMucRepoImpl extends BaseBean implements DanhMucRepo{

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

    @Override
    public List<Cau3> thongKeSLBaiVietBoiDM() {
        List<Cau3> cau3List = new ArrayList<>();
        String sql = """
                select d.madm, d.tendanhmuc, count(t.MATT) as soluong
                    from danhmuc d
                    left join tintuc t on t.MADM = d.MADM
                    group by madm, TENDANHMUC
                """;
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Cau3 cau3 = new Cau3();
                cau3.setMaDm(rs.getInt("madm"));
                cau3.setTenDm(rs.getString("tendanhmuc"));
                cau3.setSoluong(rs.getInt("soluong"));
                cau3List.add(cau3);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cau3List;
    }

    @Override
    public List<Cau3> lietKeDMco3baiViet() {
        List<Cau3> cau3List = new ArrayList<>();
        String sql = """
                select d.madm, d.tendanhmuc, count(t.MATT) as soluong
                from danhmuc d
                inner join tintuc t on t.MADM = d.MADM
                group by madm, TENDANHMUC
                having count(t.MATT) >=3
                """;
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cau3 cau3 = new Cau3();
                cau3.setMaDm(rs.getInt("madm"));
                cau3.setTenDm(rs.getString("tendanhmuc"));
                cau3.setSoluong(rs.getInt("soluong"));
                cau3List.add(cau3);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cau3List;
    }

    @Override
    public List<Cau3> dmcoSLTTnhieuNhat() {
        List<Cau3> cau3List = new ArrayList<>();
        String sql = """
                select d.madm, d.tendanhmuc, count(t.MATT) as soluong
                from danhmuc d
                inner join tintuc t on t.MADM = d.MADM
                group by madm, TENDANHMUC
                HAVING count(t.matt) = (
                    select MAX(soluong) 
                    from (
                        Select count(t2.matt) as soluong
                        from danhmuc d2
                        inner join tintuc t2 on t2.madm = d2.madm
                        group by d2.madm
                    ) as thongkemax
                );
                """;
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cau3 cau3 = new Cau3();
                cau3.setMaDm(rs.getInt("madm"));
                cau3.setTenDm(rs.getString("tendanhmuc"));
                cau3.setSoluong(rs.getInt("soluong"));
                cau3List.add(cau3);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cau3List;
    }

}
