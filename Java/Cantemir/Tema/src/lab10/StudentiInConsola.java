package lab10;

import student.Student;
import java.util.List;

public class StudentiInConsola implements StrategieStudenti {

    @Override
    public void executa(List<Student> studenti) {
        for (Student s : studenti) {
            System.out.println(s);
        }
    }
}