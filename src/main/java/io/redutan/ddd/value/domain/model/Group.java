package io.redutan.ddd.value.domain.model;

import io.redutan.ddd.value.domain.convert.GroupMembersConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Entity
@Table(name = "Groups")
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Group {
    @Id
    @GeneratedValue
    private Long groupId;
    private String description;
    private String name;
    @Convert(converter = GroupMembersConverter.class)
    @Column(name = "GROUP1_MEMBERS", length = 4000)
    private Set<GroupMember> group1Members = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GROUP_ID")
    private Set<IdentifiedGroupMember> group2Members = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "GROUP3_MEMBERS", joinColumns = @JoinColumn(name = "GROUP_ID"))
    private Set<GroupMember> group3Members = new HashSet<>();

    public Group(String description, String name, Collection<GroupMember> groupMembers) {
        this.description = description;
        this.name = name;
        this.group1Members = new HashSet<>(groupMembers);
        this.group2Members = groupMembers.stream().map(IdentifiedGroupMember::new).collect(toSet());
        this.group3Members = new HashSet<>(groupMembers);
    }
}
