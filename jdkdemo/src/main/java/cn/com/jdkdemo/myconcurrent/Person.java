package cn.com.jdkdemo.myconcurrent;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, 武汉市胜意科技发展有限公司
 * Author:   shenjx
 * Date:     2018/4/3 10:51
 * Description:
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -3197931202960757316L;
    private String name;

    private String mobilePhone;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private transient Integer age;


    public Person(String name, Integer age, String mobilePhone) {
        this.name = name;
        this.age = age;
        this.mobilePhone = mobilePhone;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", age=" + age +
                '}';
    }
}
