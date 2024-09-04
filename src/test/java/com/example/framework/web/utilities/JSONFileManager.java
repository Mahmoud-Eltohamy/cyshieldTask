package com.example.framework.web.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.io.File;
import java.io.IOException;

public class JSONFileManager {
    private JsonNode rootNode;
    private ReadContext readContext;

    public JSONFileManager(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        rootNode = objectMapper.readTree(new File(filePath));
        readContext = JsonPath.parse(rootNode.toString());
    }

    public Object getObject(String jsonPath) throws IOException {
        return readContext.read(jsonPath);
    }

    public String getStringObject(String jsonPath) {
        return readContext.read(jsonPath).toString();
    }

}
