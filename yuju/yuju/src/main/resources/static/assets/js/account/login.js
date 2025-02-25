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

// ✅ [2] 로그인 폼 제출 이벤트
document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault(); // 기본 제출 방지

  try {
    // ✅ [3] 아이디(이메일) 입력값 유효성 검사
    regexHelper.value("#userId", "아이디(이메일)를 입력하세요.");
    regexHelper.email("#userId", "아이디(이메일) 형식이 올바르지 않습니다.");

    // ✅ [4] 비밀번호 입력값 유효성 검사
    regexHelper.value("#userPw", "비밀번호를 입력하세요.");
    regexHelper.password(
      "#userPw",
      "비밀번호는 8~20자의 영문 대소문자, 숫자, 특수문자로 입력해주세요."
    );
  } catch (e) {
    // 유효성 검사 실패 시 에러 메시지 표시
    await utilHelper.alertDanger(e.message);

    // 에러가 발생한 요소에 포커스를 맞춤
    setTimeout(() => e.element.focus(), 300);
    return;
  }

  // ✅ [5] 로그인 폼 데이터 생성
  const formData = new FormData(e.currentTarget);

  try {
    // ✅ [6] 서버에 로그인 요청
    const data = await axiosHelper.postMultipart(
      "/api/account/login",
      formData
    );

    // ✅ [7] 로그인 성공 처리
    if (data && data.token) {
      localStorage.setItem("token", data.token); // ✅ JWT 토큰 저장
      localStorage.setItem("userId", data.userId); // ✅ 사용자 ID 저장
      await utilHelper.alertSuccess("로그인 성공! 메인 페이지로 이동합니다.");
      window.location = "/"; // ✅ 메인 페이지 이동
    } else {
      await utilHelper.alertDanger("로그인 실패. 다시 시도해주세요.");
    }
  } catch (error) {
    await utilHelper.alertDanger("서버와의 통신 중 오류가 발생했습니다.");
  }
});
