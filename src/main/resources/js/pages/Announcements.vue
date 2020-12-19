<template>
  <div>
    <v-row style="background: #D0D0D0">
      <v-col>
        <v-tabs background-color="#D0D0D0" color="#1F2638">
          <v-tab>Курсы</v-tab>
          <v-tab>Мероприятия</v-tab>
          <v-tab>Репетиторы</v-tab>
        </v-tabs>
      </v-col>
      <v-spacer></v-spacer>
      <v-col>
        <v-row>
          <v-text-field placeholder="Поиск..." dense clearable></v-text-field>
          <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <announcement-card v-for="announcement in announcements" :key="announcement.id"
                         :announcement="announcement"></announcement-card>
    </v-row>
  </div>
</template>

<script>
import AnnouncementCard from "components/announcements/AnnouncementCard.vue"

export default {
  components: {
    AnnouncementCard
  },
  props: ['announcements'],
  data() {
    return {
      announcement: null
    }
  }
}
</script>

<style>

</style>