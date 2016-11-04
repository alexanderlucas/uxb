package edu.cwru.mal175.uxb;

import java.math.BigInteger;

/**
 * Created by alexlucas on 9/13/16.
 */
public final class BinaryMessage implements Message {
    private final BigInteger value;

    public BinaryMessage(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    public boolean equals(Object anObject){
        if(null == anObject){
            return false;
        }
        if(anObject instanceof BinaryMessage) {
            BinaryMessage object = (BinaryMessage)anObject;
            return this.getValue().equals(object.getValue());
        }
        else {
            return false;
        }
    }

    @Override
    public void reach(Device device, Connector connector) {
        device.recv(this, connector);
    }

}
