package com.shanji.security.distributed.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.shanji.security.distributed.perm.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2021-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role>{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField(exist = false)
    private List<Permission> perms;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
