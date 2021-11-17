package com.cloud.brave.core.excel;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * @author yongchen
 * @description: 自定义表格样式
 * @date 2021/9/1 17:20
 */
public class BraveExcelStyle extends AbstractExcelExportStyler implements IExcelExportStyler {
    // 数据行类型
    private static final String ROW_STYLES = "row";
    // 标题类型
    private static final String TITLE_STYLES = "title";
    // 大标题样式
    private CellStyle headerStyle;
    // 标题样式
    private CellStyle titleStyle;
    // 数据行样式
    private CellStyle rowstyles;

    /**
     * @param workbook
     * @description:
     * @return:
     * @author yongchen
     * @date: 2021/9/1 17:35
     */
    public BraveExcelStyle(Workbook workbook) {
        this.init(workbook);
    }

    /**
     * @param workbook
     * @description: 表格样式初始化
     * @return: void
     * @author yongchen
     * @date: 2021/9/1 17:35
     */
    private void init(Workbook workbook) {
        this.headerStyle = initHeaderStyles(workbook);
        this.titleStyle = initTitleStyle(workbook);
        this.rowstyles = initRowStyles(workbook);
    }

    /**
     * @param workbook
     * @description: 表头样式初始化
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:32
     */
    private CellStyle initHeaderStyles(Workbook workbook) {
        return headerStyle;
    }

    /**
     * @param workbook
     * @description: 表标题样式初始化
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:33
     */
    private CellStyle initTitleStyle(Workbook workbook) {
        return buildCellStyle(workbook, TITLE_STYLES);
    }

    /**
     * @param workbook
     * @description: 行数据样式初始化
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:32
     */
    private CellStyle initRowStyles(Workbook workbook) {
        return buildCellStyle(workbook, ROW_STYLES);
    }

    /**
     * @param workbook excel表格
     * @param type     样式类型
     * @description: 设置单元格样式
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:31
     */
    private CellStyle buildCellStyle(Workbook workbook, String type) {
        CellStyle style = workbook.createCellStyle();
        // 字体样式
        Font font = workbook.createFont();
        if (TITLE_STYLES.equals(type)) {
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        }
        if (ROW_STYLES.equals(type)) {
            font.setFontHeightInPoints((short) 10);
        }
        font.setFontName("Courier New");
        style.setFont(font);
        // 设置底边框
        style.setBorderBottom(BorderStyle.THIN);
        // 设置左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置底边颜色
        style.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * @param i
     * @description: 表头样式
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:25
     */
    @Override
    public CellStyle getHeaderStyle(short i) {
        return headerStyle;
    }

    /**
     * @param i
     * @description: 表标题样式
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:25
     */
    @Override
    public CellStyle getTitleStyle(short i) {
        return titleStyle;
    }

    /**
     * @param b
     * @param excelExportEntity
     * @description: 获取样式方法
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:25
     */
    @Override
    public CellStyle getStyles(boolean b, ExcelExportEntity excelExportEntity) {
        return rowstyles;
    }

    /**
     * @param cell
     * @param i
     * @param excelExportEntity
     * @param o
     * @param o1
     * @description: 获取样式方法
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:26
     */
    @Override
    public CellStyle getStyles(Cell cell, int i, ExcelExportEntity excelExportEntity, Object o, Object o1) {
        return getStyles(true, excelExportEntity);
    }

    /**
     * @param b
     * @param excelForEachParams
     * @description: 获取模板样式
     * @return: org.apache.poi.ss.usermodel.CellStyle
     * @author yongchen
     * @date: 2021/9/1 17:26
     */
    @Override
    public CellStyle getTemplateStyles(boolean b, ExcelForEachParams excelForEachParams) {
        return null;
    }
}
