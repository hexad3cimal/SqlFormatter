package com.jovin.sql;

import java.io.*;

/**
 * Created by jovin on 16/11/16.
 */
public class Formatter {


    public static boolean isInEnum(String test) {

        for (Keywords c : Keywords.values()) {
            if (c.name().toString().equalsIgnoreCase(test)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String args[]){

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "Cp1252"));
            FileWriter fw = new FileWriter(args[0]+"_formatted", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            String line;
            while ((line = br.readLine()) != null) {

                String[] words = line.split(" ");
                if(words != null){

                    for (String w : words){

                        if(isInEnum(w.replace(";",""))){
                            line = line.replace(w,w.toUpperCase());
                        }

                    }
                }

                out.println(line);



            }
            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
