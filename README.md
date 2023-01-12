# Board

### 설계

[요구사항 정의서, api 문서](https://docs.google.com/spreadsheets/d/1d6erWQsG4Ac0ITCvkUHWCwQvWtLNUIMT6a4kYAhIaiw/edit#gid=307137686)

[와이어 프레임](https://www.figma.com/file/awolcWCRTHgQxB2sKZCuuF/Untitled?node-id=0%3A1&t=fIKE0lHcwOhFOPB1-0)

[DB 설계](https://www.erdcloud.com/d/e2YEzNzTj8chzdoFa)



### 이슈

- @Autowired 필드에 NullPointerException 뜸

  - 원인 : @Autowired 필드에 static을 붙이면 메모리 static 영역에 저장되는 것과 스프링 bean은 heap 영역에서 관리하는 것과 관련이 있는 것 같다

  - 해결 : @Autowired 필드에 static을 붙이지 말자

- 헤더에 쿠키를 보내는데 브라우저에 쿠키가 저장 안됨

  - 원인 : CORS 정책

  - 해결 : 백엔드, 프론트엔드 둘 다 Credentials : true 설정해주기
- 스프링에서 보낸 쿠키가 브라우저에 저장됐는데 vue-cookies에서 접근할 수 없음
  - 원인 : 쿠키의 HttpOnly(true) 설정 때문, 자바스크립트로 쿠키에 접근할 수 없게 해 보안을 강화하는 것
  - 해결 : 설정을 끄지 말고 다른 방법을 찾자

- axios에서 return해준 값이 undefined임
  - 원인 : axios안에서만 return해줬다
  - 해결 : return axios
