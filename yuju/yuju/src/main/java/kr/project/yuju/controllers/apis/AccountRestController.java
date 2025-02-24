package kr.project.yuju.controllers.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.project.yuju.helpers.FileHelper;
import kr.project.yuju.helpers.RegexHelper;
import kr.project.yuju.helpers.RestHelper;
import kr.project.yuju.helpers.WebHelper;
import kr.project.yuju.models.Member;
import kr.project.yuju.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AccountRestController {
    @Autowired
    private RegexHelper regexHelper;

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private WebHelper webHelper;

    @GetMapping("/api/account/id_check")
    public Map<String, Object> getIdCheck(@RequestParam("userId") String userId) {
        try {
            memberService.idCheck(userId);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        return restHelper.sendJson();
    }
    
    @PostMapping("/api/account/join")
    public Map<String, Object> join(
        @RequestParam("userId") String userId,
        @RequestParam("userPw") String userPw,
        @RequestParam("confirmPassword") String confirmPassword,
        @RequestParam("userName") String userName
    ) {
        try {
            // 아이디(이메일) 유효성 검사
            regexHelper.isValue(userId, "아이디(이메일)를 입력하세요.");
            regexHelper.isEmail(userId, "아이디(이메일)의 형식이 잘못되었습니다.");

             // 비밀번호 유효성 검사
            regexHelper.isValue(userPw, "비밀번호를 입력하세요.");
            regexHelper.isPassword(userPw, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력하세요.");
            regexHelper.isMatch(userPw, confirmPassword, "비밀번호 확인이 잘못되었습니다.");

            // 회원이름 유효성 검사
            regexHelper.isValue(userName, "회원이름을 입력하세요.");
            regexHelper.isKor(userName, "회원이름은 한글로만 입력하세요.");
        } catch (Exception e) {
            // 유효성 검사 실패 시 에러 응답 반환
            return restHelper.badRequest(e);
        }

        try {
            // 아이디 중복 검사
            memberService.idCheck(userId);
        } catch (Exception e) {
            // 아이디 중복 시 에러 응답 반환
            return restHelper.badRequest(e);
        }

        // 회원 정보 설정
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPw(userPw);
        member.setUserName(userName);

        try {
            // 회원 정보 저장
            memberService.addItem(member);
        } catch (Exception e) {
            // 회원 정보 저장 실패 시 서버 에러 응답 반환
            return restHelper.serverError(e);
        }

        // 성공 응답 반환
        return restHelper.sendJson();
    }
    
}
