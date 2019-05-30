package cn.com.proxy.proxy.transation;

import java.lang.reflect.Method;


import cn.com.proxy.datasource.DataSourceUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TransationProxy implements MethodInterceptor {
	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		// 回调方法
		enhancer.setCallback(this);
		// 创建代理对象
		return enhancer.create();
	}

	@Override
	// 回调方法
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Transation transation =method.getAnnotation(Transation.class);
		if(transation==null){
			transation = target.getClass().getAnnotation(Transation.class);
		}
		
		HoldConnection holdConnection = null;
		if(transation!=null){
			holdConnection = DataSourceUtils.getNewHoldConnection();
			holdConnection.addCount();
			holdConnection.getConnection().setAutoCommit(false);
		}
		Object res = null;
		try{
			res = proxy.invokeSuper(obj, args);
			//最后一个代理类出来才能提交
			if(transation!=null && holdConnection.getCount()==1){
				holdConnection.getConnection().commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
			if(holdConnection.getCount()==1){
				holdConnection.getConnection().rollback();
			}
		}finally{
			if(holdConnection.getCount()==1){
				holdConnection.getConnection().close();
			}
			holdConnection.subCount();
		}
		return res;

	}

}
