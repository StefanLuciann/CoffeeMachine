package Coffee;

import java.util.Map;

/**
 * Write an application that will simulate a coffee making machine.
 * In the event that any coffee brewing service finds an empty water tank,
 * the thread should stop.
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        CoffeeMachine coffeeMachine = new CoffeeMachine(
                new WaterTank(2000, 100, 2000),
                new MilkTank(2000, 50, 2000),
                Map.of(
                        CoffeeBeanType.ARABICA, new CoffeeTank(500, CoffeeBeanType.ARABICA, 500),
                        CoffeeBeanType.COLD_BREW, new CoffeeTank(500, CoffeeBeanType.COLD_BREW, 500),
                        CoffeeBeanType.ROBUSTA, new CoffeeTank(500, CoffeeBeanType.ROBUSTA, 500),
                        CoffeeBeanType.BLONDE_ROAST, new CoffeeTank(500, CoffeeBeanType.BLONDE_ROAST, 500)
                ),
                new CoffeeGrinder(2),
                new LiquidHeater(10)
        );

        for (int i = 1; i < 2; i++) {

            coffeeMachine.makeCoffee(CoffeeType.RISTRETTO);
            coffeeMachine.makeCoffee(CoffeeType.ESPRESSO);
            coffeeMachine.makeCoffee(CoffeeType.LATTE_MACCHIATO);
            coffeeMachine.makeCoffee(CoffeeType.CAPPUCINO);


            Thread.sleep(5000);


        }



    }

}

