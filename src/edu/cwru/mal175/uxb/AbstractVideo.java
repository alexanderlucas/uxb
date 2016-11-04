package edu.cwru.mal175.uxb;

/**
 * Created by alexlucas on 9/21/16.
 */
public abstract class AbstractVideo<T extends AbstractVideo.Builder<T>> extends AbstractPeripheral {


    public static abstract class Builder <T> extends AbstractPeripheral.Builder<T> {

        public Builder(Integer version) {
            super(version);
        }

    }

    protected AbstractVideo(AbstractPeripheral.Builder builder) {
        super(builder);
    }

    public DeviceClass getDeviceClass() {
        return DeviceClass.VIDEO;
    }

}
