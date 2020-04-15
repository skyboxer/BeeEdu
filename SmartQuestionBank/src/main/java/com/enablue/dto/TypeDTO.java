package com.enablue.dto;

/**
 * 类型dto
 * 王成
 */
public class TypeDTO {
    Integer plateId;
    String  subjectName;
    String  plateName;
    Integer amount;

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TypeDTO{" +
                "plateId=" + plateId +
                ", subjectName='" + subjectName + '\'' +
                ", plateName='" + plateName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
