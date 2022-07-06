package cn.lz.base.solution.chars;

import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 计算面积
 * 4 10 （A 几个点 目标横坐标）
 * 1 1
 * 2 -1
 * 3 2
 * 4 3
 */
public class CalArea {

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int pointNum = -1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            pairs.add(Pair.create(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            if (flag) {
                pointNum = Integer.parseInt(s[0]);
                flag = false;
            } else {
                pointNum--;
                if (pointNum == 0)
                    break;
            }
        }
        int targetX = pairs.get(0).getSecond();
        int area = 0;
        int curY = 0;
        int curX = 0;
        for (int i = 1; i < pairs.size(); i++) {
            Pair<Integer, Integer> curPair = pairs.get(i);
            area += Math.abs(curY * (curPair.getFirst() - curX));
            curY += curPair.getSecond();
            curX = curPair.getFirst();
        }
        area += Math.abs(curY * (targetX - curX));
        System.out.printf("area %s \n", area);

    }
}
