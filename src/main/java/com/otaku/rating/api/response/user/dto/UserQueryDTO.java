package com.otaku.rating.api.response.user.dto;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobjects.EnumUserRole;
import com.otaku.rating.core.user.model.valueobjects.UserAuthorizationLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDTO {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private EnumUserRole role;

    public static UserQueryDTO parse(User entity, User requester) {
        UserQueryDTO dto = new UserQueryDTO(
            entity.getId(),
            entity.getName().getValue(),
            entity.getUserName().getValue(),
            null,
            null
        );
        if (requester != null && requester.hasAuthorization(UserAuthorizationLevel.MODERATOR)) {
            dto.setEmail(entity.getEmail().getValue());
            dto.setRole(entity.getRole());
        }
        return dto;
    }
}