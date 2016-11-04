package edu.cwru.mal175.uxb;

import edu.cwru.mal175.uxb.AbstractPrinter;

import java.math.BigInteger;
import java.util.logging.Level;

/**
 * Created by alexlucas on 9/18/16.
 */
public class SisterPrinter extends AbstractPrinter {


    public static class Builder extends AbstractPrinter.Builder {

        public Builder(Integer version){
            super(version);
        }

        public SisterPrinter build() {
            validate();
            return new SisterPrinter(getThis());

        }

        protected Builder getThis(){
            return this;
        }

    }

    protected SisterPrinter(AbstractPeripheral.Builder builder) {
        super(builder);
    }

    @Override
    public void recv(StringMessage message, Connector connector) {
        areNull(message, connector); //throws exception if either is null


        if(connectorPresent(connector)) {
            LOGGER.log(Level.INFO, "Sister printer has printed the string: " + message.getString() + getSerialNumber().get());
        }

    }

    @Override
    public void recv(BinaryMessage message, Connector connector) {
        areNull(message, connector); //throws exception if either is null


        if(connectorPresent(connector)) {
            Integer productCode = (Integer) getProductCode().get(); //default value?
            BigInteger BIproductCode = BigInteger.valueOf(productCode.intValue());
            BigInteger sum = message.getValue().add(BIproductCode); // if product code isnt there, add zero
            LOGGER.log(Level.INFO, "Sister printer has printed the binary message: " + sum);
        }

    }



}
