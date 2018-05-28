package io.redutan.ddd.value.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Value
public class GroupMember {
    private String name;
    @Enumerated(EnumType.STRING)
    private GroupMemberType type;

    // For JPA
    GroupMember() {
        this.name = null;
        this.type = null;
    }

    public GroupMember(@JsonProperty("name") String name, @JsonProperty("type") GroupMemberType type) {
        this.name = name;
        this.type = type;
    }
}
