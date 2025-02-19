$(document).ready(() => {
  // ✅ 유효성 검사 함수
  const validateForm = () => {
    let isValid = true;
    let email = $("#email").val();
    let password = $("#password").val();
    let confirmPassword = $("#confirm-password").val();
    let name = $("#name").val();

    // ✅ 이메일 유효성 검사
    if (!email) {
      Swal.fire("오류", "이메일을 입력해주세요.", "error");
      isValid = false;
    }

    // ✅ 비밀번호 유효성 검사 (최소 8자, 영문, 숫자, 특수문자 포함)
    const passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if (!passwordRegex.test(password)) {
      Swal.fire(
        "오류",
        "비밀번호는 최소 8자 이상이며, 영문, 숫자, 특수문자를 포함해야 합니다.",
        "error"
      );
      isValid = false;
    }

    // ✅ 비밀번호 일치 여부 확인
    if (password !== confirmPassword) {
      Swal.fire("오류", "비밀번호가 일치하지 않습니다.", "error");
      isValid = false;
    }

    // ✅ 이름 유효성 검사 (한글 또는 영문 2~20자)
    const nameRegex = /^[가-힣a-zA-Z]{2,20}$/;
    if (!nameRegex.test(name)) {
      Swal.fire(
        "오류",
        "이름은 한글 또는 영문으로 2~20자 사이로 입력해야 합니다.",
        "error"
      );
      isValid = false;
    }

    return isValid;
  };

  // ✅ 회원가입 버튼 클릭 시 유효성 검사 실행
  $(".btn-primary").click((e) => {
    e.preventDefault(); // 기본 폼 제출 방지

    if (!validateForm()) {
      return; // 유효성 검사 실패 시 중단
    }

    // ✅ 회원가입 성공 시 알림 표시
    Swal.fire({
      title: "회원가입 완료!",
      text: "회원가입이 성공적으로 완료되었습니다.",
      icon: "success",
      confirmButtonText: "확인",
    }).then(() => {
      $("form").submit(); // ✅ 폼 제출 실행
    });
  });

  // ✅ 이메일 중복 확인 AJAX 요청
  $(".btn-secondary").click(() => {
    let email = $("#email").val();

    // ✅ 이메일 입력 여부 확인
    if (!email) {
      Swal.fire("오류", "이메일을 입력해주세요.", "error");
      return;
    }

    // ✅ AJAX 요청 (이메일 중복 확인)
    $.ajax({
      type: "POST",
      url: "/account/check-email", // 이메일 중복 확인 API
      data: JSON.stringify({ email }),
      contentType: "application/json",
      dataType: "json",
      success: (response) => {
        if (response.exists) {
          Swal.fire("오류", "이미 사용 중인 이메일입니다.", "error");
        } else {
          Swal.fire("성공", "사용 가능한 이메일입니다.", "success");
        }
      },
      error: (xhr, status, error) => {
        console.error("AJAX 요청 실패:", error);
        Swal.fire("오류", "서버와의 통신 중 문제가 발생했습니다.", "error");
      },
    });
  });
});

$(document).ready(() => {
  $(".toggle-password").click(function () {
    let input = $(this).siblings("input");
    let icon = $(this).find("i");

    // ✅ 비밀번호 타입 토글 (text <-> password)
    if (input.attr("type") === "password") {
      input.attr("type", "text");
      icon.removeClass("fa-eye").addClass("fa-eye-slash"); // 눈 감긴 아이콘
    } else {
      input.attr("type", "password");
      icon.removeClass("fa-eye-slash").addClass("fa-eye"); // 눈 뜬 아이콘
    }
  });
});
