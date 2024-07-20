package net.onedsix.net;

import net.onedsix.util.Logger;
import net.onedsix.util.Vars;

public class TcpClient {

    private static final Logger L = new Logger(TcpClient.class);

    public TcpClient() {
        L.info("Creating OnedsixClient");

        Vars.network.start();

        Common.register(Vars.network);
    }


}
