package operating_system.adder_subtractor.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
  public static void main(String[] args) {

    // 1. Create a count variable (shared resource)
    Count count = new Count();

    // 1.5. Create a common lock instance
    // Mutex Solution - Reentrance : simplest lock
    Lock lock = new ReentrantLock();

    // 2. Create adders & subtractors
    Adder adder = new Adder(count, lock);
    Subtractor subtractor = new Subtractor(count, lock);

    // 3. Create Threads for each task
    Thread adderThread = new Thread(adder);
    Thread subtractorThread = new Thread(subtractor);

    // 4. Executed threads using .start()
    adderThread.start();
    subtractorThread.start();

    // 5. Wait until completion
    try {
      adderThread.join();
      subtractorThread.join();
    } catch (InterruptedException e) {
      System.out.println("Exception : " + e);
    }

    // Doesn't print expected - 0, but some random value (no synchronisation)
    System.out.println("Final value : " + count.value);
  }
}