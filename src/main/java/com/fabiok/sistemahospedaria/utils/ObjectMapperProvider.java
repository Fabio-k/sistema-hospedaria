package com.fabiok.sistemahospedaria.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperProvider {
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static ObjectMapper getMapper(){
        return mapper;
    }
}
