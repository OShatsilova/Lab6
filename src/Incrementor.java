import java.util.Random;

/**
 * Created by EGAR on 02.05.2019.
 */
public class Incrementor extends Thread {

    public Thread other;
    public int local;

    @Override
    public void run() {
        try {
            local = 0;
            System.out.println("Incrementor start");
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                if (Thread.interrupted()) {
                    System.out.println("Incrementor gonna stop");
                    return;
                }
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupted status
                }
                synchronized (MultiThreadRaceConditionMain.count) {
                    MultiThreadRaceConditionMain.count++;
                }
                local++;
            }

            if (other.isAlive()) {
                other.interrupt();
                System.out.println("Incrementor wins!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
