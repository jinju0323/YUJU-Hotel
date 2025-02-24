// ✅ [1] 아이디(이메일) 중복 확인
document
  .querySelector("#idUniqueCheck")
  .addEventListener("click", async (e) => {
    // 폼의 기본 제출 동작을 막음
    e.preventDefault();

    try {
      // 아이디(이메일) 입력값 유효성 검사
      regexHelper.value("#userId", "아이디(이메일)를 입력하세요");
      regexHelper.email("#userId", "아이디(이메일) 형식이 올바르지 않습니다.");
    } catch (e) {
      // 유효성 검사 실패 시 에러 메시지 표시
      await utilHelper.alertDanger(e.message);
      // 에러가 발생한 요소에 포커스를 맞춤
      e.element.focus();
      return;
    }

    // 아이디(이메일) 값을 가져옴
    const userId = document.querySelector("#userId").value;

    try {
      // 서버에 아이디(이메일) 중복 확인 요청
      const data = await axiosHelper.get(`/api/account/id_check`, {
        userId: userId,
      });

      // 중복 확인 결과에 따라 처리
      if (data) {
        await utilHelper.alertSuccess("사용 가능한 아이디(이메일) 입니다.");
        document.querySelector("#emailCheck").value = "Y";
      } else {
        await utilHelper.alertDanger("이미 사용 중인 아이디(이메일) 입니다.");
        document.querySelector("#emailCheck").value = "N";
      }
    } catch (error) {
      await utilHelper.alertDanger("서버와의 통신 중 오류가 발생했습니다.");
    }
  });

// ✅ [2] 아이디(이메일) 입력값 변경 시 중복 확인 상태를 "N"으로 설정
document.querySelector("#userId").addEventListener("change", () => {
  document.querySelector("#emailCheck").value = "N";
});

// ✅ [3] 비밀번호 표시/숨김 기능
document.querySelectorAll(".togglePassword").forEach((btn) => {
  btn.addEventListener("click", () => {
    const passwordInput = btn.previousElementSibling; // 바로 앞의 input 요소 선택
    const isPassword = passwordInput.type === "password";

    // 비밀번호 보이기/숨기기 전환
    passwordInput.type = isPassword ? "text" : "password";

    // 아이콘 변경 (눈 → 눈 감은 아이콘)
    btn.innerHTML = isPassword
      ? '<i class="fas fa-eye-slash"></i>'
      : '<i class="fas fa-eye"></i>';
  });
});

// ✅ [4] 회원가입 버튼 클릭 시 유효성 검사 및 데이터 전송
document.getElementById("joinForm").addEventListener("submit", async (e) => {
  // 폼의 기본 제출 동작을 막음
  e.preventDefault();

  try {
    // 아이디(이메일) 입력값 유효성 검사
    regexHelper.value("#userId", "아이디(이메일)을 입력하세요.");
    regexHelper.email("#userId", "아이디(이메일) 형식이 올바르지 않습니다.");

    // 비밀번호 입력값 유효성 검사
    regexHelper.value("#userPw", "비밀번호를 입력하세요.");
    regexHelper.password(
      "#userPw",
      "비밀번호는 영문, 숫자, 특수문자를 포함한 8자 이상 입력하세요."
    );
    regexHelper.minLength("#userPw", 8, "비밀번호는 8자 이상 입력하세요.");
    regexHelper.maxLength("#userPw", 20, "비밀번호는 20자 이하로 입력하세요.");
    regexHelper.compareTo(
      "#userPw",
      "#confirmPassword",
      "비밀번호가 일치하지 않습니다."
    );

    // 이름 입력값 유효성 검사
    regexHelper.value("#userName", "이름을 입력하세요.");
    regexHelper.kor("#userName", "이름은 한글로만 입력하세요.");
    regexHelper.minLength("#userName", 2, "이름은 최소 2자 이상 입력하세요.");
  } catch (e) {
    await utilHelper.alertDanger(e.message);
    e.element.focus();
    return;
  }

  // 이메일 중복 확인 여부 체크
  if (document.querySelector("#emailCheck").value === "N") {
    await utilHelper.alertDanger("아이디(이메일) 중복 여부를 확인하세요.");
    return;
  }

  // ✅ 회원가입 폼 데이터를 FormData로 가져옴
  const formData = new FormData(e.currentTarget);

  try {
    // 서버로 회원가입 요청
    const data = await axiosHelper.postMultipart(
      // "[[@{/api/account/join}]]",
      "/api/account/join",
      formData
    );

    // 회원가입 성공 시 로그인 페이지로 이동
    if (data) {
      await utilHelper.alertSuccess(
        "회원가입 성공! 로그인 페이지로 이동합니다."
      );
      // window.location = "[[@{/login}]]";
      window.location = "/login";
    } else {
      await utilHelper.alertDanger("회원가입 실패. 다시 시도해주세요.");
    }
  } catch (error) {
    await utilHelper.alertDanger("서버와의 통신 중 오류가 발생했습니다.");
  }
});
