package mthread;

public class SyncBlockExample {
    public static void main(String[] args) {
        SObject sObject = new SObject();
        SimpleThreadIncr t1 = new SimpleThreadIncr(sObject, "Thread 1", 10, 1);
        SimpleThreadIncr t2 = new SimpleThreadIncr(sObject, "Thread 2", 10, 1);
        SimpleThreadIncr t3 = new SimpleThreadIncr(sObject, "Thread 3", 10, -1);
        SimpleThreadIncr t4 = new SimpleThreadIncr(sObject, "Thread 4", 10, -1);
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
    }


}

class SObject {
    private Integer incr=0;

    public synchronized void incr(String threadName, int val) {
        int curr = 0;
        while (curr < 5) {
            incr += val;
            curr++;
            System.out.println(threadName + " increment " + incr);
        }
    }
}

class SimpleThreadIncr implements Runnable {
    SObject obj;
    String name;
    int max;
    int val;

    public SimpleThreadIncr(SObject obj, String name, int max, int val) {
        this.obj = obj;
        this.name = name;
        this.max = max;
        this.val = val;
    }

    @Override
    public void run() {
        obj.incr(name, val);
    }
}