package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.domain.BillDetail;
import me.zhengjie.service.BillDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import me.zhengjie.annotation.AnonymousAccess;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Huangjun
 * @date 2020年7月8日
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
@Api(tags = "账单：账单系统")
public class BillController {
    private final BillDetailService billDetailService;

    @AnonymousAccess
    @ApiOperation("测试代码，仅测试")
    @GetMapping("/ts")
    public ResponseEntity<Object> getCode(){
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img", "aaaa");
            put("uuid", "ssss");
        }};
        return ResponseEntity.ok(imgResult);
    }

    @AnonymousAccess
    @ApiOperation("测试代码，仅测试")
    @GetMapping("/list")
    public ResponseEntity<Object> getList(){
        //从db获取数据
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img", "aaaa");
            put("uuid", "ssss");
        }};
        return ResponseEntity.ok(imgResult);
    }

    @AnonymousAccess
    @ApiOperation("测试代码，仅测试")
    @GetMapping("/data")
    public ResponseEntity<Object> getData(){
        //从db获取数据
        return new ResponseEntity<>(billDetailService.find(), HttpStatus.OK);
    }

    @Log("新增账单")
    @AnonymousAccess
    @ApiOperation(value = "新增账单")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody BillDetail resources){
        billDetailService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改账单")
    @AnonymousAccess
    @ApiOperation(value = "修改账单")
    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody BillDetail resources){
        billDetailService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除账单")
    @AnonymousAccess
    @ApiOperation(value = "删除账单")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        billDetailService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("上传账单")
    @AnonymousAccess
    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadBill(@RequestParam MultipartFile bill) throws IOException {
        return new ResponseEntity<>(billDetailService.uploadBill(bill), HttpStatus.OK);
    }
}
