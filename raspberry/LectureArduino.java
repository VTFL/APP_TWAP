import java.sql.*;
import java.util.Random;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;



public class LectureArduino {
    private static String URL = "jdbc:mysql://defortet.ddns.net/twap";
    private static String LOGIN = "vtfl";
    private static String PASSWORD = "vtfl";
	private static Connection con;
    public static void main(String args[]) throws InterruptedException {
        con = null;
        Random r = new Random();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        //GPIO 24 = port 19 breadboard
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);

        // set shutdown state for this input pin
        myButton.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO 24 PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                //genererMontee(r, "Thibault Defortescu");
            }

        });

	//GPIO 25 = port 26 breadboard
        final GpioPinDigitalInput myButton2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
        myButton2.setShutdownOptions(true);
        myButton2.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println(" --> GPIO 25 PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                //genererMontee(r, "Florian Alline");
            }

        });

	//GPIO 6 = port 25 breadboard
        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);

        // set shutdown state for this input pin
        myButton3.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton3.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO 6 PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                //genererMontee( r, "Lucas Patard");
            }

        });
        
        //GPIO 29 = port 21 breadboard
        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29, PinPullResistance.PULL_DOWN);

        // set shutdown state for this input pin
        myButton4.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton4.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO 29 PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                //genererMontee( r, "Valentin Pitel");
            }

        });

        // keep program running until user aborts (CTRL-C)
        while (true) {
            Thread.sleep(500);
        }
    }

    private static String getDirection(Random r){
        int a = r.nextInt(3);
        String direction = "";
        switch(a){
            case 0 : direction = "La Plage"; break;
            case 1 : direction = "Pré Fleuri"; break;
            case 2 : direction = "Grand Hameau"; break;
        }
        return direction;
    }

    private static String getArret(Random r){
        int a = r.nextInt(7);
        String direction = "";
        switch(a){
            case 0 : direction = "St Roch"; break;
            case 1 : direction = "Hôtel de Ville"; break;
            case 2 : direction = "Palais de Justice"; break;
            case 3 : direction = "Gares"; break;
            case 4 : direction = "Université"; break;
            case 5 : direction = "Rond-Point"; break;
            case 6 : direction = "Place Jenner"; break;
        }
        return direction;
    }


    private static void genererMontee(Random r, String passager){
        try{
            PreparedStatement pst = con.prepareStatement("insert into passages(passager,arret,date,direction) values(?,?,?,?)");
            pst.setString(1,passager);
            pst.setString(2,getArret(r));
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            pst.setDate(3, sqlDate);
            pst.setString(4,getDirection(r));
            pst.execute();
        }catch(Exception e){e.printStackTrace();}
    }
}
