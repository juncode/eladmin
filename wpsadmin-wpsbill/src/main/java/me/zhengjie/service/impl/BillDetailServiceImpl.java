package me.zhengjie.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.domain.BillDetail;
import me.zhengjie.repository.BillDetailRepository;
import me.zhengjie.service.BillDetailService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Zheng Jie
 * @date 2018-12-26
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "billDetail")
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;

    @Override
    public BillDetail config(BillDetail billDetail, BillDetail old) throws Exception {
        return null;
    }

    @Override
    @Cacheable(key = "'id:1'")
    public BillDetail find() {
        Optional<BillDetail> billDetail = billDetailRepository.findById(1L);
        return billDetail.orElseGet(BillDetail::new);
    }
}
