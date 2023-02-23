package Coffee;

import java.util.Map;

public class CoffeeMachine {
    private final WaterTank waterTank;
    private final MilkTank milkTank;
    private final Map<CoffeeBeanType, CoffeeTank> coffeeTanksByCoffeeType;
    private final CoffeeGrinder coffeeGrinder;
    private final LiquidHeater liquidHeater;

    public CoffeeMachine(WaterTank waterTank, MilkTank milkTank, Map<CoffeeBeanType, CoffeeTank> coffeeTanksByCoffeeType, CoffeeGrinder coffeeGrinder, LiquidHeater liquidHeater) {
        this.waterTank = waterTank;
        this.milkTank = milkTank;
        this.coffeeTanksByCoffeeType = coffeeTanksByCoffeeType;
        this.coffeeGrinder = coffeeGrinder;
        this.liquidHeater = liquidHeater;
    }

    public void refillWater(int water) {
        waterTank.addWater(water);
    }

    public void refillMilk(int milk) {
        milkTank.addMilk(milk);
    }

    public void refillCoffee(CoffeeBeanType coffeeType, int coffeeQuantity) {
        coffeeTanksByCoffeeType.get(coffeeType).addCoffee(coffeeQuantity);
    }

    public void makeCoffee(CoffeeType coffeeType) {
        Thread coffeeThread = new Thread(() -> {
            System.out.println("Order received for " + coffeeType);
            while (waterTank.getAvailableWaterQuantity() < coffeeType.getWaterQuantity()) {
                sleep();
            }
            int water = waterTank.removeWaterFromTank(coffeeType.getWaterQuantity());

            Integer milk = null;
            if (coffeeType.getMilkQuantity().isPresent()) {
                while (milkTank.getAvailableMilkQuantity() < coffeeType.getMilkQuantity().get()) {
                    sleep();
                }
                milk = milkTank.removeMilkFromTank(coffeeType.getMilkQuantity().get());
            }

            while (coffeeTanksByCoffeeType.get(coffeeType.getCoffeeBeanType()).getCoffeeQuantity() < coffeeType.getCoffeeQuantity()) {
                sleep();
            }
            int coffee = coffeeTanksByCoffeeType.get(coffeeType.getCoffeeBeanType()).removeCoffeeFromTank(coffeeType.getCoffeeQuantity());

            int grindingCoffee = coffeeGrinder.grind(coffee, coffeeType.getCoffeeGranularity());

            int heatedWater = liquidHeater.heat(water, coffeeType.getWaterTemperature());

            int madeCoffee = grindingCoffee + heatedWater;

            if (coffeeType.getMilkTemperature().isPresent()) {
                int heatedMilk = liquidHeater.heat(milk, coffeeType.getMilkTemperature().get());

                int coffeeWithMilk = madeCoffee + heatedMilk;
                System.out.println("Coffee is ready : " + coffeeType );
            } else {
                System.out.println("Coffee is ready : " + coffeeType );
            }


        });
        coffeeThread.start();
    }


    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
