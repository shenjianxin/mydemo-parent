package cn.com.proxy.bean;

import cn.com.proxy.bean.annotation.Autowired;
import cn.com.proxy.proxy.transation.Transation;
import cn.com.proxy.proxy.transation.TransationProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class BeanFactory {
	public static Object getBean(Class clss) throws Exception{
		Method[] methods =  clss.getMethods();
		Transation transation  = (Transation)clss.getAnnotation(Transation.class);
		if(transation==null){
			for(Method method : methods){
				transation =method.getAnnotation(Transation.class);
				if(transation!=null){
					break;
				}
			}
		}
		Object o ;
		if(transation!=null){
			TransationProxy transationProxy = new TransationProxy();
			o  = transationProxy.getInstance(clss.newInstance());
		}else{
			o = clss.newInstance();
		}
		for (Field field : clss.getDeclaredFields()) {  
			Autowired autowired = field.getAnnotation(Autowired.class);
			
			if(autowired!=null){
				field.setAccessible(true);
				field.set(o, field.getType().newInstance());
			}
		}
		
		return o;
	}
}
