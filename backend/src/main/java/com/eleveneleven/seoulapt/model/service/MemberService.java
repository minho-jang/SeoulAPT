package com.eleveneleven.seoulapt.model.service;

import java.util.List;

import com.eleveneleven.seoulapt.model.dto.History;
import com.eleveneleven.seoulapt.model.dto.Member;

public interface MemberService {

//	로그인
	Member login(Member member);
	
//	회원가입
	int registerMemeber(Member member) throws Exception;

//	지역 북마크(즐겨찾기) 추가
	int registerHistory(String userid, String gugun);

//	지역 북마크(즐겨찾기) 삭제
	int removeHistory(String historyid);

//	회원정보 수정을 위한 북마크 리스트 얻어오기
	List<History> getHistories(String userid);
	
//	회원정보 수정
	int modifyMember(Member member);
	
//	회원탈퇴
	int deleteMember(String userid);

// 	비밀번호 찾기
	String findPassword(Member member);



	
}
