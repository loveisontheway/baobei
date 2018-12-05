package com.yaozhitech.baobei.child.utils;


import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;
import com.yaozhitech.baobei.child.dto.LeaderFeedbackDTO;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 * Excel Merge Cell
 *
 * @author jiangjialiang 2018/5/24
 */
public abstract class ExcelMergeUtil {

    private static Logger logger = Logger.getLogger(ExcelMergeUtil.class);

    /**
     * 反馈表
     *
     * @param fileName   文件名称
     * @param td         表列
     * @param list       数据集
     * @param mergeCells 要合并的列 [可选]
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     */
    public static void feedbackExelMerge(String fileName, final String[] td, List<LeaderFeedbackDTO> list, final Integer[] mergeCells, HttpServletRequest request, HttpServletResponse response) {
        try {
            String userAgent = request.getHeader("User-Agent").toLowerCase();
            if (userAgent.indexOf("firefox") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream os = response.getOutputStream();
            feedbackExcelMerge(os, fileName, td, list, mergeCells);
            response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 导出Excel (反馈表-合并单元格)
     *
     * @param os         输出流
     * @param fileName   文件名称
     * @param td         表列
     * @param list       数据集
     * @param mergeCells 要合并的列
     */
    public static void feedbackExcelMerge(OutputStream os, String fileName, final String[] td, List<LeaderFeedbackDTO> list,
                                        final Integer[] mergeCells) throws Exception {
        // expanded-name
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // HSSFWorkbook=2003/xls  XSSFWorkbook=2007/xlsx
        Workbook workbook = suffix.equals("xls") ? new HSSFWorkbook() : new XSSFWorkbook();
        String title = URLDecoder.decode(fileName, "UTF-8");
        title = title.substring(title.lastIndexOf("/") + 1, title.lastIndexOf("."));
        Sheet sheet = workbook.createSheet(title);

        sheet.setDefaultColumnWidth(15);                                // 设置表格默认列宽度为15个字节

        CellStyle headStyle = createHeadStyle(workbook);                // 生成表头样式
        CellStyle tdStyle = createTdStyle(workbook);                    // 生成表列样式
        CellStyle commonDataStyle = createCommonDataStyle(workbook);    // 生成数据样式

        if (td == null || td.length <= 0) {
            return;
        }

        /** 表头 */
        Row row = sheet.createRow(0);

        /** 表列 */
        for (int i = 0; i < td.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(tdStyle);
            RichTextString text = suffix.equals("xls") ?
                    new HSSFRichTextString(td[i]) :
                    new XSSFRichTextString(td[i]);
            cell.setCellValue(text);
        }

        /** 遍历集合数据，产生数据行 */
        Iterator<LeaderFeedbackDTO> it = list.iterator();
        String[] data = new String[td.length];
        int rowNum = 0;             // 记录行号
        int mergeNum = rowNum + 1;  // 合并行号
        while (it.hasNext()) {
            LeaderFeedbackDTO dto = it.next();
            List<LeaderFeedbackComplaint> complaintList = dto.getComplaintList();
            if (complaintList.size() > 0) {
                for (int i = 0; i < complaintList.size(); i++) {
                    rowNum++;
                    row = sheet.createRow(rowNum);
                    LeaderFeedbackComplaint complaint = dto.getComplaintList().get(i);
                    data[0] = dto.getCities();
                    data[1] = dto.getTitle();
                    data[2] = dto.getSname();
                    data[3] = dto.getLeaderName();
                    data[4] = dto.getNickName();
                    data[5] = DateUtil.timestamp2Str(dto.getCrtTime());
                    data[6] = dto.getSigninImg();
                    data[7] = dto.getLeaderImg();
                    data[8] = dto.getChildLeaderInsuranceImg();

                    String alltuu = "";
                    if (Util.isNotNull(dto.getAlltuu())) {
                        switch (dto.getAlltuu()) {
                            case 0:
                                alltuu = "否"; break;
                            case 1:
                                alltuu = "是"; break;
                            default:
                                break;
                        }
                    }
                    data[9] = alltuu;

                    String poster = "";
                    if (Util.isNotNull(dto.getPoster())) {
                        switch (dto.getPoster()) {
                            case 0:
                                poster = "否"; break;
                            case 1:
                                poster = "是"; break;
                            default:
                                break;
                        }
                    }
                    data[10] = poster;

                    data[11] = dto.getRemark();
                    data[12] = dto.getBusImg();
                    data[13] = complaint.getName();
                    data[14] = complaint.getDescr();
                    data[15] = complaint.getPic();
                    for (int j = 0; j < data.length; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(commonDataStyle);
                        cell.setCellValue(data[j]);
                    }
                }
            } else {
                rowNum++;
                row = sheet.createRow(rowNum);
                data[0] = dto.getCities();
                data[1] = dto.getTitle();
                data[2] = dto.getSname();
                data[3] = dto.getLeaderName();
                data[4] = dto.getNickName();
                data[5] = DateUtil.timestamp2Str(dto.getCrtTime());
                data[6] = dto.getSigninImg();
                data[7] = dto.getLeaderImg();
                data[8] = dto.getChildLeaderInsuranceImg();

                String alltuu = "";
                if (Util.isNotNull(dto.getAlltuu())) {
                    switch (dto.getAlltuu()) {
                        case 0:
                            alltuu = "否"; break;
                        case 1:
                            alltuu = "是"; break;
                        default:
                            break;
                    }
                }
                data[9] = alltuu;

                String poster = "";
                if (Util.isNotNull(dto.getPoster())) {
                    switch (dto.getPoster()) {
                        case 0:
                            poster = "否"; break;
                        case 1:
                            poster = "是"; break;
                        default:
                            break;
                    }
                }
                data[10] = poster;

                data[11] = dto.getRemark();
                data[12] = dto.getBusImg();
                data[13] = "";
                data[14] = "";
                data[15] = "";
                for (int j = 0; j < data.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(commonDataStyle);
                    cell.setCellValue(data[j]);
                }
            }

            // 避免一行数据合并单元格
            if (dto.getComplaintList().size() > 1) {
                // 根据要求合并单元格
                if (mergeCells != null && mergeCells.length > 0) {
                    for (int i = 0; i < mergeCells.length; i++) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(mergeNum, mergeNum + dto.getComplaintList().size() - 1, mergeCells[i], mergeCells[i]);
                        sheet.addMergedRegion(cellRangeAddress);
                    }
                }
                // 记录上一次合并行号范围
                mergeNum = mergeNum + dto.getComplaintList().size();
            } else {
                mergeNum++;
            }
        }

        try {
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 解决合并单元格边框缺失的问题
     *
     * @param sheet  工作表
     * @param region 合并单元格
     * @param cs     单元格样式
     */
    private static void setRegionStyle(Sheet sheet, CellRangeAddress region, CellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = CellUtil.getRow(i, sheet);
            Cell cell = null;
            // 循环设置单元格样式
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                cell = CellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 表头单元格样式
     *
     * @param workbook 工作簿
     * @return CellStyle
     */
    private static CellStyle createHeadStyle(Workbook workbook) {
        // 表头单元格样式
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);           // 垂直居中
        headStyle.setAlignment(HorizontalAlignment.CENTER);                 // 水平居中
        headStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index); // 设置图案颜色
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);         // 设置图案
        // 设置边框
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setAlignment(HorizontalAlignment.CENTER); // 设置居中
        // 表头单元格字体
        Font headFont = workbook.createFont();
        headFont.setColor(IndexedColors.BLACK.index);
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 16);     // 设置字体大小
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);
        return headStyle;
    }

