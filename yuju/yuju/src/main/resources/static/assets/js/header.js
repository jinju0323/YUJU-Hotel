document.addEventListener("DOMContentLoaded", () => {
  // ✅ 로그인 상태 확인 (토큰 존재 여부)
  const token = localStorage.getItem("token");

  // ✅ 네비게이션 메뉴 요소 가져오기
  const loginMenu = document.getElementById("loginMenu");
  const signupMenu = document.getElementById("signupMenu");
  const logoutMenu = document.getElementById("logoutMenu");
  const mypageMenu = document.getElementById("mypageMenu");

  // ✅ 로그인 상태에 따라 메뉴 표시 변경
  token
    ? ((loginMenu.style.display = "none"),
      (signupMenu.style.display = "none"),
      (logoutMenu.style.display = "block"),
      (mypageMenu.style.display = "block"))
    : ((loginMenu.style.display = "block"),
      (signupMenu.style.display = "block"),
      (logoutMenu.style.display = "none"),
      (mypageMenu.style.display = "none"));

  // ✅ 로그아웃 기능
  document.getElementById("logoutBtn").addEventListener("click", async (e) => {
    e.preventDefault();

    // ✅ 로그아웃 확인 알럿창 (사용자가 '확인'을 눌렀을 때만 실행)
    const confirmLogout = await utilHelper.confirmSuccess(
      "로그아웃",
      "정말 로그아웃 하시겠습니까?"
    );
    if (!confirmLogout.isConfirmed) return;

    // ✅ localStorage에서 사용자 정보 삭제
    localStorage.removeItem("token");
    localStorage.removeItem("userId");

    // ✅ 로그아웃 성공 알럿창
    await utilHelper.alertSuccess("로그아웃 되었습니다.");

    // ✅ 메인 페이지로 이동
    window.location.href = "/";
  });
});
