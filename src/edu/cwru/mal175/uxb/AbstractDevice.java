package edu.cwru.mal175.uxb;

import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by alexlucas on 9/13/16.
 */
public abstract class AbstractDevice<T extends AbstractDevice.Builder<T>> implements Device {
    public static abstract class Builder<T> {
        private Integer version;
        private Optional<Integer> productCode;
        private Optional<BigInteger> serialNumber;
        private  List<Connector.Type> connectorTypes;

        public Builder(Integer version) {
            this.version = version;
            this.productCode = Optional.empty();
            this.serialNumber = Optional.empty();
            connectorTypes = new ArrayList<Connector.Type>();
        }

        public T productCode(Integer productCode) {
            this.productCode = Optional.ofNullable(productCode); //ofNullable returns an empty Optional if productCode is null
            return getThis();
        }

        public T serialNumber(BigInteger serialNumber) {
            this.serialNumber = Optional.ofNullable(serialNumber); //sets serialNumber or empty Optional
            return getThis();
        }

        public T connectors(List<Connector.Type> connectors) {
            this.connectorTypes = new ArrayList<Connector.Type>(connectors);
            return getThis();
        }

        protected abstract T getThis();

        protected List<Connector.Type> getConnectors() {
            return this.connectorTypes;
        }

        protected void validate() {
            if(null == version){
                throw new NullPointerException("UXB device has no version.");
            }
        }
    }

    protected static final Logger LOGGER = Logger.getLogger(AbstractDevice.class.getName());

    private final Optional<Integer> productCode;
    private final Optional<BigInteger> serialNumber;
    private final Integer version;
    private final List<Connector> connectors;

    protected AbstractDevice(Builder<T> builder) {
        this.productCode = builder.productCode;
        this.serialNumber = builder.serialNumber;
        this.version = builder.version;

        List<Connector.Type> connectorTypes = builder.getConnectors();
        List<Connector> connectors = new ArrayList<Connector>();
        int index = 0;
        for (Connector.Type type: connectorTypes) {
            connectors.add(index, new Connector(this, index, type));
            index++;
        }
        this.connectors = connectors;
    }

    protected boolean connectorPresent(Connector connector){

        if( ! getConnectors().contains(connector)){
            throw new IllegalArgumentException("Device is not connected");

        }
        else {
            return true;
        }
    }

    protected void areNull(Message message, Connector connector){
        if(message == null || connector == null){
            throw new NullPointerException("Needs message and connector");
        }
    }

    @Override
    public Set<Device> peerDevices(){
        Set<Device> toReturn = new HashSet();

        for (Connector connector: getConnectors()) {
            if(connector.getPeer().isPresent()) {
                toReturn.add(connector.getPeer().get().getDevice()); //finds the device connected at that connector
            }
        }

        return toReturn;
    }

    @Override
    public Optional<Integer> getProductCode() {
        return this.productCode;
    }

    @Override
    public Optional<BigInteger> getSerialNumber() {
        return this.serialNumber;
    }

    @Override
    public Integer getVersion() {
        return this.version;
    }

    @Override
    public List<Connector> getConnectors() {
        return this.connectors;
    }

    @Override
    public Integer getConnectorCount() {
        return connectors.size();
    }

    @Override
    public Connector getConnector(int index) {
        return connectors.get(index);
    }

    @Override
    public Set<Device> reachableDevices(){
        Set<Device> toReturn = new HashSet();

        reachable(Optional.empty(), toReturn);

        return toReturn; //if all of peerdevices are in set, returns empty set
    }

    public boolean reachable(Optional<Device> searchDevice, Set<Device> deviceSet){

        for (Device device: peerDevices()) {
            if(deviceSet.add(device)){
                if(isNotEmptyAndEquals(device, searchDevice) || device.reachable(searchDevice, deviceSet) ){
                    return true;
                }

            }
        }

        return false;
    }

    private boolean isNotEmptyAndEquals(Device device, Optional<Device> optionalDevice){
        if(optionalDevice.isPresent()){
            if(device.equals(optionalDevice.get())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isReachable(Device device) {

        Optional<Device> dev = Optional.of(device);
        Set<Device> deviceSet = new HashSet<>();

        return (reachable(dev, deviceSet));

    }



}
