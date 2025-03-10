document.addEventListener("DOMContentLoaded", () => {
  // ✅ [1] 탈퇴 폼 요소 가져오기
  const deleteForm = document.getElementById("deleteAccountForm");
  const deleteButton = deleteForm.querySelector(".btnDelete");

  // ✅ [2] 탈퇴 버튼 클릭 이벤트
  deleteButton.addEventListener("click", async (e) => {
    e.preventDefault(); // 기본 폼 제출 동작 방지

    try {
      // ✅ [3] 탈퇴 확인 알럿 (사용자가 '확인'을 눌렀을 때만 실행)
      const result = await utilHelper.confirmDanger(
        "정말 탈퇴하시겠습니까?",
        "이 작업은 되돌릴 수 없습니다."
      );

      // ✅ [4] 사용자가 확인을 눌렀을 경우 탈퇴 처리 진행
      if (result.isConfirmed) {
        // 🔥 실제 서버 API 요청을 보낼 경우, 여기에 추가 가능
        // await axiosHelper.post("/api/account/delete", { userId });

        // ✅ [5] 탈퇴 성공 알럿
        await utilHelper.alertSuccess("회원 탈퇴가 완료되었습니다.");

        // ✅ [6] 로컬 스토리지에 저장된 정보 삭제
        // localStorage.removeItem("token");
        // localStorage.removeItem("userId");
        // localStorage.removeItem("savedUserId");

        // ✅ [7] 메인 페이지로 리디렉션
        // window.location.href = "/";
      }
    } catch (error) {
      // ⛔ [ERROR] 탈퇴 처리 중 오류 발생
      await utilHelper.alertDanger("회원 탈퇴 중 오류가 발생했습니다.");
    }
  });
});
