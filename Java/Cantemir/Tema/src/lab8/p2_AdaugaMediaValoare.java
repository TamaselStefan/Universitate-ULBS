package lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class p2_AdaugaMediaValoare {

    public static void copieazaSiAdaugaMedia(String fisierInput, String fisierOutput) {
        try (FileInputStream fis = new FileInputStream(fisierInput);
             Workbook workbookIn = new XSSFWorkbook(fis)) {

            Sheet sheetIn = workbookIn.getSheetAt(0);
            Workbook workbookOut = new XSSFWorkbook();
            Sheet sheetOut = workbookOut.createSheet("Sheet1");

            int rowNum = 0;
            for (Row rowIn : sheetIn) {
                Row rowOut = sheetOut.createRow(rowNum++);
                int colNum = 0;

                // copiem toate celulele existente
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
                    Cell c3 = rowIn.getCell(3);
                    Cell c4 = rowIn.getCell(4);
                    Cell c5 = rowIn.getCell(5);

                    if (c3 != null && c4 != null && c5 != null) {
                        double medie = (c3.getNumericCellValue()
                                + c4.getNumericCellValue()
                                + c5.getNumericCellValue()) / 3.0;
                        celulaMedie.setCellValue(medie);
                    }
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
        copieazaSiAdaugaMedia("laborator8_input.xlsx", "laborator8_output2.xlsx");
    }
}
