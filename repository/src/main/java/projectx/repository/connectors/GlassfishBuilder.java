package projectx.repository.connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author proger
 * @since 5/26/15
 */
public class GlassfishBuilder {

    private String domain ="domain1";
    private String cluster = "cluster1";
   // private String server = "server";
    private String user = "admin";
   // private String name = "~/ProjectX/web/target/web.war";
    private String passwordFilePath = "/home/proger/password.txt";
    private String script="--user "+ user +" --passwordfile " + passwordFilePath +" ";
    /*
    Create password.txt file in /home/proger/ with following
        AS_ADMIN_PASSWORD=adminadmin
        AS_ADMIN_ADMINPASSWORD=adminadmin
        AS_ADMIN_USERPASSWORD=adminadmin
        AS_ADMIN_MASTERPASSWORD=adminadmin
     */
    public void runServerInstanceCommand (String parametr) throws IOException,
            InterruptedException, IllegalArgumentException {
        String[] command = {"bash","-c", "asadmin " + parametr+"-domain " + domain };
        Process pb =Runtime.getRuntime().exec(command);
        printOutput(pb);
        pb.waitFor();
    }
     public void runNodeInstanceCommand(String parameter,String node) throws IOException,
            InterruptedException, IllegalArgumentException{
        String[] command = {"bash","-c", "asadmin " + script  + parameter +"-instance " + node};
        Process pb =Runtime.getRuntime().exec(command);
         printOutput(pb);
         pb.waitFor();
    }
    public void runClusterInstanceCommand(String parameter) throws IOException,
            InterruptedException, IllegalArgumentException{
        String[] command = {"bash","-c", "asadmin " + script + parameter + "-cluster " + cluster};
        System.out.println("asadmin " + script);
        Process pb =Runtime.getRuntime().exec(command);
        printOutput(pb);
        pb.waitFor();

    }
    public static void printOutput(Process pb) throws IOException{
    String line;
    BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));

    while ((line = input.readLine()) != null) {

        System.out.println(line);
    }
    input.close();}
/*
public static void main(String[] args)
            throws IOException, InterruptedException, IllegalArgumentException
    {
       // GlassfishBuilder builder = new GlassfishBuilder();
      //builder.runServerInstanceCommand("start");
      //builder.runClusterInstanceCommand("start");
        //builder.runNodeInstanceCommand("stop","node1");


    }*/
}
