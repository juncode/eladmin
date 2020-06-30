package me.zhengjie.service;

import me.zhengjie.domain.BillDetail;

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
}
