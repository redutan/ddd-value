package io.redutan.ddd.value;

import io.redutan.ddd.value.domain.model.Group;
import io.redutan.ddd.value.domain.model.GroupMember;
import io.redutan.ddd.value.domain.model.GroupMemberType;
import io.redutan.ddd.value.domain.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DddValueApplication implements CommandLineRunner {
    private final GroupRepository groupRepository;

    public DddValueApplication(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DddValueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Group group = new Group("설명", "이름", Arrays.asList(
                new GroupMember("그룹1", GroupMemberType.GROUP),
                new GroupMember("그룹회원", GroupMemberType.MEMBER)));
        groupRepository.save(group);
    }
}
