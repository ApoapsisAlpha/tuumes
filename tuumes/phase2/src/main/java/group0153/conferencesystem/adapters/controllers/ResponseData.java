package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.Data;

/**
 * A class facilitating the response of the program about whether the user's request has successfully completed and
 * containing data to be displayed
 */
public class ResponseData extends Response {
    public final Data data;

    /**
     * Construct an instance of Response with the provided validity, message, and information
     *
     * @param valid   a boolean representing whether the operation completed successfully
     * @param message a message to be displayed to the user about the operation
     * @param data    Data instance that contains information to be displayed
     */
    public ResponseData(Boolean valid, String message, Data data) {
        super(valid);
        this.data = data;
    }

    /**
     * Construct an instance of Response with the provided validity and information
     *
     * @param valid a boolean representing whether the operation completed successfully
     * @param data  Data instance that contains information to be displayed
     */
    public ResponseData(Boolean valid, Data data) {
        super(valid);
        this.data = data;
    }
}
