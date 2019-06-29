package com.zzm.demo;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

public class test {


    public static void main(String[] args) {
//        BigDecimal v = new BigDecimal(0);
//        System.out.println("-----: "+v);

//        int[] arrs = {1,2,3,4,5};
//        int[] ints = twoSum(arrs, 6);
//        for (int c = 0;c<ints.length;c++) {
//            System.out.println("-----: "+ints[c]);
//        }

//        double amount = new BigDecimal((float)7200/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println("----: "+amount);
        // 生成EXCEL并指定输出路径
        try {
        OutputStream out = new FileOutputStream("withoutHead3.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET名称
        String sheetName = "测试SHEET";

        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);

        // 模拟分批查询：总记录数250条，每个SHEET存100条，每次查询20条  则生成3个SHEET，前俩个SHEET查询次数为5， 最后一个SHEET查询次数为3 最后一次写的记录数是10
        // 注：该版本为了较少数据判断的复杂度，暂时perSheetRowCount要能够整除pageSize， 不去做过多处理  合理分配查询数据量大小不会内存溢出即可。
        Integer totalRowCount = 25856000;
        Integer perSheetRowCount = 4000;
        Integer pageSize = 1000;
        Integer sheetCount = totalRowCount % perSheetRowCount == 0 ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);
        Integer previousSheetWriteCount = perSheetRowCount / pageSize;
        Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0 ?
                previousSheetWriteCount :
                (totalRowCount % perSheetRowCount % pageSize == 0 ? totalRowCount % perSheetRowCount / pageSize : (totalRowCount % perSheetRowCount / pageSize + 1));

        for (int i = 0; i < sheetCount; i++) {

            // 创建SHEET
            Sheet sheet = new Sheet(i, 0);
            sheet.setSheetName(sheetName + i);

            if (i < sheetCount - 1) {

                // 前2个SHEET, 每个SHEET查5次 每次查20条 每个SHEET写满100行  2个SHEET合计200行  实用环境：参数： currentPage: j+1 + previousSheetWriteCount*i, pageSize: pageSize
                for (int j = 0; j < previousSheetWriteCount; j++) {
                    List<List<String>> userList = new ArrayList<>();
                    for (int k = 0; k < 20; k++) {
                        userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));
                    }
                    writer.write0(userList, sheet, table);
                }

            } else if (i == sheetCount - 1) {

                // 最后一个SHEET 实用环境不需要将最后一次分开，合成一个即可， 参数为： currentPage = i+1;  pageSize = pageSize
                for (int j = 0; j < lastSheetWriteCount; j++) {

                    // 前俩次查询 每次查询20条
                    if (j < lastSheetWriteCount - 1) {

                        List<List<String>> userList = new ArrayList<>();
                        for (int k = 0; k < 20; k++) {
                            userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);

                    } else if (j == lastSheetWriteCount - 1) {

                        // 最后一次查询 将剩余的10条查询出来
                        List<List<String>> userList = new ArrayList<>();
                        Integer lastWriteRowCount = totalRowCount - (sheetCount - 1) * perSheetRowCount - (lastSheetWriteCount - 1) * pageSize;
                        for (int k = 0; k < lastWriteRowCount; k++) {
                            userList.add(Arrays.asList("ID_" + Math.random(), "小明1", String.valueOf(Math.random()), new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);

                    }
                }
            }
        }

        writer.finish();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
