package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.common.exception.BadAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final BoardService service;
	private final PageFactory pageFactory;
	
	@GetMapping("/boardlist.do")
	public void boardlist(
			@RequestParam(defaultValue="1") int cPage,
			@RequestParam(defaultValue="5") int numPerpage,
			Model model) {
		List<Board> result=service.selectList(
				Map.of("cPage",cPage,"numPerpage",numPerpage));
		model.addAttribute("boards",result);
		int totalData=service.selectBoardCount();
		String pageBar=pageFactory.getPage
				(cPage, numPerpage, totalData, "boardlist.do");
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("totalContents",totalData);
	}
	
	@PostMapping("/insertboard.do")
	public String insertBoard(Board b, MultipartFile[] upFile, 
			Model model,HttpSession session) {
//		log.debug("{}",upFile.getName());
//		log.debug("{}",upFile.getOriginalFilename());
//		log.debug("{}",upFile.getSize());
		List<Attachment> files=new ArrayList<>();
		String path=session.getServletContext().getRealPath("/resources/upload/board");
		if(upFile!=null) {
			for(MultipartFile file:upFile) {
				if(!file.isEmpty()) {
					//저장할 경로를 가져오기
					//파일 rename설정
					//String oriName=upFile.getOriginalFilename();
					String oriName=file.getOriginalFilename();
					String ext=oriName.substring(oriName.lastIndexOf("."));
					Date today=new Date(System.currentTimeMillis());
					int randomVal=(int)(Math.random()*10000)+1;
					String rename="BSLOVE_"
							+(new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(today))+
							"_"+randomVal+ext;
					File dir=new File(path);
					if(!dir.exists()) dir.mkdirs();
					try {
						//파일을 저장
						//upFile.transferTo(new File(path,rename));
						file.transferTo(new File(path,rename));
						files.add(Attachment.builder()
								.originalFilename(oriName)
								.renamedFilename(rename)
								.build());
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			//files board대입
			b.setFiles(files);
		}
		int result=0;
		String msg,loc;
		try {
			result=service.insertBoard(b);
			msg="게시글 등록 성공!";
			loc="/board/boardlist.do";
		}catch(BadAuthenticationException e) {
			msg="게시글 등록실패 다시 시도하세요!";
			loc="/board/inputboard.do";
			files.stream().forEach(f->{
				File delFile=new File(path+"/"+f);
				delFile.delete();
			});
		}
//		if(result>0) {
//			msg="게시글 등록 성공!";
//			loc="/board/boardlist.do";
//		}else {
//			msg="게시글 등록실패 다시 시도하세요!";
//			loc="/board/inputboard.do";		
//		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "common/msg";
	}
//	@GetMapping("/inputboard.do")
//	public void inputboard() {}
	@GetMapping("/boardview.do")
	public void boardView(int boardNo,Model model) {
		Board b=service.selectBoardByNo(boardNo);
		model.addAttribute("board",b);
	}
	
	@GetMapping("/filedownload.do")
	public void filedownload(String oriname, String rename,
			OutputStream out, HttpServletResponse response,
			HttpSession session) {
		String path=session.getServletContext()
				.getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+rename);
		try(FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);
				BufferedOutputStream bos
				=new BufferedOutputStream(out)){
			
			String encoding=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition",
					"attachment;filename=\""+encoding+"\"");
			int data=1;
			while((data=bis.read())!=-1) {
				bos.write(data);
			}
			
		}catch(IOException e) {
			
		}
				
	}
}
