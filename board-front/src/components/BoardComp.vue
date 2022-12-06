<template>
  <div class="border rounded-4">
    <div class="board" style="margin: 10px">
      <table class="table table-hover table-sm">
        <thead>
          <tr>
            <th scope="col">글 번호</th>
            <th scope="col">제목</th>
            <th scope="col">글쓴이</th>
            <th scope="col">작성시간</th>
            <th scope="col">조회수</th>
            <th scope="col">추천/비추천</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>게시판 만드는 중</td>
            <td>노호준</td>
            <td>2022-12-06 16:53</td>
            <td>1</td>
            <td>10 / 0</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td>Larry the Bird</td>
            <td>@fat</td>
            <td>@twitter</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.board {
  width: 1000px;
}
</style>