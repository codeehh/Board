<template>
  <div class="container">
    <UserComp />
    <BoardComp />
  </div>
</template>

<script>
// @ is an alias to /src
import UserComp from "@/components/UserComp.vue";
import BoardComp from "@/components/BoardComp.vue";

export default {
  name: "HomeView",
  components: {
    UserComp,
    BoardComp,
  },
};
</script>
<style>
.container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  margin-top: 100px;
}
</style>