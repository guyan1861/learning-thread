package org.guyan.thinking.in.thread.interrupt;

public class TestInterrupt {

    public static void main(String[] args) {
        Thread th = new Thread();
        while (true) {
            if (th.isInterrupted()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        th.interrupt();
    }

}
