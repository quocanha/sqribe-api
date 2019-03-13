package org.sqribe.api.security;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;
import org.sqribe.api.repositories.UserRepository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDetailsManagerImpl extends JdbcDaoSupport implements UserDetailsManager {

    // ~ Static fields/initializers
    // =====================================================================================


    // UserDetailsService SQL
    private static final String DEF_USERS_BY_USERNAME_QUERY =
            "select username, password, enabled from `user` u where u.username = ?";

    private static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
            "select u.username, a.authority from `user` u left join `user_authorities` ua on u.id = ua.user_id "
                    + "left join `authority` a on ua.authorities_id = a.id where u.username = ?";

    // UserDetailsManager SQL
    private static final String DEF_CREATE_USER_SQL =
            "insert into user (username, password, enabled) values (?, ?, ?)";
    private static final String DEF_CREATE_AUTHORITY_SQL =
            "insert into authority (user_id, authority_id) values (?, ?, ?)";
    private static final String DEF_USER_EXISTS_SQL =
            "select id, username from users where username = ?";
    private static final String DEF_USER_ID_BY_USERNAME_SQL =
            "select id from `user` where username = ?";
    private static final String DEF_AUTHORITY_ID_BY_AUTHORITY_SQL =
            "select id from authority where authority = ?";


    // ~ Instance fields
    // ================================================================================================

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private String authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
    private String usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;

    private String createUserSql = DEF_CREATE_USER_SQL;
    private String createAuthoritySql = DEF_CREATE_AUTHORITY_SQL;
    private String userExistsSql = DEF_USER_EXISTS_SQL;
    private String userIdByUsernameSql = DEF_USER_ID_BY_USERNAME_SQL;
    private String authorityIdByAuthoritySql = DEF_AUTHORITY_ID_BY_AUTHORITY_SQL;

    private String rolePrefix = "";

    @Autowired
    private UserRepository userRepository;

    // ~ Constructors
    // ===================================================================================================

    public UserDetailsManagerImpl() {
        this.usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        this.authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        org.sqribe.api.models.User user =  this.userRepository.findByUsername(username);
        Hibernate.initialize(user.getAuthorities());

        if(user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        return userDetails;
    }

    // ~ UserDetailsManager implementation
    // ==============================================================================

    @Override
    public void createUser(UserDetails user) {
        validateUserDetails(user);
        getJdbcTemplate().update(createUserSql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setBoolean(3, user.isEnabled());
            }

        });

        insertUserAuthorities(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        List<String> users = getJdbcTemplate().queryForList(userExistsSql,
                new String[] { username }, String.class);

        if (users.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(
                    "More than one user found with name '" + username + "'", 1);
        }

        return users.size() == 1;
    }

    private void insertUserAuthorities(UserDetails user) {
        for (GrantedAuthority auth : user.getAuthorities()) {
            getJdbcTemplate().update(
                    createAuthoritySql,
                    this.getUsernameId(user.getUsername()),
                    this.getAuthorityId(auth.getAuthority())
            );
        }
    }

    private Integer getUsernameId(String username) {
        List<Integer> users = getJdbcTemplate().queryForList(
                this.userIdByUsernameSql,
                new String[] { username }, Integer.class
        );

        if (users.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(
                    "More than one user found with name '" + username + "'", 1);
        }

        return users.get(0);
    }

    private Integer getAuthorityId(String authority) {
        List<Integer> authorities = getJdbcTemplate().queryForList(
                this.authorityIdByAuthoritySql,
                new String[] { authority }, Integer.class
        );

        if (authorities.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(
                    "More than one authority found with label '" + authority + "'", 1);
        }

        return authorities.get(0);
    }


    private void validateUserDetails(UserDetails user) {
        Assert.hasText(user.getUsername(), "Username may not be empty or null");
        validateAuthorities(user.getAuthorities());
    }

    private void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Authorities list must not be null");

        for (GrantedAuthority authority : authorities) {
            Assert.notNull(authority, "Authorities list contains a null entry");
            Assert.hasText(authority.getAuthority(),
                    "getAuthority() method must return a non-empty string");
        }
    }

}
