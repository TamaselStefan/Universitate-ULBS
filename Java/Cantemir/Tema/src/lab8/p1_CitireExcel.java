package lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class p1_CitireExcel {

    public static void citesteSiAfiseaza(String numeFisier) {
        try (FileInputStream fis = new FileInputStream(numeFisier);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            System.out.println("=== Continut fisier: " + numeFisier + " ===");

            for (Row row : sheet) {
                StringBuilder sb = new StringBuilder();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            sb.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            double val = cell.getNumericCellValue();
                            if (val == Math.floor(val)) {
                                sb.append((int) val);
                            } else {
                                sb.append(val);
                            }
                            break;
                        default:
                            sb.append("?");
                            break;
                    }
                    sb.append("\t");
                }
                System.out.println(sb.toString().trim());
            }

        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        citesteSiAfiseaza("laborator8_input.xlsx");
    }
}
