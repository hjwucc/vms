package com.wu.vms.controller.viewobject;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/23 17:42
 * @description：菜单视图层模型
 */

public class MenuVO {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;

    public MenuVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
