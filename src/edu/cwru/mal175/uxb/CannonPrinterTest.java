package edu.cwru.mal175.uxb;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by alexlucas on 10/6/16.
 */
public class CannonPrinterTest {

    CannonPrinter cannonPrinter;

    @Before
    public void setup(){
        CannonPrinter.Builder cpBuilder;

        cpBuilder = new CannonPrinter.Builder(1234);
        cpBuilder.productCode(1111);
        cpBuilder.serialNumber(BigInteger.TEN);
        List<Connector.Type> typeList = new ArrayList<Connector.Type>();
        typeList.add(Connector.Type.PERIPHERAL);
        typeList.add(Connector.Type.PERIPHERAL);
        cpBuilder.connectors(typeList);

        this.cannonPrinter = cpBuilder.build();
    }

    @Test
    public void recv() throws Exception {

    }

    @Test
    public void recv1() throws Exception {

    }

    @Test
    public void reachableDevices() throws Exception {
        GoAmateur.Builder gaBuilder = new GoAmateur.Builder(776);
        gaBuilder.productCode(667);
        gaBuilder.serialNumber(new BigInteger("11235"));
        List<Connector.Type> typeList = new ArrayList<Connector.Type>();
        typeList.add(Connector.Type.PERIPHERAL);
        typeList.add(Connector.Type.PERIPHERAL);
        gaBuilder.connectors(typeList);
        GoAmateur ga = gaBuilder.build();
        ga.getConnector(0).setPeer(cannonPrinter.getConnector(0));
        assertEquals(cannonPrinter.reachableDevices().size(), 1);
    }

    @Test
    public void isReachable() throws Exception {

    }

}