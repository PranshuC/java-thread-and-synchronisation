package operating_system.multi_thread.merge_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runner {
    public static void main(String[] args) throws Exception {
        Integer[] numbers = { 10, 9, 8, 7, 1, 2, 3, 4 };
        List<Integer> values = new ArrayList<>();
        Collections.addAll(values, numbers);

        ExecutorService executor = Executors.newCachedThreadPool();
        Sorter sorter = new Sorter(values, executor);
        Future<List<Integer>> sortedValues = executor.submit(sorter);
        System.out.println(sortedValues.get());
        executor.shutdown();
    }
}