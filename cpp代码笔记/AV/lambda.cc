
/**
 * @brief
                lambda形式
                [capture list] (param list)  -> return type {function body}
                其中 capture list 和 function body 必选
 *
 */
#include <algorithm>
#include <iostream>
#include <iterator>
#include <vector>

int main(int argc, const char **argv) {
    int array[] = {12, 1, 26, 12, 85, 90};
    std::vector<int> vec1;
	std::vector<int> vec2;

	
    std::sort(array, array + 6,
              [](const int &a, const int &b) { return a < b; });



    std::for_each(std::begin(array), std::end(array),
                  [](const int &e) { std::cout << e << ' '; });
    return 0;
}
