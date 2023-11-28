package com.main.lacj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.main.lacj.model.dto.CommentDto;
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
    public String registinsert(MemberDto dto, UploadFile uploadFile) {

        if (uploadFile.getFile() != null) {

            MultipartFile file = uploadFile.getFile();
            
            String originalBimg = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalBimg);
            String bimg = UUID.randomUUID().toString() + "." + extension;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            dto.setMimg(bimg);

            if (biz.insertRegi(dto) > 0) {

                try {
                    inputStream = file.getInputStream();

                    String path = "C:\\workspace\\6_FrameWork\\SemiProject_LACJ\\src\\main\\resources\\static\\img\\profile";
                    File storage = new File(path);
                    if(!storage.exists()) {    //존재여부 확인
                        storage.mkdirs();    //없으면 디렉토리 만들기(폴더생성)
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
            }

            return "login";

        }else if (biz.insertRegi(dto) > 0) {
            return "login";
        } else {
            return "redirect:regist";
        }
    }
	
	@RequestMapping("/boardUpdate")
	public String boardUpdate(BoardDto dto, UploadFile uploadfile) {
		
		if (uploadfile.getFile() != null) {

            MultipartFile file = uploadfile.getFile();
            
            String originalBimg = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalBimg);
            String bimg = UUID.randomUUID().toString() + "." + extension;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            dto.setBimg(bimg);

            if (biz.boardUpdate(dto) > 0) {

                try {
                    inputStream = file.getInputStream();

                    String path = "C:\\workspace\\6.FrameWork\\SemiProject_LACJ\\src\\main\\resources\\static\\img\\profile";
                    File storage = new File(path);
                    if(!storage.exists()) {    //존재여부 확인
                        storage.mkdirs();    //없으면 디렉토리 만들기(폴더생성)
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
            }
		}
		return "redirect:mypage";
	}		

	@RequestMapping("/regist")
	public String regist() {

		return "regist";
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(BoardDto dto) {
		int bno = dto.getBno();
		biz.boardDelete(bno);
		
		return "redirect:mainlist";
	}
	
	@RequestMapping("/boardUpdateForm")
	public String boardUpdateForm(Model model, BoardDto dto) {
		BoardDto dto1 = biz.boardSelectOne(dto.getBno());
		
		model.addAttribute("dto", dto1);
		
		return "boardupdate";
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
    public Map<String, Boolean> updatemember(@RequestBody HashMap<String, Object> param, HttpSession session) {
        MemberDto dto = (MemberDto) session.getAttribute("user");
        dto.setMpw((String)param.get("mpw"));
        Map<String, Boolean> map = new HashMap<>();
        int res = biz.updatemember(dto);
        if (res>0) {
            map.put("msg", true);
        } else {
            map.put("msg", false);
        }

        return map;
    } 

	@RequestMapping("/mypagedetail")
    public String mypagedetail(HttpSession session, Model model) {
        MemberDto dto = (MemberDto)session.getAttribute("user");
        model.addAttribute("dto",dto); 
        return "mypagedetail";
    }
	
	
//	@RequestMapping(value = "/updatemember", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Boolean> updatemember(@RequestBody MemberDto dto, HttpSession session) {
//
//        MemberDto login = biz.selectLogin(dto);
//        boolean check = false;
//
//        if (login != null) {
//        	//비밀번호 업데이트 biz
//            session.setAttribute("user", login);
//            session.setMaxInactiveInterval(60 * 10);
//            check = true;
//        }
//
//        Map<String, Boolean> map = new HashMap<>();
//        map.put("check", check);
//        return map;
//    }
	
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
    @RequestMapping(value = "/downlikes")
    @ResponseBody
    public Map<String, Integer> downlikes(@RequestBody BoardDto dto) {
        int bno = dto.getBno();
        BoardDto dto1 = biz.boardSelectOne(bno);

        int count = 0;
        count = dto1.getBlikes();
        int blikes = 0;

        blikes = count - 1;
        biz.likesDown(blikes, bno);

        Map<String, Integer> map = new HashMap<>();
        map.put("count", blikes);
        return map;

    }

    @RequestMapping(value = "/addcomment")
    @ResponseBody
    public Map<String, Integer> addcomment(@RequestBody CommentDto commentdto) {
        int bno=commentdto.getBno();
        String comment=commentdto.getComment();
        
        int res =biz.addcomment(bno,comment);
        System.out.println(res);
        Map<String, Integer> map = new HashMap<>();
        map.put("res", res);
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
		String bpublic = dto.getBpublic();
		
		MultipartFile file = uploadFile.getFile();
		//System.out.println(filename);
		
		String originalBimg = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalBimg);
        String bimg = UUID.randomUUID().toString() + "." + extension;
        
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		
		if (biz.insertBoard(btitle, bcontent, bimg, mno, bpublic) > 0) {
			
			try {
				inputStream = file.getInputStream();
				
				String path = "C:\\workspace\\6_FrameWork\\SemiProject_LACJ\\src\\main\\resources\\static\\img";
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
	public String pagenation(@RequestParam(name = "page", defaultValue = "1") int page, Model model, HttpSession session) {
	    int pageSize = 3;
	    int currentPage = Math.max(1, page);
	    int offset = (currentPage - 1) * pageSize;
	    MemberDto dto = (MemberDto)session.getAttribute("user");
	    
	    if(dto.getMstatus() == null) {
		    	
		    List<BoardDto> boardList = biz.getBoards(offset, pageSize);
		    model.addAttribute("list", boardList);
	
		    int boardCount = biz.getBoardCount();
		    int totalPages = (int) Math.ceil((double) boardCount / pageSize);
	
		    model.addAttribute("currentPage", currentPage);
		    model.addAttribute("totalPages", totalPages);
	
		    return "mainlist";
	    }else {
	    	List<BoardDto> boardList = biz.getGuestBoards(offset, pageSize);
		    model.addAttribute("list", boardList);
	
		    int boardCount = biz.getBoardCount();
		    int totalPages = (int) Math.ceil((double) boardCount / pageSize);
	
		    model.addAttribute("currentPage", currentPage);
		    model.addAttribute("totalPages", totalPages);
	
		    return "mainlist";
	    }
	}
	
	@RequestMapping("/boarddetail")
	public String boarddetail(@RequestParam(name="bno")int bno, HttpSession session, Model model) {
		
		BoardDto dto = biz.boardSelectOne(bno);
		List<CommentDto> list = biz.commentSelectAll(bno);
		
		//session.setAttribute("user", session.getAttribute("user"));
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		
		return "boarddetail";
	}
	

}
	

