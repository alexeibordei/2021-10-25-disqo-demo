package com.disqo.demo.NotesApp.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id=1;
        if (principal instanceof UserDetailsImpl) {
            id = ((UserDetailsImpl) principal).getUser().getId();
        }
        return Optional.of(id);
    }
}
