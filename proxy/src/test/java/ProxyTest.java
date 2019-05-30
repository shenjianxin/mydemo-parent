import cn.com.proxy.proxy.stati.example.Handle;
import cn.com.proxy.proxy.stati.example.HelloServiceImpl;
import cn.com.proxy.proxy.stati.example.IHelloService;
import cn.com.proxy.proxy.stati.example.ProxyHelloServiceImpl;
import org.junit.Test;

public class ProxyTest {

	@Test
	public void noProxy() {
		HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
		helloServiceImpl.hello();
	}
	
	@Test
	public void proxy() {
		IHelloService helloServiceImpl = new ProxyHelloServiceImpl(new HelloServiceImpl(),new Handle());
		helloServiceImpl.hello();
	}


}
