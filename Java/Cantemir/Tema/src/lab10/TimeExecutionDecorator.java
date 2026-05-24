package lab10;

import student.Student;
import java.util.List;

public class TimeExecutionDecorator implements StrategieStudenti, ITimeExecution {
    private StrategieStudenti strategieStudenti;
    private List<Student> studentl;

    public TimeExecutionDecorator(StrategieStudenti exporter, List<Student> studentl) {
        this.strategieStudenti = exporter;
        this.studentl = studentl;
    }

    @Override
    public void executa(List<Student> studenti) {
        long startTime = System.currentTimeMillis();
        strategieStudenti.executa(studenti);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " ms for " + strategieStudenti.getClass().getSimpleName());
    }

    @Override
    public long executionTime(List<Student> studenti) {
        long execTime = System.currentTimeMillis();
        strategieStudenti.executa(studenti);
        return System.currentTimeMillis() - execTime;
    }
}