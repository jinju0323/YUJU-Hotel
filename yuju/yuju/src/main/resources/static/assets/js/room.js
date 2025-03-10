// ✅ Swiper 초기화 (이미지 슬라이더)
document.addEventListener("DOMContentLoaded", function () {
    var swiperContainer = document.querySelector(".swiper-container");
    if (swiperContainer) {
        var swiper = new Swiper(".swiper-container", {
            loop: true, // 무한 루프
            slidesPerView: 1, // 한 번에 한 장 표시
            spaceBetween: 10, // 슬라이드 간격
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
            autoplay: {
                delay: 3000, // 3초마다 자동 슬라이드
                disableOnInteraction: false, // 사용자가 클릭해도 자동 슬라이드 유지
            },
        });
    }
});
