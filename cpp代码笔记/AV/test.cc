#include <iostream>
#include <vector>
int main(int argc, const char **argv)
{
	std::vector<int> vec;
	std::vector<int> vec1 = {1, 2, 3, 4};
	std::vector<int> vec2 = {2};
	std::vector<int> vec3(1, 2);
	for (auto var : vec2)
	{
		std::cout << var;
	}
	return 0;
}