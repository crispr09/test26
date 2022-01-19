//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.JsonNodeFactory;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import java.io.IOException;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.syncope.core.provisioning.api.AbstractTest;
//import org.identityconnectors.common.security.EncryptorFactory;
//import org.identityconnectors.common.security.GuardedString;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.test.util.ReflectionTestUtils;
//
//public class GuardedStringDeserializerTest extends AbstractTest {
//
//    private static final String READONLY = "readOnly";
//
//    private static final String DISPOSED = "disposed";
//
//    private static final String ENCRYPTED_BYTES = "encryptedBytes";
//
//    private static final String BASE64_SHA1_HASH = "base64SHA1Hash";
//
//    private final GuardedStringDeserializer deserializer = new GuardedStringDeserializer();
//
//    @Mock
//    private JsonParser jp;
//
//    @Mock
//    private DeserializationContext ctx;
//
//    @Mock
//    private JsonNode node;
//
//    @Test
//    public void deserialize() throws IOException {
//        Map<String, JsonNode> kids = new HashMap<>();
//        kids.put(READONLY, node);
//        kids.put(DISPOSED, node);
//        kids.put(ENCRYPTED_BYTES, node);
//        kids.put(BASE64_SHA1_HASH, node);
//        ObjectNode tree = new ObjectNode(JsonNodeFactory.instance, kids);
//        String testString = "randomTestString";
//        byte[] encryptedBytes = EncryptorFactory.getInstance().getDefaultEncryptor().encrypt(testString.getBytes());
//        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
//
//        when(jp.readValueAsTree()).thenReturn(tree);
//        when(node.asText()).thenReturn(encryptedString);
//        assertEquals(Boolean.FALSE, ReflectionTestUtils.getField(deserializer.deserialize(jp, ctx), READONLY));
//        kids.remove(READONLY);
//        assertEquals(Boolean.FALSE, ReflectionTestUtils.getField(deserializer.deserialize(jp, ctx), DISPOSED));
//        kids.remove(DISPOSED);
//        assertEquals(encryptedString, 
//                ReflectionTestUtils.getField(deserializer.deserialize(jp, ctx), BASE64_SHA1_HASH));
//
//        kids.remove(BASE64_SHA1_HASH);
//        GuardedString expected = new GuardedString(new String(testString.getBytes()).toCharArray());
//        assertTrue(EqualsBuilder.reflectionEquals(ReflectionTestUtils.getField(expected, ENCRYPTED_BYTES),
//                ReflectionTestUtils.getField(deserializer.deserialize(jp, ctx), ENCRYPTED_BYTES)));
//    }
//}