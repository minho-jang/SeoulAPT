package com.eleveneleven.seoulapt.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eleveneleven.seoulapt.model.dto.Comment;
import com.eleveneleven.seoulapt.model.dto.HouseDeal;
import com.eleveneleven.seoulapt.model.dto.Qna;

@Mapper
public interface HouseDao {

	public int insertQuestion(Qna qna);
	public List<Qna> selectQna();
	public int updateQuestion(Qna qna);
	public int deleteQuestion(Qna qna);

	public List<Comment> selectComments(String qnaboardid);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(String qnacommentid);
	
	public String selectDongCode(String address);
	public Qna selectOneQuestion(String qnaboardid);
	
	public List<HouseDeal> selectDeals(String gugunName);
	public List<HouseDeal> selectApts(HashMap<String, String> map);
//	통계 자료
	public List<Map<String, String>> satisticsOfGugunByPrice(Map<String, String> map);
	public List<Map<String, String>> satisticsOfGugunByArea(Map<String, String> map);
	public List<Map<String, String>> satisticsOfAptByPrice(Map<String, String> map);
}
