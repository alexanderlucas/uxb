package edu.cwru.mal175.uxb;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by alexlucas on 9/13/16.
 */
public interface Device {
    Optional<Integer> getProductCode();
    Optional<BigInteger> getSerialNumber();
    Integer getVersion();
    DeviceClass getDeviceClass();
    Integer getConnectorCount();
    List<Connector> getConnectors();
    Connector getConnector(int index);

    void recv(StringMessage message, Connector connector);
    void recv(BinaryMessage message, Connector connector);

    Set<Device> peerDevices();
    Set<Device> reachableDevices();
    boolean isReachable(Device device);

    boolean reachable(Optional<Device> searchDevice, Set<Device> deviceSet);
}
