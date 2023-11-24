package com.main.lacj.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.lacj.dto.MemberDto;
import com.main.lacj.dto.CommentDto;
import com.main.lacj.dto.BoardDto;

@Service
public class BizImpl implements Biz{

	@Override
	public List<MemberDto> selectList() {
		return null;
	}

	@Override
	public MemberDto selectOne(int mno) {
		return null;
	}

	@Override
	public int insert(MemberDto dto) {
		return 0;
	}

	@Override
	public int update(MemberDto dto) {
		return 0;
	}

	@Override
	public int delete(int mno) {
		return 0;
	}

}
