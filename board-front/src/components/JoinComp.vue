<template>
  <div class="border rounded-4">
    <div style="width: 1000px; margin: 50px">
      <div class="mb-0" style="display: flex">
        <input type="text" style="width: 400px" class="form-control" v-model="inputId" placeholder="아이디" aria-describedby="idHelp" />
        <button type="button" id="send_id" class="btn btn-primary" style="margin-left: 10px">확인</button>
      </div>
      <div id="idHelp" class="form-text" :class="{ blue: inputId && idCheck, red: inputId && !idCheck }">알파벳 소문자, 숫자로 2~12글자</div>
      <br />
      <div class="mb-0" style="display: flex">
        <input type="text" style="width: 400px" class="form-control" v-model="inputNickname" placeholder="닉네임" aria-describedby="nicknameHelp" />
        <button type="button" id="send_nickname" class="btn btn-primary" style="margin-left: 10px">확인</button>
      </div>
      <div id="nicknameHelp" class="form-text" :class="{ blue: inputNickname && nicknameCheck, red: inputNickname && !nicknameCheck }">알파벳, 한글, 숫자로 2~12글자</div>
      <br />
      <div class="mb-0">
        <input type="password" style="width: 400px" class="form-control" v-model="inputPassword" placeholder="비밀번호" aria-describedby="passwordHelp" />
        <div id="passwordHelp" class="form-text" :class="{ blue: inputPassword && passwordCheck, red: inputPassword && !passwordCheck }">특수문자, 알파벳, 숫자 1개 이상 포함, 8~15글자</div>
      </div>
      <br />
      <div class="mb-0">
        <input type="password" style="width: 400px" class="form-control" v-model="inputPasswordCheck" placeholder="비밀번호 확인" aria-describedby="passwordCheckHelp" />
        <div id="passwordCheckHelp" class="form-text" :class="{ blue: inputPasswordCheck && passwordCheckCheck, red: inputPasswordCheck && !passwordCheckCheck }">비밀번호 확인</div>
      </div>
      <br />
      <div class="mb-1" style="display: flex">
        <input type="text" style="width: 400px" class="form-control" v-model="inputEmail" placeholder="이메일" />
        <button type="button" id="send_email" class="btn btn-primary" style="margin-left: 10px">전송</button>
      </div>
      <div class="mb-1" style="display: flex">
        <input type="text" style="width: 400px" class="form-control" v-model="inputAuthCode" placeholder="인증코드" />
        <button type="button" id="send_auth_code" class="btn btn-primary" style="margin-left: 10px">확인</button>
      </div>
      <button type="button" id="cancel" class="btn btn-danger" style="width: 110px; margin-right: 10px">취소</button>
      <button type="submit" id="join" class="btn btn-primary" :disabled="!(idCheck && nicknameCheck && passwordCheck && passwordCheckCheck)" style="width: 130px">회원가입</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { id_check } from "@/api/index.js";
import { nickname_check } from "@/api/index.js";
import { email_check } from "@/api/index.js";
import { auth_code_check } from "@/api/index.js";
import { join } from "@/api/index.js";
export default {
  data() {
    return {
      inputId: "",
      inputNickname: "",
      inputPassword: "",
      inputPasswordCheck: "",
      inputEmail: "",
      inputAuthCode: "",

      idCheck: false,
      nicknameCheck: false,
      passwordCheck: false,
      passwordCheckCheck: false,
      emailCheck: false,
    };
  },
  watch: {
    inputId() {
      if (!(2 <= this.inputId.length && this.inputId.length <= 12)) {
        this.idCheck = false;
        return;
      }

      for (let i = 0; i < this.inputId.length; i++) {
        let c = this.inputId[i];
        if (!(("a" <= c && c <= "z") || ("0" <= c && c <= "9"))) {
          this.idCheck = false;
          return;
        }
      }

      this.idCheck = true;
    },
    inputNickname() {
      if (!(2 <= this.inputNickname.length && this.inputNickname.length <= 12)) {
        this.nicknameCheck = false;
        return;
      }

      for (let i = 0; i < this.inputNickname.length; i++) {
        let c = this.inputNickname[i];
        if (!(("a" <= c && c <= "z") || ("A" <= c && c <= "Z") || ("0" <= c && c <= "9") || ("가" <= c && c <= "힣"))) {
          this.nicknameCheck = false;
          return;
        }
      }

      this.nicknameCheck = true;
    },
    inputPassword() {
      this.passwordCheckCheck = this.inputPasswordCheck && this.passwordCheck && this.inputPassword == this.inputPasswordCheck;

      if (!(8 <= this.inputPassword.length && this.inputPassword.length <= 15)) {
        this.passwordCheck = false;
        return;
      }

      let special = false;
      let alphabet = false;
      let numeric = false;
      for (let i = 0; i < this.inputPassword.length; i++) {
        let c = this.inputPassword[i];
        if (!(33 <= c.charCodeAt(0) && c.charCodeAt(0) <= 126)) {
          this.passwordCheck = false;
          console.log("hole");
          return;
        }
        if (("a" <= c && c <= "z") || ("A" <= c && c <= "Z")) {
          alphabet = true;
        } else if ("0" <= c && c <= "9") {
          numeric = true;
        } else {
          special = true;
        }
      }

      this.passwordCheck = special && alphabet && numeric;
      this.passwordCheckCheck = this.inputPasswordCheck && this.passwordCheck && this.inputPassword == this.inputPasswordCheck;
    },
    inputPasswordCheck() {
      this.passwordCheckCheck = this.inputPasswordCheck && this.passwordCheck && this.inputPassword == this.inputPasswordCheck;
    },
  },
  mounted() {
    const send_id_btn = document.querySelector("#send_id");
    const send_nickname_btn = document.querySelector("#send_nickname");
    const send_email_btn = document.querySelector("#send_email");
    const send_auth_code_btn = document.querySelector("#send_auth_code");
    const cancel_btn = document.querySelector("#cancel");
    const join_btn = document.querySelector("#join");

    send_id_btn.onclick = function () {
      id_check(this.inputId);
    };
    send_nickname_btn.onclick = function () {
      nickname_check(this.inputNickname);
    };
    send_email_btn.onclick = function () {
      email_check(this.inputEmail);
    };
    send_auth_code_btn.onclick = function () {
      auth_code_check(this.inputEmail, this.inputAuthCode);
    };
    cancel_btn.onclick = function () {
      router.push("/");
    };
    join_btn.onclick = function () {
      // if (join_check()) console.log(true);
      // else console.log(false);
      join(this.inputId, this.inputNickname, this.inputPassword, this.inputEmail);
    };
  },
};
</script>

<style>
.blue {
  color: blue;
}
.red {
  color: red;
}
</style>