package com.ecom.common.utils;

import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeToExcelIUtil {

    public static <T> void writeListToExcel(List<T> data, String filePath,String longtextID) throws IOException, IllegalAccessException {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data list is empty");
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            Field[] fields = data.get(0).getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // make private fields readable
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields[i].getName()); // header = field name
            }

            // Style for text fields (e.g. tracking number)
            CellStyle textStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            textStyle.setDataFormat(format.getFormat("@")); // force text

            // Fill data rows
            for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                T obj = data.get(rowIndex);

                for (int colIndex = 0; colIndex < fields.length; colIndex++) {
                    Cell cell = row.createCell(colIndex);
                    Object value = fields[colIndex].get(obj);

                    if (value != null) {
                        if (fields[colIndex].getName().equalsIgnoreCase(longtextID)) {
                            // force text for tracking numbers
                            cell.setCellStyle(textStyle);
                            cell.setCellValue(value.toString());
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }

            // Autosize columns
            for (int i = 0; i < fields.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        }
    }


}
