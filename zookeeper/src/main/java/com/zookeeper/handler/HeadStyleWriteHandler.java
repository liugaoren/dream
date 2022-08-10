package com.zookeeper.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class HeadStyleWriteHandler extends AbstractCellStyleStrategy{

    private Workbook workbook;


    @Override
    protected void initCellStyle(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer integer) {


        if(integer == 1){
            WriteCellStyle writeCellStyle=new WriteCellStyle();
            // 设置自定义的表头样式
            writeCellStyle.setHorizontalAlignment(HorizontalAlignment.RIGHT);


            // WriteCellStyle转换为CellStyle
            CellStyle headCellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);

            cell.setCellStyle(headCellStyle);
        }
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer integer) {


    }
}
