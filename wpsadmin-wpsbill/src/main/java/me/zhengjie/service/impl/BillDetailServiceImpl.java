package me.zhengjie.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.config.FileProperties;
import me.zhengjie.domain.BillDetail;
import me.zhengjie.excel.KsyunBillExcel;
import me.zhengjie.repository.BillDetailRepository;
import me.zhengjie.service.BillDetailService;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import me.zhengjie.utils.ValidationUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Zheng Jie
 * @date 2018-12-26
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "billDetail")
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;
    private final FileProperties properties;

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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(BillDetail resources) {
        billDetailRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BillDetail resources) {
        BillDetail billDetail = billDetailRepository.findById(resources.getId()).orElseGet(BillDetail::new);
        ValidationUtil.isNull(billDetail.getId(),"BillDetail","id",resources.getId());
        billDetail.copy(resources);
        billDetailRepository.save(billDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            billDetailRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> uploadBill(MultipartFile multipartFile) throws IOException {
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath());
        // excel 文件内容处理
        List<BillDetail> data = new ArrayList<>();
        try {
            data = KsyunBillExcel.read(file);
        }catch (Exception exception) {
            String err = "erro";
        }

        return new HashMap<String,String>(){{put("filePath", "ss");}};
    }
}
