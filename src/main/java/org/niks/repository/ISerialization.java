package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ISerialization<T> {

    default void writeJSON(Map<String, T> map, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<T> list = new ArrayList<>(map.values());
        mapper.writeValue(new File(filePath), list);
    }

    T[] readJSON() throws IOException;
}
