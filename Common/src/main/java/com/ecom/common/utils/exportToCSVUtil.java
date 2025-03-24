package com.ecom.common.utils;

import com.opencsv.CSVWriter;
import java.lang.reflect.Field;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class exportToCSVUtil {

    public static void writeDataToCSV(String filepath, List<String[]> data){

        try (CSVWriter csvWriter= new CSVWriter(new FileWriter(filepath))){
            csvWriter.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> void writeToCsv(String filePath, List<T> data) {
        if (data == null || data.isEmpty())
        {
            System.out.println("No data to write to CSV.");
            return;
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Get the class of the first element
            Class<?> clazz = data.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            // Write header row
            String[] header = extractFieldNames(fields);
            writer.writeNext(header);
            // Write data rows
            for (T item : data) {
                String[] row = extractFieldValues(fields, item);
                writer.writeNext(row);
            }
        }
        catch (IOException | IllegalAccessException e) {
            e.printStackTrace(); }
    }

    /** * Extracts field names from the object class. */
    private static String[] extractFieldNames(Field[] fields) {
        return List.of(fields).stream() .map(Field::getName) .toArray(String[]::new); }
    /** * Extracts field values from an object. */
    private static <T> String[] extractFieldValues(Field[] fields, T item) throws IllegalAccessException {
        return List.of(fields).stream() .map(field -> {
            field.setAccessible(true);
            try { return String.valueOf(field.get(item)); }
            catch (IllegalAccessException e) {
                return ""; } }).toArray(String[]::new); }
}
