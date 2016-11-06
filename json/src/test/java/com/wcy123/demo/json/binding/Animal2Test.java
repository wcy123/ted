package com.wcy123.demo.json.binding;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Animal2Test {
    @Rule
    public TestLogger logger = new TestLogger(Animal2Test.class);

    @Test
    public void main() throws IOException {
        final Animal2.Dog dog = new Animal2.Dog();
        dog.setDogName("wangwang");
        final Animal2.Cat cat = new Animal2.Cat();
        cat.setCatName("mimi");
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonDog = mapper.writeValueAsString(dog);
        logger.log("jsonDog is " + jsonDog);
        final String jsonCat = mapper.writeValueAsString(cat);
        logger.log("jsonCat is " + jsonCat);

        final Animal2 dogAnimal = mapper.readValue(jsonDog, Animal2.class);
        final Animal2 catAnimal = mapper.readValue(jsonCat, Animal2.class);

        logger.log("dog is " + dogAnimal);
        logger.log("cat is " + catAnimal);
    }
}
