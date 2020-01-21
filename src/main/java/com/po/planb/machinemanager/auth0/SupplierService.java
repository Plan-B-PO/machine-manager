package com.po.planb.machinemanager.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.po.planb.machinemanager.model.role.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService {

    private final AuthAPI auth = new AuthAPI(System.getenv("AUTH_API"),
            System.getenv("AUTH_CLIENT_ID"), System.getenv("AUTH_CLIENT_SECRET"));

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getUserInfo(String accessToken, String idToken) {
        Request<UserInfo> request = auth.userInfo(determineToken(accessToken, idToken));
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

    private String determineToken(String accessToken, String idToken) {
        String id = null;
        if (accessToken != null) {
            id = accessToken;
        } else if (idToken != null) {
            id = idToken;
        }

        return id;
    }

    public String getRole(String accessToken, String idToken) {
        Request<UserInfo> request = auth.userInfo(determineToken(accessToken, idToken));
        try {
            UserInfo info = request.execute();
            Map<String, Object> values = info.getValues();
            String sub = values.get("sub").toString();
            HttpResponse<String> response;
            try {
                response = Unirest.get(System.getenv("AUTH0_USER_ENDPOINT") + sub + "/roles")
                        .header("authorization", System.getenv("MANAGEMENT_API_TOKEN"))
                        .asString();
                String body = response.getBody();
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Role> roles = objectMapper.readValue(body, new TypeReference<>() {
                    });
                    if (!roles.isEmpty()) {
                        return roles.get(0).getName();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return body;
            } catch (UnirestException e) {
                e.printStackTrace();
                logger.error("Role Exception");
                throw new IllegalStateException("Role API Exception");
            }
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
