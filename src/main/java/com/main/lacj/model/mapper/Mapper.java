package com.main.lacj.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.MemberDto;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

	@Select(" SELECT * FROM MULTIBOARD ORDER BY MNO DESC ")
	List<BoardDto> selectList();

    @Insert(" INSERT INTO MULTIMEMBER VALUES(NULL, #{mid}, #{mpw}, #{mname}, #{mimg}, #{mstatus} )")
	public int insertRegi(MemberDto dto);
	
	@Insert(" INSERT INTO MULTIBOARD VALUE(NULL, #{btitle}, #{bcontent}, NOW(), #{bimg}, 0, #{mno})")
	public int insertBoard(String btitle, String bcontent, String bimg, int mno);
	
    @Select(" SELECT * FROM MULTIMEMBER WHERE MID = #{mid} AND MPW = #{mpw}  ")
    MemberDto selectLogin(MemberDto dto);
    
    @Select(" SELECT COUNT(*) FROM MULTIBOARD WHERE MNO = #{mno}")
    int countPostsByUser(@Param("mno") int mno);
    
    @Update(" UPDATE MULTIBOARD SET BLIKES = #{blikes} WHERE BNO = #{bno} ")
    int likesUp(@Param("blikes") int blikes, @Param("bno") int bno);
    
    @Select(" SELECT * FROM MULTIBOARD WHERE BNO = #{bno} ")
    BoardDto boardSelectOne(@Param("bno") int bno);
}
