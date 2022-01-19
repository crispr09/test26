package com.example.demo;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.identityconnectors.common.security.GuardedString;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class GuardedStringSerializerTest  {

    private static final String READONLY = "readOnly";

    private static final String DISPOSED = "disposed";

    private static final String ENCRYPTED_BYTES = "encryptedBytes";

    private static final String BASE64_SHA1_HASH = "base64SHA1Hash";
    
    private final GuardedStringSerializer serializer = new GuardedStringSerializer();
    
    
    // test comment pushing it from eclipse
    @Test
    public void serialize(
            @Mock JsonGenerator jgen, 
            @Mock SerializerProvider sp) throws IOException {
        serializer.serialize(new GuardedString(), jgen, sp);
        verify(jgen).writeBooleanField(READONLY, false);
        verify(jgen).writeBooleanField(DISPOSED, false);
        verify(jgen).writeBooleanField(DISPOSED, false);
        verify(jgen).writeStringField(eq(ENCRYPTED_BYTES), anyString());
        verify(jgen).writeStringField(eq(BASE64_SHA1_HASH), anyString());
        verify(jgen).writeEndObject();
    }
}