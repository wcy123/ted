package com.wcy123.demo.json.binding;

import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Animal3Test {
    @Rule
    public TestLogger logger = new TestLogger(Animal3Test.class);

    @Test
    public void main() throws IOException {
        final Animal3.Dog dog = new Animal3.Dog();
        dog.setDogName("wangwang");
        final Animal3.Cat cat = new Animal3.Cat();
        cat.setCatName("mimi");
        final ObjectMapper mapper = new ObjectMapper();

        mapper.registerSubtypes(Animal3.Dog.class);
        mapper.registerSubtypes(Animal3.Cat.class);

        final String jsonDog = mapper.writeValueAsString(dog);
        logger.log("jsonDog is " + jsonDog);
        final String jsonCat = mapper.writeValueAsString(cat);
        logger.log("jsonCat is " + jsonCat);

        final Animal3 dogAnimal = mapper.readValue(jsonDog, Animal3.class);
        final Animal3 catAnimal = mapper.readValue(jsonCat, Animal3.class);

        logger.log("dog is " + dogAnimal);
        logger.log("cat is " + catAnimal);
    }


}
