package com.wcy123.demo.json.binding;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
        property = "animalType")
public interface Animal3 {
    @JsonTypeName("Dog")
    class Dog implements Animal3 {
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

    @JsonTypeName("Cat")
    class Cat implements Animal3 {
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
