package com.main.lacj.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.CommentDto;
import com.main.lacj.model.dto.MemberDto;
import com.main.lacj.model.mapper.Mapper;

@Service
public class BizImpl implements Biz{

	@Autowired
	private Mapper mapper;
	
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
