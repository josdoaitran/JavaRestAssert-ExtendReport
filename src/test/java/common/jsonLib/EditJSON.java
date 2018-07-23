package common.jsonLib;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.Json;
import javax.json.JsonPointer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;


public class EditJSON {

    public static JSONObject setValueForKeyInJSON(String key, String value, String jsonFile){
        JSONObject jsonObject = null;
        try {
            FileReader reader = new FileReader(jsonFile);
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject.toString());
            jsonObject.put(key,value);
            System.out.println(jsonObject.toString());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Example:
     * AuditorSignUp =>
     * @param keyPointer : /payload/user
     * @param value
     * @param jsonFile
     * @return
     */
    public static javax.json.JsonObject setValueForArrayKeyInJson(String keyPointer, String value, String jsonFile){
        javax.json.JsonObject jsonObject = null;
        javax.json.JsonObject newJsonObject = null;
        try {
            FileReader reader = new FileReader(jsonFile);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObjectRaw = (JSONObject) jsonParser.parse(reader);
            String jsonString = jsonObjectRaw.toJSONString();
            jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
            System.out.println(jsonObject.toString());
            JsonPointer pointer = Json.createPointer(keyPointer);
            newJsonObject = pointer.replace(jsonObject,Json.createValue(value));
            System.out.println(newJsonObject.toString());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return newJsonObject;
    }
}
