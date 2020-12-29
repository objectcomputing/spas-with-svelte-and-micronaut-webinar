<script lang="ts">
  import CounterTrend from './CounterTrend.svelte';

  const API_URL_PREFIX = 'http://localhost:8080/api/';
  const OAUTH_URL_PREFIX = 'http://localhost:8080/oauth/';

  $: decimalPlaces = 3;
  $: subtitle = '';
  $: title = '';
  $: widgets = [];

  async function loadData() {
    try {
      let json = await myFetch('widgets');
      if (!json) return; // login
      for (const d of json.data) {
        title = d.title;
        for (const slot of d.slots) {
          subtitle = slot.subtitle;
          const {label} = slot;
          json = await myFetch('values/' + label);
          if (!json) return; // login
          const data = [];
          for (const obj of json.data) {
            const date = new Date(obj.timestamp * 1000);
            obj.day = date.getDate();
            data.push(obj);
          }
          data.reverse(); // to order from oldest to newest
          widgets.push({data, subtitle, title});
        }
      }
      widgets = widgets; // trigger reactivity
    } catch (e) {
      console.error('App.svelte loadData: e =', e);
      alert('loadData error: ' + e.message);
    }
  }

  function login() {
    location.replace(OAUTH_URL_PREFIX + 'login/cognito');
  }

  function logout() {
    sessionStorage.removeItem('token');
    location.replace(OAUTH_URL_PREFIX + 'logout?token=' + token);
  }

  async function myFetch(urlSuffix) {
    const headers = new Headers();
    headers.append('Authorization', 'Bearer ' + token);
    let res = await fetch(API_URL_PREFIX + urlSuffix, {headers});
    if (res.status === 401) {
      login();
    } else if (res.ok) {
      return res.json();
    } else {
      const msg = await res.text();
      throw new Error(msg);
    }
  }

  const params = new URLSearchParams(location.search);
  let token = params.get('token');
  if (token) {
    sessionStorage.setItem('token', token);
  } else {
    token = sessionStorage.getItem('token');
  }

  if (token) {
    loadData();
  } else {
    login();
  }
</script>

<main>
  {#if token}
    <!-- <div>
    <label for="dp">Decimal Places:</label>
    <span>{decimalPlaces}</span>
    <input id="dp" type="range" min="0" max="6" bind:value={decimalPlaces} />
  </div> -->
    <header><button on:click={logout}>Logout</button></header>
    {#each widgets as {data, subtitle, title}}
      <CounterTrend {data} {decimalPlaces} {subtitle} {title} />
    {/each}
  {/if}
</main>

<style>
  button {
    background-color: transparent;
    border: none;
    text-decoration: underline;
  }
  header {
    text-align: right;
  }
</style>
