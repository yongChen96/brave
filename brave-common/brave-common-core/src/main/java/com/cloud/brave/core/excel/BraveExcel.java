package com.cloud.brave.core.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author yongchen
 * @description: Excel导入导出
 * @date 2021/9/1 16:31
 */
@Component
public class BraveExcel {

    /**
     * @param list           导出数据
     * @param title          导出标题
     * @param sheetName      sheet名称
     * @param clazz          导出实体
     * @param fileName       文件名称
     * @param isCreateHeader 是否创建表头
     * @param response       返回
     * @description: 导出excel
     * @return: void
     * @author yongchen
     * @date: 2021/9/1 16:47
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> clazz, String fileName, Boolean isCreateHeader, HttpServletResponse response) {
        ExportParams params = new ExportParams(title, sheetName);
        params.setCreateHeadRows(isCreateHeader);
        params.setStyle(BraveExcelStyle.class);
        defaultExport(list, clazz, fileName, response, params);
    }

    /**
     * @param list     导出数据
     * @param fileName 文件名称
     * @param response 返回
     * @description: Map导出
     * @return: void
     * @author yongchen
     * @date: 2021/9/1 17:06
     */
    public static void exportExcelMap(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }


    /**
     * @param list     导出数据
     * @param clazz    导出实体
     * @param fileName 文件名称
     * @param response 返回
     * @param params   excel封装实体
     * @description: 默认导出方法
     * @return: void
     * @author yongchen
     * @date: 2021/9/1 16:46
     */
    private static void defaultExport(List<?> list, Class<?> clazz, String fileName, HttpServletResponse response, ExportParams params) {
        Workbook workbook = ExcelExportUtil.exportExcel(params, clazz, list);
        if (workbook == null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * @param fileName 文件名称
     * @param response 返回
     * @param workbook excel对象
     * @description: Excel导出
     * @return: void
     * @author yongchen
     * @date: 2021/9/1 16:45
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: 根据文件路径来导入Excel
     * @param filePath 文件路径
     * @param titleRows 表标题行数
     * @param headerRows 表头行数
     * @param clazz excel实体
     * @return: java.util.List<T> 
     * @author yongchen
     * @date: 2021/9/1 17:14
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> clazz) {
        //判断文件是否存在
        if (null == filePath && "".equals(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), clazz, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @description: 上传导入excel
     * @param file 上传文件
     * @param titleRows 表标题行数
     * @param headerRows 表头行数
     * @param clazz excel行数
     * @return: java.util.List<T> 
     * @author yongchen
     * @date: 2021/9/1 17:19
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> clazz) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
}
