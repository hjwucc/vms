package com.wu.vms.service.model;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/23 17:40
 * @description：菜单业务层模型
 */

public class MenuModel {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;
    private Integer menuRoleid;
    private String iconcls;
    private Boolean keepalive;
    private Integer parentid;

    public MenuModel() {
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

    public Integer getMenuRoleid() {
        return menuRoleid;
    }

    public void setMenuRoleid(Integer menuRoleid) {
        this.menuRoleid = menuRoleid;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public Boolean getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(Boolean keepalive) {
        this.keepalive = keepalive;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}
