package com.baidu.mall.bean;

import com.baidu.mall.mapper.CskaoyanMallRegionMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class CskaoyanMallRegion {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    private List<CskaoyanMallRegion> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<CskaoyanMallRegion> getChildren() {
        return children;
    }

    public void setChildren(List<CskaoyanMallRegion> children) {
        this.children = children;
    }
}
