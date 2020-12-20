<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" app clipped width="300" color="#1F2638">
      <v-btn block text class="white--text mt-3">
        <v-row class="ml-5">
          <v-icon left>mdi-teach</v-icon>
          Мои репетиторы
        </v-row>
      </v-btn>
      <v-btn block @click="showUserAnnouncements" text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-bag-checked</v-icon>
          Мои курсы
        </v-row>
      </v-btn>
      <v-btn @click="openTimetable" block text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-calendar-month</v-icon>
          Моё расписание
        </v-row>
      </v-btn>
      <v-btn v-if="showBecomeTutorButton" block text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-account-plus</v-icon>
          Стать репетитором
        </v-row>
      </v-btn>
      <v-btn v-else block text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-plus-box-multiple</v-icon>
          Добавить уроки
        </v-row>
      </v-btn>
      <v-btn v-if="showAddOrganizationButton" block text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-briefcase-plus</v-icon>
          Добавить организацию
        </v-row>
      </v-btn>
      <v-btn v-else block text class="white--text">
        <v-row class="ml-5">
          <v-icon left>mdi-plus-box-multiple</v-icon>
          Добавить мероприятия
        </v-row>
      </v-btn>
    </v-navigation-drawer>
    <v-app-bar app color="#D0D0D0" clipped-left>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" class="black--text"></v-app-bar-nav-icon>
      <v-btn large text @click="mainPage">
        <v-icon left>mdi-school</v-icon>
        Lead Skills
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn v-if="!profile" text class="ml-2" href="/signup">
        Вход и регистрация
      </v-btn>
      <v-btn v-if="profile" text class="ml-2">
        {{profile.username}}
      </v-btn>
      <v-btn v-if="profile" icon href="/logout">
        <v-icon>mdi-exit-to-app</v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container fluid class="pt-0">
        <v-row style="background: #D0D0D0">
          <v-col>
            <v-tabs background-color="#D0D0D0" color="#1F2638">
              <v-tab @click="findAnnouncementsOfType('TRAINING')">Курсы</v-tab>
              <v-tab @click="findAnnouncementsOfType('EVENT')">Мероприятия</v-tab>
              <v-tab @click="findAnnouncementsOfType('TUTORING')">Репетиторы</v-tab>
            </v-tabs>
          </v-col>
          <v-spacer></v-spacer>
          <v-col>
            <v-row>
              <v-text-field v-model="search" @keyup.enter="searchForAnnouncements" placeholder="Поиск..." dense clearable></v-text-field>
              <v-btn icon @click="searchForAnnouncements">
                <v-icon>mdi-magnify</v-icon>
              </v-btn>
            </v-row>
          </v-col>
        </v-row>
        <router-view :announcements="announcements"></router-view>
      </v-container>
    </v-main>
    <v-footer app color="#D0D0D0" class="white--text">
      <span class="mr-2" style="color: black">Omae Wa</span>
      <span style="color: black">&copy; {{ new Date().getFullYear() }}</span>
      <v-spacer></v-spacer>
    </v-footer>
  </v-app>
</template>

<script>
import {mapState} from 'vuex'

export default {

  data: () => ({
    announcements: [],
    drawer: null,
    search: '',
    showBecomeTutorButton: true,
    showAddOrganizationButton: true
  }),
  computed: mapState(['profile', 'addedAnnouncements']),
  methods: {
    mainPage() {
      if (this.$route.path !== '/') {
        this.$router.push('/')
      }
      this.announcements = []
      this.$resource("/api/announcement").get().then(result =>
          result.json().then(data =>
              data.forEach(announcement => this.announcements.push(announcement))
          )
      )
    },
    searchForAnnouncements() {
      if (this.$route.path !== '/') {
        this.$router.push('/')
      }
      this.announcements = []
      this.$resource('/api/announcement').get({q: this.search}).then(result =>
          result.json().then(data =>
              data.forEach(announcement => this.announcements.push(announcement))
          )
      )
    },
    findAnnouncementsOfType(type) {
      if (this.$route.path !== '/') {
        this.$router.push('/')
      }
      this.announcements = []
      this.$resource('/api/announcement').get({type: type}).then(result =>
          result.json().then(data =>
              data.forEach(announcement => this.announcements.push(announcement))
          )
      )
    },
    showUserAnnouncements() {
      this.announcements = this.addedAnnouncements
    },
    openTimetable() {
      this.$router.push('/timetable')
    }
  },
  props: {
    source: String,
  },
  created() {
    this.$resource("/api/announcement").get().then(result =>
        result.json().then(data =>
            data.forEach(announcement => this.announcements.push(announcement))
        )
    )
    this.showBecomeTutorButton = !this.profile.authorities.includes('TUTOR')
    this.showAddOrganizationButton = !this.profile.authorities.includes('MEMBER')
  }
}
</script>

<style>

</style>
