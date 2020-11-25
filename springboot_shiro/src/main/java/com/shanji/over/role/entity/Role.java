package com.shanji.over.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.shanji.over.perm.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> implements Serializable{

    private static final long serialVersionUID = 2L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private List<Permission> pers;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
