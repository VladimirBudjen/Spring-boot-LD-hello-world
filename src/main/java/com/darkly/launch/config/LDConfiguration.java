package com.darkly.launch.config;

import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.*;
import com.launchdarkly.sdk.server.integrations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class LDConfiguration {
    @Value("${SDK_KEY}")
    String SDK_KEY;

    @Bean
    public LDClient getClient(){
        LDConfig config = new LDConfig.Builder()
                .dataStore(
                        Components.persistentDataStore(
                                Redis.dataStore().uri(URI.create("redis://redis:6379"))
                        ).cacheSeconds(30)
                )
                .build();
        return new LDClient(SDK_KEY, config);
    }
}
