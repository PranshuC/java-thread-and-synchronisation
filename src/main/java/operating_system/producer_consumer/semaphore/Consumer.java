package operating_system.producer_consumer.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Consumer implements Runnable {

  private String name;
  private Queue<FoodItem> belt;
  Semaphore filledSlots;
  Semaphore availableSlots;

  @Override
  public void run() {
    while (true) {
      // Decrease filled slots
      try {
        filledSlots.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (belt.size() > 0) {
        belt.remove();
        System.out.println("Consumed by : " + name + ", Q size : " + belt.size());
      }

      // Increase filled slots
      availableSlots.release();
    }

  }

}
