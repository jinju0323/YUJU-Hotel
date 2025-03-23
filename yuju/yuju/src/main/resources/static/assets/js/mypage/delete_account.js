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

/**
 * 🗑️ 회원 탈퇴 요청 처리
 * - 회원 탈퇴 폼 제출 시 서버로 요청을 보내고 결과를 처리
 */
document.addEventListener("DOMContentLoaded", () => {
  const deleteForm = document.getElementById("deleteAccountForm");

  // 폼 제출 이벤트 핸들러
  deleteForm.addEventListener("submit", async (e) => {
    e.preventDefault(); // 기본 폼 제출 동작 방지

    // 🔑 입력된 비밀번호와 비밀번호 확인 값 가져오기
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    // 🚨 비밀번호와 비밀번호 확인이 일치하는지 검증
    if (password !== confirmPassword) {
      await utilHelper.alertDanger(
        "비밀번호가 일치하지 않습니다. 다시 확인해주세요."
      );
      return;
    }

    // 🆔 로컬 스토리지에서 사용자 ID 가져오기
    const userId = localStorage.getItem("userId");

    // 🚨 사용자 ID가 없는 경우 처리
    if (!userId) {
      await utilHelper.alertDanger(
        "사용자 정보가 없습니다. 다시 로그인 해주세요."
      );
      return;
    }

    try {
      // ⚠️ 탈퇴 확인 알림 표시
      const result = await utilHelper.confirmDanger(
        "정말 탈퇴하시겠습니까?",
        "이 작업은 되돌릴 수 없습니다."
      );

      // 사용자가 확인을 눌렀을 경우 탈퇴 처리 진행
      if (result.isConfirmed) {
        // 📨 서버 API 요청 (회원 탈퇴 요청)
        await axios.post("/api/account/delete", {
          userId: userId,
          currentPassword: password,
          confirmPassword: confirmPassword,
        });

        // ✅ 탈퇴 성공 알림
        await utilHelper.alertSuccess("회원 탈퇴가 완료되었습니다.");

        // 🗑️ 로컬 스토리지에 저장된 정보 삭제
        localStorage.removeItem("token");
        localStorage.removeItem("userId");

        // 🔄 메인 페이지로 리디렉션
        window.location.href = "/";
      }
    } catch (error) {
      // ❌ 오류 처리
      await utilHelper.alertDanger("회원 탈퇴 중 오류가 발생했습니다.");
    }
  });
});
