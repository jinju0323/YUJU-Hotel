// jQuery 4.0 베타 버전 사용
$(document).ready(function () {
    // 비밀번호 토글 아이콘과 입력 필드 선택
    const $togglePassword = $("#togglePassword");
    const $passwordInput = $("#password");
  
    // 비밀번호 보기/숨기기 기능
    $togglePassword.on("click", function () {
      // 현재 비밀번호 필드의 타입이 'password'인지 확인
      const isPasswordHidden = $passwordInput.attr("type") === "password";
  
      // 'password' → 'text' 또는 'text' → 'password'로 변경
      $passwordInput.attr("type", isPasswordHidden ? "text" : "password");
  
      // 아이콘 변경: 눈 모양(open) ⇄ 눈 감은 모양(close)
      $togglePassword.toggleClass("fa-eye fa-eye-slash");
    });
  });
  