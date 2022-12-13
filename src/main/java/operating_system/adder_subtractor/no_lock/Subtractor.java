package operating_system.adder_subtractor.no_lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Subtractor implements Runnable {

  private Count count;

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      count.value--;
    }
  }
}