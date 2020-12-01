package com.example.utils;

import com.example.basicfunctions.entity.MetaData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONParser {
    /**
     * Parse a given metadata `String` to `List` of {@link MetaData}.
     *
     * @param metadata A metadata string of arrays of json.
     * @return `List` of serialized {@link MetaData}.
     * @throws JsonProcessingException TODO: Exception Handling
     */
    public List<MetaData> parseMetadataToList(String metadata)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(metadata, MetaData[].class));
    }
}
