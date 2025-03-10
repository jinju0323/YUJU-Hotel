package kr.project.yuju.services;

import java.util.List;
import java.util.Map;

import kr.project.yuju.models.Member;

public interface MemberService {
    /**
     * 회원 데이터를 저장한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Member addItem(Member params) throws Exception;

    /**
     * 회원 데이터를 수정한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Member editItem(Member params) throws Exception;

    /**
     * 회원 데이터를 삭제한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Member params) throws Exception;

    /**
     * 회원 데이터를 단일 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Member getItem(Member params) throws Exception;

    /**
     * 회원 데이터를 목록 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public List<Member> getList(Member params) throws Exception;

    /**
     * 회원 데이터를 수를 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Member params) throws Exception;

    /**
     * 아이디(이메일) 중복검사를 수행한다.
     * @param params
     * @return
     * @throws Exception
     */
    public void idCheck(String userId) throws Exception;

    /**
     * 로그인 처리 (JWT 발급)
     * @param userId
     * @param userPw
     * @return JWT 토큰 문자열
     * @throws Exception 로그인 실패 시 예외 발생
     */
    public Map<String, Object> login(String userId, String userPw) throws Exception;

}
