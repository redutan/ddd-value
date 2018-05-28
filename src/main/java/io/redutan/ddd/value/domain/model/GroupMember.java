package io.redutan.ddd.value.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Value
public class GroupMember {
    private String name;
    /**
     * ORM과 상태로서의 열거형 객체 : ORM and Enum-as-State Objects
     */
    @Enumerated(EnumType.STRING)
    private GroupMemberType type;

    // For JPA
    GroupMember() {
        this.name = null;
        this.type = null;
    }

    // For Jackson
    @JsonCreator
    public GroupMember(@JsonProperty("name") String name, @JsonProperty("type") GroupMemberType type) {
        this.name = name;
        this.type = type;
    }
}
