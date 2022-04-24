package cn.lz.base.sort;

/**
 * 堆排序
 * 从下标1处开始存放数据
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        int[] res = HeapSort.sort(arr);

        for (int i = 1; i < res.length; i++) {
            if (res[i] < res[i - 1]) {
                throw new RuntimeException("sort error");
            }
        }

    }

    public static int[] sort(int[] origin) {
        // arr[0]不放数据
        int[] arr = new int[origin.length + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = origin[i - 1];
        }
        // 构建堆
        buildHeap(arr, arr.length);
        // 排序
        int L = arr.length;
        while (L > 1) {
            exchange(arr, 1, --L);
            sink(arr, 1, L);
        }
        return arr;
    }

    /**
     * 建堆。从右往左 下沉建堆
     */
    private static void buildHeap(int[] arr, int k) {
        for (int i = k / 2; i > 0; i--) {
            sink(arr, i, arr.length);
        }
    }

    /**
     * 上浮
     */
    private static void swim(int[] arr, int k) {
        while (k / 2 > 0) {
            if (!less(arr, k, k - 1) && less(arr, k / 2, k)) {
                exchange(arr, k, k / 2);
                k = k / 2;
            }
        }
    }

    /**
     * 下沉
     */
    private static void sink(int[] arr, int k, int L) {
        while (2 * k < L) {
            int maxIndex = 2 * k;
            if (maxIndex < L - 1 && less(arr, maxIndex, maxIndex + 1)) {
                maxIndex++;
            }
            if (!less(arr, k, maxIndex)) {
                /*
                 * 等价于
                 * k = maxIndex;
                 * continue;
                 */
                break;
            }
            exchange(arr, k, maxIndex);
            k = maxIndex;
        }
    }

    /**
     * ? arr[i] < arr[j]
     */
    private static boolean less(int[] arr, int i, int j) {
        return arr[i] < arr[j];
    }

    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
