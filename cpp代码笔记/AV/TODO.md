# c++ 基础

- 变量



- 常量

  1. 字面常量

			整数常量：例如 10, -5, 1000 等。

			浮点数常量：例如 3.14, 0.5, -8.0 等。

			字符常量：用单引号括起来的单个字符，例如 'A', 'b', '1' 等。

			字符串常量：用双引号括起来的一串字符，例如 "Hello", "C++" 等。

			布尔常量：true 和 false，表示逻辑真和逻辑假。


  2. 符号常量

		```c++
		#const int MAX_VALUE 120
		#const string 
		```

- 数据类型
  -  数组(Array)



  -  向量(Vector)
		```c++
		#include <vector>
		#include <iostream>
		std::vector<int> vec;
		std::vector<int> vec1 = {1,2,3,4};
		std::vector<int> vec2(1,2);
		std::cout << vec2;
		```

  -  链表（LinkedList）


  -  栈和堆(Stack和Queue)
  -  树(Tree)

			```c++
				class TreeNode{
				public:
					int val;
					TreeNode* left;
					TreeNode* right;
					TreeNode(int value) : val(value), left(nullptr), right(nullptr);
				};
			```

- 运算符


- 流程控制语句

- 数组
 

- 指针
 

- 宏定义
 

- 结构体
 

- 链表
 

- IO操作
