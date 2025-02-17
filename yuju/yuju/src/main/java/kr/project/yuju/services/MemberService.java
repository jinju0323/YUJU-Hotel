package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.Member;

public interface MemberService {
    public Member addItem(Member params) throws Exception;

    public Member editItem(Member params) throws Exception;

    public int deleteItem(Member params) throws Exception;

    public Member getItem(Member params) throws Exception;

    public List<Member> getList(Member params) throws Exception;

    public int getCount(Member params) throws Exception;
}
