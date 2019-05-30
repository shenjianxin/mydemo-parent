package cn.com.proxy.proxy.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Cglib动态代理 
	JDK的动态代理机制只能代理实现了接口的类，
	而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，
	他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，
	但因为采用的是继承，所以不能对final修饰的类进行代理。 

 * @author Administrator
 *
 */
public class DemoJDKProxy implements InvocationHandler {
	private Object target;  
	 /** 
	* 绑定委托对象并返回一个代理类 
	* @param target 
	 * @return 
	*/  
	 public Object bind(Object target) {  
		 this.target = target;  
	     //取得代理对象  
		 return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
	              target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)  
	 }  

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		 System.out.println("开启事务");
		 Object result=method.invoke(target, args);  
		 System.out.println("结束事务"); 
		 return result;
	}  


}
