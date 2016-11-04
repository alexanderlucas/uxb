package edu.cwru.mal175.uxb;

import org.junit.Test;
import org.junit.Before;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexlucas on 9/15/16.
 */
public class HubTest {

    Hub hub;

    @Before
    public void setup(){
        Hub.Builder hubBuilder;

        hubBuilder = new Hub.Builder(1234);
        hubBuilder.productCode(1111);
        hubBuilder.serialNumber(BigInteger.TEN);
        List<Connector.Type> typeList = new ArrayList<Connector.Type>();
        typeList.add(Connector.Type.COMPUTER);
        typeList.add(Connector.Type.PERIPHERAL);
        hubBuilder.connectors(typeList);

        this.hub = hubBuilder.build();
    }

    @Test
    public void getDeviceClass() throws Exception {
        assertEquals(hub.getDeviceClass(), DeviceClass.HUB);
    }

    @Test
    public void getProductCode() throws Exception {
        assertEquals(hub.getProductCode().get(), new Integer(1111));
    }

    @Test
    public void getSerialNumber() throws Exception {
        assertEquals(hub.getSerialNumber().get(), BigInteger.TEN);
    }

    @Test
    public void getVersion() throws Exception {
        assertEquals(hub.getVersion(), new Integer(1234));
    }

    @Test
    public void getConnectors() throws Exception {
        assertEquals(hub.getConnectors().size(), 2);
    }

    @Test
    public void getConnectorCount() throws Exception {
        assertEquals((long)(hub.getConnectorCount()), 2);
    }

    @Test
    public void getConnector() throws Exception {
        assertEquals(hub.getConnector(0).getType(), Connector.Type.COMPUTER);
    }


}
