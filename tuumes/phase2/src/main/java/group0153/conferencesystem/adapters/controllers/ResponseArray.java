package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.Data;

import java.util.List;

/**
 * A class representing multiple responses from the program to the front end
 */
public class ResponseArray extends Response {
    public final List<? extends Data> data;

    /**
     * Construct an instance of ResponseArray with a status, message, and information
     *
     * @param valid   boolean representing whether the operations completed successfully
     * @param message String message explaining the status of the operations
     * @param data    List of Data containing information from the entities
     */
    public ResponseArray(Boolean valid, String message, List<Data> data) {
        super(valid);
        this.data = data;
        // TODO: message parameter is not used. remove it?
    }

    /**
     * Construct an instance of ResponseArray with a status and information
     *
     * @param valid boolean representing whether the operations completed successfully
     * @param data  List of Data containing information from the entities
     */
    public ResponseArray(Boolean valid, List<? extends Data> data) {
        super(valid);
        this.data = data;
    }
}
