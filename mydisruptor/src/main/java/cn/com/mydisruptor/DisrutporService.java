package cn.com.mydisruptor;

import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

public class DisrutporService {


    private Disruptor<Event> disruptor;

    public DisrutporService(){

        WaitStrategy waitStrategy=new YieldingWaitStrategy();

        disruptor=new Disruptor(Event.eventFactory,1024,threadFactory, ProducerType.MULTI,waitStrategy);
    }


    public static final ThreadFactory threadFactory=new ThreadFactory(){
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    };


}
