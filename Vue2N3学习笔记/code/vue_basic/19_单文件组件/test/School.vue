<template>
  <div>
	<h1>学校信息</h1>
	<h2>{{name}}</h2>
	<h2>{{address}}</h2>
  </div>
</template>

<script>
export default {
	data() {
		return {
			name: '郑州轻工业大学',
			address: '河南郑州'
		}
	},
	methods: {
		change() {
			console.log(change);
		}
	},
}
</script>
