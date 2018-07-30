package com.yingu.test;

import java.io.*;

public class Cutfile {
    public static void main(String[] args) throws Exception {
        FileReader file = new FileReader("/Users/wuyuwu/Desktop/QA.csv");
        BufferedReader br = new BufferedReader(file);

        String line = "";
        while ((line = br.readLine()) != null) {

            String[] array = line.split(",");

            FileWriter fileWriter = new FileWriter("/Users/wuyuwu/Desktop/temp/" + array[0] + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println(array[1] + array[2]);
            bufferedWriter.write(array[1]);

            bufferedWriter.close();
            fileWriter.close();
        }
        br.close();
        file.close();

    }
}
