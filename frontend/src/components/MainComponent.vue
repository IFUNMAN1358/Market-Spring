<template>
  <div class="main-container">

    <section class="intro">
      <div class="intro-text">
        <h2>Добро пожаловать в PetFoodStore!</h2>
        <p>Мы предлагаем широкий ассортимент кормов для собак и кошек, который удовлетворит потребности ваших любимцев. Высокое качество продукции, доступные цены и быстрая доставка.</p>
      </div>
    </section>

    <section class="advantages">
      <h2>Наши преимущества</h2>
      <div class="advantages-list">
        <div class="advantage-item">
          <h3>Качество</h3>
          <p>Все наши продукты изготовлены из высококачественных ингредиентов.</p>
        </div>
        <div class="advantage-item">
          <h3>Быстрая доставка</h3>
          <p>Доставка в течение 24 часов по всему городу.</p>
        </div>
        <div class="advantage-item">
          <h3>Уникальные предложения</h3>
          <p>Регулярные скидки и акции для наших клиентов.</p>
        </div>
      </div>
    </section>

    <section class="reviews">
      <h2>Отзывы клиентов</h2>
      <div class="reviews-list">
        <div class="review" v-for="review in reviews" :key="review.id">
          <p>{{ review.text }}</p>
          <p class="reviewer">- {{ review.reviewer }}</p>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  name: 'MainComponent',
  data() {
    return {
      reviews: [
        { id: 1, text: 'Отличный магазин! Быстрая доставка и качественные корма.', reviewer: 'Иван Иванов' },
        { id: 2, text: 'Мои кошки в восторге от нового корма!', reviewer: 'Анна Смирнова' },
        { id: 3, text: 'Спасибо за быструю доставку и свежий корм.', reviewer: 'Александр Кравченко' },
        { id: 4, text: 'Самые низкие цены и приятная поддержка!', reviewer: 'Марина Ивлева' },
      ]
    };
  }
};
</script>

<style scoped>
.main-container {
  background-color: #566E3A;
  color: #BBD49D;
  border-radius: 10px;
  font-family: 'Roboto', sans-serif;
  padding: 20px;
}

.intro {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.intro-text {
  text-align: center;
}

h2, h3 {
  text-align: center;
}

.advantages {
  margin-bottom: 20px;
}

.advantages-list {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.advantage-item {
  margin-bottom: 10px;
  text-align: center;
}

.product img {
  max-width: 100%;
  border-radius: 10px;
}

.reviews {
  margin-bottom: 20px;
}

.reviews-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.review {
  background-color: #BBD49D;
  color: #566E3A;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  max-width: 45%;
  text-align: center;
}

.reviewer {
  font-style: italic;
  margin-top: 5px;
}

button {
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
}

button:hover {
  background-color: #a7bf8f;
}

@media (max-width: 768px) {
  .intro,
  .advantages-list,
  .reviews-list {
    flex-direction: column;
  }

  .review {
    max-width: 100%;
  }
}
</style>
