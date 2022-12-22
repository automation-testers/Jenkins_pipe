import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;
//import org.openqa.selenium.remote.RemoteWebDriver;


public class ex {
  public static void main(String[] args) {
    UIAutomatorTest();
  }

  public static String generateBasicAuth() {
      String username = "gyanadeeps";
     String apiKey = "15a9ea3f-38fb-450c-9706-08a72ed71950";
    byte[] encodedBytes = Base64.getEncoder().encode((username + ":" + apiKey).getBytes());
    String encodeAuth = new String(encodedBytes);
    return "Basic "  + encodeAuth;
  }

  public static void UIAutomatorTest() {
    try {
      List<String> tests = new ArrayList<String>();
//      tests.add("com.todolist.app.version1");
      //tests.add("ChangeTextBehaviorTest");
      //tests.add("com.example.todolist.MainActivityTest#MainActivityTest");
//      tests.add("com.example.android.testing.uiautomator.BasicSample.test");
      tests.add("ChangeTextBehaviorTest");
//      tests.add("ChangeTextBehaviorTest#testChangeText_sameActivity");

      
      String username = "gyanadeeps";
      String apiKey = "15a9ea3f-38fb-450c-9706-08a72ed71950";
      JSONObject options = new JSONObject();

      options.put("sessionName", "Automation test session");
      options.put("sessionDescription", "");      
      options.put("deviceName", "Pixel 4a");
      options.put("sessionTimeout", 30);
      options.put("testTimeout", 10);
      options.put("retryTimes", 3);
//      options.put("platformVersion", "13");  
      // The given team is used for finding devices and the created session will be visible for all members within the team.
      options.put("groupId", 12170); // Group: AutomationDocs
      options.put("deviceGroup", "KOBITON");
      options.put("app", "kobiton-store:v385600");
      options.put("testRunner", "https://kobiton-devvn.s3-ap-southeast-1.amazonaws.com/apps-test/uiautomator-espresso/esspresso-test-runner.apk"); 
      options.put("continueOnFailure", true);
      options.put("testFramework", "UIAUTOMATOR");
      options.put("sessionTimeout", 1);
      options.put("testTimeout", 1);
      options.put("retryTimes", 1);
//      System.out.println("Creating Driver Test2");
//		driver = new RemoteWebDriver(new URL(kobitonServerUrl), options);

      // If the "tests" is blank, all tests in testRunner will be run

      // To specific some test cases: let's say we have 2 classes Foo & Bar in
      // packages com.abc.xyz.A & com.abc.xyz.B respectively. To run method Test1
      // in class Foo and all tests in class Bar, the input will be:
//            tests = ["com.abc.xyz.A.Foo#Test1", "com.abc.xyz.B.Bar]

      // To specific package com.abc.xyz.A, the input will be:
//            tests = ["-e package com.abc.xyz.A"]   
      options.put("tests", tests);

      JSONObject configuration = new JSONObject();
      configuration.put("configuration", options);

      // Access https://github.com/kobiton/samples/tree/master/uiautomator-espresso/java to learn more about configuration of Java language. 


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
      Process process=Runtime.getRuntime().exec("");
      process.destroy();
     //in.close();
      //in.quit;
      
      

      System.out.println("Result: " + response.toString());
      con.disconnect();
    } catch (Exception ex) {
      System.out.println(ex.toString());
//      System.out.println(ex.getCause().getMessage());
    }
    
    System.out.println(":::::::::::::::::::::::::");
  }
 
}


