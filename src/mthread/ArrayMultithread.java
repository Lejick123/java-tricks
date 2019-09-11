package mthread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayMultithread {
    public static void main(String[] args) {
        List<Integer> arr = Collections.synchronizedList(new ArrayList<>());

        arr.add(0);
        ExtendArray exArr = new ExtendArray(arr);
        SimpleThreadAdd t1 = new SimpleThreadAdd(exArr, 1000, "t1");
        SimpleThreadAdd t2 = new SimpleThreadAdd(exArr, 1000, "t2");
        new Thread(t1).start();
        new Thread(t2).start();
    }
}

class SimpleThreadAdd implements Runnable {
    ExtendArray arr;
    int max;
    String name;

    public SimpleThreadAdd(ExtendArray arr, int max, String name) {
        this.arr = arr;
        this.max = max;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            arr.incrLast(name);
        }
    }


}

class ExtendArray {
    List<Integer> arr;

    public ExtendArray(List<Integer> arr) {
        this.arr = arr;
    }

    public /* synchronized */ void incrLast(String name) {
        Integer pre = arr.get(arr.size() - 1);
        arr.add(pre + 1);
        System.out.println(name + " add " + (pre + 1));
    }
}
