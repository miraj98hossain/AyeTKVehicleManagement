package com.aye.mobileservice.utils.serializables;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

public class PageImplDeserializer extends JsonDeserializer<PageImpl<?>> {
    private final Class<?> contentClass;

    public PageImplDeserializer(Class<?> contentClass) {
        this.contentClass = contentClass;
    }

    @Override
    public PageImpl<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        JsonNode contentNode = node.get("content");
        JsonNode pageNode = node.get("page");

        List<?> content = mapper.convertValue(
                contentNode,
                mapper.getTypeFactory().constructCollectionType(List.class, contentClass)
        );

        int page = pageNode.get("number").asInt();
        int size = pageNode.get("size").asInt();
        long total = pageNode.get("totalElements").asLong();
        return new PageImpl<>(content, PageRequest.of(page, size), total);
    }
}


