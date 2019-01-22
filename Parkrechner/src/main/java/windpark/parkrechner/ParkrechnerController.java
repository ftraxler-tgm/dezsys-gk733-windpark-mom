package windpark.parkrechner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ParkrechnerController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(WindparkReceiver.class);

    @RequestMapping("/parkrechner")
    public String windengineMain() {

        String mainPage ="";
            mainPage = "This is the windengine application! (DEZSYS_GK72_WINDPARK-ftraxler-tgm) <br/><br/>" ;


        return mainPage;
    }



    @RequestMapping(value = "/parkrechner/{windengineID}/json",produces = "application/json")
    public String windengineDataJ( @PathVariable String windengineID ) {
            return this.getData(windengineID);


    }
    public String getData(String id) {
        File file = new File("data.json");;
        if (file.exists()) {
            try {

                FileReader fr = new FileReader("data.json");
                BufferedReader br = new BufferedReader(fr);
                String text;
                do {
                    text = br.readLine();
                    //System.out.println("ID: "+id);
                    String[] textid= text.split("\"",5);
                    //System.out.println(text.split("\"",5)[3]);
                    //System.out.println(textid[3].equals(id));
                    if(textid[3].equals(id)) {
                        System.out.println(text);
                        return text;
                    }
                } while (text != null);


            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
            }

        }
        return "{\"ERROR\":\"Windengine not found\"}";
    }
    @RequestMapping(value = "/parkrechner/all",produces = "application/json")
    public String windengineDatas() {
        String all="[";
        String text1 = this.getData("35");
        String text2 = this.getData("41");

        for(int i=1;i<=50;i++){
            String text = this.getData(""+i);
            if(text.equals("{\"ERROR\":\"Windengine not found\"}")){

            }else{
                all+=text+",";
            }
        }
        if(all.substring((all.length()-1),all.length()).equals(",")){
            all = all.substring(0,(all.length()-2));
        }
        all+="}]";
        return all;
    }

}