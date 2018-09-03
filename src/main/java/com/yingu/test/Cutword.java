package com.yingu.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Cutword {

    public static void main(String[] args) throws Exception {

        FileReader file = new FileReader("data/sourcefile1.txt");
        BufferedReader br = new BufferedReader(file);


        FileWriter fw = new FileWriter("data/new2.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        String line = "";
        while ((line=br.readLine())!= null){
            //分词
            System.out.println(HanLP.segment(line)); // “正确”是副形词。

            List<Term> list = HanLP.segment(line);
            String temp = "";

            for (Term term: list){
                temp += term.word + " ";
            }
            bw.write(temp);
            bw.newLine();
//            String[] array = line.split("\\t");
//
//            if (line.contains("Q:")){
//                String[] array = line.split("Q:");
//                bw.write(array[1]);
//                bw.newLine();
//            }else {
//                continue;
//            }

        }

        //关闭读文件
        br.close();
        file.close();

        //关闭写文件
        bw.close();
        fw.close();
    }
}
