/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author PHIVH
 */
public class LotteryDataExcelExporter {

    /**
     * Export list table to excel file (.xlsx)
     *
     * @param listTable list of table to be exported
     */
    public void exportTables(List<LotteryTable> listTable) {
        try {
            Workbook wb;
            wb = new XSSFWorkbook();

            String[] titles = new String[100];
            for (int i = 0; i < 100; ++i) {
                if (i <= 9) {
                    titles[i] = "0" + i;
                } else {
                    titles[i] = "" + i;
                }
            }

            Map<String, CellStyle> styles = createStyles(wb);
            Sheet sheet = wb.createSheet("SoKetQua");

            //the header row: centered text in 48pt font
            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(12.75f);

            for (int i = 0; i < titles.length; ++i) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(styles.get("header"));
            }

            sheet.setColumnWidth(0, 12 * 256);
            for (int i = 1; i <= titles.length; ++i) {
                sheet.setColumnWidth(i, 3 * 256);
            }

            sheet.createFreezePane(1, 1);

            Row row;
            Cell cell;
            int rowNum = 1;
            int colNum = 0;
            for (LotteryTable table : listTable) {
                row = sheet.createRow(rowNum);

                for (colNum = 0; colNum <= 101; ++colNum) {
                    cell = row.createCell(colNum);

                    if (colNum == 0 || colNum == 101) {
                        cell.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(table.getDate()));
                    } else {
                        List<String> listAllGiai = table.getAllListGiaiNormalized();

                        if (!listAllGiai.contains(headerRow.getCell(colNum).getStringCellValue())) {
                            cell.setCellStyle(styles.get("cell_bg_grey"));
                        } else {
                            int count = 0;
                            for (int i = 0; i < listAllGiai.size(); ++i) {
                                for (String string : listAllGiai) {
                                    if (string.equalsIgnoreCase(headerRow.getCell(colNum).getStringCellValue())) {
                                        count++;
                                    }
                                }

                                if (table.giaiDB().substring(table.giaiDB().length() - 2).equalsIgnoreCase(headerRow.getCell(colNum).getStringCellValue())) {
                                    cell.setCellStyle(styles.get("cell_bg_green"));
                                } else {
                                    cell.setCellStyle(styles.get("cell_bg_normal"));
                                }
                                cell.setCellValue(count);
                                count = 0;
                            }
                        }
                    }
                }

                rowNum++;
            }

            // Write the output to a file            
            String file = listTable.size() > 1 ? "SoKetQua.xls" : "KetQua.xls";
            if (wb instanceof XSSFWorkbook) {
                file += "x";
            }
            FileOutputStream out = new FileOutputStream(file);
            wb.write(out);
            out.close();

            wb.close();
        } catch (Exception e) {
        }
    }

    /**
     *
     * @param listLocationPair
     */
    public void exportLocationPairs(List<LotteryTable> listTables) {
        try {
            Workbook wb;
            wb = new XSSFWorkbook();

            String[] titles = new String[11449];
            int k = 0;
            for (int i = 0; i < 107; ++i) {
                for (int j = 0; j < 107; ++j) {
                    titles[k] = i + "-" + j;
                    ++k;
                }
            }

            Map<String, CellStyle> styles = createStyles(wb);
            Sheet sheet = wb.createSheet("Cầu lô");

            //the header row: centered text in 48pt font
            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(12.75f);

            for (int i = 0; i < titles.length; ++i) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(styles.get("header"));
            }

            sheet.setColumnWidth(0, 12 * 256);
            for (int i = 1; i <= titles.length; ++i) {
                sheet.setColumnWidth(i, 7 * 256);
            }

            sheet.createFreezePane(1, 1);

            Row row;
            Cell cell;
            int rowNum = 1;
            int colNum = 0;
            row = sheet.createRow(rowNum);

            for (int i = 0; i < 100 /*listTables.size()*/; ++i) {
                LotteryTable table = (LotteryTable) listTables.get(i);
                row = sheet.createRow(rowNum);

                for (colNum = 0; colNum <= 11449; ++colNum) {
                    cell = row.createCell(colNum);

                    if (colNum == 0) {
                        cell.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(table.getDate()));
                    } else {
                        List<LocationPair> listLocationPairs = new LocationPairExtractor(table).getListLocationPair();
                        cell = row.createCell(colNum);
                        LocationPair l = listLocationPairs.get(colNum - 1);
                        cell.setCellValue(l.getNumber());
                    }
                }

                rowNum++;
            }

            // Write the output to a file
            String file = "CauLo.xls";
            if (wb instanceof XSSFWorkbook) {
                file += "x";
            }
            FileOutputStream out = new FileOutputStream(file);
            wb.write(out);
            out.close();

            wb.close();
        } catch (Exception e) {
        }
    }

    /**
     * create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat df = wb.createDataFormat();

        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("cell_bg_normal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("cell_bg_grey", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("cell_bg_green", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_b_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_g", style);

        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short) 14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_normal_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setIndention((short) 1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.put("cell_blue", style);

        return styles;
    }

    private static CellStyle createBorderedStyle(Workbook wb) {
        BorderStyle thin = BorderStyle.THIN;
        short black = IndexedColors.BLACK.getIndex();

        CellStyle style = wb.createCellStyle();
        style.setBorderRight(thin);
        style.setRightBorderColor(black);
        style.setBorderBottom(thin);
        style.setBottomBorderColor(black);
        style.setBorderLeft(thin);
        style.setLeftBorderColor(black);
        style.setBorderTop(thin);
        style.setTopBorderColor(black);
        return style;
    }
}
