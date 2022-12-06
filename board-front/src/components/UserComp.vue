<template>
  <div class="border rounded-4" style="margin-right: 10px">
    <div style="width: 280px; margin: 50px">
      <div class="mb-0">
        <input type="text" class="form-control" id="inputId" placeholder="아이디" aria-describedby="idHelp" />
        <div id="idHelp" class="form-text">12자 이내</div>
      </div>
      <div class="mb-0">
        <input type="password" class="form-control" id="inputPassword" placeholder="비밀번호" aria-describedby="passwordHelp" />
        <div id="passwordHelp" class="form-text">12자 이내</div>
      </div>
      <div class="mb-0 form-check">
        <input type="checkbox" class="form-check-input" id="autoLogin" />
        <label class="form-check-label" for="autoLogin">자동 로그인</label>
      </div>
      <button type="button" id="login" class="btn btn-primary" style="width: 110px; margin-right: 10px">로그인</button>
      <button type="button" id="join-form" class="btn btn-success" style="width: 130px">회원가입</button>
      <br />
      <button type="button" id="find-id" class="btn btn-info" style="margin-right: 10px">아이디 찾기</button>
      <button type="button" id="find-password" class="btn btn-warning">비밀번호 찾기</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
export default {
  mounted() {
    const login_btn = document.querySelector("#login");
    const join_form_btn = document.querySelector("#join-form");
    const find_id_btn = document.querySelector("#find-id");
    const find_password_btn = document.querySelector("#find-password");

    login_btn.onclick = function () {
      console.log("login");
    };
    join_form_btn.onclick = function () {
      router.push("join");
    };
    find_id_btn.onclick = function () {
      console.log("id");
    };
    find_password_btn.onclick = function () {
      console.log("pw");
    };
  },
};
</script>

<style>
</style>