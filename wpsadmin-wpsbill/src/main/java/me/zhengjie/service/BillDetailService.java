package me.zhengjie.service;

import me.zhengjie.domain.BillDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Set;

public interface BillDetailService {
    /**
     *  账单更新
     * @param billDetail 账单信息
     * @param old 旧账单
     * @return BillDetail
     * @throws Exception
     */
    BillDetail config(BillDetail billDetail, BillDetail old) throws Exception;


    /**
     * 查询配置
     * @return EmailConfig 邮件配置
     */
    BillDetail find();

    /**
     * 创建
     * @param billDetail /
     */
    void create(BillDetail billDetail);

    /**
     * 编辑
     * @param billDetail /
     */
    void update(BillDetail billDetail);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 上传账单
     * @param file 文件
     * @return /
     */
    Map<String, String> uploadBill(MultipartFile file);
}
