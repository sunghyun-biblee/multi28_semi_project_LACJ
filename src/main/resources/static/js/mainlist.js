const heartbox = document.querySelectorAll(".likebtn2");

heartbox.forEach((heartbox) => {
  heartbox.addEventListener("click", function (event) {
    heartchange(event, heartbox);
  });
});

function heartchange(event, clickedButton) {
  const changeheart = clickedButton.querySelector(".changbox");
  const bno = event.currentTarget.getAttribute("data-id");
  const likescountElement = clickedButton.querySelector(".likescount"); // 해당 버튼 내의 likescount 엘리먼트 찾기

  if (!likescountElement) {
    console.error("Error: Likes count element not found!");
    return; // 요소를 찾지 못한 경우 함수를 여기서 종료
  }

  if (changeheart.classList.contains("off")) {
    changeheart.classList.remove("off");
    changeheart.classList.add("on");

    $.ajax({
      type: "POST",
      url: "/uplikes",
      contentType: "application/json",
      data: JSON.stringify({ bno: bno }),
      success: function (msg) {
        console.log("성공");
        console.log(msg.count);
        likescountElement.textContent = msg.count; // 해당 버튼 내의 likescount 엘리먼트의 텍스트 변경
        $(likescountElement).text(msg.count);
      },
      error: function () {
        alert("오류가 발생했습니다.");
      },
    });
  } else {
    changeheart.classList.remove("on");
    changeheart.classList.add("off");

    $.ajax({
      type: "POST",
      url: "/downlikes",
      contentType: "application/json",
      data: JSON.stringify({ bno: bno }),
      success: function (msg) {
        console.log("성공");
        likescountElement.textContent = msg.count; // 해당 버튼 내의 likescount 엘리먼트의 텍스트 변경
      },
      error: function () {
        alert("오류가 발생했습니다.");
      },
    });
  }
}
