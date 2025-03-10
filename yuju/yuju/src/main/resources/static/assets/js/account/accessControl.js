document.addEventListener("DOMContentLoaded", async () => {
  // ✅ 현재 로그인 상태 확인 (JWT 토큰 존재 여부 체크)
  const token = localStorage.getItem("token");

  // ✅ 현재 사용자가 보고 있는 페이지의 URL 경로 가져오기
  const currentPath = window.location.pathname;

  /**
   * ✅ [1] 로그인한 사용자가 접근할 수 없는 페이지 차단 (로그인, 회원가입)
   *
   * - 사용자가 이미 로그인한 상태라면 `/login`, `/join` 페이지에 접근할 이유가 없음
   * - 이러한 경우, 메인 페이지(`/`)로 강제 이동시킴
   */
  if (token && (currentPath === "/login" || currentPath === "/join")) {
    await utilHelper.alertWarning(
      "이미 로그인된 상태입니다.", // ✅ 경고 메시지
      "메인 페이지로 이동합니다." // ✅ 추가 설명
    );
    window.location.href = "/"; // ✅ 메인 페이지로 이동
    return;
  }

  /**
   * ✅ [2] 로그아웃한 사용자가 접근할 수 없는 페이지 차단 (마이페이지 관련)
   *
   * - 사용자가 로그인하지 않은 상태에서 `/mypage/*` URL로 접근하면 안 됨
   * - 로그인이 필요한 페이지이므로, 로그인 페이지(`/login`)로 강제 이동시킴
   */
  if (!token && currentPath.startsWith("/mypage")) {
    await utilHelper.alertWarning(
      "로그인이 필요합니다.", // ✅ 경고 메시지
      "로그인 페이지로 이동합니다." // ✅ 추가 설명
    );
    window.location.href = "/login"; // ✅ 로그인 페이지로 이동
    return;
  }
});
