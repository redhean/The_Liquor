package net.theliquor.theliquor.config.login;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Member;
import net.theliquor.theliquor.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminDataLoader {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${ADMIN_USERNAME}")
    private String username;

    @Value("${ADMIN_PASSWORD}")
    private String password;

    @PostConstruct
    public void loadData() {
        if (memberRepository.findByUsername(username) == null) {
            Member admin = new Member();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setIsActive(Boolean.TRUE);
            admin.setRole(Role.ROLE_ADMIN);
            memberRepository.save(admin);
        }
    }
}
