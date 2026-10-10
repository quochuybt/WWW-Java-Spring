package com.se.demorestdb.service;

import com.se.demorestdb.dto.Cau1;
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
}
