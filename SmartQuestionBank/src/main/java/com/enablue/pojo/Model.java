package com.enablue.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @author cnxjk
 * 试卷模板
 */
public class Model {
    private Integer modelId;
    private String modelName;
    private String modelUrl;
    private String modelFnName;
    private Integer subjectId;
    private JSONArray dataLayout;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public String getModelFnName() {
        return modelFnName;
    }

    public void setModelFnName(String modelFnName) {
        this.modelFnName = modelFnName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public JSONArray getDataLayout() {
        return dataLayout;
    }

    public Model(Integer modelId, String modelName, String modelUrl, String modelFnName, Integer subjectId, JSONArray dataLayout, Date gmtCreate, Date gmtModified) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
        this.dataLayout = dataLayout;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.page = page;
        this.limit = limit;
    }

    public Model(Integer modelId, String modelName, String modelUrl, String modelFnName, Integer subjectId, String dataLayout, Date gmtCreate, Date gmtModified) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
        this.dataLayout = JSONArray.parseArray(dataLayout);
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.page = page;
        this.limit = limit;
    }

    public void setDataLayout(JSONArray dataLayout) {
        this.dataLayout = dataLayout;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Model(Integer modelId, String modelName, String modelUrl, String modelFnName, Integer subjectId, Date gmtCreate, Date gmtModified) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Model(String modelName, String modelUrl, String modelFnName, Integer subjectId) {
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
    }

    public Model(Integer modelId, String modelName, String modelUrl, String modelFnName, Integer subjectId) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
    }

    public Model(Integer modelId, String modelName, String modelUrl, String modelFnName, Integer subjectId, Date gmtModified) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelFnName = modelFnName;
        this.subjectId = subjectId;
        this.gmtModified = gmtModified;
    }

    public Model(Integer subjectId, Integer page, Integer limit) {
        this.subjectId = subjectId;
        this.page = page;
        this.limit = limit;
    }
}
