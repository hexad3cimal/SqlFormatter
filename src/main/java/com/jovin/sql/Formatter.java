package com.jovin.sql;

import java.io.*;

/**
 * Created by jovin on 16/11/16.
 */
public class Formatter {


    public static boolean isInEnum(String test) {

        for (Keywords c : Keywords.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String args[]) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\path\\to\\test.xml"), "Cp1252"));
            FileWriter fw = new FileWriter("D:\\path\\to\\test_formatted.xml", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toUpperCase().contains("SQLQUERY") ||
                        line.toUpperCase().contains("TYPE=\"SQL\"")) {
                    String[] words = line.split(" ");
                    if (words != null) {

                        for (String w : words) {

                            if (isInEnum(w.replace(";", ""))) {
                                line = line.replaceAll("\\b"+w+"\\b", w.toUpperCase());
                            }
                        }
                    }
                }
                out.println(line);

            }
            out.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
