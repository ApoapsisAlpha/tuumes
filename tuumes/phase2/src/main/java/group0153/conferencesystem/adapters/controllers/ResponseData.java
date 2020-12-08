package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.Data;

public class ResponseData extends Response {
    public Data data;

    public ResponseData(Boolean valid, String message, Data data) {
        super(valid);
        this.data = data;
    }

    public ResponseData(Boolean valid, Data data) {
        super(valid);
        this.data = data;
    }
}
