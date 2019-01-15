package windpark.parkrechner;

import javax.jms.*;
import javax.swing.*;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class WindparkReceiver {

    @JmsListener(destination = "windengine", containerFactory = "myFactory")
    public void windengineMessage(ActiveMQTextMessage data) {
        try {
            Thread.sleep(100);
            String m = data.getText();
            //System.out.println(m);
            this.writeMessage(m);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public synchronized void writeMessage(String message) {
        String content = message;
        String path = "C:/Users/Traxl/Documents/GitHub/dezsys-gk733-windpark-mom-ftraxler-tgm/Parkrechner/src/main/java/windpark/parkrechner/";
        String[] split = message.split(",", 2)[0].split("\"", 5);

        File file = new File(path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i=0;
            while ((st = br.readLine()) != null) {
                i++;

            }
            if(i>=10){
                file.delete();
            }
        }catch (IOException e){
            e.getStackTrace();
        }
        try(FileWriter fw = new FileWriter(path+"data"+split[3]+".json", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(message);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());//exception handling left as an exercise for the reader
        }



    }

}
