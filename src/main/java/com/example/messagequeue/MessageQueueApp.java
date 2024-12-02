package com.example.messagequeue;

public class MessageQueueApp {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producerThread.start();
        consumerThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.stop();
        consumer.stop();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Final Stats ---");
        System.out.println("Total messages processed successfully: " + consumer.getSuccessfulCount());
        System.out.println("Total errors encountered: " + consumer.getErrorCount());
    }
}
