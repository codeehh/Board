import axios from "axios";
// import store from "@/store";
// import router from "@/router";

const API_BASE_URL = process.env.VUE_APP_API_BASE_URL;

async function id_check(id) {
    await axios
        .post(API_BASE_URL + `/api/id-check`, {
            id: id,
        })
        .then((res) => {
            if (res.data["canUse"])
                alert("사용할 수 있는 아이디입니다")
            else
                alert("사용할 수 없는 아이디입니다")
        })
        .catch((err) => {
            console.log(err)
        });
}

async function nickname_check(nickname) {
    await axios
        .post(API_BASE_URL + `/api/nickname-check`, {
            nickname: nickname,
        })
        .then((res) => {
            if (res.data["canUse"])
                alert("사용할 수 있는 닉네임입니다")
            else
                alert("사용할 수 없는 닉네임입니다")
        })
        .catch((err) => {
            console.log(err)
        });
}

async function email_check(email) {
    await axios
        .post(API_BASE_URL + `/api/email-check`, {
            email: email,
        })
        .then(() => {
            alert("메일로 인증코드를 보냈습니다")
        })
        .catch(() => {
            alert("잘못된 메일 주소입니다")
        });
}

async function auth_code_check(email, auth_code) {
    await axios
        .post(API_BASE_URL + `/api/auth-code-check`, {
            email: email,
            auth_code: auth_code,
        })
        .then((res) => {
            if (res.data["isMatch"]) {
                alert("올바른 인증코드입니다")
                return true;
            }
            else
                alert("잘못된 인증코드입니다")
        })
        .catch((err) => {
            console.log(err)
        });
}


async function join(id, nickname, password, email) {
    await axios
        .post(API_BASE_URL + `/api/users`, {
            id: id,
            nickname: nickname,
            password: password,
            email: email
        })
        .then((res) => {
            console.log(res);
        })
        .catch((err) => {
            console.log(err)
        });
}

async function login(id, password) {
    await axios
        .post(API_BASE_URL + `/api/login`, {
            data: {
                id: id,
                password: password
            }
        })
        .then((res) => {
            console.log(res);

        })
        .catch((err) => {
            console.log(err)
        });
}

export {
    id_check,
    nickname_check,
    email_check,
    auth_code_check,
    join,
    login
};
