package io.redutan.ddd.value.domain.model;

import io.redutan.ddd.value.domain.IdentifiedValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP2_MEMBERS")
@Value
@EqualsAndHashCode(callSuper = false)
public class IdentifiedGroupMember extends IdentifiedValueObject {
    private String name;
    @Enumerated(EnumType.STRING)
    private GroupMemberType type;

    // For JPA
    IdentifiedGroupMember() {
        this.name = null;
        this.type = null;
    }

    public IdentifiedGroupMember(GroupMember source) {
        this.name = source.getName();
        this.type = source.getType();
    }
}
