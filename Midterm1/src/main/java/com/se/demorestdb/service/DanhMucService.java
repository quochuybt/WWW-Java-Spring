package com.se.demorestdb.service;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.model.DanhMuc;
import com.se.demorestdb.repo.DanhMucRepo;
import com.se.demorestdb.repo.TinTucRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DanhMucService {

    @Inject
    private DanhMucRepo danhMucRepo;

    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepo.getAllDanhMuc();
    }

    public DanhMuc getDanhMucById(int id) {
        return danhMucRepo.getDanhMucById(id);
    }

    public DanhMuc addDanhMuc(DanhMuc danhMuc) {
        return danhMucRepo.addDanhMuc(danhMuc);
    }

    public DanhMuc updateDanhMuc(int id,DanhMuc danhMuc) {
        return danhMucRepo.updateDanhMuc(id,danhMuc);
    }

    public void deleteDanhMuc(int id) {
        danhMucRepo.deleteDanhMuc(id);
    }


}
