package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.data.UserContactData;

import java.util.List;

public class ResponseArray extends Response {
    public List<? extends Data> data;

    public ResponseArray(Boolean valid, String message, List<Data> data) {
        super(valid);
        this.data = data;
    }

    public ResponseArray(Boolean valid, List<? extends Data> data) {
        super(valid);
        this.data = data;
    }
}
