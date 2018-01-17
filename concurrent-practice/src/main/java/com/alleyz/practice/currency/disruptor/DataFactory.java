package com.alleyz.practice.currency.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * date: 2018-01-14
 * author: alleyz
 * email: alleyz@126.com
 */
public class DataFactory implements EventFactory<DataBean> {
    @Override
    public DataBean newInstance() {
        return new DataBean();
    }
}
