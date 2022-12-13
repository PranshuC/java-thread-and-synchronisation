package operating_system.producer_consumer.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

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
  Semaphore availableSlots;
  Semaphore filledSlots;

  @Override
  public void run() {
    while (true) {
      // Decrease available slots
      try {
        availableSlots.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (belt.size() <= maxSize) {
        // Create a food item
        FoodItem sushi = new FoodItem();
        // Add it to the belt
        belt.add(sushi);
        System.out.println("Produced by : " + name + ", Q size : " + belt.size());
      }

      // Increase filled slots
      filledSlots.release();
    }

  }

}
