package demo.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.message.GZipEncoder;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public class JerseyClient {

    private static final String SERVER_URL = "http://api.stackexchange.com";
    private static final String API_VERSION = "2.2";

    private static final String PARAM_SOURCE_TYPE = "site";
    private static final String PARAM_SOURCE_VALUE = "stackoverflow";

    public static WebTarget buildClient(ActionTypeEnum action)
    {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);
        clientConfig.register(GZipEncoder.class);
        clientConfig.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
        clientConfig.property(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES.name(), Boolean.FALSE);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        clientConfig.property(ClientProperties.READ_TIMEOUT, 500);

        return ClientBuilder.newClient(clientConfig)
            .target(SERVER_URL)
            .path(String.format("%s/%s", API_VERSION, action.getValue()))
            .queryParam(PARAM_SOURCE_TYPE, PARAM_SOURCE_VALUE);
    }
}
