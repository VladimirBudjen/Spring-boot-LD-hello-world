package com.darkly.launch.service;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.LDClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import static com.darkly.launch.util.Flags.MY_SERVICE_FLAG;
import static com.darkly.launch.util.UtilStrings.SESSION_USER;

@Component
public class MyFeatureFlagService implements FlagService {
    private final LDClient ldClient;
    private final HttpSession httpSession;

    public MyFeatureFlagService(LDClient ldClient, HttpSession httpSession) {
        this.ldClient = ldClient;

        this.httpSession = httpSession;
    }

    @Override
    //TODO use flag with boolean values
    public Boolean isNewServiceEnabled() {
        var user = getLDUser();
        var json = ldClient.jsonValueVariation(MY_SERVICE_FLAG, user, null).toJsonString();
        System.out.println(json);
        return json.equals("[{},{}]");
    }

    private LDUser getLDUser() {
        return (LDUser) httpSession.getAttribute(SESSION_USER);
    }

}
