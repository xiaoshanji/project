package com.shanji.security.distributed.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.shanji.security.distributed.role.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User>{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String username;

    private String password;

    @TableField(exist = false)
    private List<Role> roles;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
