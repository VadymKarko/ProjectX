package projectx.repository.connectors;

import java.io.IOException;

/**
 * @author proger
 * @since 5/27/15
 */
public class SetUp {
    private  MongodBuilder mongod = new MongodBuilder();
    private  GlassfishBuilder glassfish = new GlassfishBuilder();
    private int nodePort0 = 27018;
    private int nodePort1 = 27019;
    private int nodePort2 = 27020;

    public  void runAllReplNodes() throws IOException, InterruptedException {
        mongod.runMongod(nodePort0);
        mongod.runMongod(nodePort1);
        mongod.runMongod(nodePort2);
    }

    public  void stopAllReplNodes() throws IOException, InterruptedException {
        mongod.stopMongod(nodePort0);
        mongod.stopMongod(nodePort1);
        mongod.stopMongod(nodePort2);
    }

    public  void startReplNode(int port) throws IOException, InterruptedException {
        mongod.runMongod(port);

    }

    public  void stopReplNode(int port) throws IOException, InterruptedException {
        mongod.stopMongod(port);

    }

    public  void startNode(String node) throws IOException, InterruptedException {
        glassfish.runNodeInstanceCommand("start", node);

    }

    public  void startCluster() throws IOException, InterruptedException {
        glassfish.runClusterInstanceCommand("start");

    }

    public  void stopCluster() throws IOException, InterruptedException {
        glassfish.runClusterInstanceCommand("stop");

    }

    public  void startDomain() throws IOException, InterruptedException {
        glassfish.runServerInstanceCommand("start");

    }

    public  void stopDomain() throws IOException, InterruptedException {
        glassfish.runServerInstanceCommand("stop");

    }

    public void testConfiguration() throws IOException, InterruptedException {
        mongod.runMongod(27019);
        glassfish.runNodeInstanceCommand("stop","node1");

    }
  /*  public static void main(String[] args)
            throws IOException, InterruptedException, IllegalArgumentException
    {
        SetUp setup = new SetUp();
        setup.testConfiguration();

    }*/
}
