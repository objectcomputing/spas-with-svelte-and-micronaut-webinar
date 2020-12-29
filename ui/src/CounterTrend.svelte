<script lang="ts">
  export let data: {
    day?: number;
    period: number;
    timestamp: number;
    value: number;
  }[];
  export let decimalPlaces;
  export let title: string;
  export let subtitle: string;

  const BAR_DARK = '#e7e7e0';
  const BAR_LIGHT = '#ebebe4';
  const CARD_PADDING = 16; // 1rem
  const SECTION_WIDTH = 400;
  const CHART_HEIGHT = 130;
  const CHART_WIDTH = SECTION_WIDTH - CARD_PADDING - 6;
  const DAYS_IN_MONTH = 31; // It's okay that not all months have this many.
  const BAR_WIDTH = CHART_WIDTH / DAYS_IN_MONTH;

  $: lastValue = data.length ? data[data.length - 1].value : 0;
  $: prevValue = data.length ? data[data.length - 2].value : 0;
  $: lastDelta = lastValue - prevValue;
  $: minValue = data.reduce(
    (acc, item) => Math.min(acc, item.value),
    Number.MAX_VALUE
  );
  $: maxValue = data.reduce((acc, item) => Math.max(acc, item.value), 0);
  $: valueRange = maxValue - minValue;
  $: viewBox = `0 0 ${CHART_WIDTH} ${CHART_HEIGHT}`;

  $: hoverBarIndex = -1;
  $: hoverText = 0;
  $: hoverTransform = '';
  $: hoverX = 0;
  $: hoverY = 0;

  const getBarColor = (index: number) =>
    index % 2 === 0 ? BAR_DARK : BAR_LIGHT;

  function handleHover(index: number, item, event) {
    hoverBarIndex = index;
    const {target} = event;
    const x = target.getAttribute('x');
    hoverX = Number(x) + BAR_WIDTH / 2;
    hoverY = CHART_HEIGHT - 35;
    hoverText = item.value.toFixed(decimalPlaces);
    hoverTransform = `rotate(-90, ${hoverX + 4}, ${hoverY})`;
  }
</script>

<section>
  <div class="card">
    <div class="title">{title}</div>

    <svg height={CHART_HEIGHT} width={CHART_WIDTH} {viewBox}>
      {#each data as item, index}
        <rect
          class={index === hoverBarIndex ? 'hover' : ''}
          x={BAR_WIDTH * index}
          y={((maxValue - item.value) / valueRange) * CHART_HEIGHT}
          width={BAR_WIDTH}
          height={((item.value - minValue) / valueRange) * CHART_HEIGHT}
          fill={getBarColor(index)}
          on:mouseover={event => handleHover(index, item, event)}
          on:mouseout={() => (hoverBarIndex = -1)}>
          {item.day}
          -
          {item.value}
        </rect>
      {/each}
      {#each data as item, index}
        {#if index % 5 === 0}
          <text x={BAR_WIDTH * index} y={CHART_HEIGHT - 2}>{item.day}</text>
        {/if}
      {/each}
      {#if hoverBarIndex !== -1}
        <text
          class="hover-text"
          text-anchor="middle"
          transform={hoverTransform}
          x={hoverX}
          y={hoverY}>
          {hoverText}
        </text>
      {/if}
    </svg>

    <div class="last-value">{lastValue.toFixed(decimalPlaces)}</div>
    <div class="subtitle">{subtitle}</div>
    <div class="last-delta">
      {lastDelta >= 0 ? '+' : ''}{lastDelta.toFixed(decimalPlaces)}
    </div>
  </div>
</section>

<style>
  .card {
    --day-color: #b8b8b8;

    background-color: #f4f4ed;
    border: solid #eee4d5 0.7rem;
    border-radius: 0.5rem;
    color: #cb77a2;
    position: relative;
  }

  .hover {
    fill: lightblue;
  }

  .hover-text {
    pointer-events: none;
    stroke: black;
    transform: rotate(90);
  }

  .last-delta {
    position: absolute;
    bottom: 3rem;
    right: 1rem;

    color: var(--day-color);
    font-size: 1.4rem;
    font-weight: bold;
    pointer-events: none;
  }

  .last-value {
    position: absolute;
    bottom: 4.3rem;
    left: 2rem;

    font-size: 4rem;
    font-weight: bold;
    height: 4rem;
    pointer-events: none;
  }

  section {
    display: inline-block;
    padding: 0 1rem 1rem 0;
    width: 400px;
  }

  .subtitle {
    position: absolute;
    bottom: 3rem;
    left: 2rem;

    font-size: 1.2rem;
    font-weight: bold;
    pointer-events: none;
  }

  svg text {
    fill: var(--day-color);
    font-size: 0.7rem;
  }

  .title {
    box-sizing: border-box;
    color: darkgray;
    font-size: 0.8rem;
    font-weight: bold;
    padding: 0.5rem;
    pointer-events: none;
    text-align: right;
    text-transform: uppercase;
    width: 100%;
  }
</style>
