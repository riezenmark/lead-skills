<template>
  <v-col class="col-12 col-md-4">
    <v-card>
      <v-img height="250" src="images/placeholder-image.png"></v-img>
      <v-card-title>{{ announcement.heading }}</v-card-title>
      <v-card-text>
        <v-row>
          <v-rating :value="announcement.rating" readonly dense class="ml-2"></v-rating>
        </v-row>
        <v-divider class="ma-2"></v-divider>
        <v-row>
          <v-btn class="mx-2">Подробнее</v-btn>
          <v-btn class="mx-2">Записаться</v-btn>
        </v-row>
      </v-card-text>
    </v-card>
  </v-col>
</template>

<script>
export default {
  props: ['announcement']
}
</script>

<style>

</style>