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

    /**
     * 회원 탈퇴 기능
     * @param userId 탈퇴할 사용자 ID
     * @param currentPassword 현재 비밀번호
     * @param confirmPassword 비밀번호 확인
     * @return
     * @throws Exception
     */
    public boolean deleteMember(String userId, String currentPassword, String confirmPassword) throws Exception;

    /**
     * 탈퇴 상태 회원 목록을 조회하고 실제 삭제 처리
     * @return 삭제된 회원 목록
     */
    public List<Member> deleteOutMembers() throws Exception;

}
