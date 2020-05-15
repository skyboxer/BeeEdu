package com.enablue.pojo;

import java.util.List;

/**
 * 功能树
 * @author cn_xjk
 */
public class MenuTree {
    private Integer id;
    private String title;
    private Integer parentId;
    private String field;
    private List<MenuTree> children;
    private String href;
    private Boolean spread;
    private Boolean checked;
    private Boolean disabled;

    public MenuTree() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public MenuTree(Integer id, String title, Integer parentId, String field, List<MenuTree> children, String href, Boolean spread, Boolean checked, Boolean disabled) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.field = field;
        this.children = children;
        this.href = href;
        this.spread = spread;
        this.checked = checked;
        this.disabled = disabled;
    }
}
