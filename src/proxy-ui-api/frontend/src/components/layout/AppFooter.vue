<!--
   The MIT License
   Copyright (c) 2019- Nordic Institute for Interoperability Solutions (NIIS)
   Copyright (c) 2018 Estonian Information System Authority (RIA),
   Nordic Institute for Interoperability Solutions (NIIS), Population Register Centre (VRK)
   Copyright (c) 2015-2017 Estonian Information System Authority (RIA), Population Register Centre (VRK)

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
   THE SOFTWARE.
 -->
<template>
  <v-footer color="grey lighten-3">
    <v-layout align-center justify-center>
      <v-row class="xrd-tab-max-width">
        <v-col cols="6" sm="3" class="pt-2">
          <v-img
            :src="require('../../assets/xroad_logo_64_purple.png')"
            height="64"
            width="128"
            max-height="64"
            max-width="128"
          ></v-img>
        </v-col>
        <v-col cols="3" class="footer-col pt-5">
          <v-row>
            <v-col>
              <span class="footer-title">{{
                $t('footer.software.title')
              }}</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="py-0">
              {{ $t('footer.software.versionPrefix') }}&nbsp;
              <span data-test="app-footer-server-version">{{
                securityServerVersion.info || ''
              }}</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <a
                class="footer-link"
                target="_blank"
                href="https://x-road.global/feedback"
              >
                {{ $t('footer.software.feedback') }}
              </a>
            </v-col>
          </v-row>
        </v-col>
        <v-col class="footer-col pt-5">
          <v-row>
            <v-col>
              <span class="footer-title">{{
                $t('footer.copyright.title')
              }}</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="py-0">
              <a class="footer-link" href="https://niis.org/" target="_blank">
                {{ $t('footer.copyright.company') }}
              </a>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <a
                class="footer-link"
                href="https://x-road.global/xroad-licence-info"
                target="_blank"
              >
                {{ $t('footer.copyright.licenceInfo') }}
              </a>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-layout>
  </v-footer>
</template>

<script lang="ts">
import Vue from 'vue';
import { mapGetters } from 'vuex';

export default Vue.extend({
  name: 'app-footer',
  computed: {
    ...mapGetters(['securityServerVersion']),
  },
});
</script>

<style lang="scss" scoped>
$text-color: darken(#9e9e9e, 15);

.footer-title {
  color: $text-color;
  text-transform: uppercase;
  font-size: 0.9rem;
  font-weight: bold;
}

.footer-col {
  color: $text-color;
  font-size: 0.875rem;
}

.footer-link {
  color: $text-color;
}
</style>
