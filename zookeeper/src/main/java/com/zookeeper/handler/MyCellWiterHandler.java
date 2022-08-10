package com.zookeeper.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class MyCellWiterHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        Sheet sheet = writeSheetHolder.getCachedSheet();
        Workbook workbook = sheet.getWorkbook();
        sheet.setColumnWidth(cell.getColumnIndex(), 40*256);
        if(!aBoolean){
            if(cell.getStringCellValue().equals("a")){
                WriteCellStyle writeCellStyle=new WriteCellStyle();
                // 设置自定义的表头样式
                writeCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
                writeCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);


                // WriteCellStyle转换为CellStyle
                CellStyle headCellStyle = StyleUtil.buildContentCellStyle(workbook, writeCellStyle);
                cell.setCellStyle(headCellStyle);
            }
        }

    }
}
