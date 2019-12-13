package j8;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}