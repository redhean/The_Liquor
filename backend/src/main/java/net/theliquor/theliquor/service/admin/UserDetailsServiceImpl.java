package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Member;
import net.theliquor.theliquor.domain.SecurityUser;
import net.theliquor.theliquor.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = Optional.ofNullable(memberRepository.findByUsername(username));
        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 username 입니다. Username: " + username);

        log.info("loadUserByUsername member.username = {}", username);

        return new SecurityUser(findMember.get());
    }
}
