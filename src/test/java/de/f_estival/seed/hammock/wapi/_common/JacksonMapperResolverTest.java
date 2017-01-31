package de.f_estival.seed.hammock.wapi._common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.f_estival.seed.hammock.testutil.TestTags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO jdoc
 */
class JacksonMapperResolverTest {


    @Test
    @DisplayName("The configured Jackson ObjectMapper should serialize LocalDateTime as String")
    @Tag(TestTags.TECH)
    public void testJSR310Support() throws JsonProcessingException {
        Object testData = new Object() {
            private LocalDateTime timestamp = LocalDateTime.of(2017,01,31,8,34,42);
            public LocalDateTime getTimestamp() { return timestamp; }
            public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        };

        JacksonMapperResolver underTest = new JacksonMapperResolver();
        underTest.logger = LoggerFactory.getLogger(JacksonMapperResolver.class);
        underTest.init();
        ObjectMapper mapper = underTest.getContext(ObjectMapper.class);

        String result = mapper.writeValueAsString(testData);

        assertEquals("{\"timestamp\":\"2017-01-31T08:34:42\"}", result);
    }
}