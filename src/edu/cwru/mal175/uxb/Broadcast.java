package edu.cwru.mal175.uxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexlucas on 9/22/16.
 */
public class Broadcast {

    public static void broadcast(List<Device> deviceList, List<Message> messageList) {
        for (Device device : deviceList) {
            for (Message message : messageList) {
                if (device.getConnectorCount() >= 1) {
                    device.getConnector(0).recv(message);

                }
            }
        }
    }

    public static void main(String[] args) throws ConnectionException {
//        CannonPrinter.Builder cpBuilder = new CannonPrinter.Builder(1234);
//        cpBuilder.productCode(1111);
//        cpBuilder.serialNumber(new BigInteger("250"));
//        List<Connector.Type> typeList = new ArrayList<Connector.Type>();
//        typeList.add(Connector.Type.PERIPHERAL);
//        typeList.add(Connector.Type.PERIPHERAL);
//        cpBuilder.connectors(typeList);
//
//        GoAmateur.Builder gaBuilder = new GoAmateur.Builder(2345);
//        gaBuilder.productCode(2222);
//        gaBuilder.serialNumber(BigInteger.ONE);
//        gaBuilder.connectors((typeList));
//
//
//        List<Device> deviceList = new ArrayList<>();
//        deviceList.add(cpBuilder.build());
//        deviceList.add(gaBuilder.build());
//
//
//        BinaryMessage bm = new BinaryMessage(new BigInteger("423"));
//        StringMessage sm = new StringMessage("hello world");
//
//        List<Message> messageList = new ArrayList<>();
//        messageList.add(bm);
//        messageList.add(sm);
//
//        broadcast(deviceList, messageList);



        List<Connector.Type> typeList = new ArrayList<Connector.Type>();
        typeList.add(Connector.Type.COMPUTER);
        typeList.add(Connector.Type.PERIPHERAL);
        typeList.add(Connector.Type.PERIPHERAL);


        //make 2 hubs
        Hub.Builder hubBuilder1 = new Hub.Builder(1);
        Hub.Builder hubBuilder2 = new Hub.Builder(1);

        hubBuilder1.productCode(1234567);
        hubBuilder2.productCode(1234567);

        hubBuilder1.serialNumber(new BigInteger("97531"));
        hubBuilder2.serialNumber(new BigInteger("86420"));

        hubBuilder1.connectors(typeList);
        hubBuilder2.connectors(typeList);
        Hub hub1 = hubBuilder1.build();
        Hub hub2 = hubBuilder2.build();

        typeList.remove(0);
        //make 3 printers
        CannonPrinter.Builder cpBuilder1 = new CannonPrinter.Builder(3);
        cpBuilder1.productCode(1111);
        cpBuilder1.serialNumber(new BigInteger("36912"));
        cpBuilder1.connectors(typeList);
        CannonPrinter cp1 = cpBuilder1.build();

        CannonPrinter.Builder cpBuilder2 = new CannonPrinter.Builder(3);
        cpBuilder2.productCode(1111);
        cpBuilder2.serialNumber(new BigInteger("481216"));
        cpBuilder2.connectors(typeList);
        CannonPrinter cp2 = cpBuilder2.build();

        SisterPrinter.Builder spBuilder = new SisterPrinter.Builder(5);
        spBuilder.productCode(2222);
        spBuilder.serialNumber(new BigInteger("235711"));
        spBuilder.connectors(typeList);
        SisterPrinter sp = spBuilder.build();

        //make a cam
        GoAmateur.Builder gaBuilder = new GoAmateur.Builder(776);
        gaBuilder.productCode(667);
        gaBuilder.serialNumber(new BigInteger("11235"));
        gaBuilder.connectors(typeList);
        GoAmateur ga = gaBuilder.build();

        //connect them

        hub1.getConnector(0).setPeer(cp1.getConnector(0));
        hub1.getConnector(1).setPeer(sp.getConnector(0));
        hub2.getConnector(0).setPeer(cp2.getConnector(0));
        hub2.getConnector(1).setPeer(ga.getConnector(0));

        //ga.getConnector(1).setPeer(cp2.getConnector(1));

        //broadcast string message from hub
        StringMessage sm = new StringMessage("hello world");
        hub1.recv(sm, hub1.getConnector(0));
        //send binary message to hub
        BinaryMessage bm = new BinaryMessage(new BigInteger("2468"));
        hub2.getConnector(1).recv(bm);
        //broadcast binary message from hub
        hub2.recv(bm, hub2.getConnector(0));
    }
}