package collection;

import java.lang.reflect.Field;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Java Program to show How to use PriorityQueue in Java. This example also demonstrate
 * that PriorityQueue doesn't allow null elements and how PriorityQueue keeps elements i.e. ordering.
 *
 * @author
 */
public class PriorityQueueTest {

    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {

        Queue<Item> items = new PriorityQueue<>((i1, i2) -> {
            if (i1.price == i2.price) {
                return i1.name.compareTo(i2.name);
            }
            return i2.price - i1.price;
        });
        Item i1 = new Item("IPone", 900);
        Item i2 = new Item("IPad", 1200);
        Item i3 = new Item("Xbox", 300);
        Item i4 = new Item("Watch", 200);

        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);

        Object obj = getFieldValue(items, "queue");
        Field f = (Field) obj;
        Object[] st = (Object[]) f.get(items);
        st[1] = new Item("Huawey", 100);


        System.out.println("Order of items in PriorityQueue");
        System.out.println(items);

        System.out.println("Element consumed from head of the PriorityQueue : " + items.poll());
        System.out.println(items);

        System.out.println("Element consumed from head of the PriorityQueue : " + items.poll());
        System.out.println(items);

        System.out.println("Element consumed from head of the PriorityQueue : " + items.poll());
        System.out.println(items);

        //items.add(null); // null elements not allowed in PriorityQueue - NullPointerException

    }

    //Access private field by using filed name.
    public static Object getFieldValue(Queue<Item> item, String fieldName) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = item.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    private static class Item {

        private String name;
        private int price;

        public Item(String name, int price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Item other = (Item) obj;
            if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
                return false;
            }
            if (this.price != other.price) {
                return false;
            }
            return true;
        }


        @Override
        public String toString() {
            return String.format("%s: $%d", name, price);
        }

    }
}
