package sample;

import java.util.*;
import java.io.*;

class mechanic {
    public static void command1() {
        Scanner scan = new Scanner(System.in);
        methods.showing_data(methods.getting_datalist("allcars.csv"));
        ArrayList<ArrayList<String>> cars = methods.getting_datalist("allcars.csv");
        ArrayList<String> to_service = new ArrayList<>();
        while (true) {
            int c = 0;
            System.out.print("Enter ID of the car >>> ");
            String car_id = scan.nextLine().strip();
            if (methods.isNumeric(car_id)) {
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).get(0).equals(car_id)) {
                        if (cars.get(i).get(6).equals("working")) {
                            to_service.addAll(cars.get(i));
                            cars.get(i).set(6, "on_repair");
                            c++;
                        } else {
                            System.out.println("\nThis car is already on repairing, choose another !\n");
                            break;
                        }
                    }
                }
                if (c != 1) {
                    System.out.println("\nThere is no car with this ID !!!\n");
                    continue;
                }
                to_service.remove(6);
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
                try {
                    BufferedWriter service_cars = new BufferedWriter(new FileWriter("in_service.csv", true));
                    for (int i = 0; i < to_service.size(); i++) {
                        service_cars.write(to_service.get(i));
                        if (i != to_service.size() - 1) {
                            service_cars.write(",");
                        }
                    }
                    service_cars.write("\n");
                    service_cars.close();
                    System.out.println("\nYou've taken car to service !");
                    break;
                } catch (Exception e) {
                    System.out.println("\n\t\tMaybe you have problem in 'IN_SERVICE' path, please check !");
                }
            } else {
                System.out.println("\nYour ID is not numeric !\n");
            }
        scan.close();
        }
    }

    public static void command2() {
        System.out.println();
        methods.showing_data(methods.getting_datalist("in_service.csv"));
        System.out.println();
    }

    public static void command3() {
        Scanner scan = new Scanner(System.in);
        methods.showing_data(methods.getting_datalist("in_service.csv"));
        ArrayList<ArrayList<String>> cars = methods.getting_datalist("allcars.csv");
        ArrayList<ArrayList<String>> in_service = methods.getting_datalist("in_service.csv");
        ArrayList<String> served = new ArrayList<>();
        if (in_service.size() == 0 || in_service.size() == 1) {
            System.out.println("\n\t\tThere is no cars on service, please choose option (1) to service !");
            return;
        }
        while (true) {
            int c = 0;
            System.out.print("Enter ID of the car >>> ");
            String car_id = scan.nextLine().strip();
            if (methods.isNumeric(car_id)) {
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).get(0).equals(car_id)) {
                        if (cars.get(i).get(6).equals("on_repair")) {
                            served.addAll(cars.get(i));
                            cars.get(i).set(6, "working");
                            in_service.remove(i);
                            c++;
                        } else {
                            System.out.println("\nThis car is already on repairing, choose another !\n");
                            break;
                        }
                    }
                }
                if (c != 1) {
                    System.out.println("\nThere is no car with this ID !!!\n");
                    continue;
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
                served.remove(6);
                try {
                    BufferedWriter in_service_write = new BufferedWriter(new FileWriter("in_service.csv"));
                    for (int i = 0; i < in_service.size(); i++) {
                        for (int j = 0; j < in_service.get(i).size(); j++) {
                            in_service_write.write(in_service.get(i).get(j));
                            if (j != in_service.get(i).size() - 1) {
                                in_service_write.write(",");
                            }
                        }
                        in_service_write.write("\n");
                    }
                    in_service_write.close();
                } catch (Exception e) {
                    System.out.println("\n\t\tMaybe you have problem in 'IN_SERVICE' path, please check !");
                }
                try {
                    BufferedWriter served_cars_write = new BufferedWriter(
                            new FileWriter("served_cars.csv", true));
                    for (int i = 0; i < served.size(); i++) {
                        served_cars_write.write(served.get(i));
                        if (i != served.size() - 1) {
                            served_cars_write.write(",");
                        }
                    }
                    served_cars_write.write("\n");
                    served_cars_write.close();
                } catch (Exception e) {
                    System.out.println("\n\t\tMaybe you have problem in 'SERVED_CARS' path, please check !");
                }
                System.out.println("\nYou have done !!!");
                break;
            } else {
                System.out.println("\nYour ID is not numeric !\n");
            }
        scan.close();
        }
    }

    public static void command4() {
        System.out.println("\n\t\t\t >>> List of served cars <<<\n");
        String path = "served_cars.csv";
        methods.showing_data(methods.getting_datalist(path));
        System.out.print("Amount: ");
        System.out.println(methods.getting_datalist(path).size() - 1);
    }

    public static void command5() {
        ArrayList<ArrayList<String>> served = methods.getting_datalist("served_cars.csv");
        int cars = served.size() - 1;
        int salary = cars * 100;
        System.out.println("\nYou have served " + cars + " car(s) and your salary is " + salary + "$");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n\t\tGreetings dear Mechanic!".toUpperCase());
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n1. Pick up the car for service \n2. Show a list of all vehicles in service \n3. Service the car \n4. Show serviced vehicles \n5. Show my earnings\n6.Back to the main menu");
            System.out.print("Please, choose one of commands, if you want quit deal 0 >>> ");
            int com = input.nextInt();
            if (com == 1) {
                command1();
            } else if (com == 2) {
                command2();
            } else if (com == 3) {
                command3();
            } else if (com == 4) {
                command4();
            } else if (com == 5) {
                command5();
            } else if (com == 6) {
                auth.main(args);
            } else if (com == 0) {
                System.out.println("The program is over, we look forward to your return!");
                System.exit(0);
            }
        }
    }
}
