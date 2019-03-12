package windpark.parkrechner;

import javax.jms.*;
import javax.swing.*;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import windpark.Gk73Application;

import java.io.*;



@Component
public class WindparkReceiver {

    public static String pid="01";
    @Autowired
    private Gk73Application sender;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(WindparkReceiver.class);

    @JmsListener(destination = "windengine", containerFactory = "myFactory")
    public void windengineMessage(ActiveMQTextMessage data) {

        try {
            Thread.sleep(100);
            String m = data.getText();

            m = m.substring(0,m.length()-1);

            m+=",\"parkrechnerId\":\""+pid+"\"}";


            LOGGER.info("Windengine Data='{}'", m);
            this.writeMessage(m);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public synchronized void writeMessage(String message) {
        String content = message;

        File file = new File("data.json");
        try {
            if(file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);

                int linenumber = 0;

                while (lnr.readLine() != null){
                    linenumber++;
                }
                if(linenumber>=8){
                    System.out.println("Delete");
                    BufferedWriter writer = new BufferedWriter(new FileWriter("data.json"));
                    writer.write("");
                    writer.close();
                }

                //System.out.println("Total number of lines : " + linenumber);

                lnr.close();
            }else{
                System.out.println("File doesn't exists");
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                message+="\n"+everything;

                out.println(message);

            } catch (IOException e) {
                System.out.println(e.getMessage());//exception handling left as an exercise for the reader
            }


        } catch (Exception e){
            e.getStackTrace();
        }




    }

}
