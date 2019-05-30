package cn.com.proxy.proxy.stati.example;

public class ProxyHelloServiceImpl implements IHelloService{
	private IHelloService helloServiceImpl;
	
	private IHandle handle;
	
	public ProxyHelloServiceImpl(IHelloService helloServiceImpl) {
		this.helloServiceImpl = helloServiceImpl;
	}

	public ProxyHelloServiceImpl(IHelloService helloServiceImpl, IHandle handle) {
		this.helloServiceImpl = helloServiceImpl;
		this.handle = handle;
	}

	public void hello(){
		if(handle!=null) handle.before();
//		System.out.println("helloProxy start");
		helloServiceImpl.hello();
		if(handle!=null) handle.end();
//		System.out.println("helloProxy end");
	}
}
