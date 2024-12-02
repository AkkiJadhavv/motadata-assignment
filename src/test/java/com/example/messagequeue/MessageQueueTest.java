package com.example.messagequeue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageQueueTest {
    @Test
    public void testSuccessfulProcessing() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        queue.produce("Message-1");
        queue.produce("Message-2");

        Thread.sleep(500);
        consumer.stop();
        consumerThread.join();

        assertEquals(2, consumer.getSuccessfulCount());
        assertEquals(0, consumer.getErrorCount());
    }

    @Test
    public void testErrorProcessing() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        queue.produce("Message-5");
        queue.produce("Message-6");

        Thread.sleep(500);
        consumer.stop();
        consumerThread.join();

        assertEquals(1, consumer.getSuccessfulCount());
        assertEquals(1, consumer.getErrorCount());
    }
}
