package com.se.demorestdb.repo;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.model.TinTuc;
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
public class TinTucRepoImpl implements TinTucRepo{

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
    public List<TinTuc> getAllTinTuc() {
        return List.of();
    }

    @Override
    public TinTuc getTinTucById(int id) {
        return null;
    }

    @Override
    public TinTuc addTinTuc(TinTuc tinTuc) {
        return null;
    }

    @Override
    public TinTuc updateTinTuc(int id, TinTuc tinTuc) {
        return null;
    }

    @Override
    public void deleteTinTuc(int id) {

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

//    Lấy danh sách tất cả tin tức kèm theo tên danh mục tương ứng

}
