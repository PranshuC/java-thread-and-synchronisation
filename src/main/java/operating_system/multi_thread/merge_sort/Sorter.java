package operating_system.multi_thread.merge_sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sorter implements Callable<List<Integer>> {
  private List<Integer> values = new ArrayList<>();
  private ExecutorService executorService;

  @Override
  public List<Integer> call() throws Exception {

    // Base case - If size of array 1 is return
    if (values.size() <= 1) {
      return values;
    }

    // Split the array
    int mid = values.size() / 2;

    List<Integer> leftArray = values.subList(0, mid);
    List<Integer> rightArray = values.subList(mid, values.size());

    Sorter leftSorter = new Sorter(leftArray, executorService);
    Sorter rightSorter = new Sorter(rightArray, executorService);

    Future<List<Integer>> sortedLeft = executorService.submit(leftSorter);
    Future<List<Integer>> sortedRight = executorService.submit(rightSorter);

    // Merge the array
    return merge(sortedLeft, sortedRight);
  }

  private List<Integer> merge(Future<List<Integer>> sortedLeftFuture, Future<List<Integer>> sortedRightFuture)
  throws InterruptedException, ExecutionException {
    List<Integer> sortedArray = new ArrayList<>();
    int first = 0;
    int second = 0;

    // Blocking call
    List<Integer> sortedLeft = sortedLeftFuture.get();
    List<Integer> sortedRight = sortedRightFuture.get();

    // Compare values from both the arrays
    while (first < sortedLeft.size() && second < sortedRight.size()) {
      // If left is smaller, add to sorted array
      // increment first
      if (sortedLeft.get(first) < sortedRight.get(second)) {
        sortedArray.add(sortedLeft.get(first));
        ++first;
      }
      // Add the right one to the sorted array
      // increment second
      else {
        sortedArray.add(sortedRight.get(second));
        ++second;
      }
    }

    while (first < sortedLeft.size()) {
      sortedArray.add(sortedLeft.get(first));
      ++first;
    }

    while (second < sortedRight.size()) {
      sortedArray.add(sortedRight.get(second));
      ++second;
    }

    return sortedArray;
  }

}