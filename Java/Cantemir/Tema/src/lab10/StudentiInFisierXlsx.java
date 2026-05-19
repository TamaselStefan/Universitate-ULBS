package lab10;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentiInFisierXlsx implements StrategieStudenti {

    private String numeFisier;

    public StudentiInFisierXlsx(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    @Override
    public void executa(List<Student> studenti) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Studenti");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nr matricol");
        header.createCell(1).setCellValue("Prenume");
        header.createCell(2).setCellValue("Nume");
        header.createCell(3).setCellValue("Formatie");
        header.createCell(4).setCellValue("Nota");

        int rand = 1;

        for (Student s : studenti) {
            Row row = sheet.createRow(rand++);

            row.createCell(0).setCellValue(s.getNumarMatricol());
            row.createCell(1).setCellValue(s.getPrenume());
            row.createCell(2).setCellValue(s.getNume());
            row.createCell(3).setCellValue(s.getFormatieDeStudiu());
            row.createCell(4).setCellValue(s.getNota());
        }

        try (FileOutputStream fos = new FileOutputStream(numeFisier)) {
            workbook.write(fos);
            workbook.close();

            System.out.println("Studentii au fost salvati in XLSX: " + numeFisier);

        } catch (IOException e) {
            System.out.println("Eroare la scriere XLSX: " + e.getMessage());
        }
    }

    public List<Student> citeste() {
        List<Student> studenti = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(numeFisier);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                int nr = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();
                double nota = row.getCell(4).getNumericCellValue();

                studenti.add(new Student(nr, prenume, nume, formatie, nota));
            }

        } catch (IOException e) {
            System.out.println("Eroare la citire XLSX: " + e.getMessage());
        }

        return studenti;
    }
}