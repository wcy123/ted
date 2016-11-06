package com.wcy123.demo.json.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class FindAllSubtypesTest {
    @Rule
    public TestLogger logger = new TestLogger(FindAllSubtypesTest.class);

    @Test
    public void main() throws Exception {
        final List<Class<?>> allSubtypes = findAllSubtypes(Animal3.class);
        for (Class<?> subtype : allSubtypes) {

        }
    }

    List<Class<?>> findAllSubtypes(Class<?> clazz) throws ClassNotFoundException {
        List<Class<?>> result = new ArrayList();
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));

        Set<BeanDefinition> components =
                provider.findCandidateComponents(clazz.getPackage().getName());
        for (BeanDefinition component : components) {
            Class cls = Class.forName(component.getBeanClassName());
            result.add(cls);
        }
        return result;
    }
}
