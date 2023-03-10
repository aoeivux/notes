<template>
  <div>
	<h1>学生信息</h1>
	<h2>学生：{{name}}</h2> <br>
	<button @click="change">更改名字</button>
  </div>
</template>

<script>
/*  原始写法
	export default Vue.extend({
		name: 'Student',
		template: `
			<div>
				<h1>Welcome to Student Page</h1>
				<h2>{{学生姓名：name}}</h2> <br>
				<button @click="rename">点我换名字</button>
			</div>
		`,
		data() {
			return {
				name: '小红'
			}
		},

		methods: {
			rename() {
				this.name = '小白'
			}
		},
	})
 */

//暴露标准写法
export default {
	data() {
		return {
			name: '小红',
		}
	},

	methods: {
		rename() {
			this.name = '小白'
		}
	},
}
</script>