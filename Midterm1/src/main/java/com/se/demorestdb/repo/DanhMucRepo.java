package com.se.demorestdb.repo;

import com.se.demorestdb.model.DanhMuc;

import java.util.List;

public interface DanhMucRepo {
    List<DanhMuc> getAllDanhMuc();
    DanhMuc getDanhMucById(int id);
    DanhMuc addDanhMuc(DanhMuc danhMuc);
    DanhMuc updateDanhMuc(int id, DanhMuc danhMuc);
    void deleteDanhMuc(int id);
}
