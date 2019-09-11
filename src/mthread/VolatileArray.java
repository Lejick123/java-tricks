package mthread;


import java.util.Arrays;

public class VolatileArray {
    public static void main(String[] args) {
        int[] arr = new int[3];
        SimpleThreadArr t1 = new SimpleThreadArr(arr, "t1", 10000, 1);
        SimpleThreadArr t2 = new SimpleThreadArr(arr, "t2", 10000, -1);
        new Thread(t1).start();
        new Thread(t2).start();

    }
}

class SimpleThreadArr implements Runnable {
    int[] arr;
    int max;
    int val;
    String name;

    public SimpleThreadArr(int[] arr, String name, int max, int val) {
        this.arr = arr;
        this.max = max;
        this.val = val;
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < max) {
            i++;
            arr[0] += val;
            arr[1] += val;
            arr[2] += val;
        }
        System.out.println(name+ "  "+Arrays.toString(arr));
    }
}