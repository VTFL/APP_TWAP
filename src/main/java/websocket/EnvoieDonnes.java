package websocket;

/**
 * Created by valentinpitel on 04/11/2016.
 */

import beans.Passages;
import com.google.gson.Gson;
import db.DatabaseManager;
import org.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint(value="/websocket/EnvoieDonnes",configurator=ConfigurationWs.class)
public class EnvoieDonnes {
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
    private static Set<Session> allSessions;
    private DatabaseManager dbm = null;

    @OnMessage
    public void onMessage(Session session, String jsonMessage) {
    
    }
    @OnClose
    public void onClose(final Session session) {
       System.out.println("Close connection for client: "+
                session.getId());

    }
    @OnOpen
    public void showTimer(Session session){
        dbm= new DatabaseManager();

        // on envoie toute les secondes
        timer.scheduleAtFixedRate(
                () -> sendTimeToAll(session),0,1, TimeUnit.SECONDS);
    }

    private void sendTimeToAll(Session session ){

        ArrayList<Passages> lst_passages = new ArrayList<>();
        try{
            if (session.isOpen()) {
                Gson gson = new Gson();
                //session.getUserProperties().get("cookie")[0];

                lst_passages = dbm.getPassages();
                Passages p;
               /* SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'");
                for (int i = 0; i < lst_passages.size(); i++) {
                    p = lst_passages.get(i);
                    if(isValidFormat("yyyy-MM-dd'",p.getDate()))
                        p.setDate(sdf1.format(sdf2.parse(p.getDate())));
                }*/
                String json = gson.toJson(lst_passages);
                session.getBasicRemote().sendText(json);
            }else{
                if (session.isOpen())
                    session.close();
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
              SimpleDateFormat sdf = new SimpleDateFormat(format);
           date = sdf.parse(value);
              if (!value.equals(sdf.format(date))) {
                        date = null;
                    }
            } catch (ParseException ex) {
               System.out.println(ex);
        }
        return date != null;
    }

}
