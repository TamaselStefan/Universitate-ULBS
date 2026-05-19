package lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import student.Student;
import student.StudentBursieri;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class p4_StudentiXls {

    public static void writeToXls(Set<Student> studenti, String numeFisier) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Studenti");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nr Matricol");
        header.createCell(1).setCellValue("Prenume");
        header.createCell(2).setCellValue("Nume");
        header.createCell(3).setCellValue("Formatie");
        header.createCell(4).setCellValue("Nota");
        header.createCell(5).setCellValue("Bursa");

        int rowNum = 1;
        for (Student s : studenti) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(s.getNumarMatricol());
            row.createCell(1).setCellValue(s.getPrenume());
            row.createCell(2).setCellValue(s.getNume());
            row.createCell(3).setCellValue(s.getFormatieDeStudiu());
            row.createCell(4).setCellValue(s.getNota());

            if (s instanceof StudentBursieri) {
                row.createCell(5).setCellValue(((StudentBursieri) s).getCuantumBursa());
            } else {
                row.createCell(5).setCellValue(0.0);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(numeFisier)) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Studenti salvati in: " + numeFisier);
        } catch (IOException e) {
            System.err.println("Eroare la scriere: " + e.getMessage());
        }
    }

    public static List<Student> readFromXls(String numeFisier) {
        List<Student> studenti = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(numeFisier);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;

            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                int nrMatricol = (int) row.getCell(0).getNumericCellValue();
                String prenume   = row.getCell(1).getStringCellValue();
                String nume      = row.getCell(2).getStringCellValue();
                String formatie  = row.getCell(3).getStringCellValue();
                double nota      = row.getCell(4).getNumericCellValue();
                double bursa     = row.getCell(5).getNumericCellValue();

                Student s;
                if (bursa > 0) {
                    s = new StudentBursieri(nrMatricol, prenume, nume, formatie, bursa);
                    s.setNota(nota);
                } else {
                    s = new Student(nrMatricol, prenume, nume, formatie, nota);
                }
                studenti.add(s);
            }

        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        }

        return studenti;
    }

    public static void main(String[] args) {
        Set<Student> studenti = new HashSet<>();
        studenti.add(new StudentBursieri(1025, "Andrei", "Popa", "ISM141/2", 725.50));
        studenti.add(new StudentBursieri(1024, "Ioan", "Mihalcea", "ISM141/1", 801.10));
        studenti.add(new StudentBursieri(1026, "Anamaria", "Prodan", "TI131/1", 745.50));
        studenti.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1", 780.80));
        studenti.add(new Student(1030, "Maria", "Ionescu", "TI131/2", 8.50));

        String xlsFileName = "laborator8_students.xlsx";
        writeToXls(studenti, xlsFileName);

        List<Student> studentsFromXls = readFromXls(xlsFileName);
        System.out.println("\nStudenti cititi din xlsx:");
        for (Student st : studentsFromXls) {
            System.out.println(st);
        }
    }
}
