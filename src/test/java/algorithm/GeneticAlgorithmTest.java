package algorithm;

import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Task;
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
        Task task0 = new Task(1, 1, (long) (1.5 * ONE_HOUR), "Algebra");
        Task task1 = new Task(2, 2, (long) (1 * ONE_HOUR), "Biology");
        Task task2 = new Task(3, 3, (long) (2 * ONE_HOUR), "Chemistry");
        Task task3 = new Task(5, 1, (long) (1.5 * ONE_HOUR), "Algebra");
        Task task4 = new Task(6, 2, (long) (1 * ONE_HOUR), "Biology");
        Task task5 = new Task(7, 3, (long) (2 * ONE_HOUR), "Chemistry");
        Schedule schedule = new Schedule(LocalDate.parse("2016-04-27", dtf), LocalDate.parse("2016-05-20", dtf));
        schedule.createScheduleBySemester(Arrays.asList(task0, task1, task2, task3, task4, task5));

        System.out.println(schedule.toString());
    }
}
