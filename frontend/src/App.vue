<template>
  <router-view />

</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  height: 100vh;
}
</style>

<script>
export default {
  mounted() {
    window.embeddedChatbotConfig = {
      chatbotId: "Yq7OIJ7i2sB-nzCuhRMuN",
      domain: "www.chatbase.co"
    }

    let script = document.createElement('script');
    script.src = "https://www.chatbase.co/embed.min.js";
    script.setAttribute('chatbotId', 'Yq7OIJ7i2sB-nzCuhRMuN');
    script.setAttribute('domain', 'www.chatbase.co');
    script.defer = true;
    document.body.appendChild(script);
  }
}

</script>
