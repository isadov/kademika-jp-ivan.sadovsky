package day12.killing_thread;

public class SelfDestroyerDemo {

    public static void main(String[] args) throws Exception{
        SelfDestroyer selfDestroyer = new SelfDestroyer();
        selfDestroyer.start();

        Thread.currentThread().sleep(1000);

//        selfDestroyer.killMyself();
        selfDestroyer.interrupt();
        
    }
}
