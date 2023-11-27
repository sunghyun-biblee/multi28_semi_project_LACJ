package com.main.lacj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.main.lacj.model.biz.Biz;
import com.main.lacj.model.dto.BoardDto;
import com.main.lacj.model.dto.MemberDto;
import com.main.lacj.model.file.UploadFile;

@Controller
public class LacjController {
//     index.html > login page

	@Autowired
	private Biz biz;
	
	@RequestMapping("/none")
	public String none() {
		return "none";
	}
	

//	@RequestMapping("/mainlist")
//	public String mainlist(Model model) {
//		model.addAttribute("list", biz.selectList());
//		return "mainlist";
//	}

	@RequestMapping("/registinsert")
	public String registinsert(MemberDto dto) {

		if (biz.insertRegi(dto) > 0) {
			return "login";
		} else {
			return "redirect:regist";
		}

	}

	@RequestMapping("/regist")
	public String regist() {

		return "regist";
	}

	@GetMapping("/memberdelete")
    public String withdrawMember(HttpSession session) {
        MemberDto member = (MemberDto) session.getAttribute("user");

        biz.memberDelete(member.getMno());

        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        MemberDto dto = (MemberDto)session.getAttribute("user");
        model.addAttribute("dto",dto);

        int mno = dto.getMno();
        List<BoardDto> mywrite = biz.getMyWrite(mno);
        int totalPosts = biz.countPostsByUser(mno);
        int totalLikes = biz.countTotalLikes(mno);

        model.addAttribute("totalLikes", totalLikes);
        model.addAttribute("mywrite", mywrite);
        model.addAttribute("totalPosts", totalPosts);

        if(dto.getMstatus() != "G") {
            return "mypage";
        }else {
            return "mypagefail";    //alert창으로 '게스트는 마이페이지가 존제하지않습니디...'
        }
    }
	
	 
	
	@RequestMapping("/mypagefail")
	public String mypagefail() {
		return "redirect:mainlist";
	}
	
//	@RequestMapping("/login")
//	public String login(MemberDto dto, HttpSession session) {
//
//		MemberDto login = biz.selectLogin(dto);
//
//		if (login != null) {
//			session.setAttribute("user", login);
//			session.setMaxInactiveInterval(60 * 10);
//			return "redirect:mainlist";
//		}else {
//			// 로그인 실패 alert창 처리하기
//			return "logback";
//		}
//	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> checkLogin(@RequestBody MemberDto dto, HttpSession session) {
		
		
		MemberDto login = biz.selectLogin(dto);
		boolean check = false;
		
		if(login != null) {
			session.setAttribute("user", login);
			session.setMaxInactiveInterval(60*10);
			check = true;
		}
		
		Map<String, Boolean> map = new HashMap<>();
		map.put("check", check);		
		return map;
	}
	
	@RequestMapping(value = "/updatemember", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> updatemember(@RequestBody MemberDto dto, HttpSession session) {

        MemberDto login = biz.selectLogin(dto);
        boolean check = false;

        if (login != null) {
        	//비밀번호 업데이트 biz
            session.setAttribute("user", login);
            session.setMaxInactiveInterval(60 * 10);
            check = true;
        }

        Map<String, Boolean> map = new HashMap<>();
        map.put("check", check);
        return map;
    }
	
//	@RequestMapping("/rollbackPassword")
//	public int rollbackPassword()
	
	
	@RequestMapping(value="/uplikes")
	@ResponseBody
	public Map<String, Integer> uplikes(@RequestBody BoardDto dto, HttpSession session) {
		int bno = dto.getBno();
		BoardDto dto1 = biz.boardSelectOne(bno);
		
		int count = 0;
		count = dto1.getBlikes();
		int blikes = 0;
		
		blikes = count+1;
		biz.likesUp(blikes, bno);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("count", blikes);
		return map;
		
	}
	
//	@RequestMapping("/loginfail")
//	public String loginfail() {
//		return "login";
//	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping("/guestLogin")
	public String guestLogin(HttpSession session) {
		MemberDto guest = new MemberDto(0, null, null, "GUEST", null, "G");

		session.setAttribute("user", guest);
		session.setMaxInactiveInterval(60 * 10);
		return "redirect:mainlist";
	}

	@RequestMapping("/boardinsertform")
	public String boardinsertform() {
		return "boardinsert";
	}

	@PostMapping("/boardinsert")
	public String boardinsert(BoardDto dto, HttpSession session, UploadFile uploadFile, BindingResult result ) {

		MemberDto logindto = (MemberDto) session.getAttribute("user");
		int mno = logindto.getMno();
		String btitle = dto.getBtitle();
		String bcontent = dto.getBcontent();
		
		MultipartFile file = uploadFile.getFile();
		String bimg = file.getOriginalFilename();	//업로드 되어 controller로 넘어온 filedml 실제 이름
		//System.out.println(filename);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		
		if (biz.insertBoard(btitle, bcontent, bimg, mno) > 0) {
			
			try {
				inputStream = file.getInputStream();
				
				String path = "C:\\workspace\\6.FrameWork\\SemiProject_LACJ\\src\\main\\resources\\static\\img";
				File storage = new File(path);
				if(!storage.exists()) {	//존재여부 확인
					storage.mkdirs();	//없으면 디렉토리 만들기(폴더생성)
				}
				
				File newfile = new File(path+"/"+bimg);
				if(!newfile.exists()) {
					newfile.createNewFile();
				}
				
				System.out.println(newfile.getPath());
				outputStream = new FileOutputStream(newfile);
				
				int read = 0;
				byte[] b = new byte[(int)file.getSize()];
				while( (read=inputStream.read(b)) != -1 ) {
					outputStream.write(b,0,read);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
				return "redirect:mainlist";
			}else {
			return "insert";
		}
	}
	
	@RequestMapping("/mainlist")
	public String pagenation(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
	    int pageSize = 3;
	    int currentPage = Math.max(1, page);
	    int offset = (currentPage - 1) * pageSize;

	    List<BoardDto> boardList = biz.getBoards(offset, pageSize);
	    model.addAttribute("list", boardList);

	    int boardCount = biz.getBoardCount();
	    int totalPages = (int) Math.ceil((double) boardCount / pageSize);

	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);

	    return "mainlist";
	}
	
	
	
	@RequestMapping("/mypagedetail")
	public String mypagedetail(Model model, HttpSession session) {
		MemberDto dto = (MemberDto)session.getAttribute("user");
		
		model.addAttribute("dto", dto);
		return "mypagedetail";
	}
	
	

}
	

