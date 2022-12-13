package operating_system.producer_consumer.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

  public static void main(String[] args) {

    Queue<FoodItem> belt = new ConcurrentLinkedDeque<>();
    int maxSize = 20;

    Lock lock = new ReentrantLock();

    Producer p1 = new Producer("p1", belt, maxSize, lock);
    Producer p2 = new Producer("p2", belt, maxSize, lock);
    Producer p3 = new Producer("p3", belt, maxSize, lock);
    Producer p4 = new Producer("p4", belt, maxSize, lock);

    Consumer c1 = new Consumer("c1", belt, lock);
    Consumer c2 = new Consumer("c2", belt, lock);
    Consumer c3 = new Consumer("c3", belt, lock);
    Consumer c4 = new Consumer("c4", belt, lock);

    new Thread(p1).start();
    new Thread(p2).start();
    new Thread(p3).start();
    new Thread(p4).start();

    new Thread(c1).start();
    new Thread(c2).start();
    new Thread(c3).start();
    new Thread(c4).start();
  }

}

// Want to have multiple food items
// Producer will add to the belt
// Consumer will remove from the belt
// Prioritize older items first