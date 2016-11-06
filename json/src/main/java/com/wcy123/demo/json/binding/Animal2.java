package com.wcy123.demo.json.binding;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
        property = "animalType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Animal2.Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Animal2.Cat.class, name = "cat")})
public interface Animal2 {
    class Dog implements Animal2 {
        private String dogName;

        public String getDogName() {
            return dogName;
        }

        public void setDogName(String dogName) {
            this.dogName = dogName;
        }

        @Override
        public String toString() {
            return "Dog{" + "dogName='" + dogName + '\'' + '}';
        }
    }

    class Cat implements Animal2 {
        private String catName;

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        @Override
        public String toString() {
            return "Cat{" + "catName='" + catName + '\'' + '}';
        }
    }
}
