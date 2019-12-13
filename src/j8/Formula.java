package j8;

@Hint("hint1")
@Hint("hint2")
interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}