package com.zookeeper.controller;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import com.zookeeper.Content;
import com.zookeeper.handler.HeadStyleWriteHandler;
import com.zookeeper.handler.MyCellWiterHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lgr
 * @date 2022-01-05 14:05:00
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("get")
    public Content test(@RequestBody Content content1){
        System.out.println(content1);
        Content content = new Content();
        content.setB("b");
        content.setC("c");
        content.setD("d");
        content.setE("e");

        return content;
    }

    /**
     * 导出excel
     */
    @GetMapping("export")
    public void export() throws Exception {
        //获取httpResponse对象
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=easyexcel.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 将数据写出去
        HeadStyleWriteHandler headStyleWriteHandler = new HeadStyleWriteHandler();
        MyCellWiterHandler myCellWiterHandler = new MyCellWiterHandler();
        EasyExcel.write(response.getOutputStream(), Content.class)
                // 这里放入动态头
                .head(head())
                .registerWriteHandler(headStyleWriteHandler)
                .registerWriteHandler(myCellWiterHandler)
                .sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data());
    }
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(Lists.newArrayList("数据表","导出数据","字段1"));
        list.add(Lists.newArrayList("数据表","导出数据","字段2"));
        list.add(Lists.newArrayList("数据表","导出数据","字段3"));
        list.add(Lists.newArrayList("数据表","导出数据","字段4"));
        list.add(Lists.newArrayList("数据表","导出数据","字段5"));


        return list;
    }

    // 写出数据
    private List<Content> data() {
        List<Content> list = new ArrayList<Content>();
        Content content = new Content();
        content.setA("a");
        content.setB("b");
        content.setC("c");
        content.setD("d");
        content.setE("e");
        list.add(content);
        return list;
    }



}
