package edu.cwru.mal175.uxb;
import java.util.*;
/**
 * Created by alexlucas on 9/13/16.
 */
public final class Connector {
    public enum Type{
        COMPUTER, PERIPHERAL
    }

    private final int index;
    private final Type type;
    private final Device device;
    private Optional<Connector> peer;

    public Connector(Device device, int index, Type type) {
        this.index = index;
        this.type = type;
        this.device = device;
        this.peer = Optional.empty();
    }

    public int getIndex() {
        return index;
    }

    public Type getType() {
        return type;
    }

    public Optional<Connector> getPeer() {
        return peer;
    }

    public Device getDevice() {
        return device;
    }

    public void setPeer (Connector peer) throws ConnectionException {
        Optional<Connector> optionalPeer = Optional.of(peer); //throws NullPointerException if peer is null
        //Objects.requireNonNull

        if(this.getPeer().isPresent() || peer.getPeer().isPresent()){
            throw new ConnectionException(this, ConnectionException.ErrorCode.CONNECTOR_BUSY);
        }
        else {
            if(this.device.isReachable(peer.device)){
                throw new ConnectionException(this, ConnectionException.ErrorCode.CONNECTION_CYCLE);
            }
            else{
                this.peer = optionalPeer;
                optionalPeer.get().peer = Optional.of(this);
            }
        }


    }

    public void recv(Message message) {
        message.reach(device, this);
    }

    public boolean isReachable(Device device){
        return this.device.isReachable(device);
    }
}

