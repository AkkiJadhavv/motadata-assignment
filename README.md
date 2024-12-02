# Message Queue Application

## Overview
This is a simple message-driven producer-consumer application implemented in core Java. It uses a custom message queue to simulate message production and consumption.

## Features
- Producer thread generates messages and adds them to a queue.
- Consumer thread processes messages and logs success or failure.
- Tracks total messages processed successfully and errors encountered.
- Includes unit tests to verify functionality.

## Prerequisites
- Java 8 or later
- JUnit 5 (for tests)

## Instructions

### Compile
```bash
javac -d out src/main/java/com/example/messagequeue/*.java
```

### Run
```bash
java -cp out com.example.messagequeue.MessageQueueApp
```

### Run Tests
```bash
javac -cp <path-to-junit-jar> -d out src/test/java/com/example/messagequeue/*.java
java -cp out:<path-to-junit-jar> org.junit.platform.console.ConsoleLauncher --scan-classpath
```

---
