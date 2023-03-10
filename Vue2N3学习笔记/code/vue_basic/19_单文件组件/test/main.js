import App from './App.vue'


new Vue({
	el: '#root',
	template: `<App></App>`,
	components: {
/*
		完整写法
		App:App
		当重名的时候，只需要写一个 
*/
		App
	},
})