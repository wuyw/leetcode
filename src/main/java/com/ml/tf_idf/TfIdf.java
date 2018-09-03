package com.ml.tf_idf;

import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 实现tf
 * 实现idf
 * 文件的每一行当成一个文本
 * 此程序没有分类，是在特定特定场景下应用，如果有分类可以参考，不能直接使用
 */
public class TfIdf {

    //    TFw=在某一类中词条w出现的次数/该类中所有的词条数目

    /**
     *
     * @param sourceFile
     * @return
     * @throws IOException
     */
    public Map<String,Double> Tf(String sourceFile) throws IOException {

        FileReader fileReader = new FileReader(sourceFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";
        long count = 0;
        Map<String,Integer> map = new HashMap<String, Integer>();
        while ((line = bufferedReader.readLine()) != null) {

            String[] array = line.split(" ");
            count += array.length;
            for (String str : array) {
                if (map.get(str) != null) {
                    int temp = map.get(str);
                    map.put(str,temp +1);
                } else {
                    map.put(str, 1);
                }
            }

        }

        bufferedReader.close();
        fileReader.close();

        Map<String,Double> map1 = new HashMap<String, Double>();

        for (String key : map.keySet()){
            int total = map.get(key);
            map1.put(key,total*1.0/count);
        }

        return map1;
    }


    //    IDF=log(语料库的文档总数/包含词条w的文档数+1),分母之所以要加1，是为了避免分母为0

    /**
     *
     * @return
     */
    public Map<String,Double> Idf(String sourceFile) throws IOException{

        FileReader fileReader = new FileReader(sourceFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        long count = 0;
        Map<String,Integer> map = new HashMap<String, Integer>();

        while ((line = bufferedReader.readLine()) != null) {

            String[] arraylist = line.split(" ");
            Set<String> set = new HashSet<String>();
            CollectionUtils.addAll(set, arraylist);

            for (String string : set) {
                if (map.get(string) != null) {
                    int temp = map.get(string);
                    map.put(string,temp +1);
                } else {
                    map.put(string, 1);
                }
            }
        }
        bufferedReader.close();
        fileReader.close();

        Map<String,Double> map1 = new HashMap<String, Double>();
        long lineCount = getLineNum(sourceFile);
        for (String str : map.keySet()){
            int total = map.get(str);
            double idf = Math.log(lineCount*1.0/total+1);
            map1.put(str,idf);
        }


        return map1;
    }

    /**
     *  计算tf-idf的值
     * @param tf
     * @param idf
     * @return
     */
    public Map<String,Double> TfIdf(Map<String,Double> tf,Map<String,Double> idf) {

        Map<String,Double> tfidf = new HashMap<String, Double>();

        for (String str : tf.keySet()){

            float compute = tf.get(str).floatValue() * idf.get(str).floatValue();
            tfidf.put(str,Double.valueOf(compute));
        }

        return tfidf;
    }

    /**
     * @param sourceFile
     * @return
     * @throws IOException
     */
    public long getLineNum(String sourceFile) throws IOException{

        long count = 0;

        File file = new File(sourceFile);
        if(file.exists()){
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            while (lnr.readLine() != null){
                count++;
            }
            System.out.println("Total number of lines : " + count);
            lnr.close();
        }else{
            return 0;
        }
        return  count;
    }


    /**
     *  将map写成文件，一个键值对一行
     * @param source
     */
    public void writeFile(Map<String,Double> source,String targetFile) throws IOException {
        if (source != null){
            FileWriter fileWriter = new FileWriter(targetFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String str : source.keySet()) {
                bufferedWriter.write( str + " " + source.get(str).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        }

    }


}
