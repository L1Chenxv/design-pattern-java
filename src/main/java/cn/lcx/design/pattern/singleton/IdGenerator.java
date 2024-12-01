package cn.lcx.design.pattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator {
    INSTANCE,
    SINGLE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
