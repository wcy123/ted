import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SortPersonTest {
    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Person.Gender.FEMALE, 20),
                new Person("Sara", Person.Gender.FEMALE, 22),
                new Person("Bob", Person.Gender.MALE, 20),
                new Person("Paula", Person.Gender.FEMALE, 32),
                new Person("Paul", Person.Gender.MALE, 32),
                new Person("JACk", Person.Gender.MALE, 2),
                new Person("JACK", Person.Gender.MALE, 72),
                new Person("Jill", Person.Gender.FEMALE, 12));
    }

    public static <T, U extends Comparable<? super U>> MyComparator<T> myComparing(
            Function<? super T, ? extends U> fKey) {
        return (t1, t2) -> fKey.apply(t1).compareTo(fKey.apply(t2));
    }

    public static void main(String[] args) {

        createPeople().stream().sorted(
                myComparing(Person::getGender)
                        .myReversed()
                        .myThenComparing(Person::getAge)
                        .myThenComparing(Person::getName))
                .forEach(System.out::println);
    }

    interface MyComparator<T> extends Comparator<T> {
        default MyComparator<T> myReversed() {
            return (t1, t2) -> this.compare(t2, t1);
        }

        default <U extends Comparable<? super U>> MyComparator<T> myThenComparing(
                Function<? super T, ? extends U> fKey) {
            return (t1, t2) -> {
                final int r1 = compare(t1, t2);
                return r1 == 0 ? myComparing(fKey).compare(t1, t2) : r1;
            };
        }
    }
}
