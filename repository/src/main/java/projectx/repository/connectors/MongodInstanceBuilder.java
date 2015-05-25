package projectx.repository.connectors;

import java.io.IOException;

/**
 * @author proger
 * @since 5/25/15
 */
public class MongodInstanceBuilder {
    private static String dbpath;

    public static Process runMongodInstance(Integer port) throws IOException, InterruptedException {
        String line;
        switch(port){
            case 27018:{
                dbpath = "/rs0-0";
                break;
            }
            case 27019:{
                dbpath = "/rs0-1";
                break;
            }
            case 27020:{
                dbpath = "/rs0-2";
                break;
            }
            default:{
                return null;
            }
        }
        String[] command = {"bash","-c","echo proger| sudo -S mongod --port "
                + port.toString() + " --dbpath " + dbpath + " --replSet rs0 "};
        Process process = Runtime.getRuntime().exec(command);
       // BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //while ((line = input.readLine()) != null) {
          //  System.out.println(line);
       // }
        //input.close();
        return process;
    }




    public static void main(String[] args)
            throws IOException, InterruptedException
    {
       Process p = runMongodInstance(27018);
        Process p1 = runMongodInstance(27019);
        Process p2 = runMongodInstance(27020);
        //p.destroy();
        //p1.destroy();
        //p2.destroy();
    }

}
