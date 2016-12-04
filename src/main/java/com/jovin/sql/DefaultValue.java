package com.jovin.sql;

import java.io.*;

/**
 * Created by jovin on 4/12/16.
 */
public class DefaultValue {

    public static void main(String args[]) {

        try {
            BufferedReader br = new BufferedReader
                    (new InputStreamReader(new FileInputStream("/home/jovin/test.xml"), "Cp1252"));
            FileWriter fw = new FileWriter("/home/jovin/file.xml", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            String line;
            StringBuffer sb = null;
            Integer flag = 0;
            Integer i = 0;

            while ((line = br.readLine()) != null) {

                if (line.trim().contains("<Field>")) {

                    if(i==26){
                        System.out.println(line);
                    }
                    sb = new StringBuffer();
                    flag =1;
                }
                if (flag == 0) {
                    out.println(line);
                }
                if(flag == 1) {
                    if(i==27){
                        System.out.println(line);
                    }
                    sb.append(line);
                }
                if (line.contains("</Field>")) {
                    flag = 2;
                }
                if (flag == 2) {

                    if(i==41){
                        System.out.println(line);
                    }
                    if (sb.toString().contains("</DefaultValue>") &&
                            sb.toString().contains("</Calculated>")) {

                       String formatted = sb.toString().replace("<DefaultValue>0</DefaultValue>",
                                "")
                                .replace("<DefaultValue></DefaultValue>",
                                        "");
                        out.println(formatted);

                    }else {

                        out.println(sb);
                    }

                    flag=0;

                }

                i++;
            }
            out.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
