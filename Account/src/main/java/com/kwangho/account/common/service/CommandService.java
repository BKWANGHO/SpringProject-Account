package com.kwangho.account.common.service;

import com.kwangho.account.common.component.Messenger;

public interface CommandService<T> {
    Messenger save (T t) ;
    Messenger deleteById (Long id);
    Messenger modify (T t);


}
