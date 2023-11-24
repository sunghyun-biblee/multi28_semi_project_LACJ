package com.main.lacj.biz;

import java.util.List;

import com.main.lacj.dto.BoardDto;
import com.main.lacj.dto.CommentDto;
import com.main.lacj.dto.MemberDto;

public interface Biz {

	public List<MemberDto> selectList();
	public MemberDto selectOne(int mno);
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(int mno);
}
