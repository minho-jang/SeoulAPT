package com.eleveneleven.seoulapt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eleveneleven.seoulapt.model.dto.Comment;
import com.eleveneleven.seoulapt.model.dto.HouseDeal;
import com.eleveneleven.seoulapt.model.dto.Qna;

@Service
public interface HouseService {
	
	public int writeQuestion(Qna qna);
	public List<Qna> getQnaList();
	public int modifyQuestion(Qna qna);
	public int deleteQuestion(Qna qna);

	public List<Comment> getCommentList(String qnaboardid);
	public int writeComment(Comment comment);
	public int modifyComment(Comment comment);
	public int deleteComment(String qnacommentid);
	
	public String getCode(String address);
	public Qna getOneQna(String qnaboardid);
	
	public List<HouseDeal> getDeals(String gugunName);
	public List<HouseDeal> getAptDeals(HashMap<String, String> map);
	
//	통계메소드
	public List<Map<String, String>> satisticsOfGugunByPrice(Map<String, String> map);
	public List<Map<String, String>> satisticsOfGugunByArea(Map<String, String> map);
	public List<Map<String, String>> satisticsOfAptByPrice(Map<String, String> map);
}
