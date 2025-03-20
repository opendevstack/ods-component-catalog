package com.boehringer.componentcatalog.server.services.common;

import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;

import java.util.Base64;

public class IdEncoderDecoder {

    private IdEncoderDecoder() {
        // Hide the implicit public constructor
    }

    public static String idEncode(String path) {
        return Base64.getUrlEncoder().encodeToString(path.getBytes());
    }

    public static String idDecode(String id) throws InvalidIdException {
        try {
            return new String(Base64.getUrlDecoder().decode(id));
        } catch (Exception e) {
            throw new InvalidIdException(id);
        }
    }
}
