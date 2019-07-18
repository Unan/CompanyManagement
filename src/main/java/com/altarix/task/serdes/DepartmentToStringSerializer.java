package com.altarix.task.serdes;

import com.altarix.task.model.Department;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DepartmentToStringSerializer extends JsonSerializer<Department> {
    @Override
    public void serialize(Department value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getName());
    }
}
