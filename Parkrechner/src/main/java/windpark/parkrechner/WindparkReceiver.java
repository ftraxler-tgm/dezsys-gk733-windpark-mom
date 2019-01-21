package windpark.parkrechner;

import javax.jms.*;
import javax.swing.*;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Component
public class WindparkReceiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(WindparkReceiver.class);

    @JmsListener(destination = "windengine", containerFactory = "myFactory")
    public void windengineMessage(ActiveMQTextMessage data) {

        try {
            Thread.sleep(100);
            String m = data.getText();
            LOGGER.info("'subscriber1' received message='{}'", m);
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
                if(linenumber>=30){
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
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());//exception handling left as an exercise for the reader
        }



    }

}
