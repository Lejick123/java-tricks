package j8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        formula.calculate(100);
        formula.sqrt(16);

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        converter = s -> Integer.valueOf(s);
         converted = converter.convert("123");
        System.out.println(converted);   // 123


        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"



        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));


        /**
         * Нахождение максимального и минимального значений
         */
        ArrayList<Integer> testValues = new ArrayList();

        testValues.add(0,15);
        testValues.add(1,1);
        testValues.add(2,2);
        testValues.add(3,100);
        testValues.add(4,50);
        testValues.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                integer++;
            }
        });
        Optional<Integer> maxValue = testValues.stream().max(Integer::compareTo);
        System.out.println("MaxValue="+maxValue);
        Optional<Integer> minValue = testValues.stream().min(Integer::compareTo);
        System.out.println("MinValue="+minValue);
    }
}
