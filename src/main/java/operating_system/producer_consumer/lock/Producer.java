package operating_system.producer_consumer.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Producer implements Runnable {

  private String name;
  private Queue<FoodItem> belt;
  private int maxSize;
  private Lock lock;

  @Override
  public void run() {
    while (true) {
      // Acquire a lock
      lock.lock();

      if (belt.size() <= maxSize) {
        // Create a food item
        FoodItem sushi = new FoodItem();
        // Add it to the belt
        belt.add(sushi);
        System.out.println("Produced by : " + name + ", Q size : " + belt.size());
      }

      // Release a lock
      lock.unlock();
    }

  }

}
