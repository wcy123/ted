import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class JsonUseProperty {

    public static void main(String[] args) throws IOException {
        Zoo zoo = new Zoo();
        zoo.setLeader(new Dog());
        zoo.setFollowers(new Animal[] {new Dog(), new Cat()});
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(zoo);
        System.out.println(json);
        final Zoo zoo2 = mapper.readValue(json, Zoo.class);
        System.out.println(zoo2);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT,
            property = "animalType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")})
    @JsonIgnoreProperties(ignoreUnknown = true)
    public interface Animal {
        String getAnimalType();

        String getName();

        void setName(String name);
    }

    @Data
    static class Zoo {
        private Animal leader;
        private Animal[] followers;
    }

    static class Dog implements Animal {
        private String name = "wangwang";

        @Override
        public String getAnimalType() {
            return "dog";
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
    }

    static class Cat implements Animal {
        private String name = "miao";

        @Override
        public String getAnimalType() {
            return "cat";
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
    }
}
