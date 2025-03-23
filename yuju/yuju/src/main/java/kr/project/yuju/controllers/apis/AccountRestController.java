package kr.project.yuju.controllers.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.project.yuju.helpers.RegexHelper;
import kr.project.yuju.helpers.RestHelper;
import kr.project.yuju.models.Member;
import kr.project.yuju.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AccountRestController {
    @Autowired
    private RegexHelper regexHelper;

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    /**
     * 아이디 중복 검사 API
     * @param userId
     * @return
     */
    @GetMapping("/api/account/id_check")
    public Map<String, Object> getIdCheck(
            @RequestParam("userId") String userId) {

        try {
            memberService.idCheck(userId);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        return restHelper.sendJson();
    }

    /**
     * 회원가입 API
     * @param userId
     * @param userPw
     * @param confirmPassword
     * @param userName
     * @return
     */
    @PostMapping("/api/account/join")
    public Map<String, Object> join(
        @RequestParam("userId") String userId,
        @RequestParam("userPw") String userPw,
        @RequestParam("confirmPassword") String confirmPassword,
        @RequestParam("userName") String userName) {
            
        try {
            // ✅ [1] 아이디(이메일) 유효성 검사
            regexHelper.isValue(userId, "아이디(이메일)를 입력하세요.");
            regexHelper.isEmail(userId, "아이디(이메일)의 형식이 잘못되었습니다.");
        
            // ✅ [2] 비밀번호 유효성 검사
            regexHelper.isValue(userPw, "비밀번호를 입력하세요.");
            regexHelper.isPassword(userPw, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력하세요.");
            regexHelper.isMatch(userPw, confirmPassword, "비밀번호 확인이 잘못되었습니다.");
        
            // ✅ [3] 회원이름 유효성 검사
            regexHelper.isValue(userName, "회원이름을 입력하세요.");
            regexHelper.isKor(userName, "회원이름은 한글로만 입력하세요.");
        } catch (Exception e) {
            // ⛔ [ERROR] 유효성 검사 실패 → 400 Bad Request 응답 반환
            return restHelper.badRequest(e);
        }
        
        try {
            // ✅ [4] 아이디 중복 검사
            memberService.idCheck(userId);
        } catch (Exception e) {
            // ⛔ [ERROR] 중복된 아이디(이메일) 존재 → 400 Bad Request 응답 반환
            return restHelper.badRequest(e);
        }
        
        // ✅ [5] 회원 정보 설정
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPw(userPw);
        member.setUserName(userName);
        
        try {
            // ✅ [6] 회원 정보 저장 (비밀번호 암호화는 서비스 레이어에서 수행됨)
            memberService.addItem(member);
        } catch (Exception e) {
            // ⛔ [ERROR] 데이터베이스 오류 → 500 Internal Server Error 응답 반환
            return restHelper.serverError(e);
        }
        
        // ✅ [7] 회원가입 성공 응답 반환
        return restHelper.sendJson();
        
    }
    
    /**
     * 로그인 API
     * @param userId
     * @param userPw
     * @return
     */
    @PostMapping("/api/account/login")
    public Map<String, Object> login(
        @RequestParam("userId") String userId,
        @RequestParam("userPw") String userPw) {

        try {
            // ✅ [1] 아이디(이메일) 유효성 검사
            regexHelper.isValue(userId, "아이디(이메일)를 입력하세요.");
            regexHelper.isEmail(userId, "아이디(이메일)의 형식이 잘못되었습니다.");

            // ✅ [2] 비밀번호 유효성 검사
            regexHelper.isValue(userPw, "비밀번호를 입력하세요.");
            regexHelper.isPassword(userPw, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력하세요.");
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        try {
            // ✅ [3] 로그인 처리 (토큰 & userId 함께 반환)
            Map<String, Object> loginResult = memberService.login(userId, userPw);

            return restHelper.sendJson(Map.of(
                "token", loginResult.get("token"),
                "userId", loginResult.get("userId"),
                "message", "로그인 성공"
            ));
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
    }

    /**
     * 회원 탈퇴 API
     * @param userId 탈퇴할 사용자 ID
     * @param currentPassword 현재 비밀번호
     * @param confirmPassword 비밀번호 확인
     * @return 처리 결과 메시지
     */
    @PostMapping("/api/account/delete")
    public Map<String, Object> deleteAccount(@RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        String currentPassword = body.get("currentPassword");
        String confirmPassword = body.get("confirmPassword");
    
        try {
            if (!currentPassword.equals(confirmPassword)) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }
    
            boolean result = memberService.deleteMember(userId, currentPassword, confirmPassword);
            if (result) {
                return restHelper.sendJson(Map.of("message", "회원 탈퇴가 완료되었습니다."));
            } else {
                return restHelper.badRequest("탈퇴 처리에 실패했습니다.");
            }
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
    }
    
    
}
