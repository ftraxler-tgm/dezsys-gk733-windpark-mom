package windpark.mom;

import javax.jms.*;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import windpark.model.WindengineData;
import windpark.model.WindengineMessage;

@Component
public class WindparkReceiver {

    /*@JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(WindengineMessage message) {
        System.out.println("Received <" + message + ">");
    }*/
    /*@JmsListener(destination = "windengineRec",containerFactory = "myFactory")
    public void windengineMessage(ActiveMQTextMessage data){
        try {
            System.out.println(data.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/

}
	
