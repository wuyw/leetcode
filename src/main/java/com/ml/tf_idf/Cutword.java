package com.ml.tf_idf;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

import java.io.*;
import java.util.List;

/**
 * 分词
 * 使用hanlp 分词
 */
public class Cutword {


    /**
     *  使用hanlp 进行分词
     * @param sourceFile
     * @param targetFile
     * @return
     * @throws IOException
     */
    public String segment(String sourceFile,String targetFile) throws IOException {

        FileReader fileReader = new FileReader(sourceFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(targetFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {

//            List<Term> list =  HanLP.segment(line);
            List<Term> list =  NotionalTokenizer.segment(line);
            String temp = "";
            for(Term term : list) {
                temp += term.word + " ";
            }

            bufferedWriter.write(temp);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
        bufferedReader.close();
        fileReader.close();

        return targetFile;
    }

}
