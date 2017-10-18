package com.nowcoder.toutiao.Service;

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
 * Created by lenovo on 2017/10/5.
 */
@Service
public class SensitiveService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);
    /**
     * 替换的敏感词
     */
    private static final String DEFAULT_REPLACEMENT = "**";

    private class TreeNode {

        /**
         * true是关键词的终结， false表示不是关键字
         */
        private boolean end = false;

        /**
         * key是下一个字符， value是对用的节点
         */
        Map<Character, TreeNode> subNode = new HashMap<>();

        /**
         * 向指定的位置加入及诶单
         */
        public void addSubNode(Character key, TreeNode node) {
            subNode.put(key, node);
        }

        /**
         * 获取下一个节点
         */
        public TreeNode getSubNode(Character key) {
            return subNode.get(key);
        }

        /**
         * 是否是结尾
         */
        public boolean isKeywordEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        private int getSubNodeCount() {
            return subNode.size();
        }
    }

    public String filter(String text) {

        if (StringUtils.isEmpty(text)) {
            return text;
        }
        int begin = 0;
        int position = 0;
        StringBuffer result = new StringBuffer();
        TreeNode tempNode = rootNode;
        String replacement = DEFAULT_REPLACEMENT;

        while (position < text.length()) {
            char ch = text.charAt(position);  //得到一个字符

            tempNode = tempNode.getSubNode(ch);

            //如果当前节点下面没有这个节点，表示ch不是一个敏感词
            if (tempNode == null) {
                result.append(text.charAt(begin));
               position = begin+1;
               begin = position;
                tempNode = rootNode;
            } else if (tempNode.end == true) { //是一个敏感词
                result.append(replacement);
                position = position+1;
                begin = position;
                tempNode = rootNode;
            } else {
                position++;
            }
        }

        result.append(text.substring(begin));  //添加最后一段
        return result.toString();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            TreeNode rootNode = new TreeNode();
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("SensitiveWords");
            InputStreamReader read = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(read);
            String lineText;
            while((lineText = br.readLine())!= null){
                lineText = lineText.trim();
                addWord(lineText);
            }
            br.close();
        }catch (Exception e){
            logger.error("读取敏感词文件失败" + e.getMessage());
        }
    }

    private TreeNode rootNode = new TreeNode();

    private void addWord(String lineText) {
        TreeNode tempNode = rootNode;
        for (int i = 0; i < lineText.length(); i++) {
            char ch = lineText.charAt(i);

            //判断当前节点下是否有个这节点
            TreeNode node = tempNode.getSubNode(ch);

            if (node == null) { //没有节点,进行挂载
                node = new TreeNode();
                tempNode.addSubNode(ch, node);
            }
            tempNode = node;  //有节点

            if (i == lineText.length() - 1) {
                tempNode.setEnd(true);
            }
        }
    }
}
