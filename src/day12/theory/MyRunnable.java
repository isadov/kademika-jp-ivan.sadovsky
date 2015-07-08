package day12.theory;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Running " + Thread.currentThread().getName() + " " + this.getClass().getSimpleName());

    }
}
