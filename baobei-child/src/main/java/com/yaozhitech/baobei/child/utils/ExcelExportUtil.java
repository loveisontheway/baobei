package com.yaozhitech.baobei.child.utils;

import com.yaozhitech.baobei.child.annotation.ExcelField;
import com.yaozhitech.baobei.child.annotation.ExcelSheet;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导出工具
 *
 */
public class ExcelExportUtil {
    private static Logger logger = Logger.getLogger(ExcelExportUtil.class);

    /**
     * 导出Excel对象
     *
     * @param sheetDataListArr  Excel数据
     * @return
     */
    public static Workbook exportWorkbook(String fileName, List<?>... sheetDataListArr){

        // data array valid
        if (sheetDataListArr==null || sheetDataListArr.length==0) {
            throw new RuntimeException(">>>>>>>>>>> excel error, data array can not be empty.");
        }

        // expanded-name -> jjl
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        // workbook (HSSFWorkbook=2003/xls、XSSFWorkbook=2007/xlsx) -> jjl
        Workbook workbook = suffix.equals("xls") ? new HSSFWorkbook() : new XSSFWorkbook();

        // sheet
        for (List<?> dataList: sheetDataListArr) {
            makeSheet(workbook, dataList);
        }

        return workbook;
    }

    private static void makeSheet(Workbook workbook, List<?> sheetDataList){
        // data
        if (sheetDataList==null || sheetDataList.size()==0) {
//            throw new RuntimeException(">>>>>>>>>>> excel error, data can not be empty.");
            workbook.createSheet();
        }else{
            // sheet
            Class<?> sheetClass = sheetDataList.get(0).getClass();
            ExcelSheet excelSheet = sheetClass.getAnnotation(ExcelSheet.class);

            String sheetName = sheetDataList.get(0).getClass().getSimpleName();
            int headColorIndex = -1;
            if (excelSheet != null) {
                if (excelSheet.name()!=null && excelSheet.name().trim().length()>0) {
                    sheetName = excelSheet.name().trim();
                }
                headColorIndex = excelSheet.headColor().getIndex();
            }

            Sheet existSheet = workbook.getSheet(sheetName);
            if (existSheet != null) {
                for (int i = 2; i <= 1000; i++) {
                    String newSheetName = sheetName.concat(String.valueOf(i));  // avoid sheetName repetition
                    existSheet = workbook.getSheet(newSheetName);
                    if (existSheet == null) {
                        sheetName = newSheetName;
                        break;
                    } else {
                        continue;
                    }
                }
            }

            Sheet sheet = workbook.createSheet(sheetName);

            // sheet field
            List<Field> fields = new ArrayList<Field>();
            if (sheetClass.getDeclaredFields()!=null && sheetClass.getDeclaredFields().length>0) {
                for (Field field: sheetClass.getDeclaredFields()) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    fields.add(field);
                }
            }

            if (fields==null || fields.size()==0) {
                throw new RuntimeException(">>>>>>>>>>> excel error, data field can not be empty.");
            }

            // sheet header row
            CellStyle[] fieldDataStyleArr = new CellStyle[fields.size()];
            int[] fieldWidthArr = new int[fields.size()];
            Row headRow = sheet.createRow(0);
            for (int i = 0; i < fields.size(); i++) {

                // field
                Field field = fields.get(i);
                ExcelField excelField = field.getAnnotation(ExcelField.class);

                String fieldName = field.getName();
                int fieldWidth = 0;
                HorizontalAlignment align = null;
                if (excelField != null) {
                    if (excelField.name()!=null && excelField.name().trim().length()>0) {
                        fieldName = excelField.name().trim();
                    }
                    fieldWidth = excelField.width();
                    align = excelField.align();
                }

                // field width
                fieldWidthArr[i] = fieldWidth;

                // head-style、field-data-style
                CellStyle fieldDataStyle = workbook.createCellStyle();
                if (align != null) {
                    fieldDataStyle.setAlignment(align);
                }
                fieldDataStyleArr[i] = fieldDataStyle;

                CellStyle headStyle = workbook.createCellStyle();
                headStyle.cloneStyleFrom(fieldDataStyle);
                if (headColorIndex > -1) {
                    headStyle.setFillForegroundColor((short) headColorIndex);
                    headStyle.setFillBackgroundColor((short) headColorIndex);
                    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    // set head cell center -> jjl
                    headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    headStyle.setAlignment(HorizontalAlignment.CENTER);
                    // set head cell border -> jjl
                    headStyle.setBorderBottom(BorderStyle.THIN);
                    headStyle.setBorderLeft(BorderStyle.THIN);
                    headStyle.setBorderRight(BorderStyle.THIN);
                    headStyle.setBorderTop(BorderStyle.THIN);
                    // set head font bold -> jjl
                    Font headFont = workbook.createFont();
                    headFont.setBold(true);
                    headStyle.setFont(headFont);
                }

                // head-field data
                Cell cellX = headRow.createCell(i, CellType.STRING);
                cellX.setCellStyle(headStyle);
                cellX.setCellValue(String.valueOf(fieldName));
            }

            // sheet data rows
            for (int dataIndex = 0; dataIndex < sheetDataList.size(); dataIndex++) {
                int rowIndex = dataIndex+1;
                Object rowData = sheetDataList.get(dataIndex);

                Row rowX = sheet.createRow(rowIndex);

                for (int i = 0; i < fields.size(); i++) {
                    Field field = fields.get(i);
                    try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(rowData);

                        String fieldValueString = FieldReflectionUtil.formatValue(field, fieldValue);

                        Cell cellX = rowX.createCell(i, CellType.STRING);
                        // set data cell border -> jjl
                        fieldDataStyleArr[i].setBorderBottom(BorderStyle.THIN);
                        fieldDataStyleArr[i].setBorderLeft(BorderStyle.THIN);
                        fieldDataStyleArr[i].setBorderRight(BorderStyle.THIN);
                        fieldDataStyleArr[i].setBorderTop(BorderStyle.THIN);

                        cellX.setCellValue(fieldValueString);
                        cellX.setCellStyle(fieldDataStyleArr[i]);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                }
            }

            // sheet finally
            for (int i = 0; i < fields.size(); i++) {
                int fieldWidth = fieldWidthArr[i];
                if (fieldWidth > 0) {
                    sheet.setColumnWidth(i, fieldWidth);
                } else {
                    sheet.autoSizeColumn((short)i);
                }
            }
        }
    }

    /**
     * 导出Excel文件到磁盘
     *
     * @param fileName 文件名
     * @param sheetDataListArr  数据，可变参数，如多个参数则代表导出多张Sheet
     */
    public static void exportToFile(HttpServletRequest request, HttpServletResponse response, String fileName, List<?>... sheetDataListArr) {
        // workbook
        Workbook workbook = exportWorkbook(fileName, sheetDataListArr);
        OutputStream os = null;
        try {
            String userAgent = request.getHeader("User-Agent").toLowerCase();
            if (userAgent.indexOf("firefox") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            os = response.getOutputStream();
            // workbook 2 OutputStream
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 导出Excel字节数据
     *
     * @param sheetDataListArr
     * @return
     */
    public static byte[] exportToBytes(List<?>... sheetDataListArr){
        String filePath = "";
        // workbook
        Workbook workbook = exportWorkbook(filePath, sheetDataListArr);

        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] result = null;
        try {
            // workbook 2 ByteArrayOutputStream
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            // flush
            byteArrayOutputStream.flush();

            result = byteArrayOutputStream.toByteArray();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

}
