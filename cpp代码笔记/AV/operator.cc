#include <iostream>

class Point {

private:
	void debug() {
		std::cout << "debug: " << std::endl;
	}

public:
	int x, y;

    friend std::ostream& operator<<(std::ostream& os, const Point& point) { // 友元函数，访问Point的private成员变量
        os << "Point(" << point.x << ", " << point.y << ")";
        return os;
    }


};

int main() {
	
	/* 	聚合初始化, 能够聚合的条件:
		1. 所有成员都是公有的
		2. 没有用户声明的构造函数
		3. 没有私有、受保护、或虚拟函数 */

    Point p = {3, 4};  // 会按照列表初始化的中顺序，依次初始化成员变量

	// std::cout << p.y << " " << p.x << std::endl;

	// operator<<(std::cout, p); // 完整形式
	std::cout << p; // 简写形式
	
    return 0;
}
