package com.bartoszbalukiewicz.appsensor;

import com.google.gson.Gson;
import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.reporting.WebSocketJsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.net.URI;

/**
 * Created by Bartek on 01.11.2016.
 */
@ClientEndpoint
@Component
public class WebSocketClientAppSensor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private boolean webSocketInitialized = false;

    private Session localSession = null;

    private static final String WEBSOCKET_HOST_URL = "ws://localhost:8085/dashboard";


    private Gson gson = new Gson();

    @PostConstruct
    private void connect() {
        if (! webSocketInitialized) {
            WebSocketContainer client = ContainerProvider.getWebSocketContainer();

            try {
                localSession = client.connectToServer(this, new URI(WEBSOCKET_HOST_URL));
                webSocketInitialized = true;
            } catch (Exception e) {
                logger.warn("Connection to websocket host [" + WEBSOCKET_HOST_URL + "] failed.", e);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {

        WebSocketJsonObject object = gson.fromJson(message, WebSocketJsonObject.class);
        if(object.getDataType() .equals("response"))
            logger.info("GOT MESSAGE FROM APP SENSOR SERVER: " + message);

        return null;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        webSocketInitialized = false;
    }


    @Scheduled(fixedDelay = 5000)
    public void reconnectIfNecessary() {
        connect();
    }
}
