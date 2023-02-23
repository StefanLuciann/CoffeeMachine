package Coffee;

public class CoffeeTank {
    private final int maxCapacity;
    private final CoffeeBeanType coffeeBeanType;
    private int coffeeQuantity;

    public CoffeeTank(int maxCapacity, CoffeeBeanType coffeeBeanType, int coffeeQuantity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero");
        }
        if (coffeeBeanType == null) {
            throw new IllegalArgumentException("Coffee bean type cannot be null");
        }
        if (coffeeQuantity < 0) {
            throw new IllegalArgumentException("Coffee quantity cannot be negative");
        }
        this.maxCapacity = maxCapacity;
        this.coffeeBeanType = coffeeBeanType;
        this.coffeeQuantity = coffeeQuantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public CoffeeBeanType getCoffeeType() {
        return coffeeBeanType;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void addCoffee(int coffee){
        if(coffee < 0){
            throw new IllegalArgumentException("Invalid value of coffee : negative value");
        }
        if(coffeeQuantity + coffee > maxCapacity){
            throw new MaxCapacityReachedException("Coffee maximum capacity reached");
        }
        coffeeQuantity = coffeeQuantity + coffee;
    }
    public int removeCoffeeFromTank(int coffee){
        if(coffee < 0){
            throw new IllegalArgumentException("Invalid value of coffee : negative value");
        }
        if(coffeeQuantity - coffee < 0){
            throw new MinCapacityReachedException("Coffe tank is empty");
        }
        coffeeQuantity = coffeeQuantity - coffee;
        return coffee;
    }

    @Override
    public String toString() {
        return "CoffeeTank{" +
                "maxCapacity=" + maxCapacity +
                ", coffeeType=" + coffeeBeanType +
                ", coffeeQuantity=" + coffeeQuantity +
                '}';
    }
}

