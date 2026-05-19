package lab10;

import student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentiInFisierText implements StrategieStudenti {

    private String numeFisier;

    public StudentiInFisierText(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    @Override
    public void executa(List<Student> studenti) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Student s : studenti) {
                bw.write(
                        s.getNumarMatricol() + "," +
                                s.getPrenume() + "," +
                                s.getNume() + "," +
                                s.getFormatieDeStudiu() + "," +
                                s.getNota()
                );
                bw.newLine();
            }

            System.out.println("Studentii au fost salvati in TXT: " + numeFisier);

        } catch (IOException e) {
            System.out.println("Eroare la scriere TXT: " + e.getMessage());
        }
    }

    public List<Student> citeste() {
        List<Student> studenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String linie;

            while ((linie = br.readLine()) != null) {
                String[] date = linie.split(",");

                int nr = Integer.parseInt(date[0].trim());
                String prenume = date[1].trim();
                String nume = date[2].trim();
                String formatie = date[3].trim();
                double nota = Double.parseDouble(date[4].trim());

                studenti.add(new Student(nr, prenume, nume, formatie, nota));
            }

        } catch (IOException e) {
            System.out.println("Eroare la citire TXT: " + e.getMessage());
        }

        return studenti;
    }
}