package cn.com.mydisruptor;

import com.lmax.disruptor.EventFactory;

public class Event {

    //事件数据
    private Object data;

    public void setData(Object data){
        this.data=data;
    }



    final static EventFactory<Event> eventFactory =new EventFactory<Event>() {
        @Override
        public Event newInstance() {
            return new Event();
        }
    };


}
