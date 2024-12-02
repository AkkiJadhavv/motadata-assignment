package com.example.messagequeue;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private final MessageQueue queue;
    private volatile boolean running = true;
    private final AtomicInteger successfulCount = new AtomicInteger();
    private final AtomicInteger errorCount = new AtomicInteger();

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String message = queue.consume();
                System.out.println("Consumed: " + message);
                if (message.contains("5")) {
                    throw new Exception("Simulated error");
                }
                successfulCount.incrementAndGet();
            } catch (Exception e) {
                errorCount.incrementAndGet();
                System.err.println("Error processing message: " + e.getMessage());
            }
        }
    }

    public void stop() {
        running = false;
    }

    public int getSuccessfulCount() {
        return successfulCount.get();
    }

    public int getErrorCount() {
        return errorCount.get();
    }
}
