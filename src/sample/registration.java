package sample;

import java.io.*;
import java.util.*;

public class registration {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        registrate();
    }

    public static void registrate() {
        while (true) {
            System.out.print("\nEnter account type ('d','s','m') or ('e') to exit: ");
            String acc_type = scan.nextLine();
            if (acc_type.toLowerCase().equals("e")) {
                return;
            } else if (!acc_type.equals("") && !acc_type.equals("d") && !acc_type.equals("s") || acc_type.equals("m")) {
                System.out.println("\nWrong input !");
                continue;
            }
            System.out.print("Enter username: ");
            String username = scan.nextLine();
            if (username.equals("")) {
                System.out.println("\nYou entered nothing !");
                continue;
            }
            ArrayList<ArrayList<String>> accounts = data();
            int a = 0;
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).get(2).equals(acc_type) && accounts.get(i).get(0).equals(username)) {
                    break;
                } else {
                    a++;
                }
            }
            if (a == accounts.size()) {
                System.out.print("Enter password: ");
                String pass = scan.nextLine();
                if (pass.equals("")) {
                    System.out.println("\nInput is required");
                    continue;
                }
                try {
                    BufferedWriter write = new BufferedWriter(new FileWriter("account_data.csv", true));
                    String result = String.format("%s,%s,%s\n", username, pass, acc_type);
                    write.write(result);
                    write.close();
                    System.out.println("\n\t\tYou've successfully signed up !");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("\nUsername has taken, try antother !!!");
            }
        }
    }

    public static ArrayList<ArrayList<String>> data() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("account_data.csv"));
            ArrayList<ArrayList<String>> allusers = new ArrayList<>();
            String line = "";
            while ((line = read.readLine()) != null) {
                String[] name = line.split(",");
                ArrayList<String> l = new ArrayList<>();
                if (!name[0].equals("Username")) {
                    for (String a : name) {
                        l.add(a);
                    }
                    allusers.add(l);
                }
            }
            read.close();
            return allusers;

        } catch (Exception e) {
            System.out.println(e);
        }
        scan.close();
        return null;
    }
}
