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
    /**
     * ORM과 한 열로 직렬화되는 여러 값 : ORM and Many Values Serialized into a Single Column
     */
    @Convert(converter = GroupMembersConverter.class)
    @Column(name = "GROUP1_MEMBERS", length = 4000)
    private Set<GroupMember> group1Members = new HashSet<>();
    /**
     * ORM과 데이터베이스 엔터티로 지원되는 여러 값 : ORM and Many Values Backed by a Database Entity
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GROUP_ID")
    private Set<IdentifiedGroupMember> group2Members = new HashSet<>();
    /**
     * ORM과 조인 테이블로 지원되는 여러 값 : ORM and Many Values Backed by a Join Table
     */
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
