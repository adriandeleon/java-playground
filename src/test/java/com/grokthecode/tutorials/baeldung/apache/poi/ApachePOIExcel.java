package com.grokthecode.tutorials.baeldung.apache.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApachePOIExcel {

    private static String FILE_NAME = "temp.xlsx";
    private String fileLocation;

    @BeforeEach
    public void generateExcelFile() throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;
    }

    @Test
    public void whenParsingPOIExcelFile_thenCorrect() throws IOException {
        Map<Integer, List<String>> data
                = writeExcelFile(fileLocation);

        assertEquals("Name", data.get(0).get(0));
        assertEquals("Age", data.get(0).get(1));

        assertEquals("John Smith", data.get(1).get(0));
        assertEquals("20", data.get(1).get(1));
    }


    private Map<Integer, List<String>> writeExcelFile(final String fileLocation) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        //String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        return null;
    }
}