    /**
     * 表列单元格样式
     *
     * @param workbook 工作簿
     * @return CellStyle
     */
    private static CellStyle createTdStyle(Workbook workbook) {
        // 表列单元格样式
        CellStyle tdStyle = workbook.createCellStyle();
        tdStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        tdStyle.setAlignment(HorizontalAlignment.CENTER);
        tdStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        tdStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        tdStyle.setBorderBottom(BorderStyle.THIN);
        tdStyle.setBorderLeft(BorderStyle.THIN);
        tdStyle.setBorderRight(BorderStyle.THIN);
        tdStyle.setBorderTop(BorderStyle.THIN);
        tdStyle.setAlignment(HorizontalAlignment.CENTER);
        // 表列单元格字体
        Font headFont = workbook.createFont();
        headFont.setColor(IndexedColors.VIOLET.index);
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBold(true);
        // 把字体应用到当前的样式
        tdStyle.setFont(headFont);
        return tdStyle;
    }

    /**
     * 数据单元格样式
     *
     * @param workbook 工作簿
     * @return CellStyle
     */
    private static CellStyle createCommonDataStyle(Workbook workbook) {
        // 普通数据单元格样式
        CellStyle commonDataStyle = workbook.createCellStyle();
        commonDataStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        commonDataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        commonDataStyle.setBorderBottom(BorderStyle.THIN);
        commonDataStyle.setBorderLeft(BorderStyle.THIN);
        commonDataStyle.setBorderRight(BorderStyle.THIN);
        commonDataStyle.setBorderTop(BorderStyle.THIN);
        commonDataStyle.setAlignment(HorizontalAlignment.CENTER);
        commonDataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 普通数据单元格字体
        Font commonDataFont = workbook.createFont();
        // 把字体应用到当前的样式
        commonDataStyle.setFont(commonDataFont);
        return commonDataStyle;
    }

}