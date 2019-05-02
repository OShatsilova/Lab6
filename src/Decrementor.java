import java.util.Random;

/**
 * Created by EGAR on 02.05.2019.
 */
public class Decrementor extends Thread {

    public Thread other;
    public int local;

    @Override
    public void run() {
        try {
            local = 0;
            System.out.println("Decrementor start");
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                if (Thread.interrupted()) {
                    System.out.println("Decrementor gonna stop");
                    return;
                }
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupted status
                }
                synchronized (MultiThreadRaceConditionMain.count) {
                    MultiThreadRaceConditionMain.count--;
                }
                local++;
            }

            if (other.isAlive()) {
                other.interrupt();
                System.out.println("Decrementor wins!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
