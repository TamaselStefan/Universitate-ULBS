package lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class p3_AdaugaMediaFormula {

    public static void copieazaSiAdaugaFormulaMedia(String fisierInput, String fisierOutput) {
        try (FileInputStream fis = new FileInputStream(fisierInput);
             Workbook workbookIn = new XSSFWorkbook(fis)) {

            Sheet sheetIn = workbookIn.getSheetAt(0);
            Workbook workbookOut = new XSSFWorkbook();
            Sheet sheetOut = workbookOut.createSheet("Sheet1");

            int rowNum = 0;
            for (Row rowIn : sheetIn) {
                Row rowOut = sheetOut.createRow(rowNum++);
                int colNum = 0;

                for (Cell cellIn : rowIn) {
                    Cell cellOut = rowOut.createCell(colNum++);
                    switch (cellIn.getCellType()) {
                        case STRING:
                            cellOut.setCellValue(cellIn.getStringCellValue());
                            break;
                        case NUMERIC:
                            cellOut.setCellValue(cellIn.getNumericCellValue());
                            break;
                        default:
                            cellOut.setCellValue("");
                    }
                }

                Cell celulaMedie = rowOut.createCell(colNum);

                if (rowNum == 1) {
                    celulaMedie.setCellValue("Media");
                } else {
                    String formula = "AVERAGE(D" + rowNum + ":F" + rowNum + ")";
                    celulaMedie.setCellFormula(formula);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(fisierOutput)) {
                workbookOut.write(fos);
            }
            workbookOut.close();
            System.out.println("Fisier generat: " + fisierOutput);

        } catch (IOException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        copieazaSiAdaugaFormulaMedia("laborator8_input.xlsx", "laborator8_output3.xlsx");
    }
}
