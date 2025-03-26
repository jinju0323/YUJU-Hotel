document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");

  // ✅ 로그인/로그아웃/마이페이지 메뉴 DOM 가져오기
  const loginMenu = document.getElementById("loginMenu");
  const signupMenu = document.getElementById("signupMenu");
  const logoutMenu = document.getElementById("logoutMenu");
  const mypageMenu = document.getElementById("mypageMenu");

  // ✅ 로그인 상태에 따라 메뉴 표시 변경
  if (token) {
    loginMenu.style.display = "none";
    signupMenu.style.display = "none";
    logoutMenu.style.display = "block";
    mypageMenu.style.display = "block";
  } else {
    loginMenu.style.display = "block";
    signupMenu.style.display = "block";
    logoutMenu.style.display = "none";
    mypageMenu.style.display = "none";
  }

  // ✅ 로그아웃 처리
  document.getElementById("logoutBtn").addEventListener("click", async (e) => {
    e.preventDefault();

    const confirmLogout = await utilHelper.confirmSuccess(
      "로그아웃",
      "정말 로그아웃 하시겠습니까?"
    );
    if (!confirmLogout.isConfirmed) return;

    localStorage.removeItem("token");
    localStorage.removeItem("userId");

    await utilHelper.alertSuccess("로그아웃 되었습니다.");
    window.location.href = "/";
  });

  // ✅ 햄버거 버튼 클릭 시 메뉴 펼치기
  const hamburgerBtn = document.getElementById("hamburgerBtn");
  const navbarNav = document.getElementById("navbarNav");

  hamburgerBtn.addEventListener("click", () => {
    navbarNav.classList.toggle("active");
  });
});
