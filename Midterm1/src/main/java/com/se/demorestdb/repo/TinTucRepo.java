package com.se.demorestdb.repo;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.model.DanhMuc;
import com.se.demorestdb.model.TinTuc;

import java.util.List;

public interface TinTucRepo {
    List<TinTuc> getAllTinTuc();
    TinTuc getTinTucById(int id);
    TinTuc addTinTuc(TinTuc tinTuc);
    TinTuc updateTinTuc(int id, TinTuc tinTuc);
    void deleteTinTuc(int id);
    List<Cau1> getTinTucWithTenDM();
}
