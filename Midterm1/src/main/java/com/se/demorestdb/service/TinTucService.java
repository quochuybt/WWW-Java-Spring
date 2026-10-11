package com.se.demorestdb.service;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.model.TinTuc;
import com.se.demorestdb.repo.TinTucRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TinTucService {

    @Inject
    private TinTucRepo tinTucRepo;

    public List<Cau1> getTinTucWithTenDanhMuc() {
        return tinTucRepo.getTinTucWithTenDM();
    }

    public List<TinTuc> getAllTinTuc() {
        return tinTucRepo.getAllTinTuc();
    }

    public TinTuc getTTById(int id) {
        return tinTucRepo.getTinTucById(id);
    }

    public TinTuc addTT(TinTuc tinTuc) {
        return tinTucRepo.addTinTuc(tinTuc);
    }

    public TinTuc updateTT(int id, TinTuc tinTuc) {
        return tinTucRepo.updateTinTuc(id,tinTuc);
    }

    public void deleteTT(int id) {
        tinTucRepo.deleteTinTuc(id);
    }
}
