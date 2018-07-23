package tests;

import common.jsonLib.EditJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


import static common.Utilities.sDirPath;


/**
 * This test class demonstrates on how do we create new nodes under the test
 */
public class DemoTest {
    @Test
    public void testMethod(){
        System.out.println("Test Demo");
        String sampleJSON = "{\"email\":\"aaa\",\"name\":\"aaaaa\"}";
        String filePath = "test.json";

        try {
            // read the json file
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            System.out.println(jsonObject.toString());

            // get a String from the JSON object
            String firstName = (String) jsonObject.get("firstname");
            System.out.println("The first name is: " + firstName);
            // get a number from the JSON object
            long id =  (long) jsonObject.get("id");
            System.out.println("The id is: " + id);

            jsonObject.put("firstname","doai");

            // get an array from the JSON object
            JSONArray lang= (JSONArray) jsonObject.get("languages");
            // take the elements of the json array
            for(int i=0; i<lang.size(); i++){
                System.out.println("The " + i + " element of the array: "+lang.get(i));
            }
            Iterator i = lang.iterator();
            // take each value from the json array separately
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println("language "+ innerObj.get("lang") +
                        " with level " + innerObj.get("knowledge"));
            }
            // handle a structure into the json object
            JSONObject structure = (JSONObject) jsonObject.get("job");
            System.out.println("Into job structure, name: " + structure.get("name"));

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

    }

    @Test
    public void testEdit(){
        String jsonFile = "/src/test/java/common/jsonData/AuditorSignup.json";
        EditJSON.setValueForKeyInJSON("email","aaaa@aaa.com",sDirPath+jsonFile);
        String [] dataTest = {"KK","LL"};
        EditJSON.setValueForArrayKeyInJson("/payload/user/agreements/0/agreementID","com",sDirPath+"/src/test/java/common/jsonData/AuditorSignup.json");
    }
}


