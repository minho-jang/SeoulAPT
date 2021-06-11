package com.eleveneleven.seoulapt.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eleveneleven.seoulapt.model.dto.History;
import com.eleveneleven.seoulapt.model.dto.Member;


@Mapper
public interface MemberDao {	
//	로그인
	Member login(Member member);
	
//	회원가입
	int registerMemeber(Member member) throws Exception;
	
//	지역 즐겨찾기 추가
	int insertHistory(Map map);

//	북마크 삭제
	int deleteHistory(String historyid);
	
//	회원정보 수정을 위한 즐겨찾기 목록 얻기
	List<History> selectHistories(String userid);
	
//	회원정보 수정
	int modifyMember(Member member);
	
//	회원탈퇴
	int deleteMember(String userid);
	
// 비밀번호 찾기	
	String findPassword(Member member);


}
