package websocket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

public class ConfigurationWs extends ServerEndpointConfig.Configurator {
   /* @Override
    public void modifyHandshake(ServerEndpointConfig config,
                                HandshakeRequest request,
                                HandshakeResponse response)
    {
        Map<String,List<String>> headers = request.getHeaders();
        config.getUserProperties().put("cookie",headers.get("cookie"));
    }*/
}
