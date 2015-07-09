package day12.killing_thread;

public class SelfDestroyer extends Thread {

    private volatile boolean killMe = false;    // vse vremia bydem rabotat' s samoi svegei versiei etoi peremennoi
                                                // volatile -- vse peremenue ne bydyt keshyrovatsa
                                                // kak tolko killMe v killMyself izmenitsa na true to ona srazy
                                                // ge nepezapishetsa v volatile (5)

    public void killMyself() {
        killMe = true;
    }

    @Override
    public void run() {
        while (!killMe) {
            try {
                System.out.println("Enjoying life..");
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Life is cool...(Dying)");
    }
}
