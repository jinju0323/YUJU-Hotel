document.addEventListener("DOMContentLoaded", () => {
  // ✅ 사이드바 메뉴 클릭 시 "active" 클래스 적용
  // - 클릭한 메뉴 항목에 "active" 클래스를 추가하고, 나머지 항목에서는 제거
  document.querySelectorAll(".sidebar ul li").forEach((item) => {
    item.addEventListener("click", () => {
      // 모든 메뉴에서 "active" 제거 (초기화)
      document
        .querySelectorAll(".sidebar ul li")
        .forEach((li) => li.classList.remove("active"));

      // 현재 클릭한 항목에 "active" 추가 (선택된 상태 유지)
      item.classList.add("active");
    });
  });

  // ✅ "조회" 버튼 클릭 시 예약 내역 조회 (현재는 빈 데이터 표시)
  // - 실제 데이터가 없을 경우 "예약 내역이 없습니다." 메시지 출력
  document.querySelector(".btnSearch").addEventListener("click", () => {
    document.querySelector(".reservationList").innerHTML =
      '<p class="emptyMessage">예약 내역이 없습니다.</p>';
  });
});
