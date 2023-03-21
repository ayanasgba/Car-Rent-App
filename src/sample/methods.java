package sample;

import java.util.*;
import java.io.*;

public class methods {

    public static boolean isNumeric(String num) {
        if (num.equals("") || num.equals(null)) {
            return false;
        } else {
            try {
                int numm = Integer.parseInt(num);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static void showing_data(ArrayList<ArrayList<String>> l) {
        if (l.size() != 0) {
            int col_size = l.get(0).size();
            Formatter formatter = new Formatter();
            String formats = "";
            String dashes = "";
            String nulls = "";
            for (int i = 0; i < col_size; i++) {
                for (int j = 0; j < l.get(0).get(i).length(); j++) {
                    dashes += "-";
                }
                dashes += " ";
                formats += "%13s ";
                nulls += null + " ";
            }
            String[] spl_dash = dashes.strip().split(" ");
            String[] spl_formt = formats.strip().split(" ");
            String[] spl_nulls = nulls.strip().split(" ");
            for (int i = 0; i < l.size(); i++) {
                formatter = new Formatter();
                for (int j = 0; j < l.get(i).size(); j++) {
                    formatter = new Formatter();
                    System.out.print(formatter.format("%13s", l.get(i).get(j)));

                }
                if (i == 0) {
                    System.out.println();
                    for (int q = 0; q < col_size; q++) {
                        formatter = new Formatter();
                        System.out.print(formatter.format(spl_formt[q], spl_dash[q]));
                    }
                }
                if (l.size() == 1) {
                    System.out.println();
                    for (int q = 0; q < col_size; q++) {
                        formatter = new Formatter();
                        System.out.print(formatter.format("%13s", spl_nulls[q]));
                    }
                    break;
                }
                System.out.println();
            }
        }
    }

    public static ArrayList<ArrayList<String>> getting_datalist(String p) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(p));
            String s;
            while ((s = read.readLine()) != null) {
                String[] splitted = s.split(",");
                ArrayList<String> l = new ArrayList<>();
                for (String a : splitted) {
                    l.add(a);
                }
                l.removeIf(n -> n.equals(""));
                if (l.size() != 0) {
                    list.add(l);
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println("You need to correct you path settings !");
            return null;
        }
    }
}
