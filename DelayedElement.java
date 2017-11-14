package com.continental.utility;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed{
	private long duration = 0;
    private String message;

    public DelayedElement(long duration, String name) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = name;
    }

    @Override
    public int compareTo(Delayed o) {

        return (int) (this.duration - ((DelayedElement) o).getDuration());
    }

    @Override
    /*
     * Expiration occurs when an element's getDelay(TimeUnit unit) method
     * returns a value less than or equal to zero.
     */
    public long getDelay(TimeUnit unit) {
        long diff = duration - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayedElement [duration=" + duration + ", message=" + message
                + "]";
    }

}
