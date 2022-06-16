import java.util.concurrent.atomic.AtomicInteger;

public class Timer implements Runnable {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                atomicInteger.incrementAndGet();
            } catch (Exception e) {}
        }
    }

    public Integer getTime() {
        return atomicInteger.get();
    }
}
