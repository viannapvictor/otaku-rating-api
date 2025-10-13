package com.otaku.rating.user.app.response.dto;

import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.valueobject.EnumUserRole;
import com.otaku.rating.user.core.model.valueobject.UserAuthorizationLevel;
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