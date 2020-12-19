package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.service.api.util.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CrudService<User, Long>, UserDetailsService {
    boolean userWithNameExists(final User user);
}
