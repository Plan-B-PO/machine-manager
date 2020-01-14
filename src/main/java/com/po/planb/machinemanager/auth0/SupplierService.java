package com.po.planb.machinemanager.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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

    public String getRole(String accessToken, String idToken) {
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
            String sub = values.get("sub").toString().substring(6);
            HttpResponse<String> response = null;
            try {
                response = Unirest.get("https://utnickir.eu.auth0.com/api/v2/connections/" + sub + "?fields=options")
                        .header("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5UWTROVFZGTVRORVJEVkJOVE16UkRBNU5rWkZORGt3UWpNeU5qSXpSVFUzUVRCQ1JrSkdOdyJ9.eyJpc3MiOiJodHRwczovL3V0bmlja2lyLmV1LmF1dGgwLmNvbS8iLCJzdWIiOiJiYTlKcEZTS05lUTB2MWFmdWpoOGszMDE1MWl3YTRvSkBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly91dG5pY2tpci5ldS5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTU3OTA0MTc0MCwiZXhwIjoxNTgxNjMzNzQwLCJhenAiOiJiYTlKcEZTS05lUTB2MWFmdWpoOGszMDE1MWl3YTRvSiIsInNjb3BlIjoicmVhZDpjbGllbnRfZ3JhbnRzIGNyZWF0ZTpjbGllbnRfZ3JhbnRzIGRlbGV0ZTpjbGllbnRfZ3JhbnRzIHVwZGF0ZTpjbGllbnRfZ3JhbnRzIHJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMgcmVhZDp1c2Vyc19hcHBfbWV0YWRhdGEgdXBkYXRlOnVzZXJzX2FwcF9tZXRhZGF0YSBkZWxldGU6dXNlcnNfYXBwX21ldGFkYXRhIGNyZWF0ZTp1c2Vyc19hcHBfbWV0YWRhdGEgY3JlYXRlOnVzZXJfdGlja2V0cyByZWFkOmNsaWVudHMgdXBkYXRlOmNsaWVudHMgZGVsZXRlOmNsaWVudHMgY3JlYXRlOmNsaWVudHMgcmVhZDpjbGllbnRfa2V5cyB1cGRhdGU6Y2xpZW50X2tleXMgZGVsZXRlOmNsaWVudF9rZXlzIGNyZWF0ZTpjbGllbnRfa2V5cyByZWFkOmNvbm5lY3Rpb25zIHVwZGF0ZTpjb25uZWN0aW9ucyBkZWxldGU6Y29ubmVjdGlvbnMgY3JlYXRlOmNvbm5lY3Rpb25zIHJlYWQ6cmVzb3VyY2Vfc2VydmVycyB1cGRhdGU6cmVzb3VyY2Vfc2VydmVycyBkZWxldGU6cmVzb3VyY2Vfc2VydmVycyBjcmVhdGU6cmVzb3VyY2Vfc2VydmVycyByZWFkOmRldmljZV9jcmVkZW50aWFscyB1cGRhdGU6ZGV2aWNlX2NyZWRlbnRpYWxzIGRlbGV0ZTpkZXZpY2VfY3JlZGVudGlhbHMgY3JlYXRlOmRldmljZV9jcmVkZW50aWFscyByZWFkOnJ1bGVzIHVwZGF0ZTpydWxlcyBkZWxldGU6cnVsZXMgY3JlYXRlOnJ1bGVzIHJlYWQ6cnVsZXNfY29uZmlncyB1cGRhdGU6cnVsZXNfY29uZmlncyBkZWxldGU6cnVsZXNfY29uZmlncyByZWFkOmhvb2tzIHVwZGF0ZTpob29rcyBkZWxldGU6aG9va3MgY3JlYXRlOmhvb2tzIHJlYWQ6ZW1haWxfcHJvdmlkZXIgdXBkYXRlOmVtYWlsX3Byb3ZpZGVyIGRlbGV0ZTplbWFpbF9wcm92aWRlciBjcmVhdGU6ZW1haWxfcHJvdmlkZXIgYmxhY2tsaXN0OnRva2VucyByZWFkOnN0YXRzIHJlYWQ6dGVuYW50X3NldHRpbmdzIHVwZGF0ZTp0ZW5hbnRfc2V0dGluZ3MgcmVhZDpsb2dzIHJlYWQ6c2hpZWxkcyBjcmVhdGU6c2hpZWxkcyBkZWxldGU6c2hpZWxkcyByZWFkOmFub21hbHlfYmxvY2tzIGRlbGV0ZTphbm9tYWx5X2Jsb2NrcyB1cGRhdGU6dHJpZ2dlcnMgcmVhZDp0cmlnZ2VycyByZWFkOmdyYW50cyBkZWxldGU6Z3JhbnRzIHJlYWQ6Z3VhcmRpYW5fZmFjdG9ycyB1cGRhdGU6Z3VhcmRpYW5fZmFjdG9ycyByZWFkOmd1YXJkaWFuX2Vucm9sbG1lbnRzIGRlbGV0ZTpndWFyZGlhbl9lbnJvbGxtZW50cyBjcmVhdGU6Z3VhcmRpYW5fZW5yb2xsbWVudF90aWNrZXRzIHJlYWQ6dXNlcl9pZHBfdG9rZW5zIGNyZWF0ZTpwYXNzd29yZHNfY2hlY2tpbmdfam9iIGRlbGV0ZTpwYXNzd29yZHNfY2hlY2tpbmdfam9iIHJlYWQ6Y3VzdG9tX2RvbWFpbnMgZGVsZXRlOmN1c3RvbV9kb21haW5zIGNyZWF0ZTpjdXN0b21fZG9tYWlucyByZWFkOmVtYWlsX3RlbXBsYXRlcyBjcmVhdGU6ZW1haWxfdGVtcGxhdGVzIHVwZGF0ZTplbWFpbF90ZW1wbGF0ZXMgcmVhZDptZmFfcG9saWNpZXMgdXBkYXRlOm1mYV9wb2xpY2llcyByZWFkOnJvbGVzIGNyZWF0ZTpyb2xlcyBkZWxldGU6cm9sZXMgdXBkYXRlOnJvbGVzIHJlYWQ6cHJvbXB0cyB1cGRhdGU6cHJvbXB0cyByZWFkOmJyYW5kaW5nIHVwZGF0ZTpicmFuZGluZyByZWFkOmxvZ19zdHJlYW1zIGNyZWF0ZTpsb2dfc3RyZWFtcyBkZWxldGU6bG9nX3N0cmVhbXMgdXBkYXRlOmxvZ19zdHJlYW1zIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.pfVEoTfa7svvHD3GdkEzFnA_S0krIFDeN6hS6glMwySi9rWBFttHVlu3OI_9aJlT8xOSWwm7X3W3_3EBPxOD5e0tslTmHLlMWYaZFmwPml4VH3BmarqEwhZe5k0bXIPDZOiqp49U5ywVkQLwZPUCoK_VbFhjy7_2evVfmIG8dsfqLa1umKmghOyQN6pXJni7Bjq9kevmGoeGUpYKAt28MYD-DMxwKJesMrsRG2N77w-zxVEoeMrlLDDC7PDdZ-93VSXeiUuhktT_PUkv4t92f19OV0nVUyIVBM8wwa9DxP3GlKdnoqCDn2djQFQfLPQGYg3LzeAFCe-tsNOv-OTwWg")
                        .asString();
                System.out.println("ROLE ============== " + response.getBody());
                return response.getBody();
            } catch (UnirestException e) {
                e.printStackTrace();
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
