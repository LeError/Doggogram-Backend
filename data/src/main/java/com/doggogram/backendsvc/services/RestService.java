package com.doggogram.backendsvc.services;

public interface RestService<T> {

    int count();
    T getAllItems();

}
