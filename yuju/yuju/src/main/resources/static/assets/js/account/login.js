// ✅ 페이지 로드 시 저장된 아이디 불러오기 (아이디 저장 기능 반영)
document.addEventListener("DOMContentLoaded", function () {
  const savedUserId = localStorage.getItem("savedUserId");
  const userIdInput = document.getElementById("userId");
  const rememberMeCheckbox = document.getElementById("rememberMe");

  // ✅ 저장된 아이디가 있으면 입력 필드에 자동 입력 & 체크박스 체크
  if (savedUserId) {
    userIdInput.value = savedUserId;
    rememberMeCheckbox.checked = true;
  } else {
    rememberMeCheckbox.checked = false;
  }
});

// ✅ 비밀번호 보기/숨기기 기능
document
  .getElementById("togglePassword")
  .addEventListener("click", function () {
    const passwordInput = document.getElementById("userPw");
    const isPasswordHidden = passwordInput.type === "password";

    // ✅ 비밀번호 필드의 타입을 'password' <-> 'text' 로 변경
    passwordInput.type = isPasswordHidden ? "text" : "password";

    // ✅ 아이콘 변경 (눈 모양 토글)
    this.classList.toggle("fa-eye");
    this.classList.toggle("fa-eye-slash");
  });

// ✅ 로그인 폼 제출 이벤트 (서버로 로그인 요청)
document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault(); // 기본 제출 방지

  try {
    // ✅ 아이디 입력값 유효성 검사
    regexHelper.value("#userId", "아이디(이메일)를 입력하세요.");
    regexHelper.email("#userId", "아이디(이메일) 형식이 올바르지 않습니다.");

    // ✅ 비밀번호 입력값 유효성 검사
    regexHelper.value("#userPw", "비밀번호를 입력하세요.");
    regexHelper.password(
      "#userPw",
      "비밀번호는 8~20자의 영문 대소문자, 숫자, 특수문자로 입력해주세요."
    );
  } catch (e) {
    await utilHelper.alertDanger(e.message);
    setTimeout(() => e.element.focus(), 300);
    return;
  }

  // ✅ 로그인 폼 데이터 생성
  const formData = new FormData(e.currentTarget);

  try {
    // ✅ 서버에 로그인 요청
    const data = await axiosHelper.postMultipart(
      "/api/account/login",
      formData
    );

    // ✅ 로그인 성공 처리
    if (data && data.token) {
      localStorage.setItem("token", data.token); // ✅ JWT 토큰 저장
      localStorage.setItem("userId", data.userId); // ✅ 사용자 ID 저장 (UI 업데이트 용도)

      // ✅ "아이디 저장" 체크 여부 확인
      const rememberMe = document.getElementById("rememberMe").checked;
      if (rememberMe) {
        localStorage.setItem("savedUserId", data.userId); // ✅ 아이디 저장 (토큰 만료까지 유지)
      } else {
        localStorage.removeItem("savedUserId"); // ✅ 체크 해제 시 아이디 삭제
      }

      await utilHelper.alertSuccess("로그인 성공! 메인 페이지로 이동합니다.");
      window.location.href = "/"; // ✅ 메인 페이지 이동
    } else {
      await utilHelper.alertDanger("로그인 실패. 다시 시도해주세요.");
    }
  } catch (error) {
    await utilHelper.alertDanger("서버와의 통신 중 오류가 발생했습니다.");
  }
});
