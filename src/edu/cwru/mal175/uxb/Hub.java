package edu.cwru.mal175.uxb;

import org.junit.Test;
import sun.rmi.runtime.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static edu.cwru.mal175.uxb.DeviceClass.HUB;

/**
 * Created by alexlucas on 9/14/16.
 */
public class Hub extends AbstractDevice<Hub.Builder> {
    public static class Builder extends AbstractDevice.Builder<Builder> {

        private final int ZERO = 0;

        public Builder(Integer version) {
            super(version);
        }

        public Hub build() {
            validate();
            return new Hub(getThis());
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        protected void validate() {
            super.validate(); //validates version

            List<Connector.Type> typeList = this.getConnectors();
            if (ZERO == typeList.size()) {
                throw new IllegalStateException("UXB Hub has no connections.");
            } else if (!typeList.contains(Connector.Type.PERIPHERAL)) {
                throw new IllegalStateException("UXB Hub has no peripheral.");
            } else if (!typeList.contains(Connector.Type.COMPUTER)) {
                throw new IllegalStateException("UXB Hub is not connected to a computer.");
            }
        }
    }


    private Hub(Builder builder) {
        super(builder);
    }

    @Override
    public DeviceClass getDeviceClass() {
        return HUB;
    }

    @Override
    public void recv(StringMessage message, Connector connector) {
        areNull(message, connector); //throws exception if either is null

        if (!forwardMessage(message, connector)) {
            LOGGER.log(Level.WARNING, "Message not forwarded to " + connector);
        }
        else {
            LOGGER.log(Level.INFO, "Message forwarded to "+ connector);
        }


    }

    @Override
    public void recv(BinaryMessage message, Connector connector) {
        areNull(message, connector); //throws exception if either is null

        if (!forwardMessage(message, connector)) {
            LOGGER.log(Level.WARNING, "Message not forwarded to " + connector);
        }
        else {
            LOGGER.log(Level.INFO, "Message forwarded to "+ connector);
        }


    }

    private boolean forwardMessage(Message message, Connector connector) {
        if (connectorPresent(connector)) {
            List<Connector> conns = getConnectors();
            for (Connector conn : conns) {
                if (!conn.equals(connector)) {
                    if (conn.getPeer().isPresent()) {
                        conn.getPeer().get().recv(message);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
