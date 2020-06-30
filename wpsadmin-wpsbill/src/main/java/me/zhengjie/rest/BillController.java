package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.service.BillDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import me.zhengjie.annotation.AnonymousAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2018-12-31
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
}
