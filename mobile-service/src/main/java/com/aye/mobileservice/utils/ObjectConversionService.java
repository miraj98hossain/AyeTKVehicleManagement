package com.aye.mobileservice.utils;

import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.mobileservice.utils.serializables.PageImplDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjectConversionService {

    public Object convertToObject(ApiRequestResponseDetail apiRequestResponseDetail) throws ClassNotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object o;
        mapper.registerModule(new JavaTimeModule());

        if (apiRequestResponseDetail.getObjectType().equals(ApiRequestResponseDetail.ObjectType.A)) {

            Class<?> c = Class.forName("java.util.ArrayList");

            String asString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiRequestResponseDetail.getObject());
            o = mapper.readValue(asString, c);
            return o;
        } else if (apiRequestResponseDetail.getObjectType().equals(ApiRequestResponseDetail.ObjectType.PD)) {
            Class<?> elementClass = Class.forName(apiRequestResponseDetail.getMapperClass());
            String asString = mapper.writeValueAsString(apiRequestResponseDetail.getObject());

            // Register custom deserializer for PageImpl with the element class
            SimpleModule module = new SimpleModule();
            module.addDeserializer(PageImpl.class, new PageImplDeserializer(elementClass));
            mapper.registerModule(module);

            TypeFactory typeFactory = mapper.getTypeFactory();
            JavaType javaType = typeFactory.constructParametricType(PageImpl.class, elementClass);

            return mapper.readValue(asString, javaType);
        } else {
            Class<?> c = Class.forName(apiRequestResponseDetail.getMapperClass());
            String asString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiRequestResponseDetail.getObject());
            o = mapper.readValue(asString, c);
            return o;
        }
    }


    public List<?> convertObjectArayListToClassArrayList(List<Object> objects, ApiRequestResponseDetail apiRequestResponseDetail) throws ClassNotFoundException {

//        System.out.println(apiRequestResponseDetail.getMapperClass());
        Class<?> cl = Class.forName(apiRequestResponseDetail.getMapperClass());
        ObjectMapper mapper = new ObjectMapper();
        return objects.stream().map(io -> mapper.convertValue(io, cl)).collect(Collectors.toList());
    }

    public Object convertObjectClass(Object object, ApiRequestResponseDetail apiRequestResponseDetail)
            throws ClassNotFoundException {

        Class<?> cl = Class.forName(apiRequestResponseDetail.getMapperClass());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object, cl);
    }


    public Page<?> convertObjectPageableDataToClassPageableData2(Page<Object> objects, ApiRequestResponseDetail apiRequestResponseDetail) throws ClassNotFoundException {
        Class<?> cl = Class.forName(apiRequestResponseDetail.getMapperClass());
        ObjectMapper mapper = new ObjectMapper();

        List<?> convertedList = objects.getContent()
                .stream()
                .map(io -> mapper.convertValue(io, cl))
                .collect(Collectors.toList());

        return new PageImpl<>(convertedList, objects.getPageable(), objects.getTotalElements());
    }


}