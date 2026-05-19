package lab8;

import student.Student;
import student.StudentBursieri;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainLab8 {

    public static void main(String[] args) {

        System.out.println("Citire si afisare Excel");
        p1_CitireExcel.citesteSiAfiseaza("laborator8_input.xlsx");

        System.out.println("\nCopiere + media ca valoare");
        p2_AdaugaMediaValoare.copieazaSiAdaugaMedia(
                "laborator8_input.xlsx",
                "laborator8_output2.xlsx"
        );

        System.out.println("\nCopiere + media ca formula AVERAGE=");
        p3_AdaugaMediaFormula.copieazaSiAdaugaFormulaMedia(
                "laborator8_input.xlsx",
                "laborator8_output3.xlsx"
        );

        System.out.println("\n8.5.4 - Export/Import Studenti");
        Set<Student> studenti = new HashSet<>();
        studenti.add(new StudentBursieri(1025, "Andrei", "Popa", "ISM141/2", 725.50));
        studenti.add(new StudentBursieri(1024, "Ioan", "Mihalcea", "ISM141/1", 801.10));
        studenti.add(new StudentBursieri(1026, "Anamaria", "Prodan", "TI131/1", 745.50));
        studenti.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1", 780.80));
        studenti.add(new Student(1030, "Maria", "Ionescu", "TI131/2", 8.50));

        // 8.5.4 a
        String xlsFileName = "laborator8_students.xlsx";
        p4_StudentiXls.writeToXls(studenti, xlsFileName);

        // 8.5.4 b
        List<Student> studentsFromXls = p4_StudentiXls.readFromXls(xlsFileName);
        System.out.println("\nStudenti cititi din xlsx:");
        for (Student st : studentsFromXls) {
            System.out.println(st);
        }
    }
}
