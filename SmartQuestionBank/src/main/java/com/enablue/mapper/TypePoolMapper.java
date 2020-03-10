package com.enablue.mapper;

import com.enablue.pojo.TypePool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypePoolMapper {
    int addTypePool(@Param("typePool") TypePool typePool);
    int updataTypePool(@Param("typePool") TypePool typePool);
    int daleteTypePool(@Param("id") int id);
    List<TypePool> queryAllTypet();

    List<TypePool> queryType(@Param("page") Long page, @Param("limit") Long limit);

    List<TypePool> queryTypeBySubjectId(@Param("subId") Integer subId);
}
