package lab10;
import student.Student;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {

    public static void main(String[] args) {

        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        List<StrategieStudenti> strategies = Arrays.asList(
                new StudentiInConsola(),
                new StudentiInFisierText("studentiStrategyText.txt"),
                new StudentiInFisierXlsx("studentiStrategyExcel.xlsx")
        );

        for (StrategieStudenti strategy : strategies) {
            TimeExecutionDecorator decorator = new TimeExecutionDecorator(strategy, studenti);
            long time = decorator.executionTime(studenti);
            System.out.println("Execution time: " + time + " ms for " + strategy.getClass().getSimpleName());
            System.out.println();
        }

        System.out.println("\n  Citire TXT  ");
        StudentiInFisierText citireText = new StudentiInFisierText("studentiStrategyText.txt");
        List<Student> studentiDinTxt = citireText.citeste();
        StudentiInConsola consola = new StudentiInConsola();
        consola.executa(studentiDinTxt);

        System.out.println("\n  Citire XLSX  ");
        StudentiInFisierXlsx citireXlsx = new StudentiInFisierXlsx("studentiStrategyExcel.xlsx");
        List<Student> studentiDinXlsx = citireXlsx.citeste();
        consola.executa(studentiDinXlsx);
    }
}