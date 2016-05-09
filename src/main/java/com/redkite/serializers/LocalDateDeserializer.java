package com.redkite.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return StringUtils.isEmpty(jsonParser.getText()) ? null : LocalDate.parse(jsonParser.getText(), dtf);
    }
}
