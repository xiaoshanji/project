package com.shanji.over.perm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Permission extends Model<Permission> implements Serializable{

    private static final long serialVersionUID = 3L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
