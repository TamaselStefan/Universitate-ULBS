package lab9;

import student.Student;

import java.util.*;
import java.util.stream.*;

public class p3 {
    public static void main(String[] args) {

        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025, "Andrei",   "Popa",     "ISM141/2", 8.70),
                new Student(1024, "Ioan",     "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan",   "TI131/1",  8.90),
                new Student(1029, "Bianca",   "Popescu",  "TI131/1",  10),
                new Student(1029, "Maria",    "Pana",     "TI131/2",  4.10),
                new Student(1029, "Gabriela", "Mohanu",   "TI131/2",  7.33),
                new Student(1029, "Marius",   "Nasta",    "TI131/2",  3.20),
                new Student(1029, "Marius",   "Nasta",    "TI131/1",  5.12),
                new Student(1029, "Andrei",   "Dobrescu", "TI131/2",  2.22)
        );

        System.out.println("=== Studenti cu nota 10 ===");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10)
                .forEach(System.out::println);

        System.out.println("\n=== Studenti cu nota sub 5 ===");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5)
                .forEach(System.out::println);

        System.out.println("\n=== Lista transformata (nota minima 4) ===");
        List<Student> listaTransformata = studentiCuNote.stream()
                .map(s -> {
                    if (s.getNota() < 4) {
                        s.setNota(4.0f);
                    }
                    return s;
                })
                .collect(Collectors.toList());
        listaTransformata.forEach(System.out::println);

        double sumaNote = studentiCuNote.stream()
                .mapToDouble(s -> s.getNota())
                .sum();
        System.out.println("\n=== Suma si Media ===");
        System.out.println("Suma notelor: " + sumaNote);

        double media = sumaNote / studentiCuNote.size();
        System.out.println("Media notelor: " + media);
    }
}