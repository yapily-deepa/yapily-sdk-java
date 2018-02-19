package com.acaciaconnect.sdk.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.acaciaconnect.sdk.services.ApiException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

public class ResponseDeserializer<T> {

    private final Class<T> clazz;
    private final TypeReference<T> typeReference;

    public ResponseDeserializer(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
        this.clazz = null;
    }

    public ResponseDeserializer(Class<T> clazz) {
        this.clazz = clazz;
        this.typeReference = null;
    }

    public T getObject(InputStream content) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        T result = null;
        final String text = getContent(content);
        try {
            if (clazz != null) {
                result = objectMapper.readValue(text, clazz);
            } else {
                result = objectMapper.readValue(text, typeReference);
            }
        } catch (final IOException e) {
            throw new ApiException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error deserializing: " + text, e);
        }
        return result;
    }

    private String getContent(InputStream inputStream) throws IOException {
        String text = null;
        try (final Reader reader = new InputStreamReader(inputStream)) {
            text = CharStreams.toString(reader);
        }
        return text;
    }

    public String getMimeType() {
        return ContentType.APPLICATION_JSON.getMimeType();
    }
}
