package com.yingu.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Cutword {

    public static void main(String[] args) throws Exception {

        FileReader file = new FileReader("/Users/wuyuwu/Desktop/yingu.txt");
        BufferedReader br = new BufferedReader(file);


        FileWriter fw = new FileWriter("data/yingu1.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        String line = "";
        while ((line=br.readLine())!= null){
            //分词
//            System.out.println(NLPTokenizer.segment(line)); // “正确”是副形词。
//
//            List<Term> list = NLPTokenizer.segment(line);
//            String temp = "";
//
//            for (Term term: list){
//                temp += term.word + " ";
//            }
//            bw.write(temp);
//            String[] array = line.split("\\t");

            if (line.contains("Q:")){
                String[] array = line.split("Q:");
                bw.write(array[1]);
                bw.newLine();
            }else {
                continue;
            }

        }

        //关闭读文件
        br.close();
        file.close();

        //关闭写文件
        bw.close();
        fw.close();
    }
}
