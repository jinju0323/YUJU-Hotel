document.addEventListener("DOMContentLoaded", function () {
    // ✅ 초기 페이지: Standard A형 자동 로드
    loadRoomDetails("Standard", "A");

    // ✅ 객실 유형 및 하위 카테고리 클릭 이벤트 추가
    document.querySelectorAll(".room-category-option").forEach(button => {
        button.addEventListener("click", function () {
            // 기존 선택된 버튼 스타일 초기화
            document.querySelectorAll(".room-category-option").forEach(btn => btn.classList.remove("active"));

            // 선택된 버튼 스타일 적용
            this.classList.add("active");

            const roomType = this.dataset.roomType;
            const category = this.dataset.category;

            // ✅ 객실 정보 및 이미지 로드
            loadRoomDetails(roomType, category);
        });
    });
});

// ✅ 1. 특정 객실 정보 불러오기
function loadRoomDetails(roomType, category) {
    fetch(`/api/rooms/type/${roomType}`)
        .then(response => response.json())
        .then(data => {
            const roomData = data.find(room => room.roomCategory === category);
            if (!roomData) return;

            document.getElementById("room-title").textContent = `${roomType} ${category}형`;
            document.getElementById("room-capacity").textContent = roomData.capacity;
            document.getElementById("room-description").textContent = roomData.description;

            // ✅ 객실 이미지 불러오기
            loadRoomImages(roomData.roomId);
        })
        .catch(error => console.error("객실 정보 불러오기 실패:", error));
}

// ✅ 2. 특정 객실의 이미지 가져오기
function loadRoomImages(roomId) {
    fetch(`/api/rooms/${roomId}/images`)
        .then(response => response.json())
        .then(images => {
            const imgContainer = document.getElementById("room-imgs");
            imgContainer.innerHTML = "";

            images.forEach(img => {
                const imgDiv = document.createElement("div");
                imgDiv.classList.add("swiper-slide");
                imgDiv.innerHTML = `<img src="${img.imgUrl}" alt="객실 이미지">`;
                imgContainer.appendChild(imgDiv);
            });

            // ✅ Swiper 슬라이더 초기화
            initializeSwiper();
        })
        .catch(error => console.error("객실 이미지 불러오기 실패:", error));
}

// ✅ 3. Swiper 이미지 슬라이더 초기화
function initializeSwiper() {
    new Swiper(".swiper-container", {
        loop: true,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
    });
}
