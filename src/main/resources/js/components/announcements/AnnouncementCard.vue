<template>
  <v-col class="col-12 col-md-4">
    <v-card>
      <v-img height="250" src="/images/placeholder-image.png"></v-img>
      <v-card-title>{{ announcement.heading }}</v-card-title>
      <v-card-text>
        <v-row class="text-center">
          <v-rating :value="announcement.rating" readonly dense half-increments empty-icon="" background-color="orange lighten-3" color="orange" class="ml-2"></v-rating>
        </v-row>
        <v-divider class="ma-2"></v-divider>
        <v-row>
          <v-btn class="mx-2" @click="openAnnouncement">Подробнее</v-btn>
          <!--todo если пользователь не записан-->
          <v-btn @click="getOnCourse" class="mx-2">Записаться</v-btn>
        </v-row>
      </v-card-text>
    </v-card>
  </v-col>
</template>

<script>
import {mapState} from "vuex";

export default {
  computed: mapState(['profile', 'addedAnnouncements']),
  props: ['announcement'],
  methods: {
    openAnnouncement() {
      this.$router.push("/announcement/" + this.announcement.id)
    }
  }
}
</script>

<style>

</style>