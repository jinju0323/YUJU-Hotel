$(() => {
  // ✅ [1] 아이디(이메일) 중복 확인
  $("#idUniqueCheck").on("click", async (e) => {
    e.preventDefault(); // 기본 이벤트(폼 제출) 막기

    const userIdInput = $("#user_id");
    const emailCheckInput = $("#emailCheck");

    // 1️⃣ 아이디 입력값이 있는지 확인
    try {
      regexHelper.value("#user_id", "아이디(이메일)을 입력하세요.");
      regexHelper.email("#user_id", "올바른 이메일 형식을 입력하세요.");
    } catch (e) {
      Swal.fire("경고", e.message, "warning");
      e.element?.focus(); // 오류 발생한 입력창으로 이동
      return;
    }

    // 2️⃣ 서버에 중복 확인 요청 (AJAX)
    try {
      const response = await $.ajax({
        url: `/api/account/id_check`, // 중복 확인 API 엔드포인트
        method: "GET",
        data: { user_id: userIdInput.val() }, // 서버에 전달할 데이터
        dataType: "json",
      });

      // 3️⃣ 중복 여부에 따라 사용자에게 알림
      if (response) {
        Swal.fire("성공", "사용 가능한 이메일입니다.", "success");
        emailCheckInput.val("Y"); // 중복 확인 완료 상태로 변경
      } else {
        Swal.fire("실패", "이미 사용 중인 이메일입니다.", "error");
        emailCheckInput.val("N"); // 중복 확인 실패 상태로 변경
      }
    } catch (error) {
      Swal.fire("오류", "서버와의 통신 중 문제가 발생했습니다.", "error");
    }
  });

  // ✅ [2] 비밀번호 표시/숨김 기능
  $(".togglePassword").on("click", function () {
    const passwordInput = $(this).prev("input"); // 바로 앞의 input 요소 선택
    const isPassword = passwordInput.attr("type") === "password"; // 현재 상태 확인

    // 비밀번호 보이기/숨기기 전환
    passwordInput.attr("type", isPassword ? "text" : "password");

    // 아이콘 변경 (눈 → 눈 감은 아이콘)
    $(this).html(
      isPassword
        ? '<i class="fas fa-eye-slash"></i>'
        : '<i class="fas fa-eye"></i>'
    );
  });

  // ✅ [3] 회원가입 버튼 클릭 시 유효성 검사
  $(".btnPrimary").on("click", async (e) => {
    e.preventDefault(); // 기본 이벤트(폼 제출) 막기

    // 1️⃣ 입력값 검증
    try {
      // 아이디(이메일) 입력값 유효성 검사
      regexHelper.value("#user_id", "아이디(이메일)을 입력하세요.");
      regexHelper.email("#user_id", "아이디(이메일) 형식이 올바르지 않습니다.");

      // 비밀번호 입력값 유효성 검사
      regexHelper.value("#password", "비밀번호를 입력하세요.");
      regexHelper.password(
        "#password",
        "비밀번호는 영문, 숫자, 특수문자를 포함한 8자 이상 입력하세요."
      );
      regexHelper.minLength("#password", 8, "비밀번호는 8자 이상 입력하세요.");
      regexHelper.maxLength(
        "#password",
        20,
        "비밀번호는 20자 이하로 입력하세요."
      );
      regexHelper.compareTo(
        "#password",
        "#confirmPassword",
        "비밀번호가 일치하지 않습니다."
      );

      // 이름 입력값 유효성 검사
      regexHelper.value("#name", "이름을 입력하세요.");
      regexHelper.kor("#name", "이름은 한글로만 입력하세요.");
      regexHelper.minLength("#name", 2, "이름은 최소 2자 이상 입력하세요.");
    } catch (e) {
      Swal.fire("경고", e.message, "warning");
      e.element?.focus();
      return;
    }

    // 2️⃣ 이메일 중복 확인 여부 체크
    if ($("#emailCheck").val() === "N") {
      Swal.fire("경고", "아이디(이메일) 중복 여부를 확인하세요.", "warning");
      return;
    }

    // 3️⃣ 회원가입 데이터 전송
    const formData = new FormData($("form")[0]); // 폼 데이터 가져오기

    try {
      const response = await $.ajax({
        url: "/api/account/join", // 회원가입 API 엔드포인트
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
      });

      // 4️⃣ 회원가입 성공 시 로그인 페이지로 이동
      if (response) {
        Swal.fire(
          "회원가입 성공",
          "로그인 페이지로 이동합니다.",
          "success"
        ).then(() => {
          window.location.href = "/login";
        });
      } else {
        Swal.fire("회원가입 실패", "다시 시도해주세요.", "error");
      }
    } catch (error) {
      Swal.fire("오류", "서버와의 통신 중 문제가 발생했습니다.", "error");
    }
  });

  // ✅ [4] 아이디 입력 시 중복확인 상태 초기화
  $("#user_id").on("input", () => {
    $("#emailCheck").val("N"); // 중복 확인 초기화 (다시 확인 필요)
  });
});
