<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" app clipped width="300" color="#1F2638">
      <v-btn block text class="white--text mt-3">
        <v-row align="left" class="ml-5">
          <v-icon left>mdi-teach</v-icon>
          Мои репетиторы
        </v-row>
      </v-btn>
      <v-btn block text class="white--text">
        <v-row align="left" class="ml-5">
          <v-icon left>mdi-bag-checked</v-icon>
          Мои курсы
        </v-row>
      </v-btn>
      <v-btn block text class="white--text">
        <v-row align="left" class="ml-5">
          <v-icon left>mdi-calendar-month</v-icon>
          Моё расписание
        </v-row>
      </v-btn>
      <!--todo v-if чувак не репет-->
      <v-btn block text class="white--text">
        <v-row align="left" class="ml-5">
          <v-icon left>mdi-account-plus</v-icon>
          Стать репетитором
        </v-row>
      </v-btn>
      <!--todo v-if чувак не представитель-->
      <v-btn block text class="white--text">
        <v-row align="left" class="ml-5">
          <v-icon left>mdi-briefcase-plus</v-icon>
          Добавить организацию
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
      <!--todo v-if чувак не авторизован-->
      <v-btn text class="ml-2" @click="mainPage">
        Вход
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container fluid class="pt-0">
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
    drawer: null
  }),
  computed: mapState(['profile']),
  methods: {
    mainPage() {
      if (this.$route.path !== '/') {
        this.$router.push('/')
      }
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
  }
}
</script>

<style>

</style>
