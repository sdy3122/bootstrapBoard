package kr.co.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.BoardVO;
import kr.co.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//여기는 servlet-context.xml에 기본으로 설정되어 있어 별도 설정 필요없음
@Log4j
@Controller
@RequestMapping("/board") // /board가 시작점
@AllArgsConstructor
public class BoardController {

	private BoardService service; //

	@GetMapping("/list")
	public void list(Model model) { // model : 내가 원하는 정보를 jsp쪽으로 보낼때
									// (상자라고 비유하고 상자안에 필요한 정보를 담아 보낸다)
		log.info("list"); // 목록
		model.addAttribute("list", service.getList());
		// service에서 getList로 가져온 값을 list에 넣는다
		// list는 나중에 게시물의 목록을 전달해야 하므로 Model을 파라미터로 지정하고
		// 이를 통해 BoardServiceImpl 객체의 getList결과를 담아 전달한다
		// addAttribute : Controller에서 사용자에게 응답할 View를 생성할 때
		// Model을 이용하여 View에게 데이터를 전달하는 방법

	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register") // 생성
	public String register(BoardVO board, RedirectAttributes rttr) {
		// String을 리턴타입으로 지정하고, RedirectAttributes를 파라미터로 지정한다
		// 등록작업이 끝난 후 다시 목록화면으로 이동하기 위함임
		// 추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해서 RedirectAttributes를 사용함
		service.register(board);
		log.info("register...." + board);

		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
		// url이동을 하기위해 redirect를 사용함
		// redirect: 접두어를 사용하는데 이를 이용하면 스프링 MVC가 내부적으로
		// response.sendRedirect()를 처리해 준다
	}

	@GetMapping({ "/get", "/modify" }) // 조회 (get과 modify를 동시에) (modify 이건 Get으로)
	public void get(@RequestParam("bno") Long bno, Model model) {
		// RequestParam : bno값을 좀 더 명시적으로 처리한다
		// 화면쪽으로 해당번호를 게시물을 전달해야 하므로 Model을 파라미터로 지정한다
		log.info("/get or modify ");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify") // 수정 (이건 Post로)
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify : " + board);
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}

	
	@PostMapping("/remove") //삭제 
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove..."+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	 
}
