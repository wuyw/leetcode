package com.yingu.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;

/**
 * 校验准确率
 */
public class Word2VecTest {


    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String url="http://127.0.0.1:8090/ask/ask";
//        String url="http://172.24.132.87:8090/ask/ask";

        /* 注意：必须 http、https……开头，不然报错，浏览器地址栏不加 http 之类不出错是因为浏览器自动帮你补全了 */

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        FileReader file = new FileReader("csv/test.csv");
        BufferedReader br = new BufferedReader(file);


        FileWriter fw = new FileWriter("log/cutword11.txt");
        BufferedWriter bw = new BufferedWriter(fw);


        String line = "";

        int count = 0;
        int correct = 0;
        int suggest = 0;

        while ((line=br.readLine())!= null){

            String[] array = line.split(",");
            JSONObject json = new JSONObject();
            json.put("orgId","123456");
            json.put("q",array[0]);
//            json.put("switch","on");
            HttpEntity<String> entity = new HttpEntity<String>(json.toJSONString(), headers);

            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            String jsonObject = res.getBody();

            System.out.println(jsonObject);

            JSONObject jsonObject1 = JSON.parseObject(jsonObject);

            if (jsonObject1.get("question") != null){
//                System.out.println("========question=======");
//                System.out.println(array[0]);
//                System.out.println("========question=======");
//                if (array[1].substring(3).equals(jsonObject1.getString("answer").substring(3))){
                if (jsonObject1.getString("question").contains(array[1]) || array[1].contains(jsonObject1.getString("question"))){
                    correct++;
                }else {
                    System.out.println("---------------------------------");
                    System.out.println(array[0]);
                    System.out.println(jsonObject1.getString("question"));
                    System.out.println("---------------------------------");
                    bw.write(array[0]);
                    bw.write(",");
                    bw.write(jsonObject1.getString("question"));
                    bw.newLine();
                }
            }else {
//                System.out.println("---------------------------------");
//                System.out.println(array[0]);
//                System.out.println(jsonObject1.getString("suggestquestion"));
//                System.out.println("---------------------------------");
                if (jsonObject1.getString("suggestquestion").contains(array[1])){
                    suggest++;
                }else {
                    System.out.println("---------------------------------");
                    System.out.println(array[0]);
                    System.out.println(jsonObject1.getString("suggestquestion"));
                    System.out.println("---------------------------------");
                    bw.write(array[0]);
                    bw.write(",");
                    bw.write(jsonObject1.getString("suggestquestion"));
                    bw.newLine();
                }
            }


            count++;
        }
        //关闭读文件
        br.close();
        file.close();

        //关闭写文件
        bw.close();
        fw.close();


        double correctRate = 0.0;

        System.out.println("suggest : "+suggest);
        System.out.println("correct : "+correct);
        correctRate = (correct+suggest)*1.0/count;

        System.out.println("correct : "  + correct*1.0/count );

        System.out.println("suggest : "  + suggest*1.0/count );

        System.out.println("correct + suggest: " +correctRate);


    }
}
