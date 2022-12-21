import axios from "axios";
// import store from "@/store";
// import router from "@/router";

const API_BASE_URL = process.env.VUE_APP_API_BASE_URL;

async function id_check(id) {
    await axios
        .post(API_BASE_URL + `/api/id-check`, {
            data: {
                id: id,
            }
        })
        .then((res) => {
            console.log(res);

        })
        .catch((err) => {
            console.log(err)
        });
}

async function nickname_check(nickname) {
    await axios
        .post(API_BASE_URL + `/api/nickname-check`, {
            data: {
                nickname: nickname,
            }
        })
        .then((res) => {
            console.log(res);

        })
        .catch((err) => {
            console.log(err)
        });
}

async function email_check(email) {
    await axios
        .post(API_BASE_URL + `/api/email-check`, {
            data: {
                email: email,
            }
        })
        .then((res) => {
            console.log(res);

        })
        .catch((err) => {
            console.log(err)
        });
}

async function auth_code_check(email, auth_code) {
    await axios
        .post(API_BASE_URL + `/api/auth-code-check`, {
            data: {
                email: email,
                auth_code: auth_code,
            }
        })
        .then((res) => {
            console.log(res);

        })
        .catch((err) => {
            console.log(err)
        });
}


async function join(id, nickname, password, email) {
    await axios
        .post(API_BASE_URL + `/api/users`, {
            data: {
                id: id,
                nickname: nickname,
                password: password,
                email: email
            }
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
