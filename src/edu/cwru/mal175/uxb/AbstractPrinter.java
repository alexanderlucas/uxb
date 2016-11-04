package edu.cwru.mal175.uxb;


/**
 * Created by alexlucas on 9/18/16.
 */
public abstract class AbstractPrinter<T extends AbstractPrinter.Builder<T>> extends AbstractPeripheral {

    public static abstract class Builder<T> extends AbstractPeripheral.Builder<T> {

        public Builder(Integer version) {
            super(version);
        }
    }

    protected AbstractPrinter(AbstractPeripheral.Builder<Builder<Builder>> builder) {
        super(builder);
    }

    @Override
    public DeviceClass getDeviceClass() {
        return DeviceClass.PRINTER;
    }

}
