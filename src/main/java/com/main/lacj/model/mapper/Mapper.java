package com.main.lacj.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.MemberDto;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

	@Select(" SELECT * FROM MULTIBOARD ORDER BY MNO DESC ")
	List<BoardDto> selectList();

	@Insert(" INSERT INTO MULTIMEMBER VALUE(NULL,#{mid},#{mpw},#{mname}) ")
	public int insertRegi(MemberDto dto);
	
	@Insert(" INSERT INTO MULTIBOARD VALUE(NULL,#{btitle},#{bcontent},NOW(),#{bimg},0,#{mno})")
	public int insertBoard(BoardDto dto, int mno);
}
