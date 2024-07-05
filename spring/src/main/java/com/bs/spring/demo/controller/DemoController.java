package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.bs.spring.common.ExcelDemoListConvert;
import com.bs.spring.demo.model.dto.Address;
import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.model.service.DemoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DemoController {
	
	private final DemoService service;
	
	//Web에서 Controller객체는 client요청을 받아 서비스를 진행
	//Domain => URL 
	//서비스를 한개의 메소드로 처리 -> URL주소와 연결하기 위해서 @RequestMapping을 사용
	//@RequestMapping("URL매핑주소") -> URL주소와 연결
	
	//@Controller의 메소드를 선언하는 방법
	//Request주소와 매핑하기 위해서 어노테이션을 선언
	/*
	 * @RequetMapping("주소") : 요청 method가 get, post 모두 매핑할때
	 * Rest방식으로 서비스를 구현했을때 사용
	 * @GetMapping("주소") : 요청방식이 GET일때 매핑 -> GET방식일때만 실행
	 * @PostMapping("주소") : 요청방식이 POST일때 매핑 -> POST방식일때만 실행
	 * @PutMapping("주소") :  요청방식이 PUT일때 매핑 -> PUT방식일때만 실행
	 * @DeleteMapping("주소") : 요청방식이 DELETE일때 매핑 -> DELETE방식일때만 실행
	 * @PatchMapping("주소") : 요청방식이 PATCH일때 매핑 -> PATCH방식일때만 실행
	 * 매소드방식이 일치하지않으면 에러를 발생시킴 -> 에러페이지로 이동!
	 */
	
	/*
	 * 메소드 선언방식
	 * 반환형 메소드명([매개변수,....])
	 * 1. 반환형
	 * 	1) String : InternalResourceViewResolver클래스를 이용해서
	 * 				내부에서 jsp파일을 선택할때 
	 * 				prefix value+반환값+suffix value 파일을 찾음
	 *  2) void : URL 매핑주소가 view이름과 같을때 사용, 
	 *  		직접 HttpServletResponse객체를 이용해서 응답해줄때, 
	 *  3) ModelAndView : Model객체와 view을 한번에 저장하는 객체로, 
	 *  				jsp에 보낼데이터와 view이름을 저장해서 반환
	 *  4) ViewResolver를 구현한 클래스 : 구현된 클래스의 객체를 반환 -> 라이브러리
	 *  5) 일반 클래스타입 : Data를 json방식으로 응답할때 -> converter를 등록
	 *  
	 *  @ResponseBody어노테이션을 추가(반환형앞, 메소드위) -> Rest방식으로 data응답
	 *  
	 * 2. 매개변수 -> Spring이 줌~ 알아서 줌! 짜식!
	 *  1) HttpServletRequest : 서블릿 그놈 
	 *  2) HttpServletResponse : 서블릿 그놈
	 *  3) HttpSession : 서블릿 그녀석
	 *  4) java.util.Locale : 서버의 로케일정보를 저장한 객체
	 *  5) InputStream/Reader : 클라이언트와 연결된 스트림(받는것)
	 *  6) OutputStream/Writer : 클라이트와 연결된 스트림(보내는것)
	 *  7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터를 저장할 변수 -> 자동대입해줌  
	 *     - parameter key값과 변수명이 일치
	 *     - @RequestParam어노테이션으로 연결해줌 
	 *  8) DTO(클래스타입) : spring 만들어서 데이터저장 후 전달해줌. - command
	 *     - 필드명과 paramter key값이 같아야함.
	 *  9) java.util.Map : parameter를 map방식으로 전달해줌.
	 *  
	 *  10) @RequestHaeder("key") String변수선언 : 해더정보 가져오기
	 *  11) @CookieValue("key") String변수선언 : 쿠기정보 가져오기 
	 *  
	 *  12) Model : spring에서 사용하는 jsp에 데이터를 전달할때 사용하는 객체 == request
	 *      ModelAndView : Model과 view를 한번에 저장하는 객체
	 *      
	 *  매핑메소드에 사용하는 추가 어노테이션
	 *  @RequestBody : request body의 데이터가 json일때 -> 자바 클래스로 convert
	 *  @ResponseBody : 응답 body에 자바클래스를 json으로 convert
	 *  @ModelAttribute : model데이터 관리하는 속성 -> hibernate validator이용시 사용
	 *  @SessionAttributes : Model에 저장된 데이터를 scope를 Session으로 설정
	 *   
	 * */
	
	// 메소드명 : 개발자 맘대로, 규칙에 맞춰서 설정
	
	
	
	@RequestMapping("/demo/demo.do")
	public void demo() {}
	
//	public String demo() {
//		//화면전환용 메소드
//		//prefix+반환값+suffix
//		// /WEB-INF/views/demo/demo.jsp
//		return "demo/demo";//- RequestDispatcher.foward()동일
//	}
	
	
	//서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException,IOException {
		System.out.println(request);
		System.out.println(response);
		
		//클라이언트가 보낸 데이터 받아오기
		String devName=request.getParameter("devName");
		int devAge=Integer.parseInt(request.getParameter("devAge"));
		String devGender=request.getParameter("devGender");
		String devEmail=request.getParameter("devEmail");
		String[] devLang=request.getParameterValues("devLang");
		
		Demo d=Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devGender(devGender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		System.out.println(d);
		
		request.setAttribute("demo",d);
		
		request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp")
		.forward(request, response);
		
	}
	
	//전달된 파라미터 1:1로 매핑해서 가져오기
	@RequestMapping("/demo/demo2.do")
	public String demo2(String name, int devAge, String gender
			,String devEmail,String[] devLang, Model m) {
		
		System.out.println(name+devAge+gender+devEmail
				+Arrays.toString(devLang));
		
		Demo d=Demo.builder().devName(name).devAge(devAge)
				.devEmail(devEmail).devLang(devLang).devGender(gender)
				.build();
		//데이터는 Model클래스에 저장함.
		//데이터를 key:value구조로 저장함.
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	//@RequestParam을 이용해서 매개변수 선언하기
	//속성값을 설정할 수 있음
	// value, defaultValue, required
	@RequestMapping("/demo/demo3.do")
	public String demo3(
			@RequestParam(value="devName") String name,
			@RequestParam(value="devAge", defaultValue="10") int age,
			@RequestParam(value="devEmail") String email,
			@RequestParam(value="devGender", required=false) String gender,
			@RequestParam(value="devLang", required=true) String[] lang,
			Model m) {
		Demo d=Demo.builder().devName(name).devAge(age)
				.devEmail(email).devGender(gender)
				.devLang(lang).build();
		
		m.addAttribute("demo",d);
		
		return "demo/demoResult";
	}
	
	//DTO를 파라미터로 선언하기
	@RequestMapping("/demo/demo4.do")
	public String demo4(@ModelAttribute Demo d1, Address address) {
			//, Model m) {
		d1.setAddress(address);
//		m.addAttribute("demo",d);
//		System.out.println(d);
		
		return "demo/demoResult";
	}
	
	
	//Map으로 클라이언트가 보낸 데이터 받기
	@RequestMapping("/demo/demo5.do")
	public String demo5(@RequestParam Map param,String[] devLang,
			Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo",param);
		
		return "demo/demoResult";
	}
	
	//추가데이터 전달받아 처리하기
	//RequestHeader의 값, Cookie값, Session에 저장된 값
	
	@RequestMapping("/demo/extra.do")
	public String extraData(
			@RequestHeader(value="Accept-Language") String userAgent,
			@CookieValue(value="cookieData",required=false) String cookieData,
//			HttpSession session
			@SessionAttribute(value="sessionId") String sessionId
			) {
		System.out.println("userAgent : "+userAgent);
		System.out.println("cookieData : "+cookieData);
		System.out.println("sessionId : "+sessionId);
		
		return "index";
	}
	
	//ModelAndView객체 활용하기
	
	@RequestMapping("/demo/demo6.do")
	public ModelAndView modelViewTest(Demo d, ModelAndView mv) {
		//객체 저장하기 
		mv.addObject("demo", d);
		//화면이름설정하기
		mv.setViewName("demo/demoResult");
		
		Map<String, Object> data=mv.getModel();//데이터가져올때 사용할 수 있는 메소드
		System.out.println(data);
		
		String viewName=mv.getViewName();
		System.out.println(viewName);
		
		return mv;
	}
	
	//값을 반환하기 -> REST서비스를 구현할 때 많이 사용
	//반환하는 값이 객체일때는 classpath에 converter(Gson,Jackson)가 있어야함
	@RequestMapping("/demo/demo7.do")
	public @ResponseBody Demo dataRetrun() {
		return Demo.builder().devName("yoo").build();
	}
	
	//메소드별로 요청을 처리하는 요청메소드선언하기
//	@RequestMapping("/demo/test") -> get, post 모두 허용
//	@RequestMapping(value="/demo/demo8.do",
//			method = RequestMethod.POST)
//	@PostMapping("/demo/demo8.do")
	@GetMapping("/demo/demo8.do")
	public String postrequest(Demo d,Model m) {
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	//주소동적으로 매핑하기	
	//@PathVariable -> 동적으로 변경되는 주소값을 가져오는 기능
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable String no) {
		System.out.println(no);
		return "demo/demo";
	}
	
	//요청내용 리다이렉트 시키기
	// return "redirect:주소(jsp파일명 X -> mapping주소)";
	@GetMapping("/demo/demo9.do")
	public String sendRedirect() {
		return "redirect:/";
	}
	
	
	@PostMapping("/demo/insertdemo.do")
	public String insertDemo(Demo demo) {
		int result=service.insertDemo(demo);
		//Post Redirect Get
		return "redirect:/demo/demo.do";
	}
	
	@GetMapping("/demo/demolist.do")
	public String selectDemoAll(
			@RequestParam(defaultValue="1") int cPage,
			@RequestParam(defaultValue="10") int numPerpage, 
			Model m) {
		List<Demo> demos=service.selectDemoList(
				Map.of("cPage",cPage,"numPerpage",numPerpage));
		m.addAttribute("demos",demos);
		
		return "demo/demolist";
		
	}
	
	@GetMapping("/demo/excelfile")
//	public View excelDocument(Model model) {
	public String excelDocument(Model model) {
		List<Demo> demoList=service.selectDemoList(Map.of("cPage",1,"numPerpage",2));
		model.addAttribute("demoList",demoList);
//		return new ExceltDemoListConverter();
		return "excel";
	}
	
	
	
}
