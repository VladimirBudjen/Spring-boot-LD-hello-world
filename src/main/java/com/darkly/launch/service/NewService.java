package com.darkly.launch.service;

@org.springframework.stereotype.Service
public class NewService implements Service{
    @Override
    public String doSomething() {
        return "New value";
    }
}
