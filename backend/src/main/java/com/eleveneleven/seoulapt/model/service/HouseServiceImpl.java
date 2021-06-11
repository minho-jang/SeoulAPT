package com.eleveneleven.seoulapt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleveneleven.seoulapt.model.dao.HouseDao;
import com.eleveneleven.seoulapt.model.dto.Comment;
import com.eleveneleven.seoulapt.model.dto.HouseDeal;
import com.eleveneleven.seoulapt.model.dto.Qna;

@Service
public class HouseServiceImpl implements HouseService{
	
	@Autowired 
	private HouseDao dao;
	
	/* 질문글 */
	@Override
	public int writeQuestion(Qna qna) {
		return dao.insertQuestion(qna);
	}
	@Override
	public List<Qna> getQnaList() {
		return dao.selectQna();
	}
	@Override
	public int modifyQuestion(Qna qna) {
		return dao.updateQuestion(qna);
	}
	@Override
	public int deleteQuestion(Qna qna) {
		return dao.deleteQuestion(qna);
	}
	
	/* 댓글 */
	@Override
	public List<Comment> getCommentList(String qnaboardid) {
		return dao.selectComments(qnaboardid);
	}
	@Override
	public int writeComment(Comment comment) {
		return dao.insertComment(comment);
	}
	@Override
	public int modifyComment(Comment comment) {
		return dao.updateComment(comment);
	}
	@Override
	public int deleteComment(String qnacommentid) {
		return dao.deleteComment(qnacommentid);
	}
	
	@Override
	public String getCode(String address) {
		return dao.selectDongCode(address);
	}
	@Override
	public Qna getOneQna(String qnaboardid) {
		return dao.selectOneQuestion(qnaboardid);
	}
	@Override
	public List<HouseDeal> getDeals(String gugunName) {
		return dao.selectDeals(gugunName);
	}
	@Override
	public List<HouseDeal> getAptDeals(HashMap<String, String> map) {
		return dao.selectApts(map);
	}
	@Override
	public List<Map<String, String>> satisticsOfGugunByPrice(Map<String, String> map) {
		return dao.satisticsOfGugunByPrice(map);
	}
	@Override
	public List<Map<String, String>> satisticsOfGugunByArea(Map<String, String> map) {
		return dao.satisticsOfGugunByArea(map);
	}
	@Override
	public List<Map<String, String>> satisticsOfAptByPrice(Map<String, String> map) {
		return dao.satisticsOfAptByPrice(map);
	}
}
