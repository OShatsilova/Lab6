/**
 * Created by EGAR on 02.05.2019.
 */
public class MultiThreadRaceConditionMain {

    public static Integer count;

    public static void main(String[] args) throws InterruptedException {

        count = 0;
        Decrementor t1 = new Decrementor();
        Incrementor t2 = new Incrementor();

        t1.other = t2;
        t2.other = t1;

        long start = System.currentTimeMillis();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count = " + count);
        System.out.println("Decrementor local = "+t1.local);
        System.out.println("Incrementor local = "+t2.local);
    }
}
