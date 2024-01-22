package com.messanger.userService.models.entity;

import com.messanger.userService.models.request.Action;
import com.messanger.userService.models.request.UpdateUserToGroupRequestModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shashidhar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_user")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "group_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private Role role;

    private Status status;

    public GroupUser(Group group, User user, Role role, Status status) {
        this.group = group;
        this.user = user;
        this.role = role == null ? Role.USER : role;
        this.status = status;
    }
}
