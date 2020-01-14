package com.po.planb.machinemanager.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SupplierService {

    private final AuthAPI auth = new AuthAPI("utnickir.eu.auth0.com",
            "iiQzTcmBYGq5vN4sfbLlOMB9D5VuJWRD", "7FiVcecUlgvH5yc2allJHwnDLoSvoX9Gw06Cqbp5Td7rMvthw08LhfhaNDOG9Oxf");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getUserInfo(String accessToken, String idToken) {
        String id = null;
        if (accessToken != null) {
            id = accessToken;
        } else if (idToken != null) {
            id = idToken;
        }

        assert id != null;
        Request<UserInfo> request = auth.userInfo(id);
        try {
            UserInfo info = request.execute();
            Map<String, Object> values = info.getValues();
            return values.get("sub").toString().substring(6);
        } catch (APIException exception) {
            logger.error("API Exception");
            throw new IllegalStateException("Auth0API Exception");
            // api error
        } catch (Auth0Exception exception) {
            // request error
            logger.error("Request Exception");
            throw new IllegalStateException("Auth0Exception Request Exception");
        }
    }
}
