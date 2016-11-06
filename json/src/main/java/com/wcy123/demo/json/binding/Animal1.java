package com.wcy123.demo.json.binding;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "animalType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Animal1.Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Animal1.Cat.class, name = "cat")})
public interface Animal1 {
    

    String getAnimalType();

    class Dog implements Animal1 {
        private String dogName;

        public String getDogName() {
            return dogName;
        }

        public void setDogName(String dogName) {
            this.dogName = dogName;
        }

        @Override
        public String getAnimalType() {
            return "dog";
        }

        @Override
        public String toString() {
            return "Dog{" + "dogName='" + dogName + '\'' + '}';
        }
    }

    class Cat implements Animal1 {
        private String catName;

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        @Override
        public String getAnimalType() {
            return "cat";
        }

        @Override
        public String toString() {
            return "Cat{" + "catName='" + catName + '\'' + '}';
        }
    }
}
