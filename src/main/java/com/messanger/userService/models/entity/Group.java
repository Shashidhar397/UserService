package com.messanger.userService.models.entity;

import com.messanger.userService.models.request.CreateGroupRequestModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author shashidhar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`group`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String uuid;

    private String name;
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupUser> groupUsers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    public Group(CreateGroupRequestModel createGroupRequestModel, User createdBy, User updatedBy){
        this.uuid = UUID.randomUUID().toString();
        this.name = createGroupRequestModel.getName();
        this.description = createGroupRequestModel.getDescription();
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
