package com.moments.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.moments.models.Moment;

import java.io.IOException;

public class CommentSerializer extends StdSerializer<Moment> {

    public CommentSerializer() {
        this(null);
    }

    public CommentSerializer(Class<Moment> t) {
        super(t);
    }

    @Override
    public void serialize(Moment moment, JsonGenerator g, SerializerProvider serializerProvider) throws IOException {
        g.writeStartObject();
        g.writeNumberField("id", moment.getId());
        g.writeEndObject();
    }
}
