package com.ml.tf_idf;


import java.io.IOException;
import java.util.Map;

/**
 * 读写文件
 * 实现对文件的读写功能
 */
public class ReadAndWriteFile {

    /**
     * 测试一下
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Cutword cutword = new Cutword();

        String sourceFile = "data/sourcefile.txt";
        String targetFile = "data/target.txt";

        cutword.segment(sourceFile,targetFile);


        TfIdf tfIdf  =new TfIdf();
        Map<String,Double> tf = tfIdf.Tf(targetFile);
        Map<String,Double> idf = tfIdf.Idf(targetFile);
        Map<String,Double> ti = tfIdf.TfIdf(tf,idf);

        tfIdf.writeFile(ti,"data/tf-idf.txt");

    }

}
