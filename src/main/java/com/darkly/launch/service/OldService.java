package com.darkly.launch.service;

@org.springframework.stereotype.Service
public class OldService implements Service {
    @Override
    public String doSomething() {
        return "Old value";
    }
}
