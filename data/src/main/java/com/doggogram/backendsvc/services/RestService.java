package com.doggogram.backendsvc.services;

import javax.transaction.Transactional;

@Transactional
public interface RestService<T> {

    int count();
    T getAllItems();

}
