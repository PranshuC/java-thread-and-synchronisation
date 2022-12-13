package operating_system.producer_consumer.synchronised;

import java.util.Queue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Consumer implements Runnable {

  private String name;
  private Queue<FoodItem> belt;

  @Override
  public void run() {
    while (true) {
      // Acquire a lock
      synchronized (belt) {

        if (belt.size() > 0) {
          belt.remove();
          System.out.println("Consumed by : " + name + ", Q size : " + belt.size());
        }

        // Release a lock
      }
    }

  }

}
