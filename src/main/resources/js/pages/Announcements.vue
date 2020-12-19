<template>
  <div>
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