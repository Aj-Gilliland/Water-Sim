import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Glass> glassList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int capacity = 0;
        int numOfGlasses = 0;

        System.out.print(""
                + "  |=|\n"
                + "  | |\n"
                + "  | |\n"
                + " /   \\\n"
                + ".     .\n"
                + "|-----|\n"
                + "|     |\n"
                + "|-----|\n"
                + "|_____|\n"
                + "=============\n"
                + "  |=|     |=|\n"
                + "  | |     | |\n"
                + "  | |     | |\n"
                + " /   \\   /   \\\n"
                + ".     . .     .\n"
                + "|-----| |-----|\n"
                + "|     | |     |\n"
                + "|-----| |-----|\n"
                + "|_____| |_____|\n"
                + "====================\n"
                + "  |=|     |=|    |=|\n"
                + "  | |     | |    | |\n"
                + "  | |     | |    | |\n"
                + " /   \\   /   \\  /   \\\n"
                + "|     | |     ||     |\n"
                + "|-----| |-----||-----|\n"
                + "|     | |     ||     |\n"
                + "|-----| |-----||-----|\n"
                + "|_____| |_____||_____|\n"
                + "============================\n");
        System.out.print("This program simulates filling glasses of water.\n");
        System.out.print("you can enter >Exit< to leave\n");
        while(true) {//work flow for getting initial values prt1 (capacity)
            try{
                System.out.print("\nHow big are your drinking glasses? (in oz)\n");
                String capacityInput = scanner.nextLine();
                if (capacityInput.equalsIgnoreCase("exit")) {
                    System.out.print("\n\n\nHave a nice day!");
                    scanner.close(); //this is here to prevent data leaks?
                    System.exit(0);
                }
                capacity = Integer.parseInt(capacityInput);
                if (capacity < 1){
                    System.out.print("Sorry positive whole numbers only.\n");
                    continue;
                }
                break;
            } catch (Exception e){
                System.out.print("Sorry positive whole numbers only.\n");
                scanner.nextLine();
            }
        }
        while(true) {//work flow for getting initial values pr2 (numOfGlasses)
            try{
                System.out.print("\nHow many drinking glasses do you have?\n");
                String numOfGlassesInput = scanner.nextLine();
                if (numOfGlassesInput.equalsIgnoreCase("exit")) {
                    System.out.print("\n\n\nHave a nice day!");
                    scanner.close(); //this is here to prevent data leaks?
                    System.exit(0);
                }
                numOfGlasses = Integer.parseInt(numOfGlassesInput);
                if (numOfGlasses < 1){
                    System.out.print("Sorry positive whole numbers only.\n");
                    continue;
                }
                break;
            } catch (Exception e){
                System.out.print("Sorry positive whole numbers only.\n");
                scanner.nextLine();
            }
        }
        for (int i = 1; i <= numOfGlasses; i++) {//makes all my Glass instances
            Glass glass = new Glass(capacity);
            glassList.add(glass);
        }
        while (true) {//work flow for picking a glass
            System.out.print("\nSelect a glass to fill or pour from.\n");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.print("\n\n\nHave a nice day!");
                scanner.close(); //this is here to prevent data leaks?
                System.exit(0);
            }
            int glassNum = Integer.parseInt(userInput);
            glassNum--;
            int listLength = glassList.size();
            if (glassNum >= listLength || glassNum < 0){
                System.out.println("You dont have that many glasses.\n");
                continue;
            } else {
                while(true){//flow for what to do with glass
                    int amount;
                    System.out.println(String.format("\nGlass %d currently has %d oz(s)", glassNum+1, glassList.get(glassNum).CurrentAmount));
                    System.out.print("\nWould you like to 'pour' water in or 'drain' some out?\n");
                    String action = scanner.nextLine();
                    if (action.equalsIgnoreCase("exit")){
                        System.out.print("\n\n\nHave a nice day!");
                        scanner.close(); // this is here to prevent data leaks?
                        System.exit(0);
                    } if (action.equalsIgnoreCase("pour")){
                        System.out.println("How much would you like to pour in (oz)?");
                        try{
                            amount = Integer.parseInt(scanner.nextLine());
                            int isNeg = Integer.signum(amount);//this just checks if the num is negative which of course we don't want
                            if (isNeg != 1){
                                throw new Exception("\nWhole numbers only please.");
                            }
                        } catch(Exception e){
                            System.out.println("\nWhole numbers only please.");
                            continue;
                        }
                        if ((amount + glassList.get(glassNum).CurrentAmount) > glassList.get(glassNum).Capacity) {
                            System.out.println("Woahhh there cowboy your gonna overflow.");
                        } if ((amount + glassList.get(glassNum).CurrentAmount) < glassList.get(glassNum).Capacity) {
                            glassList.get(glassNum).CurrentAmount = amount + glassList.get(glassNum).CurrentAmount;
                        }
                        System.out.println(String.format("\nGlass %d currently has %d oz(s)", glassNum+1, glassList.get(glassNum).CurrentAmount));
                        break;
                    } if(action.equalsIgnoreCase("drain")){
                        System.out.println("How much would you like to drain out?");
                        try{
                            amount = Integer.parseInt(scanner.nextLine());
                            int isNeg = Integer.signum(amount);//this just checks if the num is negative which of course we don't want
                            if (isNeg != 1){
                                throw new Exception("\nWhole numbers only please.");
                            }
                        } catch(Exception e){
                            System.out.println("\nWhole numbers only please.");
                            continue;
                        }
                        if ((glassList.get(glassNum).CurrentAmount - amount) >= 0) {
                            glassList.get(glassNum).CurrentAmount = glassList.get(glassNum).CurrentAmount - amount;
                        } else {
                            System.out.println("You dont have that much water.");
                            continue;
                        }
                        System.out.println(String.format("\nGlass %d currently has %d oz(s)", glassNum+1, glassList.get(glassNum).CurrentAmount));
                        break;
                    }
                }
            }
        }
    }
}
