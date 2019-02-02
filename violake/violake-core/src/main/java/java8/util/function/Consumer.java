package java8.util.function;

@FunctionalInterface
public interface Consumer<T> {

    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        if (after == null) {
            throw new NullPointerException("after is null");
        }
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}