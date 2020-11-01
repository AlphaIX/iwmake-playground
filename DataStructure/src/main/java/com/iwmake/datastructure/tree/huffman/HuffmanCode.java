package com.iwmake.datastructure.tree.huffman;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 * @author Dylan
 * @since 2020-10-31
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] bytes = content.getBytes();
//        System.out.println(bytes.length); // 40

//        List<Node> nodes = getNodes(bytes);
//        System.out.println(nodes.toString());
//
//        // 测试
//        System.out.println("创建的赫夫曼树：");
//        Node root = createHuffmanTree(nodes);
//        preOrder(root);
//
//        // 测试是否生成了对应的Huffman编码
//        Map<Byte, String> codes = getCodes(root);
//        System.out.println("Huffman编码："+codes.toString());
//
//        //
//        byte[] zip = zip(bytes, codes);
//        System.out.println("Huffman编码后的byte[]="+Arrays.toString(zip));

//        byte[] zip = huffmanZip(bytes);
//        System.out.println("压缩后：" + Arrays.toString(zip));
//        System.out.println("长度：" + zip.length);

        // 完成数据解压
//        byte[] sources = decode(huffmanCode, zip);
//        System.out.println("原始字符串：" + new String(sources));


        // 测试压缩文件
//        String srcFile = "2.txt";
//        String dstFile = "dst.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功");

        // 测试解压文件
        String zipFile = "dst.zip";
        String dstFile = "2-2.txt";
        unzipFile(zipFile, dstFile);
        System.out.println("解压成功");


    }

    /**
     * 完成对压缩文件解压
     * @param zipFile
     * @param dstFile
     */
    public static void unzipFile(String zipFile, String dstFile){
        // 定义文件输入流
        InputStream is  = null;
        // 定义对象输入流
        ObjectInputStream ois = null;
        // 文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            // 读取byte数组
            byte[] huffmanBytes = (byte[])ois.readObject();
            // 读取Huffman编码表
            Map<Byte,String> codes = (Map<Byte,String>)ois.readObject();
            // 解码
            byte[] bytes = decode(codes, huffmanBytes);
            // 写入目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 编写一个方法，对一个文件进行压缩
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile,String dstFile) {
        // 创建文件输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        // 创建文件输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创文件输出流存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把Huffman编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 这里以对象流的方式写入Huffman编码，是为了以后恢复文件时使用
            // 注意一定要把Huffman编码写入压缩文件
            oos.writeObject(huffmanCode);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * 对压缩数据解码
     * @param huffmanCode
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        //1 得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, huffmanBytes[i]));
        }
        //System.out.println("二进制字符串：" + stringBuilder.toString());
        // 2把字符串按照指定的赫夫曼编码解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println("map"+map.toString());

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 取出一个'1' '0'
                String key = stringBuilder.substring(i, i + count);// i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {// 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }
        //System.out.println(list.toString());
        // 当for循环结束后，list中就存放了所有的字符
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     * 将一个byte转成一个二进制字符串
     * @param flag 标识是否需要补高位 true需要，
     * @param b    传入的byte
     * @return 返回改byte对应的二进制字符串，注意是按补码返回
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b; //b-->int
        // 如果是正数还需要补高位
        if (flag) {
            temp |= 256;// 按位或！
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }


    // 使用一个方法将各个操作封装成一个方法
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 2.创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        // 3.生成赫夫曼编码
        Map<Byte, String> codes = getCodes(root);
        // 4.根据生成的赫夫曼编码压缩
        byte[] resByte = zip(bytes, codes);

        return resByte;
    }

    // 编写一个方法，将字符串对应的byte[]，处理生成Huffman编码压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        //
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }
        //System.out.println(stringBuilder.toString());

        // 转成byte[]
        // 一句话：len=(stringBuilder.length()+7) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 压缩后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {// 步长为8，每8位一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    static Map<Byte, String> huffmanCode = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入节点的所有叶子节点 得到的code存入 huffmanCode中
     * @param node 传入节点
     * @param code 路径 左0，右1
     * @param sb   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {
            // 判断是否是叶子节点
            if (node.data == null) {
                // 左递归处理
                getCodes(node.left, "0", sb2);
                // 右递归处理
                getCodes(node.right, "1", sb2);
            } else {// 叶子节点
                huffmanCode.put(node.data, sb2.toString());
            }
        }
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", stringBuilder);
        return huffmanCode;

    }


    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        // 存储每个byte出现的次数-->map处理
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {// 第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每个键值对，转成Node,放入list
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);// 从小到大排序

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);// 注意没有data，只有权值
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);// 返回根节点
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    static class Node implements Comparable<Node> {
        Byte data;// 存放数据本身，如：'a'==>97, ' '==>32
        int weight;// 权值，表示字符出现的次数
        Node left;
        Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        // 前序遍历
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
