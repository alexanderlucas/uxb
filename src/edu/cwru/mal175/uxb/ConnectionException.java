package edu.cwru.mal175.uxb;

/**
 * Created by alexlucas on 9/25/16.
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 293;


    public enum ErrorCode{
        CONNECTOR_BUSY, CONNECTOR_MISMATCH, CONNECTION_CYCLE
    }

    private final Connector connector;
    private final ErrorCode errorCode;

    public ConnectionException(Connector connector, ErrorCode errorCode) {
        this.connector = connector;
        this.errorCode = errorCode;
    }

    public Connector getConnector() {
        return connector;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


}
