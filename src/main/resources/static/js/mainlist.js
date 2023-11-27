const heartbox = document.querySelectorAll(".likebtn2");
const changeheart = document.getElementById("changbox");


function heartchange(event) {
  const bno = event.currentTarget.getAttribute("data-id");
  if (changeheart.classList.contains("likesoff")) {
    
    changeheart.classList.remove("likesoff");
    changeheart.classList.add("likeson");
	    
    $.ajax({
      type: "POST",
      url: "/uplikes",
      contentType: "application/json",
      data: JSON.stringify({"bno": bno}),
      success: function (msg) {
        console.log("성현");
        console.log(msg.count);
        $('.likescount').text(msg.count);
      },
      error: function () {
        alert("오류가 발생했습니다.");
      }
    }); 
  } else {
    changeheart.classList.remove("likeson");
    changeheart.classList.add("likesoff");
    $.ajax({
      type: "POST",
      url: "/downlikes",
      contentType: "application/json",
      data: JSON.stringify({"bno": bno}),
      success: function (msg) {
        console.log("성공");
        $('.likescount').text(msg.count);
      },
      error: function () {
        alert("오류가 발생했습니다.");
      }
    });
  }
}

heartbox.forEach((button) => {
  button.addEventListener("click", heartchange);
});
