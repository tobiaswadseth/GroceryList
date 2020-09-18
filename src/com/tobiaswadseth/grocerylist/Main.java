package com.tobiaswadseth.grocerylist;

import com.tobiaswadseth.grocerylist.categories.*;

import java.util.Scanner;

/*
    TODO: ADD DESCRIPTIVE COMMENTS
 */

public class Main {

    private static Scanner scanner;
    private static boolean open;
    private static GroceryList groceryList;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        open = true;
        groceryList = new GroceryList();

        while (open) {
            mainMenu();
        }

        scanner.close();
    }

    private static void mainMenu() {
        System.out.println("Please choose action!");
        System.out.println("\t1 - View Items");
        System.out.println("\t2 - Add Item");
        System.out.println("\t3 - Remove Item");
        System.out.println("\t4 - Edit Item");
        System.out.println("\t5 - Exit");

        String input = scanner.nextLine();

        if (isInt(input)) {
            int choice = Integer.parseInt(input);
            if (choice == 1) {
                viewItems();
            } else if (choice == 2) {
                addItem();
            } else if (choice == 3) {
                viewItems();
                if (groceryList.getItemList().isEmpty()) {
                    System.out.println("There are not products to edit!");
                } else {
                    removeItem();
                }
            } else if (choice == 4) {
                viewItems();
                if (groceryList.getItemList().isEmpty()) {
                    System.out.println("There are not products to edit!");
                } else {
                    editItem();
                }
            } else if (choice == 5) {
                open = false;
                System.out.println("\nQuitting program!");
                return;
            } else {
                mainMenu();
            }
            System.out.println("\nPress enter to return to the Main Menu!");
            scanner.nextLine();
        }
    }

    private static void editItem() {
        System.out.println("\nPlease enter the ID of the product you would like to edit!");
        String indexS = scanner.nextLine();

        if (isInt(indexS)) {
            int index = Integer.parseInt(indexS);
            if (index > 0 && index < groceryList.getItemList().size() + 1) {
                Item item = groceryList.getItemList().get(index - 1);
                String itemInfo = getItemInfo(item, false);
                System.out.println(itemInfo);
                System.out.println("\nPlease choose action!");
                System.out.println("\t1 - Edit priority");
                System.out.println("\t2 - Edit amount");
                System.out.println("\t3 - Edit bought state");

                String input = scanner.nextLine();

                if (isInt(input)) {
                    int choice = Integer.parseInt(input);
                    if (choice == 1) {
                        // Edit priority
                        System.out.println("\nPlease enter the priority");
                        System.out.println("Low, Medium, High");
                        String priorityString;
                        Priority priority = null;
                        while (priority == null) {
                            priorityString = scanner.nextLine();
                            if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                                priority = Priority.valueOf(priorityString.toUpperCase());
                            } else {
                                System.out.println("That is not a valid priority\n");
                                System.out.println("\nPlease enter the priority");
                                System.out.println("Low, Medium, High");
                            }
                        }
                        item.setPriority(priority);
                        System.out.println("Successfully updated product!");
                        return;
                    } else if (choice == 2) {
                        // Edit amount
                        System.out.println("\nPlease enter the amount");
                        String amount = scanner.nextLine();
                        item.setAmount(amount);
                        System.out.println("Successfully updated product!");
                        return;
                    } else if (choice == 3) {
                        item.setBought(!item.isBought());
                        System.out.println("Successfully updated product!");
                        return;
                    }
                }
                System.out.println("That is not a valid action!");
                System.out.println("Press enter to return to the edit menu!");
                scanner.nextLine();
            }
        }

        System.out.println("That is not a valid ID!");
        editItem();
    }

    private static void removeItem() {
        System.out.println("\nPlease enter the ID of the product you would like to remove!");
        String indexS = scanner.nextLine();

        if (isInt(indexS)) {
            int index = Integer.parseInt(indexS);
            if (index > 0 && index < groceryList.getItemList().size() + 1) {
                groceryList.removeItem(index - 1);
                System.out.println("Successfully removed product with ID " + index);
                return;
            }
        }

        System.out.println("That is not a valid ID!");
        removeItem();
    }

    private static void viewItems() {
        if (groceryList.getItemList().isEmpty()) {
            System.out.println("\nThe Grocery List is empty!\n");
        }
        groceryList.getItemList().forEach(item -> System.out.println(getItemInfo(item, true)));
    }

    private static String getItemInfo(Item item, boolean index) {
        String category = "";
        if (item instanceof Dairy) {
            category = "🧀";
        } else if (item instanceof Egg) {
            category = "🥚";
        } else if (item instanceof Fish) {
            category = "🐟";
        } else if (item instanceof Fruit) {
            category = "🍎";
        } else if (item instanceof Meat) {
            category = "🥩";
        } else if (item instanceof Vegetable) {
            category = "🥬";
        }
        if (index) {
            return "----\nID: " + (groceryList.getItemList().indexOf(item) + 1) + "\nName: " + item.getItem() + " | Category: " + category + "\nAmount: " + item.getAmount() + " | Priority: " + item.getPriority() + "\nBought: " + (item.isBought() ? "Yes" : "No") + "\n----\n";
        }
        return "----\nName: " + item.getItem() + " | Category: " + category + "\nAmount: " + item.getAmount() + " | Priority: " + item.getPriority() + "\n ought: " + (item.isBought() ? "Yes" : "No") + "\n----\n";
    }

    private static void addItem() {
        System.out.println("\nPlease choose category!");
        System.out.println("Dairy, Egg, Fish, Fruit, Meat, Vegetable");
        String cat = scanner.nextLine();
        if (cat.equalsIgnoreCase("Dairy")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Dairy(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new dairy product!\n");
        } else if (cat.equalsIgnoreCase("Egg")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Egg(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new egg product!\n");
        } else if (cat.equalsIgnoreCase("Fish")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Fish(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new fish product!\n");
        } else if (cat.equalsIgnoreCase("Fruit")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Fruit(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new fruit product!\n");
        } else if (cat.equalsIgnoreCase("Meat")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Meat(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new meat product!\n");
        } else if (cat.equalsIgnoreCase("Vegetable")) {
            System.out.println("\nPlease enter the name of the item!");
            String name = scanner.nextLine();
            System.out.println("\nPlease enter the amount");
            String amount = scanner.nextLine();
            System.out.println("\nPlease enter the priority");
            System.out.println("Low, Medium, High");
            String priorityString;
            Priority priority = null;
            while (priority == null) {
                priorityString = scanner.nextLine();
                if (priorityString.equalsIgnoreCase("low") || priorityString.equalsIgnoreCase("medium") || priorityString.equalsIgnoreCase("high")) {
                    priority = Priority.valueOf(priorityString.toUpperCase());
                } else {
                    System.out.println("That is not a valid priority\n");
                    System.out.println("\nPlease enter the priority");
                    System.out.println("Low, Medium, High");
                }
            }
            Item item = new Vegetable(name, amount, priority);
            groceryList.addItem(item);
            System.out.println("\nSuccessfully added a new vegetable product!\n");
        }
    }

    private static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
