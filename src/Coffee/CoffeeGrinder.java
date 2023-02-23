package Coffee;

public class CoffeeGrinder {
    private final int speedInGramsPerSeconds;

    public CoffeeGrinder(int speedInGramsPerSeconds) {
        this.speedInGramsPerSeconds = speedInGramsPerSeconds;
    }
    public synchronized int grind(int coffeeQuantity, int granularity){
        if (coffeeQuantity <= 0) {
            throw new IllegalArgumentException("Invalid value of coffee quantity: " + coffeeQuantity);
        }
        if (granularity < 1 || granularity > 10) {
            throw new IllegalArgumentException("Invalid value of granularity: " + granularity);
        }

        int waitingTimeInMillis = coffeeQuantity / speedInGramsPerSeconds * 1000;
        waitingTimeInMillis = waitingTimeInMillis + (waitingTimeInMillis / 100 * granularity);
        System.out.println("Coffee grinder will start grinding " + coffeeQuantity + " grams of coffee");
        try {
            Thread.sleep(waitingTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Coffee grinder job finished in " + waitingTimeInMillis + " seconds");
        return coffeeQuantity;
    }
}
