package com.aye.backendservice.config;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MySchemaFilter implements SchemaFilter {

    public static final MySchemaFilter INSTANCE = new MySchemaFilter();
    public static final List<String> TABLE_NAMES = List.of("M_NOTIFICATION_DTL_V");

    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {
        // Skip validation for selected tables
        //return !table.getName().equalsIgnoreCase("M_NOTIFICATION_DTL_V");
        // or: !table.getName().startsWith("V_")
        return !TABLE_NAMES.contains(table.getName());
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}

