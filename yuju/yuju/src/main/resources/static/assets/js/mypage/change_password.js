document.addEventListener("DOMContentLoaded", () => {
  // ✅ [1] 사이드바 활성화 효과 적용 (현재 페이지 강조)
  document.querySelectorAll(".sidebar ul li").forEach((item) => {
    item.addEventListener("click", () => {
      document
        .querySelectorAll(".sidebar ul li")
        .forEach((li) => li.classList.remove("active"));
      item.classList.add("active");
    });
  });

  // ✅ [2] 비밀번호 변경 폼 요소 가져오기
  const passwordForm = document.getElementById("passwordChangeForm");

  if (!passwordForm) {
    console.error("❌ 비밀번호 변경 폼을 찾을 수 없습니다.");
    return;
  }

  // ✅ [3] 폼 제출 시 유효성 검사 수행
  passwordForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // 기본 폼 제출 방지

    try {
      // ✅ [4] 현재 비밀번호 유효성 검사
      if (!regexHelper) throw new Error("regexHelper가 정의되지 않았습니다.");
      regexHelper.value("#currentPassword", "현재 비밀번호를 입력하세요.");

      // ✅ [5] 서버에 현재 비밀번호 검증 요청 (비동기 요청)
      const currentPassword = document.getElementById("currentPassword").value;
      const isValidPassword = await checkCurrentPassword(currentPassword);

      if (!isValidPassword) {
        throw new Error("현재 비밀번호가 올바르지 않습니다.");
      }

      // ✅ [6] 새 비밀번호 유효성 검사
      regexHelper.value("#newPassword", "새 비밀번호를 입력하세요.");
      regexHelper.password(
        "#newPassword",
        "비밀번호는 영문, 숫자, 특수문자를 포함해야 하며, 8자리 이상이어야 합니다."
      );
      regexHelper.minLength(
        "#newPassword",
        8,
        "비밀번호는 8자 이상이어야 합니다."
      );
      regexHelper.maxLength(
        "#newPassword",
        20,
        "비밀번호는 20자 이하이어야 합니다."
      );

      // ✅ [7] 비밀번호 확인 유효성 검사
      regexHelper.value("#confirmPassword", "비밀번호 확인을 입력하세요.");
      regexHelper.compareTo(
        "#newPassword",
        "#confirmPassword",
        "새로운 비밀번호가 일치하지 않습니다."
      );

      // ✅ [8] 서버에 비밀번호 변경 요청 (비동기 처리)
      const formData = new FormData(passwordForm);
      // const response = await axiosHelper.postMultipart(
      //   "/api/mypage/change_password",
      //   formData
      // );

      if (response.success) {
        // ✅ 비밀번호 변경 성공 알림
        await utilHelper.alertSuccess("비밀번호가 성공적으로 변경되었습니다.");
        window.location.reload(); // 페이지 새로고침
      } else {
        throw new Error(response.message || "비밀번호 변경 실패");
      }
    } catch (e) {
      console.error("❌ 유효성 검사 오류:", e);
      await utilHelper.alertDanger(e.message || "오류가 발생했습니다.");
      if (e.element) setTimeout(() => e.element.focus(), 300);
    }
  });
});

/**
 * 🔒 비밀번호 입력 필드 눈모양 아이콘 클릭 이벤트
 * - 비밀번호를 보이거나 숨길 수 있도록 전환
 */
document.querySelectorAll(".togglePassword").forEach((btn) => {
  btn.addEventListener("click", () => {
    const passwordInput = btn.previousElementSibling; // 바로 앞의 input 요소 선택
    const isPassword = passwordInput.type === "password";

    // 비밀번호 보이기/숨기기 전환
    passwordInput.type = isPassword ? "text" : "password";

    // 아이콘 변경 (눈 → 눈 감은 아이콘)
    btn.querySelector("i").classList.toggle("fa-eye-slash");
    btn.querySelector("i").classList.toggle("fa-eye");
  });
});
