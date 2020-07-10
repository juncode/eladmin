package me.zhengjie.excel;

import com.alibaba.fastjson.JSON;
import me.zhengjie.domain.BillDetail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class KsyunBillExcel {
    private static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    private static boolean isNumberic(String str){
        return isInteger(str) || isDouble(str);
    }

    public static List<BillDetail> read(File file) throws IOException, ParseException {
        XSSFWorkbook hssfWorkBOok = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = hssfWorkBOok.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        List<BillDetail> list= new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("title", 6);
        map.put("type", 2);
        map.put("key", 5);
        map.put("fee", 7);

        LocalDate billMonth=LocalDate.now().withDayOfMonth(1);
        for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
            //3.获得要解析的行
            XSSFRow row = sheet.getRow(i);
            HashMap<String, Object> tmpDetail = new HashMap<>();

            for(HashMap.Entry<String, Integer> enter : map.entrySet()) {
                String mapKey = enter.getKey();
                Integer cellIndex = enter.getValue();
                Cell cell  = row.getCell(cellIndex);
                Object cellValue;
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        cellValue = (cell.getNumericCellValue());
                        break;
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    default:
                        cellValue = "";
                }

                tmpDetail.put(mapKey, cellValue);
            }
            // 金额非数字、为0，一律过滤
            if(tmpDetail.get("fee").equals("") || tmpDetail.get("fee").equals("0")){
                continue;
            }

            BillDetail detail = JSON.parseObject(JSON.toJSONString(tmpDetail), BillDetail.class);
            detail.setPlatform("金山云");
            detail.setMonth(billMonth);

            list.add(detail);
        }

        return list;
    }
}
