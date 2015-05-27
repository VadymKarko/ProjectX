package projectx.repository.connectors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author proger
 * @since 5/25/15
 */
public class MongodBuilder {
    private static String dbpath;
    private static int nodePort0 = 27018;
    private static int nodePort1 = 27019;
    private static int nodePort2 = 27020;
    private static Map<Integer, Process> mongodMap = new HashMap<Integer, Process>();
    private static MongodBuilder instance;
    private static String replicaSet = "rs0";

    public static MongodBuilder getInstance(){
        if (instance == null){instance = new MongodBuilder();}
        return instance;
    }
    public  void runMongod(int port) throws IOException,
            InterruptedException, IllegalArgumentException {
        if (port != nodePort0 && port != nodePort1 && port != nodePort2){
            throw  new IllegalArgumentException("Invalid port");
        }
        switch(port){case 27018:{dbpath = "/rs0-0";
                                break;}
                     case 27019:{dbpath = "/rs0-1";
                                break;}
                     case 27020:{dbpath = "/rs0-2";
                                break;} }

        for (int i=0;i<mongodMap.size();i++)
        {
            if (mongodMap.keySet().toArray()[i].equals(port)){ stopMongod(port); }
        }
        String[] command = {"bash","-c","echo proger| sudo -S mongod --port "
                + port + " --dbpath " + dbpath + " --replSet " + replicaSet};
        Process process = Runtime.getRuntime().exec(command);
        mongodMap.put(port,process);
    }


    public static Process getProcess(int port){
        return mongodMap.get(port);
    }

    public  void stopMongod(int port){
        getProcess(port).destroy();
        mongodMap.remove(port);

    }
    public  void showMapEntries(){
        System.out.println(mongodMap.entrySet());
    }



}
