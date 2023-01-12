<template>
  <div class="border rounded-4">
    <div class="board" style="margin: 50px">
      <table class="table table-hover table-sm">
        <thead>
          <tr>
            <th scope="col">글 번호</th>
            <th scope="col" style="width:300px">제목</th>
            <th scope="col" style="width:150px">글쓴이</th>
            <th scope="col">작성시간</th>
            <th scope="col">조회수</th>
            <th scope="col">추천/비추천</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="post in posts">
            <th scope="row">{{ post.postId }}</th>
            <td>{{ post.title }}</td>
            <td>{{ post.nickname }}</td>
            <td>{{ post.time }}</td>
            <td>{{ post.viewCount }}</td>
            <td>{{ post.like }} / {{ post.unlike }}</td>
          </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item"><a class="page-link" id="prev_btn">◀</a></li>
          <li class="page-item"><a class="page-link" id="p1_btn">{{ p1 }}</a></li>
          <li class="page-item"><a class="page-link" id="p2_btn">{{ p2 }}</a></li>
          <li class="page-item"><a class="page-link" id="p3_btn">{{ p3 }}</a></li>
          <li class="page-item"><a class="page-link" id="p4_btn">{{ p4 }}</a></li>
          <li class="page-item"><a class="page-link" id="p5_btn">{{ p5 }}</a></li>
          <li class="page-item"><a class="page-link" id="next_btn">▶</a></li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import { get_posts } from "@/api/index.js";
let page = new URLSearchParams(location.search).get('page');
if (page == null) {
  page = 1;
}
const firstPage = page - ((page - 1) % 5);
console.log(page);
console.log(firstPage);
export default {
  data() {
    return {
      API_BASE_URL: process.env.VUE_APP_API_BASE_URL,
      CLIENT_BASE_URL: process.env.VUE_APP_CLIENT_BASE_URL,
      p1: firstPage,
      p2: firstPage + 1,
      p3: firstPage + 2,
      p4: firstPage + 3,
      p5: firstPage + 4,
      posts: [{ title: '1', time: '1' }],
    }
  },
  async mounted() {
    const prev_btn = document.querySelector("#prev_btn");
    const p1_btn = document.querySelector("#p1_btn");
    const p2_btn = document.querySelector("#p2_btn");
    const p3_btn = document.querySelector("#p3_btn");
    const p4_btn = document.querySelector("#p4_btn");
    const p5_btn = document.querySelector("#p5_btn");
    const next_btn = document.querySelector("#next_btn");

    let $vm = this;

    $vm.posts = await get_posts(0, page);

    prev_btn.onclick = async function () {
      if (firstPage != 1) {
        window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + (firstPage - 5);
      }
    }
    p1_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + $vm.p1;
    }
    p2_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + $vm.p2;
    }
    p3_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + $vm.p3;
    }
    p4_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + $vm.p4;
    }
    p5_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + $vm.p5;
    }
    next_btn.onclick = async function () {
      window.location.href = $vm.CLIENT_BASE_URL + "/?page=" + (firstPage + 5);
    }
  }
};
</script>

<style>
.board {
  width: 1000px;
}
</style>