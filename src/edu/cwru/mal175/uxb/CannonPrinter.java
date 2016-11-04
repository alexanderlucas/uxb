package edu.cwru.mal175.uxb;

import java.math.BigInteger;
import java.util.logging.Level;

/**
 * Created by alexlucas on 9/21/16.
 */
public class CannonPrinter extends AbstractPrinter {



    public static class Builder extends AbstractPrinter.Builder {

        public Builder(Integer version) {
            super(version);
        }

        public CannonPrinter build() {
            validate();
            return new CannonPrinter(getThis());
        }

        protected Builder getThis() {
            return this;
        }


    }

    protected CannonPrinter(AbstractPeripheral.Builder builder) {
        super(builder);
    }


    @Override
    public void recv(StringMessage message, Connector connector) {
        areNull(message, connector); //throws exception if either is null

        if(connectorPresent(connector)) { //if connector is not present, throws an error
            LOGGER.log(Level.INFO, "Cannon printer has printed the string: " + message.getString() + getVersion());
        }

    }

    @Override
    public void recv(BinaryMessage message, Connector connector) {
        areNull(message, connector); //throws excpetion if either is null

        if(connectorPresent(connector)) {
            BigInteger serialNumber = (BigInteger) getSerialNumber().get();
            BigInteger product = message.getValue().multiply(serialNumber);
            LOGGER.log(Level.INFO, "Cannon printer has printed the binary message: " + product);
        }
    }

}
