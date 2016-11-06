package com.wcy123.demo.json.binding;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Animal1Test {
    @Rule
    public TestLogger logger = new TestLogger(Animal1Test.class);

    @Test
    public void main() throws IOException {

        final Animal1.Dog dog = new Animal1.Dog();
        dog.setDogName("wangwang");
        final Animal1.Cat cat = new Animal1.Cat();
        cat.setCatName("mimi");
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonDog = mapper.writeValueAsString(dog);
        logger.log("jsonDog is " + jsonDog);
        final String jsonCat = mapper.writeValueAsString(cat);
        logger.log("jsonCat is " + jsonCat);

        final Animal1 dogAnimal = mapper.readValue(jsonDog, Animal1.class);
        final Animal1 catAnimal = mapper.readValue(jsonCat, Animal1.class);

        logger.log("dog is " + dogAnimal);
        logger.log("cat is " + catAnimal);
    }
}
