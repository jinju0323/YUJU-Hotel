// ✅ [1] 비밀번호 보기/숨기기 기능
document
  .getElementById("togglePassword")
  .addEventListener("click", function () {
    // 비밀번호 입력 필드를 선택
    const passwordInput = document.getElementById("userPw");

    // 현재 비밀번호 필드의 타입이 'password'인지 확인
    const isPasswordHidden = passwordInput.type === "password";

    // 'password' → 'text' 또는 'text' → 'password'로 변경하여 비밀번호 보이기/숨기기 전환
    passwordInput.type = isPasswordHidden ? "text" : "password";

    // 아이콘 변경: 눈 모양(open) ⇄ 눈 감은 모양(close)
    this.classList.toggle("fa-eye");
    this.classList.toggle("fa-eye-slash");
  });
