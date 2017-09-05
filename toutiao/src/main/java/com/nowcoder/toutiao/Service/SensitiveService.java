package com.nowcoder.toutiao.Service;

import com.sun.org.apache.xml.internal.security.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lenovo on 2017/8/31.
 */
@Service
public class SensitiveService implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine())!=null){
                addWord(line.trim());
            }
            br.close();
        }catch (Exception e){
            logger.error("读取敏感词失败"+e.getMessage());
        }
    }

    //增加关键字
    private void addWord(String lineTxt){
        TrieNode tempNode = rootNode;  //指向根节点
        for(int i = 0; i < lineTxt.length(); i++){
           Character c = lineTxt.charAt(i);

           TrieNode node = tempNode.getSubNode(c);   //根据字符找出对应的节点

           if(node == null){                        //节点不存在
               node = new TrieNode();              //新建一个节点
               tempNode.addSubNode(c,node);         //加入这个节点的子节点
           }
           tempNode = node;                         //指向新生成的节点

           if(i == lineTxt.length() - 1){
               tempNode.setKeyWordEnd(true);
           }
        }
    }
    //构造字典树,将字符作为key值,value值为node
    private class TrieNode{
        private boolean end = false;                                                    //判断是否结束

        private Map<Character, TrieNode> subNodes = new HashMap<Character, TrieNode>(); //底层使用hashmap进行存储

        public void addSubNode(Character key, TrieNode node){                           //添加一个节点
            subNodes.put(key,node);
        }

        TrieNode getSubNode(Character key){
            return subNodes.get(key);                                                   //获取下一个节点(子节点)
        }

        boolean isKeyWorldEnd(){
            return end;
        }

        void setKeyWordEnd(boolean end){
            this.end = end;
        }
    }

    //生成根节点
    private TrieNode rootNode = new TrieNode();

    public String filter(String text){
        if(StringUtils.isEmpty(text)){
            return text;
        }
        StringBuilder result = new StringBuilder();
        String replacement = "***";
        TrieNode tempNode = rootNode;
        int begin = 0;
        int postion = 0;

        while(postion < text.length()){
            char c = text.charAt(postion);

            tempNode = tempNode.getSubNode(c);

            if(tempNode == null){
                result.append(text.charAt(begin));
                postion = begin + 1;
                begin = postion;
                tempNode = rootNode;
            }else if(tempNode.isKeyWorldEnd()){
                result.append(replacement);
                postion = postion+1;
                begin = postion;
                tempNode = rootNode;
            }else{
                postion++;
            }
        }
        result.append(text.substring(begin));
        return result.toString();
    }
}
