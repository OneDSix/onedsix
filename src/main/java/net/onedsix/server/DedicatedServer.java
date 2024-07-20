package net.onedsix.server;

import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.Vars;

@Slf4j
public class DedicatedServer implements Runnable {

    public DedicatedServer() {
        Vars.serverInit();
		this.run();
    }

    /** OnDSix's Dedicated Server. */
    @Override
    public void run() {
    }
}
