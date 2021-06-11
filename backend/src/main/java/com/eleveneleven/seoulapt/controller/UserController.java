package com.eleveneleven.seoulapt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleveneleven.seoulapt.model.dto.History;
import com.eleveneleven.seoulapt.model.dto.Member;
import com.eleveneleven.seoulapt.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private MemberService service;

//	1. 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "쿼리스트링으로 userid, userpwd 전달받아 확인 후 일치 시 해당 유저의 정보를 반환")
	private ResponseEntity<Member> login(@RequestBody Member m) {
//		System.out.println(m.getUserid() + ", " + m.getUserpwd());
		try {
			Member member = service.login(new Member(m.getUserid(), m.getUserpwd(),null, null, null));
			if (member != null) { // 로그인 성공
				System.out.println(member.toString());
				return new ResponseEntity<Member>(member, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

//	2. 회원가입
	@PostMapping("/join")
	@ApiOperation(value = "회원가입", notes = "@RequestBody로 userid, gugun을 전달하면 DB에 새로 회원정보 저장")
	private ResponseEntity<String> join(@RequestBody Member member) {
		try {
			if (service.registerMemeber(member) == 1) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

//	3. 회원정보 수정
	@PutMapping("/modify")
	@ApiOperation(value = "회원정보수정시 해당 회원 정보 수정", notes = "@RequestBody로 Member객체를 전달받아 userid에 해당하는 userpwd를 수정")
	private ResponseEntity<String> modify(@RequestBody Member member) {
		try {
			if (service.modifyMember(member) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

//	4. 회원탈퇴
	@DeleteMapping("/delete")
	@ApiOperation(value = "회원 탈퇴", notes = "쿼리스트링으로 userid 전달하면 DB에서 해당 회원id row delete")
	private ResponseEntity<String> delete(@RequestParam String userid) {
		try {
			if (service.deleteMember(userid) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

//	5. 지역 즐겨찾기 추가
	@PostMapping("/history/add")
	@ApiOperation(value = "히스토리추가 ", notes = "쿼리스트링으로 userid, gugun을 전달하면 DB에 history 저장")
	private ResponseEntity<String> addHistory(@RequestBody Map<String, String> map) {
		System.out.println(map.get("userid"));
		System.out.println(map.get("gugun"));
		String userid = map.get("userid");
		String gugun = map.get("gugun");
		try {
			if (service.registerHistory(userid, gugun) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

//	6. 지역 즐겨찾기 삭제
	@DeleteMapping("/history/delete")
	@ApiOperation(value = "히스토리 삭제", notes = "쿼리스트링으로 bookmarkid 전달받아 DB에 해당하는 history 삭제")
	private ResponseEntity<String> removeHistory(@RequestParam String historyid) {
		try {
			if (service.removeHistory(historyid) == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
//	7. 즐겨찾기 목록 가져오기
	@GetMapping("/history")
	@ApiOperation(value = "히스토리 목록 가져오기", notes = "해당 유저 아이디에 해당하는 최근검색 목록5개 가져오기")
	private ResponseEntity<List<History>> getHistoris(@RequestParam String userid) {
		try {
			List<History> histories = service.getHistories(userid);
			System.out.println(histories.toString());
			return new ResponseEntity<List<History>>(histories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
