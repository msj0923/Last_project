package project.project.config.OAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.project.Entity.Role;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuthAttributes {

    private Map<String ,Object> attributes;
    private String nameAttributeKey;
    private String member_username;
    private String member_nickname;
    private String member_email;
    private Role role;


}
