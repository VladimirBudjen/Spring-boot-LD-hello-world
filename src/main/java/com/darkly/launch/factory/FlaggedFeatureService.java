package com.darkly.launch.factory;

import com.darkly.launch.service.MyFeatureFlagService;
import com.darkly.launch.service.NewService;
import com.darkly.launch.service.OldService;
import com.darkly.launch.service.Service;
import org.springframework.stereotype.Component;

@Component
public class FlaggedFeatureService extends FeatureFlagFactoryBean<Service>{
    public FlaggedFeatureService(MyFeatureFlagService MyFeatureFlagService) {
        super(
                Service.class,
                MyFeatureFlagService,
                new NewService(),
                new OldService());
    }
}
