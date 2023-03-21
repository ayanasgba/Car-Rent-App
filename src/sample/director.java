package sample;

import java.util.*;

public class director {
    public static <K, V extends Comparable<V>> Map.Entry<K, V> getMax(Map<K, V> map) {
        Map.Entry<K, V> entryWithMaxValue = null;
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
            if (entryWithMaxValue == null || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > 0) {
                entryWithMaxValue = currentEntry;
            }
        }
        return entryWithMaxValue;
    }

    public static <K, V extends Comparable<V>> Map.Entry<K, V> getMin(Map<K, V> map, int val) {
        Map.Entry<K, V> entryWithMaxValue = null;
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
            if (val > 100000) {
                if (entryWithMaxValue == null
                        || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > val) {
                    entryWithMaxValue = currentEntry;
                }
            } else {
                if (entryWithMaxValue == null
                        || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) < val) {
                    entryWithMaxValue = currentEntry;
                }
            }
        }
        return entryWithMaxValue;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\tWelcome, dear Director!\n".toUpperCase());
        ArrayList<ArrayList<String>> sold_cars = methods.getting_datalist("sold_cars.csv");
        ArrayList<ArrayList<String>> served_cars = methods.getting_datalist("served_cars.csv");
        ArrayList<ArrayList<String>> all_cars = methods.getting_datalist("allcars.csv");
        while (true) {
            System.out.println("1. Show a list of cars ");
            System.out.println("2. Show the number of all rented cars ");
            System.out.println("3. Show the car with the highest number of sales ");
            System.out.println("4. Show the car with the lowest number of sales");
            System.out.println("5. Show the car that requires the most maintenance");
            System.out.println("6. Show the most expensive car ");
            System.out.println("7. Show the cheapest car");
            System.out.println("8. Back to the main menu");
            System.out.println("If you want to end program, then enter 0");
            System.out.print("\nPlease, choose one of commands >>> ");
            int command = sc.nextInt();
            System.out.println();
            if (command == 1) {
                methods.showing_data(methods.getting_datalist("allcars.csv"));
                System.out.println();
            } else if (command == 2) {
                methods.showing_data(methods.getting_datalist("sold_cars.csv"));
                System.out.println();
            } else if (command == 3) {
                ArrayList<ArrayList<String>> sold2 = sold_cars;
                HashMap<String, Integer> dict = new HashMap<>();
                for (int i = 1; i < sold2.size(); i++) {
                    int c = 0;
                    if (dict.containsKey(sold2.get(i).get(1))) {
                        c = dict.get(sold2.get(i).get(1));

                    }
                    dict.put(sold2.get(i).get(1), c + 1);
                }

                System.out
                        .println(
                                "The car with the highest number of sales is " + getMax(dict).getKey().toUpperCase());
                System.out.println(getMax(dict).getValue() + " car(s) sold.\n");
            } else if (command == 4) {
                ArrayList<ArrayList<String>> sold2 = sold_cars;
                HashMap<String, Integer> dict = new HashMap<>();
                for (int i = 1; i < sold2.size(); i++) {
                    int c = 0;
                    if (dict.containsKey(sold2.get(i).get(1))) {
                        c = dict.get(sold2.get(i).get(1));

                    }
                    dict.put(sold2.get(i).get(1), c + 1);
                }

                System.out
                        .println(
                                "The car with the lowest number of sales is "
                                        + getMin(dict, 100).getKey().toUpperCase());
                System.out.println(getMin(dict, 100).getValue() + " car(s) sold.\n");
            } else if (command == 5) {
                ArrayList<ArrayList<String>> served = served_cars;
                HashMap<String, Integer> dict = new HashMap<>();
                for (int i = 1; i < served.size(); i++) {
                    int c = 0;
                    if (dict.containsKey(served.get(i).get(1))) {
                        c = dict.get(served.get(i).get(1));

                    }
                    dict.put(served.get(i).get(1), c + 1);
                }

                System.out
                        .println(
                                "The car with the highest number of servicing is "
                                        + getMax(dict).getKey().toUpperCase());
                System.out.println(getMax(dict).getValue() + " car(s) serviced.\n");
            } else if (command == 6) {
                ArrayList<ArrayList<String>> cars = all_cars;
                HashMap<String, Integer> dict = new HashMap<>();
                for (int i = 1; i < cars.size(); i++) {
                    dict.put(cars.get(i).get(0), Integer.parseInt(cars.get(i).get(5)));
                }
                String car_name = "";
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).get(0).equalsIgnoreCase(getMax(dict).getKey())) {
                        car_name = cars.get(i).get(1);
                    }
                }
                System.out
                        .println(
                                "The most expensive car is " + car_name.toUpperCase());
                System.out.println("Price: " + getMax(dict).getValue() + "\n");
            } else if (command == 7) {
                ArrayList<ArrayList<String>> cars = all_cars;
                HashMap<String, Integer> dict = new HashMap<>();
                for (int i = 1; i < cars.size(); i++) {
                    dict.put(cars.get(i).get(0), Integer.parseInt(cars.get(i).get(5)));
                }
                String car_name = "";
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).get(0).equalsIgnoreCase(getMin(dict, 500000).getKey())) {
                        car_name = cars.get(i).get(1);
                    }
                }
                System.out
                        .println(
                                "The cheapest car is " + car_name.toUpperCase());
                System.out.println("Price: " + getMin(dict, 500000).getValue() + "\n");
            } else if (command == 8) {
                auth.main(args);
            } else if (command == 0) {
                System.out.println("The program is over, we look forward to your return!");
                System.exit(0);
            }
        sc.close();
        }
    }
}
