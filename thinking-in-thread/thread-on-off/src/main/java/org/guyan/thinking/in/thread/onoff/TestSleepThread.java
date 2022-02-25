package org.guyan.thinking.in.thread.onoff;

public class TestSleepThread {

    public static void main(String[] args) {
        Runner1 r = new Runner1();
        r.start();
        long time = System.currentTimeMillis();
        try {
            r.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - time) / 1000);

        for (int i = 0; i < 3; i++) {
            System.out.println("Main" + i);
        }
    }

}

class Runner1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Runner1" + i);
        }
    }
}