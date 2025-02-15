$(function () {
  // 사이드바 활성화 효과
  $(".sidebar ul li").on("click", (event) => {
    $(".sidebar ul li").removeClass("active");
    $(event.currentTarget).addClass("active");
  });

  // 비밀번호 변경 유효성 검사
  $("#password-change-form").on("submit", (event) => {
    event.preventDefault(); // 기본 제출 방지

    const currentPassword = $("#current-password").val();
    const newPassword = $("#new-password").val();
    const confirmPassword = $("#confirm-password").val();

    // 비밀번호 유효성 검사 (영문/숫자/특수문자 조합)
    const passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{9,}$/;

    if (!passwordRegex.test(newPassword)) {
      alert(
        "비밀번호는 영문/숫자/특수문자를 포함해야 하며, 9자리 이상이어야 합니다."
      );
      return;
    }

    if (newPassword !== confirmPassword) {
      alert("새로운 비밀번호가 일치하지 않습니다.");
      return;
    }

    alert("비밀번호가 성공적으로 변경되었습니다.");
  });
});
