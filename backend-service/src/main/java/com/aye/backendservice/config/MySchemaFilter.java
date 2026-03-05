package com.aye.backendservice.config;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySchemaFilter implements SchemaFilter {

    public static final MySchemaFilter INSTANCE = new MySchemaFilter();

    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {
        // Skip validation for selected tables
        return !table.getName().equalsIgnoreCase("M_NOTIFICATION_DTL_V");
        // or: !table.getName().startsWith("V_")
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}

