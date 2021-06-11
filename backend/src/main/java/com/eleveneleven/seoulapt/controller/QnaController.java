package com.eleveneleven.seoulapt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleveneleven.seoulapt.model.dto.Comment;
import com.eleveneleven.seoulapt.model.dto.Qna;
import com.eleveneleven.seoulapt.model.service.HouseService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/qna")
public class QnaController {

	@Autowired
	private HouseService service;

	@GetMapping("/list")
	@ApiOperation(value = "모든 질문 글 리스트 반환", notes = "성공 시 List<Qna> 반환, 실패 or 비어있을 시 204코드 반환")
	private ResponseEntity<List<Qna>> list() {
		try {
			List<Qna> qna = service.getQnaList();
			if (qna == null || qna.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Qna>>(qna, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/write")
	@ApiOperation(value = "질문 글 작성", notes = "질문 글 작성")
	private ResponseEntity<String> write(@RequestBody Qna qna) {
		try {
			if (service.writeQuestion(qna) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/modify")
	@ApiOperation(value = "질문 글 수정", notes = "body로 qna객체 전달받아 해당 질문 글 수정")
	private ResponseEntity<String> modify(@RequestBody Qna qna) {
		try {
			if (service.modifyQuestion(qna) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "질문 글 삭제", notes = "질문 글 삭제")
	private ResponseEntity<String> delete(@RequestParam String id) {
		try {
			if (service.deleteQuestion(new Qna(id, null, null, null, null)) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/comment/list")
	@ApiOperation(value = "해당 글에 달린 댓글 반환", notes = "쿼리스트링으로 qnaboardid 전달받아 List<Comment> 반환")
	private ResponseEntity<List<Comment>> listComment(@RequestParam String qnaboardid) {
		try {
			List<Comment> list = service.getCommentList(qnaboardid);
			System.out.println(list.toString());
			if (list == null || list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/comment/write")
	@ApiOperation(value = "댓글 작성", notes = "body로 comment 객체 전달받아 댓글 작성")
	private ResponseEntity<String> writeComment(@RequestBody Comment comment) {
		try {
			if (service.writeComment(comment) == 1) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/comment/modify")
	@ApiOperation(value = "댓글 수정", notes = "body로 comment 객체 전달받아 댓글 수정")
	private ResponseEntity<String> modifyComment(@RequestBody Comment comment) {
		try {
			if (service.modifyComment(comment) == 1) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/comment/delete")
	@ApiOperation(value = "댓글 삭제", notes = "쿼리스트링으로 qnacommentid 전달받아 해당 댓글 삭제")
	private ResponseEntity<String> deleteComment(@RequestParam String qnacommentid) {
		try {
			if (service.deleteComment(qnacommentid) == 1) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/list/{qnaboardid}")
	@ApiOperation(value = "문제 얻어오기", notes = "pathvariable로 qnaboardid를 넘겨주면 그에 해당하는 qna객체 반환")
	private ResponseEntity<Qna> getQna(@PathVariable String qnaboardid) {
		try {
			Qna qna = service.getOneQna(qnaboardid);
			if (qna != null) {
				return new ResponseEntity<Qna>(qna, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
