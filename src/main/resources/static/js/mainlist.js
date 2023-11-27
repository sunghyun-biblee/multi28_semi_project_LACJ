const heartbox = document.querySelectorAll(".likebtn2");

const commentsubmit = document.querySelectorAll(".commentsubmit");
heartbox.forEach((heartbox) => {
  heartbox.addEventListener("click", function (event) {
    heartchange(event, heartbox);
  });
});
commentsubmit.forEach((commentsubmit) => {
  commentsubmit.addEventListener("click", function (event) {
    addcomment(event);
  });
});

function heartchange(event, clickedButton) {
  const changeheart = clickedButton.querySelector(".changbox");
  const likescountElement = clickedButton.querySelector(".likescount"); // 해당 버튼 내의 likescount 엘리먼트 찾기
  const bno = event.currentTarget.getAttribute("data-id"); //bno = ${boardlist.bno}

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
        console.log("성현");
        console.log(msg.count);
        likescountElement.textContent = msg.count; // 해당 버튼 내의 likescount 엘리먼트의 텍스트 변경
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
        console.log("현록");
        console.log(msg.count);
        likescountElement.textContent = msg.count; // 해당 버튼 내의 likescount 엘리먼트의 텍스트 변경
      },
      error: function () {
        alert("오류가 발생했습니다.");
      },
    });
  }
}
function addcomment(event) {
  const commentsubmitButton = event.currentTarget;
  const comentbox = commentsubmitButton.closest("#coment_box");
  const commentboxTextarea = comentbox.querySelector(".commentbox");
  const comment = commentboxTextarea.value;

  console.log(comment);
  const bno = event.currentTarget.getAttribute("data-bno");

  let commentdata = { comment: comment, bno: bno };
  $.ajax({
    type: "post",
    url: "/addcomment",
    data: JSON.stringify(commentdata),
    contentType: "application/json",
    dataType: "json",
    success: function (map) {
      if (map.res > 0) {
        alert("성공현");
      } else {
        alert("success:실패록");
      }
    },
    error: function () {
      alert("error:실패록");
    },
  });
}

// function test() {
//   const test = document.querySelector(".commentbox");
//   const value = test.value;
//   console.log(value);
// }
