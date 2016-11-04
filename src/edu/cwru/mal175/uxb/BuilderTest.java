package edu.cwru.mal175.uxb;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by alexlucas on 9/15/16.
 */
public class BuilderTest {
    Hub.Builder hubBuilder = new Hub.Builder(1234);

    @Test
    public void build() throws Exception {
        System.out.println(hubBuilder.build());
    }

    @Test
    public void getThis() throws Exception {
        System.out.println(hubBuilder.getThis());
    }

    @Test
    public void validate() throws Exception {
        hubBuilder.validate();
        System.out.println("Validated");
    }

    @Test
    public void productCode() throws Exception {
        System.out.println(hubBuilder.productCode(11111));
    }

    @Test
    public void serialNumber() throws Exception {
        System.out.println(hubBuilder.serialNumber(BigInteger.ONE));
    }

    @Test
    public void connectorsWithoutPeripherals() throws Exception {
        List<Connector.Type> conns = new ArrayList<Connector.Type>();
        conns.add(Connector.Type.COMPUTER);

        System.out.println(hubBuilder.connectors(conns));
        validate(); // will throw error because needs connectors of both types
    }

    @Test
    public void connectorsWithoutComputers() throws Exception {
        List<Connector.Type> conns = new ArrayList<Connector.Type>();
        conns.add(Connector.Type.PERIPHERAL);

        System.out.println(hubBuilder.connectors(conns));
        validate(); // will throw error because needs connectors of both types
    }
    @Test
    public void connectorsWithBoth() throws Exception {
        List<Connector.Type> conns = new ArrayList<Connector.Type>();
        conns.add(Connector.Type.PERIPHERAL);
        conns.add(Connector.Type.COMPUTER);
        System.out.println(hubBuilder.connectors(conns));
    }

    @Test
    public void getConnectors() throws Exception {
        System.out.println(hubBuilder.getConnectors());
    }

}