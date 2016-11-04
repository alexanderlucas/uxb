package edu.cwru.mal175.uxb;

/**
 * Created by alexlucas on 9/18/16.
 */
public abstract class AbstractPeripheral<T extends AbstractPeripheral.Builder<T>> extends AbstractDevice {
    public static abstract class Builder<T> extends AbstractDevice.Builder<T> {

        public Builder(Integer version) {
            super(version);
        }

        @Override
        protected void validate() {
            super.validate();
            for (Connector.Type type : getConnectors()) {
                if(! type.equals(Connector.Type.PERIPHERAL)){
                    throw new NullPointerException("UXB connection is not a peripheral.");
                }
            }
        }
    }

    protected AbstractPeripheral(Builder<T> builder){
        super(builder);
    }
}
