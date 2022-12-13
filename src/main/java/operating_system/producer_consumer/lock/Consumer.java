package operating_system.producer_consumer.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Consumer implements Runnable {

  private String name;
  private Queue<FoodItem> belt;
  private Lock lock;

  @Override
  public void run() {
    while (true) {
      // Acquire a lock
      lock.lock();

      if (belt.size() > 0) {
        belt.remove();
        System.out.println("Consumed by : " + name + ", Q size : " + belt.size());
      }

      // Release a lock
      lock.unlock();
    }

  }

}
