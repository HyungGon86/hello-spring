package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = Member.builder()
                .name("spring")
                .build();

        Member save = repository.save(member);
        Member result = repository.findById(save.getId()).orElse(null);

        assertThat(save).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = Member.builder()
                .name("spring1")
                .build();
        Member save1 = repository.save(member1);

        Member member2 = Member.builder()
                .name("spring2")
                .build();
        Member save2 = repository.save(member2);
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(save1);
    }

    @Test
    public void findAll() {
        Member member1 = Member.builder()
                .name("spring")
                .build();
        repository.save(member1);

        Member member2 = Member.builder()
                .name("spring")
                .build();
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
