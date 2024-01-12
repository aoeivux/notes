#include <iostream>
using namespace std;

class Animal
{
	public:
		const std::string str = "animal";

	private:
		const int number = 123;
};

int main(int argc, const char** argv) {
	Animal a;
	// cout << a.number;
	cout << a.str;
	return 0;
}

