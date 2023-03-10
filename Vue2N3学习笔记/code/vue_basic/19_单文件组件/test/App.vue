<template>
	<div>
		<sc></sc>
		<st></st>
	</div>
</template>

<script>

import School from './School.vue'
import Student from './Student.vue'

export default {
	components: {
		School: sc,
		Student: st
	},
}
</script>