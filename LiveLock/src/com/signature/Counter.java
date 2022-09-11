package com.signature;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public void decrement() {
        count.decrementAndGet();
    }

    public void getValue() {
        System.out.println(count.get());
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.increment();
        counter.getValue();
        counter.increment();
        counter.getValue();
        counter.decrement();
        counter.getValue();
    }
}
