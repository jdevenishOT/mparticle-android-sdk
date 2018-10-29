package com.mparticle.testutils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MPLatch extends CountDownLatch {
    int countDowned = 0;
    int count;
    Handler mHandler = new Handler(Looper.getMainLooper());
    Runnable timeoutRunnable = new Runnable() {
        @Override
        public void run() {
            throw new RuntimeException("Timed Out");
        }
    };

    /**
     * Constructs a {@code CountDownLatch} initialized with the given count.
     *
     * @param count the number of times {@link #countDown} must be invoked
     *              before threads can pass through {@link #await}
     * @throws IllegalArgumentException if {@code count} is negative
     */
    public MPLatch(int count) {
        super(count);
        this.count = count;
    }

    @Override
    public void countDown() {
        countDowned++;
        if (countDowned == count) {
            mHandler.removeCallbacks(timeoutRunnable);
        }
        super.countDown();
    }

    @Override
    public void await() throws InterruptedException {
        int timeoutTimeMs = 2 * 1000;
        if (count == countDowned) {
            return;
        }
        mHandler.postDelayed(timeoutRunnable, timeoutTimeMs);
        this.await(timeoutTimeMs, TimeUnit.MILLISECONDS);
    }
}
