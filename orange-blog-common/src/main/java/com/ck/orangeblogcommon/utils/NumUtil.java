package com.ck.orangeblogcommon.utils;
/**
 * @author ck
 * @date 2018/12/12 13:50
 * Description  : 数字工具类
 */
public class NumUtil {

    /**
     * 得到0到9的随机数
     */
    public static int getNum0From9(){
        int random = (int)(Math.random()*10);
        return random;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("打印结果" + i + "：" + getNum0From9());
        }
    }
}
