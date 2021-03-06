package cn.com.myspringmvc.ioc;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.Arrays;

public class BeanDefinitionReaderExample {
    public static void main(String[] args) {
        BeanDefinitionRegistry register = new SimpleBeanDefinitionRegistry();

        // 读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(register);

        // 资源读取器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("spring-bean.xml");

        // 装载构建Bean的定义
        reader.loadBeanDefinitions(resource);

        System.out.println(Arrays.toString(register.getBeanDefinitionNames()));


    }


}
