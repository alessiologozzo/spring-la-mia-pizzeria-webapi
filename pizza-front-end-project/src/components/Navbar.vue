<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-light px-lg-2">
        <div class="container-fluid">
          <div class="navbar-brand logo-container me-md-5">
            <a href="/">
              <img :src="getImage('logo.png')" alt="logo">
            </a>
          </div>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a href="/" class="nav-link active" aria-current="page">Home</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
</template>

<script setup>
  import useGetImage from '@/composables/useGetImage'

  let getImage = useGetImage() 
</script>

<style lang="scss" scoped>
    li:hover {
        background-color: var(--al-light-hover);
    }

    .logo-container {
      width: 180px;
      overflow: hidden;
    }

    img {
      object-fit: cover;
      width: 100%;
      height: 100%;
    }
</style>