package cn.lz.base.solution.chars;

import java.util.Scanner;

/**
 * 字符串格式：a-zA-Z±
 * 示例:abc12ss-123b
 * 最小值：1+2+（-123）= -120
 */
public class MinValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 是否在缓存数字
            boolean flag = false;
            int res = 0;
            String num = "";
            String input = scanner.nextLine();
            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    if (flag) {
                        num += chars[i];
                    } else {
                        res += Character.getNumericValue(chars[i]);
                    }
                } else if (chars[i] != '-') {
                    if (flag && num.length() > 1) {
                        res += Integer.parseInt(num);
                    }
                    num = "";
                    flag = false;
                } else if (chars[i] == '-') {
                    if (flag) {
                        if (num.length() > 1)
                            res += Integer.parseInt(num);
                    } else {
                        flag = true;
                    }
                    num = "-";
                }
            }
            if (flag && num.length() > 1) {
                res += Integer.parseInt(num);
            }
            System.out.println(res);
        }
    }
}
