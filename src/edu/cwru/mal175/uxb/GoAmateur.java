package edu.cwru.mal175.uxb;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by alexlucas on 9/21/16.
 */
public class GoAmateur extends AbstractVideo {



    public static class Builder extends AbstractVideo.Builder {

        public Builder(Integer version) {
            super(version);
        }

        public GoAmateur build() {
            validate();
            return new GoAmateur(getThis());
        }

        protected Builder getThis() {
            return this;
        }
    }

    protected GoAmateur(AbstractPeripheral.Builder builder) {
        super(builder);
    }

    @Override
    public void recv(StringMessage message, Connector connector) {
        areNull(message, connector); //throws excpetion if either is null


        if(connectorPresent(connector)) {
            LOGGER.log(Level.INFO, "GoAmateur does not understand string messages:" + message.getString() + connector.getIndex());
        }

    }

    @Override
    public void recv(BinaryMessage message, Connector connector) {
        areNull(message, connector); //throws excpetion if either is null

        if(connectorPresent(connector)) {
            List<Connector> conns = getConnectors();
            for (Connector conn: conns) {
                if(conn.getPeer().isPresent()){
                    conn.getPeer().get().recv(new BinaryMessage(new BigInteger("293")));
                    LOGGER.log(Level.INFO, "GoAmateur has received binary message: "+ message +
                            " and has sent binary message of value 293 to all connectors.");
                }
            }
           // LOGGER.log(Level.INFO, "GoAmateur is not yet active: " + message.getValue());
        }


    }
}
