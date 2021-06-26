package com.library.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public abstract class AbstractIntegration {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    protected <T> T sendGet(String url, TypeReference<T> type) throws Exception {
        return sendRequest(get(url), type);
    }

    protected <T> T sendPost(String url, Object body, TypeReference<T> type) throws Exception {
        return sendRequest(post(url).contentType("application/json").content(objectMapper.writeValueAsString(body)), type);
    }

    protected <T> T sendDelete(String url, TypeReference<T> type) throws Exception {
        return sendRequest(delete(url), type);
    }


    private <T> T sendRequest(MockHttpServletRequestBuilder requestBuilder, TypeReference<T> type) throws Exception {
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), type);
    }
}
