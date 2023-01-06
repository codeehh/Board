import router from "@/router";
import axios from "axios";
import $cookies from "vue-cookies";
// import store from "@/store";
// import router from "@/router";
// axios.defaults.withCredentials = true;

const API_BASE_URL = process.env.VUE_APP_API_BASE_URL;

async function id_check(id) {
    await axios
        .post(API_BASE_URL + '/api/id-check', {
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
        .post(API_BASE_URL + '/api/nickname-check', {
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
        .post(API_BASE_URL + '/api/email-check', {
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
        .post(API_BASE_URL + '/api/auth-code-check', {
            email: email,
            auth_code: auth_code,
        })
        .then((res) => {
            if (res.data["isMatch"]) {
                alert("올바른 인증코드입니다")
                return true;
            }
            else {
                alert("잘못된 인증코드입니다")
                return false;
            }
        })
        .catch((err) => {
            console.log(err)
        });
}


async function join(id, nickname, password, email) {
    await axios
        .post(API_BASE_URL + '/api/users', {
            id: id,
            nickname: nickname,
            password: password,
            email: email
        })
        .then((res) => {
            console.log(res);
            router.push("/");
            alert("회원가입이 완료되었습니다")
        })
        .catch((err) => {
            console.log(err)
        });
}

async function login(id, password, auto_login) {
    await axios
        .post(API_BASE_URL + '/api/login', {
            id: id,
            password: password,
            auto_login: auto_login,
        }, {
            withCredentials: true
        })
        .then((res) => {
            if (auto_login) {
                $cookies.set("nickname", res.data["nickname"], 2592000);
            } else {
                $cookies.set("nickname", res.data["nickname"], 1800);
            }
            window.location.reload();
        })
        .catch((err) => {
            console.log(err)
        });
}

async function logout() {
    await axios
        .get(API_BASE_URL + '/api/logout', {
            withCredentials: true
        })
        .then((res) => {
            console.log(res);
            $cookies.remove("nickname");
            window.location.href = "http://localhost:3000/";
        })
        .catch((err) => {
            console.log(err)
        });
}

async function find_id(email, auth_code) {
    await axios
        .post(API_BASE_URL + '/api/find-id', {
            email: email,
            auth_code: auth_code,
        },)
        .then((res) => {
            console.log(res);
            let ids = res.data["id"];

            let str = "아이디는\n";
            for (let i = 0; i < ids.length; i++) {
                str += (ids[i] + "\n");
            }
            str += "입니다"
            alert(str);
        })
        .catch((err) => {
            console.log(err)
        });
}

async function find_password(id, email, auth_code) {
    await axios
        .post(API_BASE_URL + '/api/find-password', {
            id: id,
            email: email,
            auth_code: auth_code,
        },)
        .then((res) => {
            console.log(res);
            if (res.data["isMatch"]) {
                let password = prompt("변경할 비밀번호(특수문자, 알파벳, 숫자 1개 이상 포함, 8~15자리)");
                reset_password(id, password);
            }
        })
        .catch((err) => {
            console.log(err)
        });
}

async function reset_password(id, password) {
    await axios
        .post(API_BASE_URL + '/api/reset-password', {
            id: id,
            password: password,
        },)
        .then((res) => {
            console.log(res);
            if (res.data["isReset"]) {
                alert("비밀번호가 변경되었습니다");
            } else {
                alert("잘못된 비밀번호입니다");
            }
        })
        .catch((err) => {
            console.log(err)
        });
}

async function test() {
    await axios
        .post(API_BASE_URL + '/api/test', {}, {
            withCredentials: true
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
    login,
    logout,
    find_id,
    find_password,
    test
};
