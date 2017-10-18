package com.nowcoder.toutiao.async;

import java.util.List;

/**
 * Created by lenovo on 2017/10/10.
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
