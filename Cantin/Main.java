import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static private ArrayList<Integer> ordIds = new ArrayList<>();
    static private HashMap<Integer, String> emIds = new HashMap<>();
    static private HashMap<Integer, int[]> foodIds = new HashMap<>();
    static private HashMap<Integer, int[]> foodCounts = new HashMap<>();
    static private HashMap<Integer, int[]> prepTimes = new HashMap<>();
    static private HashMap<Integer, String> overallStatus = new HashMap<>();
    static private HashMap<String, Integer> chefs = new HashMap<>();

    public static HashMap<Integer, Foods> createFoodObject() {
        HashMap<Integer, Foods> foods = new HashMap<>();

        foods.put(1, new Foods(1, "Pizza", 3, 10));
        foods.put(2, new Foods(2, "Cheese Burger", 2, 5));
        foods.put(3, new Foods(3, "Sandwich", 5, 20));
        foods.put(4, new Foods(4, "Muffin", 3, 15));
        foods.put(5, new Foods(5, "Taco", 5, 20));
        foods.put(6, new Foods(6, "Hotdog", 3, 10));

        chefs.put("chef 1", 0);
        chefs.put("chef 2", 0);
        chefs.put("chef 3", 0);
        chefs.put("chef 4", 0);
        chefs.put("chef 5", 0);

        return foods;
    }

    public static int[] convertStrToInt(String[] s) {
        int[] sInt = new int[s.length];

        for (int i = 0; i < s.length; i++)
            sInt[i] = Integer.parseInt(s[i]);

        return sInt;
    }

    public static void findPrepTime(int orderNo, int[] oId, int[] oCount,
                                    HashMap<Integer, Foods> foods,
                                    String empId) {
        Scanner sc = new Scanner(System.in);

        int TotalPrepTime = 0;
        int[] prepTime = new int[oId.length];

        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < oId.length; i++) {
            int foodId = oId[i];
            int foodCount = oCount[i];
            String foodName = foods.get(foodId).fName;

            int foodPrepTime = foods.get(foodId).fPrepTIme;
            int foodMaxCount = foods.get(foodId).fMaxCount;
            int foodWaitTime = foods.get(foodId).waitTime;

            if (foodCount % foodMaxCount == 0) {
                TotalPrepTime +=
                        foodWaitTime + (foodCount / foodMaxCount) * foodPrepTime;
                prepTime[i] =
                        foodWaitTime + (foodCount / foodMaxCount) * foodPrepTime;

                System.out.println("--> Food Prepartion time for " + foodName +
                        " - " +
                        (foodCount / foodMaxCount) * foodPrepTime);
                System.out.println("--> Waiting Time for " + foodName + " - " +
                        foodWaitTime);

                System.out.println();
            } else {
                TotalPrepTime +=
                        foodWaitTime + ((foodCount / foodMaxCount) + 1) * foodPrepTime;
                prepTime[i] =
                        foodWaitTime + ((foodCount / foodMaxCount) + 1) * foodPrepTime;

                System.out.println("--> Food Prepartion time for " + foodName +
                        " - " + ((foodCount / foodMaxCount) +
                        1) * foodPrepTime);
                System.out.println("--> Waiting Time for " + foodName + " - " +
                        foodWaitTime);

                System.out.println();
            }
        }
        System.out.println("Total Preparation Time is " + TotalPrepTime);

        System.out.println("-----------------------------------------------------------------------------------------------------------");

        System.out.printf("Do you want Continue this Order (Yes - 1 / No - 2)");
        int option = sc.nextInt();

        if (option == 1) {
            dataEntry(oId, prepTime, oCount, foods, "on-Progess", orderNo, empId);
            placeOrder(oId, prepTime, foods, orderNo);
            System.out.printf("\nYour Order was Placed Successful, Order ID : %d\n", orderNo);
        } else {
            dataEntry(oId, prepTime, oCount, foods, "Pending", orderNo, empId);
            System.out.printf("\nYour Order Cancel Successful - Order No : %d\n", orderNo);
        }
    }

    public static void placeOrder(int[] oId, int[] prepTime, HashMap<Integer, Foods> foods, int orderNo) {
        for (int i = 0; i < oId.length; i++)
            foods.get(oId[i]).waitTime += prepTime[i];

        for (String c : chefs.keySet())
            if (chefs.get(c) == 0) {
                chefs.put(c, orderNo);
                System.out.printf("%s is assigned for order No %d", c, orderNo);
                break;
            }
    }

    public static void orderComplete(int orderNo, HashMap<Integer, Foods> foods) {
        for (int i = 0; foodIds.get(orderNo).length > i; i++)
            foods.get(foodIds.get(orderNo)[i]).waitTime -= prepTimes.get(orderNo)[i];

        for (String c : chefs.keySet())
            if (chefs.get(c) == orderNo) {
                chefs.put(c, 0);
                break;
            }

        overallStatus.replace(orderNo, "Complete");
    }

    public static void dataEntry(int[] oId, int[] prepTime, int[] oCount,HashMap<Integer, Foods> foods,String orderStatus, int orderNo, String empId) {
        ordIds.add(orderNo);

        emIds.put(orderNo, empId);
        foodIds.put(orderNo, oId);
        foodCounts.put(orderNo, oCount);
        prepTimes.put(orderNo, prepTime);
        overallStatus.put(orderNo, orderStatus);
    }

    public static void chefAvailability() {
        System.out.println();
        System.out.println("chef      Status ");
        System.out.println("-----------------");
        for (String c : chefs.keySet())
            if (chefs.get(c) == 0)
                System.out.println(c + "    Available");
            else
                System.out.println(c + "    Busy");

        System.out.println("-----------------");
        System.out.println();
    }

    public static void printMenu(HashMap<Integer, Foods> foods) {
        System.out.println("Order_No       Food_Name     Max_Count      Prep_Time");
        System.out.println("----------------------------------------------------------------");

        for (Foods f : foods.values())
            System.out.printf("%d %s %d %d\n", f.fId, f.fName, f.fMaxCount, f.fPrepTIme);

        System.out.println("----------------------------------------------------------------");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Foods> foods = createFoodObject();

        int orderNo = 1;

        while (true) {
            System.out.printf("\n1 - Employee Order \n2 - Menu \n3 - Admin Panel \nEnter Option : ");
            int mainMenuOpt = sc.nextInt();

            switch (mainMenuOpt) {
                case 1:
                    if (!(chefs.get("chef 1") == 0 || chefs.get("chef 2") == 0 || chefs.get("chef 3") == 0 || chefs.get("chef 4") == 0 || chefs.get("chef 4") == 0)) 
                    {
                        System.out.println("Chefs are not Available");
                        break;
                    }
                    
                    System.out.printf("Enter Employee ID : ");
                    String empId = sc.next();

                    sc.nextLine();
                    System.out.printf("Enter Food ID by Space : ");
                    String orderId = sc.nextLine();

                    System.out.printf("Enter Food count by Space :");
                    String orderCount = sc.nextLine();

                    int[] oId = convertStrToInt(orderId.split(" "));
                    int[] oCount = convertStrToInt(orderCount.split(" "));

                    findPrepTime(orderNo, oId, oCount, foods, empId);
                    orderNo += 1;

                    break;

                case 2:
                    printMenu(foods);
                    break;

                case 3:
                    System.out.printf("\n1 - Chefs Availability \n2 - Order History \n3 - Complete Order \n4 - Back to Main Menu \nEnter your Option : ");
                    int adminOption = sc.nextInt();
                    switch (adminOption) {
                        case 1:
                            chefAvailability();
                            break;

                        case 2:
                            System.out.println("ORDER ID         EMPLOYEE ID        FOODS         FOODS COUNT       PREP TIME        STATUS");
                            System.out.println("-------------------------------------------------------------------------------------------------");
                            for (int i : ordIds)
                                System.out.println(i + "      " + emIds.get(i) + "    " + Arrays.toString(foodIds.get(i)) + "    " + Arrays.toString(foodCounts.get(i)) + "    " + Arrays.toString(prepTimes.get(i)) + "    " + overallStatus.get(i));

                            System.out.println("-------------------------------------------------------------------------------------------------");
                            break;

                        case 3:
                            System.out.println("Enter Order No");
                            int completeOrderNo = sc.nextInt();
                            orderComplete(completeOrderNo, foods);
                            break;

                        case 4:
                            printMenu(foods);
                            break;
                    }
            } // switch
        } // while
    }
}
