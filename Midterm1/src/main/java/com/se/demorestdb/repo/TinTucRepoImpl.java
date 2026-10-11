package com.se.demorestdb.repo;

import com.se.demorestdb.bean.BaseBean;
import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.dto.Cau2;
import com.se.demorestdb.model.TinTuc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

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
public class TinTucRepoImpl extends BaseBean implements TinTucRepo{

    @Override
    public List<TinTuc> getAllTinTuc() {
        List<TinTuc> tinTucs = new ArrayList<>();
        String sql = "Select * from tintuc";
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                TinTuc tinTuc = new TinTuc();
                tinTuc.setMaTT(rs.getInt("matt"));
                tinTuc.setTieuDe(rs.getString("tieude"));
                tinTuc.setNoiDungTT(rs.getString("noidungtt"));
                tinTuc.setLienKet(rs.getString("lienket"));
                tinTuc.setMaDM(rs.getInt("madm"));
                tinTucs.add(tinTuc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tinTucs;
    }

    @Override
    public TinTuc getTinTucById(int id) {
        TinTuc tinTuc = null;
        String sql = "Select * from tintuc where matt = ?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tinTuc = new TinTuc();
                    tinTuc.setMaTT(rs.getInt("matt"));
                    tinTuc.setTieuDe(rs.getString("tieude"));
                    tinTuc.setNoiDungTT(rs.getString("noidungtt"));
                    tinTuc.setLienKet(rs.getString("lienket"));
                    tinTuc.setMaDM(rs.getInt("madm"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tinTuc;
    }

    @Override
    public TinTuc addTinTuc(TinTuc tinTuc) {
        String sql = "insert into tintuc (matt,tieude,noidungtt,lienket,madm) values (?,?,?,?,?)";
        try (Connection conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1,tinTuc.getMaTT());
            ps.setString(2, tinTuc.getTieuDe());
            ps.setString(3, tinTuc.getNoiDungTT());
            ps.setString(4, tinTuc.getLienKet());
            ps.setInt(5, tinTuc.getMaDM());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tinTuc;
    }

    @Override
    public TinTuc updateTinTuc(int id, TinTuc tinTuc) {
        String sql = "update tintuc set tieude = ?, noidungtt=?,lienket=?,madm=? where matt=?";
        try (Connection conn = getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, tinTuc.getTieuDe());
            ps.setString(2, tinTuc.getNoiDungTT());
            ps.setString(3, tinTuc.getLienKet());
            ps.setInt(4, tinTuc.getMaDM());
            ps.setInt(5,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tinTuc;
    }

    @Override
    public void deleteTinTuc(int id) {
        String sql = "delete from tintuc where matt=?";
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
    public List<Cau1> getTinTucWithTenDM() {
        List<Cau1> cau1List = new ArrayList<>();
        String sql ="select matt,tieude,dm.TENDANHMUC from tintuc t left join danhmuc dm on t.madm = dm.madm";
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Cau1 cau1 = new Cau1();
                cau1.setMaTT(rs.getInt("matt"));
                cau1.setTieuDe(rs.getString("tieude"));
                cau1.setTenDM(rs.getString("tendanhmuc"));
                cau1List.add(cau1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cau1List;
    }

    @Override
    public List<Cau2> getTTByTenDM(String tendanhmuc) {
        List<Cau2> cau2List = new ArrayList<>();
        String sql = """
                SELECT matt, tieude, noidungtt, tendanhmuc
                from tintuc t
                inner join danhmuc d on t.MADM = d.MADM
                where d.TENDANHMUC = ?;
                """;
        try (Connection conn = getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1,tendanhmuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cau2 cau2 = new Cau2();
                    cau2.setMatt(rs.getInt("matt"));
                    cau2.setTieude(rs.getString("tieude"));
                    cau2.setNoidungtt(rs.getString("noidungtt"));
                    cau2.setTendm(rs.getString("tendanhmuc"));
                    cau2List.add(cau2);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cau2List;
    }


}
