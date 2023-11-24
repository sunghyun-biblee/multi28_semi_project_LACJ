function readURL(input) {
  if (input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("preview").src = e.target.result;
      console.log(e.target.result);
    };
    console.log(input.files);
    console.log(input.files[0]);
    console.log("1");

    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById("preview").src = "";
  }
}
/*
1. FileReader 가 됐을때 사용할 수 있게 reader로 객체를 생성함 
2. input.files >> files는 선택한 파일을 나열한 FileList이다.
    즉 , input[type=file]인 태그가 선택된 파일의 리스트를 볼 수 있는 것이다.
    multiple 속성을 사용하지 않았다면 파일은 하나만 선택된다.

    input.files를 콘솔창에 출력해보면 선택된 해당파일의 정보가 나온다.
    input.files[0] 또한 선택된 파일의 정보가 똑같이 출력된다.
    여기서 우리는 멀티플을 사용하지 않았기 때문에 하나의 파일만 선택할 수 있다.
    즉, files (FileList)에는 하나의 파일정보만 저장되기때문에 files[0]과 같은 결과를 출력한다.
    콘솔창에서의 다른점은 files[0]은 배열의 길이 (선택된 파일의 갯수) 를 출력해준다.


    !! input.files && input.files[0] !! 해당부분은 if분으로서
    input.files 안에 있는 파일의 리스트가 files[0]의 파일정보와 동일한지 조건을 확인한다.
    서로가 다른파일이 매칭되면 img태그안에 src는 아무것도 작성되지않으므로 , 화면에 이미지가 나오지않는다.

3. reader.readAsDataURL == FileReader.readAsDataURL
    readAsDataURL는 특정 파일에서 데이터를 읽어오는 역할을 하고 종료되는경우 readyState 상태가 done되며 loadend이벤트 트리거가 된다.
    이떄 base64 인코딩된 스트링 데이터가 result 속성(attribute)에 담아지게 됩니다.
    즉, 파일을 선택하는 순간 onload함수가 실행되고 해당 파일의 데이터를 읽은 후에 loadend 상태로 변경되며 인코딩된 문자열을 result에 저장됩니다.
    저장된 값을 img태그의 src 소스에 바로 담아줍니다

  간단하게 설명하면 
  input 태그에서 파일을 선택하는 순간 FileReader 가 실행되고 , 선택된 파일의 데이터를 불러와서 해당 파일의 정보를 result에 저장하게됩니다.
  이후 저장된 정보를 img태그의 src소스에다가 작성되고 , 함수가 종료됩니다.
  
  (readAsDataURL(input.files[0])을 통해 onload를 트리거 시킨다. 그러면 onload의 e.target.result 에는 Base64로 인코딩된 문자열이 저장되고, 그것을 img의 src에 넣어주면 이미지 미리보기 구현 끝)

*/
