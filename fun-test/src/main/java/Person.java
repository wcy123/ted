import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Person {
    String name;
    Gender gender;
    Integer age;

    enum Gender {
        MALE, FEMALE
    }
}
