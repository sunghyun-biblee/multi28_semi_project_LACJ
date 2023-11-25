package com.main.lacj.model.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.CommentDto;
import com.main.lacj.model.dto.MemberDto;

public interface Biz {

	public List<BoardDto> selectList();
	public MemberDto selectOne(int mno);
	public int insertRegi(MemberDto dto);
	public int insertBoard(@Param("btitle") String btitle, @Param("bcontent")String bcontent ,@Param("bimg")String bimg, @Param("mno")int mno);
	public int update(MemberDto dto);
	public int delete(int mno);
    public MemberDto selectLogin(MemberDto dto);
}
