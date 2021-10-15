package net.clock.mod;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.clock.mod.client.clockClient;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFile {
    public void write(int x , int y, int colour){
        JSONObject object = new JSONObject();
        object.put("x",x);
        object.put("y",y);
        object.put("colour",colour);
        try (FileWriter file = new FileWriter("ClockMod.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(object.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int[] read(){
        int x;
        int y;
        int colour;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("ClockMod.json"))
        {
            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            long x1 = (Long)jsonObject.get("x");
            long y1 = (Long)jsonObject.get("y");
            long colour1 = (Long)jsonObject.get("colour");
            x = (int)x1;
            y = (int)y1;
            colour = (int)colour1;




            //Iterate over employee array


        } catch (FileNotFoundException e) {
            x = 0;
            y = 0;
            colour = 1;
        } catch (IOException e) {
            x = 0;
            y = 0;
            colour = 1;
        } catch (ParseException e) {
            x = 0;
            y = 0;
            colour = 1;
        }
        int[] arr = new int[3];
        arr[0] = x;
        arr[1] = y;
        arr[2] = colour;
        return arr;
    }

}
