package com.example.messagequeue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final int capacity = 10;

    public synchronized void produce(String message) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(message);
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        String message = queue.poll();
        notifyAll();
        return message;
    }
}
