package com.enablue.mapper;

import com.enablue.pojo.TypePool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypePoolMapper {
    int addTypePool(@Param("typePool") TypePool typePool);
    int updateTypePool(@Param("typePool") TypePool typePool);
    int deleteTypePool(@Param("id") int id);
    List<TypePool> queryAllType();

    List<TypePool> queryType(@Param("page") Long page, @Param("limit") Long limit);

    List<TypePool> queryTypeBySubjectId(@Param("subId") Integer subId);

    int daleteTypePoolBySubjectId(@Param("id") int id);

    TypePool queryTypeById(@Param("typeId") Integer typeId);
}
