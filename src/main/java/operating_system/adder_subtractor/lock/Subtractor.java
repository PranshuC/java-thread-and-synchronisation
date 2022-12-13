package operating_system.adder_subtractor.lock;

import java.util.concurrent.locks.Lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Subtractor implements Runnable {

  private Count count;
  private Lock lock;

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      // Acquire the lock
      lock.lock();

      count.value--;

      // Release the lock
      lock.unlock();
    }
  }
}