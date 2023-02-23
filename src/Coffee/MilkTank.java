package Coffee;

/**
 * All levels are measured in ml.
 */
public class MilkTank {
    private final int maxCapacity;
    private final int minCapacity;
    private int milkQuantity;

    public MilkTank(int maxCapacity, int minCapacity, int milkQuantity) {
        if (maxCapacity < 0 || minCapacity < 0 || milkQuantity < 0) {
            throw new IllegalArgumentException("Invalid input argument: negative value");
        }
        if (minCapacity > maxCapacity) {
            throw new IllegalArgumentException("Invalid input argument: minCapacity greater than maxCapacity");
        }
        if (milkQuantity < minCapacity || milkQuantity > maxCapacity) {
            throw new IllegalArgumentException("Invalid input argument: milkQuantity outside the range [minCapacity, maxCapacity]");
        }
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.milkQuantity = milkQuantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }


    public int getMinCapacity() {
        return minCapacity;
    }


    public int getAvailableMilkQuantity() {
        return milkQuantity - minCapacity;
    }

    public void addMilk(int milk){
        if(milk < 0){
            throw new IllegalArgumentException("Invalid value of milk : negative value");
        }
        if(milkQuantity + milk > maxCapacity){
            throw new MaxCapacityReachedException("Milk maximum capacity reached");
        }
        milkQuantity = milkQuantity + milk;
    }

    public int removeMilkFromTank(int milk){
        if(milk < 0){
            throw new IllegalArgumentException("Invalid value of milk : negative value");
        }
        if(milkQuantity - milk < minCapacity){
            throw new MinCapacityReachedException("Milk tank is empty");
        }
        milkQuantity = milkQuantity - milk;
        return milk;
    }
    @Override
    public String toString() {
        return "MilkTank{" +
                "maxCapacity=" + maxCapacity +
                ", minCapacity=" + minCapacity +
                ", milkQuantity=" + milkQuantity +
                '}';
    }
}
