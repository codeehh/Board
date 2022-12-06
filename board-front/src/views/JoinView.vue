<template>
  <div class="container">
    <UserComp />
    <JoinComp />
  </div>
</template>

<script>
import UserComp from "@/components/UserComp.vue";
import JoinComp from "@/components/JoinComp.vue";

export default {
  name: "HomeView",
  components: {
    UserComp,
    JoinComp,
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