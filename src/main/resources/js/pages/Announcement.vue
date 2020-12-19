<template>
  <v-row>
    <v-col class="col-12 col-md-1"></v-col>
    <v-col class="col-12 col-md-7">
      <v-row class="mb-1">{{announcement.heading}}</v-row>
      <v-row class="mt-1">
        <v-rating :value="announcement.rating" readonly half-increments background-color="orange lighten-3" color="orange" dense></v-rating>
      </v-row>
      <v-row class="mt-12">Описание курса:</v-row>
      <v-row>{{announcement.description}}</v-row>
    </v-col>
    <v-col class="col-12 col-md-4">
      <v-img height="250" src="/images/placeholder-image.png"></v-img>
      <v-btn block tile color="#D0D0D0" class="mt-2">Записаться</v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      announcement: null
    }
  },
  created() {
    this.$resource('/api/announcement{/id}').get({id: parseInt(this.$route.params.id)})
        .then(result => result.json()
            .then(data => {
              this.announcement = data
            })
        )
  }
}
</script>

<style>

</style>