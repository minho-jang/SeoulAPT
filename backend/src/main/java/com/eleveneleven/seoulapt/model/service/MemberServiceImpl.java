package com.eleveneleven.seoulapt.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleveneleven.seoulapt.model.dao.MemberDao;
import com.eleveneleven.seoulapt.model.dto.History;
import com.eleveneleven.seoulapt.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public Member login(Member member) {
		return dao.login(member);
	}
	
	@Override
	public int registerMemeber(Member member) throws Exception {
		return dao.registerMemeber(member);
	}

	@Override
	public int registerHistory(String userid, String gugun) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("gugun", gugun);
		return dao.insertHistory(map);
	}
	
	@Override
	public int removeHistory(String historyid) {
		return dao.deleteHistory(historyid);
	}
	
	@Override
	public List<History> getHistories(String userid) {
		return dao.selectHistories(userid);
	}

	@Override
	public int modifyMember(Member member) {
		return dao.modifyMember(member);
	}

	@Override
	public int deleteMember(String userid) {
		return dao.deleteMember(userid);
	}

	@Override
	public String findPassword(Member member) {
		return dao.findPassword(member);
	}



	

}
