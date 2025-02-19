$(document).ready(() => {
  const deleteForm = $("#delete-account-form");
  const deleteButton = deleteForm.find(".btn-delete");

  deleteButton.on("click", (event) => {
    event.preventDefault(); // 기본 폼 제출 동작을 막음

    Swal.fire({
      title: "정말 탈퇴하시겠습니까?",
      text: "이 작업은 되돌릴 수 없습니다.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#dc3545",
      cancelButtonColor: "#6c757d",
      confirmButtonText: "네, 탈퇴합니다",
      cancelButtonText: "취소",
    }).then((result) => {
      if (result.isConfirmed) {
        // 메인 페이지로 리디렉션
        window.location.href = "/";
      }
    });
  });
});
