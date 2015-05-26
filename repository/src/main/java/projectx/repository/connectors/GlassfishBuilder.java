package projectx.repository.connectors;

import java.io.IOException;

/**
 * @author proger
 * @since 5/26/15
 */
public class GlassfishBuilder {

    private String domain ="domain1";
    private String cluster = "cluster1";
    private String server = "server";
    private String user = "admin";
    private String name = "~/ProjectX/web/target/web.war";
    private String node1 = "http://localhost:28080/web/index.xhtml";
    private String node2 = "http://localhost:28081/web/index.xhtml";

    public void runGlassfishInstanceCommand (String parametr) throws IOException,
            InterruptedException, IllegalArgumentException {
        String script;
        if (parametr.equals("deploy")){script=parametr + " --target "+ cluster + " --user " +
                                        user + " --name web " + name;}
        else if (parametr.equals("redeploy")){script=parametr + " --target "+ cluster + " --user " +
                user + " --name web " + name;}
        else if (parametr.equals("undeploy")){script=parametr + " --target "+ server + " --user " +
                user + " web ";}
        else if (parametr.equals("start-domain")){script=parametr +" "+ domain;}
        else if (parametr.equals("stop-domain")){script=parametr +" "+ domain;}
        else {throw  new IllegalArgumentException("Invalid command");}
        String[] command = {"bash","-c", "echo proger| sudo -S asadmin " + script };
        Process process = Runtime.getRuntime().exec(command);
    }
    public void runNodeInstanceCommand(String parametr,String node) throws IOException,
            InterruptedException, IllegalArgumentException{
        String script;
        if (parametr.equals("start")){script=parametr + "-instance "+ node;}
        else if (parametr.equals("stop")){script=parametr + "-instance "+ node;}
        else {throw  new IllegalArgumentException("Invalid command");}
        String[] command = {"bash","-c", "echo proger| sudo -S asadmin " + script };
        Process process = Runtime.getRuntime().exec(command);

    }
}
