package sample;

import java.util.*;
import java.io.*;

public class seller {

    public static void command1() {

        // (Показывает список всех доступных к аренде автомобилей с указанием модели и
        // брэнда )
        System.out.println();
        String path = "allcars.csv";
        methods.showing_data(methods.getting_datalist(path));
    }

    public static void command2() {
        Scanner scan = new Scanner(System.in);
        String path = "allcars.csv";
        ArrayList<ArrayList<String>> cars = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String car;
            while ((car = reader.readLine()) != null) {
                String[] splitted = car.split(",");
                ArrayList<String> l = new ArrayList<>();
                for (String a : splitted) {
                    l.add(a);
                }
                l.removeIf(n -> n.equals(""));
                if (l.size() == 7) {
                    cars.add(l);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("\nSomething went wrong (path, etc.), please check code in this method !!!");
            return;
        }
        Formatter formatter = new Formatter();
        String model = "";
        String brand = "";
        String body_type = "";
        String transmission = "";
        while (true) {
            System.out.println("\nPlease, choose one of commands: ");
            System.out.println("*********************************");
            System.out.print(
                    "1. By model name\n2. By car brand name\n3. By body type\n4. By type of automatic transmission\n");
            System.out.println("If you want to go main menu, enter 0");
            String comm = scan.nextLine();
            if (methods.isNumeric(comm)) {
                int com = Integer.parseInt(comm);
                if (com == 1) {
                    System.out.print("Write model of the car >>> ");
                    model = scan.nextLine();
                } else if (com == 2) {
                    System.out.print("Write brand of the car >>> ");
                    brand = scan.nextLine();
                } else if (com == 3) {
                    System.out.print("Write body type of the car >>> ");
                    body_type = scan.nextLine();
                } else if (com == 4) {
                    while (true) {
                        System.out.println(
                                "Select type of automatic transmission:\n1. Automatic\n2. Manual automatic transmission\n");
                        System.out.print("Select >>> ");
                        transmission = scan.nextLine();
                        if (transmission.equals("1")) {
                            transmission = "AT";
                            break;
                        } else if (transmission.equals("2")) {
                            transmission = "MAT";
                            break;
                        } else {
                            System.out.println("Wrong input, try again !!!");
                        }
                    }
                } else if (com == 0) {
                    break;
                }
                int c = 0;
                if (com < 5 && com > 0) {
                    System.out.println();
                    for (int i = 0; i < cars.size(); i++) {
                        formatter = new Formatter();
                        if (cars.get(i).get(com).equalsIgnoreCase(model)
                                || cars.get(i).get(com).equalsIgnoreCase(brand)
                                || cars.get(i).get(com).equalsIgnoreCase(body_type)
                                || cars.get(i).get(com).equalsIgnoreCase(transmission)) {

                            System.out.println(formatter.format("%13s %13s %13s %13s %13s %13s %13s",
                                    cars.get(i).get(0),
                                    cars.get(i).get(1), cars.get(i).get(2), cars.get(i).get(3), cars.get(i).get(4),
                                    cars.get(i).get(5), cars.get(i).get(6)));
                            c++;
                        }
                        if (cars.get(i).get(0).equals("ID")) {
                            System.out.println(formatter.format("%13s %13s %13s %13s %13s %13s %13s",
                                    cars.get(i).get(0),
                                    cars.get(i).get(1), cars.get(i).get(2), cars.get(i).get(3), cars.get(i).get(4),
                                    cars.get(i).get(5), cars.get(i).get(6)));
                            formatter = new Formatter();
                            System.out.println(
                                    formatter.format("%13s %13s %13s %13s %13s %13s %13s", "--", "-----", "-----",
                                            "----", "------------", "-----", "------"));
                        }
                    }
                } else {
                    System.out.println("\nWrong input, try again !!!");
                    continue;
                }
                if (c == 0) {
                    formatter = new Formatter();
                    System.out.println(formatter.format("%13s %13s %13s %13s %13s %13s %13s", null, null, null,
                            null, null, null, null));
                    System.out.println("\nWe didn't find car with this information, try again !");
                } else {
                    break;
                }
            } else {
                System.out.println("\nInput is not numeric, try again !!!");
            }
        }
    }

    public static void command3() {
        String path = "";
        for (int i = 1; i <= 3; i++) {
            switch (i) {
                case 1:
                    path = "allcars.csv";
                    System.out.println("Cars that are left: ");
                    break;
                case 2:
                    path = "sold_cars.csv";
                    System.out.println("\nSold cars: ");
                    break;
                case 3:
                    path = "in_service.csv";
                    System.out.println("\nCars that are in service: ");
                    break;
            }
            methods.showing_data(methods.getting_datalist(path));
            System.out.print("Amount: ");
            System.out.println(methods.getting_datalist(path).size() - 1);
        }
    }

    public static void command4() {
        Scanner scan = new Scanner(System.in);
        methods.showing_data(methods.getting_datalist("allcars.csv"));
        System.out.println();
        ArrayList<ArrayList<String>> cars = methods.getting_datalist("allcars.csv");
        ArrayList<String> sold = new ArrayList<>();
        while (true) {
            int c = 0;
            System.out.print("Please enter the ID number of car: ");
            String car_id = scan.nextLine().strip();
            if (methods.isNumeric(car_id)) {
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).get(0).equals(car_id)) {
                        if (cars.get(i).get(6).equals("working")) {
                            sold.addAll(cars.get(i));
                            cars.remove(i);
                        } else {
                            c++;
                            System.out.println("\nThis car is on repairing now, choose another !\n");
                            break;
                        }
                    }
                }
                sold.remove(6);
                if (sold.size() != 0) {
                    int cost = Integer.parseInt(sold.get(5));
                    double commission = cost * 1.5 / 100;
                    double result = commission + cost;
                    try {
                        BufferedWriter sold_cars = new BufferedWriter(new FileWriter("sold_cars.csv", true));
                        sold.set(5, String.valueOf((int) result));
                        for (int i = 0; i < sold.size(); i++) {
                            sold_cars.write(sold.get(i));
                            if (i != sold.size() - 1) {
                                sold_cars.write(",");
                            }
                        }
                        sold_cars.write("\n");
                        sold_cars.close();
                    } catch (Exception e) {
                        System.out.println("\n\t\tMaybe you have problem in 'SOLD_CARS' path, please check !");
                    }
                    try {
                        BufferedWriter all_cars = new BufferedWriter(new FileWriter("allcars.csv"));
                        for (int i = 0; i < cars.size(); i++) {
                            for (int j = 0; j < cars.get(i).size(); j++) {
                                all_cars.write(cars.get(i).get(j));
                                if (j != cars.get(i).size() - 1) {
                                    all_cars.write(",");
                                }
                            }
                            all_cars.write("\n");
                        }
                        all_cars.close();
                    } catch (Exception e) {
                        System.out.println("\n\t\tMaybe you have problem in 'ALL_CARS' path, please check !");
                    }
                    Formatter formatter = new Formatter();
                    System.out.print(
                            "Price for "
                                    + formatter.format("%s %s", sold.get(1).toUpperCase(), sold.get(2).toUpperCase())
                                    + " is ");
                    System.out.println(cost + "$");
                    formatter = new Formatter();
                    System.out.println("Tax amount was: 1% or " + formatter.format("%s", cost / 100) + "$");
                    System.out.println("The amount of commissions to the seller for the sale was "
                            + String.format("%s", cost * 0.5 / 100) + "$");
                    System.out.println("Total final price: " + String.format("%s", (int) result) + "$");

                    break;
                } else if (c != 1) {
                    System.out.println("\nThere is no car with this ID !!!\n");
                }
            } else {
                System.out.println("\nYour ID is not numeric !\n");
            }
        }
    }

    public static void command5() {
        System.out.println();
        methods.showing_data(methods.getting_datalist("sold_cars.csv"));
    }

    public static void command6() {
        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<String>> sold_cars = methods.getting_datalist("sold_cars.csv");
        ArrayList<ArrayList<String>> all_cars = methods.getting_datalist("allcars.csv");
        if (sold_cars.size() == 1 || sold_cars.size() == 0) {
            System.out.println("\n\t\tThere is no sold cars, please choose option (4) to complete car order !");
            return;
        }
        ArrayList<String> returned = new ArrayList<>();
        command5();
        while (true) {
            int c = 0;
            System.out.print("\nPlease enter ID of the car that you want to return >>> ");
            String car_id = scan.nextLine().strip();
            if (methods.isNumeric(car_id)) {
                for (int i = 0; i < sold_cars.size(); i++) {
                    if (sold_cars.get(i).get(0).equals(car_id)) {
                        returned.addAll(sold_cars.get(i));
                        sold_cars.remove(i);
                        c++;
                    }
                }
                if (c != 0) {
                    try {
                        for (int i = 0; i < all_cars.size(); i++) {
                            if (all_cars.get(i).get(0).equals(car_id)) {
                                returned.set(5, all_cars.get(i).get(5));
                                break;
                            }
                        }
                        BufferedWriter sold_cars_write = new BufferedWriter(new FileWriter("sold_cars.csv"));
                        for (int i = 0; i < sold_cars.size(); i++) {
                            for (int j = 0; j < sold_cars.get(i).size(); j++) {
                                sold_cars_write.write(sold_cars.get(i).get(j));
                                if (j != sold_cars.get(i).size() - 1) {
                                    sold_cars_write.write(",");
                                }
                            }
                            sold_cars_write.write("\n");
                        }
                        sold_cars_write.close();
                    } catch (Exception e) {
                        System.out.println("\n\t\tMaybe you have problem in 'SOLD_CARS' path, please check !");
                    }
                    try {
                        BufferedWriter returned_cars_write = new BufferedWriter(
                                new FileWriter("returned_cars.csv", true));
                        for (int i = 0; i < returned.size(); i++) {
                            returned_cars_write.write(returned.get(i));
                            if (i != returned.size() - 1) {
                                returned_cars_write.write(",");
                            }
                        }
                        returned_cars_write.write("\n");
                        returned_cars_write.close();
                    } catch (Exception e) {
                        System.out.println("\n\t\tMaybe you have problem in 'RETURNED_CARS' path, please check !");
                    }
                    try {
                        returned.add("working");
                        BufferedWriter all_cars_write = new BufferedWriter(new FileWriter("allcars.csv", true));
                        for (int i = 0; i < returned.size(); i++) {
                            all_cars_write.write(returned.get(i));
                            if (i != returned.size() - 1) {
                                all_cars_write.write(",");
                            }
                        }
                        all_cars_write.write("\n");
                        all_cars_write.close();
                    } catch (Exception e) {
                        System.out.println("\n\t\tMaybe you have problem in 'ALL_CARS' path, please check !");
                    }
                    System.out.println("\nYou have successfully returned !");
                    break;
                } else if (c == 0) {
                    System.out.println("\nThere is no car with this ID !!!");

                }
            } else {
                System.out.println("\nYour ID is not numeric !");
            }
        }
        // (Удаляет автомобиль из файла «solt-cars.txt» и одновременно записывает в
        // «returned.txt»,
        // «cars.txt» - увеличивается количество автомобилей)

    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome, dear Seller!".toUpperCase());
        System.out.println("\n*********************************");
        int command = 1;
        while (command != 0) {
            System.out.print(
                    "\n1. Show all car list\n2. Search for a car\n3. Show vehicle report\n4. Complete the car order\n5. View the list of purchased cars\n6. Return the sold car\n7. Back to the main menu\n");
            System.out.println("If you want to end program, then enter 0");
            System.out.println("\n*********************************\n");
            System.out.println("Please, choose one of commands: ");
            command = sc.nextInt();

            System.out.println("\n*********************************");
            if (command == 1) {
                command1();
                System.out.println("\n*********************************");
            } else if (command == 2) {
                command2();
                System.out.println("\n*********************************");
            } else if (command == 3) {
                command3();
                System.out.println("\n*********************************");
            } else if (command == 4) {
                command4();
                System.out.println("\n*********************************");
            } else if (command == 5) {
                command5();
                System.out.println("\n*********************************");
            } else if (command == 6) {
                command6();
                System.out.println("\n*********************************");
            } else if (command == 7) {
                auth.main(args);
            } else if (command == 0) {
                System.out.println("The program is over, we look forward to your return!");
                System.exit(0);
            } else {
                System.out.println("\nWrong input");
            }
        }

        sc.close();
    }
}