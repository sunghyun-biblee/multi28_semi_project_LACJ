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
	
	@Autowired
	public BizImpl(Mapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<BoardDto> selectList() {
		return mapper.selectList();
	}

	@Override
	public MemberDto selectOne(int mno) {
		return null;
	}

	@Override
	public int insertRegi(MemberDto dto) {
		
		return mapper.insertRegi(dto);
	}
	
	@Override
	public int insertBoard(String btitle, String bcontent, String bimg, int mno, String bpublic) {
		return mapper.insertBoard(btitle,bcontent,bimg,mno,bpublic);
	}

	@Override
	public int update(MemberDto dto) {
		return 0;
	}

	@Override
	public int delete(int mno) {
		return 0;
	}

    @Override
    public MemberDto selectLogin(MemberDto dto) {
        return mapper.selectLogin(dto);
    }

	@Override
	public int countPostsByUser(int mno) {
		return mapper.countPostsByUser(mno);
	}

	@Override
	public int likesUp(int blikes, int bno) {
		return mapper.likesUp(blikes, bno);
	}

	@Override
	public BoardDto boardSelectOne(int bno) {
		return mapper.boardSelectOne(bno);
	}

	@Override
	public List<BoardDto> getBoards(int offset, int pageSize) {
		return mapper.getBoards(offset, pageSize);
	}

	@Override
	public int getBoardCount() {
		return mapper.getBoardCount();
	}

	@Override
    public void memberDelete(int mno) {
        mapper.memberDelete(mno);
    }

    @Override
    public List<BoardDto> getMyWrite(int mno) {
        return mapper.getMyWrite(mno);
        }

	@Override
	public Integer countTotalLikes(int mno) {
		Integer result = mapper.countTotalLikes(mno); 
		return result != null ? result : 0;
	}

    @Override
    public int likesDown(int blikes, int bno) {
        return mapper.likesDown(blikes, bno);
    }
    @Override
    public int addcomment(int bno, String comment) {
        
        return mapper.addcomment(bno,comment);
    }

	@Override
	public List<CommentDto> commentSelectAll(int bno) {
		return mapper.commentSelectAll(bno);
	}

	@Override
	public List<BoardDto> getGuestBoards(int offset, int pageSize) {
		return mapper.getGuestBoards(offset, pageSize);
	}

	@Override
    public int updatemember(MemberDto dto) {

        return mapper.updatemember(dto);
    }

	@Override
	public int boardDelete(int bno) {
		return mapper.boardDelete(bno);
	}

	@Override
	public int boardUpdate(BoardDto dto) {
		return mapper.boardUpdate(dto);
	}

}
