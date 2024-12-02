package com.example.messagequeue;

public class Producer implements Runnable {
    private final MessageQueue queue;
    private volatile boolean running = true;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int count = 0;
        while (running) {
            try {
                String message = "Message-" + (++count);
                queue.produce(message);
                System.out.println("Produced: " + message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
