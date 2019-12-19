import java.util.Scanner;

enum CoffeeMachineState {
    MAIN,
    BUY,
    FILL_MENU_WATER,
    FILL_MENU_MILK,
    FILL_MENU_BEANS,
    FILL_MENU_CUPS,
    EXIT
}

public class CoffeeMachine {
    private int amountWater;
    private int amountMilk;
    private int amountCoffeeBeans;
    private int amountCups;
    private int amountMoney;
    private CoffeeMachineState state;

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        coffeeMachine.scan();
    }

    public CoffeeMachine(int amountWater, int amountMilk, int amountCoffeeBeans, int amountCups, int amountMoney) {
        this.amountWater = amountWater;
        this.amountMilk = amountMilk;
        this.amountCoffeeBeans = amountCoffeeBeans;
        this.amountCups = amountCups;
        this.amountMoney = amountMoney;
        this.state = CoffeeMachineState.MAIN;
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);

        do {
            dialogMainMenu(scanner);
        } while(state != CoffeeMachineState.EXIT);
    }

    private void process(String action) {
        switch (state) {
            case MAIN:
                switch (action) {
                    case "buy":
                        state = CoffeeMachineState.BUY;
                        break;
                    case "fill":
                        state = CoffeeMachineState.FILL_MENU_WATER;
                        break;
                    case "take":
                        takeMoney();
                        break;
                    case "remaining":
                        printAmounts();
                        break;
                    case "exit":
                        state = CoffeeMachineState.EXIT;
                        break;
                    default:
                        break;
                }
                break;
            case BUY:
                switch (action) {
                    case "1":
                        buyEspresso();
                        state = CoffeeMachineState.MAIN;
                        break;
                    case "2":
                        buyLatte();
                        state = CoffeeMachineState.MAIN;
                        break;
                    case "3":
                        buyCappuccino();
                        state = CoffeeMachineState.MAIN;
                        break;
                    case "back":
                        state = CoffeeMachineState.MAIN;
                        break;
                }
                break;
            case FILL_MENU_WATER:
                amountWater += Integer.parseInt(action);
                state = CoffeeMachineState.FILL_MENU_MILK;
                break;
            case FILL_MENU_MILK:
                amountMilk += Integer.parseInt(action);
                state = CoffeeMachineState.FILL_MENU_BEANS;
                break;
            case FILL_MENU_BEANS:
                amountCoffeeBeans += Integer.parseInt(action);
                state = CoffeeMachineState.FILL_MENU_CUPS;
                break;
            case FILL_MENU_CUPS:
                amountCups += Integer.parseInt(action);
                state = CoffeeMachineState.MAIN;
                break;
        }
    }

    private void dialogMainMenu(Scanner scanner) {
        String action;

        switch(state) {
            case MAIN:
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                System.out.print("> ");
                action = scanner.next();
                process(action);
                break;
            case BUY:
                action = dialogBuyMenu(scanner);
                process(action);
                break;
            case FILL_MENU_WATER:
                action = dialogFillMenuWater(scanner);
                process(action);
                break;
            case FILL_MENU_MILK:
                action = dialogFillMenuMilk(scanner);
                process(action);
                break;
            case FILL_MENU_BEANS:
                action = dialogFillMenuBeans(scanner);
                process(action);
                break;
            case FILL_MENU_CUPS:
                action = dialogFillMenuCups(scanner);
                process(action);
                break;
        }
    }

    private String dialogBuyMenu(Scanner scanner) {
        String action;

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        System.out.print("> ");
        action = scanner.next();

        return action;
    }

    private String dialogFillMenuWater(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add: ");
        System.out.print("> ");
        return scanner.next();
    }

    private String dialogFillMenuMilk(Scanner scanner) {
        System.out.println("Write how many ml of milk do you want to add: ");
        System.out.print("> ");
        return scanner.next();
    }

    private String dialogFillMenuBeans(Scanner scanner) {
        System.out.println("Write how many grams of coffee beans do you want to add");
        System.out.print("> ");
        return scanner.next();
    }

    private String dialogFillMenuCups(Scanner scanner) {
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        System.out.print("> ");
        return scanner.next();
    }

    private void printAmounts() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(amountWater + " of water");
        System.out.println(amountMilk + " of milk");
        System.out.println(amountCoffeeBeans + " of coffee beans");
        System.out.println(amountCups + " of disposable cups");
        System.out.println(amountMoney + " of money");
    }

    private void buyLatte() {
        if(amountWater < 350) {
            System.out.println("Sorry, not enough water!");
        } else if(amountMilk < 75) {
            System.out.println("Sorry, not enough milk!");
        } else if(amountCoffeeBeans < 20) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if(amountCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            amountWater -= 350;
            amountMilk -= 75;
            amountCoffeeBeans -= 20;
            amountCups -= 1;
            amountMoney += 7;
        }
    }

    private void buyEspresso() {
        if(amountWater < 250) {
            System.out.println("Sorry, not enough water!");
        } else if(amountCoffeeBeans < 16) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if(amountCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            amountWater -= 250;
            amountCoffeeBeans -= 16;
            amountCups -= 1;
            amountMoney += 4;
        }
    }

    private void buyCappuccino() {
        if(amountWater < 200) {
            System.out.println("Sorry, not enough water!");
        } else if(amountMilk < 100) {
            System.out.println("Sorry, not enough milk!");
        } else if(amountCoffeeBeans < 12) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if(amountCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            amountWater -= 200;
            amountMilk -= 100;
            amountCoffeeBeans -= 12;
            amountCups -= 1;
            amountMoney += 6;
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + amountMoney);
        amountMoney = 0;
    }
}
