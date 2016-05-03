package algorithm;

import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.SubTask;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class GeneticAlgorithmTest {

    private final int ONE_HOUR = 60 * 60 * 1000;
    private final int ONE_DAY = 24 * ONE_HOUR;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void testModel() {
        SubTask subTask0 = new SubTask(1, 1, (long) (1.5 * ONE_HOUR), "Algebra");
        SubTask subTask1 = new SubTask(2, 2, (long) (1 * ONE_HOUR), "Biology");
        SubTask subTask2 = new SubTask(3, 3, (long) (2 * ONE_HOUR), "Chemistry");
        SubTask subTask3 = new SubTask(5, 1, (long) (1.5 * ONE_HOUR), "Algebra");
        SubTask subTask4 = new SubTask(6, 2, (long) (1 * ONE_HOUR), "Biology");
        SubTask subTask5 = new SubTask(7, 3, (long) (2 * ONE_HOUR), "Chemistry");
        Schedule schedule = new Schedule(LocalDate.parse("2016-04-27", dtf), LocalDate.parse("2016-05-20", dtf));
        schedule.createScheduleBySemester(Arrays.asList(subTask0, subTask1, subTask2, subTask3, subTask4, subTask5));

        System.out.println(schedule.toString());
    }
}
