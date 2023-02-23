package Coffee;

public class LiquidHeater {
    private final int speedInMillilitersPerSecond;

    public LiquidHeater(int speedInMillilitersPerSecond) {
        this.speedInMillilitersPerSecond = speedInMillilitersPerSecond;
    }

    public synchronized int heat(int liquidQuantity, int temperature) {
        //TODO  add validation on input data, and for the temperature to bee between 80 <> 100
        if (temperature < 60 || temperature > 100) {
            throw new IllegalArgumentException("Temperature must be between 80 and 100 degrees Celsius");
        }

        int waitingTimeInMillis = liquidQuantity / speedInMillilitersPerSecond * 1000;
        waitingTimeInMillis = waitingTimeInMillis + (waitingTimeInMillis / 100 * temperature);
        System.out.println("Liquid heater will start heating " + liquidQuantity + " ml of liquid");
        try {
            Thread.sleep(waitingTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Liquid heater job finished in " + waitingTimeInMillis+" seconds");
        return liquidQuantity;
    }
}