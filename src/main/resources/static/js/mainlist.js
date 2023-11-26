const heartbox = document.querySelector("#likebtn");
const changeheart = document.getElementById("changbox");

function heartchange() {
  if (changeheart.classList.contains("likesoff")) {
    changeheart.classList.remove("likesoff");
    changeheart.classList.add("likeson");

    var boardlistblikes = $(button).data("id");
    $.ajax({
      type: "post",
      url: "/uplikes",
      contentType: "application/json",
      data: JSON.stringify("boardlistblikes"),
      success: function () {
        console.log("성공");
      },
      error: function () {
        alert("오류가 발생했습니다.");
      },
    });
  } else {
    changeheart.classList.remove("likeson");
    changeheart.classList.add("likesoff");
    $.ajax({
      type: "post",
      url: "/uplikes",
      contentType: "application/json",
      data: JSON.stringify("boardlistblikes"),
      success: function () {
        console.log("성공");
      },
      error: function () {
        alert("오류가 발생했습니다.");
      },
    });
  }
}

heartbox.addEventListener("click", heartchange);
