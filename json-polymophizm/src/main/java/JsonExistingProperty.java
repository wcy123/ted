import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class JsonExistingProperty {

    public static void main(String[] args) throws JsonProcessingException {
        Zoo zoo = new Zoo();
        zoo.setAnimal(new Dog());
        zoo.setAll(new Animal[] {new Dog(), new Cat()});
        System.out.println(new ObjectMapper().writeValueAsString(zoo));
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
            property = "animalType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")})
    public interface Animal {
        String getAnimalType();

        String getSay();
    }

    @Data
    static class Zoo {
        private Animal animal;
        private Animal[] all;
    }

    static class Dog implements Animal {
        @Override
        public String getAnimalType() {
            return "dog";
        }

        @Override
        public String getSay() {
            return "wangwang";
        }
    }

    static class Cat implements Animal {
        @Override
        public String getAnimalType() {
            return "cat";
        }

        @Override
        public String getSay() {
            return "miao";
        }
    }
}
