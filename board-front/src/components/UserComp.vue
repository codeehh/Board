<template>
  <div>
    <div v-show="nickname === null">
      <div class="border rounded-4" style="margin-right: 10px">
        <div style="width: 280px; margin: 50px">
          <div class="mb-0">
            <input type="text" class="form-control" v-model="inputId" placeholder="아이디" aria-describedby="idHelp" />
            <br>
          </div>
          <div class="mb-0">
            <input type="password" class="form-control" v-model="inputPassword" placeholder="비밀번호" aria-describedby="passwordHelp" />
          </div>
          <div class="mb-0 form-check">
            <input type="checkbox" class="form-check-input" id="auto-login" />
            <label class="form-check-label" for="autoLogin">자동 로그인</label>
          </div>
          <br>
          <button type="button" id="login" class="btn btn-primary" style="width: 110px; margin-right: 10px">로그인</button>
          <button type="button" id="join-form" class="btn btn-primary" style="width: 130px">회원가입</button>
          <br />
          <br />
          <button type="button" id="find-id" class="btn btn-primary" style="margin-right: 10px">아이디 찾기</button>
          <button type="button" id="find-password" class="btn btn-primary">비밀번호 찾기</button>
          <br />
          <br />
          <button type="button" id="test" class="btn btn-primary" style="margin-right: 10px">테스트</button>
        </div>
      </div>
    </div>
    <div v-show="!(nickname === null)">
      <div class="border rounded-4" style="margin-right: 10px">
        <div style="width: 280px; margin: 50px">
          {{ nickname }}님 환영합니다.
          <br>
          <br>
          <button type="button" id="logout" class="btn btn-danger" style="width: 110px; margin-right: 10px">로그아웃</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { login } from "@/api/index.js";
import { logout } from "@/api/index.js";
import { email_check } from "@/api/index.js";
import { find_id } from "@/api/index.js";
import { find_password } from "@/api/index.js";
export default {
  data() {
    return {
      inputId: "",
      inputPassword: "",
      autoLogin: false,
      nickname: null,
    };
  },
  mounted() {
    const login_btn = document.querySelector("#login");
    const logout_btn = document.querySelector("#logout");
    const join_form_btn = document.querySelector("#join-form");
    const auto_login_btn = document.querySelector("#auto-login");
    const find_id_btn = document.querySelector("#find-id");
    const find_password_btn = document.querySelector("#find-password");

    let $vm = this;

    $vm.nickname = this.$cookies.get("nickname");

    login_btn.onclick = function () {
      login($vm.inputId, $vm.inputPassword, $vm.autoLogin);
    };
    logout_btn.onclick = function () {
      logout();
    };
    join_form_btn.onclick = function () {
      router.push("join");
    };
    auto_login_btn.onclick = function () {
      $vm.autoLogin = !$vm.autoLogin;
    };
    find_id_btn.onclick = async function () {
      const email = await prompt("가입 시 입력한 메일 주소")
      await email_check(email);
      const auth_code = await prompt("인증코드 입력")
      await find_id(email, auth_code);
    };
    find_password_btn.onclick = async function () {
      const id = await prompt("아이디 입력")
      const email = await prompt("가입 시 입력한 메일 주소")
      await email_check(email);
      const auth_code = await prompt("인증코드 입력")
      await find_password(id, email, auth_code);
    };

    const test_btn = document.querySelector("#test");
    test_btn.onclick = function () {
      router.push("/");
    };
  },
};
</script>

<style>

</style>