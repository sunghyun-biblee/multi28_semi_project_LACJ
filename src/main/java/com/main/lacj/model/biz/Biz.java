package com.main.lacj.model.biz;

import java.util.List;

import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.CommentDto;
import com.main.lacj.model.dto.MemberDto;

public interface Biz {

	public List<BoardDto> selectList();
	public MemberDto selectOne(int mno);
	public int insertRegi(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(int mno);
}
