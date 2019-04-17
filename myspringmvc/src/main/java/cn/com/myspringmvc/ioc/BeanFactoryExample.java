package cn.com.myspringmvc.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;

public class BeanFactoryExample {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("spring-bean.xml");
//        reader.loadBeanDefinitions("spring-bean.xml");


        reader.setResourceLoader(resourceLoader);
        reader.loadBeanDefinitions("spring-bean.xml");


        Bean1 bean1 = (Bean1) factory.getBean("bean1");
        System.out.println(bean1.getClass().getSimpleName());


    }

}
