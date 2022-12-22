package com.darkly.launch.factory;

import com.darkly.launch.service.MyFeatureFlagService;
import com.darkly.launch.service.NewService;
import com.darkly.launch.service.OldService;
import com.darkly.launch.service.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FlaggedFeatureService extends FeatureFlagFactoryBean<Service>{

    public FlaggedFeatureService(MyFeatureFlagService MyFeatureFlagService, NewService newService, OldService oldService) {
        super(
                Service.class,
                MyFeatureFlagService,
                newService,
                oldService);

    }
}
