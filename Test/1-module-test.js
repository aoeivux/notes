/* // var x = function(x, y) {
// 	return x + y;
// }

// const x = (x, y) => x + y

// // better

// const x = (x, y) => {return x+y};

const q1 = ["Jan", "Feb", "Mar"];
const q2 = ["Apr", "May", "Jun"];
const q3 = ["Jul", "Aug", "Sep"];
const q4 = ["Oct", "Nov", "May"];

const year = [...q1, ...q2, ...q3, ...q4];
console.log(year);

const numbers = [12, 31, 53, 98, 61]
let maxValue = Math.max(...numbers)
console.log(maxValue)
console.log("===============");
console.log(...numbers);
console.log("===============");
console.log(numbers);
console.log("===============");

const text = "hello";

let first = numbers.findIndex((value)=> {return value > 17});
console.log(first);

console.log("=================");
let iter = numbers.keys()
for(v of iter){
	console.log(v);
}

function sum(...avg) {
	let sum = 0
	for(let x in avg) {
		sum += x
	}
	return sum
}
console.log("=========================");
let x = sum(1,2,3,4,5)
for(i in numbers){
	console.log(numbers[i]);
} */



console.log(Math.sign(-11));
console.log(Math.sign(0));
console.log(Math.sign(11));
console.log(Number.EPSILON);

const fruits = ["Banana", "Orange", "Apple", "Mango"];
let x = fruits.entries
document.getElementById('app').innerHTML = x
