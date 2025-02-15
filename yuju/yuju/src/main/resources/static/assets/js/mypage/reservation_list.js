$(function () {
  // 사이드바 메뉴 클릭 시 활성화 효과 적용
  $(".sidebar ul li").on("click", function () {
    $(".sidebar ul li").removeClass("active"); // 기존 활성화 클래스 제거
    $(this).addClass("active"); // 클릭한 메뉴에 활성화 클래스 추가
  });

  // "조회" 버튼 클릭 시 예약 내역 조회 (현재는 빈 데이터 표시)
  $(".btn-search").on("click", function () {
    $(".reservation-list").html(
      '<p class="empty-message">예약 내역이 없습니다.</p>' // 예약 내역이 없을 때 기본 메시지 표시
    );
  });
});
