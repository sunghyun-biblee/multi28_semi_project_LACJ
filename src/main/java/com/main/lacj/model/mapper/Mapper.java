package com.main.lacj.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.main.lacj.model.dto.BoardDto;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

	@Select(" SELECT * FROM MULTIBOARD ORDER BY MNO DESC ")
	List<BoardDto> selectList();
}
