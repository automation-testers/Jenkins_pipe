import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;

public class Espresso {
  public static void main(String[] args) {
    UIAutomatorTest();
  }

  public static String generateBasicAuth() {
      String username = "gyanadeeps";
     String apiKey = "15a9ea3f-38fb-450c-9706-08a72ed71950";
    byte[] encodedBytes = Base64.getEncoder().encode((username + ":" + apiKey).getBytes());
    String encodeAuth = new String(encodedBytes);
    return "Basic " + encodeAuth;
  }

  public static void UIAutomatorTest() {
    try {
      List<String> tests = new ArrayList<String>();
      //tests.add("com.todolist.app.version1");
      //tests.add("ChangeTextBehaviorTest");
      tests.add("com.example.todolist.MainActivityTest#MainActivityTest");

       JSONObject options = new JSONObject();

      options.put("sessionName", "Automation test session");
      options.put("sessionDescription", ""); 
      options.put("noReset", true);
      options.put("fullReset", false);     
      options.put("deviceName", "Galaxy S20+ 5G (Payload Enabled)");
      // The tag is used for finding devices and the user can input only one tag. 
      // For example, the data value will be inputted: tagName="TagName1"
      options.put("tagName", "");
//      options.put("platformVersion", "13");  
      // The given team is used for finding devices and the created session will be visible for all members within the team.
      options.put("groupId", 12170); // Group: AutomationDocs
      options.put("deviceGroup", "ORGANIZATION");
      options.put("app", "https://kobiton-us-east.s3.amazonaws.com/users/220465/apps/todoEspresso-c1e0f3d0-7c4b-11ed-a67d-4d6b545f9ede.apk");
      options.put("testRunner", "https://kobiton-us-east.s3.amazonaws.com/users/220465/apps/todoEspresso-c1e0f3d0-7c4b-11ed-a67d-4d6b545f9ede.apk"); 
      options.put("continueOnFailure", true);
      options.put("testFramework", "UIAUTOMATOR");

      // If the "tests" is blank, all tests in testRunner will be run

      // To specific some test cases: let's say we have 2 classes Foo & Bar in
      // packages com.abc.xyz.A & com.abc.xyz.B respectively. To run method Test1
      // in class Foo and all tests in class Bar, the input will be:
//            tests = ["com.abc.xyz.A.Foo#Test1", "com.abc.xyz.B.Bar]



      options.put("tests", tests);

      JSONObject configuration = new JSONObject();
      configuration.put("configuration", options);

      // Access https://github.com/kobiton/samples/tree/master/uiautomator-espresso/java to learn more about configuration of Java language. 

      

      String url = "https://api.kobiton.com/hub/session";
      URL uri = new URL(url);
      HttpURLConnection con = (HttpURLConnection) uri.openConnection();

      con.setDoOutput(true);
      con.setDoInput(true);
      con.setUseCaches(false);

     
      String postData = configuration.toString();

      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Accept", "application/json");
      con.setRequestProperty("Authorization", generateBasicAuth());
      OutputStream os = con.getOutputStream();
      os.write(postData.getBytes());
      os.flush();

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      con.disconnect();

      System.out.println("Result: " + response.toString());
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}
