package student;

public class Student {

    private final int numarMatricol;
    private final String prenume, nume, formatieDeStudiu;
    private double nota=0;

    public Student() {
        numarMatricol = 0;
        prenume = "Nespecificat";
        nume = "Nespecificat";
        formatieDeStudiu = "Nespecificat";
    }

    public Student(int nr, String pre, String num, String form, double nota) {
        numarMatricol = nr;
        prenume = pre;
        nume = num;
        formatieDeStudiu = form;
        this.nota=nota;
    }
    public Student(int nr, String pre, String num, String form) {
        numarMatricol = nr;
        prenume = pre;
        nume = num;
        formatieDeStudiu = form;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Student " +
                "Nr. Matricol: " + numarMatricol +
                ", Nume: " + nume +
                ", Prenume: " + prenume +
                ", Formatie: " + formatieDeStudiu +
                ", Nota: " + nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol == student.numarMatricol;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numarMatricol);
    }
}