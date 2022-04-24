package cn.lz.base.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        String[] str = {"b", "a", "a", "c", "c"};
//        QuickSort.sort(str);
        Arrays.stream(str)
                .forEach(System.out::println);
        System.out.println();
        QuickSort.sort3way(str, 0, str.length - 1);
        Arrays.stream(str)
                .forEach(System.out::println);
    }

    public static void sort(Comparable[] sequence) {
        sort(sequence, 0, sequence.length - 1);
    }

    private static void sort(Comparable[] sequence, int left, int right) {
        if (left >= right) {
            return;
        }
        // 切分
        int j = partition(sequence, left, right);
        sort(sequence, left, j - 1);
        sort(sequence, j + 1, right);
    }

    /**
     * 切分元素 seq[left]
     */
    private static int partition(Comparable[] sequence, int left, int right) {
        int i = left, j = right + 1;

        while (true) {
            while (less(sequence[++i], sequence[left])) {
                if (i == right) {
                    break;
                }
            }
            while (less(sequence[left], sequence[--j])) {
                // 冗余条件，因为seq[left]不可能比自己小
                if (j == left) {
                    break;
                }
            }
            if (i >= j) break;
            exchange(sequence, i, j);
        }
        exchange(sequence, left, j);
        return j;
    }

    /**
     * 三向切分元素 seq[left]， 有bug
     */
    private static void sort3way(Comparable[] sequence, int left, int right) {
        if (left >= right) {
            return;
        }

        int lt = left, i = lt + 1, gt = right;
        while (i <= gt) {
            if (sequence[i].compareTo(sequence[left]) < 0) {
                exchange(sequence, i++, lt++);
            } else if (sequence[i].compareTo(sequence[left]) > 0) {
                exchange(sequence, i, gt--);
            } else {
                i++;
            }
        }
        sort3way(sequence, left, lt - 1);
        sort3way(sequence, gt + 1, right);
    }

    // 在等于的时候停下，避免在某些情况下运行时间变为平方级别
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] c, int idx1, int idx2) {
        Comparable temp = c[idx1];
        c[idx1] = c[idx2];
        c[idx2] = temp;
    }
}