package cn.lz.base.encode;

import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class CharEncode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 先把字符串按gb2312转成byte数组
        byte[] bytes = "中国".getBytes("gb2312");
        StringBuilder gbString = new StringBuilder();
        // 遍历数组
        int flag = 0;
        for (byte b : bytes) {
            flag++;
            // 再用Integer中的方法，把每个byte转换成16进制输出
            String temp = Integer.toHexString(b);
            // 因为byte是负值所以需要截取
            temp = temp.substring(6, 8);
            gbString.append(temp);
            if (flag % 2 == 0) {
                gbString.append(" ");
            }
        }
        System.out.println(gbString);

        int a1 = 0xa;
        System.out.println(a1);
        // 方法二
        char[] chars = new char[]{'纵', '中'};
        char a = '中';
        // 下面这条注释中的代码会被执行
        // \u000d a = '国';
        System.out.println(a);
        System.out.println(Integer.toHexString(a));
        // unicode -> 字符
        System.out.println((char) 0x56fd);
        int b = chars[0];
        System.out.println(Integer.toHexString(b));
        Collator collator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(chars);
        System.out.println(chars);
    }

}
