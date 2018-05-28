package io.redutan.ddd.value.domain.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.redutan.ddd.value.domain.model.GroupMember;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Set;

@Converter
public class GroupMembersConverter implements AttributeConverter<Set<GroupMember>, String> {
    private ObjectMapper om = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<GroupMember> attribute) {
        try {
            return om.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new JpaSystemException(new RuntimeException(e));
        }
    }

    @Override
    public Set<GroupMember> convertToEntityAttribute(String dbData) {
        try {
            return om.readValue(dbData, new TypeReference<Set<GroupMember>>(){});
        } catch (IOException e) {
            throw new JpaSystemException(new RuntimeException(e));
        }
    }
}
